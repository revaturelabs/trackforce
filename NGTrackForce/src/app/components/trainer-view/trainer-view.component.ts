import { Component, OnInit } from '@angular/core';
import { Trainer } from '../../models/trainer.model';

@Component({
  selector: 'app-trainer-view',
  templateUrl: './trainer-view.component.html',
  styleUrls: ['./trainer-view.component.css']
})
export class TrainerViewComponent implements OnInit {

  public trainer: Trainer;

  constructor() { }

  ngOnInit() {
  }

}
