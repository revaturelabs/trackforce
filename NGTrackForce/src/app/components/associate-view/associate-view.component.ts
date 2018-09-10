import { User } from './../../models/user.model';
import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Associate } from '../../models/associate.model';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from '../../services/client-service/client.service';
import { Router } from '@angular/router';
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
  public user: User;
  public id: number;
  public message: string;
  isDataReady = false;

  constructor(
    private associateService: AssociateService,
    private authService: AuthenticationService,
    private activated: ActivatedRoute,
    private clientService: ClientService,
    private router: Router
  ) {}

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.id = this.user.id;
    this.associateService.getAssociate(this.id).subscribe(
      data => {
        this.associate = data;
        this.isDataReady = true;
      },
      error => {
        console.log('error');
      }
    );
  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }

  updateInfo() {
    this.associate.firstName = this.newFirstName;
    this.associate.lastName = this.newLastName;

    this.associateService.updateAssociate(this.associate).subscribe(
      success => {
        this.succMsg = 'Information updated';
      },
      err => {
        if (err.status === 500) {
          this.errMsg = 'There was an error with the server.';
        } else {
          this.errMsg =
            'Something went wrong, your information was not updated.';
        }
      }
    );
  }
}
