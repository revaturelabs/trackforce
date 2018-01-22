import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment';
import {User} from '../../models/user.model';

/**
  *Service for handling data from the back-end
  *i.e. login data, client data, and etc
  */
@Injectable()
export class RequestService {

  host: string = environment.url;
  trackPath: string = this.host + 'TrackForce/track';
  dataPath: string = this.host + 'TrackForce/track/data/get';

  /**
    *@param {HttpClient} http
    *Need to create a connection to REST endpoint
    *And initiate Http requests
    */
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

  /**
    * Function for submitting login data to the back-end
    *@param {String} username
    *@param {String} password
    *
    *@return User data from back-end if credentials are correct
    * user data contains JWT token, username, and role
    * If credentials are wrong, 400 is returned
    */
  public login(username: string, password: string): Observable<User> {
    return this.http.post<User>(this.host + 'TrackForce/track/user/submit', { username: username, password: password });
  }

  public getUsername(): Observable<any> {
    return this.http.get<any>(this.host + 'TrackForce/track/user/name');
  }

  public updateAssociates(): Observable<any>{
    return this.http.post<any>(this.trackPath + '/data/update/associate', {});
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

  /**
    *For the create user feature of the admin
    *
    *@param {String} username
    *@param {String} password
    *@param {number} roleId
    *symbolizes the role of the user
    * 1 ->  admin
    * 2 -> manager
    * 3 -> vp
    * 4 -> associate
    */
  public createUser(username: string, password: string, roleId: number): Observable<any> {
    return this.http.post<any>(this.host + 'TrackForce/track/create/user', {username: username, password: password, role: roleId})
  }

    /** get first match of Client Object
      *@param {number} clientId
      *uses the id parameter in the router to get a specific client
      */
    getOneClient(clientId: number): Observable<any> {
      return this.http.get(this.trackPath + '/clients/' + clientId);
    }

}
