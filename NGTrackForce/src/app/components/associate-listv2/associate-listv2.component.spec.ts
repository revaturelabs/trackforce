import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociateListv2Component } from './associate-listv2.component';

describe('AssociateListv2Component', () => {
  let component: AssociateListv2Component;
  let fixture: ComponentFixture<AssociateListv2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssociateListv2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateListv2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
