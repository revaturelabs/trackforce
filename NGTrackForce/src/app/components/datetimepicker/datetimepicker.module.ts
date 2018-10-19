import { NgModule } from "@angular/core";
import { DateTimePickerComponent } from './datetimepicker.component';
import { DateService } from '../../services/date-service/date.service';
import {CommonModule } from '@angular/common';
import { DateTimePickerRoutingModule } from './datetimepickerrouting.module';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  declarations: [DateTimePickerComponent],
  providers: [DateService],
  imports:[
    CommonModule,
    FormsModule,
    ChartsModule,
    MatProgressSpinnerModule
  ]
})

export class DateTimePickerModule {};
