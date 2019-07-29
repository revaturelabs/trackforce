import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SprintReportsComponent } from './components/sprint-reports/sprint-reports.component';

@NgModule({
  declarations: [
    AppComponent,
    SprintReportsComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
