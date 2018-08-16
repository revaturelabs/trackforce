import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
// import * as $ from 'jquery';


@Component({
  selector: 'app-datetimepicker',
  templateUrl: './datetimepicker.component.html',
  styleUrls: ['./datetimepicker.component.css']
})

export class DateTimePickerComponent implements OnInit {
    @Input() width = '250px';   //default value
    @Input() format = 'date';   //default value
    @Output() datePicked = new EventEmitter();
    calendarView = false;

    options_date = {month:'long', day: 'numeric', year: 'numeric'};
    options_datetime = {month:'short', day: 'numeric', year: 'numeric', minute:'numeric', hour: 'numeric'};
    date = new Date();   //initialized to today's date
    stringDate: string;

    constructor() {
    }

    ngOnInit() {

    }

    public toggleCalendarView(){
        this.calendarView = !this.calendarView;
    }

    public dateChanged(){
        let localOptions = null;
        switch(this.format){
            case 'date': localOptions = this.options_date; break;
            case 'datetime': localOptions = this.options_datetime; break;
        }
        this.stringDate = this.date.toLocaleDateString("en-US", localOptions);
        this.datePicked.emit(this.stringDate);
    }




}
