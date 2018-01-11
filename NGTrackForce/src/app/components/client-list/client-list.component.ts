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
  private client$: any;
  private searchName;
  // chart variable
  public barChartLabel: string[] = ['Training', 'Reserved/Open', 'Selected', 'Confirmed', 'Deployed'];
  public barChartType: string = 'bar';
  public barChartLegend: boolean = true;
  public barChartColors: any = [{
    backgroundColor: '#e85410'
  }, {
    backgroundColor: '#59504c'
  }, '#e85410', '#e85410']
  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: true
  }
  public barChartData: any[] = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Mapped' },
    { data: [28, 48, 40, 19, 86, 27, 90], label: 'Unmapped' }
  ];


  constructor(
    private clientService: ClientListService) { }

  ngOnInit() {
    this.getAllClientNames();
    this.getAllClients();
  }

  // get client names from data and push to clientNames string array
  getAllClientNames() {
    var self = this;
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
        this.barChartData = [this.client$.trainingMapped, ]
      }, err => {
        console.log("Failed grabbing clients");
      });
  }

  // get client name and find id to request client information
  getOneClient(name: string) {
    this.selectedCompany = name;
    let oneClient = this.clientInfo.find(item => item.name == name);
    this.clientService.getOneClient(oneClient.id)
      .subscribe(
      client => {
        this.client$ = client;
      }, err => {
        console.log("Failed grabbing client");
      });
  }
}
