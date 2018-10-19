import { NgModule } from '@angular/core';
import { RequestService } from '../../services/request-service/request.service';
import { ClientService } from '../../services/client-service/client.service';
import { CreateUserComponent} from './create-user.component';

import {CreateUserRoutingModule } from './create-user-routing.module';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
    declarations: [
        CreateUserComponent
    ],
    providers:[
        RequestService,
        ClientService
    ],
    imports:[
        CreateUserRoutingModule,
        CommonModule,
        FormsModule,
        ChartsModule,
        MatProgressSpinnerModule
    ]
})
export class CreateUserModule { }
