import { NgModule } from '@angular/core';

import { RequestService } from '../../services/request-service/request.service';
import { ClientService } from '../../services/client-service/client.service';
import { ClientMappedComponent} from './client-mapped.component';

import {ClientMappedRoutingModule } from './client-mapped-routing.module';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
    declarations: [
        ClientMappedComponent
    ],
    providers:[
        RequestService,
        ClientService
    ],
    imports:[
        ClientMappedRoutingModule,
        CommonModule,
        FormsModule,
        ChartsModule,
        MatProgressSpinnerModule
    ]
})
export class ClientMappedModule { };
