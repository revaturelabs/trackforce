## S3 Metrics Bucket App Instructions:

1. npm install -y
2. Set system variables with AMI credentials (may need to restart computer after setting)
        METRICSS3ACCKEY = [your-access-key]
        METRICSS3SECKEY = [your-secret-key]
3. set environment.prod.ts and environment.ts variables
        bucketName      (name of bucket)
        region	        (check region name and use region column: 
                            https://docs.aws.amazon.com/general/latest/gr/rande.html)
        s3appfolder     (the folder you want to send build application to)
4. ng build --baseHref="./"
5. Install and Configure the AWS CLI (https://aws.amazon.com/getting-started/tutorials/backup-to-s3-cli/)
6. aws s3 cp [path/to/angular/dist/appname] s3://[bucketName]/ --recursive


# S3BucketApp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 8.0.4.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
