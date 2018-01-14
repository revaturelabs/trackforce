import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http/';
import { environment}  from '../../../environments/environment'
/**
 * This service is used to handle data for the ClientMappedComponent
 */
@Injectable()
export class ClientMappedService {
  constructor(private http: HttpClient) { }

  /**
   * 
   * @param statusId 
   */
  getAssociatesByStatus(statusId) {
    console.log("Inside ClientMappedService");
    console.log("statudId: " + statusId);
    return this.http.get(environment.url + 'TrackForce/track/client/'+ statusId);
  }
}
