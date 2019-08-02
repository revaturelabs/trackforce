import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadService } from 'src/app/service/upload.service';

@Component({
  selector: 'app-view-reports',
  templateUrl: './view-reports.component.html',
  styleUrls: ['./view-reports.component.css']
})
export class ViewReportsComponent implements OnInit {

  @Input() bucketName: string;
  @Input() projectList: Observable<Array<string>>;
  projectChoice: string;
  iterationList: Observable<Array<string>>;
  iterationLink: string;
  iterationViewShow = false;

  constructor(private uploadService: UploadService) { }

  ngOnInit() {
  }

  createLink(iter: string) {
    this.iterationViewShow = true;
    this.iterationLink = 'https://' + this.bucketName + '.s3.amazonaws.com/' + this.projectChoice + '/' + iter + '/index.html';
  }

    showIterations( ) {
      this.iterationList = this.uploadService.getProjectSprints(this.projectChoice);
    }


}
