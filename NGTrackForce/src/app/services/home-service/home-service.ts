import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http/src/client";
import { environment } from "../../../environments/environment";


@Injectable()
export class HomeService{
    private url: string = 'localhost:8080/TrackForce/track';

    constructor(private http: HttpClient){

    }

    getAllData(){
        return this.http.get(environment.url + '/info');
    }

    emptyDatabase(){
        return this.http.get(environment.url + '/database/deleteFromDB');
    }

    populateDBSF(){
        return this.http.get(environment.url + '/database/populateDBSF');
    }

    populateDB(){
        return this.http.get(environment.url + '/database/populateDB');
    }
}