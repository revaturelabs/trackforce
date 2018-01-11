/**
 * @author Michael Tseng
 * @description Service
 */
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {

  /**
  * @constructor
  *
  *@param {HttpClient} http
  * the dependency need to make http requests to REST API
  *
  */
  constructor(private http: HttpClient) { }

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
  *@param {string} url
  *The login url endpoint to be hit
  *
  *@return
  *The user object that contains the JWT, username, and role id
  */
  login(username: string, password: string, url: string){
    return this.http.post<any>(url, {username: username, password: password})
    .map(
      user => {
        if(user && user.token){
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

}
