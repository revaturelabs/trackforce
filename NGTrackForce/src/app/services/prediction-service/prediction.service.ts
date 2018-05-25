import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable()
export class PredictionService {
  private predictionPath = "TrackForce/prediction/";
  private predictionGetBatches = "api/batches/curriculum/";

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

    public getBathcesByCurricula(startTime: number,endTime: number, curricula: any) {
      // retrieves batches by curriculum and 
      return this.http.get<any>("http://localhost:8085/TrackForce/api/batches/curriculum/java?start=1483938000001&end=1489723200001");
    }





}
