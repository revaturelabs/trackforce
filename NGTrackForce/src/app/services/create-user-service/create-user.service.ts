/**
 * @author Matt Snee 
 */
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { RequestService } from '../request-service/request.service';

@Injectable()
export class CreateUserService {

    /**
    * @constructor
    * @param {RequestService} 
    * Service for handling all requests to the server
    */
    constructor(private rs: RequestService) {  }

    /**
     * Creates new user in database
     * @param {string} username
     * New user's Username
     * 
     * @param {string} password
     * New user's Password
     * 
     * @param {number} roleId
     * New user's Role ID 
     *      1 Admin ----------- user has full privileges including Creating New Users
     *      2 Manager --------- user has full privileges excluding Creating New Users
     *      3 Vice President -- pending implementation
     *      4 Associate ------- pending implemenation
     */
    createUser(username: string, password: string, roleId: number) {
        return this.rs.createUser(username, password, roleId);
    }

}
