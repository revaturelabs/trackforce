import { TestBed, inject } from '@angular/core/testing';

import { SalesforceDataService } from './salesforce-data.service';

describe('SalesforceDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SalesforceDataService]
    });
  });

  it('should be created', inject([SalesforceDataService], (service: SalesforceDataService) => {
    expect(service).toBeTruthy();
  }));
});
