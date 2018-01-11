import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class RequestService {

  constructor(private http: HttpClient) { }

  public populateDB(): Observable<any> {
    return this.http.get('http://localhost:8080/TrackForce/track/database/populateDB');
  }

  public populateDBSF(): Observable<any> {
    return this.http.get('http://localhost:8080/TrackForce/track/database/populateDBSF');
  }

  public initForce(): Observable<any> {
    return this.http.post('http://localhost:8080/TrackForce/track/init/Force', {});
  }

  public deleteDB(): Observable<any> {
    return this.http.delete('http://localhost:8080/TrackForce/track/database/deleteDB');
  }
}
