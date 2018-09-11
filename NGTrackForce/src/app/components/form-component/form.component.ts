import { Component, OnInit, Input } from '@angular/core';
import { DatePipe } from '@angular/common';
import { AssociateService } from '../../services/associate-service/associate.service';
import { Associate } from '../../models/associate.model';
import { ClientService } from '../../services/client-service/client.service';
import { Client } from '../../models/client.model';
import { element } from 'protractor';
import { ActivatedRoute } from '@angular/router';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { User } from '../../models/user.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { Interview } from '../../models/interview.model';
import { InterviewService } from '../../services/interview-service/interview.service';
import { HttpClient } from '@angular/common/http';
import { Router, NavigationExtras } from '@angular/router';
import { MarketingStatus } from '../../models/marketing-status.model';
import { FormStatus, StatusProp, StatusClass } from './form.enum';

/**
 * Component for viewing an individual associate and editing as admin.
 */
@Component({
  selector: 'app-form-comp',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
/** Decorator for automatically unsubscribing all observables upon ngDestory()
 * Prevents memory leaks
 */
@AutoUnsubscribe
export class FormComponent implements OnInit {
  user: User;
  associate: Associate;
  clients: Client[];
  interviews: Interview[];

  newStartDate: Date;
  successMessage = '';
  errorMessage = '';
  selectedVerificationStatus: string;
  selectedMarketingStatus: MarketingStatus;
  selectedClient: number;
  id: number;
  formOpen: boolean;
  isVP: boolean;
  isAssociate: boolean;
  iid: number;

  // form booleans
  // isVerified: string;
  /**
   * isApproved: 0 unitialized, 1 true, 2 false
   */
  isApproved = 0;
  isMapped: boolean;
  eligibleForInterview: boolean;
  interviewScheduled: boolean;
  clearedAllInterviews: boolean;
  receivedEmailFromClient: boolean;
  passedBackgroundCheck: boolean;
  hasStartDate: boolean;
  public isDataReady = false;

  //loading booleans
  approvalPending = false;
  associateIsLoaded = false;
  interviewsLoading = true;

  //disable the submit button if we're waiting for a server response
  submitDisabled = false;

  formStatus: FormStatus;
  approveStatus: FormStatus;
  formStatusClass: string;
  approveStatusClass: string;

  associateId: number;
  private sub: any;

  public interviewSelected: Interview = null;
  public feedback: string;

  /**
   *@param {AssociateService} associateService
   * Service for grabbing associate data from the back-end
   */
  constructor(
    private associateService: AssociateService,
    private clientService: ClientService,
    private authService: AuthenticationService,
    private interviewService: InterviewService,
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router
  ) {
    this.interviews = [];
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id']; // (+) converts string 'id' to a number
      this.associateService.getByAssociateId(this.id).subscribe(
        data => {
          // TODO: Once the code is ready to use AsyncSubject Remove this check
          if (!data.firstName) {
            return;
          }
          this.associate = data;
          this.associateIsLoaded = this.associate.user !== undefined && this.associate.user !== null;
          this.getAssociateInterviews(this.associate.id);
        },
        error => {
          console.error(error);
        }
      );
    });

    this.user = this.authService.getUser();
    // this.isVerified = this.user.verified;
    //Role checks
    if (this.user.role === 3) {
      this.isVP = true;
    } else if (this.user.role === 5) {
      this.isAssociate = true;
    } else {
      this.isVP = false;
      this.isAssociate = false;
    }

    this.clientService.getAllClients().subscribe(data => {
      this.clients = data.sort(
        (a: Client, b: Client) =>
          a.name.toLowerCase() < b.name.toLowerCase() ? -1 : 1
      );
    });
  }

  adjustDate(date: any) {
    // dates are off by 1 day - this corrects them
    const ldate = new Date(date);
    const origDate = ldate.getDate();
    ldate.setDate(origDate + 1);
    if (ldate.getDate() < 1) {
      ldate.setMonth(ldate.getMonth() - 1);
      ldate.setDate(origDate);
    }
    return ldate;
  }

  /**
   * Recieves a promise from the associate service that either resolves:
   * true - approval was recieved and successfully processed by server
   * error - server threw an error
   *
   * Disables the approval button if server response is pending
   */
  approveAssociate() {
    this.approvalPending = true;
    this._displayFormStatus(FormStatus.WAIT, StatusProp.APPROVE, StatusClass.APROVE_CLASS);
    return this.associateService
      .approveAssociate(this.associate.id)
      .then(
        data => {
          this.isApproved = 1;
          this.approvalPending = false;
          this._displayFormStatus(FormStatus.SUCCESS, StatusProp.APPROVE, StatusClass.APROVE_CLASS);
        }, //1 for true, 2 for false, 0 for initial state
      ).catch(error=> {
        console.error(error)
        this.isApproved = 2;
        this.approvalPending = false;
        this._displayFormStatus(FormStatus.FAILURE, StatusProp.APPROVE, StatusClass.APROVE_CLASS);
      });
  }

  /**
   * Allows user to hide the status message on click
   */
  closeApprovalStatus() {
    this.approveStatus = null;
  }

  processForm() {
    if (this.hasStartDate) {
      if (Date.now() < new Date(this.newStartDate).getTime()) {
        // if start date is before today, set status to MAPPED: DEPLOYED
        this.selectedMarketingStatus = new MarketingStatus(5, 'MAPPED: DEPLOYED');
      } else {
        // if start date is after today, set status to MAPPED: CONFIRMED
        this.selectedMarketingStatus = new MarketingStatus(4, 'MAPPED: CONFIRMED');
      }
    } else if (this.passedBackgroundCheck && this.hasStartDate) {
      // if background check is passed and associate has start date, set status to MAPPED: CONFIRMED
      this.selectedMarketingStatus = new MarketingStatus(4, 'MAPPED: CONFIRMED');
    } else if (this.clearedAllInterviews) {
      // if interviews are cleared, set status to MAPPED: SELECTED
      this.selectedMarketingStatus = new MarketingStatus(3, 'MAPPED: SELECTED');
    } else if (this.interviewScheduled) {
      // if an interview is scheduled, set status to MAPPED: RESERVED
      this.selectedMarketingStatus = new MarketingStatus(2, 'MAPPED: RESERVED');
    } else if (this.eligibleForInterview) {
      if (this.isMapped) {
        // if associate is mapped and eligible for an interview, set status to MAPPED: TRAINING
        this.selectedMarketingStatus = new MarketingStatus(1, 'MAPPED: TRAINING');
      } else {
        // if associate is NOT mapped, set status to UNMAPPED: TRAINING
        this.selectedMarketingStatus = new MarketingStatus(6, 'UNMAPPED: TRAINING');
      }
    } else if (this.isMapped) {
      // if associate is mapped, set status to MAPPED: TRAINING
      this.selectedMarketingStatus = new MarketingStatus(1, 'MAPPED: TRAINING');
    } else {
      // associate is unmapped
      // set status to UNMAPPED: TRAINING
      this.selectedMarketingStatus = new MarketingStatus(6, 'UNMAPPED: TRAINING');
    }
    this.updateAssociate();
  }

  /**
   * Formats the message to make it appropriate to the situation
   */
  private _formatStatusMsg(status: FormStatus, prop: StatusProp) {
    const format = (string, sub) => string.replace('{}', sub);
    switch(prop) {
      case StatusProp.UPDATE:
        this[prop] = format(status, 'update');
        break;
      case StatusProp.APPROVE:
        this[prop] = format(status, 'approval');
        break;
    }
  }

  /**
   * Displays a status upon submission, telling user to wait for server response, or whether
   * request was successful or failed.
   * @param status SUCCESS, WAIT, FAILURE, displays the appropriate message
   * @param prop determines which property the message will be applied to.
   * StatusProp.APPROVAL - displays the message in the APPROVAL status
   * StatusProp.UPDATE - displays the message in the UPDATE status
   * @param StatusClass determines which property recieves the bootstrap classes
   */
  private _displayFormStatus(status: FormStatus, prop: StatusProp, classProp: StatusClass) {
    switch(status) {
      case FormStatus.SUCCESS:
        this[classProp] = 'alert-success';
        this.submitDisabled = false;
        this._formatStatusMsg(status, prop);
        break;
      case FormStatus.WAIT:
        this[classProp]  = 'alert-warning';
        this.submitDisabled = true;
        this._formatStatusMsg(status, prop);
        break;
      case FormStatus.FAILURE:
        this[classProp]  = 'alert-danger';
        this.submitDisabled = false;
        this._formatStatusMsg(status, prop);
        break;
    }
  }

  closeStatus() {
    this.formStatus = null;
  }

  /**
   * Update the associate with the new verification status, client, status, and/or start date
   */
  updateAssociate() {
    this._displayFormStatus(FormStatus.WAIT, StatusProp.UPDATE, StatusClass.UPDATE_CLASS);
    this.clientService.getAllClients().subscribe(
      clients => {
        // the select element holds the numbers in string format so loose equality is required here
        // in order to match with the number type being held in the client object id.
        // tslint:disable-next-line:triple-equals
        const assoc_client = clients.filter(client => client.id == this.selectedClient)[0];
        const newAssociate = new Associate(
          this.associate.firstName,
          this.associate.lastName,
          this.associate.user,
          this.associate.id,
          this.associate.batch,
          this.selectedMarketingStatus,
          assoc_client,
          this.associate.endClient,
          this.associate.interview,
          this.associate.placement,
          this.associate.clientStartDate
        );
        this.associate = newAssociate;
        this.associateService.updateAssociate(this.associate).then(
          data => {
            this._displayFormStatus(FormStatus.SUCCESS, StatusProp.UPDATE, StatusClass.UPDATE_CLASS);
          },
        ).catch(error => {
          this._displayFormStatus(FormStatus.FAILURE, StatusProp.UPDATE, StatusClass.UPDATE_CLASS);
          console.error(error);
        });
      },
      error => console.error(error)
    );
  }

  getAssociateInterviews(id) {
    this.interviewService.getInterviewsForAssociate(id).subscribe(
      data => {
        this.interviews = data;
        this.interviewsLoading = false;
        this.isDataReady = true;
      },
      error => {
        console.error(error);
        this.isDataReady = true;
      }
    );
  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }

  updateInterviewFeedback() {
    this.interviewSelected.clientFeedback = this.feedback;
    this.interviewService.updateInterview(this.interviewSelected).subscribe();
  }

  setSelectedInterview(interview: Interview){
    this.interviewSelected = interview;
  }

  resetAllFields() {
    this.formOpen = false;
    // THESE NEED REFACTORING
    // this.newInterview.client = null;
    // this.newInterview.type = null;
    // this.newInterview.date = null;
    // this.newInterview.feedback = null;
    this.selectedClient = null;
    this.selectedMarketingStatus = null;
  }

  goToInterviewDetails( interview: Interview ) {
    this.user = this.authService.getUser();
    if (this.user.role === 3) {
      this.router.navigate(['interview-details/' + interview.id]);
    }
  }
}
