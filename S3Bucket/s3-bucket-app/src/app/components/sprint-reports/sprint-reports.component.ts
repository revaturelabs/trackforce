import { Component, OnInit } from '@angular/core';
import { UploadService } from 'src/app/service/upload.service';
import { Observable } from 'rxjs';
import { FnParam } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-sprint-reports',
  templateUrl: './sprint-reports.component.html',
  styleUrls: ['./sprint-reports.component.css']
})

export class SprintReportsComponent implements OnInit {

  projectList : Observable<Array<string>>;
  projectChoice : string;
  fileList : File[];
  index : File;
  iteration : string;
  iterationList: Observable<Array<string>>;
  project : string;
  link : string;
  submitted : boolean;
  bucketName : string;
  startDate: Date;
  endDate: Date;
  completedStoryPoints: number;
  assignedStoryPoints: number;
  

  constructor(private uploadService:UploadService) { }

  ngOnInit() {
    this.projectList = this.uploadService.getProjectList();
    this.fileList = [];
    this.project= "";
    this.submitted = false;
    this.bucketName = "ccoverage"; // temp
  }

  showIterations(value : string) {
    this.projectChoice = value;
    console.log(value)
    this.iterationList=this.uploadService.getProjectSprints(value);
  }

  selectFiles(event) {
    this.fileList.push(event.target.files.item(0));
  }

  setProject(project: string) {
    this.project = project;
  }

  createLink(iter: string) {
    this.link = "https://" + this.bucketName + ".s3.amazonaws.com/" + this.projectChoice + "/" + iter + "/index.html";
  }


  submit() {
    // send this.fileList, this.iteration, this.project, and this.index to S3 bucket
    this.submitted = true;
    const days = (this.endDate.getTime()-this.startDate.getTime())*1000*60*60*24
    const velocity = (this.completedStoryPoints-this.assignedStoryPoints)/days;
    this.index = new File(
      [`
      <html>
        <head>
          <title>${this.iteration}</title>
        </head>
        <body>
          <h1>Sprint Metrics:</h1>
          <b>Project:</b> ${this.project} <br>
          <b>Iteration:</b> ${this.iteration} <br>
          <b>Velocity:</b> ${velocity} <br>
          <b>Start Date:</b> ${this.startDate.toUTCString} <br>
          <b>End Date:</b> ${this.endDate.toUTCString} <br>
          <b>Story Points:</b> ${this.completedStoryPoints}/${this.assignedStoryPoints}<br>
          <b>Files:</b>${this.fileList.map(file => `<a href="report/${file.name}" target="_blank">${file.name}</a>`)}
        </body>
      </html>
      `]
      , "index.html", {type: "text/html"});
    console.log("Project: " + this.project);
    console.log("Iteration: " + this.iteration);
    console.log("Files: " + this.fileList);
    const proj = this.project;
    const iter = this.iteration;
    const uservice= this.uploadService;
    this.fileList.forEach(function(file){
      uservice.uploadReport(file, proj, iter+"/report/"+ file.name)
    });
      this.uploadService.uploadReport(this.index, this.project, this.iteration+"/index.html")
    console.log(this.index);
    setTimeout( () => {
      this.resetValues();
    }, 2000);
  }

  resetValues() {
    this.submitted = false;
    this.fileList = [];
    this.project = "";
    this.iteration = "";
  }

}
