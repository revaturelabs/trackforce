/**
 * @author Antony Lulciuc
 * @description Defines all possible navigatable routes for TrackForce application
 */

import {NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";

//import { ModuleWithProviders } from "@angular/core";

// import { FormComponent } from "../components/form-component/form.component";
// import { HomeComponent } from "../components/home/home.component";
// import { ClientListComponent } from "../components/client-list/client-list.component";
import { LoginComponent } from "../components/login/login.component";
// import { ClientMappedComponent } from "../components/client-mapped/client-mapped.component";
// import { BatchListComponent } from "../components/batch-list/batch-list.component";
// import { CreateUserComponent } from "../components/create-user/create-user.component";
// import { SkillsetComponent } from "../components/skillset/skillset.component";
// import { BatchDetailsComponent } from "../components/batch-details/batch-details.component";
import { AssociateViewComponent } from "../components/associate-view/associate-view.component";
import { NotFoundComponent } from "../components/not-found/not-found.component";
// import { PredictionsComponent } from "../components/predictions/predictions.component";
// import { MyInterviewComponent } from "../components/myinterview-view/myinterview-view.component";
import { AuthGuard } from "../guards/auth.guard";
// import { InterviewDetailsComponent } from "../components/interview-details/interview-details.component";
// import { InterviewsComponent } from "../components/interviews-view/interviews-view.component";
// import { TrainerViewComponent } from "../components/trainer-view/trainer-view.component";
// import { DeployedComponent } from "../components/deployed/deployed.component";
// import { UndeployedComponent } from "../components/undeployed/undeployed.component";
import { InvalidSessionComponent } from "../components/invalid-session/invalid-session.component";
// import { AssociateListPageComponent } from "../components/associate-list-page/associate-list-page.component";






/**
 * Place paths here
 */

const appRoutes: Routes = [
  {
    path: "",
    redirectTo: "/login",
    pathMatch: "full"
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "invalid-session",
    component: InvalidSessionComponent
  },
  {
    path: "client-listing",
    canActivate: [AuthGuard],
    loadChildren: '../components/client-list/client-list.module#ClientListModule',
    //component: ClientListComponent,
    data: { expectedRoles: [1, 3, 4] }
  },
  {
    path: "client-mapped/:id",
    canActivate: [AuthGuard],
    loadChildren: '../components/client-mapped/client-mapped.module#ClientMappedModule',
    //component: ClientMappedComponent,
    data: { expectedRoles: [1, 3, 4] }
  },
  {
    path: "associate-listing",
    canActivate: [AuthGuard],
    loadChildren:
      "../components/associate-list-page/associate-list-page.module#AssociateListPageModule",
    //component: AssociateListPageComponent,
    data: { expectedRoles: [1, 2, 3, 4] }
  },
  {
    path: "associate-listing/:CliOrCur/:name/:mapping/:status",
    canActivate: [AuthGuard],
    loadChildren:
      "../components/associate-list-page/associate-list-page.module#AssociateListPageModule",
    //component: AssociateListPageComponent,
    data: { expectedRoles: [1, 2, 3, 4] }
  },
  {
    path: "batch-listing",
    canActivate: [AuthGuard],
    loadChildren: '../components/batch-list/batch-list.module#BatchListModule',
    //component: BatchListComponent,
    data: { expectedRoles: [1, 2, 3, 4] }
  },
  {
    path: "batch-details/:id",
    canActivate: [AuthGuard],
    loadChildren: '../components/batch-details/batch-details.module#BatchDetailsModule',
    //component: BatchDetailsComponent,
    data: { expectedRoles: [1, 2, 3, 4] }
  },
  {
    path: "form-comp/:id",
    canActivate: [AuthGuard],
    loadChildren: "../components/form-component/newform.module#NewFormModule",
    //component: FormComponent,
    data: { expectedRoles: [1, 2, 3, 4] }
  },
  {
    path: "create-user",
    canActivate: [AuthGuard],
    loadChildren: '../components/create-user/create-user.module#CreateUserModule',
    //component: CreateUserComponent,
    data: { expectedRoles: [1, 3, 4] }
  },
  {
    path: "app-home",
    canActivate: [AuthGuard],
    loadChildren: "../components/home/home.module#HomeModule",
    //component: HomeComponent,
    data: { expectedRoles: [1, 3, 4] }
  },
  {
    path: "predictions",
    canActivate: [AuthGuard],
    loadChildren:
      "../components/predictions/predictions.module#PredictionsModule",
    //component: PredictionsComponent,
    data: { expectedRoles: [1, 3, 4] }
  },
  {
    path: "skillset/:id",
    canActivate: [AuthGuard],
    loadChildren: "../components/skillset/skillset.module#SkillsetModule",
    //component: SkillsetComponent,
    data: { expectedRoles: [1, 3, 4] }
  },
  {
    path: "associate-view",
    canActivate: [AuthGuard],
    // loadChildren: '../components/associate-view/associate-view.module#AssociateViewModule',
    component: AssociateViewComponent,
    data: { expectedRoles: [5] }
  },
  {
    path: "myinterview-view",
    canActivate: [AuthGuard],
    loadChildren:
      "../components/myinterview-view/myinterview-view.module#MyInterviewViewModule",
    //component: MyInterviewComponent,
    data: { expectedRoles: [5] }
  },
  {
    path: "interview-details/:id",
    canActivate: [AuthGuard],
    loadChildren:
      "../components/interview-details/interview-details.module#InterviewDetailsModule",
    //component: InterviewDetailsComponent,
    data: { expectedRoles: [1, 2, 3, 4, 5] }
  },
  {
    path: "interviews",
    canActivate: [AuthGuard],
    loadChildren:
      "../components/interviews-view/interviews-view.module#InterviewsViewModule",
    //component: InterviewsComponent,
    data: { expectedRoles: [1, 2, 3, 4, 5] }
  },
  {
    path: "trainer-view",
    canActivate: [AuthGuard],
    loadChildren:
      "../components/trainer-view/trainer-view.module#TrainerViewModule",
    //component: TrainerViewComponent,
    data: { expectedRoles: [2] }
  },
  {
    path: "deployed/:id",
    canActivate: [AuthGuard],
    loadChildren: "../components/deployed/deployed.module#DeployedModule",
    //component: DeployedComponent,
    data: { expectedRoles: [1, 3, 4] }
  },
  {
    path: "undeployed/:id",
    canActivate: [AuthGuard],
    loadChildren: "../components/undeployed/undeployed.module#UndeployedModule",
    //component: UndeployedComponent,
    data: { expectedRoles: [1, 3, 4] }
  },
  {
    // must be LAST in this array because this matches all other paths (fallback)
    path: "**",
    component: NotFoundComponent
  }
];

@NgModule( {
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }



//export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
