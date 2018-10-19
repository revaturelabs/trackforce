import { NgModule } from "@angular/core";
import { FormComponent } from './form.component';
import { AssociateService } from '../../services/associate-service/associate.service';
import { ClientService } from '../../services/client-service/client.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { InterviewService } from '../../services/interview-service/interview.service';

import {CommonModule} from '@angular/common';
import {NewFormRoutingModule} from './newform-routing.module';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  declarations: [FormComponent],
  providers: [AssociateService, ClientService, AuthenticationService, InterviewService],
  imports: [CommonModule, NewFormRoutingModule, FormsModule, ChartsModule, MatProgressSpinnerModule]
})

export class NewFormModule {};
