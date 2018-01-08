///
//  DEPENDENCIES
///

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

///
//  COMPONENTS
///

import { AppComponent } from './app.component';

///
//  SERVICES
///

import {ClientListService} from './services/client-list/client-list.service';
///
//  CONSTANTS
///

import { appRoutes } from './routing/routes';
import { ClientListComponent } from './components/client-list/client-list.component';
import { ClientMappedComponent } from './components/client-mapped/client-mapped.component';


@NgModule({
  declarations: [
    AppComponent,
    ClientListComponent,
    ClientMappedComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ClientListService],
  bootstrap: [AppComponent]
})
export class AppModule { }
