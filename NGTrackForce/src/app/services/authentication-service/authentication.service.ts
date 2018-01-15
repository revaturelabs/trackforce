/**
 * @author Michael Tseng
 * @description Service
 */
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { RequestService } from '../request-service/request.service';

@Injectable()
export class AuthenticationService {

  /**
  * @constructor
  *
  *@param {RequestService}
  * Service for handling all requests to the server
  *
  */
  constructor(private rs: RequestService) { }

  /**
  *Login service that stores a user object on local storage
  *It will only store a user if the object itself is valid and the token is valid
  *
  *@param {string} username
  * The username to be checked against the database
  *
  *@param {string} password
  *The password need to be sent to the database for checking
  *
  *@return
  *The user object that contains the JWT, username, and role id
  */
  login(username: string, password: string){
    return this.rs.login(username, password).map(
      user => {
        if(user){
          localStorage.setItem('currentUser', JSON.stringify(user));
        }
        return user;
      });
  }

  /**
  *Removes user from localStorage
  *
  *@param none
  */
  logout(){
    localStorage.removeItem('currentUser');
  }

  /**
   * Check for an active session
   */
  getUser(): any{
    return localStorage.getItem('currentUser');
  }

}
