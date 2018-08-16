// Karma configuration file, see link for more information
// https://karma-runner.github.io/1.0/config/configuration-file.html

module.exports = function (config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine', '@angular-devkit/build-angular'],
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'),
      require('karma-phantomjs-launcher'),
      require('karma-jasmine-html-reporter'),
      require('karma-coverage-istanbul-reporter'),
      require('@angular-devkit/build-angular/plugins/karma')
    ],
    client:{
      captureConsole: true,
      clearContext: false // leave Jasmine Spec Runner output visible in browser
    },
    coverageIstanbulReporter: {
      dir: require('path').join(__dirname, 'coverage'), reports: [ 'html', 'lcovonly' ],
      fixWebpackSourcePaths: true
    },

    reporters: ['progress', 'kjhtml'],
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    browserNoActivityTimeout: 60000,
    autoWatch: true,
    customLauncher: {
     ChromeHeadless: {
       base: 'Chrome',
       flags: [
         '--headless--',
         '--disable-gpu',
          '--no-sandbox',
          '--remote-debugging-port-9222'
       ]
     }
   },
    browsers: ['ChromeHeadless'],
    // singleRun is true so it'll work on the pipeline, please don't change it <3
    singleRun: true    //TODO: change this back to true before deploying.
  });
};
