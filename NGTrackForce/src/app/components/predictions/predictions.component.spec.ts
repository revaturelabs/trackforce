import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionsComponent } from './predictions.component';
// added imports; DK
import { FormsModule } from '@angular/forms';
import { CurriculumService } from '../../services/curriculum-service/curriculum.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('PredictionsComponent', () => {
  let component: PredictionsComponent;
  let fixture: ComponentFixture<PredictionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PredictionsComponent ],
      imports: [ FormsModule, HttpClientTestingModule ],
      providers: [ CurriculumService ]
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
