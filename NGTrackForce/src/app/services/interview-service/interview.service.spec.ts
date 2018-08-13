import { TestBed, inject } from '@angular/core/testing';import { InterviewService } from './interview.service';
// added imports; DK
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('InterviewService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [InterviewService],
      imports: [ HttpClientTestingModule ]
    });
  });

  it('should be created', inject([InterviewService], (service: InterviewService) => {
    expect(service).toBeTruthy();
  }));
});
