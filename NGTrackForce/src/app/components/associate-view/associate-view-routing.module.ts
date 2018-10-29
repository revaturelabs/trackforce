import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AssociateViewComponent } from './associate-view.component';


const routes: Routes = [
  {
    path: '',
    component: AssociateViewComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AssociateViewRoutingModule { }