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
  private mappedClientUrl = environment.url + 'TrackForce/clients/mapped/get/'
  private clientUrl = environment.url + 'TrackForce/clients/associates/get/'

  constructor(private http: HttpClient) { }

  /**
   * 
   * Get a list of all of the clients
   */
  getAllClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.baseURL);
  }

  //This method was meant to return all clients with mapped associates.
  //But is currently not used due to incorrect query in the back-end.
  getAllClientsWithAssoc(): Observable<Client[]>{
    return this.http.get<Client[]>(this.mappedClientUrl);
  }

  //This method returns mapped associate counts for a selected client
  getClientCount(clientId: number): Observable<number>{
    return this.http.get<number>(this.clientUrl + clientId);
  }

}
