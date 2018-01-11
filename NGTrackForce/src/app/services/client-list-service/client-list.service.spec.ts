import { TestBed, inject } from '@angular/core/testing';

import { ClientListService } from './client-list.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ClientListService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [ClientListService]
    });
  });

  it('should be created', inject([ClientListService], (service: ClientListService) => {
    expect(service).toBeTruthy();
  }));
});
