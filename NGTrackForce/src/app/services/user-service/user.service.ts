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

     *      1	- Admin - Can do anything and everything
     *      2 - Trainer -	Can view everything, but not edit, approve associate registration
     *      3	- Sales/Delivery - Can view and edit everything, comment on interviews, and add feedback
     *      4	- Staging Manager - Can view and edit everything, comment on interviews, and add feedback
     *      5	- Associate	- Can register, view and edit their info, add and flag interviews

     */

    public createUser(username: string, password: string, roleId: number): Observable<any> {
      return this.http.post<any>(environment.url + this.userPath, {username: username, password: password, role: roleId});
    }
  	public createAssociate(username: string, password: string,fname: string, lname: string): Observable<any> {
      return this.http.post<any>(environment.url + this.userPath, {username: username, password: password,fname: fname, lname: lname});
    }
    public getUser(): Observable<any> {
      return this.http.get<any>(environment.url + this.userPath);
    }

    public getUsername(): Observable<any> {
      return this.http.get<any>(environment.url + this.userPath + '/name');
    }

}
