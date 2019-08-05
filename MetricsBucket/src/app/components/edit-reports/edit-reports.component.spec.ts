import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditReportsComponent } from './edit-reports.component';

describe('EditReportsComponent', () => {
  let component: EditReportsComponent;
  let fixture: ComponentFixture<EditReportsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditReportsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
