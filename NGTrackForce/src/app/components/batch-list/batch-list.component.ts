/** @Author Princewill Ibe **/
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Batch } from '../../models/batch.model';
import { BatchService } from '../../services/batch-service/batch.service';
import { ThemeConstants } from '../../constants/theme.constants';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { ChartOptions, SideValues } from '../../models/ng2-charts-options.model';
import { Color } from 'ng2-charts';


// TODO: LABELS SHOULD PROPERLY WRAP
/**
 * @class BatchListComponent
 * @description This component is the batch list page
 *        to get all batches and show meaningful information
 */
@Component({
  selector: 'app-batch-list',
  templateUrl: './batch-list.component.html',
  styleUrls: ['./batch-list.component.css']
})
@AutoUnsubscribe
export class BatchListComponent implements OnInit {

  start: any;
  end: any;
  pieChartType = 'pie';
  startDate: Date = new Date();
  endDate: Date = new Date();
  batches: Batch[];
  curriculumNames: string[];
  curriculumCounts: number[];
  dataReady = false;
  dataEmpty = false;
  batchColors: Array<Color> = ThemeConstants.BATCH_COLORS;
  counter = 0;

  stringStart: string;
  stringEnd: string;

  dateRangeMessage: string;
  showDateRangeError = false;

  chartOptions: ChartOptions = ChartOptions.createOptionsSpacing(
    new SideValues(-100, 0, 0, 0),
    new SideValues(0, 0, 0, 0),
    'right', false, false
  );


  constructor(private batchService: BatchService, private authService: AuthenticationService) {
  }

  /**
   * load default batches on initialization
   */
  ngOnInit() {
    // get current user
    const user = this.authService.getUser();
    //user is a trainer they can only see their batches
    if (user.role === 2) {
      this.dataReady = false;
      this.batchService.getAllBatches().subscribe(
        batches => {
          // filter out batches that don't have an associated trainer
          this.batches = batches.filter(
            batch => {
              if (batch.trainer.firstName === this.authService.getTrainer().firstName) {
                return true;
              }
              if (batch.coTrainer) {
                return batch.coTrainer.includes(this.authService.getTrainer());
              }
              return false;
            }
          );
        },
        error => {
          console.log("========ERROR==========");
          console.log(error);
          console.log("=======================");
        }
      );
      this.dataReady = true;
    }
    else {
      // set default dates displayed on page
      this.startDate.setMonth(new Date().getMonth() - 3);
      this.endDate.setMonth(new Date().getMonth() + 3);
      const startTime = Date.now();
      this.dataReady = false;

      this.stringStart = this.startDate.toJSON().substring(0, 10);
      this.stringEnd = this.endDate.toJSON().substring(0, 10);


      this.batchService.getAllBatches().subscribe(
        (batches) => {
          this.batches = [];
          for ( var bat in batches ) 
          {
            if ( batches[bat]['startDate'] != null && 
                 batches[bat]['endDate'] != null && 
                 batches[bat]['location'] != null && 
                 batches[bat]['curriculumName'] != null )
            {
              this.batches[this.counter] = batches[bat];
              this.counter++;
            }
            else
            {

            }
          }

          this.updateCountPerCurriculum();
          this.dataReady = true;
          const elapsed = Date.now() - startTime;
        }
      );
      this.resetToDefaultBatches();
     
    }
  }


  /**
   * after user selects date range, this handles updating the data,
   * and the corresponding graph accordingly
   */
  public applySelectedRange() {

    let dateStartDate = new Date(this.stringStart);
    let dateEndDate = new Date(this.stringEnd);



    let longStartDate: number;
    let longEndDate: number;

    this.resetFormWarnings();

    if (this.startDate && this.endDate) {
      longStartDate = dateStartDate.getTime();
      longEndDate = dateEndDate.getTime();
      console.log("=======================================");
      console.log(longStartDate);
      console.log(longEndDate);
      console.log("=======================================");


      if (longStartDate > longEndDate){
        this.dateRangeMessage = "The to date cannot be before the from date, please try another date.";
        this.showDateRangeError = true;
      } else {
        this.updateBatches();
      }
    }
  }

  public resetFormWarnings(){
    if(this.showDateRangeError == true)
      this.showDateRangeError = false;
  }

  /*
   * reset to original batches
   */
  public resetToDefaultBatches() {
    this.startDate = new Date();
    this.startDate.setMonth(new Date().getMonth() - 3);
    this.endDate = new Date();
    this.endDate.setMonth(new Date().getMonth() + 3);
    const startTime = Date.now();
    this.dataReady = false;
    this.batchService.getAllBatches().subscribe(
      (batches) => {
        this.batches = batches;
        this.updateCountPerCurriculum();
        this.dataReady = true;
        const elapsed = Date.now() - startTime;
      },
    );
 
  }

  /**
   * @function updateBatches
   * @memberof BatchListComponent
   * @description This function will return a JavaScript object that contains
   *              all of the batches within startDate and endDate
   */
  public updateBatches() {
    const user = this.authService.getUser();
    if (user.role === 2) {
      this.dataReady = false;
      this.batchService.getAllBatches().subscribe(
        batches => {
          // filter out batches that don't have an associated trainer
          this.batches = batches.filter(
            batch => {
              if (batch.trainer.firstName !== this.authService.getTrainer().firstName) {
                return false;
              }
              if (batch.coTrainer) {
                if (!batch.coTrainer.includes(this.authService.getTrainer())) {
                  return false;
                }
              }
              let dateStartDate = new Date(this.startDate);
              let dateEndDate = new Date(this.endDate);
              let longStartDate = dateStartDate.getTime();
              let longEndDate = dateEndDate.getTime();

              if (batch.startDate && batch.endDate) {
                return batch.startDate > longStartDate && batch.endDate < longEndDate;
              }
              else {
                return false;
              }
            }
          );
        }
      );
      this.dataReady = true;
    }
    else {
      this.dataReady = false;
      this.batchService.getAllBatches().subscribe(
        batches => {
          // they serve as flags in template
          this.batches = batches.filter(
            batch => {
              let dateStartDate = new Date(this.startDate);
              let dateEndDate = new Date(this.endDate);
              let longStartDate = dateStartDate.getTime();
              let longEndDate = dateEndDate.getTime();

              if (batch.startDate && batch.endDate) {
                return batch.startDate > longStartDate && batch.endDate < longEndDate;
              }
              else {
                return false;
              }
            }
          );
          this.dataReady = false;
          this.batches = this.batches.sort((a, b) => {
            if (a.startDate < b.startDate) {
              return -1;
            } else if (a.startDate > b.startDate) {
              return 1;
            }
            return 0;
          }
          );
          this.updateCountPerCurriculum();
          this.dataReady = true;
        },
      );
    }
  }


  /**
  * @function updateCountPerCurriculum
  * @memberof BatchListComponent
  * @description This function will return an object that contains
  *              all of the batches within startDate and endDate
  *
  */
  updateCountPerCurriculum() {
    this.curriculumNames = this.curriculumCounts = null;
    const curriculumCountsMap = new Map<string, number>();

    this.dataEmpty = this.batches.length === 0;

    for (const batch of this.batches) {
      if (batch.curriculumName) {
        let count = curriculumCountsMap.get(batch.curriculumName.name);
        if (count === undefined) {
          count = 0;
        }
        curriculumCountsMap.set(batch.curriculumName.name, count + 1);
      }
    }

    // note: for angular/ng2-charts to recognize the changes to chart data, the object reference has to change
    this.curriculumNames = Array.from(curriculumCountsMap.keys());
    this.curriculumCounts = Array.from(curriculumCountsMap.values());
  }
}
