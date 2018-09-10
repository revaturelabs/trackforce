import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../services/authentication-service/authentication.service';

@Injectable()
/**
 * Controls whether or not the user can actually see a component. If they cannot, they are also redirected by this.
 * Use case: on the component where you want this, in the routes.ts file, simply set the 'canActivate' property to
 * an array that contains this
 **/
export class AuthGuard implements CanActivate {
	// dependency-injecting the router
    constructor(private router: Router, private authService: AuthenticationService) {}
	/**
	 *  The method provided to us by CanActivate interface. Controls whether or not the element with this...canActivate !
	 *  Responsible for redirecting the user to the login page, and maintaining the state of their original request, which,
	 *  when they log in, should be redirected to.
	 */
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

        // not logged in so redirect to login page with the return url
        if (!localStorage.getItem("currentUser")) {
            this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
            return false;
        }

        const expectedRoles: number[] = route.data.expectedRoles;

        // check of component is restricted by role
        if(expectedRoles !== undefined){
            const user = this.authService.getUser();
            if(!expectedRoles.includes(user.role)){
                this.routeToUserHome(user.role);
                return false;
            }
        }

        return true;
    }

    /**
     * 1806_Austin_M
     * Routes to home page of given user role.
     * @param role user role held in local storage
     */
    routeToUserHome(role: number){
        if (role === 5) {
            this.router.navigate(['associate-view']);
        } else if (role === 2) {
            this.router.navigate(['trainer-view']);
        } else if (role === 1 || role === 3 || role === 4) {
            this.router.navigate(['app-home']);
        }
    }
}
