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
import { Router } from '@angular/router'
import {
  InterviewStatusMsg,
  AlertClass,
  StatusProp } from './myinterview-view.enum';

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
  public newInterview: Interview;
  public formOpen = false;
  public conflictingInterviews = '';
  public interviewDate: Date;
  public interviewAssigned: Date;
  public clients: Client[];
  public typeId: number;
  public was24HRNotice: boolean;
  public associateId: Associate;
  public user: User;
  public clientSelected: any;
  public interviewType: InterviewType;
  public clientId: Client;
  public openDateNotified: boolean;
  public openInterviewDate: boolean;
  public isDataReady = false;
  public dateAssignedError: boolean;
  public dateOfInterviewError: boolean;
  public dateError: boolean;
  public updateInterviewStatus: string;
  public alertUpdateClass: AlertClass;
  public alertSubmitClass: AlertClass;
  public submitInterviewStatus: string;
  public statusProp = StatusProp;

  constructor(
    private authService: AuthenticationService,
    private associateService: AssociateService,
    private activated: ActivatedRoute,
    private interviewService: InterviewService,
    private clientService: ClientService,
    private router: Router,
  ) {}

  ngOnInit() {
    //gets the associate id from the path
    //the '+' coerces the parameter into a number
    // this.id = +this.activated.snapshot.paramMap.get('id');
    this.openDateNotified = false;
    this.openDateNotified = false;

    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.associateService.getAssociate(this.user.id).subscribe(
      data => {
        this.associate = data;
        //don't call getAssociateInterviews until data is ready
        if(this.associate.id) {
          this.getAssociateInterviews(this.associate.id);
        }
      },
      error => {
        console.log(error);
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

  getAssociateInterviews(id: number) {
    this.interviewService.getInterviewsForAssociate(id).subscribe(
      data => {
        this.interviews = data;
        this.isDataReady = true;
      },
      error => {
        console.error(error);
      }
    );
  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }

  /**
   * Displays a status message dependant on whether the request was successful or not
   * Generic method for displaying either the update status message or the submit status message
   * @param prop StatusProp enum, determines whether the message is for updating or submitting
   * SUBMIT - status of attempt to submit a new interview
   * UPDATE - status of attempt to submit a new interview
   * @param status Determines status message
   * SUCCESS - Request was recieved by back end and processed appropriately
   * WAIT - Request was sent, response from the server is still pending
   * FAILURE - Server threw an error
   * @param statusClass Determines the bootstrap styling class.
   */
  private _displayStatus(prop: StatusProp, status: InterviewStatusMsg, statusClass: AlertClass) {
    // format replaces {} with either submission or update to make the message more meaningful
    const format = (string, sub)=> string.replace('{}', sub);
    switch(prop) {
      case StatusProp.SUBMIT:
        this.alertSubmitClass = statusClass;
        this[prop] = format(status, 'submission');
        break;
      case StatusProp.UPDATE:
        this.alertUpdateClass = statusClass;
        this[prop] = format(status, 'update');
        break;
    }
  }

  /**
   * Submits a new interview
   */
  addInterview() {
      if (!this.dateAssignedError && !this.dateOfInterviewError){
        this._displayStatus(StatusProp.SUBMIT, InterviewStatusMsg.WAIT, AlertClass.WAIT);
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
          new Date(this.interviewDate),
          null,
          this.was24HRNotice ? 1 : 0,
          null,
          new Date(this.interviewAssigned).getTime(),
          new Date(this.interviewAssigned).getTime().toString()
        );

        this.interviewService
          .createInterview(this.newInterview, this.associate.id)
          .subscribe(
            res => {
              console.log(res)
              this._displayStatus(StatusProp.SUBMIT, InterviewStatusMsg.SUCCESS, AlertClass.SUCCESS);
            },
            error => {
              console.error(error)
              this._displayStatus(StatusProp.SUBMIT, InterviewStatusMsg.FAILURE, AlertClass.FAILURE);
            }
          );
      }
  }

  /**
   * Updates a specific interview
   * @param interview: interview to be updated
   */
  updateInterview(interview: Interview) {
    if (!this.dateError){
        this._displayStatus(StatusProp.UPDATE, InterviewStatusMsg.WAIT, AlertClass.WAIT);
        interview.isInterviewFlagged = +interview.isInterviewFlagged; // set it to number
        interview.interviewDate = new Date(interview.interviewDate); // convert into timestamp
        interview.dateSalesIssued = new Date(
          interview.dateAssociateIssued
        ).getTime(); // convert into timestamp
        interview.dateAssociateIssued = new Date(
          interview.dateAssociateIssued
        ).getTime();
        this.interviewService.updateInterview(interview).subscribe(
          res => {
            this._displayStatus(StatusProp.UPDATE, InterviewStatusMsg.SUCCESS, AlertClass.SUCCESS);
          },
          error => {
            this._displayStatus(StatusProp.UPDATE, InterviewStatusMsg.FAILURE, AlertClass.FAILURE);
            console.error(error);
          }
        );
    }
  }

  viewInterview(interviewId: number) {
      this.router.navigate(['/interview-details', interviewId]);
  }

  /**
   Function to search for conflicting interviews.
   This function is called once for every row in the
   "My Interviews" table. If it returns true, the date
   cell is colored red to highlight the conflict.
   THIS FUNCTION IS VERY USEFUL BUT IT IS NOT BEING USED // Fixed by batch 1806
  */
  highlightInterviewConflicts(interview: number) {
    const checkDate = new Date(this.interviews[interview].interviewDate);
    const thereIsConflict = (index)=> {
      return new Date(this.interviews[index].interviewDate)
        .getTime() === checkDate.getTime()
        && index !== interview
    };
    for (let i = 0; i < this.interviews.length; i++) {
      if (thereIsConflict(i)) {
        this._displayStatus(StatusProp.UPDATE, InterviewStatusMsg.CONFLICT, AlertClass.FAILURE);
        return true;
      }
    }
    return false;
  }

  /**
   * Check that earlier dates are in fact earlier dates
   * and make sure the dates are later than or equal to today.
   */
  private _validateDates(date1: Date, date2: Date): boolean {
    if(!this._datesAfterToday(date1) || !this._datesAfterToday(date2)) {
      return false;
    }
    return date1 < date2;
  }

  /**
   * Ensure the given dates are after today
   */
  private _datesAfterToday(date: Date) {
    return new Date() < date;
  }

  /**
   * Data bound to disable button
   * Enables the button only if the information in the form is valid
   */
  validateNewInterviewForm(): boolean {
    // const datesAreValid = this._validateDates(this.interviewAssigned, this.interviewDate);
    const datesAreValid = this.interviewAssigned !== undefined && this.interviewDate !== undefined
                          && !this.dateAssignedError && !this.dateOfInterviewError;
    const clientIsValid = this.clientId !== null && this.clientId !== undefined;
    // using coercion to check if the value of typeId is not zero.  tslint disabled on purpose
    // tslint:disable-next-line:triple-equals
    const typeIsValid = this.typeId !== null && this.typeId !== undefined && this.typeId != 0;
    const was24HRselected = this.was24HRNotice !== undefined;

    return datesAreValid && clientIsValid && typeIsValid && was24HRselected;
  }

  /**
   * Ensures the 24 hour notice was at least selected.
   */
  twentyFourHourNotice() {
    this.was24HRNotice = Boolean((<HTMLInputElement>event.target).value);
  }

  /**
   * Hides the update/submit statuses
   */
  closeStatus(prop: StatusProp) {
    this[prop] = null;
  }

  // ============================================
  // THIS NEEDS TO BE IMPLEMENTED
  // ============================================
  saveInterview(interview: Interview) {}
}
