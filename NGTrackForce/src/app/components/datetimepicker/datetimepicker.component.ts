import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { DateService } from "../../services/date-service/date.service";


@Component({
  selector: "app-datetimepicker",
  templateUrl: "./datetimepicker.component.html",
  styleUrls: ["./datetimepicker.component.css"]
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
  }

    public dateClicked(){
        let localOptions = null;
        switch(this.format){
            case 'date': localOptions = this.options_date; break;
            case 'datetime': localOptions = this.options_datetime; break;
        }
        if (this.date != null){
            this.stringDate = this.date.toLocaleDateString("en-US", localOptions);
            this.datePicked.emit(this.stringDate);
            if (this.oldDate !== this.date) {
              this.calendarView = !this.calendarView;
            }

            this.validateDate();
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
