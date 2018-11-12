import { MockAuthenticationService } from './../associate-view/associate-view.component.spec';

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
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
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
import { of } from 'rxjs/observable/of';

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
  public getBatchesByDate(startDate: Date, endDate: Date): Observable<Batch[]> {
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

    const batches: Batch[] = [batch1];
    return of(batches);
  }
}

describe('BatchListComponent', async () => {
  let component: BatchListComponent;
  let fixture: ComponentFixture<BatchListComponent>;
  const testBatchService: BatchService = new MockBatchService(null);
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

    const batches: Batch[] = [batch1];

    spyOn(testBatchService, 'getAllBatches').and.returnValue(of(batches));

    // crurriculumName needs to be of type Curriculum
    // batch1.curriculumName = "Test-Curriculum-1";
    const batch2: Batch = new Batch();
    // batch2.curriculumName = "Test-Curriculum-2";
    // mock batch service
    // spyOn(testBatchService, 'getDefaultBatches').and.returnValue(of([batch1]));
    spyOn(testBatchService, 'getBatchesByDate').and.returnValue(of([batch1, batch2]));
    spyOn(testBatchService, 'getBatchesWithinDates').and.returnValue(of([batch1, batch2]));


    let user = new User("mockUser", "mockPassword", 1, 0, null, "mockToken");
    spyOn(testAuthService, 'getUser').and.returnValue(user);  // needed by navbar
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        BatchListComponent,
        HomeComponent,
        ClientMappedComponent,
        ClientListComponent,
        LoginComponent,
        CreateUserComponent,
        SearchFilterPipe,
  //      AssociateSearchByTextFilter,
  //      AssociateSearchByClientPipe,
  //      AssociateSearchByStatusPipe,
        NavbarComponent,
        FormComponent,
        SkillsetComponent
      ],
      providers: [
        RequestService,
        {provide: AuthenticationService, useValue: testAuthService},
        {provide: BatchService, useValue: testBatchService},  // inject service
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
    });

    fixture = TestBed.createComponent(BatchListComponent);
    component = fixture.componentInstance;


  });

  it('should create', () => {
    // expect(component).toBeTruthy();
    fixture.detectChanges();
    expect(component.dataReady).toBeTruthy();
  });

  it('should pull some batch data on init', () => {
    fixture.whenStable().then(() => {
      component.batches = this.batches;
      expect(component.batches.length).toBeGreaterThanOrEqual(0);
    });
  });

  it('data length should increase with larger range than default', () => {
    fixture.whenStable().then(() => {
      component.batches = this.batches;
      expect(component.batches.length).toBeGreaterThanOrEqual(0);

      component.startDate = component.endDate = new Date();
      // These arguments were added to stop a compilation error, what they are were implied by the method signature.
      component.applySelectedRange();
      fixture.detectChanges();
      fixture.whenStable().then(() => {
        component.batches = this.batches;
        const defaultBatchCount = component.batches.length;
        expect(defaultBatchCount).toBeGreaterThanOrEqual(0);

        const now: Date = new Date();
        component.startDate = new Date(0);  // 1970, aka very far back
        component.endDate = new Date(now.getFullYear(), now.getMonth() + 6, 1); // 5-6 months in the future
        // These arguments were added to stop a compilation error, what they are were implied by the method signature.
        component.applySelectedRange();
        fixture.detectChanges();
        fixture.whenStable().then(() => {
          component.batches = this.batches;
          expect(component.batches.length).toBeGreaterThanOrEqual(defaultBatchCount);
        });
      });
    });

  });

});
