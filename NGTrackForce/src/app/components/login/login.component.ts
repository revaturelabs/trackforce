/**
 * @author Michael Tseng
 * @description Receives user inputs from form and submits them to the back-end for validation
 */
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { Router } from '@angular/router';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
// Decorator for automatically unsubscribing all observables upon ngDestory()
//Prevents memory leaks
@AutoUnsubscribe
export class LoginComponent implements OnInit {
  public username: string;
  public password: string;
  public errMsg: any;

  /**
  *@constructor
  *
  *@param {AuthenticationService} authService
  * Service for login; provides the needed functions, such as login() and logout()
  *
  *@param {Router} router
  * Service needed for redirecting user upon successful login
  *
  */
  constructor(private authService: AuthenticationService, private router: Router) { }

  /**
  * Called upon component initiation
  * Checks if the user is already to logged-in
  * If they are re-route to root
  * If the user is an associate, route them to associate view
  * Admins, VPs, and managers/directors are sent to root
  *
  *@param none
  *
  */
  ngOnInit() {
    const user = this.authService.getUser();
    if (user != null){
      if(user.tfRoleId === 4){
        this.router.navigate(['associate-view', user.userId]);
      }
      else{
        this.router.navigate(['root']);
      }

    }

  }

  /**
  * Function wrapper for AuthenticationService login()
  * Sends user input to service for real login
  *Then navigates user to home if correct info is provided
  *
  *@param none
  *
  */
  login() {
    this.errMsg = "";
    this.authService.login(this.username, this.password).subscribe(
      data => {
        const user = this.authService.getUser();
        //navigate to appropriate page if return is valid
        //4 represents an associate role, who are routed to associate-view
        if(user.tfRoleId === 4){
          this.router.navigate(['associate-view', user.userId]);
        } else {
          //otherwise, they are set to root
          this.router.navigate(['root']);
        }
      },
      err => {
        this.authService.logout();
        console.log(err);
        if (err.status == 500)
          this.errMsg = "There was an error on the server";
        else if (err.status == 400)
          this.errMsg = "Invalid username and/or password";
        else {
          this.errMsg = "Unable to login";
        }
      }
    );
  }

}
