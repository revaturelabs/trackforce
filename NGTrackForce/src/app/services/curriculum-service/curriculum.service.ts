import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable()
export class CurriculumService {
  MOCK_API = "https://9a03ee58-6ed8-4d7b-8df1-60f505a77580.mock.pstmn.io/";
  NO_BACKEND = false;
  private curriculumPath = "TrackForce/skillset";

  /**
    *@param {HttpClient} http
    *Need to create a connection to REST endpoint
    *And initiate Http requests
    */
  constructor(private http: HttpClient) { }

  /**
    *Get skill set info from the back-end
    *
    *@param {number} statusID
    *id of the skillset
    *
     @param {RequestService}
    *@return skillset data based on id
    */
  getSkillsetsForStatusID(statusID: number): Observable<any> {
    return this.http.get(((this.NO_BACKEND) ? this.MOCK_API : environment.url) +
      'TrackForce/skillset/unmapped/' + statusID);
  }

  public getAllCurricula(): Observable<any> {
    return this.http.get<any>(environment.url + this.curriculumPath);
  }

}
