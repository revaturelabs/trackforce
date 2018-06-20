import { Component, OnInit } from '@angular/core';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Router } from '@angular/router';
import { User } from '../../models/user.model';
import { Interview } from '../../models/interview.model';
import { InterviewService } from '../../services/interview-service/interview.service';


/**
*@description This will be for the staging manager to provide feedback from the client
*/
@Component({
  selector: 'app-interviews-view',
  templateUrl: './interviews-view.component.html',
  styleUrls: ['./interviews-view.component.css']
})
@AutoUnsubscribe
export class InterviewsComponent implements OnInit {

  interviews: Interview[];

  //used for filtering
  searchByText = "";

  //status/client to be updated
  updateShow = false;
  updated = false;

  //used for ordering of rows
  desc = false;
  sortedColumn = "";

  //user access data - controls what they can do in the app
  user: User;
  canUpdate = false;

  constructor(
    private interviewService: InterviewService, 
  ) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem("currentUser"));
    if (this.user.role === 1 || this.user.role === 3 ||this.user.role===4) {
      this.canUpdate = true; // let the user update data if user is admin or manager
    }
    this.getInterviews();
  }

  getInterviews() {
    const self = this;
    this.interviewService.getAllInterviews().subscribe(
     data => {
        this.interviews = data;
      }
    )
  } 

  // updateInterviews() {
  //   const ids: number[] = [];
  //   let i = 1;
  //   const self = this;
  //   for (i; i <= this.interviews.length; i++) { //grab the checked ids
  //     const check = <HTMLInputElement>document.getElementById("" + i);
  //     if (check != null && check.checked) {
  //       ids.push(i);
  //     }
  //   }
  // }
}