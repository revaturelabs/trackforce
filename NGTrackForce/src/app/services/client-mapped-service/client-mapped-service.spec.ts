import { TestBed, inject } from '@angular/core/testing';

import { ClientMappedService } from './client-mapped-service.service';

describe('ClientMappedServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ClientMappedService]
    });
  });

  it('should be created', inject([ClientMappedService], (service: ClientMappedService) => {
    expect(service).toBeTruthy();
  }));
});
