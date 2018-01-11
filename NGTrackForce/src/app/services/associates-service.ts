import { Injectable } from "@angular/core";
import { Http } from '@angular/http';
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/map'
import { Associate } from "../../models/Associate";
import { Response } from "@angular/http/src/static_response";
import { environment } from "../../environments/environment";

/**
 * Service for retrieving and updating data relating to associates.
 * @author Alex, Xavier
 */
@Injectable()
export class AssociateService {

    status: string
    client: string

    constructor(private http: Http) {

    }

    /**
     * Get all of the associates
     */
    getAllAssociates() {
        let url: string = environment.url + "TrackForce/track/associates/all"

        return this.http.get(url).map((response: Response) => response.json());
    }

    getAssociate(id: number) {
        let url: string = environment.url + "TrackForce/track/associates/" + id

        return this.http.get(url).map((response: Response) => response.json());
    }

    /**
     * Update the given associates statuses/clients
     * @param ids of associates to be updated
     */
    updateAssociates(ids: number[], ustatus: string, uclient: string) {
        console.log("service");
        let url: string = environment.url + "TrackForce/track/associates/update/" + ustatus + "/" + uclient
        
        this.http.put(url, ids).subscribe()
    }

    updateAssociate(id: number, ustatus: string, uclient: string) {
        console.log("service");
        let url: string = environment.url + "TrackForce/track/associates/"+id+"/update/" + ustatus + "/" + uclient;
        console.log(url);
        
        this.http.get(url).subscribe()
    }
}