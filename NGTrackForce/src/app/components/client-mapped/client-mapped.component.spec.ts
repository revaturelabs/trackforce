import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ClientMappedComponent } from './client-mapped.component';
import { ChartsModule } from 'ng2-charts';
import { ClientMappedService } from '../../services/client-mapped-service/client-mapped-service';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterTestingModule } from '@angular/router/testing';
import { RootComponent } from '../root/root.component';
import { HomeComponent } from '../home/home.component';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {RequestService} from '../../services/request-service/request.service';
import { ClientMappedModel } from '../../models/clientMapped.model';
import { User } from '../../models/user.model';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";

describe('ClientMappedComponent', () => {
  let component: ClientMappedComponent;
  let fixture: ComponentFixture<ClientMappedComponent>;
  const testClientMappedService: ClientMappedService = new ClientMappedService(null);
  const testAuthService: AuthenticationService = new AuthenticationService(null, null);

  //Setup service mocks
  beforeAll(() => {
    //Mock data
    const client1: ClientMappedModel = new ClientMappedModel();
    client1.name = "Client 1";
    client1.count = 50;
    const client2: ClientMappedModel = new ClientMappedModel();
    client2.name = "Client 2";
    client2.count = 30;
    const client3: ClientMappedModel = new ClientMappedModel();
    client3.name = "Client 3";
    client3.count = 40;

    //Mock the ClientMappedService
    spyOn(testClientMappedService, 'getAssociatesByStatus').and.returnValue(Observable.of([client1, client2, client3]));

    //Mock the Authentication Service
    const user: User = new User();
    user.token = "mockToken";
    user.username = "mockUser";
    user.tfRoleId = 1;
    spyOn(testAuthService, 'getUser').and.returnValue(user);
  });

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        ClientMappedComponent,
        NavbarComponent,
        RootComponent,
        HomeComponent
      ],
      imports: [
        ChartsModule,
        HttpClientTestingModule,
        RouterTestingModule
      ],
      providers: [
        RequestService,
        {provide: AuthenticationService, useValue: testAuthService},
        {provide: ClientMappedService, useValue: testClientMappedService}
      ],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ]
    })
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientMappedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //Smoke test. Check the component is created
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //Grab mock data and test length of returned array
  it('should grab data', () => {
    expect(component.clientMappedData.length).toEqual(3);
  });

  //Test that chart is of type 'bar' by default
  it('should display bar chart by default', () => {
    //Variable containing the expected chart type
    let chart_type = 'bar';

    //Grab the graph from the DOM
    let the_graph = document.getElementById("the_graph");

    //Test the chartType field in Component
    expect(component.chartType).toEqual(chart_type);

    //Test the chartType in the DOM
    expect(the_graph.getAttribute("ng-reflect-chart-type")).toEqual(chart_type);
  });
});
