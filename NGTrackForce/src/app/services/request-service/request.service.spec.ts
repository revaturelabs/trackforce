import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RequestService } from '../../services/request-service/request.service';

describe('RequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RequestService],
      imports: [ HttpClientTestingModule ]
    });
  });

  it('should be created', inject([RequestService], (service: RequestService) => {
    expect(service).toBeTruthy();
  }));
});
