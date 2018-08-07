import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";

import { Associate } from "../../models/associate.model";
import { environment } from "../../../environments/environment";
import { Interview } from "../../models/interview.model";
import {GraphCounts} from "../../models/graph-counts";

/**
 * Service for retrieving and updating data relating to associates.
 * @author Alex, Xavier
 */
@Injectable()
export class AssociateService {
  private baseURL: string = environment.url + "TrackForce/associates";

  constructor(private http: HttpClient) { }

  /**
   *
   * Gets all of the associates
   */
  getAllAssociates(): Observable<Associate[]> {
    const url: string = this.baseURL + '/allAssociates';
    return this.http.get<Associate[]>(url);
  }

  /**
   *
   * Get specific associate by user id
   * @param id - the user id of the user object of an associate to retrieve
   */
  getAssociate(id: number) {
    const url: string = this.baseURL + '/' + id;
    console.log(url);
    return this.http.get<Associate>(url);
  }

  /**
   *
   * Update the given associate's status/client
   * @param ids - list of associate ids of associates to be updated
   * @param marketingStatusId - the marketing status these associates will be updated to
   * @param clientId - the client id that the associates will be mapped to
   */
  updateAssociates(ids: number[], verification: number, marketingStatusId: number, clientId: number): Observable<boolean> {
    const url: string = this.baseURL + "?marketingStatusId=" + marketingStatusId + "&clientId=" + clientId + "&verification=" + verification;
    return this.http.put<boolean>(url, ids);
  }

  /**
   *
   * This method updates the associate in the database
   * @param associate - the associate object with the updated values
   */
  updateAssociate(associate: any) {
    const url: string = this.baseURL + "/" + associate.id;
    return this.http.put<boolean>(url, associate);
  }

  getAssociatesByStatus(statusId: number): Observable<GraphCounts[]> {
    return this.http.get<GraphCounts[]>(this.baseURL + '/mapped/' + statusId);
  }

  approveAssociate(associateID: number) {
    const url: string = this.baseURL + "/" + associateID + "/approve";
    return this.http.put<boolean>(url, associateID);
  }

  getUndeployedAssociates(mappedOrUnmapped: string): Observable<GraphCounts[]> {
    const url: string = this.baseURL + "/undeployed/" + mappedOrUnmapped;
    return this.http.get<GraphCounts[]>(url);
  }

}