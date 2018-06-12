import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
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

    /**
     * My guess is that this method ... tbh I do not know what this method does
     * Reviewed by Max
     * @since 6.18.06.08
     * 
     * @param startTime - start date
     * @param endTime - end date
     * @param techs - unclear what this param is for
     */
    public getPrediction(startTime: number,endTime: number, techs: any) {
      // for each technology, retrieve the number of associates that will be available
      return this.http.get<any>(environment.url + this.predictionPath + startTime + "/" + endTime);
    }


    /**
     * This is used to get a specific curriculum for the prediction
     * Reviewed by Max
     * @since 6.18.06.08
     * 
     * @param startTime - start date
     * @param endTime - end date
     * @param curricula - specifies the cirricula of the batch
     */
    public getBatchesByCurricula(startTime: number,endTime: number, curricula: any) {
      // retrieves batches by curriculum and 
      //return this.http.get<any>("http://localhost:8085/TrackForce/api/batches/curriculum/jta");
      //curricula = "jta";
      return this.http.get<any>(environment.url + this.predictionGetBatches + curricula+"?start="+startTime+"&end="+endTime) ;
    }





}
