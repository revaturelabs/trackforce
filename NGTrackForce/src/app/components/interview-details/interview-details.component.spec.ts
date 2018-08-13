import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import { InterviewDetailsComponent } from './interview-details.component';
//added imports; DK
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { InterviewService } from '../../services/interview-service/interview.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { ActivatedRoute } from '../../testing-helpers/router-stubs';
import {RequestService} from '../../services/request-service/request.service';
import {Observable} from 'rxjs/Observable';
import { Batch } from '../../models/batch.model';
import { User } from '../../models/user.model';
import { MarketingStatus } from '../../models/marketing-status.model';
import { Client } from '../../models/client.model';
import { EndClient } from '../../models/end-client.model';
import { Associate } from '../../models/associate.model';
import { HttpClient, HttpHandler } from '../../../../node_modules/@angular/common/http';

export class MockActivatedRoute {
  static createMockRoute(tid: number): any {
    return {
      params: Observable.of({id: tid}),
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

describe('InterviewDetailsComponent', () => {
  let component: InterviewDetailsComponent;
  let fixture: ComponentFixture<InterviewDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      declarations: [ InterviewDetailsComponent ],
      imports: [ FormsModule, RouterTestingModule ],
      providers: [
        RequestService,
        HttpClient,
        HttpHandler,
        InterviewService,
        {provide: AuthenticationService, useClass: MockAuthenticationService},
        {provide: ActivatedRoute, useValue: MockActivatedRoute.createMockRoute(1)}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InterviewDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
