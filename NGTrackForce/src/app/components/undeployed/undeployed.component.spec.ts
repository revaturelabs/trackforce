import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UndeployedComponent } from './undeployed.component';

describe('UndeployedComponent', () => {
  let component: UndeployedComponent;
  let fixture: ComponentFixture<UndeployedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UndeployedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UndeployedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
