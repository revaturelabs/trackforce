import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Interview } from '../../models/interview.model';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable()
export class InterviewService {

  private baseURL: string = environment.url + 'TrackForce/interviews';

  constructor(private http: HttpClient) { }

  /**
   * 
   * Get all of the interviews, every single one
   */
  public getAllInterviews(): Observable<Interview[]> {
    return this.http.get<Interview[]>(this.baseURL);
  }


  /**
   * 
   * This method will create a new interview for an associate
   * @param interview - the new interview object
   * @param id - associate id
   * 
   */
  public createInterview(interview: Interview, id: number): Observable<boolean> {
    const url: string = this.baseURL + "/" + id;
    return this.http.post<boolean>(url, interview);
  }

  /**
   * 
   * This method gets all the interviews for a specific associate
   * Reviewed by Max
   * @since 6.18.06.08
   * 
   * @param id - this is the associate's id
   */
  public getInterviews(id: number): Observable<Interview[]> {
    const url: string = this.baseURL + "/" + id;
    return this.http.get<Interview[]>(url);
  }

  /**
   * 
   * This method updates an existing interview
   * Reviewed by Max
   * @since 6.18.06.08
   * 
   * @param interview - this is the updated interview object
   * @param id - this is the id of the associate
   */
  public updateinterview(interview: any, id: number): Observable<boolean> {
    const url: string = this.baseURL + "/" + id + "/interviews" + "/" + interview.interviewId;
    return this.http.put<boolean>(url, interview);
  }

}
