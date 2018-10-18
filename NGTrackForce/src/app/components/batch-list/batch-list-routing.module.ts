import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BatchListComponent } from './batch-list.component';


const routes: Routes = [
  {
    path: '',
    component: BatchListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BatchListRoutingModule { }