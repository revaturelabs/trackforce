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
*@author Katherine Obioha
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
    // console.log(this.clients)

  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }

  addInterview(){
    console.log(this.newInterview);  
  
    this.newInterview.dateOfInterview = new Date(this.interviewDate).getTime()
    this.newInterview.dateNotified = new Date(this.interviewDateNotification).getTime()

  
 
    console.log(this.newInterview.clientid);
    console.log(this.newInterview.dateOfInterview);
    console.log(this.newInterview.dateNotified);
    console.log(this.newInterview.typeName);
    console.log(this.newInterview.notified);



   
    // this.associateService.addInterviewForAssociate(this.id,this.newInterview).subscribe(
    //   data => {
    //     this.getInterviews(this.id);
    //   },
    //   err => {
    //     console.log(err);
    //   }
    // )

  }
  
  updateInterview(){
  }

  getInterviews(id: number) {
    this.associateService.getInterviewsForAssociate(id).subscribe(
     data => {
       console.log(data);
        let tempArr = [];
        for (let i=0;i<data.length;i++) {
          let interview = data[i];
          let intObj = {
            id: interview.id,
            client: interview.tfClientName,
           DInterview : new Date(interview.tfInterviewDate),
           type: interview.typeName,
            AFeedback: interview.tfInterviewFeedback,  
            JDescription: "Testing company applications in an agile environment",
            date: "June 22, 2018",
            CFeedback: "Impressive interview, final decision will be made soon",    
          	Flag: true,
          }
          console.log(intObj.date)
          tempArr.push(intObj);
        }
        this.interviews = tempArr;
      }
    )
  } 
  
  
 

  /* Function to search for conflicting interviews */
  highlightInterviewConflicts(interview: number) {
    var checkDate = this.interviews[interview].DInterview;
    for (var i = 0; i < this.interviews.length; i++) {
      if (this.interviews[i].DInterview === checkDate && i != interview) {
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
        console.log(err);
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
  console.log(interview.isEditingAvailable)
}

showAvailableDate(interview,dateVal){
  if(!interview.isDateAvailable){
    interview.isDateAvailable=true;
  } else {
    if(dateVal)
      interview.date = dateVal;
    interview.isDateAvailable=false;
  }
  console.log(interview.isDateAvailable)
}


saveInterview(interview:Interview){



}
getClientNames() {
  var self = this;
  this.clientService.getAllClients().subscribe(data => {
    self.clients = data;
    console.log(this.clients);
  });
}

}
