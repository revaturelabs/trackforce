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


///
//  CONSTANTS
///

import { appRoutes } from './routing/routes';
import { SkillsetComponent } from './components/skillset/skillset.component';


@NgModule({
  declarations: [
    AppComponent,
    SkillsetComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
