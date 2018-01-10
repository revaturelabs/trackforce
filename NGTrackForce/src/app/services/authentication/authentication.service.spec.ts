import { TestBed, inject } from '@angular/core/testing';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import 'rxjs/add/operator/map';

import { AuthenticationService } from './authentication.service';


describe('AuthenticationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthenticationService]
    });
  });

  it('should be created', inject([HttpTestingController, AuthenticationService], (httpMock: HttpTestingController, service: AuthenticationService) => {
    expect(service).toBeTruthy();
  }));
});
