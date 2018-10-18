import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TrainerViewComponent } from './trainer-view.component';



const routes: Routes = [
  {
    path: '',
    component: TrainerViewComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SkillsetRoutingModule { }