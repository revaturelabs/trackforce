import { Component, OnInit } from '@angular/core';
import { UploadService } from 'src/app/service/upload.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-sprint-reports',
  templateUrl: './sprint-reports.component.html',
  styleUrls: ['./sprint-reports.component.css']
})

export class SprintReportsComponent implements OnInit {

  projectList: Observable<Array<string>>;

  constructor(
    private uploadService : UploadService) { }

  ngOnInit() {
    this.projectList = this.uploadService.getProjectList();
  }

}
