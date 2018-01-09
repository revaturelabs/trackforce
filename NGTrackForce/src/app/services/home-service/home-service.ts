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
        this.http.get(environment.url + '/database/deleteFromDB');
    }

    populateDBSF(){
        this.http.get(environment.url + '/database/populateDBSF');
    }

    populateDB(){
        this.http.get(environment.url + '/database/populateDB');
    }

    cleanCache(){
        this.http.put(environment.url + '/TrackForce/track/init', {});
    }
}