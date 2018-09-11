import { User } from './../../models/user.model';
import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Associate } from '../../models/associate.model';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from '../../services/client-service/client.service';
import { userInfo } from 'os';
import { UpdateStatus, AlertStatusClass } from './associate-view.enum';

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

  public statusMsg: UpdateStatus;
  public statusClass: AlertStatusClass;
  public user: User;
  public id: number;
  isDataReady: boolean;

  constructor(
    private associateService: AssociateService,
    private authService: AuthenticationService,
    private activated: ActivatedRoute,
    private clientService: ClientService
  ) {}

  ngOnInit() {
    this.isDataReady = false;
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

  /**
   * Disables submit button if the form is invalid
   * Data bound to [disabled] on button
   */
  formIsInvalid(): boolean {
    if (!this.newLastName || !this.newFirstName) {
      return true;
    } else if (this.newFirstName.trim() === '' || this.newLastName.trim() === '') {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Displays a status message based on whether the update was successful
   * @param updateStatus Uses UpdateStatus enum to display a message
   * @param alertClass uses AlertStatusClass to display the message in a Bootstrap CSS class
   */
  private _displayStatus(updateStatus: UpdateStatus, alertClass: AlertStatusClass) {
    this.statusMsg = updateStatus;
    this.statusClass = alertClass;
  }

  /**
   * Updates the user info and displays a status message
   */
  updateInfo() {
    this.associate.firstName = this.newFirstName;
    this.associate.lastName = this.newLastName;
    this._displayStatus(UpdateStatus.WAIT, AlertStatusClass.WAIT);

    this.associateService.updateAssociate(this.associate).then(() => {
      this._displayStatus(UpdateStatus.SUCCESS, AlertStatusClass.SUCCESS);
    }).catch((err) => {
      console.error(err);
      this._displayStatus(UpdateStatus.SUCCESS, AlertStatusClass.SUCCESS);
    });

    // this.associateService.updateAssociate(this.associate).subscribe(
    //   success => {
    //     console.log(success);
    //     this._displayStatus(UpdateStatus.SUCCESS, AlertStatusClass.SUCCESS);
    //   },
    //   err => {
    //     console.error(err);
    //     this._displayStatus(UpdateStatus.SUCCESS, AlertStatusClass.SUCCESS);
    //   }
    // );
  }
}
