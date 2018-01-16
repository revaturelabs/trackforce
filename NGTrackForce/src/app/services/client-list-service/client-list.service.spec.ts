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

  it('should grab values for all clients', inject([ClientListService], (service: ClientListService) => {
    expect(service.getAllClients()).toBeTruthy();
  }));
  it('should grab values for all client\'s name', inject([ClientListService], (service: ClientListService) => {
    expect(service.getAllClientsNames()).toBeTruthy();
  }));
  it('should grab value by client ID', inject([ClientListService], (service: ClientListService) => {
    expect(service.getOneClient(1)).toBeTruthy();
    expect(service.getOneClient(2)).toBeTruthy();
    expect(service.getOneClient(3)).toBeTruthy();
  }));
});
