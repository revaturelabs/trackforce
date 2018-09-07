/**
 * @author Antony Lulciuc
 * @description Defines all possible navigatable routes for TrackForce application
 */

import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from '../components/form-component/form.component';
import { HomeComponent } from '../components/home/home.component';
import { ClientListComponent } from '../components/client-list/client-list.component';
import { LoginComponent } from '../components/login/login.component';
import { ClientMappedComponent } from '../components/client-mapped/client-mapped.component';
import { AssociateListComponent } from '../components/associate-list/associate-list.component';
import { BatchListComponent } from '../components/batch-list/batch-list.component';
import { CreateUserComponent } from '../components/create-user/create-user.component';
import { SkillsetComponent } from '../components/skillset/skillset.component';
import { BatchDetailsComponent } from '../components/batch-details/batch-details.component';
import { AssociateViewComponent } from '../components/associate-view/associate-view.component';
import { NotFoundComponent } from '../components/not-found/not-found.component';
import { PredictionsComponent } from '../components/predictions/predictions.component';
import { MyInterviewComponent } from '../components/myinterview-view/myinterview-view.component';
import { AuthGuard } from '../guards/auth.guard';
import { InterviewDetailsComponent } from '../components/interview-details/interview-details.component';
import { InterviewsComponent } from '../components/interviews-view/interviews-view.component';
import { TrainerViewComponent } from '../components/trainer-view/trainer-view.component';
import { DeployedComponent } from '../components/deployed/deployed.component';
import { UndeployedComponent } from '../components/undeployed/undeployed.component';
import { InvalidSessionComponent } from '../components/invalid-session/invalid-session.component';
import { SalesforceComponent } from '../components/salesforce/salesforce.component';
import { NavbarComponent } from '../components/navbar/navbar.component';


/**
 * Place paths here
 */

export const appRoutes: Routes = [
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
    path: 'invalid-session',
    component: InvalidSessionComponent
  },
  {
    path: 'client-listing',
    canActivate: [AuthGuard],
    component: ClientListComponent,
    data: { expectedRoles: [1,3,4] }
  },
  {
    path: 'client-mapped/:id',
    canActivate: [AuthGuard],
    component: ClientMappedComponent,
    data: { expectedRoles: [1,3,4] }
  },
  {
    path: 'associate-listing',
    canActivate: [AuthGuard],
    component: AssociateListComponent,
    data: { expectedRoles: [1,2,3,4] }
  },
  {
    path: 'associate-listing/:CliOrCur/:name/:mapping/:status',
    canActivate: [AuthGuard],
    component: AssociateListComponent,
    data: { expectedRoles: [1,2,3,4] }
  },
  {
    path: 'batch-listing',
    canActivate: [AuthGuard],
    component: BatchListComponent,
    data: { expectedRoles: [1,2,3,4] }
  },
  {
    path: 'batch-details/:id',
    canActivate: [AuthGuard],
    component: BatchDetailsComponent,
    data: { expectedRoles: [1,2,3,4] }
  },
  {
    path: 'form-comp/:id',
    canActivate: [AuthGuard],
    component: FormComponent,
    data: { expectedRoles: [1,2,3,4] }
  },
  {
    path: 'create-user',
    canActivate: [AuthGuard],
    component: CreateUserComponent,
    data: { expectedRoles: [1,3,4] }
  },
  {
    path: 'app-home',
    canActivate: [AuthGuard],
    component: HomeComponent,
    data: { expectedRoles: [1,3,4] }
  },
  {
    path: 'predictions',
    canActivate: [AuthGuard],
    component: PredictionsComponent,
    data: { expectedRoles: [1,3,4] }
  },
  {
    path: 'skillset/:id',
    canActivate: [AuthGuard],
    component: SkillsetComponent,
    data: { expectedRoles: [1,3,4] }
  },
  {
    path: 'associate-view',
    canActivate: [AuthGuard],
    component: AssociateViewComponent,
    data: { expectedRoles: [5] }
  },
  {
    path: 'myinterview-view',
    canActivate: [AuthGuard],
    component: MyInterviewComponent,
    data: { expectedRoles: [5] }
  },
  {
    path: 'interview-details/:id',
    canActivate: [AuthGuard],
    component: InterviewDetailsComponent,
    data: { expectedRoles: [1,2,3,4,5] }
  },
  {
    path: 'interviews',
    canActivate: [AuthGuard],
    component: InterviewsComponent,
    data: { expectedRoles: [1,2,3,4,5] }
  },
  {
    path: 'trainer-view',
    canActivate: [AuthGuard],
    component: TrainerViewComponent,
    data: { expectedRoles: [2] }
  },
  {
    path: 'deployed/:id',
    canActivate: [AuthGuard],
    component: DeployedComponent,
    data: { expectedRoles: [1,3,4] }
  },
  {
    path: 'undeployed/:id',
    canActivate: [AuthGuard],
    component: UndeployedComponent,
    data: { expectedRoles: [1,3,4] }
  },
  {
    path: 'salesforce',
    canActivate: [AuthGuard],
    component: SalesforceComponent,
    data: {expectedRoles: [1]}
  },
  {
    path: 'navbar',
    canActivate: [AuthGuard],
    component: NavbarComponent,
    data: {expectedRoles: [1]}
  },
  {
    // must be LAST in this array because this matches all other paths (fallback)
    path: '**',
    component: NotFoundComponent
  }
];
