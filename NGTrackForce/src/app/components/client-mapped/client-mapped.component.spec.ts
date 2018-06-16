import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ClientMappedComponent } from './client-mapped.component';
import { ChartsModule } from 'ng2-charts';
import { ClientService} from "../../services/client-service/client.service";
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HomeComponent } from '../home/home.component';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {RequestService} from '../../services/request-service/request.service';
import { ClientMappedModel } from '../../models/clientMapped.model';
import { User } from '../../models/user.model';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {AssociateService} from "../../services/associate-service/associate.service";

describe('ClientMappedComponent', () => {
  let component: ClientMappedComponent;
  let fixture: ComponentFixture<ClientMappedComponent>;
  const testClientService: ClientService = new ClientService(null);
  const testAssociateService = new AssociateService(null);
  const testAuthService: AuthenticationService = new AuthenticationService(null, null, null);

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

    
    // Mock the AssociateService
    // Note: this used to be "Mock the Client Service" with the same method.
    // That was spitting up errors because getAssociatesByStatus wasn't in Client Service,
    // so I switched it to testAssociateService
    spyOn(testAssociateService, 'getAssociatesByStatus').and.returnValue(Observable.of([client1, client2, client3]));

    //Mock the Authentication Service
    const user: User = new User();
    user.token = "mockToken";
    user.username = "mockUser";
    user.role = 1;
    spyOn(testAuthService, 'getUser').and.returnValue(user);
  });

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        ClientMappedComponent,
        NavbarComponent,
        HomeComponent
      ],
      imports: [
        ChartsModule,
        HttpClientTestingModule,
        RouterTestingModule
      ],
      providers: [
        RequestService,
        AssociateService,
        {provide: AuthenticationService, useValue: testAuthService},
        {provide: ClientService, useValue: testClientService}
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
