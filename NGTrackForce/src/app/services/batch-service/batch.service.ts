/** @Author Princewill Ibe */

import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Batch } from '../../models/batch.model';
import { Associate } from '../../models/associate.model';

@Injectable()
export class BatchService {

  private baseURL: string = environment.url + "TrackForce/batches";

  constructor(private http: HttpClient) { }

  /**
   *  This gets all of the batches, every single one
   */
  public getAllBatches(): Observable<Batch[]> {
    const url = this.baseURL;
    return this.http.get<Batch[]>(url);
  }

  /**
   * Given start and end date, return the batches that started and completed
   * within the time range
   *
   * @param {Date} startDate - needs to be in long time
   * @param {Date} endDate - needs to be in long time
   * @returns {Observable<Batch[]>}
   */
  public getBatchesByDate(startDate: Date, endDate: Date): Observable<Batch[]> {
    const url = this.baseURL + `?start=${startDate.getTime()}&end=${endDate.getTime()}`;
    return this.http.get<Batch[]>(url);
  }

  /**
   * This method will get a list of associates in the batch with given id
   * @param {number} id - the id of the batch you want the assciates for
   * @returns {Observable<Associate[]>}
   */
  public getAssociatesForBatch(id: number): Observable<Associate[]> {
    const url = this.baseURL + '/' + id + '/associates';
    return this.http.get<Associate[]>(url);
  }




  // // ============================================================================
  // // Not in the batch resource

  // /**
  //  * To save time, only retrieves the batches between 
  //  * three months before the currrent date 
  //  * and three months after the current date.
  //  *
  //  * @returns {Observable<Batch[]>}
  //  */
  // public getDefaultBatches(): Observable<Batch[]> {
  //   const now: Date = new Date();
  //   // all batches will be over by then
  //   const monthRadius = 3;
  //   const threeMonthsBefore = new Date(now.getFullYear(), now.getMonth(), now.getDate());
  //   threeMonthsBefore.setMonth(threeMonthsBefore.getMonth() - monthRadius);
  //   const threeMonthsAfter = new Date(now.getFullYear(), now.getMonth(), 28);
  //   threeMonthsAfter.setMonth(threeMonthsAfter.getMonth() + monthRadius);
  //   return this.getBatchesByDate(threeMonthsBefore, threeMonthsAfter);
  // }


  // public getBatchesSortedById(): Observable<Batch[]> {
  //   return this.http.get<Batch[]>(this.baseURL + '?sorted=id');
  // }

  // public getBatchesSortedByDate(): Observable<Batch[]> {
  //   return this.http.get<Batch[]>(this.baseURL + '?sorted=date');
  // }

  // public getBatchByType(threeMonthsBefore: number, threeMonthsAfter: number, type: string): Observable<Batch[]> {
  //   return this.http.get<Batch[]>(this.baseURL + '?start=' + threeMonthsBefore + '&end=' + threeMonthsAfter + '&type=' + type);
  // }

}
