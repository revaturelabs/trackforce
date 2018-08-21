import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BatchDetailsComponent } from './batch-details.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ChartsModule } from 'ng2-charts';
import { User } from '../../models/user.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { FormComponent } from '../form-component/form.component';
import { SkillsetComponent } from '../skillset/skillset.component';
import { Batch } from '../../models/batch.model';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { RequestService } from '../../services/request-service/request.service';
import { MockActivatedRoute } from '../associate-view/associate-view.component.spec';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { BatchService } from '../../services/batch-service/batch.service';
import { Ng2OrderPipe } from 'ng2-order-pipe';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('BatchDetailsComponent', () => {
  let component: BatchDetailsComponent;
  let fixture: ComponentFixture<BatchDetailsComponent>;
  const testAuthService: AuthenticationService = new AuthenticationService(null, null, null);
  const testBatchService: BatchService = new BatchService(null);
  let user = new User('mockUser', 'mockPassword', 1, 0, 0, 'mockTokent');

  //setup service mocks
  beforeAll(()=>{
    let user = new User('mockUser', 'mockPassword', 1, 0, 0, 'mockTokent');

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
        ChartsModule,
        RouterTestingModule,
        HttpClientTestingModule
      ],
      schemas:[ 
        CUSTOM_ELEMENTS_SCHEMA 
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
    
  it('should contain chartType = bar', ()=>{
    expect(component.chartType).toEqual('bar');
  });
});
