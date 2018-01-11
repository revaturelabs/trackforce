import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { environment } from '../../../environments/environment';

/**
 * @author Michael Tseng
 * @description Receives user inputs from form and submits them to the back-end for validation
 */
@Injectable()
export class RequestService {

  host: string = environment.url;

  constructor(private http: HttpClient) { }

  public populateDB(): Observable<any> {
    return this.http.get(this.host + '/TrackForce/track/database/populateDB');
  }

  public populateDBSF(): Observable<any> {
    return this.http.get(this.host + '/TrackForce/track/database/populateDBSF');
  }

  public initForce(): Observable<any> {
    return this.http.post(this.host + '/TrackForce/track/init/Force', {});
  }

  public deleteDB(): Observable<any> {
    return this.http.delete(this.host + '/TrackForce/track/database/deleteDB');
  }

  public login(username: string , password: string): Observable<any> {
    return this.http.post<any>(this.host + 'TrackForce/track/user/submit', {username: username, password: password});
  }
}
