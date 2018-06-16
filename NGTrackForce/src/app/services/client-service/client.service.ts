import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment'
import { Client } from '../../models/client.model';

/**
 * @author Han Jung
 * @description methods for grabbing client data
 */

@Injectable()
export class ClientService {
  private baseURL: string = environment.url + 'TrackForce/clients';

  constructor(private http: HttpClient) { }

  /**
   * 
   * Get a list of all of the clients
   */
  getAllClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.baseURL); 
  }

  /** 
   * 
   * Client Object by id
   * @param {number} clientId
   */
  getOneClient(clientId: number): Observable<Client> {
    return this.http.get<Client>(this.baseURL + "/" + clientId);
  }

}
