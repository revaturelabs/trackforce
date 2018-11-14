import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeployedComponent } from './deployed.component';



const routes: Routes = [
  {
    path: '',
    component: DeployedComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DeployedRoutingModule { }