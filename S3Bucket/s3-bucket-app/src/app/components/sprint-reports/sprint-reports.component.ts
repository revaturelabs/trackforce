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
  link : string;
  submitted : boolean;
  

  constructor(private uploadService:UploadService) { }

  ngOnInit() {

    this.projectList = this.uploadService.getProjectList();
    this.fileList = [];
    this.project="";
    this.link = "#"; // find out link later
    this.submitted = false;
  }

  showIterations(value : string) {
    this.projectChoice = value;
    console.log(value)
    this.iterationList=this.uploadService.getProjectSprints(value);
  }

  selectFiles(event) {
    this.fileList.push(event.target.files.item(0));
    console.log(this.fileList);
  }

  setProject(value: string) {
    this.project = value.substring(0, value.length-5);
  }


  submit() {
    // send this.fileList, this.iteration, this.project, and this.index to S3 bucket
    this.submitted = true;
    this.index = new File(['<head><title>HI</title></head><body>Hello World.... MicDrop</body>'], "index.html", {type: "text/html"});
    console.log("Project: " + this.project);
    console.log("Iteration: " + this.iteration);
    console.log("Files: " + this.fileList);
    const proj = this.project;
    const iter = this.iteration;
    const uservice= this.uploadService;
    this.fileList.forEach(function(file){
      uservice.uploadReport(file, proj, iter+"/report/"+ file.name)
    });
      this.uploadService.uploadReport(this.index, this.project, this.iteration+"/index.html")
    console.log(this.index);
  }

}
