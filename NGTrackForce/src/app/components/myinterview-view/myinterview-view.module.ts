import { NgModule } from "@angular/core";
import { MyInterviewComponent } from './myinterview-view.component';
import { AssociateService } from '../../services/associate-service/associate.service';
import { InterviewService } from '../../services/interview-service/interview.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';

import {MyInterviewRoutingModule} from './myinterview-view-routing.module';
import {CommonModule} from '@angular/common'

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  declarations: [MyInterviewComponent],
  providers: [InterviewService, AssociateService, AuthenticationService],
  imports: [CommonModule,MyInterviewRoutingModule, FormsModule, ChartsModule, MatProgressSpinnerModule]
})

export class MyInterviewViewModule {};
