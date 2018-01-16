/**
 * @author Michael Tseng
 * @description Service for authenicating users
 */
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { RequestService } from '../request-service/request.service';
import {User} from '../../models/user.model';
import { Router } from '@angular/router';

const USER_KEY = 'currentUser';

@Injectable()
export class AuthenticationService {

  /**
  * @constructor
  *
  *@param {RequestService}
  * Service for handling all requests to the server
  *
  */
  constructor(private rs: RequestService, private router: Router) { }

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
          localStorage.setItem(USER_KEY, JSON.stringify(user));
        }
        return user;
      });
  }

  /**
  *Removes user from localStorage
  *And navigates back to login
  *
  *@param none
  */
  logout(){
    localStorage.removeItem(USER_KEY);
    this.router.navigate(['login']);
  }

  /**
   * Check for an active session
   */
  getUser(): User {
    const user: User = JSON.parse(localStorage.getItem(USER_KEY));
    return user;
  }

}
