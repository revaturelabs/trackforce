import {ComponentFixture, TestBed, async} from '@angular/core/testing';

import {AssociateListComponent} from './associate-list.component';
import {AssociateService} from '../../services/associate-service/associate.service';
import {ClientService} from '../../services/client-service/client.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormsModule} from '@angular/forms';
import {AssociateSearchByTextFilter} from '../../pipes/associate-search-by-text-filter/associate-search-by-text-filter.pipes';
import {NavbarComponent} from '../navbar/navbar.component';
import {RouterTestingModule} from '@angular/router/testing';
import {HomeComponent} from '../home/home.component';
import {ChartsModule} from 'ng2-charts';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {RequestService} from '../../services/request-service/request.service';
import {User} from '../../models/user.model';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';


describe('AssociateListComponent', () => {
  let component: AssociateListComponent;
  let fixture: ComponentFixture<AssociateListComponent>;
  const testAuthService: AuthenticationService = new AuthenticationService(null, null, null);

  // setup service mocks
   beforeAll(() => {
    let user = new User("mockUser", "mockPassword", 1, 0, 0, "mockToken");
    // user.token = "mockToken";
    // user.username = "mockUser";
    // user.role = 1;
    spyOn(testAuthService, 'getUser').and.returnValue(user);  // needed by navbar
  });

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AssociateListComponent,
        AssociateSearchByTextFilter,
        NavbarComponent,
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
        ClientService,
        RequestService,
        {provide: AuthenticationService, useValue: testAuthService}
      ],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeDefined();
  });

  it('should have a curriculums dropdown', () => {
    expect(component.curriculums).toBeTruthy();
  });






});
