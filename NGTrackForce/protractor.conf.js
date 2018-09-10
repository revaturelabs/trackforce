// Protractor configuration file, see link for more information
// https://github.com/angular/protractor/blob/master/lib/config.ts

const { SpecReporter } = require('jasmine-spec-reporter');


exports.config = {
  allScriptsTimeout: 20000,
  specs: [
    './e2e/register-associate/register-associate.e2e-spec.ts',
    //**'./e2e/login/login.e2e-spec.ts',

    //**'./e2e/navbar/navbar.e2e-spec.ts',
    //'./e2e/create-user/create-user.e2e-spec.ts',
    //'./e2e/predictions/predictions.e2e-spec.ts',
    //**'./e2e/client-list/client-list-spec.ts',
    //   './e2e/app/app.e2e-spec.ts',
    //   './e2e/associate-list/associate-list.js',
    //  './e2e/associate-list/associate-list-search.js',

    // './e2e/associate-list/associate-list-status.js',
    // './e2e/associate-list/associate-list-curriculum.js',
    // './e2e/associate-list/associate-list-client.js',
    // './e2e/batch-list/batch-list.js',
    // './e2e/test-associate/homepage-spec.js'
  ],
  capabilities: {
    'browserName': 'chrome',
    chromeOptions: {
      args: [ "--headless", "--disable-gpu", "--window-size=800,600" ]
    }
  },
  // directConnect: true,
   baseUrl: 'http://localhost:4200/',
   //baseUrl: 'http://34.227.178.103:8090/NGTrackForce',
   //for pipeline delpoyment
     seleniumAddress: "http://127.0.0.1:4444/wd/hub",
    // SELENIUM_PROMISE_MANAGER: false,
    //
    //

  framework: 'jasmine',
  jasmineNodeOpts: {
    showColors: true,
    defaultTimeoutInterval: 30000,
    print: function() {}
  },
  onPrepare() {
    require('ts-node').register({
      project: 'e2e/tsconfig.e2e.json'
    });
    jasmine.getEnv().addReporter(new SpecReporter({ spec: { displayStacktrace: true } }));

    // require('jasmine-reporters');
    // jasmine.getEnv().addReporter(
    //   new jasmineReporters.JUnitXmlReporter('outputxmldir', true, true));
    var jasmineReporters = require('jasmine-reporters');
    jasmine.getEnv().addReporter(new jasmineReporters.JUnitXmlReporter({
        consolidateAll: true,
        savePath: 'testresults',
        filePrefix: 'xmloutput'
    }));
  },
  onComplete: function() {
    var browserName, browserVersion;
    var capsPromise = browser.getCapabilities();

    capsPromise.then(function (caps) {
       browserName = caps.get('browserName');
       browserVersion = caps.get('version');

       var HTMLReport = require('protractor-html-reporter');

       testConfig = {
           reportTitle: 'Test Execution Report',
           outputPath: './',
           screenshotPath: './screenshots',
           testBrowser: browserName,
           browserVersion: browserVersion,
           modifiedSuiteName: false,
           screenshotsOnlyOnFailure: true
       };
       new HTMLReport().from('testresults/xmloutput.xml', testConfig);
   });
 }
};
