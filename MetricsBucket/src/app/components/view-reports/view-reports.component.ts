import { Component, OnInit, Input, ViewEncapsulation, ElementRef, PipeTransform, Pipe } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadService } from 'src/app/service/upload.service';
import { DomSanitizer } from '@angular/platform-browser';
import { environment } from 'src/environments/environment.prod';

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

  @Input() projectList: Observable<Array<string>>;
  bucketName: string;
  projectChoice: string;
  iterationList: string[];
  iterationLink: string;
  selectedIteration: string;
  iterationViewShow = false;

  constructor(private uploadService: UploadService) { }

  ngOnInit() {
    this.bucketName = environment.bucketName;
    this.projectChoice = 'Select Project';
    this.selectedIteration = ' Select Iteration';
  }

  createLink(iter: string) {
    this.iterationViewShow = true;
    this.selectedIteration = iter;
    // tslint:disable-next-line: max-line-length
    this.iterationLink = 'https://' + this.bucketName + '.s3.amazonaws.com/' + this.projectChoice + '/' + iter + '/index.html';
  }

  showIterations(project: string) {
    this.selectedIteration = 'Select Iteration';
    this.projectChoice = project;
    this.uploadService.getProjectSprints(project).subscribe(iter => {
      this.iterationList = iter;
    });
    this.iterationViewShow = false;
  }

  resetValues() {
    this.iterationViewShow = false;
    this.iterationList = undefined;
    this.projectChoice = 'Select Project';
    this.selectedIteration = 'Select Iteration';
  }

}
