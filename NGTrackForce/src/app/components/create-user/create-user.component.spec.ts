import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateUserComponent} from './create-user.component';
import {NavbarComponent} from '../navbar/navbar.component';
import {RouterTestingModule} from '@angular/router/testing';
import {RootComponent} from '../root/root.component';
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
  const testAuthService: AuthenticationService = new AuthenticationService(null, null);

  // setup service mocks
  beforeAll(() => {
    const user: User = new User();
    user.token = 'mockToken';
    user.username = 'mockUser';
    user.tfRoleId = 1;
    spyOn(testAuthService, 'getUser').and.returnValue(user);  // needed by navbar
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        CreateUserComponent,
        NavbarComponent,
        RootComponent,
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
