import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  imports: [MatButtonModule, MatProgressSpinnerModule],
  exports: [MatButtonModule, MatProgressSpinnerModule],
})
export class MaterialModule { }