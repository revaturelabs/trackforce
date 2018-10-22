import { NgModule } from "@angular/core";
import { UndeployedComponent } from './undeployed.component';
import { AssociateService } from '../../services/associate-service/associate.service';

import {CommonModule} from '@angular/common';
import {UndeployedRoutingModule} from './undeployed-routing.module';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  declarations: [UndeployedComponent],
  providers: [AssociateService],
  imports: [
    CommonModule,
    UndeployedRoutingModule,
    FormsModule,
    ChartsModule,
    MatProgressSpinnerModule
  ]
})

export class UndeployedModule {};
