import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  //login service that stores a user object on local storage
  //it will only store a user if the object itself is valid and the token is valid
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

  //removes user from localStorage
  logout(){
    localStorage.removeItem('currentUser');
  }

}
