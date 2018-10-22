import { NgModule } from '@angular/core';
import { PredictionsComponent } from './predictions.component';
import { BatchService } from './../../services/batch-service/batch.service';
import { CurriculumService } from '../../services/curriculum-service/curriculum.service';
import { AssociateService } from '../../services/associate-service/associate.service';
import { ClientService } from '../../services/client-service/client.service';
import { DateService } from '../../services/date-service/date.service';
//import { DateTimePickerComponent } from '../datetimepicker/datetimepicker.component';

import {CommonModule} from '@angular/common';
import {PredictionsRoutingModule} from './predictions-routing.module';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
    declarations: [
      PredictionsComponent,
      //DateTimePickerComponent
    ],
    providers:[
      BatchService,
      CurriculumService,
      AssociateService,
      ClientService,
      DateService
    ],
    imports:[
      CommonModule,
      PredictionsRoutingModule,
      FormsModule,
      ChartsModule,
      MatProgressSpinnerModule
    ]
})
export class PredictionsModule { }
