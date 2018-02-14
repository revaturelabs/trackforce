import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/map'
import { Associate } from "../../models/associate.model";
import { Response } from "@angular/http/";
import { environment } from "../../../environments/environment";
import { forEach } from "@angular/router/src/utils/collection";

/**
 * Service for retrieving and updating data relating to associates.
 * @author Alex, Xavier
 */
@Injectable()
export class AssociateService {
    private associatePath: string = "TrackForce/associates";

    status: string
    client: string

    constructor(private http: HttpClient) {}

    /**
     * Get all of the associates
     */
    getAllAssociates(): Observable<any> {
        let url: string = environment.url + this.associatePath;
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
      console.log("Inside Associate Service - getFilteredAssociates");
      console.log("statusId: " + statusId);
      return this.http.get(environment.url+this.associatePath+'/mapped/'+statusId);
    }

    /**
     * Update the given associate's status/client
     * @param ids of associates to be updated
     */
    updateAssociates(ids: number[], ustatus: number, uclient: number): Observable<any> {
        let url: string = environment.url + this.associatePath + "?";
        let statusUrl: string = (ustatus ? "marketingStatusId="+ustatus : "");
        let clientUrl: string = (uclient ? "clientId="+uclient : "");
        if(ustatus){
            url += statusUrl + (clientUrl != "" ? "&" : "");
        }

        if (uclient) {
            url += clientUrl;
        }
        return this.http.put(url, ids);
    }

    updateAssociate(id: number, ustatus: string, uclient: string) {
        let url: string = environment.url + this.associatePath + id;
        return this.http.put(url, {
          status: ustatus,
          client: uclient
        })
    }
}
