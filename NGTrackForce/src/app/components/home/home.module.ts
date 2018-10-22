import { NgModule } from '@angular/core';
import { UserService } from '../../services/user-service/user.service';
import { DataSyncService } from '../../services/datasync-service/data-sync.service';
import { RequestService } from '../../services/request-service/request.service';
import { BatchService } from '../../services/batch-service/batch.service';
import { AssociateService } from '../../services/associate-service/associate.service';
import { HomeComponent } from './home.component';

import {CommonModule} from '@angular/common';
import {HomeRoutingModule} from './home-routing.module';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
    declarations: [
        HomeComponent
    ],
    providers:[
        AssociateService,
        BatchService,
        UserService,
        DataSyncService,
        RequestService
    ],
    imports: [
        HomeRoutingModule,
        CommonModule,
        FormsModule,
        ChartsModule,
        MatProgressSpinnerModule
    ]
})
export class HomeModule { }
