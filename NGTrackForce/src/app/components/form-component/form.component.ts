import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associates-service/associates-service';
import { Associate } from '../../models/associate.model'
import { ClientListService } from '../../services/client-list-service/client-list.service';
import { Client } from '../../models/client.model';
import { element } from 'protractor';
import { ActivatedRoute } from "@angular/router"
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { RequestService } from '../../services/request-service/request.service';

/**
 * Component for viewing an individual associate and editing as admin.
 */
@Component({
    selector: 'form-comp',
    templateUrl: './form.component.html',
    styleUrls: ['./form.component.css']
})
/** Decorator for automatically unsubscribing all observables upon ngDestory()
  *Prevents memory leaks
  */
@AutoUnsubscribe
export class FormComponent implements OnInit {
    associate: Associate = new Associate();
    clients: Client[];
    message: string = "";
    selectedMarketingStatus: string = "";
    selectedClient: string = "";
    id: number;

    /**
      *@param {AssociateService} associateService
      * Service for grabbing associate data from the back-end
      *@param {RequestService} rs
      * Originally planned to have one aggregate service to handle all requests
      *May be un-needed in this component; un-used in this particular component
      */
    constructor(private associateService: AssociateService, private rs: RequestService) {
        //gets id from router url parameter
        var id = window.location.href.split("form-comp/")[1];
        this.id = Number(id);
        this.associateService.getAssociate(this.id).subscribe(data => { this.associate = <Associate>data });
    }

    ngOnInit() {
        this.rs.getClients().subscribe(data => { this.clients = data; });
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
}
