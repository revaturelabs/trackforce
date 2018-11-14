import { NgModule } from '@angular/core';
import { SkillsetComponent } from './skillset.component';
import { CurriculumService } from '../../services/curriculum-service/curriculum.service';

import { SkillsetRoutingModule } from './skillset-routing.module';
import {CommonModule} from '@angular/common'

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
    declarations: [
      SkillsetComponent
    ],
    providers:[
      CurriculumService
    ],
    imports: [
      SkillsetRoutingModule,
      CommonModule,
      FormsModule,
      ChartsModule,
      MatProgressSpinnerModule
    ]
})
export class SkillsetModule { }
