import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BatchDetailsComponent } from './batch-details.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ChartsModule } from 'ng2-charts';
import { User } from '../../models/user.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { RouterTestingModule } from '@angular/router/testing';
import { FormComponent } from '../form-component/form.component';
import { SkillsetComponent } from '../skillset/skillset.component';
import { Batch } from '../../models/batch.model';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { RequestService } from '../../services/request-service/request.service';
import { User } from '../../models/user.model';

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MockActivatedRoute } from '../associate-view/associate-view.component.spec';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

describe('BatchDetailsComponent', () => {
  let component: BatchDetailsComponent;
  let fixture: ComponentFixture<BatchDetailsComponent>;
  const testAuthService: AuthenticationService = new AuthenticationService(null, null, null);

  let router = {
    navigate: jasmine.createSpy('navigate')
  }

  let routes = [
    {
      path: '',
      component: BatchDetailsComponent
    },
    {
      path: '/form-comp',
      component: FormComponent
    }
  ];

  // setup service mocks
  beforeAll(() => {
    const batch1: Batch = new Batch();
    // crurriculumName needs to be of type Curriculum
    // batch1.curriculumName = 'Test-Curriculum-1';
    const batch2: Batch = new Batch();
    // batch2.curriculumName = 'Test-Curriculum-2';
    // mock batch service
    // spyOn(testBatchService, 'getDefaultBatches').and.returnValue(Observable.of([batch1]));
    spyOn(testBatchService, 'getBatchesByDate').and.returnValue(Observable.of([batch1, batch2]));

    spyOn(testAuthService, 'getUser').and.returnValue(user); // needed by the navbar
  });

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ 
        Ng2OrderPipe,
        BatchDetailsComponent
      ],
      providers: [
        BatchService,
        {provide: AuthenticationService, userValue: testAuthService}
      ],
      imports: [
        // RouterTestingModule,
        { provide: Router, useValue: router},
        FormsModule,
        BrowserModule,
        HttpClientModule,
        ChartsModule,
        // { provide: ActivatedRoute, useValue: MockActivatedRoute.createMockRoute(1) }
        { provide: ActivatedRoute, useValue : {
          snapshot: {params: {id: 0},
        }
        }}
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    let mockUser:User = new User('mockUser', 'pass', 0, 0);

    fixture = TestBed.createComponent(BatchDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeDefined();
  });

  it('should contain associates if loaded', () => {
    if (component.isDataReady && !component.isDataEmpty) {
      expect(component.associates).toBeTruthy();
    }
  });

    it('should contain associates if loaded', () => {
      if (component.isDataReady && !component.isDataEmpty) {
        expect(component.associates).toBeTruthy();
      }
    });

    it('goToFormComponent() should navigate to the formcomponent', () => {
      expect(component.goToFormComponent(1)).toBeTruthy();
    });

    it('getMapStatusBatch() should fetch data and data should be ready.', () => {
      component.getMapStatusBatch()
      expect(component.isDataEmpty).toBeFalsy;
      expect(component.isDataReady).toBeTruthy;
    });
  });
});
