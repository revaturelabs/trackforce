/**
 * @author Michael Tseng
 * @description Receives user inputs from form and submits them to the back-end for validation
 */
import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  //url to REST endpoint
  public loginUrl:string = environment.url + 'TrackForce/track/user/submit';
  public username: string;
  public password: string;

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
  * Clears localStorage
  *
  *@param none
  *
  */
  ngOnInit() {
    this.authService.logout();
  }

  /**
  * Function wrapper for AuthenticationService login()
  * Sends user input to service for real login
  *Then navigates user to home if correct info is provided
  *
  *@param none
  *
  */
  login(){
    this.authService.login(this.username, this.password, this.loginUrl).subscribe(
      data => {
        console.log(data)
        //navigate to home page if return is valid
        this.router.navigate(['home']);
      },
      err => {
        console.log(err + " Error Occurred");
        this.authService.logout();
      }
    );
  }

}
