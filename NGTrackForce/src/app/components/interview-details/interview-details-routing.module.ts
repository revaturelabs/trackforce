import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InterviewDetailsComponent } from './interview-details.component';



const routes: Routes = [
  {
    path: '',
    component: InterviewDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InterviewDetailsRoutingModule { }