import { Component, OnInit } from '@angular/core';
import { ClientMappedService } from '../../services/client-mapped-service/client-mapped-service';

//HTTPClient Libraries
import { HttpClient } from '@angular/common/http/';
import { environment}  from '../../../environments/environment'

@Component({
  selector: 'app-client-mapped',
  templateUrl: './client-mapped.component.html',
  styleUrls: ['./client-mapped.component.css']
})
export class ClientMappedComponent implements OnInit {
  private statusID = 1;
  constructor(
    private clientMappedService: ClientMappedService,
    private http: HttpClient
  ) { }

  ngOnInit() {
    console.log("Inisde ngOnInit");

    this.clientMappedService.getAssociatesByStatus().subscribe( data => {
      console.log(data);
    })
  }
}
