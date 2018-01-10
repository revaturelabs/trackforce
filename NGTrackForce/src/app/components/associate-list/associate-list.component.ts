import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associates-service/associates-service';
<<<<<<< HEAD
import { Associate } from '../../../models/Associate';
import { ClientService } from '../../services/clients-service/clients-service';
import { Client } from '../../../models/Client';
=======
import { Associate } from '../../models/Associate';
import { ClientService } from '../../services/clients-service/clients-service';
import { Client } from '../../models/Client';
>>>>>>> 2a34918e84043862e8c96dd50b26b32fb7368c36

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

  constructor(private associateService: AssociateService, private clientService: ClientService) { }

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
<<<<<<< HEAD
        // this.clients = data
=======
        this.clients = <Client[]>data
>>>>>>> 2a34918e84043862e8c96dd50b26b32fb7368c36
      }
    )
  }
}