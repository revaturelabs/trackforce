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

///
//  SERVICES
///
import { AssociateService } from '../services/associates-service';

///
//  CONSTANTS
///

import { appRoutes } from './routing/routes';
import { AssociateListComponent } from './components/associate-list/associate-list.component';
import { ClientService } from '../services/clients-service';

@NgModule({
  declarations: [
    AppComponent,
    AssociateListComponent
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
