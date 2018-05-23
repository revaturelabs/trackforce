import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { ActivatedRoute } from '@angular/router';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
/**
*@author Katherine Obioha
*
*@description This is the view for interview for associates
*
*/
@Component({
   selector: 'app-myinterview-view',
  templateUrl: './myinterview-view.component.html',
  styleUrls: ['./myinterview-view.component.css']
})

@AutoUnsubscribe
export MyInterviewComponent implements OnInit{

	 public interviews:Array<any> = [];
	 public newInterview: any = {
	    client: null,
	    date: null,
	    type: null,
	    feedback: null
	  }
	  public formOpen:boolean = false;
  
  constructor(
  private associateService : AssociateService;
  private activated: ActivatedRoute;
  ){}
  
  ngOnInit()
  {
  	let id = +this.activated.snapshot.paramMap.get('id');
  	this.getInterviews(id);
  }
  
  toggleForm() {
    this.formOpen = !this.formOpen;
  }
  
  addInterview(){
    console.log(this.newInterview);
    let interview = {
      associateId: id,
      clientId: this.newInterview.client,
      typeId: this.newInterview.type,
      interviewDate: new Date(this.newInterview.date).getTime(),
      interviewFeedback: this.newInterview.feedback
    };
    this.associateService.addInterviewForAssociate(this.associate.id,interview).subscribe(
      data => {
        this.getInterviews(id);
      },
      err => {
        console.log(err);
      }
    )

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
            date: new Date(interview.tfInterviewDate),
            type: interview.typeName,
            feedback: interview.tfInterviewFeedback
          }
          tempArr.push(intObj);
        }
        this.interviews = tempArr;
      }
    )
  }
}