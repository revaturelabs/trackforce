import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewReportsComponent } from './view-reports.component';

describe('ViewReportsComponent', () => {
  let component: ViewReportsComponent;
  let fixture: ComponentFixture<ViewReportsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewReportsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
