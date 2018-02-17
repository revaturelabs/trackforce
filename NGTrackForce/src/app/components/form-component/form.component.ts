import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { AssociateService } from '../../services/associate-service/associate.service';
import { Associate } from '../../models/associate.model'
import { ClientService } from '../../services/client-service/client.service';
import { Client } from '../../models/client.model';
import { element } from 'protractor';
import { ActivatedRoute } from "@angular/router"
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';

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

    /**
      *@param {AssociateService} associateService
      * Service for grabbing associate data from the back-end
      */
    constructor(
      private associateService: AssociateService,
      private clientService: ClientService
    ) {
        //gets id from router url parameter
        var id = window.location.href.split("form-comp/")[1];
        this.id = Number(id);
    }

    ngOnInit() {
        this.associateService.getAssociate(this.id).subscribe(
          data => {
            console.log(data);
            this.associate = <Associate>data
          });
        this.clientService.getAllClients().subscribe(
          data => {
            console.log(data);
            this.clients = data;
          });
        this.getInterviews();
    }

    /**
     * Update the associate with the new client, status, and/or start date
     */
    updateAssociate() {
      if (this.newStartDate) {
        var dateTime: any = Number((new Date(this.newStartDate).getTime())/1000);
      }
      else {
        var dateTime = null;
      }
      var newAssociate = {
        id: this.id,
        mkStatus: this.selectedMarketingStatus,
        clientId: this.selectedClient,
        startDateUnixTime: dateTime
      };
      this.associateService.updateAssociate(newAssociate).subscribe(
        data => {
          this.associateService.getAssociate(this.id).subscribe(
            data => {
              this.associate = <Associate>data;
              console.log(data.clientStartDate);
              if (!data.clientStartDate)
                this.associate.clientStartDate = null;
              else
                this.associate.clientStartDate = new Date(data.clientStartDate.getTime());
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
