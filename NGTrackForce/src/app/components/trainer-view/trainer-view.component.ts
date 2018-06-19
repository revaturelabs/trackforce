import { Component, OnInit } from '@angular/core';
import { Trainer } from '../../models/trainer.model';
import { AuthenticationService } from '../../services/authentication-service/authentication.service';
import { TrainerService } from '../../services/trainer-service/trainer.service';

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
