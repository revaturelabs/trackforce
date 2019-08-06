import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadReportsComponent } from './upload-reports.component';

describe('UploadReportsComponent', () => {
  let component: UploadReportsComponent;
  let fixture: ComponentFixture<UploadReportsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadReportsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
