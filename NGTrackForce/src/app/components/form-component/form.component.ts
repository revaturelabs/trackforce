import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { AssociateService } from '../../services/associate-service/associate.service';
import { Associate } from '../../models/associate.model'
import { ClientService } from '../../services/client-service/client.service';
import { Client } from '../../models/client.model';
import { element } from 'protractor';
import { ActivatedRoute } from "@angular/router"
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { User } from '../../models/user.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';

/**
 * Component for viewing an individual associate and editing as admin.
 */
@Component({
    selector: 'form-comp',
    templateUrl: './form.component.html',
    styleUrls: ['./form.component.css']
})
/** Decorator for automatically unsubscribing all observables upon ngDestory()
  * Prevents memory leaks
  */
@AutoUnsubscribe
export class FormComponent implements OnInit {
    user: User;
    associate: Associate = new Associate();
    clients: Client[];
    interviews: any;
    newInterview: any = {
      client: null,
      date: null,
      type: null,
      feedback: null
    };
    newStartDate: Date;
    message: string = "";
    selectedMarketingStatus: string;
    selectedClient: number;
    id: number;
    formOpen: boolean;
    isVP: boolean;

    /**
      *@param {AssociateService} associateService
      * Service for grabbing associate data from the back-end
      */
    constructor(
      private associateService: AssociateService,
      private clientService: ClientService,
      private authService: AuthenticationService
    ) {
        //gets id from router url parameter
        var id = window.location.href.split("form-comp/")[1];
        this.id = Number(id);
    }

    ngOnInit() {
        this.user = this.authService.getUser();
        //Role checks
        if(this.user.tfRoleId === 3){
          this.isVP = true;
        } else {
          this.isVP = false;
        }

        this.associateService.getAssociate(this.id).subscribe(
          data => {
            console.log(data);
            this.associate = <Associate>data;
            if (data.clientStartDate.toString() == "0")
              this.associate.clientStartDate = null;
            else
              this.associate.clientStartDate = this.adjustDate(data.clientStartDate);
          });
        this.clientService.getAllClients().subscribe(
          data => {
            console.log(data);
            this.clients = data;
          });
        this.getInterviews();
    }

    adjustDate(date: Date){ // dates are off by 1 day - this corrects them
      let ldate = new Date(date);
      let origDate = ldate.getDate();
      ldate.setDate(origDate+1);
      if (ldate.getDate() < 1) {
        ldate.setMonth(ldate.getMonth() -1)
        ldate.setDate(origDate);
      }
      return ldate;
    }

    /**
     * Update the associate with the new client, status, and/or start date
     */
    updateAssociate() {
      if (this.newStartDate) {
        var dateTime = Number((new Date(this.newStartDate).getTime())/1000);
      } else {
        var dateTime = Number((new Date(this.associate.clientStartDate).getTime())/1000);
      }
      if (this.selectedMarketingStatus) {
        var newStatus = Number(this.selectedMarketingStatus);
      } else {
        var newStatus = this.associate.msid;
      }
      if (this.selectedClient) {
        var newClient = this.selectedClient;
      } else {
        var newClient = this.associate.clid;
      }
      var newAssociate = {
        id: this.id,
        mkStatus: newStatus,
        clientId: newClient,
        startDateUnixTime: dateTime
      };
      this.associateService.updateAssociate(newAssociate).subscribe(
        data => {
          this.message = "Successfully updated associate";
          this.associateService.getAssociate(this.id).subscribe(
            data => {
              this.associate = <Associate>data;
              console.log(data.clientStartDate);
              if (data.clientStartDate.toString() == "0")
                this.associate.clientStartDate = null;
              else
                this.associate.clientStartDate = this.adjustDate(data.clientStartDate);
              this.resetAllFields();
          });
        }
      )
    }

    getInterviews() {
      this.associateService.getInterviewsForAssociate(this.id).subscribe(
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
          this.interviews.push({
            client: interview.clientId,
            date: interview.interviewDate,
            type: interview.typeId,
            feedback: interview.interviewFeedback
          });
        },
        err => {
          console.log(err);
        }
      )
      this.resetAllFields();
    }

    resetAllFields(){
      this.formOpen = false;
      this.newInterview.client = null;
      this.newInterview.type = null;
      this.newInterview.date = null;
      this.newInterview.feedback = null;
      this.selectedClient = null;
      this.selectedMarketingStatus = null;
    }
 }
