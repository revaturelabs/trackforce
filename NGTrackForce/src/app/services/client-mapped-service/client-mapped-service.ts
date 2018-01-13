import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http/';
import { environment}  from '../../../environments/environment'
/**
 * This service is used to handle data for the ClientMappedComponent
 */
@Injectable()
export class ClientMappedService {
  private statusID = 1; //Initialize to 'Trainer' for now
  constructor(private http: HttpClient) { }

  getAssociatesByStatus() {
    return this.http.get(environment.url + 'TrackForce/track/client/'+ this.statusID);
  }
}
