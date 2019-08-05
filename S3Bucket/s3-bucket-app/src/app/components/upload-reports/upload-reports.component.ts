import { Component, OnInit, Input } from '@angular/core';
import { UploadService } from 'src/app/service/upload.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-upload-reports',
  templateUrl: './upload-reports.component.html',
  styleUrls: ['./upload-reports.component.css']
})
export class UploadReportsComponent implements OnInit {

  @Input() projectList: Observable<Array<string>>;
  fileList: File[];
  indexFile: File;
  jsFile: File;
  iteration: string;
  project: string;
  inputStartDate: string;
  inputEndDate: string;
  completedStoryPoints: number;
  assignedStoryPoints: number;
  submitted: boolean;
  complete: boolean;
  projectSelected: boolean;
  incorrectDateAlert: boolean;
  incorrectStoryPointsAlert: boolean;

  constructor(private uploadService : UploadService) { }

  ngOnInit() {
    this.project = 'Project';
    this.fileList = [];
  }

  selectFiles(event) {
    for(let i = 0; i<event.target.files.length; i++){
      this.fileList.push(event.target.files.item(i));
    }
  }

  removeFromFileList(file: File) {
    const index = this.fileList.indexOf(file);
    this.fileList.splice(index, 1);
  }

  setProject(project: string) {
    this.projectSelected = true;
    this.project = project;
    this.validate();
  }

  validate() {
    // if start date is after end date
    if (this.getDuration() < 0) {
      this.incorrectDateAlert = true;
      this.complete = false;
    } else {
      this.incorrectDateAlert = false;
    }

    // if completed story points are greater than assigned story points
    if (this.completedStoryPoints > this.assignedStoryPoints) {
      this.incorrectStoryPointsAlert = true;
      this.complete = false;
    } else {
      this.incorrectStoryPointsAlert = false;
    }
    
    // if all fields are completed
    if (this.inputStartDate && this.inputEndDate && this.assignedStoryPoints && 
        this.completedStoryPoints && this.projectSelected && this.iteration &&
        !this.incorrectStoryPointsAlert && !this.incorrectDateAlert && this.project != "Project") {
      this.complete = true;
    } else {
      this.complete = false;
    }

  }

  getDuration(): number {
    const startDate = new Date(this.inputStartDate);
    const endDate = new Date(this.inputEndDate);
    const days = (endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24;
    return days;
  }

  submit() {
    // send this.fileList, this.iteration, this.project, and this.index to S3 bucket
    this.submitted = true;
    const days = this.getDuration();
    this.indexFile = new File(
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
          <script src='files.js'></script>
        </body>
      </html>
      `]
      , 'index.html', { type: 'text/html' });
    this.jsFile = new File(
      // tslint:disable-next-line: max-line-length
      [`document.write(\`<b>Files:</b> ${this.fileList.map(file => `<br><a href='report/${file.name}' target='_blank'>${file.name}</a>`)}\`)`]
      , 'files.js', { type: 'application/javascript' });
    const proj = this.project;
    const iter = this.iteration;
    const uservice = this.uploadService;
    this.fileList.forEach((file) => {
      uservice.uploadReport(file, proj, iter + '/report/' + file.name);
    });
    this.uploadService.uploadReport(this.indexFile, this.project, this.iteration + '/index.html');
    this.uploadService.uploadReport(this.jsFile, this.project, this.iteration + '/files.js');
    setTimeout(() => {
      this.resetValues();
    }, 2000);
  }

  resetValues() {
    this.submitted = false;
    this.fileList = [];
    this.project = 'Project';
    this.iteration = '';
    this.inputStartDate = '';
    this.inputEndDate = '';
    this.assignedStoryPoints = undefined;
    this.completedStoryPoints = undefined;
  }

}
