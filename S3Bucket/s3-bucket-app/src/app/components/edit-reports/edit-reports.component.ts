import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadService } from 'src/app/service/upload.service';

@Component({
  selector: 'app-edit-reports',
  templateUrl: './edit-reports.component.html',
  styleUrls: ['./edit-reports.component.css']
})
export class EditReportsComponent implements OnInit {

  jsFile : File;
  @Input() projectList: Observable<Array<string>>;

  // Edit reports
  iterationListEdit: Observable<Array<string>>;
  filesEdit: Array<string>;
  fileList: File[];
  filesToDel: Array<string>;
  projectEdit: string;
  iterationChoice: string;
  iterationViewShow = false;

  // Edit validations
  submittedEdit: boolean;
  completeEdit: boolean;

  constructor(private uploadService: UploadService) { }

  ngOnInit() {
    this.iterationChoice = '';
    this.fileList=[];
    this.filesToDel=[];
  }

  // Edit Reports methods

  setProjectEdit(project: string) {
    this.projectEdit = project;
    console.log(this.projectEdit);
    this.iterationListEdit = this.uploadService.getProjectSprints(project);
    this.iterationChoice = "";
    this.filesEdit = undefined;
  }

  setIteration(iter: string) {
    this.iterationChoice = iter;
    this.filesEdit = this.uploadService.getIterationFiles(this.projectEdit, iter);

  }

  addFile(event) {
    for(let i = 0; i<event.target.files.length; i++){
      this.fileList.push(event.target.files.item(i));
      this.filesEdit.push(event.target.files.item(i).name);
    }
    
  }

  removeFile(file: string) {
    this.filesEdit = this.filesEdit.filter((value) => {

      return value != file;

    });
    this.filesToDel.push(file);
  }


  validateEdit() {
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
      [`document.write(\`<b>Files:</b> ${this.fileList.map(file => `<br><a href='report/${file.name}' target='_blank'>${file.name}</a>`)}\`)`]
      , 'files.js', { type: 'application/javascript' });
    const uservice = this.uploadService;

    this.fileList.forEach((file) => {
      uservice.uploadReport(file, this.projectEdit, this.iterationChoice + '/report/' + file.name);
    });
    this.filesToDel.forEach((file) => {
      uservice.deleteFiles(this.projectEdit, this.iterationChoice, file);
    });

    this.uploadService.uploadReport(this.jsFile, this.projectEdit, this.iterationChoice + '/files.js');
    setTimeout(() => {
      this.resetValuesEdit();
    }, 2000);
  }

  resetValuesEdit() {
    this.submittedEdit = false;
    this.filesEdit = undefined;
    this.iterationListEdit = undefined;
    this.iterationChoice = '';
    this.projectEdit = '';
  }

}
