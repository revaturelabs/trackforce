<<<<<<< HEAD
/** @Author Princewill Ibe **/
<<<<<<< HEAD
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
<<<<<<< HEAD
import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
=======
=======
=======
>>>>>>> TestAdmin "Batch" tab warnings
import { AuthenticationService } from "../../services/authentication-service/authentication.service";
import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  ViewChild
} from '@angular/core';
>>>>>>> TestAdmin "Batch" tab date limits
import { Batch } from '../../models/batch.model';
import { BatchService } from '../../services/batch-service/batch.service';
import { ThemeConstants } from '../../constants/theme.constants';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { ChartOptions, SideValues } from '../../models/ng2-charts-options.model';
import { Color } from 'ng2-charts';
import 'rxjs/add/observable/from';
import { DateService } from '../../services/date-service/date.service';
import { DateTimePickerComponent } from '../datetimepicker/datetimepicker.component';


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
<<<<<<< HEAD
<<<<<<< HEAD

  @ViewChild('start') startDateTimePicker:DateTimePickerComponent;
  @ViewChild('end') endDateTimePicker:DateTimePickerComponent;
=======
<<<<<<< HEAD
  @ViewChild('start')
  startDateTimePicker: DateTimePickerComponent;
  @ViewChild('end')
=======
=======
>>>>>>> TestAdmin "Batch" tab warnings
  @ViewChild("start")
  startDateTimePicker: DateTimePickerComponent;
  @ViewChild("end")
  endDateTimePicker: DateTimePickerComponent;
>>>>>>> TestAdmin "Batch" tab date limits

  start: any;
  end: any;
  pieChartType = 'pie';
  startDate: Date = new Date();
  endDate: Date = new Date();
  batches: Batch[];
  filteredBatches: Batch[];
  curriculumNames: string[];
  curriculumCounts: number[];
  dataReady = false;
  dataEmpty = false;
  batchColors: Array<Color> = ThemeConstants.BATCH_COLORS;
  counter = 0;
  minDate: number = Date.now();
  @Output() changeDateEm = new EventEmitter<Date>();

  stringStart: string;
  stringEnd: string;

  dateRangeMessage: string;
  showDateRangeError = false;
  dateError: boolean;

<<<<<<< HEAD
  changeDate(){
    this.changeDateEm.emit(this.startDate);
  }

=======
>>>>>>> TestAdmin "Batch" tab date limits
  chartOptions: ChartOptions = ChartOptions.createOptionsSpacing(
    new SideValues(-100, 0, 0, 0),
    new SideValues(0, 0, 0, 0),
    'right', false, false
  );
<<<<<<< HEAD

<<<<<<< HEAD
=======
  changeDate() {
    this.changeDateEm.emit(this.startDate);
  }
>>>>>>> TestAdmin "Batch" tab date limits
=======
  // This Function Doesn't seem to be used
  // changeDate() {
  //   this.changeDateEm.emit(this.startDate);
  // }
>>>>>>> TestAdmin "Batches" Submit/Reset Functionality

  constructor(
    private batchService: BatchService,
    private authService: AuthenticationService,
    private dateService: DateService
  ) {}

  /**
   * load default batches on initialization
   */
  ngOnInit() {
    // get current user
    const user = this.authService.getUser();
    //user is a trainer they can only see their batches
    if (user.role === 2) {
      this.dataReady = false;

      this.batchService.getBatchesWithinDates(this.startDate,this.endDate).subscribe(
        batches => {
          // filter out batches that don't have an associated trainer
          this.batches = batches.filter(
            batch => {
              if (batch.trainer.firstName !== this.authService.getTrainer().firstName) {
                return false;
              }
              if (batch.coTrainer) {
                return batch.coTrainer.includes(this.authService.getTrainer());
              }

              if (batch.startDate < this.minDate){
                this.minDate = batch.startDate;
              }

              this.startDate = new Date(this.minDate);
              this.dateService.changeDates(this.startDate, this.endDate);

              return true;
            }
          );
          this.filteredBatches = this.batches;
          this.updateCountPerCurriculum();
          this.dataReady = true;
        },
        error => {
          console.log(error);
        }
      );
    }
    else {
      // set default dates displayed on page
      this.startDate.setMonth(new Date().getMonth() - 3);
      this.endDate.setMonth(new Date().getMonth() + 3);
      this.dataReady = false;

      this.startDate.setMonth(-7);

      this.stringStart = this.startDate.toJSON().substring(0, 10);
      this.stringEnd = this.endDate.toJSON().substring(0, 10);
      this.batchService.getBatchesWithinDates(this.startDate,this.endDate).subscribe(
        batches => {
          // filter out batches that don't have an associated trainer
          this.batches = batches;

          this.batches.forEach(batch => {
            if (batch.startDate < this.minDate){
              this.minDate = batch.startDate;
              }
            }
          );
          this.filteredBatches = this.batches;
          this.startDate = new Date(this.minDate);
          this.dateService.changeDates(this.startDate, this.endDate);
          this.updateCountPerCurriculum();
          this.dataReady = true;
        },
        error => {
          console.log(error);
        }
      );

    }
  }


  /**
   * after user selects date range, this handles updating the data,
   * and the corresponding graph accordingly
   */
  public applySelectedRange() {
<<<<<<< HEAD
    if (!this.dateError){
    this.startDate = new Date(this.stringStart);
    this.endDate = new Date(this.stringEnd);
=======
    if (!this.dateError) {
      this.startDate = new Date(this.stringStart);
      this.endDate = new Date(this.stringEnd);

      let longStartDate: number;
      let longEndDate: number;

      this.resetFormWarnings();

      if (this.startDate && this.endDate) {
        longStartDate = this.startDate.getTime();
        longEndDate = this.endDate.getTime();

<<<<<<< HEAD
=======
>>>>>>> TestAdmin "Batch" tab date limits

    let longStartDate: number;
    let longEndDate: number;

    this.resetFormWarnings();

    if (this.startDate && this.endDate) {
      longStartDate = this.startDate.getTime();
      longEndDate = this.endDate.getTime();

<<<<<<< HEAD

      if (longStartDate > longEndDate) {
        this.dateRangeMessage = "The to date cannot be before the from date, please try another date.";
        this.showDateRangeError = true;
      } else {
        this.updateBatches();
=======
>>>>>>> TestAdmin "Batch" tab date limits
=======
>>>>>>> TestAdmin "Batch" tab warnings
        if (longEndDate < longStartDate) {
          this.dateRangeMessage =
            "The TO date cannot occur before the FROM date, please try another date.";
          this.showDateRangeError = true;
        } else {
          this.updateBatches();
        }
>>>>>>> TestAdmin "Batch" tab date limits
      }
    }
    }
  }

  public resetFormWarnings() {
    if (this.showDateRangeError === true) {
      this.showDateRangeError = false;
    }
  }

  // Logans new resetToDefaultBatches
  public resetToDefaultBatches() {
<<<<<<< HEAD
    this.filteredBatches = this.batches.filter(
      batch => {
        this.startDate = new Date();
        this.startDate.setMonth(new Date().getMonth() - 3);
        this.endDate = new Date();
        this.endDate.setMonth(new Date().getMonth() + 3);
        const startTime = Date.now();
        this.dataReady = false;
        this.counter = 0;
        this.stringStart = this.startDate.toJSON().substring(0, 10);
        this.stringEnd = this.endDate.toJSON().substring(0, 10);
        this.startDateTimePicker.dateReset();
        this.endDateTimePicker.dateReset();
      }
    );
=======
    this.filteredBatches = this.batches.filter(batch => {
      this.startDate = new Date();
      this.startDate.setMonth(new Date().getMonth() - 14);
      this.endDate = new Date();
      this.endDate.setMonth(new Date().getMonth() + 3);
      const startTime = Date.now();
      this.dataReady = false;
      this.counter = 0;
      this.stringStart = this.startDate.toJSON().substring(0, 10);
      this.stringEnd = this.endDate.toJSON().substring(0, 10);
      this.startDateTimePicker.dateReset();
      this.endDateTimePicker.dateReset();
      this.resetFormWarnings();
    });
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> TestAdmin "Batch" tab date limits

=======
>>>>>>> TestAdmin "Batch" tab date limits
=======
>>>>>>> TestAdmin "Batch" tab warnings
=======

>>>>>>> TestAdmin "Batches" Submit/Reset Functionality
    this.updateCountPerCurriculum();
    this.dataReady = true;
  }

  // Logans new update batches method
  public updateBatches()
  {
    const user = this.authService.getUser();
    if (user.role === 2) {
      // filter out batches that don't have an associated trainer
      this.filteredBatches = this.batches.filter(batch => {
        if (batch.trainer.firstName !== this.authService.getTrainer().firstName) {
          return false;
        }
        if (batch.coTrainer) {
          if (!batch.coTrainer.includes(this.authService.getTrainer())) {
              return false;
            }
          }
          const dateStartDate = new Date(this.startDate);
          const dateEndDate = new Date(this.endDate);
          const longStartDate = dateStartDate.getTime();
          const longEndDate = dateEndDate.getTime();

          if (batch.startDate && batch.endDate) {
            return batch.startDate > longStartDate && batch.endDate < longEndDate;
          }
          else {
            return false;
          }
<<<<<<< HEAD
        }
      );
=======
      });
>>>>>>> TestAdmin "Batches" Submit/Reset Functionality
      this.updateCountPerCurriculum();
      this.dataReady = true;
    }
    else{
      this.dataReady = false;
      this.filteredBatches = this.batches.filter(
        batch => {
          const dateStartDate = new Date(this.startDate);
          const dateEndDate = new Date(this.endDate);
          const longStartDate = dateStartDate.getTime();
          const longEndDate = dateEndDate.getTime();

          if (batch.startDate && batch.endDate) {
            return batch.startDate > longStartDate && batch.endDate < longEndDate;
          }
          else {
            return false;
          }
        }
<<<<<<< HEAD
<<<<<<< HEAD
      );
=======
=======
      this.filteredBatches = this.batches.filter(batch => {
<<<<<<< HEAD
        if (
          batch.trainer.firstName !== this.authService.getTrainer().firstName
        ) {
          return false;
        }
        if (batch.coTrainer) {
          if (!batch.coTrainer.includes(this.authService.getTrainer())) {
            return false;
          }
        }
=======
>>>>>>> TestAdmin "Batches" Submit/Reset Functionality
        const dateStartDate = new Date(this.startDate);
        const dateEndDate = new Date(this.endDate);
        const longStartDate = dateStartDate.getTime();
        const longEndDate = dateEndDate.getTime();

        if (batch.startDate && batch.endDate) {
          return batch.startDate > longStartDate && batch.endDate < longEndDate;
        } else {
          return false;
        }
>>>>>>> TestAdmin "Batch" tab date limits
=======
>>>>>>> TestAdmin "Batch" tab warnings
      });
>>>>>>> TestAdmin "Batch" tab date limits
      this.updateCountPerCurriculum();
      this.dataReady = true;
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

    this.dataEmpty = this.filteredBatches.length === 0;

    if (this.filteredBatches != null) {

      for (const batch of this.filteredBatches) {
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
}

