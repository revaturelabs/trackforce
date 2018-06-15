import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";

import { Associate } from "../../models/associate.model";
import { environment } from "../../../environments/environment";
import {Interview} from "../../models/interview.model";

/**
* Service for retrieving and updating data relating to associates.
* @author Alex, Xavier
*/
@Injectable()
export class AssociateService {
  private associatePath = "TrackForce/associates";

  status: string;
  client: string;

  constructor(private http: HttpClient) { }

  /**
  * Get all of the associates
  * Used in associate list and home component, and the associate and data-sync services
  */
  getAllAssociates(): Observable<any> {
    let url: string = environment.url + this.associatePath + '/allAssociates';
    return this.http.get(url);
  }

  /** Get specific associate by id
  * @param id - the id of the associate to retrieve
  */
  getAssociate(id: number) {
    let url: string = environment.url + this.associatePath + '/' + id;
    return this.http.get<Associate>(url);
  }

  /**
  * @function getAssociatesByStatus
  * @description Make an http request to the /client webservice, fetching mapped associates
  * with the given marketing status.
  * @param statusId Contains the marketing status id used to fetch data
  */
  getAssociatesByStatus(statusId: number) {
    return this.http.get(environment.url + this.associatePath + '/mapped/' + statusId);
  }

  /**
  * Update the given associate's status/client
  * @param ids of associates to be updated
  */
  updateAssociates(ids: number[], uverify: string, ustatus: number, uclient: number): Observable<any> {
    let url: string = environment.url + this.associatePath + "?";
    let verifyUrl: string = (uverify ? "verified=" + uverify : "");
    let statusUrl: string = (ustatus ? "marketingStatusId=" + ustatus : "");
    let clientUrl: string = (uclient ? "clientId=" + uclient : "");

    if (uverify) {
      url += verifyUrl + (statusUrl != "" ? "&" : "");
    }

    if (ustatus) {
      url += statusUrl + (clientUrl != "" ? "&" : "");
    }

    if (uclient) {
      url += clientUrl;
    }
    return this.http.put(url, ids);
  }

  updateAssociate(associate: any) {
    let url: string = environment.url + this.associatePath + "/" + associate.id;
    return this.http.put(url, associate);
  }

  verifyAssociate(associateID: number) {
    let url: string = environment.url + this.associatePath + "/"+ associateID + "/verify";
    return this.http.put(url, associateID);
  }

  getInterviewsForAssociate(id: number): Observable<Interview[]> {
    let url: string = environment.url + this.associatePath + "/" + id + "/interviews";
    return this.http.get<Interview[]>(url);
  }

  addInterviewForAssociate(id: number, interview: any): Observable<any> {
    let url: string = environment.url + "TrackForce/api/" + "associates" + "/" + id + "/interviews";
    return this.http.post(url, interview);
  }
}
