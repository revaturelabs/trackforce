import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsernameUpdateComponent } from './usernameupdate.component';

const routes: Routes = [
  {
    path: '',
    component: UsernameUpdateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsernameUpdateRoutingModule { }