import { BatchService } from './../../services/batch-service/batch.service';
import { Component, OnInit } from '@angular/core';
import { CurriculumService } from '../../services/curriculum-service/curriculum.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { AssociateService } from '../../services/associate-service/associate.service';
import { Associate } from '../../models/associate.model';

@Component({
  selector: 'app-predictions',
  templateUrl: './predictions.component.html',
  styleUrls: ['./predictions.component.css']
})
@AutoUnsubscribe
export class PredictionsComponent implements OnInit {

  start:any;
  end:any;
  public detailsReady = false;
  public startDateString: string;
  public endDateString: string;
  public startDate: Date = new Date();
  public endDate: Date = new Date();
  public technologies: any[];
  public expanded = true;
  public results: any[];
  public message = "";
  public selectedBatch: number;
  public noBatches: boolean;
  public batches: Object;
  public batchNumberAssociates: number[];
  public associates: Associate[];
  public techNeeded: number[];
  

  constructor(private ss: CurriculumService, private as: AssociateService, private bs: BatchService) { }

  ngOnInit() {
    this.getListofCurricula();
    this.techNeeded = [];
    this.results = [];
    this.noBatches = false;

    this.setInitialDates();
    this.generateDates();
  }

  /**
   * 1806_Austin_M
   * Get a list of curriculum from backend end to generate input fields
   */
  getListofCurricula() {
    this.ss.getAllCurricula().subscribe(
      data => {
        console.log("curricula", data);
        const tempArray = [];
        for (let i = 0; i < data.length; i++) {
          let tech = data[i];
          let localtech = {
            id: tech.id,
            name: tech.name,
          }
          tempArray.push(localtech);
        }
        this.technologies = tempArray;
      },
      err => {
      }
    );
  }

  /**
   * 1806_Austin_M
   * Gets the current date and sets the strings bound to the date inputs.
   * End date is the current date, start date is the first of the current year.
   */
  setInitialDates(){
    let now = new Date(Date.now());
    this.endDateString= now.toJSON().substring(0,10);
    now.setMonth(0);
    now.setDate(1);
    this.startDateString = now.toJSON().substring(0,10);
  }

  /**
   * 1806_Austin_M
   * Parses the date string to a date object.
   * Done onchange of date fields.
   */
  generateDates(){
    this.startDate = new Date(this.startDateString);
    this.endDate = new Date(this.endDateString);
  }

  /**
   * 1806_Austin_M
   * Performs a query for each requested that has input 
   * (tech without input is skipped within the getPredicton() method). 
   * 
   * NOTE: that this will make connection to the DB FOR EACH TECHNOLOGY WITH INPUT 
   * should be changed to a single query in back end
   */
  getAllPredictions(){
    console.log("get all predictions");
    this.results = [];
    for(let k in this.techNeeded){
      this.getPrediction(+k, false);
    }
    this.results.sort((o1,o2) => o1['technologyIndex'] - o2['technologyIndex'])
  }

  /**
   * 1806_Austin_M
   * Fetch details for a single technology, filters previous results to prevent 
   * duplicate entries and sorts results by index on return
   * 
   * @param techIndex index in technologies array to fetch predictions for
   * @param isUpdate true if part of single fetch; false when part of a batch
   */
  getPrediction(techIndex: number, isUpdate: boolean) {

    this.detailsReady = false;
    this.noBatches = false;

    if(isUpdate)
      this.results = this.results.filter(o => o['technologyIndex'] != techIndex);

    let techName = this.technologies[techIndex]["name"];
    if(this.techNeeded[techIndex] == undefined || this.techNeeded[techIndex] <= 0)
      return;

      console.log("date for count", this.startDate, this.endDate);

    this.bs.getAssociateCountByCurriculum(new Date(this.startDate), new Date(this.endDate), techName).subscribe(
      data => {
        console.log(data)
        this.results.push({
                  technologyIndex: techIndex,
                  technology: techName,
                  requested: this.techNeeded[techIndex],
                  available: data["associateCount"]
                });
        this.results.sort((o1,o2) => o1['technologyIndex'] - o2['technologyIndex'])
      },

      err => err
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

    let startTime = new Date(this.startDate);
    let endTime = new Date(this.endDate);
    
    this.detailsReady = false;
    this.noBatches = false;

    this.selectedBatch = tech;
    /*
      1806_Andrew_H
      This method populates the batches object using a json object.
      The json object is an array with each element containing info on the batch name,
      number of associates in the batch, and the batch's end date.
    */
    this.bs.getBatchDetails(startTime, endTime,tech).subscribe(
      data => {
        this.batches = data;
        this.detailsReady = true;
        if(this.batches['courseBatches'].length == 0){
          this.noBatches = true;
        }
      },
      error => {
        console.log(error);
      });
  }
}
