import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
//import { User } from '../../models/User/user.model';
import { Response } from '@angular/http/src/static_response';

@Injectable()
export class CreateUserService {

    status: string
    user: string

    constructor(private http: HttpClient) {

    }

    /**
     * Get logged-in user's username
     */
    getUserName() {
        let url: string = "http://localhost:8085/TrackForce/track/user/name"
        return this.http.get(url).map((response: Response) => response.json());
    }

     /**
     * Get logged-in user's role id
     */
    getUserRoleId() {
        let url: string = "http://localhost:8085/TrackForce/track/user/role"
        return this.http.get(url).map((response: Response) => response.json());
    }

    /**
     * Create User
     * @param username
     * @param password
     * @param roleId
     */
    createUser(username: string, password: string, roleId: number) {
        let url: string = "http://localhost:8085/TrackForce/track/user/create" //Verify this end point

        return this.http.post(url, { username: username, password: password, role: roleId})
    }

}
