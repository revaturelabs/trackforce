import { Injectable } from '@angular/core';
import * as S3 from 'aws-sdk/clients/s3';
import { Observable } from 'rxjs';
import { of as observableOf} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor() { }

  
  bucket = new S3(
    {
      accessKeyId: 'AKIAY4PLP5GV6ODA4YV6',//temp
      secretAccessKey: '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',//temp
      region: 'us-east-1' 
    }
  );

  uploadReport(file, project:string, filepath:string){
    
    const params = {
      Bucket: project,//temp
      Key: filepath,
      Body: file,
      ACL: 'public-read',
      ContentType: file.type

    };


    this.bucket.upload(params, function (err, data) {
      if (err) {
        console.log('There was an error uploading your file: ', err);
        return false;
      }
      console.log('Successfully uploaded file.', data);      
      return true;
    });
  }

  getProjectSprints(name:string):Observable<Array<string>>{
    console.log(name);
    const sprints = new Array<string>();

    const params = {
      Bucket: name,
      Prefix: '',
      Delimiter: '/'
    };

    this.bucket.listObjects(params, function (err, data) {
      if (err) {
        console.log('There was an error getting your files: ' + err);
        return;
      }

      console.log('Successfully get files.', data);

      data.CommonPrefixes.forEach(function (file) {
        sprints.push(file.Prefix.replace("/",""))
      });
    });

    return observableOf(sprints);

  }
}

