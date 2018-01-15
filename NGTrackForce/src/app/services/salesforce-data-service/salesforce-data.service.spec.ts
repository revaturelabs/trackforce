import { TestBed, inject } from '@angular/core/testing';

import { SalesforceDataService } from './salesforce-data.service';
import {AuthenticationService} from '../authentication-service/authentication.service';
import {RequestService} from '../request-service/request.service';
import {HttpClientModule} from '@angular/common/http';

describe('SalesforceDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ],
      providers: [
        SalesforceDataService,
        AuthenticationService,
        RequestService,
      ]
    });
  });

  it('should be created', inject([SalesforceDataService], (service: SalesforceDataService) => {
    expect(service).toBeTruthy();
  }));
});
