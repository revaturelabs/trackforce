import { Injectable } from "@angular/core";
import { Http } from '@angular/http';
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/map'
import { Associate } from "../models/Associate";
import { Response } from "@angular/http/src/static_response";

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
        let url: string = "http://localhost:8085/TrackForce/track/associates/all"

        return this.http.get(url).map((response: Response) => response.json());
    }

    /**
     * Update the given associates statuses/clients
     * @param ids of associates to be updated
     */
    updateAssociates(ids: number[]) {
        let url: string = "http://localhost:8085/TrackForce/track/associates/update/" +
            this.status + "/" + this.client

        return this.http.post(url, ids)
    }
}