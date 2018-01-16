import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associates-service/associates-service';
import { Associate } from '../../models/associate.model'
import { ClientListService } from '../../services/client-list-service/client-list.service';
import { Client } from '../../models/client.model';
import { element } from 'protractor';
import { ActivatedRoute } from "@angular/router"
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';

/**
 * Component for viewing an individual associate and editing as admin.
 */
@AutoUnsubscribe
@Component({
    selector: 'form-comp',
    templateUrl: './form.component.html',
    styleUrls: ['./form.component.css']
})

export class FormComponent implements OnInit {
    associate: Associate = new Associate();
    clients: Client[];
    message: string = "";
    selectedMarketingStatus: string = "";
    selectedClient: string = "";
    id: number;

    constructor(private associateService: AssociateService, private clientService: ClientListService) {
        var id = window.location.href.split("form-comp/")[1];
        this.id = Number(id);
        this.associateService.getAssociate(this.id).subscribe(data => { this.associate = <Associate>data });
    }

    ngOnInit() {
        this.clientService.getAllClientsNames().subscribe(data => { this.clients = data });
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