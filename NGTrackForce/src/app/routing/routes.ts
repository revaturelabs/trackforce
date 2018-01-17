/**
 * @author Antony Lulciuc
 * @description Defines all possible navigatable routes for TrackForce application
 */

import {RouterModule, Routes} from '@angular/router';
import {FormComponent} from '../components/form-component/form.component';
import {HomeComponent} from '../components/home/home.component';
import {ClientListComponent} from '../components/client-list/client-list.component';
import {LoginComponent} from '../components/login/login.component';
import {ClientMappedComponent} from '../components/client-mapped/client-mapped.component';
import {AssociateListComponent} from '../components/associate-list/associate-list.component';
import {BatchListComponent} from '../components/batch-list/batch-list.component';
import {CreateUserComponent} from '../components/create-user/create-user.component';
import {RootComponent} from '../components/root/root.component';
import {SkillsetComponent} from '../components/skillset/skillset.component';
import {BatchDetailsComponent} from '../components/batch-details/batch-details.component';
import { AssociateViewComponent } from '../components/associate-view/associate-view.component';
import { AuthGuard } from '../guards/auth.guard';

/**
 * Place paths here
 */

export const appRoutes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'client-listing',
    component: ClientListComponent,
	  canActivate : [AuthGuard]
  },
  {
    path: 'client-mapped/:id',
    component: ClientMappedComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'associate-listing',
    component: AssociateListComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'associate-listing/:CliOrCur/:name/:mapping/:status',
    component: AssociateListComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'batch-listing',
    component: BatchListComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'batch-details/:id',
    component: BatchDetailsComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'form-comp/:id',
    component: FormComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'create-user',
    component: CreateUserComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'root',
    component: RootComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'skillset/:id',
    component: SkillsetComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'associate-view/:id',
    component: AssociateViewComponent,
    canActivate : [AuthGuard]
  }, 
  {
    path: '**',
    redirectTo : '/root'
  }

];
