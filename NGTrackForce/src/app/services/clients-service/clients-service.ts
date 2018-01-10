import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/map'
import { Associate } from "../../models/Associate";
import { Response } from "@angular/http/src/static_response";
import { HttpClient } from "@angular/common/http/";

@Injectable()
export class ClientService {
    private url: string = 'localhost:8080/TrackForce/track';

    constructor(private http: HttpClient) {

    }

    /**
     * Fetch the names of all of the clients
     */
    getAllClientsNames(): Observable<any> {
        return this.http.get(this.url + '/clients');
    }

    getAllClients(): Observable<any> {
        return this.http.get(this.url + '/info');
    }

    getOneClient(clientName: string) {
        return this.http.get(this.url + '/clients/' + clientName);
    }

}
