import { HttpErrorResponse } from '@angular/common/http';
/**
 * @author Michael Tseng
 * @description Spec needed for testing login component
 */
import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginComponent} from './login.component';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from '@angular/forms';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RequestService} from '../../services/request-service/request.service';
import {NavbarComponent} from '../navbar/navbar.component';
import {HomeComponent} from '../home/home.component';
import {ChartsModule} from 'ng2-charts';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
// added imports; DK
import { AssociateService } from "../../services/associate-service/associate.service";
import { UserService } from "../../services/user-service/user.service";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InterviewService } from '../../services/interview-service/interview.service';
import { ClientService } from '../../services/client-service/client.service';
import { BatchService } from '../../services/batch-service/batch.service';
import { TrainerService } from '../../services/trainer-service/trainer.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  let routes = [
      {
          path: 'login',
          component: LoginComponent
      }
  ]

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        LoginComponent,
        NavbarComponent,
        HomeComponent
      ],
      providers: [
        AuthenticationService,
        RequestService,
        AssociateService,
        UserService,
        InterviewService,
        ClientService,
        BatchService,
        TrainerService
      ],
      imports: [
        FormsModule,
        HttpClientTestingModule,
        RouterTestingModule.withRoutes(routes),
        ChartsModule,
        BrowserAnimationsModule
      ],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ]
    });
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('OnInit should create user thats not null', () => {
    component.loginClicked = true;
    component.isLoggingIn = true;
    expect(component.loginClicked).toBeTruthy();
    expect(component.isLoggingIn).toBeTruthy();
  });

  it('should set isLoggingIn to true if clicked login', () => {
    component.loginClicked = true;
    expect(component.isLoggingIn).toBeTruthy();
  });

  it('should set default values when in register state', () => {
    component.register();
    expect(component.errMsg).toBeFalsy();
    expect(component.sucMsg).toBeFalsy();
    expect(component.isRegistering).toBeTruthy();
    expect(component.registerPage).toBeFalsy();
  });

  it('should set default values when in createUser state', () => {
    component.sucMsg = undefined;
    component.errMsg = undefined;
    expect(component.errMsg).toBeFalsy();
    expect(component.sucMsg).toBeFalsy();
  });

  it('should give an error message if negative values', () => {
    component.password = undefined;
    component.cpassword = undefined;
    component.createUser();
    expect(component.errMsg).toEqual('Please enter a password and confirm password!');
  });

  it('if password and cpassword are different, special error message', () => {
    component.password = 'test';
    component.cpassword = 'nottest';
    component.createUser();
    expect(component.errMsg).toEqual('Passwords do not match!');
  });

  it('if restrictions for username AND password are invalid, special error message', () => {
    component.username = '!@#invalidASDDAS*****()()~`notsurewhatsinvalid    ...';
    component.password = '!@#invalidASDDAS*****()()~`notsurewhatsinvalid    ...';
    component.createUser();
    expect(component.errMsg).toBeTruthy();
  });

  it('if restrictions for username are invalid, special error message', () => {
    component.username = '!@#invalidASDDAS*****()()~`notsurewhatsinvalid    ...';
    component.password = 'good';
    component.createUser();
    expect(component.errMsg).toBeTruthy();
  });

  it('if restrictions for password are invalid, special error message', () => {
    component.username = 'good';
    component.password = '!@#invalidASDDAS*****()()~`notsurewhatsinvalid    ...';
    component.createUser();
    expect(component.errMsg).toBeTruthy();
  });

  // it('if role is 2 for trainer, go into switch case', () => {
  //   component.role = 2;
  //   component.username = 'Trainer';
  //   component.password = 'Trainer';
  //   component.fname = 'newTrainerFname';
  //   component.lname = 'newTrainerLname';
  //   component.createUser();
  //   expect(component.newUser).toBeTruthy();
  //   expect(component.newTrainer).toBeTruthy();
  // });

});
