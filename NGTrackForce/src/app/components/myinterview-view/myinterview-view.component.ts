import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Associate } from '../../models/associate.model';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from '../../services/client-service/client.service';
import {Interview} from '../../models/interview.model';

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
  public interviews: Array<Interview> = [];
 
  public associate: Associate = new Associate();
  public id:number = 0;
  public newInterview: any = {
    client: null,
    date: null,
    type: null,
    feedback: null
  }
  public formOpen: boolean = false;
  public conflictingInterviews: string = "";

  constructor(
    private associateService: AssociateService,
    private activated: ActivatedRoute) { }

  ngOnInit() {
    //gets the associate id from the path
    //the '+' coerces the parameter into a number
    this.id = +this.activated.snapshot.paramMap.get('id');
    this.getInterviews(this.id);
    this.getAssociate(this.id);
  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }

  addInterview(){
    console.log(this.newInterview);
    let interview = {
      associateId: this.id,
      clientId: this.newInterview.client,
      typeId: this.newInterview.type,
      interviewDate: new Date(this.newInterview.date).getTime(),
      interviewFeedback: this.newInterview.feedback
    };
    this.associateService.addInterviewForAssociate(this.id,interview).subscribe(
      data => {
        this.getInterviews(this.id);
      },
      err => {
        console.log(err);
      }
    )

  }
  
  updateInterview(){
  }

  getInterviews(id: number) {
    this.associateService.getInterviewsForAssociate(id).subscribe(
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
            JDescription: "Testing company applications in an agile environment",
            date: "June 22, 2018",
            CFeedback: "Impressive interview, final decision will be made soon",    
          	Flag: true,
          }
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
}
