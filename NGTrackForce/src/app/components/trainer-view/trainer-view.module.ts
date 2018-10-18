import { NgModule } from '@angular/core';
import { TrainerViewComponent } from './trainer-view.component';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { AssociateService } from "../../services/associate-service/associate.service";

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';

@NgModule({
    declarations: [
      TrainerViewComponent
    ],
    providers:[
      AssociateService,
      AuthenticationService
    ],
    imports: [
      FormsModule,
      ChartsModule,
      MatProgressSpinnerModule,
      CommonModule
    ]
})
export class TrainerViewModule { }
