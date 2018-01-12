import { TestBed, inject } from '@angular/core/testing';
import {BatchService} from './batch.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('BatchService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [BatchService]
    });
  });

  it('should be created', inject([BatchService], (service: BatchService) => {
    expect(service).toBeTruthy();
  }));
});
