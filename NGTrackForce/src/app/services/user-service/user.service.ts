/**
 * @author Andrew Crenwelge
 */
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { RequestService } from '../request-service/request.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable()
export class UserService {
  private userPath = "TrackForce/users";

    /**
    * @constructor
    * @param {RequestService}
    * Service for handling all requests to the server
    */
    constructor(private rs: RequestService, private http: HttpClient) {  }

    /**
     * Creates new user in database
     * @param {string} username - New user's Username
     *
     * @param {string} password - New user's Password
     *
     * @param {number} roleId - New user's Role ID
     *      1 Admin ----------- user has full privileges including Creating New Users
     *      2 Manager --------- user has full privileges excluding Creating New Users
     *      3 Vice President -- pending implementation
     *      4 Associate ------- pending implementation
     */

    public createUser(username: string, password: string, roleId: number): Observable<any> {
      return this.http.post<any>(environment.url + this.userPath, {username: username, password: password, role: roleId});
    }

    public getUser(): Observable<any> {
      return this.http.get<any>(environment.url + this.userPath);
    }

    public getUsername(): Observable<any> {
      return this.http.get<any>(environment.url + this.userPath + '/name');
    }

}
