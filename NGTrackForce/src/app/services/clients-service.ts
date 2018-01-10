import { Injectable } from "@angular/core";
import { Http } from '@angular/http';
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/map'
import { Associate } from "../../models/Associate";
import { Response } from "@angular/http/src/static_response";
import { environment } from "../../environments/environment";

@Injectable()
export class ClientService {

    status: string
    client: string

    constructor(private http: Http) {

    }

    /**
     * Fetch the names of all of the clients
     */
    getAllClientsNames() {
        let url = environment.url + "TrackForce/track/clients"

        return this.http.get(url).map((response: Response) => response.json())
    }
}