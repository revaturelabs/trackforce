///
//  DEPENDENCIES
///

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
///
//  COMPONENTS
///

import { AppComponent } from './app.component';
import { AssociateListComponent } from './components/associate-list/associate-list.component';
import { BatchListComponent } from './components/batch-list/batch-list.component';
import { ClientMappedComponent } from './components/client-mapped/client-mapped.component';

///
//  SERVICES
///

import { AssociateService } from '../services/associates-service';
import { ClientService } from '../services/clients-service';

///
//  CONSTANTS
///

import { appRoutes } from './routing/routes';
import { ClientListComponent } from './components/client-list/client-list.component';


@NgModule({
  declarations: [
    AppComponent,
    ClientListComponent,
    AssociateListComponent,
    BatchListComponent,
    ClientMappedComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [AssociateService, ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
