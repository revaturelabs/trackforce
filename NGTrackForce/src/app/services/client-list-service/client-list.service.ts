import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment'

/**
 * @author Han Jung
 * @description methods for grabbing data from api for client list 
 */

@Injectable()
export class ClientListService {
  private url: string = environment.url + 'TrackForce/track';

  constructor(private http: HttpClient) { }

  // gets all clients name from the track force API
  getAllClientsNames(): Observable<any> {
    return this.http.get(this.url + '/clients');
  }

  // get all Client objects
  getAllClients(): Observable<any> {
    return this.http.get(this.url + '/info');
  }

  // get first match of Client Object
  getOneClient(clientId: number): Observable<any> {
    return this.http.get(this.url + '/clients/' + clientId);
  }

}
