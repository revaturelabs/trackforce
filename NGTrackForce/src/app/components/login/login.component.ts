/**
 * @author Michael Tseng
 * @description Receives user inputs from form and submits them to the back-end for validation
 */
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { Router } from '@angular/router';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user-service/user.service';

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
  public cpassword: string;
  public ASSOCIATEROLEID: number = 4;
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
  constructor(private authService: AuthenticationService, private router: Router,
                private userService: UserService) { }

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
  public isRegistering = false;
  /**
  *Function Wrapper for create-user createuser()
  */
  register(){
    this.errMsg = "";
  	this.isRegistering = true;
  }
  /**
  *Function Wrapper for create-user createuser()
  */
  createUser(){
	this.errMsg="";
      if(this.password !== this.cpassword){
        this.errMsg='Passwords do not match!';
      } else {
        this.userService.createUser(this.username, this.password, this.ASSOCIATEROLEID).subscribe(
          data => {
            //navigate to home page if return is valid
            this.router.navigate(['login']);
          },
          err => {
            console.error(err + " Error Occurred");
            this.errMsg='Error: new associate not created!';
          }
        );
	  }
  }
  /**
  *Function Wrapper for create-user createuser()
  */
  cancelRegister(){
  	this.isRegistering = false;
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
    if (this.username && this.password) {
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
            this.errMsg = "The login service could not be reached";
          }
        }
      );
    } else {
      this.errMsg = "Please enter a username and password";
    }
  }

}
