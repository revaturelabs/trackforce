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
import { InterviewService } from '../../services/interview-service/interview.service';
import { ClientService } from '../../services/client-service/client.service';
import { BatchService } from '../../services/batch-service/batch.service';
import { TrainerService } from '../../services/trainer-service/trainer.service';
import { Trainer } from '../../models/trainer.model';
import { Batch } from '../../models/batch.model';

const ASSOCIATE_KEY = 'currentAssociate'
const USER_KEY = 'currentUser';
const TRAINER_KEY = 'currentTrainer';
const INTERVIEWS_KEY = 'currentInterviews';
const BATCHES_TRAINER_KEY = 'currentBatchesTrainer';
const BATCHES_COTRAINER_KEY = 'currentBatchesCotrainer';
const BATCHES_KEY = 'currentBatches';
const CLIENTS_KEY = 'currentClients';
const ASSOCIATES_KEY = 'currentAssociates';
const TRAINERS_KEY = 'currentTrainers';


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
  public errMsg: string;
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
  constructor(
    private associateService: AssociateService,
    private authService: AuthenticationService,
    private router: Router,
    private userService: UserService,
    private interviewService: InterviewService,
    private clientService: ClientService,
    private batchService: BatchService,
    private trainerService: TrainerService
  ) { }

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
      if (user.role === 5) {
        // this.router.navigate(['associate-view']);
        this.router.navigate(['associate-view']);
        // } else if (user.role === 2) {
        //   this.router.navigate(['trainer-view']);
      } else if (user.role === 2) {
        this.router.navigate(['trainer-view']);
      } else {
        // this.getUser(user.id);
        this.router.navigate(['app-home']);
      }
    }
  }

  /**
  * Enter the register state
  */

  // getUser(id) {

  //   this.associateService.getAssociate(id).subscribe(
  //     data => {
  //       this.associate = data;
  //     },
  //     err => {
  //     });

  // }
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
          // console.error(err + " Error Occurred");
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
  * Then navigates user to home if correct info is provided
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
          localStorage.setItem(USER_KEY, JSON.stringify(data));
          //navigate to appropriate page if return is valid
          //4 represents an associate role, who are routed to associate-view
          // if (data.isApproved) {
          if (data.role === 5) {
            this.associateLogin(data);
          } else if (data.role === 2) {
            this.trainerLogin(data);
          } else {
            this.router.navigate(['app-home']);
            //otherwise, they are set to root
          }
          // } else {
          //   this.authService.logout();
          //   this.errMsg = "Your account has not been approved.";
          // }
        },
        err => {
          this.authService.logout();
          if (err.status === 500) {
            this.errMsg = "There was an error on the server";
          }
          else if (err.status === 401) {
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

  /**
   * This method is called if the user signing in is an Associate.
   * This method retrieves all of the Interview and Client data connected to the assciate.
   * @param user - user object that was returned after sending a username and password to the server
   * 
   * @author Max Dunn
   */
  associateLogin(user: User) {
    this.associateService.getAssociate(user.id).subscribe(
      data => {
        localStorage.setItem(ASSOCIATE_KEY, JSON.stringify(data));
        this.router.navigate(['associate-view']);
      },
      err => {
        console.log('error getting associate with userid');
        if (err.status === 500) {
          this.errMsg = "There was an error on the server";
        } else {
          this.router.navigate(['Error'])
        }
      }
    );
  }

  /**
   * This method retrieves all the necessary data from the server for a trainer user.
   * @param user - this is the user that is returned upon sending the server a username and password
   */
  trainerLogin(user: User) {
    this.trainerService.getBatchlessTrainer(user.id).subscribe(
      data => {
        localStorage.setItem(TRAINER_KEY, JSON.stringify(data));
        this.router.navigate(['trainer-view']);
      },
      err => {
        if (err.status === 500) {
          this.router.navigate(['ServerError'])
          return;
        } else {
          this.router.navigate(['Error'])
          return;
        }
      }
    );
  }

}