import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadService } from 'src/app/service/upload.service';

@Component({
  selector: 'app-edit-reports',
  templateUrl: './edit-reports.component.html',
  styleUrls: ['./edit-reports.component.css']
})
export class EditReportsComponent implements OnInit {

  @Input() projectList: Observable<Array<string>>;
  jsFile : File;
  iterationListEdit: Observable<Array<string>>;
  filesEdit: Array<string>;
  projectEdit: string;
  iterationChoice: string;
  iterationViewShow = false;
  submittedEdit: boolean;
  completeEdit: boolean;

  constructor(private uploadService: UploadService) { }

  ngOnInit() {
    this.iterationChoice = '';
  }

  setProjectEdit(project: string) {
    this.projectEdit = project;
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
