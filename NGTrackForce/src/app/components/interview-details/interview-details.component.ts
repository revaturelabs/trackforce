import { Component, OnInit } from '@angular/core';
import { InterviewService } from '../../services/interview-service/interview.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';


@Component({
  selector: 'app-interview-details',
  templateUrl: './interview-details.component.html',
  styleUrls: ['./interview-details.component.css']
})
export class InterviewDetailsComponent implements OnInit {
	public assoFeedback: string;
	public clientFeedback: string;
	public questionsFeedback: string;
  constructor(private interviewService: InterviewService, private authService: AuthenticationService) { }
  //User object containing need data
  user: any;
  isAssociate: boolean=false;
  commit(){
	  
  }
  ngOnInit() {
	  
	  //gets the user from localStorage
	  this.user = this.authService.getUser();
	  if(this.user.tfRoleId === 5){
		this.isAssociate = true;
	  } else {
		this.isAssociate = false;
	  }
	  
	  console.log(this.interviewService.myInterview);
	  this.assoFeedback = this.interviewService.myInterview.tfInterviewFeedback;
	  this.clientFeedback = this.interviewService.myInterview.clientFeedback;
  }

  

}
