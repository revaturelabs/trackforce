import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Associate } from '../../models/associate.model';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from '../../services/client-service/client.service';
import { Interview } from '../../models/interview.model';
import { InterviewService } from '../../services/interview-service/interview.service';
import { Client } from '../../models/client.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';

/**
*@author Katherine Obioha, Andrew Ahn
*
*@description This is the view for associates only
*
*/

@Component({
  selector: 'app-myinterview-view',
  templateUrl: './myinterview-view.component.html',
  styleUrls: ['./myinterview-view.component.css']
})

@AutoUnsubscribe
export class MyInterviewComponent implements OnInit {
  public interviews: Array<Interview>;
  public associate: Associate;
  // public id = 0;
  public newInterview: Interview;
  public formOpen = false;
  public conflictingInterviews = "";
  public interviewDate: Date = new Date();
  public interviewDateNotification: Date = new Date();
  public clients: Client[];

  constructor(
    private authService: AuthenticationService,
    private associateService: AssociateService,
    private activated: ActivatedRoute,
    private interviewService: InterviewService,
    private clientService: ClientService
  ) { }


  ngOnInit() {
    //gets the associate id from the path
    //the '+' coerces the parameter into a number
    // this.id = +this.activated.snapshot.paramMap.get('id');
    this.associate = this.authService.getAssociate();
    this.interviews = <Interview[]> JSON.parse(localStorage.getItem('currentInterviews'));
    this.clients = <Client[]> JSON.parse(localStorage.getItem('currentCliets'));

    // this.getInterviews(this.associate.id);
    // this.getAssociate(this.associate.id);
    // this.getClientNames();
  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }

  addInterview() {
    //this.newInterview.associateId = this.id
    this.newInterview.interviewDate = new Date(this.interviewDate).getTime()
    this.newInterview.dateAssociateIssued = new Date(this.interviewDateNotification).getTime()
    this.newInterview.jobDescription = "none available";
    this.newInterview.isInterviewFlagged = 0;
    this.newInterview.was24HRNotice = (this.newInterview.was24HRNotice * 1)
    // this.associateService.addInterviewForAssociate(this.associate.id, this.newInterview).subscribe(
    //   data => {
    //     // this.getInterviews(this.associate.id);
    //   },
    //   err => {
    //   }
    // );
  }

  // ========================================================================
  // COMMENTED OUT BECAUSE AFTER REFACTORING THE MODELS THIS BROKE
  // FIX THIS 
  // ========================================================================
  // updateInterview(id: number) {
  //   if (sessionStorage.getItem("changedin") === null) {
  //     const updateinterview = this.interviews[id];
  //     var interview: any = {
  //       clientFeedback: updateinterview.clientFeedback,
  //       dateAssociateIssued: new Date(updateinterview.dateAssociateIssued).getTime,
  //       dateSalesTeamIssued: null,
  //       reasonForFlag: null,
  //       interviewId: updateinterview.id,
  //       jobDescription: updateinterview.jobDescription,
  //       // tfClientName :updateinterview.client,
  //       interviewDate: new Date(updateinterview.interviewDate).getTime,
  //       //was24HRNotice:updateinterview.Flag,
  //       interviewFeedback: updateinterview.interviewFeedback,
  //       clientId: 9,
  //       typeId: updateinterview.typeId
  //     }
  //   }
  //   else {
  //     let u = JSON.parse(sessionStorage.getItem("changedin"));
  //     const updateinterview = this.interviews[id];
  //     updateinterview.clientFeedback = u.CFeedback;
  //     updateinterview.associateFeedback = u.AFeedback;
  //     var interview: any = {
  //       clientFeedback: updateinterview.CFeedback,
  //       dateAssociateIssued: new Date(updateinterview.date).getTime,
  //       interviewId: updateinterview.id,
  //       jobDescription: updateinterview.JDescription,
  //       interviewDate: new Date(updateinterview.DInterview).getTime,
  //       //was24HRNotice:updateinterview.Flag,
  //       interviewFeedback: updateinterview.AFeedback,
  //       clientId: 9,
  //       typeId: updateinterview.typeID
  //     }
  //     sessionStorage.clear();
  //   }
  //   this.interviewService.updateinterview(interview, this.associate.id).subscribe(
  //     data => {
  //       // this.getInterviews(this.id);
  //     },
  //     err => {
  //     }
  //   );
  // }








  // THIS FUNCTION IS REPLACED BY STORING ALL INTERVIEWS FOR THE LOGGED IN ASSOCIATE IN LOCAL STORAGE
  // getInterviews(id: number) {
  //   this.interviewService.getInterviews(id).subscribe(
  //     data => {
  //       let tempArr = [];
  //       for (let i = 0; i < data.length; i++) {
  //         let interview = data[i];
  //         let intObj = {
  //           id: interview.id,
  //           client: interview.tfClientName,
  //           DInterview: new Date(interview.tfInterviewDate),
  //           type: interview.typeName,
  //           AFeedback: interview.tfInterviewFeedback,
  //           JDescription: interview.jobDescription,
  //           date: new Date(interview.dateAssociateIssued),
  //           CFeedback: interview.clientFeedback,
  //           typeID: interview.typeId,
  //           Flag: interview.isInterviewFlagged,
  //         }
  //         tempArr.push(intObj);
  //       }
  //       sessionStorage.setItem("interviews", JSON.stringify(this.interviews));
  //     }
  //   )
  // }

  /**
   Function to search for conflicting interviews.
   This function is called once for every row in the
   "My Interviews" table. If it returns true, the date
   cell is colored red to highlight the conflict.

   THIS FUNCTION IS VERY USEFUL BUT IT IS NOT BEING USED
  */
  highlightInterviewConflicts(interview: number) {
    const checkDate = new Date(this.interviews[interview].interviewDate);
    for (let i = 0; i < this.interviews.length; i++) {
      if (new Date(this.interviews[i].interviewDate).getTime() === checkDate.getTime() && i !== interview) {
        this.conflictingInterviews = "The highlighted interviews are conflicting." +
          "They are both scheduled at the same time!";
        return true;
      }
    }
    return false;
  }

  // THIS METHOD IS REPLACED BY STORING THE ASSOCIATE IN LOCAL STORAGE
  // getAssociate(id: number) {
  //   this.associateService.getAssociate(id).subscribe(
  //     data => {
  //       this.associate = data;
  //     },
  //     err => {
  //     });
  // }

  showInputDate(interview, dateVal) {
    if (!interview.isEditingAvailable) {
      interview.isEditingAvailable = true;
    } else {
      if (dateVal) {
        interview.DInterview = dateVal;
      }
      interview.isEditingAvailable = false;
    }
  }

  showAvailableDate(interview, dateVal) {
    if (!interview.isDateAvailable) {
      interview.isDateAvailable = true;
    } else {
      if (dateVal) {
        interview.date = dateVal;
      }
      interview.isDateAvailable = false;
    }
  }

  // ===========================================
  // THIS NEEDS TO BE IMPLEMENTED
  // ============================================
  saveInterview(interview: Interview) {

  }

  // THIS METHOD IS REPLACED BY STORING THE CLIENTS IN LOCAL STORAGE
  // getClientNames() {
  //   this.clientService.getAllClients().subscribe(data => {
  //     this.clients = data;
  //   });
  // }

}
