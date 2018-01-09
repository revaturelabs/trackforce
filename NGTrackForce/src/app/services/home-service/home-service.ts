import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http/src/client";


@Injectable()
export class HomeService{
    private url: string = 'localhost:8080/TrackForce/track';

    constructor(private http: HttpClient){

    }

    getAllData(){
        return this.http.get(this.url + '/info');
    }

    emptyDatabase(){
        return this.http.get(this.url + '/database/deleteFromDB');
    }

    populateDBSF(){
        return this.http.get(this.url + '/database/populateDBSF');
    }

    populateDB(){
        return this.http.get(this.url + '/database/populateDB');
    }
}