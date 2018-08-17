
import { Component, OnInit, Input } from '@angular/core';
import { MyInterviewComponent } from '../myinterview-view/myinterview-view.component';
import { ActivatedRoute } from '@angular/router';

import { InterviewService } from '../../services/interview-service/interview.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { Associate } from '../../models/associate.model';
import { Interview } from '../../models/interview.model';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-interview-details',
  templateUrl: './interview-details.component.html',
  styleUrls: ['./interview-details.component.css']
})
export class InterviewDetailsComponent implements OnInit {

  user: User;
  public interview: Interview;
  public associate: Associate;
  isDataReady: boolean;
  isDataEmpty: boolean;
  promptClassName: string = "col-sm-4 alert alert-success";
  promptMessage: string = "Succes-interview updated";
  promptToggle: boolean = false;
  isEditAss : boolean = false;
  isDisAss : boolean = false;
  isEditCli : boolean = false;
  isDisCli : boolean = false;
  isEditQue : boolean = false;
  isDisQue : boolean = false;
  
  constructor(private route: ActivatedRoute, private interviewService: InterviewService,
    private authService: AuthenticationService) { }


  ngOnInit() {
    this.route.params.subscribe(params =>{
      const interviewId: number = +params['id'];
      this.isDataReady = false;

      this.interviewService.getInterviewById(interviewId)
        .subscribe(data =>{
          this.interview = data;
          this.isDataEmpty = this.interview == null;
          this.isDataReady = true;
        });
    });
  }

  commitchanges()
  {
    this.promptToggle = false;
    this.interviewService.updateInterview(this.interview).subscribe(
      response => {
        this.promptClassName = "col-sm-4 alert alert-success";
        this.promptMessage = "Success-interview updated";
        this.promptToggle = true;
      }, 
      error => {
        this.promptClassName = "col-sm-4 alert alert-danger";
        this.promptMessage = "Failed-interview not updated";
        this.promptToggle = true;
        console.log("Error: ",error);
      }
    );
  }

  isDisabledAssFeed()
  {
    this.user = this.authService.getUser();
    if ( this.user.role == 3 )
    {
      this.isDisAss = true;
    }
    else
    {
      this.isDisAss = false;
    }
    return this.isDisAss;
  }

  isDisabledCliFeed()
  {
    this.user = this.authService.getUser();
    if ( this.user.role == 3 )
    {
      this.isDisCli = false;
    }
    else
    {
      this.isDisCli = true;
    }
    return this.isDisCli;
  }

  isDisabledIntQue()
  {
    this.user = this.authService.getUser();
    if ( this.user.role == 3 )
    {
      this.isDisQue = true;
    }
    else
    {
      this.isDisQue = false;
    }
    return this.isDisQue;
  }

  isDisabledExpectedSkillsAndQue()
  {
    this.user = this.authService.getUser();
    if ( this.user.role == 3 )
    {
      this.isDisQue = false;
    }
    else
    {
      this.isDisQue = true;
    }
    return this.isDisQue;
  }

}
