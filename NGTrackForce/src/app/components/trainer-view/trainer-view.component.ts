import { Component, OnInit } from '@angular/core';
import { Trainer } from '../../models/trainer.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { TrainerService } from '../../services/trainer-service/trainer.service';
import { Associate } from '../../models/associate.model';

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

  // ADDED SO THIS COMPILES. Once you're actually making an ajax call you'll want to have these as your success
  // and error messages.
  errMsg = false;
  succMsg = false;

  constructor(private authService: AuthenticationService, private trainerService: TrainerService) { }

  ngOnInit() {
    this.trainer = this.authService.getTrainer();
    this.newFirstName = this.trainer.firstName;
    this.newLastName = this.trainer.lastName;
  }

  getTrainerAssociates() {
    if (this.trainer.primary !== null) {
      const primary = this.trainer.primary;
      for (const batch of primary) {
        if (batch.associates !== null) {
          for (const associate of batch.associates) {
            this.associates.push(associate);
          }
        }
      }
    }
    if (this.trainer.coTrainer !== null) {
      const coTrainer = this.trainer.coTrainer;
      for (const batch of coTrainer) {
        if (batch.associates !== null) {
          for (const associate of batch.associates) {
            this.associates.push(associate);
          }
        }
      }
    }

    localStorage.setItem('currentAssociates', JSON.stringify(this.associates));

  }

  toggleForm() {
    this.formOpen = !this.formOpen;
  }



  updateInfo() {
    this.trainer.firstName = this.newFirstName;
    this.trainer.lastName = this.newLastName;
    // this.trainerService.updateTrainer(this.trainer).subscribe(
    //   data => {

    //   },
    //   err => {

    //   }
    // );
  }

}
