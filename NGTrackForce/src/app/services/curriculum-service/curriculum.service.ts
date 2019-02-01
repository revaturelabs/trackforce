import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { environment } from '../../../environments/environment';
import { Observable, BehaviorSubject } from 'rxjs';
import { Curriculum } from "../../models/curriculum.model";
import {GraphCounts} from "../../models/graph-counts";
import { LocalStorageUtils } from '../../constants/local-storage';

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
    let skillsets: BehaviorSubject<Curriculum[]> = new BehaviorSubject<Curriculum[]>([]);
    let key: string = LocalStorageUtils.CACHE_CURRICULUM_ALL;

    if(!LocalStorageUtils.CACHE_ENABLED || !localStorage.getItem(key)) {
      const url: string = this.baseURL + '/allAssociates';
      this.http.get<Curriculum[]>(this.baseURL).subscribe(
        (data: Curriculum[]) => {
          skillsets.next(data);
          localStorage.setItem(key, JSON.stringify(data));
        },
        error => skillsets.error(error)
      );
    } else {
      skillsets.next(JSON.parse(localStorage.getItem(key)))
    }
          
    return skillsets;
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
