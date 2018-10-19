import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SalesforceComponent } from './salesforce.component';



const routes: Routes = [
  {
    path: '',
    component: SalesforceComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalesforceRoutingModule { }