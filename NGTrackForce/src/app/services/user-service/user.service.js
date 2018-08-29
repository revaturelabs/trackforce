"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
/**
 * @author Andrew Crenwelge
 */
var core_1 = require("@angular/core");
var environment_1 = require("../../../environments/environment");
var userAndCreatorRoleContainer_mode_1 = require("../../models/userAndCreatorRoleContainer.mode");
var UserService = /** @class */ (function () {
    /**
     * @constructor
     * @param {RequestService}
     * Service for handling all requests to the server
     */
    function UserService(rs, http) {
        this.rs = rs;
        this.http = http;
        this.baseURL = environment_1.environment.url + "TrackForce/users";
        this.headers = new Headers({ 'Content-Type': 'application/json' });
    }
    //For neither of the two below functions do we care about what it returns, it's pass or fail. - Curtis, 6.18.06.16
    /**
     * Creates new user in database
     * @param {User} newUser - New user object.
     *
     *      1  - Admin - Can do anything and everything
     *      2 - Trainer -  Can view everything, but not edit, approve associate registration
     *      3  - Sales/Delivery - Can view and edit everything, comment on interviews, and add feedback
     *      4  - Staging Manager - Can view and edit everything, comment on interviews, and add feedback
     *      5  - Associate  - Can register, view and edit their info, add and flag interviews
  
     */
    UserService.prototype.createUser = function (newUser, creatorRole) {
        return this.http.post(this.baseURL + '/newUser', new userAndCreatorRoleContainer_mode_1.UserAndCreatorRoleContainer(newUser, creatorRole));
    };
    UserService.prototype.createAssociate = function (newAssociate) {
        return this.http.post(this.baseURL + '/newAssociate', newAssociate);
    };
    UserService.prototype.createTrainer = function (newTrainer) {
        return this.http.post(this.baseURL + '/newTrainer', newTrainer);
    };
    //EDIT EricS 8/9/18 Added this service to fix nonunique username bug (Issue 273)
    UserService.prototype.checkUniqueUsername = function (username) {
        return this.http.post(this.baseURL + '/checkUsername', username);
    };
    UserService.prototype.checkJwtValid = function () {
        return this.http.get(this.baseURL + '/check');
    };
    UserService.prototype.buildSessionFactory = function () {
        return this.http.get(this.baseURL + '/init');
    };
    UserService = __decorate([
        core_1.Injectable()
    ], UserService);
    return UserService;
}());
exports.UserService = UserService;
