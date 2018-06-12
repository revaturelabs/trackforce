import {ComponentFixture, TestBed} from '@angular/core/testing';

import {NavbarComponent} from './navbar.component';
import {RouterTestingModule} from '@angular/router/testing';
import {RootComponent} from '../root/root.component';
import {HomeComponent} from '../home/home.component';
import {ChartsModule} from 'ng2-charts';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {RequestService} from '../../services/request-service/request.service';
import {HttpClientModule} from '@angular/common/http';
import {User} from '../../models/user.model';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;
  const testAuthService: AuthenticationService = new AuthenticationService(null, null, null);

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
        NavbarComponent,
        RootComponent,
        HomeComponent,
      ],
      imports: [
        HttpClientModule,
        RouterTestingModule,
        ChartsModule,
      ],
      providers: [
        RequestService,
        {provide: AuthenticationService, useValue: testAuthService},
      ],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ]
    });
    fixture = TestBed.createComponent(NavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
