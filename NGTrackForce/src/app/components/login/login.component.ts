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
import { trigger, state, style, transition, animate, keyframes } from '@angular/animations';
import { AssociateService } from '../../services/associate-service/associate.service';

const associateInfo = 'associateInfo'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  animations: [
    trigger(
      'enterAnimation', [
        transition(':enter', [
          style({ opacity: 0 }),
          animate('500ms', style({ opacity: 1 }))
        ]),
        transition(':leave', [
          style({ opacity: 1 }),
          animate('500ms', style({ opacity: 0 }))
        ])
      ]
    )
  ]
})
// Decorator for automatically unsubscribing all observables upon ngDestory()
//Prevents memory leaks
@AutoUnsubscribe
export class LoginComponent implements OnInit {
  public username: string;
  public password: string;
  public cpassword: string;
  public fname: string;
  public lname: string;
  public errMsg: any;
  public sucMsg: string;
  public isRegistering = false;
  public associate: any;
  public registerPage = 0;
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
  constructor(private associateService: AssociateService, private authService: AuthenticationService, private router: Router,
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



    if (user != null) {
      if (user.tfRoleId === 5) {

        // this.router.navigate(['associate-view', user.userId]);

        localStorage.setItem(associateInfo, JSON.stringify(user));
        this.router.navigate(['associate-view', user.associateId]);

      }
      else {
        this.getUser(user.userId);
        this.router.navigate(['app-home']);
      }
    }
  }
  /**
  * Enter the register state
  */

  getUser(id) {

    this.associateService.getAssociate(id).subscribe(
      data => {
        this.associate = data;
      },
      err => {
    });

  }
  register() {
    this.errMsg = "";
    this.sucMsg = "";
    this.isRegistering = true;
    this.registerPage = 0;
  }
  /**
  *Function Wrapper for create-user createuser()
  */
  createUser() {
    this.sucMsg = "";
    this.errMsg = "";
    if (this.password === undefined || this.cpassword === undefined || this.password.length === 0 || this.cpassword.length === 0) {
      this.errMsg = 'Please enter a password and confirm password';
    } else if (this.password !== this.cpassword) {
      this.errMsg = 'Passwords do not match!';
    } else {
      this.userService.createAssociate(this.username, this.password, this.fname, this.lname).subscribe(
        data => {
          this.sucMsg = "Associate account creation sucessful.";
        },
        err => {
          console.error(err + " Error Occurred");
          this.errMsg = 'Error: new associate not created!';
        }
      );
    }
  }
  /**
  * Exit the register state
  */
  cancelRegister() {
    this.sucMsg = "";
    this.errMsg = "";
    this.isRegistering = false;
    this.registerPage = 0;
  }
  /**
   * Change the current page to the firstname lastname input form
   */
  next() {
    this.registerPage = 1;
  }

  /**
   * Change the current page to username and password
   */
  previous() {
    this.registerPage = 0;
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
    this.sucMsg = "";
    this.errMsg = "";
    if (this.username && this.password) {
      this.authService.login(this.username, this.password).subscribe(
        data => {
          const user = this.authService.getUser();
          //navigate to appropriate page if return is valid
          //4 represents an associate role, who are routed to associate-view
        
          if(user.tfRoleId === 5) {
            this.router.navigate(['associate-view', user.associateId]);

          } else {
            //otherwise, they are set to root
            this.router.navigate(['app-home']);
          }
        },
        err => {
          this.authService.logout();
          if (err.status == 500)
            this.errMsg = "There was an error on the server";
          }
          else if (err.status === 400) {
            this.errMsg = "Invalid username and/or password";

          }
          else if (err.status === 403) {
            this.errMsg = "Account not verified";
          }
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
