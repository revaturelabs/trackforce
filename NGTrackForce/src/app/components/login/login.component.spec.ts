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
});
