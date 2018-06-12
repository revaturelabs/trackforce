import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Associate } from '../../models/associate.model';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from '../../services/client-service/client.service';
import { userInfo } from 'os';
/**
*@author Michael Tseng
*
*@description This is the view for associates only
*
*/
@Component({
  selector: 'app-associate-view',
  templateUrl: './associate-view.component.html',
  styleUrls: ['./associate-view.component.css']
})
@AutoUnsubscribe
export class AssociateViewComponent implements OnInit {
  public associate: Associate = new Associate();
  public interviews: Array<any> = [];
  public messages: Array<string> = ["I cleared my interview with FINRA", "Please update my status", "I am deleting you soon :)"];
  public newMessage = "";

  public selectedMarketingStatus: string;
  public clients: Array<any> = [];
  public selectedClient = "";
  public formOpen = false;

  constructor(
    private associateService: AssociateService,
    private authService: AuthenticationService,
    private activated: ActivatedRoute,
    private clientService: ClientService) { }

  ngOnInit() {
    //gets the associate id from the path
    //the '+' coerces the parameter into a number
    const id = +this.activated.snapshot.paramMap.get('id');

    const a = this.getAssociate(id);

    this.getClients();
  }

  /**
  *@description Wraps the associate service to get associate info
  *
  *@param {number} id
  *the id number of the associate
  */
  getAssociate(id: number) {
    this.associateService.getAssociate(id).subscribe(
      data => {
        this.associate = data;
        console.log("yes" + this.associate.firstName)
      },
      err => {
        console.log("bah humbug");
      });
  }

  getClients() {
    this.clientService.getAllClients().subscribe(
      data => {
        this.clients = data;
      },
      err => {
        console.log(err);
      });
  }




}
