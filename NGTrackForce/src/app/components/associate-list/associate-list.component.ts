import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associates-service';
import { Associate } from '../../../models/Associate';
import { ClientService } from '../../services/clients-service';
import { Client } from '../../../models/Client';
import { element } from 'protractor';

/**
 * Component for the Associate List page
 * @author Alex, Xavier
 */
@Component({
  selector: 'app-associate-list',
  templateUrl: './associate-list.component.html',
  styleUrls: ['./associate-list.component.css']
})

export class AssociateListComponent implements OnInit {

  associates: Associate[]
  clients: Client[];
  searchByStatus: string = ""; //used for  filtering
  searchByClient: string = "";
  searchByText: string = "";
  updateStatus: string = "";
  updateClient: string = "";
  public test:number[]; 

  constructor(private associateService: AssociateService, private clientService: ClientService) { }

  ngOnInit() {
    this.getAllAssociates();
    this.getClientNames();
  }

  /**
   * Set our array of all associates
   */
  getAllAssociates() {
    this.associateService.getAllAssociates().subscribe(
      data => {
        this.associates = data;
      }
    )
  }

  /**
   * Fetch the client names
   */
  getClientNames() {
    this.clientService.getAllClientsNames().subscribe(
      data => {
        this.clients = data;
      }
    )
  }

  updateAssociates()
  {
    var ids:number[]= [];
    var i=1;
    for(i;i<=this.associates.length;i++)
    {
      var check=<HTMLInputElement>document.getElementById(""+i);
      if(check.checked)
      {
        ids.push(i);
        console.log(i);
      }
    }
    this.associateService.updateAssociates(ids, this.updateStatus, this.updateClient);
  }

  
  updateAssociatesTest()
  {
    var ids:number[]= [];
    var i=1;
    for(i;i<=this.associates.length;i++)
    {
      var check=<HTMLInputElement>document.getElementById(""+i);
      if(check.checked)
      {
        ids.push(i);
        console.log(i);
      }
    }
    this.test=ids;
  }
}