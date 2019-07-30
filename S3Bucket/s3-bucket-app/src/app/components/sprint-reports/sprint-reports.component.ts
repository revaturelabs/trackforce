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
  index : File;
  iteration : string;
  project : string;

  constructor() { }

  ngOnInit() {
    this.projectList = ["Trackforce", "Rideforce", "SMS", "CMS"];
    this.fileList = [];
    this.project = '';
  }

  showIterations(value : string) {
    this.projectChoice = value;
  }

  selectFiles(event) {
    this.fileList.push(event.target.files.item(0));
  }

  setProject(value: string) {
    this.project = value;
  }

  submit() {
    // send this.fileList, this.iteration, this.project, and this.index to S3 bucket
    this.index = new File([""], "index.html", {type: "text/html"});
    console.log("Project: " + this.project);
    console.log("Iteration: " + this.iteration);
    console.log("Files: " + this.fileList);
    console.log(this.index);
  }

}
