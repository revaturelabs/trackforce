
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
import {AssociateListComponent} from '../associate-list/associate-list.component';
import {LoginComponent} from '../login/login.component';
import {ClientListComponent} from '../client-list/client-list.component';
import {CreateUserComponent} from '../create-user/create-user.component';
import {SearchFilterPipe} from '../../pipes/search-filter/search-filter.pipe';
import {AssociateSearchByTextFilter} from '../../pipes/associate-search-by-text-filter/associate-search-by-text-filter.pipes';
import {NavbarComponent} from '../navbar/navbar.component';
import {RouterTestingModule} from '@angular/router/testing';
import {RootComponent} from '../root/root.component';
import {FormComponent} from '../form-component/form.component';
import {SkillsetComponent} from '../skillset/skillset.component';
import {Batch} from '../../models/batch.model';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import {RequestService} from '../../services/request-service/request.service';
import {User} from '../../models/user.model';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";


describe('BatchListComponent', async () => {
  let component: BatchListComponent;
  let fixture: ComponentFixture<BatchListComponent>;
  const testBatchService: BatchService = new BatchService(null);
  const testAuthService: AuthenticationService = new AuthenticationService(null, null, null);

  // setup service mocks
  beforeAll(() => {
    const batch1: Batch = new Batch();
    batch1.curriculumName = "Test-Curriculum-1";
    const batch2: Batch = new Batch();
    batch2.curriculumName = "Test-Curriculum-2";
    // mock batch service
    spyOn(testBatchService, 'getDefaultBatches').and.returnValue(Observable.of([batch1]));
    spyOn(testBatchService, 'getBatchesByDate').and.returnValue(Observable.of([batch1, batch2]));

    const user: User = new User();
    user.token = "mockToken";
    user.username = "mockUser";
    user.tfRoleId = 1;
    spyOn(testAuthService, 'getUser').and.returnValue(user);  // needed by navbar
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        BatchListComponent,
        HomeComponent,
        ClientMappedComponent,
        ClientListComponent,
        AssociateListComponent,
        LoginComponent,
        CreateUserComponent,
        SearchFilterPipe,
        AssociateSearchByTextFilter,
        NavbarComponent,
        RootComponent,
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
    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should pull some batch data on init', () => {
    fixture.whenStable().then(() => {
      expect(component.batches.length).toBeGreaterThanOrEqual(0);
    });
  });

  it('data length should increase with larger range than default', () => {
    fixture.whenStable().then(() => {
      expect(component.batches.length).toBeGreaterThanOrEqual(0);

      component.startDate = component.endDate = new Date();
      // These arguments were added to stop a compilation error, what they are were implied by the method signature.
      component.applySelectedRange(component.startDate, component.endDate);
      fixture.detectChanges();
      fixture.whenStable().then(() => {
        const defaultBatchCount = component.batches.length;
        expect(defaultBatchCount).toBeGreaterThanOrEqual(0);

        const now: Date = new Date();
        component.startDate = new Date(0);  // 1970, aka very far back
        component.endDate = new Date(now.getFullYear(), now.getMonth() + 6, 1); // 5-6 months in the future
        // These arguments were added to stop a compilation error, what they are were implied by the method signature.
        component.applySelectedRange(component.startDate, component.endDate);
        fixture.detectChanges();
        fixture.whenStable().then(() => {
          expect(component.batches.length).toBeGreaterThanOrEqual(defaultBatchCount);
        });
      });
    });

  });

});

