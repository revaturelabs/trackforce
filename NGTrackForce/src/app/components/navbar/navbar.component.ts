import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { User } from '../../models/user.model';
/**
  * Controls the nav bar
  */

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  //User object containing need data
  user: User;
  //Used for conditional display with ngIf
  //If admin, show the create user button
  isAdmin: boolean;
  isAssociate: boolean;

  constructor(private router: Router, private authService: AuthenticationService) { }

  ngOnInit() {
    //gets the user from localStorage
    this.user = this.authService.getUser();

    //Role checks
    if(this.user.tfRoleId === 1){
      this.isAdmin = true;
    } else {
      this.isAdmin = false;
    }

    if(this.user.tfRoleId === 4){
      this.isAssociate = true;
    } else {
      this.isAssociate = false;
    }


  }

  /**
    * Removes user from localStorage and re-routes to login screen
    */
  logout(){
    this.authService.logout();
    this.router.navigateByUrl('/login');
  }

}
