import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MyInterviewComponent } from './myinterview-view.component';



const routes: Routes = [
  {
    path: '',
    component: MyInterviewComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MyInterviewRoutingModule { }