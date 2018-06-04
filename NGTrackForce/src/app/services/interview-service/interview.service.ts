import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Interview } from '../../models/interview.model';
import { environment } from "../../../environments/environment";
import { Observable } from "rxjs/Observable";

@Injectable()
export class InterviewService {

  constructor(private http: HttpClient) { }



 public  updateinterview(interview:Interview){

    // console.log(interview.associate, interview.associateFeedback, interview.clientFeedback, interview.dateNotified,
    //   interview.dateOfInterview, interview.endClient,interview.id, interview.jobDescription,
    //   interview.questions, interview.typeId, interview.typeName)
    


  }
  
  public  getInterviews(id: number): Observable<any> {
    let url: string = environment.url +"TrackForce/api/" + "associates" + "/"+id+"/interviews";
    return this.http.get(url);
  }


  

}
