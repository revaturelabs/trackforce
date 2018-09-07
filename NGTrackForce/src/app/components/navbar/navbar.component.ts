import { Component, OnInit, OnChanges, AfterContentChecked, Input, AfterViewInit, AfterViewChecked, DoCheck, AfterContentInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { User } from '../../models/user.model';
import { Associate } from '../../models/associate.model';
import { NavbarService } from '../../services/navbar-service/navbar.service';
import { AssociateService } from '../../services/associate-service/associate.service';
import { TrainerService } from '../../services/trainer-service/trainer.service';
/**
  * Controls the nav bar
  */

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnChanges, AfterContentChecked {

  // @Input() associate: Associate;
  //Used for conditional display with ngIf
  //If admin, show the create user button
  //User object containing need data
  public user: User;
  public isLoggedIn: boolean;
  public isAdmin: boolean;
  public isSales: boolean;
  public isStaging: boolean;
  public isTrainer: boolean;
  public isAssociate: boolean;
  public id: number;
  isDataReady = false;
  public firstName = '';
  public username = '';
  public currentUser: User;


  constructor(private router: Router, private authService: AuthenticationService, public navbarService: NavbarService, 
    private associateService: AssociateService, public trainerService: TrainerService) {}

  ngOnInit() {
    // this.navbarDisplay();
  }

  ngOnChanges() {
    // this.navbarDisplay();
  }

  ngAfterContentChecked() {
    this.navbarDisplay();
  }

  /**
    * Removes user from localStorage and re-routes to login screen
    */
  logout() {
    this.isLoggedIn = false;
    this.isAdmin = false;
    this.isTrainer = false;
    this.isAssociate = false;
    this.user = null;
    this.authService.logout();
  }

  navbarDisplay() {
    this.user = this.authService.getUser();
    
    if (this.user !== null && this.user !== undefined) {
      this.isLoggedIn = true;
      this.username = this.user.username;
      if (this.user.role === 1) {
        this.isAdmin = true;
        this.isSales = false;
        this.isStaging = false;
        this.isTrainer = false;
        this.isAssociate = false;
        this.firstName = "";
      } else if(this.user.role === 3){
        this.isAdmin = false;
        this.isSales = true;
        this.isStaging = false;
        this.isTrainer = false;
        this.isAssociate = false;
        this.firstName = "";
      } else if(this.user.role === 4){
        this.isAdmin = false;
        this.isSales = false;
        this.isStaging = true;
        this.isTrainer = false;
        this.isAssociate = false;
        this.firstName = "";
      } else if (this.user.role === 2){
        this.isAdmin = false;
        this.isSales = false;
        this.isStaging = false;
        this.isTrainer = true;
        this.isAssociate = false;
      } else if (this.user.role === 5){
        this.isAdmin = false;
        this.isSales = false;
        this.isStaging = false;
        this.isTrainer = false;
        this.isAssociate = true;
        this.firstName = this.associateService.getAssociate(this.user.id).value.firstName;
      }
    }

    // this.user = JSON.parse(localStorage.getItem('currentUser'));
    // this.id = this.user.id;
    // this.firstName = this.associateService.getAssociateName(this.id);

    //Role checks
    // only role check if there is already a user
    // if (this.user !== null && this.user !== undefined) {
    //   this.isLoggedIn = true;
    //   this.username = this.user.username;
    //   if (this.user.role === 1) {
    //     this.isAdmin = true;
    //     this.isSales = false;
    //     this.isStaging = false;
    //     this.isTrainer = false;
    //     this.isAssociate = false;
    //   } else if(this.user.role === 3){
    //     this.isAdmin = false;
    //     this.isSales = true;
    //     this.isStaging = false;
    //     this.isTrainer = false;
    //     this.isAssociate = false;
    //   } else if(this.user.role === 4){
    //     this.isAdmin = false;
    //     this.isSales = false;
    //     this.isStaging = true;
    //     this.isTrainer = false;
    //     this.isAssociate = false;
    //   } else if (this.user.role === 2){
    //     this.isAdmin = false;
    //     this.isSales = false;
    //     this.isStaging = false;
    //     this.isTrainer = true;
    //     this.isAssociate = false;
    //   } else if (this.user.role === 5){
    //     this.isAdmin = false;
    //     this.isSales = false;
    //     this.isStaging = false;
    //     this.isTrainer = false;
    //     this.isAssociate = true;
    //   }
    }
  }
