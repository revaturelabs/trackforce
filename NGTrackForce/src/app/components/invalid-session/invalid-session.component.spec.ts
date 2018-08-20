import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvalidSessionComponent } from './invalid-session.component';

describe('InvalidSessionComponent', () => {
  let component: InvalidSessionComponent;
  let fixture: ComponentFixture<InvalidSessionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvalidSessionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvalidSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
