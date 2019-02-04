import { HttpClient, HttpHandler } from '@angular/common/http';
import { RequestService } from './../../services/request-service/request.service';
import { FormsModule } from '@angular/forms';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsernameUpdateComponent } from './usernameupdate.component';
import { UserService } from '../../services/user-service/user.service';

describe('UsernameupdateComponent', () => {
  let component: UsernameUpdateComponent;
  let fixture: ComponentFixture<UsernameUpdateComponent>;
  let spy: any;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsernameUpdateComponent ],
      imports: [FormsModule],
      providers: [UserService, RequestService, HttpClient, HttpHandler]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsernameUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //Both of the tests in this suite currently fail because the user id that is referenced in ngOnit is null.
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a button that triggers the updateUsername function', () => {
    spy = spyOn(component, 'updateUsername');
    let el = fixture.debugElement.nativeElement;
    let btn = el.querySelector('button');
    btn.click();
    expect(spy).toHaveBeenCalled();
  });
});
