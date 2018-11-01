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
// import {InterviewService} from '../../services/interview-service/interview.service';
// import {ClientService} from '../../services/client-service/client.service';
// import {BatchService} from '../../services/batch-service/batch.service';
import { TrainerService } from '../../services/trainer-service/trainer.service';
import { Trainer } from '../../models/trainer.model';
// import {Batch} from '../../models/batch.model';
import { Associate } from "../../models/associate.model";
import { NavbarService } from '../../services/navbar-service/navbar.service';
async function delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve => {
    }, ms));
}
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
                    style({ opacity: 0 }),
                    animate('500ms', style({ opacity: 1 }))
                ]),
                transition(':leave', [
                    style({ opacity: 1 }),
                    animate('0ms', style({ opacity: 0 }))
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
    public role2: number;
    public usernameRestrictions = RegExp("^[a-zA-Z0-9]{6,20}$");
    public boolean = false;

    // public passwordRestrictions = RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[.!@#$%^&*])(?=.{8,})");
    // Regex for getting 3 out of 4 restrictions
    public passwordRestrictions = RegExp("^(?=.{8,})((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\W_])|(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[0-9])(?=.*[\\W_])|(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_])|(?=.*[a-z])(?=.*[0-9])(?=.*[\\W_]))");
    public newUser: User;
    public newTrainer: Trainer;
    public newAssociate: Associate;

    public isLoggingIn = false;
    public loginClicked = false;
    public userRequirement: String = ""
    public dataReady = true;
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
        // private interviewService: InterviewService,
        // private clientService: ClientService,
        // private batchService: BatchService,
        private trainerService: TrainerService,
        private navbarService: NavbarService
    ) {
    }


    /**
     * 1806_Austin_M
     * Checks if the user has a current session
     * Validates the JWT with the back end.
     * If successful, navigate to user home;
     * else, remain on login screen
     */
    ngOnInit() {
        //build session factory on sign up
        this.userService.buildSessionFactory().subscribe(
            data => { },
            error => { }
        );

        //Validate token with backend
        const user = this.authService.getUser();
        this.navbarService.hide();

        if (user != null) {
            this.loginClicked = true;
            this.isLoggingIn = true;


            this.userService.checkJwtValid().subscribe(
                data => { this.routeToUserHome(this.authService.getUserRole()); },
                err => { this.resetAfterLoginFail() }
            );
        }
    }

    routeToUserHome(role: number) {
        this.navbarService.show();

        if (role === 5) {
            this.router.navigate(['associate-view']);
        } else if (role === 2) {
            this.router.navigate(['trainer-view']);
        } else if (role === 1 || role === 3 || role === 4) {
            this.router.navigate(['app-home']);
        } else {
            this.navbarService.hide();
            this.authService.logout();
        }
    }

    onAnimationDone($event) {
        if (this.loginClicked) {
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
        <br>Username requirements:
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
        <br>Username requirements:
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
                    this.dataReady = false;
                    this.newUser = new User(this.username, this.password, 2, 0);
                    this.newTrainer = new Trainer(this.fname, this.lname, this.newUser);
                    this.resetFields();
                    this.userService.createTrainer(this.newTrainer).subscribe(

                        data => {
                            this.dataReady = true;
                            this.sucMsg = "Trainer account creation successful.";
                        },
                        err => {
                            this.dataReady = true;
                            console.error(err + "Error Occurred!");
                            this.errMsg = 'Error: New Trainer is not created!';
                        }
                    );
                    break;
                case 5:
                    this.dataReady = false;
                    this.newUser = new User(this.username, this.password, 5, 0);
                    this.newAssociate = new Associate(this.fname, this.lname, this.newUser);
                    this.resetFields();
                    this.userService.createAssociate(this.newAssociate).subscribe(
                        data => {
                            this.dataReady = true;
                            this.sucMsg = "Associate account creation successful.";
                        },
                        err => {
                            this.dataReady = true;
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
        this.sucMsg = "";
        this.errMsg = "";
        if (this.username && this.password) {
            this.loginClicked = true;
            this.authService.login(this.username, this.password).subscribe(
                (data: User) => {
                    console.log("In login");
                    localStorage.setItem(USER_KEY, JSON.stringify(data));
                    console.log(data);
                    if (data == null) {
                        this.resetAfterLoginFail();
                        this.errMsg = "Invalid username and/or password";
                    } else if (data.isApproved) {
                        this.authService.getUserRoleFirst();
                        setTimeout(() => {
                            this.role2 = this.authService.getUserRole();
                            console.log(this.role2);
                            if (this.role2 === 5) {
                                this.associateLogin(data);
                            } else if (this.role2 === 2) {
                                this.trainerLogin(data);
                            } else if (this.role2 === 1 || this.role2 === 3 || this.role2 === 4) {
                                console.log("In sales or staging login");
                                this.salesOrStagingLogin();
                            }
                        }, 10);


                        //navigate to appropriate page if return is valid
                        //5 represents an associate role, who are routed to associate-view
                        // if (this.role2 === 5) {
                        //     this.associateLogin(data);
                        // } else if (this.role2 === 2) {
                        //     this.trainerLogin(data);
                        // } else if (this.role2 === 1 || this.role2 === 3 || this.role2 === 4) {
                        //     console.log("In sales or staging login");
                        //     this.salesOrStagingLogin();
                        // } else {
                        //     this.resetAfterLoginFail();
                        // }
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
                    } else if (err.status === 401) {
                        this.errMsg = "Username or Password invalid";
                    } else {
                        this.errMsg = "Login service cannot be reached.";
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
    resetAfterLoginFail() {
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
                this.navbarService.show();
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
                this.navbarService.show();
                localStorage.setItem(TRAINER_KEY, JSON.stringify(data));
                this.router.navigate(['trainer-view']);
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
        this.navbarService.show();
        this.router.navigate(['app-home']);
    }

}
