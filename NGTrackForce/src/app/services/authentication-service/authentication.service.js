"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
/**
 *
 * @author Michael Tseng
 * @description Service for authenicating users
 *
 * This service contains the login and logout logic as well as
 * logic to retrieve user, associate, and trainer objects from local storage
 */
var core_1 = require("@angular/core");
var environment_1 = require("../../../environments/environment");
// import 'rxjs/Rx';
var ASSOCIATE_KEY = 'currentAssociate';
var USER_KEY = 'currentUser';
var TRAINER_KEY = 'currentTrainer';
var AuthenticationService = /** @class */ (function () {
    function AuthenticationService(rs, router, http) {
        this.rs = rs;
        this.router = router;
        this.http = http;
    }
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
    AuthenticationService.prototype.login = function (username, password) {
        return this.http.post(environment_1.environment.url + 'TrackForce/users/login', { username: username, password: password });
    };
    /**
    *Removes user from localStorage
    *And navigates back to login
    *
    *@param none
    */
    AuthenticationService.prototype.logout = function () {
        localStorage.clear();
        this.router.navigate(['login']);
    };
    /**
     * This method will return the User Object from local storage
     *
     * @param none
     *
     * @author Max Dunn
     */
    AuthenticationService.prototype.getUser = function () {
        var user = JSON.parse(localStorage.getItem(USER_KEY));
        return user;
    };
    /**
   * This method will return the Associate Object from local storage
   *
   * @param none
   *
   * @author Max Dunn
   */
    AuthenticationService.prototype.getAssociate = function () {
        var associate = JSON.parse(localStorage.getItem(ASSOCIATE_KEY));
        return associate;
    };
    /**
     * This method will return the Trainer Object from local storage
     *
     * @param none
     *
     * @author Max Dunn
     */
    AuthenticationService.prototype.getTrainer = function () {
        var trainer = JSON.parse(localStorage.getItem(TRAINER_KEY));
        return trainer;
    };
    AuthenticationService = __decorate([
        core_1.Injectable()
    ], AuthenticationService);
    return AuthenticationService;
}());
exports.AuthenticationService = AuthenticationService;
