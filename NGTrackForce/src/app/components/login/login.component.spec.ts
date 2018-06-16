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
import{ AssociateService } from "../../services/associate-service/associate.service";
import{ UserService } from "../../services/user-service/user.service";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

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
        UserService
      ],
      imports: [
        FormsModule,
        HttpClientTestingModule,
        RouterTestingModule,
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
