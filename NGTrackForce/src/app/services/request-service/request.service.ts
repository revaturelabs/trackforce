import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment';
import {User} from '../../models/user.model';

@Injectable()
export class RequestService {

  host: string = environment.url;
  trackPath: string = this.host + '/TrackForce/track';
  dataPath: string = this.host + 'TrackForce/track/data/get';

  constructor(private http: HttpClient) { }

  public populateDB(): Observable<any> {
    return this.http.get(this.host + 'TrackForce/track/database/populateDB');
  }

  public populateDBSF(): Observable<any> {
    return this.http.get(this.host + 'TrackForce/track/database/populateDBSF');
  }

  public deleteDB(): Observable<any> {
    return this.http.delete(this.host + 'TrackForce/track/database/deleteFromDB');
  }

  public login(username: string, password: string): Observable<User> {
    return this.http.post<User>(this.host + 'TrackForce/track/user/submit', { username: username, password: password });
  }

  public getUsername(): Observable<any> {
    return this.http.get<any>(this.host + 'TrackForce/track/user/name');
  }

  public getAssociates(): Observable<any> {
    return this.http.get(this.dataPath + '/associate');
  }

  public getBatchesSortedById(): Observable<any> {
    return this.http.get(this.dataPath + '/batch');
  }

  public getBatchesSortedByDate(): Observable<any> {
    return this.http.get(this.dataPath + '/batch/date');
  }

  public getClients(): Observable<any> {
    return this.http.get(this.dataPath + '/client');
  }

  public getTotals(): Observable<any> {
    return this.http.get(this.dataPath + '/summary');
  }

  public getSkills(): Observable<any> {
    return this.http.get(this.dataPath + '/skills');    
  }

  public getStatuses(): Observable<any> {
    return this.http.get(this.dataPath + '/marketing');
  }

  public getBatches(threeMonthsBefore: number, threeMonthsAfter: number): Observable<any> {
    return this.http.get<any>(this.host + 'TrackForce/track/batches/' + threeMonthsBefore + '/' + threeMonthsAfter);
  }

  public getBatchPerType(threeMonthsBefore: number, threeMonthsAfter: number): Observable<any> {
    return this.http.get<any>(this.host + 'TrackForce/track/batches/' + threeMonthsBefore + '/' + threeMonthsAfter + '/type');
  }

  public createUser(username: string, password: string, roleId: number): Observable<any> {
    return this.http.post<any>(this.host + 'TrackForce/track/create/user', {username: username, password: password, role: roleId})
  }


}
