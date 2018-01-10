import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/clients-service/clients-service'

/**
 * @author Han Jung
 * @description Translation of clientCtrl.js
 */
@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})

export class ClientListComponent implements OnInit {
  clientNames;
  searchName;
  
  constructor(private clientService: ClientService) {
  }

  ngOnInit() {
    this.getAllClientNames();
  }

  getAllClientNames() {
    var self = this;
    this.clientService.getAllClientsNames()
      .subscribe(clientNames => self.clientNames = clientNames);
  }

}
