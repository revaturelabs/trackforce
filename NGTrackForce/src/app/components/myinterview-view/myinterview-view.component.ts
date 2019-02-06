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
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LocalStorageUtils } from '../../constants/local-storage';

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
  registerForm:FormGroup; //used for updateInterview
  addInterviewForm:FormGroup; //used for addInterview
  public interviews: Interview[];
  public associate: Associate;
  public newInterview: Interview;
  public formOpen = false;
  public conflictingInterviews = '';

  public interviewDate: Date;
  public interviewAssigned: Date = new Date();
  public clients: Client[];
  public user: User;
  public id: number;
  public interviewType: InterviewType;
  public openDateNotified: boolean;
  public openInterviewDate: boolean;
  public conflictingInterview: boolean;
  public isDataReady = false;
  public dateError:boolean;
  public updateSuccess = false;
  public succMsg: string;
  public failMsg: string;
  show : boolean;
  public convertedTime : string;
  public today: string = new Date().toLocaleDateString();

  index;
  index2;
  date :string;

  constructor(
    private authService: AuthenticationService,
    private associateService: AssociateService,
    private activated: ActivatedRoute,
    private interviewService: InterviewService,
    private clientService: ClientService,
    private router: Router,
    private formBuilder:FormBuilder
  ) {}

  ngOnInit(){
      this.registerForm = this.formBuilder.group({
        dateinput: [''],
       });
      this.addInterviewForm = this.formBuilder.group({
         clientId: ['', Validators.required],
         typeId: ['', Validators.required],
         interviewDate: ['', Validators.required
         ],
         was24HRNotice: ['']
        });

    //gets the associate id from the path
    //the '+' coerces the parameter into a number
    // this.id = +this.activated.snapshot.paramMap.get('id');
    this.openDateNotified = false;
    this.openInterviewDate = false;
    this.conflictingInterview = false;

    this.user = JSON.parse(localStorage.getItem(LocalStorageUtils.CURRENT_USER_KEY));
    this.id = this.user.id;
    this.associateService.getAssociateByUserId(this.id).subscribe(
      data => {
        this.associate = data;
        this.getAssociateInterviews(this.associate.id);
      },
      error => {
        console.log('ngOnInit error');
      }
    );

    this.clientService.getAllClients().subscribe(
      data => {
        this.clients = data;
      },
      error => {
        console.log('getAllClients error');
      }
    );
  }//end ngOnInit()

  //Currently not used, could be used to toggle form view
  toggleForm() {
    this.formOpen = !this.formOpen;
  }

  //Convenience. Allows this.aif.<control> to easily access
  // addInterviewForm field data (.value) or control validator status
  get aif() {
    return this.addInterviewForm.controls;
  }

  addInterview() {
      if (!this.dateError && this.aif.interviewDate.valid){
        //the '+' coerces type to be number
        switch (+this.aif.typeId.value) {
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
          this.aif.clientId.value,
          this.interviewType,
          new Date(this.aif.interviewDate.value).getTime(),
          null,
          this.aif.was24HRNotice.value ? 1 : 0,
          null,
          //Deprecated on page, but necessary to the backend.
          // refers to the 'Assigned' date given to the Associate.
          //  unsure whether this originally referenced the given time or the actual time
          new Date(this.interviewAssigned).getTime(),
          new Date(this.aif.interviewDate.value).getTime().toString()
        );

        this.succMsg="Interview Added";
        setTimeout(() => {
          this.succMsg= '';
        }, 3000);
        this.interviewService
          .createInterview(this.newInterview, this.associate.id)
          .subscribe(res => {
            location.reload(false);
         });
      } else {
        this.failMsg = "Invalid Interview Submission";
        setTimeout(() => {
          this.failMsg = '';
        }, 3000);
        console.log("submission failed");
      }
  }


  updateInterview(interview: Interview){
    if (!this.dateError){
        interview.isInterviewFlagged = +interview.isInterviewFlagged; // set it to number
        interview.interviewDate = new Date(this.registerForm.value['dateinput']).getTime(); // convert into timestamp
        interview.dateSalesIssued = new Date(
          interview.dateAssociateIssued
        ).getTime(); // convert into timestamp

        interview.dateAssociateIssued = new Date (this.registerForm.value['dateinput']).getTime() ;
        console.log("in updateinterview");


        this.interviewService.updateInterview(interview).subscribe(res => {
        this.updateSuccess=true;


         location.reload(false);
       },
         error => console.error('Error in myinterview-view.component.ts updateInterview(): ', error.message)
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
    for (let i = 0; i < this.interviews.length; i++) {
      if (
        new Date(this.interviews[i].interviewDate).getTime() ===
          checkDate.getTime() &&
        i !== interview
      ) {
        this.conflictingInterviews =
          'The highlighted interviews are conflicting.' +
          'They are both scheduled at the same time!';
        this.conflictingInterview = true;
        return true;
      }
    }
    return false;
  }

  showDateNotified(index) {
    this.index = index;
  }

  showInterviewDate(index) {
    this.index2 = index;
  }


  getAssociateInterviews(id: number) {
    this.interviewService.getInterviewsForAssociate(id).subscribe(
      data =>{

        this.interviews = data;
        this.isDataReady = true;



      },
      error => {
        console.log('getAssociateInterview error');
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
