import { async } from '@angular/core/testing';
import { MarketingStatus } from './../../models/marketing-status.model';
import { Observable } from 'rxjs';
import { MockAuthenticationService, MockAssociateService } from './../associate-view/associate-view.component.spec';

import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BatchListComponent} from './batch-list.component';
import {ChartsModule} from 'ng2-charts';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {BatchService} from '../../services/batch-service/batch.service';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {HomeComponent} from '../home/home.component';
import {ClientMappedComponent} from '../client-mapped/client-mapped.component';
import {LoginComponent} from '../login/login.component';
import {ClientListComponent} from '../client-list/client-list.component';
import {CreateUserComponent} from '../create-user/create-user.component';
import {SearchFilterPipe} from '../../pipes/search-filter/search-filter.pipe';
//import {AssociateSearchByTextFilter} from '../../pipes/associate-search-by-text-filter/associate-search-by-text-filter.pipes';
//import { AssociateSearchByClientPipe } from '../../pipes/associate-search-by-client-pipe/client-pipe.pipe';
//import { AssociateSearchByStatusPipe } from '../../pipes/associate-search-by-status-pipe/status-pipe.pipe';
import {NavbarComponent} from '../navbar/navbar.component';
import {RouterTestingModule} from '@angular/router/testing';
import {FormComponent} from '../form-component/form.component';
import {SkillsetComponent} from '../skillset/skillset.component';
import {Batch} from '../../models/batch.model';
import {BatchLocation} from '../../models/batch-location.model';
import {Curriculum} from '../../models/curriculum.model';
import {RequestService} from '../../services/request-service/request.service';
import {User} from '../../models/user.model';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import { Associate } from '../../models/associate.model';
import { Trainer } from '../../models/trainer.model';
/* 
*  [ of ] Added on 11/10/2018. As of RXJS 6.3, Observable.of is depreciated
*  Updated with the latest intended function. Additionally, swapped out
*  uses of Observable.of with of. This was done to fix the spec file,
*  which was not a working test suite prior.
*/

/*
import { getTestBed } from '@angular/core/testing';
import { BrowserDynamicTestingModule,
         platformBrowserDynamicTesting } from '@angular/platform-browser-dynamic/testing';

declare const require : any;

getTestBed().initTestEnvironment(
  BrowserDynamicTestingModule,
  platformBrowserDynamicTesting()
);

const context = require.context('./', true, /batch-list\.spec\.ts$/);
context.keys().map(context);
*/
export class MockBatchService extends BatchService {
  batches: Batch[];

  public getBatchesByDate(startDate: Date, endDate: Date): Observable<Batch[]> {
    const mockUser: User = new User('mockUser', 'password', 0, 0);

    const mockCurriculum: Curriculum = new Curriculum();
    mockCurriculum.id = 2;
    mockCurriculum.name = 'mockCurriculum';
    const mockStartDate = 4;
    const mockEndDate = 5;

    const mockLocation: BatchLocation = new BatchLocation(700,'Massachusetts');

    const trainerUser: User = new User('mockTrainer', 'password', 0, 0);

    const mockAssociate: Associate = new Associate('FirstName', 'LastName', mockUser, 101010, null, null, null, null, null, null, null);

    const mockAssociates: Associate[] = [mockAssociate];

    const batch1: Batch = new Batch();

    const mockTrainer: Trainer = new Trainer('Trainer', 'T.', trainerUser);

    batch1.id = 1;
    batch1.batchName = 'mockBatch';
    batch1.curriculumName = mockCurriculum;
    batch1.location = mockLocation;
    batch1.startDate = 0;
    batch1.endDate = 1;
    batch1.associates = mockAssociates;
    batch1.trainer = mockTrainer;

    const batches: Batch[] = [batch1];
    return Observable.of(batches);
  }

  public getBatchDetailsById(batchId:number):Observable<Batch>{

    const mockUser: User = new User('mockUser', 'password', 0, 0);

    const mockAssociate: Associate = new Associate('FirstName', 'LastName', mockUser, 101010, null, null, null, null, null, null, null);

    const mockAssociates: Associate[] = [mockAssociate];

    const mockCurriculum: Curriculum = new Curriculum();
    mockCurriculum.id = 2;
    mockCurriculum.name = 'mockCurriculum';

    const mockLocation: BatchLocation = new BatchLocation(1, 'location');

    const mockTrainer = new Trainer('ryan', 'something', mockUser, 2);

    const mockBatch = new Batch();
    mockBatch.id = 1;
    mockBatch.batchName = 'name';
    mockBatch.curriculumName = mockCurriculum;
    mockBatch.location = mockLocation;
    mockBatch.startDate = 4;
    mockBatch.endDate = 5;
    mockBatch.associates = mockAssociates;
    mockBatch.trainer = mockTrainer;

    return Observable.of(mockBatch);
  }

  public getAssociatesForBatch(batchId:number):Observable<Associate[]>{

    const mockUser: User = new User('mockUser', 'password', 0, 0);
    const mockMarketingStatus = new MarketingStatus(1, 'status');
    const mockAssociate: Associate = new Associate('FirstName', 'LastName', mockUser, 101010, null, mockMarketingStatus, null, null, null, null, null);
    const mockAssociates: Associate[] = [mockAssociate];

    return Observable.of(mockAssociates);
  }

  public getBatchesWithinDates(mockEndDate, mockStartDate):Observable<Batch[]>{

    const mockUser: User = new User('mockUser', 'password', 0, 0);

    const mockAssociate: Associate = new Associate('FirstName', 'LastName', mockUser, 101010, null, null, null, null, null, null, null);

    const mockAssociates: Associate[] = [mockAssociate];

    const mockCurriculum: Curriculum = new Curriculum();
    mockCurriculum.id = 2;
    mockCurriculum.name = 'mockCurriculum';

    const mockLocation: BatchLocation = new BatchLocation(1, 'location');

    const mockTrainer = new Trainer('ryan', 'something', mockUser, 2);

    const mockBatch = new Batch();
    mockBatch.id = 1;
    mockBatch.batchName = 'name';
    mockBatch.curriculumName = mockCurriculum;
    mockBatch.location = mockLocation;
    mockBatch.startDate = 4;
    mockBatch.endDate = 5;
    mockBatch.associates = mockAssociates;
    mockBatch.trainer = mockTrainer;

    const batches:Batch[] = [mockBatch];

    return Observable.of(batches);

  }
}

describe('BatchListComponent', () => {
  let component: BatchListComponent;
  let fixture: ComponentFixture<BatchListComponent>;
  const testBatchService = new MockBatchService(null);
  const testAuthService: AuthenticationService = new MockAuthenticationService(null, null, null);

  // setup service mocks
  beforeAll(() => {
    const mockCurriculum: Curriculum = new Curriculum();
    mockCurriculum.id = 2;
    mockCurriculum.name = 'mockCurriculum';

    const mockLocation: BatchLocation = new BatchLocation(700,'Massachusetts');

    const mockUser: User = new User('mockUser', 'password', 0, 0);
    const trainerUser: User = new User('mockTrainer', 'password', 0, 0);

    const mockAssociate: Associate = new Associate('FirstName', 'LastName', mockUser, 101010, null, null, null, null, null, null, null);

    const mockAssociates: Associate[] = [mockAssociate];

    const batch1: Batch = new Batch();

    const mockTrainer: Trainer = new Trainer('Trainer', 'T.', trainerUser);

    batch1.id = 1;
    batch1.batchName = 'mockBatch';
    batch1.curriculumName = mockCurriculum;
    batch1.location = mockLocation;
    batch1.startDate = 0;
    batch1.endDate = 1;
    batch1.associates = mockAssociates;
    batch1.trainer = mockTrainer;

    this.batches = [batch1];

    spyOn(testBatchService, 'getAllBatches').and.returnValue(Observable.of(this.batches));

    // crurriculumName needs to be of type Curriculum
    // batch1.curriculumName = "Test-Curriculum-1";
    const batch2: Batch = new Batch();
    // batch2.curriculumName = "Test-Curriculum-2";
    // mock batch service
    // spyOn(testBatchService, 'getDefaultBatches').and.returnValue(of([batch1]));
    spyOn(testBatchService, 'getBatchesByDate').and.returnValue(Observable.of([batch1, batch2]));
    spyOn(testBatchService, 'getBatchesWithinDates').and.returnValue(Observable.of([batch1, batch2]));


    let user = new User("mockUser", "mockPassword", 1, 0, null, "mockToken");
    spyOn(testAuthService, 'getUser').and.returnValue(user);  // needed by navbar
  });

  beforeEach(async (() => {
    TestBed.configureTestingModule({
      declarations: [
        BatchListComponent,
        HomeComponent,
        ClientMappedComponent,
        ClientListComponent,
        LoginComponent,
        CreateUserComponent,
        SearchFilterPipe,
        NavbarComponent,
        FormComponent,
        SkillsetComponent
      ],
      providers: [
        RequestService,
        {provide: AuthenticationService, useValue: testAuthService},
        {provide: BatchService, useValue: testBatchService},
      ],
      imports: [
        RouterTestingModule,
        FormsModule,
        BrowserModule,
        HttpClientModule,
        ChartsModule
      ],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ]
    }).compileComponents();
  }));

  beforeEach(() =>{
    fixture = TestBed.createComponent(BatchListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should pull some batch data on init', () => {
      expect(component.batches.length).toBeGreaterThan(0);
  });

});
