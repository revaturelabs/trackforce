import { Component, OnInit, OnChanges, AfterContentChecked, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { User } from '../../models/user.model';
import { Associate } from '../../models/associate.model';
/**
  * Controls the nav bar
  */

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnChanges, AfterContentChecked {

  //User object containing need data
  user: User;
  // @Input() associate: Associate;
  //Used for conditional display with ngIf
  //If admin, show the create user button
  isAdmin: boolean;
  isAssociate: boolean;
  username = '';

  constructor(private router: Router, private authService: AuthenticationService) { }

  ngOnInit() {
    // this.navbarDisplay();
  }

  ngOnChanges() {
    // this.navbarDisplay();
  }
  ngAfterContentChecked(){
    this.navbarDisplay();
  }

  /**
    * Removes user from localStorage and re-routes to login screen
    */
  logout() {
    this.isAdmin = false;
    this.isAssociate = false;
    this.user = null;
    this.authService.logout();
    // linked to /login page directly on anchor for testing purposes
    //this.router.navigateByUrl('/login');
  }

  navbarDisplay() {
    this.user = this.authService.getUser();
    //Role checks
    // only role check if there is already a user
    if (this.user !== null && this.user !== undefined) {
      this.username = this.user.username;
<<<<<<< HEAD
      if (this.user.role.id === 1) {
=======
      if (this.user.role === 1) {
>>>>>>> 1fc12474cec4c9c234f601927392d638d3035973
        this.isAdmin = true;
      } else {
        this.isAdmin = false;
      }
<<<<<<< HEAD
      if (this.user.role.id === 5) {
=======
      if (this.user.role === 5) {
>>>>>>> 1fc12474cec4c9c234f601927392d638d3035973
        this.isAssociate = true;
      } else {
        this.isAssociate = false;
      }

    }


  }

}
