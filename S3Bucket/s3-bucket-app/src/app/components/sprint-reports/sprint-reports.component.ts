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
  iterationLink : string;
  submitted : boolean;
  bucketName : string;
  inputStartDate : string;
  inputEndDate : string;
  completedStoryPoints : number;
  assignedStoryPoints : number;
  complete : boolean;
  projectSelected : boolean;
  incompleteAlert : boolean;
  incorrectDateAlert : boolean;
  incorrectStoryPointsAlert : boolean;
  
  constructor(private uploadService:UploadService) { }

  ngOnInit() {
    this.projectList = this.uploadService.getProjectList();
    this.fileList = [];
    this.project = "";
    this.submitted = false;
    this.bucketName = "ccoverage"; // temp
  }

  showIterations(value : string) {
    this.projectChoice = value;
    this.iterationList = this.uploadService.getProjectSprints(value);
  }

  selectFiles(event) {
    this.fileList.push(event.target.files.item(0));
  }

  removeFromFileList(file: File) {
    let index = this.fileList.indexOf(file);
    this.fileList.splice(index, 1);
  }

  setProject(project: string) {
    this.projectSelected = true;
    this.project = project;
    this.validate();
  }

  createLink(iter: string) {
    this.iterationLink = "https://" + this.bucketName + ".s3.amazonaws.com/" + this.projectChoice + "/" + iter + "/index.html";
  }

  validate() {
    // if start date is after end date
    if(this.getDuration() < 0) {
      this.incorrectDateAlert = true;
      this.incorrectStoryPointsAlert = false;
      this.incompleteAlert = false;
    // if completed story points are greater than assigned story points
    } else if(this.completedStoryPoints > this.assignedStoryPoints) {
      this.incorrectStoryPointsAlert = true;
      this.incorrectDateAlert = false;
      this.incompleteAlert = false;
    // if all fieldds are completed
    } else if (this.inputStartDate != undefined && this.inputStartDate != "" && 
               this.inputEndDate != undefined && this.inputEndDate != "" && 
               this.assignedStoryPoints != undefined  && this.assignedStoryPoints != null && 
               this.completedStoryPoints != undefined  && this.completedStoryPoints != null && 
               this.projectSelected && this.iteration != undefined && this.iteration != "") {
      this.complete = true;
      this.incorrectDateAlert = false;
      this.incorrectStoryPointsAlert = false;
      this.incompleteAlert= false;
    // if all fields are not complete
    } else {
      this.incompleteAlert = true;
      this.complete = false;
      this.incorrectDateAlert = false;
      this.incorrectStoryPointsAlert = false;
    }
  }

  getDuration(): number {
    let startDate = new Date(this.inputStartDate);
    let endDate = new Date(this.inputEndDate);
    let days = (endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24;
    return days;
  }

  submit() {
    // send this.fileList, this.iteration, this.project, and this.index to S3 bucket
    this.submitted = true;
    let days = this.getDuration();
    this.index = new File(
      [`
      <html>
        <head>
          <title>Sprint Report - ${this.iteration}</title>
        </head>
        <body>
          <h1>Sprint Metrics:</h1>
          <b>Project:</b> ${this.project} <br>
          <b>Iteration:</b> ${this.iteration} <br>
          <b>Start Date:</b> ${this.inputStartDate}<br>
          <b>End Date:</b> ${this.inputEndDate} <br>
          <b>Duration:</b> ${days} days <br>
          <b>Velocity:</b> ${this.completedStoryPoints}/${this.assignedStoryPoints}<br>
          <b>Files:</b> ${this.fileList.map(file => `<br><a href="report/${file.name}" target="_blank">${file.name}</a>`)}
        </body>
      </html>
      `]
      , "index.html", {type: "text/html"});
    const proj = this.project;
    const iter = this.iteration;
    const uservice= this.uploadService;
    this.fileList.forEach(function(file){
      uservice.uploadReport(file, proj, iter+"/report/"+ file.name)
    });
      this.uploadService.uploadReport(this.index, this.project, this.iteration+"/index.html")
    setTimeout( () => {
      this.resetValues();
    }, 2000);
  }

  resetValues() {
    this.submitted = false;
    this.fileList = [];
    this.project = "";
    this.iteration = "";
    this.inputStartDate = "";
    this.inputEndDate = "";
    this.assignedStoryPoints = undefined;
    this.completedStoryPoints = undefined;
  }

}
