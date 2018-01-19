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
   * @function getAssociatesByStatus
   * @description Make an http request to the /track/client webservice, fetching mapped associates
   * with the given marketing status. 
   * @param statusId Contains the marketing status id used to fetch data
   */
  getAssociatesByStatus(statusId) {
    console.log("Inside ClientMappedService");
    console.log("statudId: " + statusId);
    
    //environment.url contains the url to the webservice. See the environment import, above.
    return this.http.get(environment.url + 'TrackForce/track/associates/client/'+ statusId);
  }
}
