///
//  DEPENDENCIES
///
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ChartsModule } from 'ng2-charts';
import { Ng2OrderModule } from 'ng2-order-pipe';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
///
//  COMPONENTS
///
import { AppComponent } from './app.component';
import { AssociateListComponent } from './components/associate-list/associate-list.component';
import { BatchListComponent } from './components/batch-list/batch-list.component';
import { ClientMappedComponent } from './components/client-mapped/client-mapped.component';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { LoginComponent } from './components/login/login.component';
import { ClientListComponent } from './components/client-list/client-list.component';
import { FormComponent } from './components/form-component/form.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { SkillsetComponent } from './components/skillset/skillset.component';
import { BatchDetailsComponent } from './components/batch-details/batch-details.component';
import { AssociateViewComponent } from './components/associate-view/associate-view.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { PredictionsComponent } from './components/predictions/predictions.component';
import { MyInterviewComponent } from './components/myinterview-view/myinterview-view.component';
import { InterviewsComponent } from './components/interviews-view/interviews-view.component';
///
//  SERVICES
///
import { RequestService } from './services/request-service/request.service';
import { AssociateService } from './services/associate-service/associate.service';
import { ClientService } from './services/client-service/client.service';
import { AuthenticationService } from './services/authentication-service/authentication.service';
import { SearchFilterPipe } from './pipes/search-filter/search-filter.pipe';
import { BatchService } from './services/batch-service/batch.service';
import { CurriculumService } from './services/curriculum-service/curriculum.service';
import { DataSyncService } from './services/datasync-service/data-sync.service';
import { UserService } from './services/user-service/user.service';
import { PredictionService } from './services/prediction-service/prediction.service';
import { InterviewService } from './services/interview-service/interview.service'

///
//  FILTERS
///

import { AssociateSearchByTextFilter } from './pipes/associate-search-by-text-filter/associate-search-by-text-filter.pipes';

///
//  SECURITY
///
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { AuthGuard } from './guards/auth.guard';

///
//  CONSTANTS
///
import { appRoutes } from './routing/routes';
import { RouterLinkStubDirective, RouterOutletStubComponent } from './testing-helpers/router-stubs';
import { InterviewDetailsComponent } from './components/interview-details/interview-details.component';
import { TrainerViewComponent } from './components/trainer-view/trainer-view.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    AssociateListComponent,
    AssociateSearchByTextFilter,
    BatchListComponent,
    ClientMappedComponent,
    FormComponent,
    ClientListComponent,
    LoginComponent,
    CreateUserComponent,
    SearchFilterPipe,
    BatchDetailsComponent,
    SkillsetComponent,
    AssociateViewComponent,
    RouterLinkStubDirective,
    RouterOutletStubComponent,
    FooterComponent,
    NotFoundComponent,
    PredictionsComponent,
    MyInterviewComponent,
    InterviewDetailsComponent,
  	InterviewsComponent,
  	TrainerViewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    ChartsModule,
    Ng2OrderModule,
	  BrowserAnimationsModule
  ],
  providers: [
    AssociateService,
    ClientService,
    AuthenticationService,
    RequestService,
    CurriculumService,
    BatchService,
    UserService,
    CurriculumService,
    DataSyncService,
    InterviewService,
    AuthGuard,
    PredictionService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
