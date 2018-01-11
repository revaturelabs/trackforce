///
//  DEPENDENCIES
///

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule, JsonpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

///
//  COMPONENTS
///

import { AppComponent } from './app.component';
import { AssociateListComponent } from './components/associate-list/associate-list.component';
import { BatchListComponent } from './components/batch-list/batch-list.component';
import { FormComponent } from './components/form-component/form.component';
import { ClientMappedComponent } from './components/client-mapped/client-mapped.component';

///
//  SERVICES
///

import { AssociateService } from './services/associates-service';
import { ClientService } from './services/clients-service';

///
//  CONSTANTS
///

import { appRoutes } from './routing/routes';
import { SearchByTextFilter } from '../pipes/searchfilter.pipes';

@NgModule({
  declarations: [
    AppComponent,
    AssociateListComponent,
    SearchByTextFilter,
    BatchListComponent,
    ClientMappedComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [AssociateService, ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
