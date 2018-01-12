import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { environment } from '../../../environments/environment';
import { Observable } from "rxjs";

@Injectable()
export class SkillsetService {
    MOCK_API   = "https://9a03ee58-6ed8-4d7b-8df1-60f505a77580.mock.pstmn.io";
    NO_BACKEND = true;

    constructor(private http : HttpClient) {}

    getSkillsetsForStatusID(statusID : number) : Observable<any> {
        return this.http.get(((this.NO_BACKEND) ? this.MOCK_API : environment.url) + 
            '/TrackForce/track/skillset/' + statusID);
    }

    
}