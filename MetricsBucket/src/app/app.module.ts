import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips'
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon'
import { MatInputModule } from '@angular/material/input'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'


import { AppComponent } from './app.component';
import { SprintReportsComponent } from './components/sprint-reports/sprint-reports.component';
import { CommonModule } from '@angular/common';
import { ViewReportsComponent, SafePipe } from './components/view-reports/view-reports.component';
import { UploadReportsComponent } from './components/upload-reports/upload-reports.component';
import { EditReportsComponent } from './components/edit-reports/edit-reports.component';
import { Browser } from 'protractor';

@NgModule({
  declarations: [
    AppComponent,
    SprintReportsComponent,
    ViewReportsComponent,
    SafePipe,
    UploadReportsComponent,
    EditReportsComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    MatChipsModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
 