///
//  DEPENDENCIES
///

import { BrowserModule } from '@angular/platform-browser';
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
import { RequestService } from './services/request.service';


///
//  CONSTANTS
///

import { appRoutes } from './routing/routes';
import { HomeComponent } from './components/home/home.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [RequestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
