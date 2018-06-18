import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionsComponent } from './predictions.component';
// added imports; DK
import { FormsModule } from '@angular/forms';
import { SkillsetService } from '../../services/skill-set-service/skill-set.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { PredictionService } from '../../services/prediction-service/prediction.service';

describe('PredictionsComponent', () => {
  let component: PredictionsComponent;
  let fixture: ComponentFixture<PredictionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PredictionsComponent ],
      imports: [ FormsModule, HttpClientTestingModule ],
      providers: [ SkillsetService, PredictionService ]
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
