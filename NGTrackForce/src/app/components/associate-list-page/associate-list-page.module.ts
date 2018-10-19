import { NgModule } from "@angular/core";
import { AssociateListPageComponent } from './associate-list-page.component';
import { AssociateService } from '../../services/associate-service/associate.service';
import { ClientService } from '../../services/client-service/client.service';
import {AssociateListRoutingModule } from './associate-list-page-routing.module';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { PipeModule } from '../../pipes/pipe.module';
@NgModule({
  declarations: [AssociateListPageComponent],
  providers: [AssociateService, ClientService],
  imports: [AssociateListRoutingModule, CommonModule, FormsModule, ChartsModule, MatProgressSpinnerModule, PipeModule]
})
export class AssociateListPageModule {}