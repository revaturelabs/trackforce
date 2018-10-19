import { NgModule } from '@angular/core';

import { AssociateService } from '../../services/associate-service/associate.service';
import { DeployedComponent } from './deployed.component';
import {CommonModule} from '@angular/common';
import { DeployedRoutingModule } from './deployed-routing.module';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
    declarations: [
        DeployedComponent
    ],
    providers:[
        AssociateService
    ],
    imports: [
        CommonModule,
        DeployedRoutingModule,
        FormsModule,
        ChartsModule,
        MatProgressSpinnerModule
    ]
})
export class DeployedModule { };
