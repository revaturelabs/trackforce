import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClientListService } from '../../services/client-list/client-list.service';

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
  public clients;
  public client = {
    id: '1',
    name: 'myname',
  }
  
  constructor(
    private http: HttpClient,
    private clientListService: ClientListService
  ) { }

  ngOnInit() {
    this.getAllClientNames();
  }

  getAllClientNames() {

  }

  getAllClients() {

  }

}
