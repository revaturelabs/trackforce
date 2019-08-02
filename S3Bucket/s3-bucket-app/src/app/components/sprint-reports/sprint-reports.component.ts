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

  // View reports
  bucketName: string;
  projectList: Observable<Array<string>>;

  // Upload reports
  fileList: File[];
  indexFile: File;
  jsFile: File;
  iteration: string;
  project: string;

  // Upload validations
  inputStartDate: string;
  inputEndDate: string;
  completedStoryPoints: number;
  assignedStoryPoints: number;
  submitted: boolean;
  complete: boolean;
  projectSelected: boolean;
  incompleteAlert: boolean;
  incorrectDateAlert: boolean;
  incorrectStoryPointsAlert: boolean;

  // Edit reports
  iterationListEdit: Observable<Array<string>>;
  filesEdit: Array<string>;
  projectEdit: string;
  fileListEdit: File[];
  iterationChoice: string;
  iterationViewShow = false;

  // Edit validations
  submittedEdit: boolean;
  completeEdit: boolean;

  constructor(private uploadService: UploadService) { }

  ngOnInit() {
    this.projectList = this.uploadService.getProjectList();
    this.fileList = [];
    this.fileListEdit = [];
    this.project = '';
    this.iterationChoice = '';
    this.bucketName = 'ccoverage'; // temp
  }

  // Upload Report methods

  selectFiles(event) {
    this.fileList.push(event.target.files.item(0));
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
      this.incorrectStoryPointsAlert = false;
      this.incompleteAlert = false;
      // if completed story points are greater than assigned story points
    } else if (this.completedStoryPoints > this.assignedStoryPoints) {
      this.incorrectStoryPointsAlert = true;
      this.incorrectDateAlert = false;
      this.incompleteAlert = false;
      // if all fieldds are completed
    } else if (this.inputStartDate != undefined && this.inputStartDate != '' &&
      this.inputEndDate != undefined && this.inputEndDate != '' &&
      this.assignedStoryPoints != undefined && this.assignedStoryPoints != null &&
      this.completedStoryPoints != undefined && this.completedStoryPoints != null &&
      this.projectSelected && this.iteration != undefined && this.iteration != '') {
      this.complete = true;
      this.incorrectDateAlert = false;
      this.incorrectStoryPointsAlert = false;
      this.incompleteAlert = false;
      // if all fields are not complete
    } else {
      this.incompleteAlert = true;
      this.complete = false;
      this.incorrectDateAlert = false;
      this.incorrectStoryPointsAlert = false;
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
    this.project = '';
    this.iteration = '';
    this.inputStartDate = '';
    this.inputEndDate = '';
    this.assignedStoryPoints = undefined;
    this.completedStoryPoints = undefined;
  }

  // Edit Reports methods

  setProjectEdit(project: string) {
    this.projectEdit = project;
    console.log(this.projectEdit);
    this.iterationListEdit = this.uploadService.getProjectSprints(project);
  }

  setIteration(iter: string) {
    this.iterationChoice = iter;
    this.filesEdit = this.uploadService.getIterationFiles(this.projectEdit, iter);

  }

  addFile(event) {
    const newFile = event.target.files.item(0);
    this.filesEdit.push(newFile.name);
    this.uploadService.uploadReport(event.target.files.item(0), this.projectEdit, this.iterationChoice + '/report/' + newFile.name);
  }

  removeFile(file: string) {
    this.filesEdit = this.filesEdit.filter((value) => {

      return value != file;

    });
    this.uploadService.deleteFiles(this.projectEdit, this.iterationChoice, file);
  }


  validateEdit() {
    console.log(this.fileListEdit.length);
    // TODO: validate that there is at least one file in filelist, even after delete
    if (this.iterationChoice != undefined) {
      this.completeEdit = true;
    } else {
      this.completeEdit = false;
    }
  }

  submitEdit() {
    this.submittedEdit = true;
    this.jsFile = new File(
      [`document.write(\`<b>Files:</b> ${this.filesEdit.map(file => `<br><a href='report/${file}' target='_blank'>${file}</a>`)}\`)`]
      , 'files.js', { type: 'application/javascript' });
    this.uploadService.uploadReport(this.jsFile, this.projectEdit, this.iterationChoice + '/files.js');

  }

  resetValuesEdit() {
    this.submittedEdit = false;
  }
}
