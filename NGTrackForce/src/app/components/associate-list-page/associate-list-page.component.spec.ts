import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociateListPageComponent } from './associate-list-page.component';

describe('AssociateListPageComponent', () => {
  let component: AssociateListPageComponent;
  let fixture: ComponentFixture<AssociateListPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssociateListPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
