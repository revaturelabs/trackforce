/** @Author Princewill Ibe */

import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Batch} from '../../models/batch.model';
import {Associate} from '../../models/associate.model';

@Injectable()
export class BatchService {
  private batchPath = "TrackForce/batches";

  constructor(private http: HttpClient) {}

  /**
   * given start and end date, return the batches that started and completed
   * within the time range
   *
   * @param {Date} startDate
   * @param {Date} endDate
   * @returns {Observable<Batch[]>}
   */
  public getBatchesByDate(startDate: Date, endDate: Date): Observable<Batch[]> {
    const url = environment.url + this.batchPath+ `?start=${startDate.getTime()}&end=${endDate.getTime()}`;
    //const url = environment.url + this.batchPath + '/';
    console.log(startDate.getTime());
    console.log(endDate.getTime());
    return this.http.get<Batch[]>(url);
  }

  /**
   *  This gets all of the batches
   */
  public getAllBatches(): Observable<Batch[]> {
    const url = environment.url + this.batchPath;
    return this.http.get<Batch[]>(url);
  }

  /**
   * get batches within six months of current
   *
   * @returns {Observable<Batch[]>}
   */
  public getDefaultBatches(): Observable<Batch[]> {
    const now: Date = new Date();
    // all batches will be over by then
    const monthRadius = 3;
    const threeMonthsBefore = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    threeMonthsBefore.setMonth(threeMonthsBefore.getMonth() - monthRadius);
    const threeMonthsAfter = new Date(now.getFullYear(), now.getMonth(), 28);
    threeMonthsAfter.setMonth(threeMonthsAfter.getMonth() + monthRadius);

    return this.getBatchesByDate(threeMonthsBefore, threeMonthsAfter);
  }

  /**
   * as the name suggests, fetches list of associates in the batch with given id
   * @param {number} id
   * @returns {Observable<Associate[]>}
   */
  public getAssociatesForBatch(id: number): Observable<Associate[]> {
    const url = environment.url + this.batchPath+'/'+id+'/associates';
    return this.http.get<Associate[]>(url);
  }

  public getBatchesSortedById(): Observable<any> {
    return this.http.get(this.batchPath + '?sorted=id');
  }

  public getBatchesSortedByDate(): Observable<any> {
    return this.http.get(this.batchPath + '?sorted=date');
  }

  public getBatchByType(threeMonthsBefore: number, threeMonthsAfter: number, type: string): Observable<any> {
    return this.http.get<any>(this.batchPath + '?start=' + threeMonthsBefore + '&end=' + threeMonthsAfter + '&type='+type);
  }

}
