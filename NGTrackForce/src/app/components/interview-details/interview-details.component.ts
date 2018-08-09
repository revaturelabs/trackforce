
import { Component, OnInit, Input } from '@angular/core';
import { MyInterviewComponent } from '../../components/myinterview-view/myinterview-view.component';
import { ActivatedRoute } from '@angular/router';

import { InterviewService } from '../../services/interview-service/interview.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { Associate } from '../../models/associate.model';

@Component({
  selector: 'app-interview-details',
  templateUrl: './interview-details.component.html',
  styleUrls: ['./interview-details.component.css']
})
export class InterviewDetailsComponent implements OnInit {

  public interview: any = {};
  public associate: Associate;
  
  constructor(private activated: ActivatedRoute) { }


  ngOnInit() {
     
    // this.i = +this.activated.snapshot.paramMap.get('i');
    // this.id = +this.activated.snapshot.paramMap.get('id');
    const u = JSON.parse(sessionStorage.getItem("interviews"));
    if(sessionStorage.getItem("changedin") === null)
      {
    // this.interview = u[this.i];
    }
    else
      {
      this.interview = JSON.parse(sessionStorage.getItem("changedin"));
    }

  //User object containing need data

  }

  commitchanges()
  {
    this
    sessionStorage.setItem("changedin", JSON.stringify(this.interview));
  }




}
