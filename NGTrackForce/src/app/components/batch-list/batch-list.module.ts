import { NgModule } from "@angular/core";
import { BatchListComponent } from './batch-list.component';
//import { DateTimePickerComponent } from '../datetimepicker/datetimepicker.component';
import { BatchService } from '../../services/batch-service/batch.service';
import { DateService } from '../../services/date-service/date.service';

import {BatchListRoutingModule } from './batch-list-routing.module';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { SearchFilterPipeModule } from '../../pipes/search-filter/search-filter.module';
//import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

@NgModule({
  declarations: [BatchListComponent],
  imports: [BatchListRoutingModule,CommonModule, FormsModule, ChartsModule, MatProgressSpinnerModule, SearchFilterPipeModule],
  entryComponents: [],
  providers: [BatchService, DateService],
  exports: [BatchListComponent],
})

export class BatchListModule {};
