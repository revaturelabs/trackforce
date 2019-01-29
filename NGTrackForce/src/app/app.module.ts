///
//  DEPENDENCIES
///

import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './routing/app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ChartsModule } from 'ng2-charts';

import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDialogModule } from '@angular/material/dialog';

///
//  COMPONENTS
///
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AssociateViewComponent } from './components/associate-view/associate-view.component';


import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { DateTimePickerComponent } from './components/datetimepicker/datetimepicker.component';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

///
//  SERVICES
///
import { RequestService } from './services/request-service/request.service';
import { AssociateService } from './services/associate-service/associate.service';
import { AuthenticationService } from './services/authentication-service/authentication.service';
import { CurriculumService } from './services/curriculum-service/curriculum.service';
import { UserService } from './services/user-service/user.service';
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
import { TrainerService } from './services/trainer-service/trainer.service';
import { InvalidSessionComponent } from './components/invalid-session/invalid-session.component';
import { HighlightInterviewDirective } from './directives/highlight-interview.directive';
import { UpdateDialogComponent } from './components/associate-list-page/associate-list-page.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    AssociateViewComponent,
    RouterLinkStubDirective,
    RouterOutletStubComponent,
    FooterComponent,
    NotFoundComponent,
    DateTimePickerComponent,
    InvalidSessionComponent,
    HighlightInterviewDirective,
    UpdateDialogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
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
    AuthenticationService,
    RequestService,
    CurriculumService,
    UserService,
    CurriculumService,
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
