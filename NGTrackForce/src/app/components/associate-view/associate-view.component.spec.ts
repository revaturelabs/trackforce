import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociateViewComponent } from './associate-view.component';

describe('AssociateViewComponent', () => {
  let component: AssociateViewComponent;
  let fixture: ComponentFixture<AssociateViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssociateViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
