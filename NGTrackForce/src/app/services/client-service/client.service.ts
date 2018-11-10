import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpRequest, HttpHandler, HttpEvent, HTTP_INTERCEPTORS, } from '@angular/common/http';
import { Observable, AsyncSubject, BehaviorSubject} from 'rxjs';
import { environment } from '../../../environments/environment'
import { Client } from '../../models/client.model';
import { User } from '../../models/user.model';
import { UserService } from './../user-service/user.service';
import { Role } from './../../models/role.model';
import { AuthenticationService } from './../authentication-service/authentication.service';
import { JwtInterceptor } from './../../interceptors/jwt.interceptor';
import { Token } from '@angular/compiler';
import { of } from 'rxjs/index';
import 'rxjs/add/operator/of';


/**
 * @author Han Jung
 * @description methods for grabbing client data
 */

@Injectable()
export class ClientService {
  private baseURL: string = environment.url + 'TrackForce/clients';
  private mappedClientUrl = environment.url + 'TrackForce/clients/mapped/get/'
  private clientUrl = environment.url + 'TrackForce/clients/associates/get/'
  private fiftyUrl = environment.url + 'TrackForce/clients/50';
  private authService: AuthenticationService;
  public role:number;
  private user: User;
  private jwtInterceptor: JwtInterceptor;
  private clients$: BehaviorSubject<Client[]> = new BehaviorSubject<Client[]>([]);

  constructor(private http: HttpClient) { }

  /**
   *
   * Get a list of all of the clients
   */
  getAllClients(): Observable<Client[]> {
    this.role = this.authService.getUserRole();
    if (this.role === 1) {
    this.http.get<Client[]>(this.baseURL).subscribe(
      (data: Client[]) => this.clients$.next(data),
      (error) => this.clients$.error(error)
    );
    return this.clients$;
  }}

  clientIntercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let user = this.user;

    //authenticate the user for access to client list
    if (request.url.endsWith('/submitCredentials') && request.method === 'POST') {
      if (request.body.username === this.user.username && request.body.password === this.user.password){
        return Observable.of(new HttpResponse({ status: 200, body: {user : this.user.token }}));
      }else {
        return Observable.of(new HttpResponse({status: 401, body: {user: null}}));
      }
    }
    //return Observable to server api call
    if (request.url.endsWith('//mapped/get/') && request.method === 'GET'){
      if (request.body.role === this.user.role) {
        this.getAllClients();
        return Observable.of(new HttpResponse({status: 200, body: {token: this.user.token}}));
        
    } else {
      return Observable.of(new HttpResponse({status: 401, body: {token: this.user.token}}));
    }
    }
    //return this.getAllClients();
  }


  getFiftyClients(): Observable<Client[]> {
    this.http.get<Client[]>(this.fiftyUrl).subscribe(
      (data: Client[]) => this.clients$.next(data),
      (error) => this.clients$.error(error)
    );
    return this.clients$;
  }

  //This method was meant to return all clients with mapped associates.
  //But is currently not used due to incorrect query in the back-end.
  getAllClientsWithAssoc(): Observable<Client[]>{
    return this.http.get<Client[]>(this.mappedClientUrl);
  }

  //This method returns mapped associate counts for a selected client
  getClientCount(clientId: number): Observable<number>{
    return this.http.get<number>(this.clientUrl + clientId);
  }

}
