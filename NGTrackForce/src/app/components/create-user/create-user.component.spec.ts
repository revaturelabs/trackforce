import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateUserComponent} from './create-user.component';
import {NavbarComponent} from '../navbar/navbar.component';
import {RouterTestingModule} from '@angular/router/testing';
import {HomeComponent} from '../home/home.component';
import {ChartsModule} from 'ng2-charts';
import {FormsModule} from '@angular/forms';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {RequestService} from '../../services/request-service/request.service';
import {HttpClientModule} from '@angular/common/http';
import {UserService} from '../../services/user-service/user.service';
import {User} from '../../models/user.model';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import { of } from 'rxjs/observable/of';

fdescribe('CreateUserComponent', () => {
  let component: CreateUserComponent;
  let fixture: ComponentFixture<CreateUserComponent>;
  const testAuthService: AuthenticationService = new AuthenticationService(null, null, null);
  let spy: any;

  // setup service mocks
  beforeAll(() => {
    let user = new User("mockUser", "mockPassword", 1, 0, 0, "mockToken");
    spyOn(testAuthService, 'getUser').and.returnValue(of(user));  // needed by navbar
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        CreateUserComponent,
        NavbarComponent,
        HomeComponent
      ],
      imports: [
        RouterTestingModule,
        ChartsModule,
        FormsModule,
        HttpClientModule
      ],
      providers: [
        UserService,
        RequestService,
        {provide: AuthenticationService, useValue: testAuthService}
      ],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ]
    });
    fixture = TestBed.createComponent(CreateUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //This button's property "disabled" is bound to a function. But is that function ever actually called?
  it('should have a button which triggers the createUser function', () =>{
    spy = spyOn(component, 'createUser');
    //let spy2 = spyOn(component, 'toggleSubmitButton').and.returnValue(true);
    let el = fixture.debugElement.nativeElement;
    let btn = el.querySelector('button');
    btn.click();
    expect(spy).toHaveBeenCalled();
  });
});