import { Component, OnInit, Input, ViewEncapsulation, ElementRef, PipeTransform, Pipe } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadService } from 'src/app/service/upload.service';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({ name: 'safe' })
export class SafePipe implements PipeTransform {
  constructor(private sanitizer: DomSanitizer) { }
  transform(url) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
}


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
  selectedIteration: string;
  iterationViewShow = false;

  constructor(private uploadService: UploadService) { }

  ngOnInit() {
  }

  createLink() {
    this.iterationViewShow = true;
    // tslint:disable-next-line: max-line-length
    this.iterationLink = 'https://' + this.bucketName + '.s3.amazonaws.com/' + this.projectChoice + '/' + this.selectedIteration + '/index.html';
  }

  showIterations() {
    this.iterationList = this.uploadService.getProjectSprints(this.projectChoice);
  }


}
