import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClientListService } from '../../services/client-list/client-list.service';
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
  private searchName = new Subject<string>();
  
  constructor(
    private http: HttpClient,
    private clientListService: ClientListService) { }

  ngOnInit() {
    this.getAllClientNames();
  }

  getAllClientNames() {
    this.clientListService.getAllClientsNames()
      .subscribe(clientNames => this.clientNames = clientNames);

  }

  getAllClients() {

  }

}
