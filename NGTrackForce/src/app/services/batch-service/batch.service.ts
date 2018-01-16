/** @Author Princewill Ibe */

import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Batch} from '../../models/batch.model';
import {Associate} from '../../models/associate.model';

@Injectable()
export class BatchService {

  constructor(private http: HttpClient) {
  }

  /**
   * given start and end date, return the batches that started and completed
   * within the time range
   *
   * @param {Date} startDate
   * @param {Date} endDate
   * @returns {Observable<Batch[]>}
   */
  public getBatchesByDate(startDate: Date, endDate: Date): Observable<Batch[]> {
    const url = environment.url + `TrackForce/track/batches/${startDate.getTime()}/${endDate.getTime()}`;
    return this.http.get<Batch[]>(url, {withCredentials: true});
  }

  /**
   * get batches within six months of current
   *
   * @returns {Observable<Batch[]>}
   */
  public getDefaultBatches(): Observable<Batch[]> {
    const now: Date = new Date();
    // all batches will be over by then
    const monthRadius = 6;
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
    const url = environment.url + 'TrackForce/track/batches/' + id + '/associates';
    return this.http.get<Associate[]>(url);
  }

}
