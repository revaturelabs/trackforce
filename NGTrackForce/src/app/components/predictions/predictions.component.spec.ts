import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import { PredictionsComponent } from './predictions.component';
// added imports; DK
import { FormsModule } from '@angular/forms';
import { CurriculumService } from '../../services/curriculum-service/curriculum.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { AssociateService } from '../../services/associate-service/associate.service';
import { BatchService } from '../../services/batch-service/batch.service';

describe('PredictionsComponent', () => {
  let component: PredictionsComponent;
  let fixture: ComponentFixture<PredictionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PredictionsComponent ],
      imports: [ FormsModule, HttpClientTestingModule ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [ BatchService, CurriculumService, AssociateService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PredictionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
