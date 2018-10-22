import { NgModule } from '@angular/core';
import { BatchService } from '../../services/batch-service/batch.service';
import { BatchDetailsComponent} from './batch-details.component';
import {BatchDetailsRoutingModule } from './batch-details-routing.module';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { Ng2OrderModule } from 'ng2-order-pipe';

@NgModule({
    declarations: [
        BatchDetailsComponent
    ],
    providers:[
        BatchService
    ],
    imports:[
        CommonModule,
        BatchDetailsRoutingModule,
        FormsModule,
        ChartsModule,
        Ng2OrderModule, 
        MatProgressSpinnerModule
    ]
})
export class BatchDetailsModule { }
