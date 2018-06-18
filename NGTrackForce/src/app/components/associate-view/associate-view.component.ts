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
  public associate: Associate;
  public formOpen = false;
  public newAssociate: Associate;
  public newFirstName: string;
  public newLastName: string;

  public errMsg: string;
  public succMsg: string;



  // THESE WERE HERE BUT DOING NOTHING
  // -MAX
  // public interviews: Array<any> = [];
  // public messages: Array<string> = ["I cleared my interview with FINRA", "Please update my status", "I am deleting you soon :)"];
  // public newMessage = "";
  // public selectedMarketingStatus: string;
  // public clients: Array<any> = [];
  // public selectedClient = "";


  constructor(
    private associateService: AssociateService,
    private authService: AuthenticationService,
    private activated: ActivatedRoute,
    private clientService: ClientService) { }

  ngOnInit() {
    this.associate = this.authService.getAssociate();
    this.newFirstName = this.associate.firstName;
    this.newLastName = this.associate.lastName;

    //gets the associate id from the path
    //the '+' coerces the parameter into a number

    // this.getClients();
  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }

  updateInfo() {
    this.associate.firstName = this.newFirstName;
    this.associate.lastName = this.newLastName;

    this.associateService.updateAssociate(this.associate).subscribe(
      success => {
        this.succMsg = "Information updated"
      },
      err => {
        if (err.status === 500) {
          this.errMsg = "There was an error with the server."
        } else {
          this.errMsg = "Something went wrong, your information was not updated."
        }
      }
    );
  }


  // THIS FUNCTION WAS REPLACED WITH GETTING ASSOCIATE FROM AUTH SERVICE
  // -MAX
  // /**
  // *@description Wraps the associate service to get associate info
  // *
  // *@param {number} id
  // *the id number of the associate
  // */
  // getAssociate(id: number) {
  //   this.associateService.getAssociate(id).subscribe(
  //     data => {
  //       this.associate = data;
  //     },
  //     err => {
  //     });
  // }

  // IT MAKES NO SENSE WHY THIS FUNCTION WAS IN HERE
  // -MAX
  // getClients() {
  //   this.clientService.getAllClients().subscribe(
  //     data => {
  //       this.clients = data;
  //     },
  //     err => {
  //     });
  // }




}
