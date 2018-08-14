/**
 * @author Michael Tseng
 * @description Receives user inputs from form and submits them to the back-end for validation
 */
import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/authentication-service/authentication.service';
import {Router} from '@angular/router';
import {AutoUnsubscribe} from '../../decorators/auto-unsubscribe.decorator';
import {User} from '../../models/user.model';
import {UserService} from '../../services/user-service/user.service';
import {trigger, state, style, transition, animate, keyframes} from '@angular/animations';
import {AssociateService} from '../../services/associate-service/associate.service';
import {InterviewService} from '../../services/interview-service/interview.service';
import {ClientService} from '../../services/client-service/client.service';
import {BatchService} from '../../services/batch-service/batch.service';
import {TrainerService} from '../../services/trainer-service/trainer.service';
import {Trainer} from '../../models/trainer.model';
import {Batch} from '../../models/batch.model';
import {Associate} from "../../models/associate.model";

const ASSOCIATE_KEY = 'currentAssociate';
const USER_KEY = 'currentUser';
const TRAINER_KEY = 'currentTrainer';
const INTERVIEWS_KEY = 'currentInterviews';
const BATCHES_TRAINER_KEY = 'currentBatchesTrainer';
const BATCHES_COTRAINER_KEY = 'currentBatchesCotrainer';
const BATCHES_KEY = 'currentBatches';
const CLIENTS_KEY = 'currentClients';

/* 
  PROBLEM
  should be admin/sales/staging_key
  SHOULDNT load all associates as soon as one of those roles logs in
*/
const ASSOCIATES_KEY = 'currentAssociates';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  animations: [
    trigger(
      'enterAnimation', [
        transition(':enter', [
          style({opacity: 0}),
          animate('500ms', style({opacity: 1}))
        ]),
        transition(':leave', [
          style({opacity: 1}),
          animate('0ms', style({opacity: 0}))
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
  public role: number;
  public usernameRestrictions = RegExp("^[a-zA-Z0-9]{6,20}$");

  // public passwordRestrictions = RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[.!@#$%^&*])(?=.{8,})");
  // Regex for getting 3 out of 4 restrictions
  public passwordRestrictions = RegExp("^(?=.{8,})((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\W_])|(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[0-9])(?=.*[\\W_])|(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_])|(?=.*[a-z])(?=.*[0-9])(?=.*[\\W_]))");
  public newUser: User;
  public newTrainer: Trainer;
  public newAssociate: Associate;

  public isLoggingIn: boolean = false;
  public loginClicked: boolean = false;

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
  ) {
  }

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
    //TODO validate token with backend
    const user = this.authService.getUser();
    if (user != null) {
      if (user.role === 5) {
        // this.router.navigate(['associate-view']);
        this.router.navigate(['associate-view']);
        // } else if (user.role === 2) {
        //   this.router.navigate(['trainer-view']);
      } else if (user.role === 2) {
        this.router.navigate(['trainer-view']);
      } else if (user.role === 1 || user.role === 3 || user.role === 4) {
        // this.getUser(user.id);
        this.router.navigate(['app-home']);
      } else {
        this.authService.logout();
      }
    }
  }

  onAnimationDone($event){
    if(this.loginClicked){
      this.isLoggingIn = true;
    }
  }

  /**
   * Enter the register state
   */

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
      this.errMsg = 'Please enter a password and confirm password!';
    } else if (this.password !== this.cpassword) {
      this.errMsg = 'Passwords do not match!';
    } else if (!this.usernameRestrictions.test(this.username.trim()) && !this.passwordRestrictions.test(this.password)) {
      this.errMsg = `Invalid username and password!<br>
        <br>Password requirements:
          <ul>
            <li>• 6 - 20 alphanumeric characters.</li>
            <li>• No spaces.</li>
            <li>• No special characters</li>
          </ul>
        <br>Password requirements:
          <ul>
            <li>• At least, 8 characters.</li>
            <li>• At least, 1 number.</li>
            <li>• At least, 1 lowercase letter.</li>
            <li>• At least, 1 uppercase letter.</li>
            <li>• At least, 1 special character. (.!@#$%^&*)</li>
          </ul>`;
    } else if (!this.usernameRestrictions.test(this.username.trim())) {
      this.errMsg = `Invalid username!<br>
        <br>Password requirements:
          <ul>
            <li>• 6 - 20 characters.</li>
            <li>• No spaces.</li>
            <li>• No special characters</li>
          </ul>`;
    } else if (!this.passwordRestrictions.test(this.password)) {
      this.errMsg = `Invalid password!<br>
        <br>Password requirements:
        <ul>
          <li>• At least, 8 characters.</li>
          <li>• At least, 1 number.</li>
          <li>• At least, 1 lowercase letter.</li>
          <li>• At least, 1 uppercase letter.</li>
          <li>• At least, 1 special character. (.!@#$%^&*)</li>
        </ul>`;
    } else {
      switch (this.role) {
        case 2:
          this.newUser = new User(this.username, this.password, 2, 0);
          this.newTrainer = new Trainer(this.fname, this.lname, this.newUser);
          this.resetFields();
          this.userService.createTrainer(this.newTrainer).subscribe(
            data => {
              this.sucMsg = "Trainer account creation successful.";
            },
            err => {
              console.error(err + "Error Occurred!");
              this.errMsg = 'Error: New Trainer is not created!';
            }
          );
          break;
        case 5:
          this.newUser = new User(this.username, this.password, 5, 0);
          this.newAssociate = new Associate(this.fname, this.lname, this.newUser);
          this.resetFields();
          this.userService.createAssociate(this.newAssociate).subscribe(
            data => {
              this.sucMsg = "Associate account creation successful.";
            },
            err => {
              console.error(err + " Error Occurred!");
              this.errMsg = 'Error: New Associate is not created!';
            }
          );
          break;
        default:
          this.errMsg = 'Please select a role!';
          break;
      }

    }
  }

  resetFields() {
    this.isRegistering = false;
    this.username = '';
    this.password = '';
    this.cpassword = '';
    this.fname = '';
    this.lname = '';
    this.role = undefined;
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
   * Function wrapper for AuthenticationService login()
   * Sends user input to service for real login
   * Then navigates user to home if correct info is provided
   *
   *@param none
   *
   */
  login() {
    this.loginClicked = true;
    this.sucMsg = "";
    this.errMsg = "";
    if (this.username && this.password) {
      this.authService.login(this.username, this.password).subscribe(
        data => {

          localStorage.setItem(USER_KEY, JSON.stringify(data));

          if(data == null){
            this.resetAfterLoginFail();
            this.errMsg = "Invalid username and/or password";
          } else if (data.isApproved) {
            //navigate to appropriate page if return is valid
          //4 represents an associate role, who are routed to associate-view
            if (data.role === 5) {
              this.associateLogin(data);
            } else if (data.role === 2) {
              this.trainerLogin(data);
            } else if (data.role === 1 || data.role === 3 || data.role === 4) {
              this.salesOrStagingLogin();
            } else {
              this.resetAfterLoginFail();
            }
          } else {
            this.resetAfterLoginFail();
            this.errMsg = "Your account has not been approved.";
          }
        },
        err => {
          this.resetAfterLoginFail();

          if (err.status === 500) {
            this.errMsg = "There was an error on the server";
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
   * Resets loading bools for ngif and performs logout action
   */
  resetAfterLoginFail(){
    this.isLoggingIn = false;
    this.loginClicked = false;
    this.authService.logout();
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
    this.trainerService.getTrainer(user.id).subscribe(
      data => {
        localStorage.setItem(TRAINER_KEY, JSON.stringify(data));
      },
      err => {
        if (err.status === 500) {
          this.router.navigate(['ServerError']);
          return;
        } else {
          this.router.navigate(['Error']);
          return;
        }
      }
    );
  }

  // Logan removed the getAllAssociates from here.
  // It was running twice, causing initial login for admin/sales/staging to be suuuuuuuper slow
  salesOrStagingLogin() {
    this.router.navigate(['app-home']);
  }

}
