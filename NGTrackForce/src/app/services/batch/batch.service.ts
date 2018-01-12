/** @Author Princewill Ibe */

import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Batch} from '../../models/Batch';

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
   * get all batches from database by selecting a sufficiently
   * wide time range for the filter start and end date
   *
   * @returns {Observable<Batch>}
   */
  public getAllBatches() {
    const now: Date = new Date();
    // all batches will be over by then
    const yearFromNow = new Date(now.getFullYear() + 1, now.getMonth(), now.getDay());
    const epoch = new Date(0);
    console.log(epoch, yearFromNow);
    return this.getBatchesByDate(epoch, yearFromNow);
  }

}
