import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsernameupdateComponent } from './usernameupdate.component';

describe('UsernameupdateComponent', () => {
  let component: UsernameupdateComponent;
  let fixture: ComponentFixture<UsernameupdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsernameupdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsernameupdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
