import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PredictionsComponent } from './predictions.component';



const routes: Routes = [
  {
    path: '',
    component: PredictionsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PredictionsRoutingModule { }