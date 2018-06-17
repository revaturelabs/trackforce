import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerViewComponent } from './trainer-view.component';

describe('TrainerViewComponent', () => {
  let component: TrainerViewComponent;
  let fixture: ComponentFixture<TrainerViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
