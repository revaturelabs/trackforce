import { NgModule } from '@angular/core';
import { InterviewService } from '../../services/interview-service/interview.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { InterviewDetailsComponent } from './interview-details.component';
//import { MyInterviewComponent } from '../myinterview-view/myinterview-view.component';

import { CommonModule } from '@angular/common';
import {InterviewDetailsRoutingModule } from './interview-details-routing.module';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
    declarations: [
        InterviewDetailsComponent,
        //MyInterviewComponent
    ],
    providers:[
        InterviewService,
        AuthenticationService
    ],
    imports: [
        CommonModule,
        InterviewDetailsRoutingModule,
        FormsModule,
        ChartsModule,
        MatProgressSpinnerModule
    ]
})
export class InterviewDetailsModule { }
