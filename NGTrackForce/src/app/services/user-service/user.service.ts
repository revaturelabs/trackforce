/**
 * @author Andrew Crenwelge
 */
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

import {RequestService} from '../request-service/request.service';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Trainer} from "../../models/trainer.model";
import {Associate} from "../../models/associate.model";
import {User} from "../../models/user.model";


@Injectable()
export class UserService {

  private baseURL: string = environment.url + "TrackForce/users";
  private headers = new Headers({ 'Content-Type': 'application/json' });


  /**
   * @constructor
   * @param {RequestService}
   * Service for handling all requests to the server
   */
  constructor(private rs: RequestService, private http: HttpClient) {
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
  public createUser(newUser: User): Observable<boolean> {
    return this.http.post<boolean>(this.baseURL + '/newUser',
      newUser);
  }

  public createAssociate(newAssociate: Associate): Observable<boolean> {
    return this.http.post<boolean>(this.baseURL + '/newAssociate',
      newAssociate);
  }

  public createTrainer(newTrainer: Trainer): Observable<boolean> {
    return this.http.post<boolean>(this.baseURL + '/newTrainer',
      newTrainer);
  }

  //EDIT EricS 8/9/18 Added this service to fix nonunique username bug (Issue 273)
  public checkUniqueUsername(username: string): Observable<boolean> {
      return this.http.post<boolean>(this.baseURL + '/checkUsername', username);
  }

}
