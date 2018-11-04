/** @Author Princewill Ibe **/
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
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
 *
 * 1807 addition: completely change date-time-picker to html input date
 */
@Component({
  selector: 'app-batch-list',
  templateUrl: './batch-list.component.html',
  styleUrls: ['./batch-list.component.css']
})
@AutoUnsubscribe
export class BatchListComponent implements OnInit {

  @ViewChild('start') startDateTimePicker:DateTimePickerComponent;
  @ViewChild('end') endDateTimePicker:DateTimePickerComponent;

  start: any;
  end: any;
  pieChartType = 'pie';
  startDate: Date = new Date(0);
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

  fromString: string;
  toString: string;

  dateRangeMessage: string;
  showDateRangeError = false;
  dateError: boolean;

  changeDate(){
    this.changeDateEm.emit(this.startDate);
  }

  chartOptions: ChartOptions = ChartOptions.createOptionsSpacing(
    new SideValues(-100, 0, 0, 0),
    new SideValues(0, 0, 0, 0),
    'right', false, false
  );

  constructor(private batchService: BatchService, private authService: AuthenticationService,
              private dateService: DateService) {
  }

  /**
   * load default batches on initialization
   */
  ngOnInit() {
    // get current user
    const user = this.authService.getUser();
    this.dataReady = false;

    this.stringStart = this.startDate.toJSON().substring(0, 10);
    this.stringEnd = this.endDate.toJSON().substring(0, 10);
    this.fromString = this.startDate.toJSON().substring(0, 10);
    this.toString = this.endDate.toJSON().substring(0, 10);
    this.dataReady = false;
    console.log(this.authService.getTrainer());
    console.log(user);
    this.batchService.getBatchesWithinDates(this.startDate,this.endDate).subscribe(
      batches => {
        this.batches = batches.filter(
          batch => {

						if (batch.startDate < this.minDate){
							this.minDate = batch.startDate;
						}

						this.startDate = new Date(this.minDate);
						this.dateService.changeDates(this.startDate, this.endDate);

						//user is a trainer they can only see their batches
						if (this.authService.getUserRole() === 2) {
              if (batch.trainer.user.id !== this.authService.getTrainer().user.id) {
                console.log(batch);
                return false;
              }
              if (batch.coTrainer) {
                console.log(batch.coTrainer.includes(this.authService.getTrainer()))
                return batch.coTrainer.includes(this.authService.getTrainer());
              }
						}

            return true;
          }
        );
        this.filteredBatches = this.batches;
				this.fromString = this.startDate.toJSON().substring(0, 10);
        this.updateCountPerCurriculum();
        this.dataReady = true;
      },
      error => {
        console.log(error);
      }
    );
  }


  /**
   * after user selects date range, this handles updating the data,
   * and the corresponding graph accordingly
   */
	 // Edited by Mussab
  public applySelectedRange() {
    if (!this.dateError){
			// Added Timezone offset becasue the constructor of new Date(yyyy-dd-mm)
			// gets the UTC time and depending on your current timezone it will add/remove
			// couple of hours thus could cause a change in the date either a day early or
			// a day late so adding the offset time makes sure that the correct
			// date is being referenced
    this.startDate = new Date(this.fromString);
		this.startDate.setMinutes(this.startDate.getMinutes() + this.startDate.getTimezoneOffset());
    this.endDate = new Date(this.toString);
		this.endDate.setMinutes(this.endDate.getMinutes() + this.endDate.getTimezoneOffset());

    console.log(this.startDate);
    console.log(this.endDate);

    let longStartDate: number;
    let longEndDate: number;

    this.resetFormWarnings();

    if (this.startDate && this.endDate) {
      longStartDate = this.startDate.getTime();
      longEndDate = this.endDate.getTime();


      if (longStartDate > longEndDate) {
        this.dateRangeMessage = "The to date cannot be before the from date, please try another date.";
        this.showDateRangeError = true;
      } else if(longStartDate < this.minDate){
				this.dateRangeMessage = `The from date cannot be before ${new Date(this.minDate).toDateString().substring(4)}, please try another date.`;
        this.showDateRangeError = true;
			} else if(longEndDate > Date.now()){
				this.dateRangeMessage = "The to date cannot be after today, please try another date.";
        this.showDateRangeError = true;
			} else {
        this.updateBatches();
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
	// Edited by Mussab
  public resetToDefaultBatches() {
        this.startDate = new Date(this.minDate);
        this.endDate = new Date();
        this.counter = 0;
        this.fromString = this.startDate.toJSON().substring(0, 10);
        this.toString = this.endDate.toJSON().substring(0, 10);
				// The next wor lines are commented because they throw an Error
        // this.startDateTimePicker.dateReset();
        // this.endDateTimePicker.dateReset();
				this.updateBatches();
  }

  // Logans new update batches method
	// Refactored by Mussab
  public updateBatches()
  {
    const user = this.authService.getUser();
		this.dataReady = false;
    this.filteredBatches = this.batches.filter(
      batch => {
				if (this.authService.getUserRole() === 2) {
			     // filter out batches that don't have an associated trainer
	        if (batch.trainer.firstName !== this.authService.getTrainer().firstName) {
	          return false;
	        }
	        if (batch.coTrainer && !batch.coTrainer.includes(this.authService.getTrainer())) {
	          return false;
	        }
				}
        const dateStartDate = new Date(this.startDate);
        const dateEndDate = new Date(this.endDate);
        const longStartDate = dateStartDate.getTime();
        const longEndDate = dateEndDate.getTime();

        if (batch.startDate && batch.endDate) {
          return batch.startDate >= longStartDate && batch.endDate <= longEndDate;
        }
        else {
          return false;
        }
      }
    );

		// Commented by Mussab cause issue #588 states the the chart
		// be for all batches
    // this.updateCountPerCurriculum();
    this.dataReady = true;
  }

  //Steve L
	// Refactored by Mussab
  public updateBatchesTest()
  {
    const user = this.authService.getUser();
    this.filteredBatches = this.batches.filter(
      batch => {
				if (this.authService.getUserRole() === 2) {
		      // filter out batches that don't have an associated trainer
          if (batch.trainer.firstName !== this.authService.getTrainer().firstName) {
            return false;
          }
          if (batch.coTrainer && !batch.coTrainer.includes(this.authService.getTrainer())) {
            return false;
          }
        }
        const dateStartDate = new Date(this.startDate);
        const dateEndDate = new Date(this.endDate);
        const longStartDate = dateStartDate.getTime();
        const longEndDate = dateEndDate.getTime();

        if (batch.startDate && batch.endDate) {
          return batch.startDate >= longStartDate && batch.endDate <= longEndDate;
        }
        else {
          return false;
        }
      }
    );
    // this.updateCountPerCurriculum();
    this.dataReady = true;
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
