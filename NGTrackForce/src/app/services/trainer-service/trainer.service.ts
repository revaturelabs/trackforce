import {Injectable} from "@angular/core";
import {RequestService} from "../request-service/request.service";
import {HttpClient} from "@angular/common/http";
import { environment } from '../../../environments/environment';
import {Trainer} from "../../models/trainer.model";
import {Observable} from "rxjs/Observable";
import {Batch} from "../../models/batch.model";

@Injectable()
export class TrainerService {

  private baseURL = '${environment.url}trainers/';

  constructor(private http: HttpClient) {  }

  /** Gets a trainer from Java. As a way to prevent infinite recursion,
  * it's coming in without a batch.
  * @param userId
  * @author Curtis H
  * @since 6.18.06.16
  */
  public getBatchlessTrainer(userId: number): Observable<Trainer> {
    return this.http.get<Trainer>(this.baseURL + userId);
  }

  /**Get all batches associated with a particular trainer
  * @param trainerId
  * @author Curtis H
  * @since 6.18.06.16
  */
  public getTrainerBatches(trainerId: number): Observable<Batch[]> {
    return this.http.get<Batch[]>(this.baseURL+trainerId+"/batch");
  }


  /**Get all batches in which the co-trainer is represented by trainerId
  * @param trainerId
  * @author Curtis H
  * @since 6.18.06.16
*/
  public getCoTrainerBatches(trainerId: number): Observable<Batch[]> {
    return this.http.get<Batch[]>(this.baseURL+trainerId+"/cotrainerbatch");
  }


  /** Gets a trainer, with some amount of batches they're assigned to.
   * @param userId
   * @param primary - whether or not the gotten trainer object will include batches in which the trainer is primary
   *                  if you don't include the parameter it will.
   *
   * @param cotrainer - whether or not the gotten trainer object will include the batches in which the trainer is a co
   *                  if you don't include the parameter it will.
   * @author Curtis H
   * @since 6.18.06.16
  */
  public getTrainer(userId: number, primary=true, cotrainer=true): Observable<Trainer> {
    return this.getBatchlessTrainer(userId).map(trainer => {
      if (primary) {
        this.getTrainerBatches(trainer.id).subscribe(batches => trainer.primary = batches);
      }
      if (cotrainer) {
        this.getCoTrainerBatches(trainer.id).subscribe(batches => trainer.coTrainer = batches);
      }
      return trainer;
    }).first();
  }

}
