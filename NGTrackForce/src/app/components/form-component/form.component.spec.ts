import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ChartsModule } from 'ng2-charts';
import { User } from '../../models/user.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { RouterTestingModule } from '@angular/router/testing';
import { BatchService } from '../../services/batch-service/batch.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormComponent } from '../form-component/form.component';
import { NgModel } from '@angular/forms';
import { AssociateService } from '../../services/associate-service/associate.service';
import { ClientService } from '../../services/client-service/client.service';
import { InterviewService } from '../../services/interview-service/interview.service';

describe('FormComponent', () => {
  let component: FormComponent;
  let fixture: ComponentFixture<FormComponent>;
  const testAuthService: AuthenticationService = new AuthenticationService(null, null, null);

  //setup service mocks
  beforeAll(()=>{
    let user = new User('mockUser', 'mockPassword', 1, 0, 0, 'mockTokent');

    spyOn(testAuthService, 'getUser').and.returnValue(user); // needed by the navbar
  });

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ 
        FormComponent,
        NgModel
      ],
      providers: [
        AssociateService,
        ClientService,
        InterviewService,
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

    fixture = TestBed.createComponent(FormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', (done) => {
    expect(component).toBeDefined();
  });
});
