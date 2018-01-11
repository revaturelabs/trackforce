import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associates-service/associates-service';
import { Associate } from '../../models/Associate';
import { ClientListService } from '../../services/client-list-service/client-list.service';
import { Client } from '../../models/Client';

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
  clients: Client[]

  constructor(private associateService: AssociateService, private clientService: ClientListService) { }

  ngOnInit() {
    this.getAllAssociates()
    this.getClientNames()
  }

  /**
   * Set our array of all associates
   */
  getAllAssociates() {
    this.associateService.getAllAssociates().subscribe(
      data => {
        this.associates = data
      }
    )
  }

  /**
   * Fetch the client names
   */
  getClientNames() {
    this.clientService.getAllClientsNames().subscribe(
      data => {
        this.clients = <Client[]>data
      }
    )
  }
}