import { NgModule } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { ClientService } from '../../services/client-service/client.service';
import { AssociateViewComponent } from './associate-view.component';
import {AssociateViewRoutingModule } from './associate-view-routing.module';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
    declarations: [
        AssociateViewComponent
    ],
    providers:[
        AuthenticationService,
        AssociateService,
        ClientService
    ],
    imports:[
        AssociateViewRoutingModule,
        CommonModule,
        FormsModule,
        ChartsModule,
        MatProgressSpinnerModule
    ]
    
})
export class AssociateViewModule { }
