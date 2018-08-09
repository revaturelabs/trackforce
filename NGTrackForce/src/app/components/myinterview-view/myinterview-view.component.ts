import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Associate } from '../../models/associate.model';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from '../../services/client-service/client.service';
import { Interview } from '../../models/interview.model';
import { InterviewService } from '../../services/interview-service/interview.service';
import { Client } from '../../models/client.model';
import { User } from '../../models/user.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { InterviewType } from '../../models/interview-type';

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
  public interviews: Interview[];
  public associate: Associate;
  // public id = 0;
  public newInterview: Interview;
  public formOpen = false;
  public conflictingInterviews = '';
  public interviewDate: Date = new Date();
  public interviewAssigned: Date = new Date();
  public clients: Client[];
  public typeId: number;
  public was24HRNotice: any;
  public associateId: Associate;
  public user: User;
  public id: number;
  public clientSelected: any;
  public interviewType: InterviewType;
  public clientId: Client;

  constructor(
    private authService: AuthenticationService,
    private associateService: AssociateService,
    private activated: ActivatedRoute,
    private interviewService: InterviewService,
    private clientService: ClientService
  ) {}

  ngOnInit() {
    //gets the associate id from the path
    //the '+' coerces the parameter into a number
    // this.id = +this.activated.snapshot.paramMap.get('id');

    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.id = this.user.id;
    this.associateService.getByAssociateId(this.id).subscribe(
      data => {
        this.associate = data;
        this.getAssociateInterviews(this.associate.id);
      },
      error => {
        console.log('error');
      }
    );

    /* this.interviews = <Interview[]>(
      JSON.parse(localStorage.getItem('currentInterviews'))
    ); */

    this.clientService.getAllClients().subscribe(
      data => {
        this.clients = data;
      },
      error => {
        console.log('error');
      }
    );
  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }

  addInterview() {
    switch (+this.typeId) {
      case 1:
        this.interviewType = new InterviewType(1, 'Phone');
        break;
      case 2:
        this.interviewType = new InterviewType(2, 'Online');
        break;
      case 3:
        this.interviewType = new InterviewType(3, 'On Site');
        break;
      case 4:
        this.interviewType = new InterviewType(4, 'Skype');
        break;
      default:
        this.interviewType = new InterviewType(5, 'Other');
        break;
    }

    this.newInterview = new Interview(
      this.associate,
      this.clientId,
      this.interviewType,
      new Date(this.interviewDate).getTime(),
      null,
      this.was24HRNotice ? 1 : 0,
      null,
      new Date(this.interviewAssigned).getTime(),
      new Date(this.interviewAssigned).getTime()
    );

    this.interviewService
      .createInterview(this.newInterview, this.associate.id)
      .subscribe();

    location.reload();
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

   THIS FUNCTION IS VERY USEFUL BUT IT IS NOT BEING USED // Fixed by batch 1806
  */
  highlightInterviewConflicts(interview: number) {
    const checkDate = new Date(this.interviews[interview].interviewDate);
    for (let i = 0; i < this.interviews.length; i++) {
      if (
        new Date(this.interviews[i].interviewDate).getTime() ===
          checkDate.getTime() &&
        i !== interview
      ) {
        this.conflictingInterviews =
          'The highlighted interviews are conflicting.' +
          'They are both scheduled at the same time!';
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

  getAssociateInterviews(id: number) {
    this.interviewService.getInterviewsForAssociate(id).subscribe(
      data => {
        this.interviews = data;
      },
      error => {
        console.log('error');
      }
    );
  }

  // ===========================================
  // THIS NEEDS TO BE IMPLEMENTED
  // ============================================
  saveInterview(interview: Interview) {}

  // THIS METHOD IS REPLACED BY STORING THE CLIENTS IN LOCAL STORAGE
  // getClientNames() {
  //   this.clientService.getAllClients().subscribe(data => {
  //     this.clients = data;
  //   });
  // }
}
