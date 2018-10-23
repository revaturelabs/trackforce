///
//  DEPENDENCIES
///
import { AppRoutingModule } from './routing/app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
//import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ChartsModule } from 'ng2-charts';

import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import { MaterialModule } from './material.module';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDialogModule } from '@angular/material/dialog';

///
//  COMPONENTS
///
import { AppComponent } from './app.component';

// import { BatchListComponent } from './components/batch-list/batch-list.component';
// import { ClientMappedComponent } from './components/client-mapped/client-mapped.component';
// import { CreateUserComponent } from './components/create-user/create-user.component';
import { LoginComponent } from './components/login/login.component';
// import { ClientListComponent } from './components/client-list/client-list.component';
import { AssociateViewComponent } from './components/associate-view/associate-view.component';
// import { BatchDetailsComponent } from './components/batch-details/batch-details.component';


// import { FormComponent } from './components/form-component/form.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
// import { HomeComponent } from './components/home/home.component';
// import { SkillsetComponent } from './components/skillset/skillset.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
// import { PredictionsComponent } from './components/predictions/predictions.component';
// import { MyInterviewComponent } from './components/myinterview-view/myinterview-view.component';
// import { InterviewsComponent } from './components/interviews-view/interviews-view.component';
import { DateTimePickerComponent } from './components/datetimepicker/datetimepicker.component';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

///
//  SERVICES
///
import { RequestService } from './services/request-service/request.service';
import { AssociateService } from './services/associate-service/associate.service';
// import { ClientService } from './services/client-service/client.service';
import { AuthenticationService } from './services/authentication-service/authentication.service';
//import { SearchFilterPipe } from './pipes/search-filter/search-filter.pipe';
// import { BatchService } from './services/batch-service/batch.service';
import { CurriculumService } from './services/curriculum-service/curriculum.service';
import { DataSyncService } from './services/datasync-service/data-sync.service';
import { UserService } from './services/user-service/user.service';
// import { InterviewService } from './services/interview-service/interview.service';
import { NavbarService } from './services/navbar-service/navbar.service';

///
//  FILTERS/PIPES
///
//import { AssociateSearchByTextFilter } from './pipes/associate-search-by-text-filter/associate-search-by-text-filter.pipes';
//import { AssociateSearchByStatusPipe } from './pipes/associate-search-by-status-pipe/status-pipe.pipe';
//import { AssociateSearchByClientPipe } from './pipes/associate-search-by-client-pipe/client-pipe.pipe';

///
//  SECURITY
///
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { InvalidSessionRerouteInterceptor } from './interceptors/invalidSessionReroute.interceptor';
import { AuthGuard } from './guards/auth.guard';

///
//  CONSTANTS
///

import {
  RouterLinkStubDirective,
  RouterOutletStubComponent
} from './testing-helpers/router-stubs';
// import { InterviewDetailsComponent } from './components/interview-details/interview-details.component';
// import { TrainerViewComponent } from './components/trainer-view/trainer-view.component';
import { TrainerService } from './services/trainer-service/trainer.service';
// import { DeployedComponent } from './components/deployed/deployed.component';
// import { UndeployedComponent } from './components/undeployed/undeployed.component';
import { InvalidSessionComponent } from './components/invalid-session/invalid-session.component';
// import { HighlightInterviewDirective } from './directives/highlight-interview.directive';
// import { SalesforceComponent } from './components/salesforce/salesforce.component';
import { UpdateDialogComponent } from './components/associate-list-page/associate-list-page.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    // HomeComponent,
    //AssociateSearchByTextFilter,
    // BatchListComponent,
    // ClientMappedComponent,
    // FormComponent,
    // ClientListComponent,
    LoginComponent,
    // CreateUserComponent,
    //SearchFilterPipe,
    // BatchDetailsComponent,
    // SkillsetComponent,
    AssociateViewComponent,
    RouterLinkStubDirective,
    RouterOutletStubComponent,
    FooterComponent,
    NotFoundComponent,
    // PredictionsComponent,
    // MyInterviewComponent,
    // InterviewDetailsComponent,
    // InterviewsComponent,
    //TrainerViewComponent,
    // DeployedComponent,
    // UndeployedComponent,
    DateTimePickerComponent,
    //AssociateSearchByStatusPipe,
    //AssociateSearchByClientPipe,
    InvalidSessionComponent,
    // HighlightInterviewDirective,
    // SalesforceComponent,
    // AssociateListPageComponent,
    UpdateDialogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    //RouterModule.forRoot(appRoutes, {useHash: true}),
    ChartsModule,
    BrowserAnimationsModule,
    MatProgressSpinnerModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    AppRoutingModule
  ],
  entryComponents: [
    UpdateDialogComponent
  ],
  providers: [
    AssociateService,
    // ClientService,
    AuthenticationService,
    RequestService,
    CurriculumService,
    // BatchService,
    UserService,
    CurriculumService,
    // InterviewService,
    AuthGuard,
    TrainerService,
    NavbarService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: InvalidSessionRerouteInterceptor, multi: true },
  ],
  exports: [
    MatProgressSpinnerModule,
    MatDialogModule,
  ],
  bootstrap: [AppComponent]
})

export class AppModule {}
