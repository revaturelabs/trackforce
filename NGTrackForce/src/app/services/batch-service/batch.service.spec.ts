import {TestBed, inject} from '@angular/core/testing';
import {BatchService} from './batch.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {Batch} from '../../models/batch.model';

describe('BatchService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        BatchService
      ]
    });
  });

  it('should be created', inject([BatchService], (service: BatchService) => {
    expect(service).toBeTruthy();
  }));


  it('should hit right endpoint', inject([BatchService], (service: BatchService) => {
    service.getBatchesByDate(new Date(), new Date()).subscribe(
      (batches: Batch[]) => {
        expect(batches).toBeTruthy();
        expect(batches.length).toEqual(0);
      }
    )
  }));
});
