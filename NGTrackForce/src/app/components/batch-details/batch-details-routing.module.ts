import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BatchDetailsComponent } from './batch-details.component';


const routes: Routes = [
  {
    path: '',
    component: BatchDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BatchDetailsRoutingModule { }