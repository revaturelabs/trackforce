import { TestBed, inject } from '@angular/core/testing';

import { ClientService } from './client.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ClientService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [ClientService]
    });
  });

  it('should be created', inject([ClientService], (service: ClientService) => {
    expect(service).toBeTruthy();
  }));

  it('should grab values for all clients', inject([ClientService], (service: ClientService) => {
    expect(service.getAllClients()).toBeTruthy();
  }));
  it('should grab values for all client\'s name', inject([ClientService], (service: ClientService) => {
    expect(service.getAllClientsNames()).toBeTruthy();
  }));
  it('should grab value by client ID', inject([ClientService], (service: ClientService) => {
    expect(service.getOneClient(1)).toBeTruthy();
    expect(service.getOneClient(2)).toBeTruthy();
    expect(service.getOneClient(3)).toBeTruthy();
  }));
});
