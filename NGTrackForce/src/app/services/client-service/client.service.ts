import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment'

/**
 * @author Han Jung
 * @description methods for grabbing data from api for client list
 */

@Injectable()
export class ClientService {
  private url: string = environment.url + 'TrackForce/';

  constructor(private http: HttpClient) { }

  // get all Client objects
  getAllClients(): Observable<any> {
    return this.http.get(this.url + 'clients'); // previously /info
  }

  /** Client Object by id
    *@param {number} clientId
    */
  getOneClient(clientId: number): Observable<any> {
    return this.http.get(this.url + 'clients/' + clientId);
  }

}
