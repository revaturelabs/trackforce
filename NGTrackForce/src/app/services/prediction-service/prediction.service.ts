import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable()
export class PredictionService {
  private predictionPath = "TrackForce/prediction/";
  private predictionGetBatches = "TrackForce/api/batches/curriculum/";

    /**
    * @constructor
    * @param {RequestService}
    * Service for handling all requests to the server
    */
    constructor(private http: HttpClient) {}

    public getPrediction(startTime: number,endTime: number, techs: any) {
      // for each technology, retrieve the number of associates that will be available
      return this.http.get<any>(environment.url + this.predictionPath + startTime + "/" + endTime);
    }

    public getBatchesByCurricula(startTime: number,endTime: number, curricula: any) {
      // retrieves batches by curriculum and 
      //return this.http.get<any>("http://localhost:8085/TrackForce/api/batches/curriculum/jta");
      //curricula = "jta";
      return this.http.get<any>(environment.url + this.predictionGetBatches + curricula+"?start="+startTime+"&end="+endTime) ;
    }





}
