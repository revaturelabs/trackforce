/**
 * @author Antony Lulciuc
 * @description Defines all possible navigatable routes for TrackForce application
 */

import { RouterModule, Routes } from '@angular/router';
import { ClientListComponent } from '../components/client-list/client-list.component';

import { LoginComponent } from '../components/login/login.component';
/**
 * Place paths here
 */

export const appRoutes: Routes = [
  {
        path: 'client-list',
        component: ClientListComponent,
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  }
]
