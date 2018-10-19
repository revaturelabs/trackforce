import { Component, OnInit } from '@angular/core';
import { Trainer } from '../../models/trainer.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { TrainerService } from '../../services/trainer-service/trainer.service';
import { Associate } from '../../models/associate.model';
import {AssociateService} from "../../services/associate-service/associate.service";

@Component({
  selector: 'app-trainer-view',
  templateUrl: './trainer-view.component.html',
  styleUrls: ['./trainer-view.component.css']
})
export class TrainerViewComponent implements OnInit {

  readonly _TRAINER_KEY = 'currentTrainer';

  trainer: Trainer;
  formOpen = false;
  newFirstName: string;
  newLastName: string;
  associates: Associate[] = [];
  statusMsg: string;
  statusClass = 'wait';

  // ADDED SO THIS COMPILES. Once you're actually making an ajax call you'll want to have these as your success
  // and error messages.
  errMsg = false;
  succMsg = false;

  constructor(
    private authService: AuthenticationService,
    private trainerService: TrainerService,
    private associateService: AssociateService
    ) { }

  ngOnInit() {
    this.trainer = this.authService.getTrainer();
    this.newFirstName = this.trainer.firstName;
    this.newLastName = this.trainer.lastName;
    //this.getTrainerAssociates();
  }

  getTrainerAssociates() {
    this.associates = [];
    // if (this.trainer.primary) {
    //   const primary = this.trainer.primary;
    //   for (const batch of primary) {
    //     if (batch.associates) {
    //       for (const associate of batch.associates) {
    //         this.associates.push(associate);
    //       }
    //     }
    //   }
    // }
    // if (this.trainer.coTrainer) {
    //   const coTrainer = this.trainer.coTrainer;
    //   for (const batch of coTrainer) {
    //     if (batch.associates) {
    //       for (const associate of batch.associates) {
    //         this.associates.push(associate);
    //       }
    //     }
    //   }
    // }

    // this.associateService.getAllAssociates().subscribe(
    //   data => {
    //     this.associates = data;
    //     localStorage.setItem('currentAssociates', JSON.stringify(this.associates));
    //   }
    // );


  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }


  /**
   * Allows a trainer to update his information, also updates the information held in local storage
   */
  updateInfo() {
    this.statusMsg = 'Please wait while your information is updated';
    this.statusClass = 'wait';
    this.trainer.firstName = this.newFirstName;
    this.trainer.lastName = this.newLastName;
    this.trainerService.updateTrainer(this.trainer).subscribe(
      data => {
        this.statusMsg = 'Update was successful!';
        this.statusClass = 'success';
        this.formOpen = false;
        localStorage.setItem(this._TRAINER_KEY, JSON.stringify(this.trainer));
      },
      error => {
        this.statusMsg = `I'm sorry, there was an error when communicating with the server`;
        this.statusClass = 'error';
        this.formOpen = false;
      }
    );
  }
}
