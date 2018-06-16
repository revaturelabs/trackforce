import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Curriculum } from "../../models/curriculum.model";

@Injectable()
export class CurriculumService {
  // MOCK_API = "https://9a03ee58-6ed8-4d7b-8df1-60f505a77580.mock.pstmn.io/";
  // NO_BACKEND = false;
  private baseURL: string = environment.url + "TrackForce/skillset";

  constructor(private http: HttpClient) { }

  /**
   * 
   * Gets all of the possible curriculum objects
   * (curricula? curriculums?)
   */
  public getAllCurricula(): Observable<Curriculum[]> {
    return this.http.get<Curriculum[]>(this.baseURL);
  }

  // /**
  //  * 
  //  * Get skill set info from the back-end
  //  *
  //  * @param {number} statusID - id of the skillset
  //  */
  // getSkillsetsForStatusID(statusID: number): Observable<any> {
  //   return this.http.get();
  // }


}
