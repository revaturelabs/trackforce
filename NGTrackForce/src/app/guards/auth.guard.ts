import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
/**
 * Controls whether or not the user can actually see a component. If they cannot, they are also redirected by this.
 * Use case: on the component where you want this, in the routes.ts file, simply set the 'canActivate' property to 
 * an array that contains this
 **/
export class AuthGuard implements CanActivate
{
	// dependency-injecting the router
    constructor(private router : Router) {}
	/**
	 *  The method provided to us by CanActivate interface. Controls whether or not the element with this...canActivate !
	 *  Responsible for redirecting the user to the login page, and maintaining the state of their original request, which,
	 *  when they log in, should be redirected to. 
	 */
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        // if there is a user already logged in, this can activate
        if (localStorage.getItem("currentUser"))
        {
            return true;
        }

        // not logged in so redirect to login page with the return url
        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }}); 
        return false;
    }
    
}