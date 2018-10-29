import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AssociateListPageComponent } from './associate-list-page.component';


const routes: Routes = [
  {
    path: '',
    component: AssociateListPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AssociateListRoutingModule { }