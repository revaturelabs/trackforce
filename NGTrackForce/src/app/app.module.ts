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
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { RootComponent } from './components/root/root.component';
import { SkillsetComponent } from './components/skillset/skillset.component';
import { BatchDetailsComponent } from './components/batch-details/batch-details.component';
import { AssociateViewComponent } from './components/associate-view/associate-view.component';

///
//  SERVICES
///
import { RequestService } from './services/request-service/request.service';
import { AssociateService } from './services/associates-service/associates-service';
import { ClientListService } from './services/client-list-service/client-list.service';
import { ClientMappedService } from './services/client-mapped-service/client-mapped-service';
import { AuthenticationService } from './services/authentication-service/authentication.service';
import { SearchFilterPipe } from './pipes/search-filter/search-filter.pipe';
import { BatchService } from './services/batch-service/batch.service';
import { SkillsetService } from './services/skill-set-service/skill-set.service';
import { DataSyncService } from './services/datasync-service/data-sync.service';
import { CreateUserService } from './services/create-user-service/create-user.service';

///
//  FILTERS
///
///

import { AssociateSearchByTextFilter } from './pipes/associate-search-by-text-filter/associate-search-by-text-filter.pipes';

///
//  SECURITY
///
import { JwtInterceptor } from './interceptors/jwt.interceptor';

///
//  CONSTANTS
///
import { appRoutes } from './routing/routes';
import { RouterLinkStubDirective, RouterOutletStubComponent } from './testing-helpers/router-stubs';
import { FooterComponent } from './components/footer/footer/footer.component';


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
    RootComponent,
    SkillsetComponent,
    AssociateViewComponent,
    RouterLinkStubDirective,
    RouterOutletStubComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    ChartsModule,
    Ng2OrderModule
  ],
  providers: [
    AssociateService,
    ClientListService,
    ClientMappedService,
    AuthenticationService,
    RequestService,
    SkillsetService,
    BatchService,
    CreateUserService,
    SkillsetService,
    DataSyncService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
