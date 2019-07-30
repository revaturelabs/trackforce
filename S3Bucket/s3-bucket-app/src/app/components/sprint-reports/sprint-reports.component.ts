import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sprint-reports',
  templateUrl: './sprint-reports.component.html',
  styleUrls: ['./sprint-reports.component.css']
})

export class SprintReportsComponent implements OnInit {

  projectList : string[];
  projectChoice : string;
  fileList : File[];
  iteration : string;
  project : string;

  constructor() { }

  ngOnInit() {
    this.projectList = ["Trackforce", "Rideforce", "SMS", "CMS"];
    this.fileList = [];
  }

  showIterations(value : string) {
    this.projectChoice = value;
  }

  selectFiles(event) {
    this.fileList.push(event.target.files.item(0));
    console.log(this.fileList);
  }

  setProject(value: string) {
    this.project = value;
  }

  submit() {
    // send this.fileList and this.iteration and this.project to S3 bucket
    console.log("Project: " + this.project);
    console.log("Iteration: " + this.iteration);
    console.log("Files: " + this.fileList);
  }

}
