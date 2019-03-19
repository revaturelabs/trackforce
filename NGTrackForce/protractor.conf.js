// Protractor configuration file, see link for more information
// https://github.com/angular/protractor/blob/master/lib/config.ts

const { SpecReporter } = require('jasmine-spec-reporter');
var HtmlScreenshotReporter = require('protractor-jasmine2-screenshot-reporter');

var reporter = new HtmlScreenshotReporter({
  dest: 'testresults/screenshots',
  filename: 'my-report.html',
  captureOnlyFailedSpecs: true
});

exports.config = {
  allScriptsTimeout: 20000,
  specs: [
    //ALL JASMINE TESTS PASSING JAMIR & BRANDON -1901SDET (tests must be in this order currently)
    './e2e/associate-list/associate-list-pagenation.js',  //Test for Association Pagenation functionality
    './e2e/associate-list/associate-list-search.js',
    './e2e/associate-list/associate-list-status.js',
    './e2e/associate-list/associate-list-client.js',

    //PROTRACTOR TESTS
    './e2e/register-associate/register-associate.e2e-spec.ts',
    './e2e/login/login.e2e-spec.ts',
    './e2e/navbar/navbar.e2e-spec.ts',
    './e2e/create-user/create-user.e2e-spec.ts',
    './e2e/client-list/client-list-spec.ts',
    './e2e/batch-list/batch-list.e2e-spec.ts',
    './e2e/predictions/predictions.e2e-spec.ts',
    
    //added already written tests to suite
    './src/app/pipes/search-filter/search-filter.pipe.spec.ts'
       
  ],
  beforeLaunch: function() {
    return new Promise(function(resolve){
      reporter.beforeLaunch(resolve);
    });
  },

  //test both chrome and firefox
  // multiCapabilities: [{
  //   'browserName': "chrome", 
  //   chromeOptions: {
  //     args: [ "--headless", "--disable-gpu", "--window-size=2000,2000" ]
  //   }
  // },{
  //   'browserName': "firefox", 
  //   'moz:firefoxOptions': {
  //     args: ["--headless"]
  //   }
  // }],

  //test only firefox
  // capabilities: {
  //   'browserName':'firefox',
  //   'moz:firefoxOptions': {
  //     args: ["--headless"]
  //   }
  // },
  //test only chrome
  capabilities: {
    'browserName': 'chrome',
    chromeOptions: {
      args: [ "--headless", "--disable-gpu", "--window-size=1000,1000" ]
      // args: [ "--disable-gpu", "--window-size=2000,2000" ]
    }
  },
  
  // baseUrl: 'http://trackforce.revaturelabs.com/NGTrackForce',
  
  //for pipeline delpoyment
  //change direct connect to false
  directConnect: false,
  // seleniumAddress: "http://127.0.0.1:4444/wd/hub",
  seleniumAddress: "http://localhost:4444/wd/hub",
  // SELENIUM_PROMISE_MANAGER: false,

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
    browser.manage().timeouts().implicitlyWait(10000);
    // browser.driver.manage().window().maximize();
    // require('jasmine-reporters');
    // jasmine.getEnv().addReporter(
    //   new jasmineReporters.JUnitXmlReporter('outputxmldir', true, true));
    var jasmineReporters = require('jasmine-reporters');
    jasmine.getEnv().addReporter(reporter);
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
 },
 afterLaunch: function(exitCode) {
    return new Promise(function(resolve){
      reporter.afterLaunch(resolve.bind(this, exitCode));
    });
  }
};
