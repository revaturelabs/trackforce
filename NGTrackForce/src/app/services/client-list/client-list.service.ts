import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

/** 
 * @author Han Jung
 * @description service for client-list component
 */

@Injectable()
export class ClientListService {
  private url: string = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }


  getAllClientsNames(): Observable<any> {
    return this.http.get<any>(this.url + '/TrackForce/track/clients');
  }

  getAllClients(): Observable<any> {
    return this.http.get<any>(this.url + '/TrackForce/track/clients/info');
  }

  getOneClient(): Observable<any> {
    return this.http.get<any>(this.url + '/TrackForce/track/clients/');
  }

}
