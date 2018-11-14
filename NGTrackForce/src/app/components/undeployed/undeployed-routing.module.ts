import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UndeployedComponent } from './undeployed.component';



const routes: Routes = [
  {
    path: '',
    component: UndeployedComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UndeployedRoutingModule { }