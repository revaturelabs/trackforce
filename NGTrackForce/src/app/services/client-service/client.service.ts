import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, AsyncSubject } from 'rxjs';
import { environment } from '../../../environments/environment'
import { Client } from '../../models/client.model';

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

  private clients$: AsyncSubject<Client[]> = new AsyncSubject<Client[]>();

  constructor(private http: HttpClient) { }

  /**
   *
   * Get a list of all of the clients
   */
  getAllClients(): Observable<Client[]> {
    this.http.get<Client[]>(this.baseURL).subscribe(
      (data: Client[]) => this.clients$.next(data),
      (error) => this.clients$.error(error)
    );
    return this.clients$;
  }

  /**
   * Same as above, but promise based rather than using an Async Behavior
   */
  getAllClientsPromise(): Promise<Client[]> {
    return new Promise((resolve, reject)=> {
      this.http.get<Client[]>(this.baseURL).subscribe(
        (data: Client[]) => resolve(data),
        (error) => reject(error)
      );
    });
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
