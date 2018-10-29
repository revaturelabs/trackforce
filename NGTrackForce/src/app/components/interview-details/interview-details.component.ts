import { Component, OnInit, Input } from '@angular/core';
import { MyInterviewComponent } from '../myinterview-view/myinterview-view.component';
import { ActivatedRoute } from '@angular/router';

import { InterviewService } from '../../services/interview-service/interview.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { Associate } from '../../models/associate.model';
import { Interview } from '../../models/interview.model';
import { User } from '../../models/user.model';
import { InterviewUpdate, PromptClass } from './interview-details.enum';

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
  serverResponsePending = false;
  promptClassName = "alert-success";
  promptMessage: InterviewUpdate;
  promptToggle = false;
  isDisabledAssociate = false;
  isDisabledClient = false;
  isDisabledQuestions = false;
  isDisabledSkillsAndQuestions = false;

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

  private _displayPrompt(
    serverResponsePending: boolean,
    promptClass: PromptClass,
    message: InterviewUpdate) {

      this.serverResponsePending = serverResponsePending;
      this.promptClassName = promptClass;
      this.promptMessage = message;
      this.promptToggle = true;
  }

  commitchanges() {
    this._displayPrompt(true, PromptClass.WAIT, InterviewUpdate.WAIT);
    this.interviewService.updateInterview(this.interview).subscribe(
      response => {
        this._displayPrompt(false, PromptClass.SUCCESS, InterviewUpdate.SUCCESS);
      },
      error => {
        this._displayPrompt(false, PromptClass.FAILURE, InterviewUpdate.FAILURE);
        console.error(error);
      }
    );
  }

  isDisabledAssociateFeedback() {
    this.user = this.authService.getUser();
    if ( this.authService.getUserRole() === 3 || this.authService.getUserRole() === 4 || this.authService.getUserRole() === 1)
    {
      this.isDisabledAssociate = true;
    }
    else
    {
      this.isDisabledAssociate = false;
    }
    return this.isDisabledAssociate;
  }

  isDisabledClientFeedback() {
    this.user = this.authService.getUser();
    if ( this.authService.getUserRole() === 3 || this.authService.getUserRole() === 4 || this.authService.getUserRole() === 1 )
    {
      this.isDisabledClient = false;
    }
    else
    {
      this.isDisabledClient = true;
    }
    return this.isDisabledClient;
  }

  isDisabledInterviewQuestions() {
    this.user = this.authService.getUser();
    if ( this.authService.getUserRole() === 3 || this.authService.getUserRole() === 4 || this.authService.getUserRole() === 1 )
    {
      this.isDisabledQuestions = true;
    }
    else
    {
      this.isDisabledQuestions = false;
    }
    return this.isDisabledQuestions;
  }

  isDisabledExpectedSkillsAndQuestions() {
    this.user = this.authService.getUser();
    if ( this.authService.getUserRole() === 3 || this.authService.getUserRole() === 4 || this.authService.getUserRole() === 1 )
    {
      this.isDisabledSkillsAndQuestions = false;
    }
    else
    {
      this.isDisabledSkillsAndQuestions = true;
    }
    return this.isDisabledSkillsAndQuestions;
  }

}
