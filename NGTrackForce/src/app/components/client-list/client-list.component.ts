import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClientListService } from '../../services/client-list-service/client-list.service'
import { Subject } from 'rxjs/Subject';
import { Client } from '../../models/Client';

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
  private selectedCompany: string;
  private clientInfo: Client[];
  private clientNames: string[] = [];
  private client$: Client;
  private searchName;
  private clientSeries: string[] = [ 'Mapped', 'Unmapped' ];
  private clientLabel: string[] = ['Training', 'Reserved/Open', 'Selected', 'Confirmed', 'Deployed'];
  

  constructor(
    private clientService: ClientListService) { }

  ngOnInit() {
    this.getAllClientNames();
    this.getAllClients();
  }

  // get client names from data and push to clientNames string array
  getAllClientNames() {
    this.clientService.getAllClientsNames()
      .subscribe(
      clientNames => {
        // save array of object Client
        this.clientInfo = clientNames;
        // clear name list to reload list and run through filter
        this.clientNames.length = 0;
        // push list of names to an array
        for (let client of clientNames) {
          this.clientNames.push(client.name);
        }
      }, err => {
        console.log("Failed grabbing names");
      });
  }

  getAllClients() {
    this.clientService.getAllClients()
      .subscribe(
      // assign response to this.clients
      client => {
        this.client$ = client;
        this.selectedCompany = this.client$.name;
      }, err => {
        console.log("Failed grabbing clients");
      });
  }

  getOneClient(name: string) {
    this.selectedCompany = name;
    let oneClient = this.clientInfo.find(item => item.name == name);
    this.clientService.getOneClient(oneClient.id);
  }

}
