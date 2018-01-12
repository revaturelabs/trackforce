import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associates-service/associates-service';
import { Associate } from '../../models/associate.model'
import { ClientListService } from '../../services/client-list-service/client-list.service';
import { Client } from '../../models/client.model';
import { element } from 'protractor';
import {ActivatedRoute} from "@angular/router"

@Component({
    selector: 'form-comp',
    templateUrl: './form.component.html',
    styleUrls: ['./form.component.css']
  })
  
  export class FormComponent implements OnInit {
    associate: Associate= new Associate();
    clients: Client[];
    message: string="";
    id:number;

    constructor( private associateService: AssociateService, private clientService: ClientListService) {
        var id=window.location.href.split("form-comp/")[1];
        console.log(id);
        this.id=Number(id);
        this.associateService.getAssociate(this.id).subscribe(data => {this.associate=data});
    }

    ngOnInit() {
        this.clientService.getAllClientsNames().subscribe(data => {this.clients = data});                   
    }

    updateAssociate(){
        this.associateService.updateAssociate(this.id,this.associate.marketingStatus, this.associate.client);
    }
    
}