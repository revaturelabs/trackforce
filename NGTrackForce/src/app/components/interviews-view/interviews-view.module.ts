import { NgModule } from "@angular/core";
import { InterviewsComponent } from './interviews-view.component';
import { InterviewService } from '../../services/interview-service/interview.service';
import {CommonModule } from '@angular/common';
import {InterviewsViewRoutingModule} from './interviews-view-routing.module';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  declarations: [InterviewsComponent],
  providers: [InterviewService, AuthenticationService],
  imports: [CommonModule, InterviewsViewRoutingModule, FormsModule, ChartsModule, MatProgressSpinnerModule]
})

export class InterviewsViewModule {};
