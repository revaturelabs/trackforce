import { TestBed, inject } from '@angular/core/testing';

import { DataSyncService } from './data-sync.service';

describe('DataSyncService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DataSyncService]
    });
  });

  it('should be created', inject([DataSyncService], (service: DataSyncService) => {
    expect(service).toBeTruthy();
  }));
});
