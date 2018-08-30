import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { DateService } from "../../services/date-service/date.service";
<<<<<<< HEAD

=======
>>>>>>> TestAdmin "Batch" tab warnings

@Component({
  selector: 'app-datetimepicker',
  templateUrl: './datetimepicker.component.html',
  styleUrls: ['./datetimepicker.component.css']
})

export class DateTimePickerComponent implements OnInit {
  @Input()
  width = "250px"; //default value
  @Input()
  format = "date"; //default value
  @Input()
  originalDate: number; //no default
  @Input()
  dateType: string; // start or end date
  @Output()
  datePicked = new EventEmitter();
  @Output()
  error = new EventEmitter();

  calendarView = false;
  displayErrorInvalidDate = false;

  options_date = { month: 'long', day: 'numeric', year: 'numeric' };
  options_datetime = {
    month: 'long',
    day: 'numeric',
    year: 'numeric',
    minute: 'numeric',
    hour: 'numeric'
  };
  date = new Date(); //initialized to today's date
  stringDate: string;
  minDate = new Date();
  maxDate = new Date();

  oldDate: Date;

  constructor(private dateService: DateService) {}

  ngOnInit() {
    this.minDate.setFullYear(2010);
    this.maxDate.setFullYear(new Date().getFullYear() + 1);

    this.dateReset();
  }

  dateReset() {
    setTimeout(() => {
      if (this.originalDate) {
        //because its an optional parameter
        this.date = new Date(this.originalDate);
        this.toggleCalendarView();
        this.dateClicked(); //this is to validate it and update other internal variables.
      }
    }, 0);
  }

  public toggleCalendarView() {
    this.calendarView = !this.calendarView;
  }

  public dateClicked() {
    this.displayErrorInvalidDate = false;
    let localOptions = null;
    switch (this.format) {
      case 'date':
        localOptions = this.options_date;
        break;
      case 'datetime':
    }
    if (this.date != null) {
      this.stringDate = this.date.toLocaleDateString("en-US", localOptions);
      this.datePicked.emit(this.stringDate);
      if (this.oldDate != this.date) {
        this.calendarView = !this.calendarView;
      }
      this.oldDate = this.date;

      //if the date chosen is invalid (before 2010, after +1 of current year), revert to previous date
      if (this.validateDate() != 0) {
        this.date = new Date(this.originalDate);
        this.stringDate = this.date.toLocaleDateString("en-US", localOptions);
        this.datePicked.emit(this.stringDate);
      }
    }
  }

  public manualEntry() {
    this.date = new Date(this.stringDate);
    this.datePicked.emit(this.stringDate);
    this.validateDate();
  }

  public validateDate() {
    if (this.date < this.minDate) {
      this.displayErrorInvalidDate = true;
      this.date = this.oldDate;
    }
    else if (this.date > this.maxDate) {
      this.displayErrorInvalidDate = true;
      this.date = this.oldDate;
    } else {
      this.displayErrorInvalidDate = false;
      return 0;
    }
    this.error.emit(this.displayErrorInvalidDate);
  }
}
