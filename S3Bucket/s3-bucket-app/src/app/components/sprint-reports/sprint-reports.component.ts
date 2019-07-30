import { Component, OnInit } from '@angular/core';
import { UploadService } from 'src/app/service/upload.service';
import { Observable } from 'rxjs';

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
  iterationList: Observable<Array<string>>;
  project : string;

  

  constructor(private uploadService:UploadService) { }

  ngOnInit() {
    this.projectList = ["trackforce", "rideforce", "sms", "cms"];
    this.fileList = [];
    this.project="";
  }

  showIterations(value : string) {
    this.projectChoice = value;
    console.log(value)
    this.iterationList=this.uploadService.getProjectSprints(value);
  }

  selectFiles(event) {
    this.fileList.push(event.target.files.item(0));
    console.log(this.fileList);
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
