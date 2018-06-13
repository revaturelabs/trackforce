import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Interview } from '../../models/interview.model';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable()
export class InterviewService {

  private associatePath: string = "TrackForce/associates";

	public myInterview:any;

  constructor(private http: HttpClient) { }
  


 public  updateinterview(interview:any, id: number){

  
    let url: string = environment.url+ this.associatePath + "/" +id + "/interviews" + "/" + interview.interviewId;
    return this.http.put(url, interview);


  }
  
  public  getInterviews(id: number): Observable<any> {
    let url: string = environment.url +"TrackForce/api/" + "associates" + "/"+id+"/interviews";
    return this.http.get(url);
  }

  /**
  * Get all of the associates
  */
  public getAllInterviews(): Observable<any> {
    let url: string = environment.url + 'TrackForce/interviews';
    return this.http.get(url);
  }
  
  public setInterview(interview:any){
	  this.myInterview = interview;
  }
  
}
