import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SprintReportsComponent } from './sprint-reports.component';

describe('SprintReportsComponent', () => {
  let component: SprintReportsComponent;
  let fixture: ComponentFixture<SprintReportsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SprintReportsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SprintReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
