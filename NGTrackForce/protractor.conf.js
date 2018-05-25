// Protractor configuration file, see link for more information
// https://github.com/angular/protractor/blob/master/lib/config.ts

const { SpecReporter } = require('jasmine-spec-reporter');

exports.config = {
  allScriptsTimeout: 11000,
  specs: [
    './e2e/login/login.e2e-spec.ts',
    './e2e/navbar/navbar.e2e-spec.ts',
    './e2e/create-user/create-user.e2e-spec.ts',
    './e2e/client-list/client-list-spec.ts',
    './e2e/app/app.e2e-spec.ts',
    //'./e2e/batch-list/batch-list.e2e-spec.ts',
  ],
  capabilities: {
    'browserName': 'chrome'
  },
  directConnect: true,
  baseUrl: 'http://localhost:4200/',
  //baseUrl: 'http://52.207.66.231:4200/',
 
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
  }
};
