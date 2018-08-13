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
import {GraphCounts} from "../../models/graph-counts";
import { User } from '../../models/user.model';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {AssociateService} from "../../services/associate-service/associate.service";
import { Associate } from '../../models/associate.model';
import { HttpClient } from '../../../../node_modules/@angular/common/http';

export class mockClientMappedComponent extends ClientMappedComponent {
  public getAssociatesByStatus(statusId: number) {
    let name1: number = 1;
    let name2: number = 2;
    let name3: number = 3;

    let names: number[] = [name1, name2, name3];
    this.clientMappedData = names;
  }
}

describe('ClientMappedComponent', () => {
  let component: ClientMappedComponent;
  let fixture: ComponentFixture<ClientMappedComponent>;
  let httpMock: HttpTestingController;
  const testClientService: ClientService = new ClientService(null);
  const testAssociateService = new AssociateService(new HttpClient(null));
  const testAuthService: AuthenticationService = new AuthenticationService(null, null, null);

  //Setup service mocks
  beforeAll(() => {
    //Mock data
    const client1: GraphCounts = new GraphCounts();
    client1.name = "Client 1";
    client1.count = 50;
    const client2: GraphCounts = new GraphCounts();
    client2.name = "Client 2";
    client2.count = 30;
    const client3: GraphCounts = new GraphCounts();
    client3.name = "Client 3";
    client3.count = 40;


    let user: User = new User('mockUser','mockPassword',1,0);
    user.token = "mockToken";
    let associate1: Associate = new Associate('first1','last1',user);
    let associate2: Associate = new Associate('first2','last2',user);
    let associate3: Associate = new Associate('first3','last3',user);

    let associates: Associate[] = [associate1, associate2, associate3];


    
    // Mock the AssociateService
    // Note: this used to be "Mock the Client Service" with the same method.
    // That was spitting up errors because getAssociatesByStatus wasn't in Client Service,
    // so I switched it to testAssociateService
    // spyOn(testAssociateService, 'getAssociatesByStatus').and.returnValue(Observable.of([client1, client2, client3]));

    // Mock the Authentication Service
    
    spyOn(testAuthService, 'getUser').and.returnValue(user);
    spyOn(testAssociateService, 'getAllAssociates').and.returnValue(Observable.of(associates));

  });

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        ClientMappedComponent,
        NavbarComponent,
        HomeComponent,
        mockClientMappedComponent
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
    });

    httpMock = TestBed.get(HttpTestingController);    
  }));

  beforeEach(() => {

        // Mock local storage
        let store = {};
        const mockLocalStorage = {
          getItem: (key: string): string => {
            return key in store ? store[key] : null;
          },
          setItem: (key: string, value: String) => {
            store[key] = `${value}`;
          },
          removeItem: (key: string) => {
            delete store[key];
          },
          clear: () => {
            store = {};
          }
        };
    
        mockLocalStorage.setItem('mappedData',JSON.stringify([1,2,3,4]));
        
        spyOn(localStorage, 'getItem').and.callFake(mockLocalStorage.getItem);
        spyOn(localStorage, 'setItem').and.callFake(mockLocalStorage.setItem);
        spyOn(localStorage,'removeItem').and.callFake(mockLocalStorage.removeItem);
        spyOn(localStorage, 'clear').and.callFake(mockLocalStorage.clear);

        fixture = TestBed.createComponent(mockClientMappedComponent);
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
    const chart_type = 'bar';

    //Grab the graph from the DOM
    const the_graph = document.getElementById("the_graph");

    //Test the chartType field in Component
    expect(component.chartType).toEqual(chart_type);

    //Test the chartType in the DOM
    expect(the_graph.getAttribute("ng-reflect-chart-type")).toEqual(chart_type);
  });
});
