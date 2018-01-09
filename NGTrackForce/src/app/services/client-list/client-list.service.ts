import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

/** 
 * @author Han Jung
 * @description service for client-list component
 */

@Injectable()
export class ClientListService {
  private url: string = 'http://ec2-54-209-164-28.compute-1.amazonaws.com:8080/TrackForce';
  constructor(private http: HttpClient) { }


  getAllClientsNames(): Observable<any> {
    return this.http.get<any>(this.url + '/track/clients');
  }

  getAllClients(): Observable<any> {
    return this.http.get<any>(this.url + '/track/clients/info');
  }

  getOneClient(): Observable<any> {
    return this.http.get<any>(this.url + '/track/clients/');
  }

}
