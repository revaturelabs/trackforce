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

describe('CreateUserComponent', () => {
  let component: CreateUserComponent;
  let fixture: ComponentFixture<CreateUserComponent>;
  const testAuthService: AuthenticationService = new AuthenticationService(null, null, null);

  // setup service mocks
  beforeAll(() => {
    let user = new User("mockUser", "mockPassword", 1, 0, 0, "mockToken");
    spyOn(testAuthService, 'getUser').and.returnValue(user);  // needed by navbar
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
});
