import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Associate } from '../../models/associate.model';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from '../../services/client-service/client.service';
import { Interview } from '../../models/interview.model';
import { InterviewService } from '../../services/interview-service/interview.service';
import { Client } from '../../models/client.model';

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
  public interviews: Array<any> = [];
  public associate: Associate = new Associate();
  public id:number = 0;
  public newInterview: Interview = new Interview();
  public formOpen: boolean = false;
  public conflictingInterviews: string = "";
  public interviewDate: Date = new Date();
  public interviewDateNotification: Date = new Date();
  public clients: Client [];

  constructor(
    private associateService: AssociateService,
    private activated: ActivatedRoute,
    private interviewService: InterviewService,
    private clientService: ClientService
  ) { }


  ngOnInit() {
    //gets the associate id from the path
    //the '+' coerces the parameter into a number
    this.id = +this.activated.snapshot.paramMap.get('id');
    this.getInterviews(this.id);
    this.getAssociate(this.id);
    this.getClientNames();
  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }

  addInterview(){
    //this.newInterview.associateId = this.id
    this.newInterview.interviewDate = new Date(this.interviewDate).getTime()
    this.newInterview.dateAssociateIssued = new Date(this.interviewDateNotification).getTime()
    this.newInterview.jobDescription = "none available";
    this.newInterview.flagAlert = 0;
    this.newInterview.was24HRNotice = (this.newInterview.was24HRNotice*1)
    this.associateService.addInterviewForAssociate(this.id,this.newInterview).subscribe(
      data => {
        this.getInterviews(this.id);
      },
      err => {
      }
    );
  }


  updateInterview(id: number){
    if (sessionStorage.getItem("changedin") === null) {
  
  
     const updateinterview = this.interviews[id];
      var interview:any = {
        clientFeedback : updateinterview.CFeedback,
        dateAssociateIssued: new Date(updateinterview.date).getTime,
        dateSalesTeamIssued : null,
        reasonForFlag: null,
        interviewId :updateinterview.id,
 
        jobDescription: updateinterview.JDescription,
       // tfClientName :updateinterview.client,
        interviewDate : new Date(updateinterview.DInterview).getTime,
        //was24HRNotice:updateinterview.Flag,
        interviewFeedback : updateinterview.AFeedback,
        clientId: 9,
        typeId : updateinterview.typeID
      } 
    }
   else
    {
      var u = JSON.parse(sessionStorage.getItem("changedin"));
      const updateinterview = this.interviews[id];
      updateinterview.CFeedback = u.CFeedback;
      updateinterview.AFeedback = u.AFeedback;
      var interview:any = {
        clientFeedback : updateinterview.CFeedback,
        dateAssociateIssued: new Date(updateinterview.date).getTime,
        interviewId :updateinterview.id,
        jobDescription: updateinterview.JDescription,
        interviewDate : new Date(updateinterview.DInterview).getTime,
        //was24HRNotice:updateinterview.Flag,
        interviewFeedback : updateinterview.AFeedback,
        clientId : 9,
       typeId : updateinterview.typeID
      } 
      
      sessionStorage.clear();
    }
     this.interviewService.updateinterview(interview, this.id).subscribe(
       data => {
         this.getInterviews(this.id);
       },
       err => {
       }
    );    
    
  }

  getInterviews(id: number) {
    this.interviewService.getInterviews(id).subscribe(
      data => {
        let tempArr = [];
        for (let i=0;i<data.length;i++) {
          let interview = data[i];
          let intObj = {
            id: interview.id,
            client: interview.tfClientName,
            DInterview : new Date(interview.tfInterviewDate),
            type: interview.typeName,
            AFeedback: interview.tfInterviewFeedback,
            JDescription: interview.jobDescription,
            date: new Date(interview.dateAssociateIssued),
            CFeedback: interview.clientFeedback,    
            typeID : interview.typeId,            
            Flag: interview.isInterviewFlagged,

          }
          tempArr.push(intObj);
        }
        this.interviews = tempArr;

        sessionStorage.setItem("interviews", JSON.stringify(this.interviews));

      }
    )
  }

  /**
   Function to search for conflicting interviews.
   This function is called once for every row in the
   "My Interviews" table. If it returns true, the date
   cell is colored red to highlight the conflict.
  */
  highlightInterviewConflicts(interview: number) {
    var checkDate = this.interviews[interview].DInterview;
    for (var i = 0; i < this.interviews.length; i++) {
      if (this.interviews[i].DInterview.getTime() === checkDate.getTime() && i != interview) {
        this.conflictingInterviews = "The highlighted interviews are conflicting." +
        "They are both scheduled at the same time!";
        return true;
      }
    }
    return false;
  }

  getAssociate(id: number){
    this.associateService.getAssociate(id).subscribe(
      data => {
        this.associate = data;
      },
      err => {
      });
    }

    showInputDate(interview,dateVal){
      if(!interview.isEditingAvailable){
        interview.isEditingAvailable=true;
      } else {
        if(dateVal){
          interview.DInterview=dateVal;
        }
        interview.isEditingAvailable=false;
      }
    }

    showAvailableDate(interview,dateVal){
      if(!interview.isDateAvailable){
        interview.isDateAvailable=true;
      } else {
        if(dateVal)
        interview.date = dateVal;
        interview.isDateAvailable=false;
      }
    }

    saveInterview(interview:Interview){

    }

    getClientNames() {
      var self = this;
      this.clientService.getAllClients().subscribe(data => {
        self.clients = data;
      });
    }

  }
