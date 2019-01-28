import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Curriculum } from "../../models/curriculum.model";
import {GraphCounts} from "../../models/graph-counts";

@Injectable()
export class CurriculumService { 
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

  /**
   *
   * Get skill set info from the back-end
   *
   * @param {number} statusID - id of the skillset
   */
  getSkillsetsForStatusID(statusID: number): Observable<GraphCounts[]> {
    return this.http.get<GraphCounts[]>((environment.url) +
      'TrackForce/skillset/unmapped/' + statusID);
  }

}
