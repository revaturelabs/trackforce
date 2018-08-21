import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BatchDetailsComponent } from './batch-details.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ChartsModule } from 'ng2-charts';
import { User } from '../../models/user.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { RouterTestingModule } from '@angular/router/testing';
import { Associate } from '../../models/associate.model';
import { BatchService } from '../../services/batch-service/batch.service';
import { Ng2OrderPipe } from 'ng2-order-pipe';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('BatchDetailsComponent', () => {
  let component: BatchDetailsComponent;
  let fixture: ComponentFixture<BatchDetailsComponent>;
  const testAuthService: AuthenticationService = new AuthenticationService(null, null, null);

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
    component.associates = [new Associate('first', 'last', mockUser), new Associate('first', 'last', mockUser), new Associate('first', 'last', mockUser)];
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

  it('should contain chartType = bar', ()=>{
    expect(component.chartType).toEqual('bar');
  });
});
