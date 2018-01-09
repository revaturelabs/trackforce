/**
 * @author Antony Lulciuc
 * @description Defines all possible navigatable routes for TrackForce application
 */

import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '../components/home/home.component';
import { ClientListComponent } from '../components/client-list/client-list.component';
import { LoginComponent } from '../components/login/login.component';
import { ClientMappedComponent } from '../components/client-mapped/client-mapped.component';
import { AssociateListComponent } from '../components/associate-list/associate-list.component';
import { BatchListComponent } from '../components/batch-list/batch-list.component';
<<<<<<< HEAD
=======
import { ClientListComponent } from '../components/client-list/client-list.component';
import { LoginComponent } from '../components/login/login.component';
>>>>>>> 0ea29a97a86ea5e57761d25bc849bbc7d3f486d5

/**
 * Place paths here
 */
<<<<<<< HEAD

=======
>>>>>>> 0ea29a97a86ea5e57761d25bc849bbc7d3f486d5
export const appRoutes: Routes = [
    {
        path: 'home',
        component: HomeComponent
    },
<<<<<<< HEAD
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
=======
>>>>>>> 0ea29a97a86ea5e57761d25bc849bbc7d3f486d5
    {
        path: 'clientListing',
        component: ClientMappedComponent
    },
    {
        path: 'associateListing',
        component: AssociateListComponent
    },
    {
        path: 'batchListing',
        component: BatchListComponent
<<<<<<< HEAD
=======
    },
    {
      path: '',
      redirectTo: '/login',
      pathMatch: 'full'
    },
    {
      path: 'login',
      component: LoginComponent
>>>>>>> 0ea29a97a86ea5e57761d25bc849bbc7d3f486d5
    }
]
