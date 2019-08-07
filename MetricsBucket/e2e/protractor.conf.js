// @ts-nocheck

// Protractor configuration file, see link for more information
// https://github.com/angular/protractor/blob/master/lib/config.ts

const {
  SpecReporter
} = require('jasmine-spec-reporter');
const jasmineReporters = require('jasmine-reporters');
const HTMLReport = require('protractor-html-reporter-2');
const fs = require('fs-extra');

/**
 * @type { import("protractor").Config }
 */
exports.config = {
  allScriptsTimeout: 11000,
  specs: [
     //'./src/**/*.e2e-spec.ts',
     './src/upload-report/uploadReport.e2e-spec.ts',
    './src/view-report/viewReport.e2e-spec.ts',
    './src/edit-report/editReport.e2e-spec.ts'
  ],

  capabilities: {
    'browserName': 'chrome',
    chromeOptions: {
      // args: ['start-maximized'],
      args: [ "--headless", "--disable-gpu", "--window-size=1000,1000" ]
    }
  },

  directConnect: false,
  seleniumAddress: 'http://localhost:4444/wd/hub/',
  framework: 'jasmine',

  jasmineNodeOpts: {
    showColors: true,
    defaultTimeoutInterval: 30000,
    print: function () {}
  },

  onPrepare() {
    require('ts-node').register({
      project: 'e2e/tsconfig.json'
    });

    fs.emptyDir('e2e/_report', (err) => {
      err && console.log(err);
    });

    const jasmineEnv = jasmine.getEnv();

    const specReporter = new SpecReporter({
      spec: {
        displayStacktrace: true
      }
    });

    const xmlReporter = new jasmineReporters.JUnitXmlReporter({
      consolidateAll: true,
      savePath: './e2e/_report',
      filePrefix: 'e2exmlresults'
    });

    const screenshotReporter = {
      specDone: function (result) {
        if (result.status === 'failed') {
          browser.getCapabilities().then(function (caps) {
            const browserName = caps.get('browserName');

            browser.takeScreenshot().then(function (png) {
              const stream = fs.createWriteStream('e2e/_report/' + browserName + '-' + result.fullName + '.png');
              stream.write(new Buffer(png, 'base64'));
              stream.end();
            });
          });
        }
      }
    };

    jasmineEnv.addReporter(specReporter);
    jasmineEnv.addReporter(xmlReporter);
    jasmineEnv.addReporter(screenshotReporter);
  },

  //HTMLReport called once tests are finished
  onComplete: function () {
    let browserName, browserVersion, platform;
    const capsPromise = browser.getCapabilities();

    capsPromise.then(function (caps) {
      browserName = caps.get('browserName');
      browserVersion = caps.get('version');
      platform = caps.get('platform');

      const testConfig = {
        reportTitle: 'Protractor Test Report',
        outputPath: './e2e/_report',
        outputFilename: 'ProtractorTestReport',
        screenshotPath: '.',
        testBrowser: browserName,
        browserVersion: browserVersion,
        modifiedSuiteName: false,
        screenshotsOnlyOnFailure: true,
        testPlatform: platform
      };
      new HTMLReport().from('e2e/_report/e2exmlresults.xml', testConfig);
    });
  }

};
