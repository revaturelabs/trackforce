import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sprint-reports',
  templateUrl: './sprint-reports.component.html',
  styleUrls: ['./sprint-reports.component.css']
})
export class SprintReportsComponent implements OnInit {

  projectChoice : string;

  constructor() { }

  ngOnInit() {
    this.projectChoice = "";
  }

  showTrackforce() {
    this.projectChoice = "trackforce";
  }

  showRideforce() {
    this.projectChoice = "rideforce";
  }

  showSMS() {
    this.projectChoice = "sms";
  }

  showCMS() {
    this.projectChoice = "cms";
  }

}
