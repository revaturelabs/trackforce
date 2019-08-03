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
  projectEdit: string;
  iterationChoice: string;
  iterationViewShow = false;

  // Edit validations
  submittedEdit: boolean;
  completeEdit: boolean;

  constructor(private uploadService: UploadService) { }

  ngOnInit() {
    this.iterationChoice = '';
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
    setTimeout(() => {
      this.resetValuesEdit();
    }, 2000);
  }

  resetValuesEdit() {
    this.submittedEdit = false;
    this.filesEdit = undefined;
    this.iterationChoice = '';
    this.projectEdit = '';
  }

}
