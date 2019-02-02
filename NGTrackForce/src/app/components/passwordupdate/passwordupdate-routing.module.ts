import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PasswordUpdateComponent } from './passwordupdate.component';

const routes: Routes = [
  {
    path: '',
    component: PasswordUpdateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PasswordUpdateRoutingModule { }