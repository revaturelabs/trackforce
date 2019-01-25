import { Injectable } from "@angular/core";
import { RequestService } from "../request-service/request.service";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from '../../../environments/environment';
import { Trainer } from "../../models/trainer.model";
import { Observable } from "rxjs/Observable";
import { Batch } from "../../models/batch.model";

@Injectable()
export class TrainerService {

  private baseURL = `${environment.url}TrackForce/trainers/`;

  constructor(private http: HttpClient) { }

  /**
   *
   * Gets a trainer from Java. As a way to prevent infinite recursion,
   * it's coming in without a batch.
   * @param userId
   * @author Curtis H
   * @since 6.18.06.16
   */
  public getTrainer(userId: number): Observable<Trainer> {
    return this.http.get<Trainer>(this.baseURL + userId);
  }

  //
  /**
   *
   * Get all batches associated with a particular trainer
   * @param trainerId
   * @author Curtis H
   * @since 6.18.06.16
   */
  public getTrainerBatches(trainerId: number): Observable<Batch[]> {
    return this.http.get<Batch[]>(this.baseURL + trainerId + "/batch");
  }


  /**
   * Gets all trainers
   * @author Adam L
   * @since 6.19.06.16
   */
  getAllTrainers(): Observable<Trainer[]> {
    return this.http.get<Trainer[]>(this.baseURL + "allTrainers");
  }

  /**
   *
   * Get all batches in which the co-trainer is represented by trainerId
   * @param trainerId
   * @author Curtis H
   * @since 6.18.06.16
   */
  public getCoTrainerBatches(trainerId: number): Observable<Batch[]> {
    return this.http.get<Batch[]>(this.baseURL + trainerId + "/cotrainerbatch");
  }


  public updateTrainer(trainer: Trainer): any {
    return this.http.put(this.baseURL + "update/" + trainer.id, JSON.stringify(trainer), {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `${trainer.user.token}`
      })
    });
  }

}
