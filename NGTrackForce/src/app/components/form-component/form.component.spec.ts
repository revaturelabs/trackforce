import { RouterModule } from '@angular/router';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ChartsModule } from 'ng2-charts';
import { User } from '../../models/user.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { BatchService } from '../../services/batch-service/batch.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormComponent } from '../form-component/form.component';
import { NgModel, FormsModule } from '@angular/forms';
import { AssociateService } from '../../services/associate-service/associate.service';
import { ClientService } from '../../services/client-service/client.service';
import { InterviewService } from '../../services/interview-service/interview.service';
import { RequestService } from '../../services/request-service/request.service';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { of } from 'rxjs/observable/of';
import { LocationStrategy } from '@angular/common';

export class MockAuthenticationService extends AuthenticationService {
  getUser(): User {
    const user: User = new User('mockUsername', 'mockPassword',0,0);
    return user;
  }
}

export class MockActivatedRoute {
  static createMockRoute(tid: number): any {
    return {
      params: { subscribe(val: string) { of({id: tid}) }},
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

export class MockRouter {
  navigate(val: any) {
    return true;
  }
}


fdescribe('FormComponent', () => {
  let component: FormComponent;
  let fixture: ComponentFixture<FormComponent>;
  const httpClient: HttpClient = new HttpClient(null);
  const requestService : RequestService = new RequestService(httpClient);
  const testAuthService: AuthenticationService = new MockAuthenticationService(null, null, null);
  const router = jasmine.createSpyObj('Router', ['navigate']);

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
        LocationStrategy,
        {provide: AuthenticationService, useValue: testAuthService},
        {provide: ActivatedRoute, useValue: MockActivatedRoute.createMockRoute(0)},
        {provide: HttpClient, useClass: HttpClient},
        {provide: Router, useClass: MockRouter}
      ],
      imports: [
        ChartsModule,
        HttpClientTestingModule,
        RouterModule
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
