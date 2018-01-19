/**
 * @author Michael Tseng
 * @description Spec needed for testing login component
 */
import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginComponent} from './login.component';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from '@angular/forms';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {RequestService} from '../../services/request-service/request.service';
import {RootComponent} from '../root/root.component';
import {NavbarComponent} from '../navbar/navbar.component';
import {HomeComponent} from '../home/home.component';
import {ChartsModule} from 'ng2-charts';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        LoginComponent,
        RootComponent,
        NavbarComponent,
        HomeComponent
      ],
      providers: [
        AuthenticationService,
        RequestService
      ],
      imports: [
        FormsModule,
        HttpClientTestingModule,
        RouterTestingModule,
        ChartsModule
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
