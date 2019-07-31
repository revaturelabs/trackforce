import { ClientService } from './../../services/client-service/client.service';
import { BatchService } from './../../services/batch-service/batch.service';
import { Component, OnInit, Output, EventEmitter, OnDestroy, ViewChild } from '@angular/core';
import { CurriculumService } from '../../services/curriculum-service/curriculum.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { AssociateService } from '../../services/associate-service/associate.service';
import { Associate } from '../../models/associate.model';
import { Client } from '../../models/client.model';
import { DateService } from '../../services/date-service/date.service';
import { DateTimePickerComponent } from '../datetimepicker/datetimepicker.component';
import { Helpers } from '../../lsHelper';


@Component({
  selector: 'app-predictions',
  templateUrl: './predictions.component.html',
  styleUrls: ['./predictions.component.css'],
  /**
   * [ClientService]
   *
   * This service was added as a provider on 11/10/2018.
   * It was needed in order to successfully create the
   * component in the Jasmine test spec, and test it.
   */
  providers: [ClientService]
})
@AutoUnsubscribe
export class PredictionsComponent implements OnInit, OnDestroy {

  //bat date-timepicker------
  @ViewChild('start') startDateTimePicker: DateTimePickerComponent;
  @ViewChild('end') endDateTimePicker: DateTimePickerComponent;

  start: any;
  end: any;
  public detailsReady = false;
  public startDateString: string;
  public endDateString: string;
  public startDate: Date = new Date();
  public endDate: Date = new Date();
  public technologies: any[];
  public expanded = true;
  public results: any[];
  public message = '';
  public selectedBatch: number;
  public noBatches: boolean;
  public batches: Object;
  public batchNumberAssociates: number[];
  public associates: Associate[];
  public techNeeded: number[];
  public loadingTechnologies: boolean;
  public loadingPredictions: boolean;
  public loadingDetails: boolean;
  public maxAssociates = 1000;
  public showEmpty = true;
  //public curriculums: any[];

  //Batch-list date-picker-----------------
  @Output() changeDateEm = new EventEmitter<Date>();

  stringStart: string;
  stringEnd: string;

  dateRangeMessage: string;
  showDateRangeError = false;
  dateError: boolean;

  //Assoc----------------------------------
  public clients: Client[];
  public curriculums: Set<string>; //stored unique curriculums
  public isDataReady = false;
  //----------------------------------------------------------

  //used for filtering
  //  searchByStatus = '';
  //  searchByClient = '';
  //  searchByText = '';
  //  searchByCurriculum = '';
  //  searchByVerification = '';

  //used for ordering of rows
  public desc = false;
  public sortedColumn = '';

  //added: cs

  changeDate() {
    this.changeDateEm.emit(this.startDate);
  }


  constructor(
    private curriculumService: CurriculumService,
    private associateService: AssociateService,
    private batchService: BatchService,
    private clientService: ClientService,
    private dateService: DateService,
    private localStorageHelper: Helpers) {
    this.curriculums = new Set<string>();
  }
  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    this.localStorageHelper.removeStorageItem("clientGetAll");
  }

  ngOnInit() {
    this.techNeeded = [];
    this.results = [];
    this.noBatches = false;
    this.loadingPredictions = false;
    this.loadingDetails = false;
    this.loadingTechnologies = false;
    //this.curriculums = [];

    //assoc-----------------------------------------------------------------
    this.getAllAssociates(); //TODO: change method to not use local storage
    this.getClientNames();
    //----------------------------------------------------------------------

    this.getListofCurricula();
    this.setInitialDates();
    this.generateDates();
  }

  //assoc--------------------------------------------
  getAllAssociates() {
    this.associateService.getAllAssociates().subscribe(data => {
      // this.associates.length = 0;
      this.associates = data;
      for (const associate of this.associates) {
        //get our curriculums from the associates
        if (
          associate.batch !== null &&
          associate.batch.curriculumName !== null
        ) {
          this.curriculums.add(associate.batch.curriculumName.name);
        }
        if (associate.batch && associate.batch.batchName === 'null') {
          associate.batch.batchName = 'None';
        }
      }
      this.curriculums.delete('');
      this.curriculums.delete('null');
      this.isDataReady = true;
    },
      error => console.error('Error in predictions.component.ts getAllAssociates(): ', error.message)
    );
  }

  /**
  * Fetch the client names
  */
  getClientNames() {
    this.clientService.getAllClients().subscribe(data => {
      this.clients = data;
    },
      error => console.error('Error in predictions.component.ts getClientNames(): ', error.message)
    );
  }

  //------------------------------------------------------------------------------------

  /**
   * 1806_Austin_M
   * Get a list of curriculum from backend end to generate input fields
   */
  getListofCurricula() {
    this.message = '';
    this.loadingTechnologies = true;

    this.curriculumService.getAllCurricula().subscribe(
      data => {
        const tempArray = [];
        for (let i = 0; i < data.length; i++) {
          const tech = data[i];
          const localtech = {
            id: tech.id,
            name: tech.name,
          }
          tempArray.push(localtech);
        }
        this.technologies = tempArray;
        this.loadingTechnologies = false;
      },
      err => {
        this.message = 'Server error when loading technologies.';
        this.loadingTechnologies = false;
      }
    );
  }

  /**
   * 1806_Austin_M
   * Gets the current date and sets the strings bound to the date inputs.
   * End date is the current date, start date is the first of the current year.
   */
  setInitialDates() {
    const now = new Date();
    this.endDateString = now.toJSON().substring(0, 10);
    now.setMonth(0);
    now.setDate(1);
    now.setFullYear(2017);
    this.startDateString = now.toJSON().substring(0, 10);
  }

  // }
  /**
   * 1806_Austin_M
   * Parses the date string to a date object.
   * Done onchange of date fields.
   */
  generateDates() {
    //convert string into base10 integer
    const startYearParsed = parseInt(this.startDateString.substring(0, 4), 10);
    const endYearParsed = parseInt(this.endDateString.substring(0, 4), 10);

    if (startYearParsed < 2012 || endYearParsed < 2012) {
      this.dateRangeMessage = 'Enter a valid year';
      this.showDateRangeError = true;
    } else {
      this.startDate = new Date(this.startDateString);
      this.endDate = new Date(this.endDateString);
      this.showDateRangeError = false;
    }
  }

  /**
   * 1806_Austin_M
   * Performs a query for each requested that has input
   * (tech without input is skipped within the getPredicton() method).
   *
   * NOTE: that this will make connection to the DB FOR EACH TECHNOLOGY WITH INPUT
   * should be changed to a single query in back end
   */
  getAllPredictions() {
    this.results = [];
    for (const tech in this.techNeeded) {
      if(tech) {
        this.getPrediction(+tech, false);
      }
    }

    this.results.sort((o1, o2) => o1['technologyIndex'] - o2['technologyIndex'])
  }

  /**
   * 1806_Austin_M
   * Fetch details for a single technology, filters previous results to prevent
   * duplicate entries and sorts results by index on return
   *
   * @param techIndex index in technologies array to fetch predictions for
   * @param isUpdate true if part of single fetch; false when part of a batch
   *
   * 1809_Courie_G
   * getPrediction now only gets called from getAllPredictions instead of on
   * every change of the table data.
   */
  getPrediction(techIndex: number, isUpdate: boolean) {
    if (this.results.length === 0) {
      this.loadingPredictions = true;
    }
    this.detailsReady = false;
    this.noBatches = false;

    if (isUpdate) {
      this.results = this.results.filter(o => o['technologyIndex'] !== techIndex);
    }

    const techName = this.technologies[techIndex]['name'];
    if (this.techNeeded[techIndex] === undefined || this.techNeeded[techIndex] <= 0 || this.techNeeded[techIndex] > this.maxAssociates) {
      return;
    }


    this.batchService.getAssociateCountByCurriculum(this.startDate, this.endDate, techName).subscribe(
      data => {
        this.results.push({
          technologyIndex: techIndex,
          technology: techName,
          requested: this.techNeeded[techIndex],
          available: data['associateCount']
        });
        this.results.sort((o1, o2) => o1['technologyIndex'] - o2['technologyIndex'])
        this.loadingPredictions = false;
      },

      err => console.error('Error in predictions.component.ts getPredictions(): ', err.message)
    )
  }

  /**
   * 1806_Andrew_H_Austin_M
   * Fetches details of a selected curriculum. The details include all batches
   * That start and end within the given time span. Resets previous data so that
   * old information is not present while loading.
   * @param tech
   */
  getDetails(tech) {

    this.detailsReady = false;
    this.loadingDetails = true;
    this.noBatches = false;


    this.selectedBatch = tech;
    /*
      1806_Andrew_H
      This method populates the batches object using a json object.
      The json object is an array with each element containing info on the batch name,
      number of associates in the batch, and the batch's end date.
    */
    this.batchService.getBatchDetails(this.startDate, this.endDate, tech).subscribe(
      data => {
        this.batches = data;
        this.detailsReady = true;
        this.loadingDetails = false;
        if (this.batches['courseBatches'].length === 0) {
          this.noBatches = true;
        }
      },
      error => {
        console.error("Error in predictions.component.ts getDetails()", error.message);
      });
  }
}
