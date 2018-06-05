import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Interview } from '../../models/interview.model';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment';

@Injectable()
export class InterviewService {

  constructor(private http: HttpClient) { }



 public  updateinterview(interview:Interview, id: number){

    // console.log(interview.associate, interview.associateFeedback, interview.clientFeedback, interview.dateNotified,
    //   interview.dateOfInterview, interview.endClient,interview.id, interview.jobDescription,
    //   interview.questions, interview.typeId, interview.typeName)
    let url: string = environment.url+ "TrackForce/api/" +id;
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

  

}
