/**
*@author Michael Tseng
*
*@description Intercepts all HttpRequest and appends the token into the header
* for verification
*
*/
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  /**
  *
  *@param {HttpRequest<any>} request
  *the HttpRequest intercepted
  *
  *@param {HttpHandler} next
  *the HttpHandler object that returns the desired HttpEvent observable
  *
  *@return the transformed HttpEvent observable
  *
  */
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if(currentUser && currentUser.token){
      request = request.clone({
        setHeaders: {
            Authorization: `${currentUser.token}`
        }
      });
    }
    return next.handle(request);
  }
}
