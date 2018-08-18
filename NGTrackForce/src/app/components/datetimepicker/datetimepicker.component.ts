import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { DateService } from '../../services/date-service/date.service';


@Component({
  selector: 'app-datetimepicker',
  templateUrl: './datetimepicker.component.html',
  styleUrls: ['./datetimepicker.component.css']
})

export class DateTimePickerComponent implements OnInit {
    @Input() width = '250px';   //default value
    @Input() format = 'date';   //default value
    @Input() originalDate: number;    //no default
    @Input() dateType: string;  // start or end date
    @Output() datePicked = new EventEmitter();
    calendarView = false;
    displayErrorInvalidDate = false;

    options_date = {month:'long', day: 'numeric', year: 'numeric'};
    options_datetime = {month:'short', day: 'numeric', year: 'numeric', minute:'numeric', hour: 'numeric'};
    date = new Date();   //initialized to today's date
    stringDate: string;

    oldDate: Date;

    constructor(private dateService: DateService) {

    }

    changeDate(){
        if (this.dateType == "start") {
            console.log("it's a start date");
            this.dateService.currStartDate.subscribe(
                data =>{
                    this.originalDate = data.getDate();
                }
            );
        } else {
            console.log("it's an end date");
            this.dateService.currEndDate.subscribe(
                data =>{
                    this.originalDate = data.getDate();
                }
            );
        }

    }

    ngOnInit() {
        setTimeout(()=>{
            if (this.originalDate){ //because its an optional parameter
                this.date = new Date(this.originalDate);
                this.toggleCalendarView();
                this.dateClicked(); //this is to validate it and update other internal variables.
            }
        },0);   //UHHHHHH....IT WORKS!! NOTE: Without the timeout, Angular compains with a error related to concurrency, 'ExpressionChangedAfterItHasBeenCheckedError'

    }

    public toggleCalendarView(){
        this.calendarView = !this.calendarView;
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
            if (this.oldDate != this.date) this.calendarView = !this.calendarView;
            this.oldDate = this.date;

            this.validateDate();
        }
    }

    public manualEntry(){
        this.date = new Date(this.stringDate);
        this.datePicked.emit(this.stringDate);
        this.validateDate();
    }

    public validateDate(){
    if (this.date.toString() == "Invalid Date"){
        this.displayErrorInvalidDate = true;
        this.date = null;
    }
    else this.displayErrorInvalidDate = false;
    }




}
