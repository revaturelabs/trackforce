import { Component, OnInit } from '@angular/core';
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
    message: string = "";
    selectedMarketingStatus: string = "";
    selectedClient: string = "";
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
     * Update the associate with the new client and/or status
     */
    updateAssociate() {
      if (this.selectedClient !== this.associate.client
          && this.selectedMarketingStatus !== this.associate.marketingStatus) {
          this.associateService.updateAssociate(this.id, this.selectedMarketingStatus, this.selectedClient).subscribe(
              data => {
                  this.associateService.getAssociate(this.id).subscribe(
                      data => {
                          this.associate = <Associate>data
                      });
              }
          )
      }
    }

    getInterviews(){
      // this.associateService.getInterviewsForAssociate(this.id).subscribe(
      //   data => {
      //     this.interviews = data;
      //   });
      this.interviews = this.associateService.getInterviewsForAssociate(this.id);
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

   updateStartDate() {
   let mydate = this.associate.startDate;
   this.associateService.updateAssociateStartDate(this.id,mydate).subscribe(
    data => {
      console.log(data);
    },
    err => {
      console.log(err);
    });
 }
}



