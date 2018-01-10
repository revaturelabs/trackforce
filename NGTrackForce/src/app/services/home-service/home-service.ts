/**
 * Service used for the home component to manage the database
 * @author Leng Vang
 */
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from "../../../environments/environment";


@Injectable()
export class HomeService{
    private url: string = 'localhost:8080/TrackForce/track';

    constructor(private http: HttpClient){ 

    }

    //Get all data needed regarding batches 
    getAllData(){
        return this.http.get(environment.url + '/info');
    }

    //Clear database of all data
    emptyDatabase(){
        this.http.get(environment.url + '/database/deleteFromDB');
    }

    //populate the database with SalesForce data
    populateDBSF(){
        this.http.get(environment.url + '/database/populateDBSF');
    }

    //populate the database with static data
    populateDB(){
        this.http.get(environment.url + '/database/populateDB');
    }

    //cleans the cache
    cleanCache(){
        this.http.put(environment.url + '/TrackForce/track/init', {});
    }
}