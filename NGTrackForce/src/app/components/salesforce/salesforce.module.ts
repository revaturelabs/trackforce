import { NgModule } from "@angular/core";
import { SalesforceComponent } from './salesforce.component';

import {CommonModule } from '@angular/common';
import { SalesforceRoutingModule } from './salesforce-routing.module';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  declarations: [SalesforceComponent],
  providers: [],
  imports: [CommonModule, SalesforceRoutingModule, FormsModule, ChartsModule, MatProgressSpinnerModule]
})

export class SalesforceModule {};
