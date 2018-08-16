import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import * as $ from 'jquery';
import * as moment from 'moment';

@Component({
  selector: 'app-datetimepicker',
  templateUrl: './datetimepicker.component.html',
  styleUrls: ['./datetimepicker.component.css']
})

export class DateTimePickerComponent implements OnInit {
    @Input() width = '250px';   //default value
    @Output() datePicked = new EventEmitter();

    constructor() {
    }

    ngOnInit() {

    }

    public onclick(){
        console.log("clicked!");
    }

    public chooseDate(){
        let date = "apples";
        console.log($('#datetimepicker1'));
        // $('#datetimepicker1').datetimepicker();

        this.datePicked.emit(date);     //tells the parent the value of date, wrapped in datePicked
    }




}
