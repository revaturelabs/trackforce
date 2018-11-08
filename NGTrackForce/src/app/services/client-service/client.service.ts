import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpRequest, HttpHandler } from '@angular/common/http';
import { Observable, AsyncSubject, BehaviorSubject } from 'rxjs';
import { environment } from '../../../environments/environment'
import { Client } from '../../models/client.model';
import { UserService } from './../user-service/user.service';
import { Role } from './../../models/role.model';
import { AuthenticationService } from './../authentication-service/authentication.service';
import { JwtInterceptor } from './../../interceptors/jwt.interceptor';
import { Token } from '@angular/compiler';

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
  private User: user;
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

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.role = User.role;

    if (request.url.endsWith('//mapped/get/') && request.method === 'GET'){
      if (request.body.role === this.user.Role)
        return Observable.of(new HttpResponse({status: 200, body: {token: this.user.Token}})
    } else {
      return Observable.of(new HttpResponse({status: 401, body: { token: null} }));
    }
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
