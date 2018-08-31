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
  selectedMarketingStatus: any;
  selectedClient: number;
  id: number;
  formOpen: boolean;
  isVP: boolean;
  isAssociate: boolean;
  iid: number;

  // form booleans
  // isVerified: string;
  isApproved: number;
  isMapped: boolean;
  eligibleForInterview: boolean;
  interviewScheduled: boolean;
  clearedAllInterviews: boolean;
  receivedEmailFromClient: boolean;
  passedBackgroundCheck: boolean;
  hasStartDate: boolean;
  public isDataReady = false;

  //loading booleans
  interviewsLoading = true;

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
    //gets id from router url parameter
    //this.id = Number(window.location.href.split('form-comp/')[1]);
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
          this.getAssociateInterviews(this.associate.id);
        },
        error => {
          console.log('error');
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

    // this.associateService.getAssociate(this.id).subscribe(data => {
    //   this.associate = data;
    //   this.isApproved = this.associate.user.isApproved;
    //   if (data.clientStartDate.toString() === '0') {
    //     this.associate.clientStartDate = null;
    //   } else {
    //     // this.associate.clientStartDate = this.adjustDate(Number(data.clientStartDate) * 1000);
    //   }
    // });
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

  approveAssociate() {
    return this.associateService
      .approveAssociate(this.associate.id)
      .subscribe(data => (this.isApproved = data ? 1 : 0));
  }

  processForm() {
    if (this.hasStartDate) {
      if (Date.now() < new Date(this.newStartDate).getTime()) {
        // if start date is before today, set status to MAPPED: DEPLOYED
        this.selectedMarketingStatus = 5;
      } else {
        // if start date is after today, set status to MAPPED: CONFIRMED
        this.selectedMarketingStatus = 4;
      }
    } else if (this.passedBackgroundCheck && this.hasStartDate) {
      // if background check is passed and associate has start date, set status to MAPPED: CONFIRMED
      this.selectedMarketingStatus = 4;
    } else if (this.clearedAllInterviews) {
      // if interviews are cleared, set status to MAPPED: SELECTED
      this.selectedMarketingStatus = 3;
    } else if (this.interviewScheduled) {
      // if an interview is scheduled, set status to MAPPED: RESERVED
      this.selectedMarketingStatus = 2;
    } else if (this.eligibleForInterview) {
      if (this.isMapped) {
        // if associate is mapped and eligible for an interview, set status to MAPPED: TRAINING
        this.selectedMarketingStatus = 1;
      } else {
        // if associate is NOT mapped, set status to UNMAPPED: TRAINING
        this.selectedMarketingStatus = 6;
      }
    } else if (this.isMapped) {
      // if associate is mapped, set status to MAPPED: TRAINING
      this.selectedMarketingStatus = 1;
    } else {
      // associate is unmapped
      // set status to UNMAPPED: TRAINING
      this.selectedMarketingStatus = 6;
    }
    // this.updateAssociate();
  }

  /**
   * Update the associate with the new verification status, client, status, and/or start date
   */
  // COMMENTED OUT BECAUSE IT BROKE BECAUSE OF CHANGES WE MADE TO MODELS
  // ALSO NEED TO UNCOMMENT OUT LINE 157 WHEN THIS WORKS
  // updateAssociate() {
  //   if (this.newStartDate) {
  //     var dateTime = Number((new Date(this.newStartDate).getTime()) / 1000);
  //   } else {
  //     var dateTime = Number((new Date(this.associate.clientStartDate).getTime()) / 1000);
  //   }
  //   if (this.selectedVerificationStatus) {
  //     var newVerificationStatus = this.selectedVerificationStatus;
  //   } else {
  //     // var newVerificationStatus = this.associate.user.verified;
  //   }
  //   if (this.selectedMarketingStatus) {
  //     var newStatus = Number(this.selectedMarketingStatus);
  //   } else {
  //     var newStatus = this.associate.marketingStatus.id;
  //   }
  //   if (this.selectedClient) {
  //     var newClient = this.selectedClient;
  //   } else {
  //     var newClient = this.associate.client.id;
  //   }
  //   let newAssociate = {
  //     id: this.id,
  //     verified: newVerificationStatus,
  //     mkStatus: newStatus,
  //     clientId: newClient,
  //     startDateUnixTime: dateTime
  //   };
  //   this.associateService.updateAssociate(newAssociate).subscribe(
  //     data => {
  //       this.successMessage = "Successfully updated associate";
  //       this.associateService.getAssociate(this.id).subscribe(
  //         data => {
  //           this.associate = data;
  //           if (data.clientStartDate.toString() === "0") {
  //             this.associate.clientStartDate = null;
  //           } else {
  //             this.associate.clientStartDate = this.adjustDate(Number(data.clientStartDate) * 1000);
  //           } this.resetAllFields();
  //         },
  //         err => {
  //         }
  //       );
  //     }
  //   )
  // }

  /* Verify this Associate */
  // WHAT EVEN IS THE PURPOSE OF VERIFYING AN ASSOCIATE??????
  // verifyAssociate() {
  //   this.associateService.verifyAssociate(this.id).subscribe(
  //     data => {
  //       this.successMessage = "The Associate was successfully verified!";
  //     },
  //     err => {
  //       this.errorMessage = "There was an error while verifying the Associate!";
  //     }
  //   )
  // }

  getAssociateInterviews(id) {
    this.interviewService.getInterviewsForAssociate(id).subscribe(
      data => {
        this.interviews = data;
        this.interviewsLoading = false;
        this.isDataReady = true;

      },
      error => {
        console.log('Failed to get interviews.');
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

  goToInterviewDetails( interview: Interview )
  {
    this.user = this.authService.getUser();
    if ( this.user.role === 3 )
    {
      this.router.navigate(['interview-details/' + interview.id]);
    }
  }
}
