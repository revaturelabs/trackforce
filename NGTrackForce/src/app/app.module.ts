///
//  DEPENDENCIES
///

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule, JsonpModule } from '@angular/http';

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

@NgModule({
  declarations: [
    AppComponent,
    AssociateListComponent,
    BatchListComponent,
    ClientMappedComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [AssociateService, ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
