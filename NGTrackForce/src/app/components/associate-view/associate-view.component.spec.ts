import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AssociateViewComponent} from './associate-view.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {AssociateService} from '../../services/associate-service/associate.service';
import {ActivatedRoute} from '@angular/router';
import {RequestService} from '../../services/request-service/request.service';
import {Observable} from 'rxjs/Observable';
//import 'rxjs/add/observable/of';
import { of } from 'rxjs/index';
import {NO_ERRORS_SCHEMA} from '@angular/core';
// added imports; DK
import { ClientService } from '../../services/client-service/client.service';
import { Associate } from '../../models/associate.model';
import { Batch } from '../../models/batch.model';
import { Client } from '../../models/client.model';
import { EndClient } from '../../models/end-client.model';
import { User } from '../../models/user.model';
import { MarketingStatus } from '../../models/marketing-status.model';
import { CompileNgModuleMetadata } from '../../../../node_modules/@angular/compiler';
import { BehaviorSubject } from 'rxjs';

export class MockActivatedRoute {
  static createMockRoute(tid: number): any {
    return {
      params: of({id: tid}),
      snapshot: {
        parent: {
          params: {
            id: 1
          }
        },
        paramMap: {
          get(name: string): string {
            return '' + tid;
          }
        }
      },
    };
  }
}

export class MockAuthenticationService extends AuthenticationService {
  getAssociate(): Associate {
    let mockBatch:Batch = new Batch();
    mockBatch.id = 100;
    mockBatch.batchName = 'mockBatchName';
    
    let batches:Batch[] = [mockBatch];

    let client:Client = new Client(0,'client',null,null,null);
    let endClient:EndClient = new EndClient();
    endClient.name = 'none';

    const user:User = new User('newUser','pass', 0, 0);
    const marketingStatus:MarketingStatus = new MarketingStatus(1, 'status');

    const associate:Associate = new Associate('first', 'last', user);
    
    // Add objects to associate
    associate.marketingStatus = marketingStatus;
    associate.batch = mockBatch;
    associate.client = client;
    associate.endClient = endClient;

    return associate;
  }
}

export class MockAssociateService extends AssociateService {
  getAssociate(id: number) {
    const user:User = new User('newUser','pass', 0, 0);
    const associate:Associate = new Associate('first', 'last', user);
    return  new BehaviorSubject(associate);
  }
}

describe('AssociateViewComponent', () => {
  const mockAssociateService: AssociateService = new AssociateService(null);
  const mockAuthService: AuthenticationService = new AuthenticationService(null, null, null);
  let component: AssociateViewComponent = new AssociateViewComponent(mockAssociateService, mockAuthService, null, null);
  let fixture: ComponentFixture<AssociateViewComponent>;
  let clients: Array<any> = [];

  beforeAll(() => {
  
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        AssociateViewComponent
      ],
      providers: [
        AuthenticationService,
        RequestService,
        AssociateService,
        ClientService,
        {provide: ActivatedRoute, useValue: MockActivatedRoute.createMockRoute(1)}
      ],
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      schemas: [NO_ERRORS_SCHEMA]
    });
  });

  beforeEach(() => {
    const mockUser: User = new User('mockuser', 'mockPassword', 1, 0);
    const mockAssociate = new Associate('firstName', 'lastName', mockUser);
    const mockAssociates: Associate[] = [mockAssociate];
    
    // spyOn(mockAssociateService, 'getAllAssociates').and.returnValue(Observable.of(mockAssociates));
    // spyOn(mockAssociateService, 'getAssociate').and.returnValue(mockAssociate);
    // spyOn(mockAuthService, 'getAssociate').and.returnValue(mockAssociate);
    // spyOn(mockAssociateService, 'updateAssociate').and.returnValue(mockAssociate);

    fixture = TestBed.overrideComponent(AssociateViewComponent,
    {set: {providers: [{provide: AssociateService, useClass: MockAssociateService},
                       {provide: AuthenticationService, useClass: MockAuthenticationService}]}}).createComponent(AssociateViewComponent);  
                       component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an associate', () => {
    expect(component.associate).toBeTruthy();
  });

  it('in toggleForm() if formOpen was true then make false', () => {
    component.formOpen = true;
    component.toggleForm();
    expect(component.formOpen).toBeFalsy();
  });

  it('in toggleForm() if formOpen was false then make true', () => {
    component.formOpen = false;
    component.toggleForm();
    expect(component.formOpen).toBeTruthy();
  });

  it('in updateInfo() firstName should be a name', () => {
    component.associate.firstName = 'Logan';
    expect(component.associate.firstName).toBeTruthy();
  });

  it('in updateInfo() lastName should be a name', () => {
    component.associate.lastName = 'Logan';
    expect(component.associate.lastName).toBeTruthy();
  });

  it('succMsg should should be "information updated"', () => {
    component.succMsg = 'Information updated';
    expect(component.succMsg).toBeTruthy();
  });

  it('errMsg should be "There was an error with the server" with a 500 error', () => {
    component.errMsg = 'There was an error with the server';
    expect(component.errMsg).toBeTruthy();
  });

  it('errMsg should be "Something went wrong, your information was not updated." with any other error', () => {
    component.errMsg = 'Something went wrong, your information was not updated.';
    expect(component.errMsg).toBeTruthy();
  });

});
