import { Component, OnInit } from '@angular/core';
import { Trainer } from '../../models/trainer.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { TrainerService } from '../../services/trainer-service/trainer.service';
import { Associate } from '../../models/associate.model';
import {AssociateService} from "../../services/associate-service/associate.service";
import { LocalStorageUtils } from '../../constants/local-storage';

@Component({
  selector: 'app-trainer-view',
  templateUrl: './trainer-view.component.html',
  styleUrls: ['./trainer-view.component.css']
})
export class TrainerViewComponent implements OnInit {
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
        localStorage.setItem(LocalStorageUtils.CURRENT_TRAINER_KEY, JSON.stringify(this.trainer));
      },
      error => {
        this.statusMsg = `I'm sorry, there was an error when communicating with the server`;
        this.statusClass = 'error';
        this.formOpen = false;
      }
    );
  }
}
