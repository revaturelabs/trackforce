import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordUpdateComponent } from './passwordupdate.component';

describe('PasswordupdateComponent', () => {
  let component: PasswordUpdateComponent;
  let fixture: ComponentFixture<PasswordUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PasswordUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PasswordUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
