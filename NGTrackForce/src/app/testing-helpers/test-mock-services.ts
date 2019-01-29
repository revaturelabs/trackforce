import { Component, OnInit } from '@angular/core';
import { InterviewService } from '../services/interview-service/interview.service';
import { AuthenticationService } from '../services/authentication-service/authentication.service';
import { of } from 'rxjs/observable/of';
import { Batch } from '../models/batch.model';
import { User } from '../models/user.model';
import { MarketingStatus } from '../models/marketing-status.model';
import { Client } from '../models/client.model';
import { EndClient } from '../models/end-client.model';
import { Associate } from '../models/associate.model';
import { Interview } from '../models/interview.model';
import { InterviewType } from '../models/interview-type';
import { Observable } from 'rxjs/Observable';
import { AssociateService } from '../services/associate-service/associate.service';
import { GraphCounts } from '../models/graph-counts';
import { Trainer } from '../models/trainer.model';
import { ClientService } from '../services/client-service/client.service';
import { BehaviorSubject } from 'rxjs';
import { CurriculumService } from '../services/curriculum-service/curriculum.service';
import { TrainerService } from '../services/trainer-service/trainer.service';
import { RequestService } from '../services/request-service/request.service';

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

export class MockAssociateService extends AssociateService {
  getAssociatesByStatus(statusId: number): Observable<GraphCounts[]> {
    const client1: GraphCounts = new GraphCounts();
    const client2: GraphCounts = new GraphCounts();
    const client3: GraphCounts = new GraphCounts();

    client1.name = "Client 1";
    client2.name = "Client 2";
    client3.name = "Client 3";
    
    client1.count = 50;
    client2.count = 30;
    client3.count = 40;

    return of([client1, client2, client3])
  }

  getUndeployedAssociates(mappedOrUnmapped: string): Observable<GraphCounts[]> {
    return MockServiceUtils.createMockGraphCounts()
  }

  fetchAssociateSnapshot(limit: number, filter) {
    let assoc1 = MockServiceUtils.createTestAssociate(1);
    let assoc2 = MockServiceUtils.createTestAssociate(2);
    let assoc3 = MockServiceUtils.createTestAssociate(3);

    return new BehaviorSubject<Associate[]>([assoc1,assoc2,assoc3]);
  }
}

export class MockAuthenticationService extends AuthenticationService {
  getAssociate(): Associate {
    return MockServiceUtils.createTestAssociate(1);
  }

  getTrainer(): Trainer {
    return new Trainer("mockFirstName", "mockLastName",null)
  }
}

export class MockClientService extends ClientService {
  getAllClients(): Observable<Client[]> {
    let client1 = new Client(0,"Test Client1",null,null,null)
    let client2 = new Client(1,"Test Client2",null,null,null)
    let client3 = new Client(2,"Test Client3",null,null,null)

    return of([client1,client2,client3])
  }

  getFiftyClients(): Observable<Client[]> {
    let clients = Client[50];
    for(let i = 0; i <= 50; i++) {
      clients[i] = new Client(i+1,"Test Client" + i,null,null,null)
    }

    return clients;
  }

  getAllClientsWithAssoc(): Observable<Client[]>{
    let client1 = new Client(0,"Test Client1",null,null,null)
    let client2 = new Client(1,"Test Client2",null,null,null)
    let client3 = new Client(2,"Test Client3",null,null,null)

    return of([client1, client2, client3]);
  }

  getClientCount(clientId: number): Observable<number>{
    return of(1234)
  }
}

export class MockCurriculumService extends CurriculumService {
  getSkillsetsForStatusID(statusID: number): Observable<GraphCounts[]> {
    return MockServiceUtils.createMockGraphCounts()
  }
}

export class MockInterviewService extends InterviewService {
  public getInterviewById(id: number): Observable<Interview> {
    let user = new User("mockUsername","mockPassword",5,1)
    let associate = new Associate('mockFirstName', 'mockLastName', user)
    let interviewType = new InterviewType(0, "Test Interview Type")
  
    let client = new Client(0,"Test Client",null,null,null)
    let interview = new Interview(associate, client, interviewType, 0,"Questions?",0,0,0)

    return of(interview)
  }

  public updateInterview(interview): Observable<boolean> {
    return of(true);
  }
}

export class MockRequestService extends RequestService {

  public populateDB(): Observable<boolean> {
    return of(true)
  }

  public populateDBSF(): Observable<boolean> {
    return of(true)
  }

  public deleteDB(): Observable<boolean> {
    return of(true)
  }

  public getStatuses(): Observable<boolean> {
    return of(true)
  }
}

export class MockTrainerService extends TrainerService {
  public updateTrainer(trainer: Trainer): any {
      return false
  }
}

export class MockServiceUtils {
  static createTestAssociate(suffix: number) {
    let mockBatch:Batch = new Batch();
    mockBatch.id = 100 + suffix;
    mockBatch.batchName = 'mockBatchName' + suffix;
    
    let batches:Batch[] = [mockBatch];
    
    let client:Client = new Client(0,'client' + suffix,null,null,null);
    let endClient:EndClient = new EndClient();
    endClient.name = 'none';
    
    const user:User = new User('newUser' + suffix ,'pass', 0, 0);
    const marketingStatus:MarketingStatus = new MarketingStatus(1, 'status');
    
    const associate:Associate = new Associate('first' + suffix, 'last' + suffix, user);
    
    associate.marketingStatus = marketingStatus;
    associate.batch = mockBatch;
    associate.client = client;
    associate.endClient = endClient;
    
    return associate;
  }

  static createMockGraphCounts(): Observable<GraphCounts[]> {
    const client1: GraphCounts = new GraphCounts(1, "Client1", 50);
    const client2: GraphCounts = new GraphCounts(2, "Client2", 30);
    const client3: GraphCounts = new GraphCounts(3, "Client3", 40);

    return of([client1, client2, client3])
  }
}