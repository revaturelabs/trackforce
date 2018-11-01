/**
 *
 * @author Michael Tseng
 * @description Service for authenicating users
 *
 * This service contains the login and logout logic as well as
 * logic to retrieve user, associate, and trainer objects from local storage
 */
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

import { RequestService } from "../request-service/request.service";
import { User } from "../../models/user.model";
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../environments/environment";
import { Associate } from "../../models/associate.model";
import { Trainer } from "../../models/trainer.model";
import { AngularWaitBarrier } from "blocking-proxy/built/lib/angular_wait_barrier";
import { THIS_EXPR } from "@angular/compiler/src/output/output_ast";
// import 'rxjs/Rx';

import { async } from '@angular/core/testing';

const ASSOCIATE_KEY = "currentAssociate";
const USER_KEY = "currentUser";
const TRAINER_KEY = "currentTrainer";

@Injectable()
export class AuthenticationService {
    role = 0;
    constructor(
        private rs: RequestService,
        private router: Router,
        private http: HttpClient
    ) { }

    /**
     *
     * Function for submitting login data to the back-end
     * Login service that stores a user object on local storage
     * It will only store a user if the object itself is valid and the token is valid
     *@param {String} username - The username to be checked against the database
     *@param {String} password - The password need to be sent to the database for checking
     *
     *@return User data from back-end if credentials are correct
     * user data contains JWT token, username, and role
     * If credentials are wrong, 401 is returned
     */
    public login(username: string, password: string): Observable<User> {
        return this.http.post<User>(environment.url + "TrackForce/users/login", {
            username: username,
            password: password
        });
    }

    /**
     *Removes user from localStorage
     *And navigates back to login
     *
     *@param none
     */
    logout() {
        localStorage.clear();
        this.router.navigate(["login"]);
    }

    /**
     * This method will return the User Object from local storage
     *
     * @param none
     *
     * @author Max Dunn
     */
    getUser(): User {
        const user: User = JSON.parse(localStorage.getItem(USER_KEY));
        return user;
    }


    getUserRole() {
        return this.role;
    }

    getUserRoleFirst() {
        return this.http.get<number>(environment.url + "TrackForce/users/getUserRole").subscribe(data => {
            this.role = data;
        }, err => err);

    }
    /**
     * This method will return the Associate Object from local storage
     *
     * @param none
     *
     * @author Max Dunn
     */
    getAssociate(): Associate {
        const associate: Associate = JSON.parse(
            localStorage.getItem(ASSOCIATE_KEY)
        );
        return associate;
    }

    /**
     * This method will return the Trainer Object from local storage
     *
     * @param none
     *
     * @author Max Dunn
     */
    getTrainer(): Trainer {
        const trainer: Trainer = JSON.parse(localStorage.getItem(TRAINER_KEY));
        return trainer;
    }

}
