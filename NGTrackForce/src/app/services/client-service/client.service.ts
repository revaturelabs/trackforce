import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, AsyncSubject, BehaviorSubject } from 'rxjs';
import { environment } from '../../../environments/environment'
import { Client } from '../../models/client.model';
import { LocalStorageUtils } from '../../constants/local-storage';
import { of } from 'rxjs/observable/of';

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

  private clients$: BehaviorSubject<Client[]> = new BehaviorSubject<Client[]>([]);

  constructor(private http: HttpClient) { }

  /**
   *
   * Get a list of all of the clients
   */
  getAllClients(): Observable<Client[]> {
    let key: string = LocalStorageUtils.CACHE_CLIENT_ALL

    if(!LocalStorageUtils.CACHE_ENABLED || !localStorage.getItem(key)) {
        this.http.get<Client[]>(this.baseURL + "/getAll/").subscribe(
          (data: Client[]) => {
            this.clients$.next(data);
            localStorage.setItem(key, JSON.stringify(data));
          },
          (error) => this.clients$.error(error)
        );
        return this.clients$;
      } else {
        return of(JSON.parse(localStorage.getItem(key)));
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
    let key: string = LocalStorageUtils.CACHE_CLIENT_ASSOCIATE_COUNT + "|" + clientId
    let count: BehaviorSubject<number> = new BehaviorSubject<number>(0);

    if(!LocalStorageUtils.CACHE_ENABLED || !localStorage.getItem(key)) {
      this.http.get<number>(this.clientUrl + clientId).subscribe(
        (data: number) => {
          count.next(data)
          localStorage.setItem(key, JSON.stringify(data));
        },
        (error) => this.clients$.error(error)
      )
      return count;
    } else {
      return of(JSON.parse(localStorage.getItem(key)));
    }
  }
}
