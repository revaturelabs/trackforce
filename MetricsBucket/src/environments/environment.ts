// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  bucketName:"ccoverage",
  //accessKey: login.env.METRICSS3ACCKEY,
  //secretKey: login.env.METRICSS3SECKEY,

  accessKey: 'AKIAY4PLP5GV6ODA4YV6',
  secretKey: '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',

  region: 'us-east-1',
  s3appfolder: 'metricsbucket',
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
