import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Interview } from '../../models/interview.model';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment';

@Injectable()
export class InterviewService {

  private associatePath = "TrackForce/associates";

  public myInterview: any;

  constructor(private http: HttpClient) { }


/**
 * This method updates an existing interview
 * Reviewed by Max
 * @since 6.18.06.08
 * 
 * @param interview - this is the updated interview object
 * @param id - this is the id of the associate
 */
  public updateinterview(interview: any, id: number) {


    let url: string = environment.url + this.associatePath + "/" + id + "/interviews" + "/" + interview.interviewId;
    return this.http.put(url, interview);


  }


  /**
   * This method gets the interviews for a specific associate
   * Reviewed by Max
   * @since 6.18.06.08
   * 
   * @param id - this is the associate's id
   */
  public getInterviews(id: number): Observable<any> {
    let url: string = environment.url + "TrackForce/api/" + "associates" + "/" + id + "/interviews";
    return this.http.get(url);
  }

  /**
  * Get all of the associates
  */
  public getAllInterviews(): Observable<any> {
    let url: string = environment.url + 'TrackForce/interviews';
    return this.http.get(url);
  }

  public setInterview(interview: any) {
    this.myInterview = interview;
  }

}
