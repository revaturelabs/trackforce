import { Component, OnInit } from '@angular/core';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';

import { Interview } from '../../models/interview.model';
import { InterviewService } from '../../services/interview-service/interview.service';


/**
*@description This is the view for associates only
*/
@Component({
  selector: 'app-interviews-view',
  templateUrl: './interviews-view.component.html',
  styleUrls: ['./interviews-view.component.css']
})
@AutoUnsubscribe
export class InterviewsComponent implements OnInit {

  public interviews;

  constructor(
    private interviewService: InterviewService  
  ) { }

  ngOnInit() {
    this.getInterviews();
  }

  getInterviews() {
    this.interviewService.getAllInterviews().subscribe(
     data => {
       console.log(data);
        this.interviews = data;
      }
    )
  } 


}
