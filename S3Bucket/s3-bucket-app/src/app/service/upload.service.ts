import { Injectable } from '@angular/core';
import * as S3 from 'aws-sdk/clients/s3';
import { Observable } from 'rxjs';
import { of as observableOf } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor() { }
  bucketName = environment.bucketName;

  bucket = new S3(
    {
      accessKeyId:  environment.accessKey,//temp
      secretAccessKey: environment.secretKey,//temp
      region: environment.region
    }
  );

  uploadReport(file, project: string, filepath: string) {

    const params = {
      Bucket: this.bucketName,
      Key: project + '/' + filepath,
      Body: file,
      ACL: 'public-read',
      ContentType: file.type

    };


    this.bucket.upload(params, (err, data) => {
      if (err) {
        return false;
      }
      return true;
    });
  }
  getProjectList(): Observable<Array<string>> {
    const projects = new Array<string>();

    const params = {
      Bucket: this.bucketName,
      Prefix: '',
      Delimiter: '/'
    };
    this.bucket.listObjects(params, (err, data) => {
      if (err) {
        return;
      }


      data.CommonPrefixes.forEach((file) => {
        console.log(file.Prefix)
        if(file.Prefix!==environment.s3appfolder+'/')//ignores s3 application on bucket
        projects.push(file.Prefix.replace('/', ''));
      });
    });

    return observableOf(projects);
  }

  getProjectSprints(project: string): Observable<Array<string>> {
    const sprints = new Array<string>();
    const params = {
      Bucket: this.bucketName,
      Prefix: project + '/',
      Delimiter: '/'
    };

    this.bucket.listObjects(params, (err, data) => {
      if (err) {
        return;
      }


      data.CommonPrefixes.forEach((file) => {
        sprints.push(file.Prefix.replace(project + '/', '').replace('/', ''));
      });
    });

    return observableOf(sprints);

  }

  getIterationFiles(project: string, iter: string): Array<string> {
    const files = new Array<string>();
    const prefix = project + '/' + iter + '/report/';
    const params = {
      Bucket: this.bucketName,
      Prefix: prefix
    };

    this.bucket.listObjects(params, (err, data) => {
      if (err) {
        return;
      }


      data.Contents.forEach((file) => {
        files.push(file.Key.replace(prefix, ''));
      });
    });

    return files;
  }

  deleteFiles(project: string, iter: string, filename: string) {
    const key = project + '/' + iter + '/report/' + filename;

    const params = {
      Bucket: this.bucketName,
      Key: key
    };

    this.bucket.deleteObject(params, (err, data) => {
      if (err) {
        return;
      }
    });
  }

  deleteIteration(project: string, iter:string){
    this.deleteIndex(project, iter);
    this.deleteJS(project, iter);
    const key = project + '/' + iter+'/';

    const params = {
      Bucket: this.bucketName,
      Key: key
    };
    
    this.bucket.deleteObject(params, (err, data) => {
      if (err) {
        return;
      }
      console.log(data)
    });
  }
  deleteIndex(project: string, iter:string){
    const key = project + '/' + iter+'/index.html';

    const params = {
      Bucket: this.bucketName,
      Key: key
    };
    
    this.bucket.deleteObject(params, (err, data) => {
      if (err) {
        return;
      }
      console.log(data)
    });
  }
  deleteJS(project: string, iter:string){
    const key = project + '/' + iter+'/files.js';

    const params = {
      Bucket: this.bucketName,
      Key: key
    };
    
    this.bucket.deleteObject(params, (err, data) => {
      if (err) {
        return;
      }
      console.log(data)
    });
  }
  


}

