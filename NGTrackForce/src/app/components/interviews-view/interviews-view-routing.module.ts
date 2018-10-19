import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InterviewsComponent } from './interviews-view.component';



const routes: Routes = [
  {
    path: '',
    component: InterviewsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InterviewsViewRoutingModule { }