import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { environment } from '../../environments/environment';
import { Observable } from "rxjs";

@Injectable()
export class SkillsetService {
    constructor(private http : HttpClient) {}

    getSkillsetsForStatusID(statusID : number) : Observable<any> {
        return this.http.get('/TrackForce/track/skillset/' + statusID);
    }
}