import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AssociateListComponent} from './associate-list.component';
import {AssociateService} from '../../services/associates-service/associates-service';
import {ClientListService} from '../../services/client-list-service/client-list.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormsModule} from '@angular/forms';
import {AssociateSearchByTextFilter} from '../../pipes/associate-search-by-text-filter/associate-search-by-text-filter.pipes';
import {NavbarComponent} from '../navbar/navbar.component';
import {RouterTestingModule} from '@angular/router/testing';
import {RootComponent} from '../root/root.component';
import {HomeComponent} from '../home/home.component';
import {ChartsModule} from 'ng2-charts';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {RequestService} from '../../services/request-service/request.service';
import {User} from '../../models/user.model';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';


describe('AssociateListComponent', () => {
  let component: AssociateListComponent;
  let fixture: ComponentFixture<AssociateListComponent>;
  const testAuthService: AuthenticationService = new AuthenticationService(null, null);

  // setup service mocks
   beforeAll(() => {
    const user: User = new User();
    user.token = "mockToken";
    user.username = "mockUser";
    user.tfRoleId = 1;
    spyOn(testAuthService, 'getUser').and.returnValue(user);  // needed by navbar
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        AssociateListComponent,
        AssociateSearchByTextFilter,
        NavbarComponent,
        RootComponent,
        HomeComponent,
        NavbarComponent
      ],
      imports: [
        HttpClientTestingModule,
        FormsModule,
        RouterTestingModule,
        ChartsModule
      ],
      providers: [
        AssociateService,
        ClientListService,
        RequestService,
        {provide: AuthenticationService, useValue: testAuthService}
      ],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ]
    });
    fixture = TestBed.createComponent(AssociateListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
