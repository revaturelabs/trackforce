import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Associate } from '../../models/associate.model';
import { ActivatedRoute } from '@angular/router';
/**
*@author Michael Tseng
*
*@description This is the view for associates only
*
*/
@Component({
  selector: 'app-associate-view',
  templateUrl: './associate-view.component.html',
  styleUrls: ['./associate-view.component.css']
})
@AutoUnsubscribe
export class AssociateViewComponent implements OnInit {
  public associate: Associate = new Associate();
  public interviews: Array<any> = [
    {
      id: 1,
      client: "Accenture",
      date: new Date(),
      type: "Phone",
      feedback: "Good"
    },
    {
      id: 2,
      client: "Revature",
      date: new Date(),
      type: "On-site",
      feedback: "Bad"
    },
    {
      id: 3,
      client: "FINRA",
      date: new Date(),
      type: "Skype",
      feedback: "Okay"
    }
  ]
  public messages: Array<string> = ["I cleared my interview with FINRA","Please update my status"];
  public newMessage: string = "";
  public newInterview: any = {
    client: null,
    date: null,
    type: null,
    feedback: null
  }
  public selectedMarketingStatus: string;
  public clients: Array<any> = [
    {
      name: "Accenture"
    },
    {
      name: "Revature"
    },
    {
      name: "FINRA"
    }
  ]
  public selectedClient: string = "";
  public formOpen: boolean = false;

  constructor(
    private associateService: AssociateService,
    private authService: AuthenticationService,
    private activated: ActivatedRoute) { }

  ngOnInit() {
    //gets the associate id from the path
    //the '+' coerces the parameter into a number
    let id = +this.activated.snapshot.paramMap.get('id');
    this.getAssociate(id);
  }

  /**
  *@description Wraps the associate service to get associate info
  *
  *@param {number} id
  *the id number of the associate
  */
  getAssociate(id: number){
    this.associateService.getAssociate(id).subscribe(
      data => {
        this.associate = data;
      },
      err => {
        console.log(err);
    });
  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }

  sendMessage() {
    let tempString = new String(this.newMessage);
    if (this.newMessage) {
      this.messages.push(tempString.toString());
    }
  }

  addInterview() {
    console.log(this.newInterview);
    let tempVar = {
      client: this.newInterview.client,
      type: this.newInterview.type,
      date: this.newInterview.date,
      feedback: this.newInterview.feedback
    }
    this.interviews.push(tempVar);
    this.newInterview.client = null;
    this.newInterview.type = null;
    this.newInterview.date = null;
    this.newInterview.feedback = null;
  }

}
