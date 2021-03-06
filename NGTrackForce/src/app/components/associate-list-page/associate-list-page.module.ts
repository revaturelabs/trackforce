import { NgModule } from "@angular/core";
import { AssociateListPageComponent } from './associate-list-page.component';
import { AssociateService } from '../../services/associate-service/associate.service';
import { ClientService } from '../../services/client-service/client.service';
import {AssociateListRoutingModule } from './associate-list-page-routing.module';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { SearchFilterPipeModule } from '../../pipes/search-filter/search-filter.module';
// import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

@NgModule({
  declarations: [AssociateListPageComponent],
  providers: [AssociateService, ClientService],
  imports: [AssociateListRoutingModule, CommonModule, FormsModule, ChartsModule, MatProgressSpinnerModule, SearchFilterPipeModule]
})
export class AssociateListPageModule {}