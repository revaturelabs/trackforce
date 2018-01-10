import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClientService } from '../../services/clients-service/clients-service'
import { Subject } from 'rxjs/Subject';

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

  public clientNames;
  private searchName;
  
  constructor(
    private clientService: ClientService) { }

  ngOnInit() {
    this.getAllClientNames();
  }

  getAllClientNames() {
    this.clientService.getAllClientsNames()
      .subscribe(clientNames => this.clientNames = clientNames);
      console.log(this.clientNames);
  }

  getAllClients() {

  }
  getOneClient(){

  }

}
