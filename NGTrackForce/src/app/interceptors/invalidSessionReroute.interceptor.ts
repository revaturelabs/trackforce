/**
* @author Austin Molina
*
* @description Intercepts all HttpRequest and checks the response for an authorization error. 
* When a 401 is found, redirect to login
*
*/
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap} from 'rxjs/operators';
import { Router } from '@angular/router';


@Injectable()
export class InvalidSessionRerouteInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler, ): Observable<HttpEvent<any>> {

    return next.handle(request).pipe(tap((event: HttpEvent<any>) => {
      //none
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401) {
          console.log("401 caught by session reroute")
          this.router.navigate(['/login'], { queryParams: { invalidsession: true }}); 
        }
      }
    }));
  }
}
