// Karma configuration
// Generated on Thu Nov 02 2017 11:11:03 GMT-0400 (Eastern Daylight Time)

module.exports = function(config) {
  config.set({

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '',


    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['jasmine'],


    // list of files / patterns to load in the browser
    files: [
      'https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.js',
      'https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.6/angular-route.js',
      'https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.6/angular-mocks.js',
      'https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js',
      'http://cdn.jsdelivr.net/angular.chartjs/latest/angular-chart.min.js',
      'js/*.js',
      'test/spec/*.js'
    ],


    // list of files to exclude
    exclude: [
    ],
	
	// To capture console in terminal
	client : {
        captureConsole : true
    },


    // preprocess matching files before serving them to the browser
    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
    preprocessors: {
    },


    // test results reporter to use
    // possible values: 'dots', 'progress'
    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['progress'],


    // web server port
    port: 9876,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_INFO,

	// To show console.log in terminal
	browserConsoleLogOptions: {
		terminal: true,
		level: 'log'
	},

    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,


    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    browsers: ['Chrome', 'Firefox'],


    // Continuous Integration mode
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: false,

    // Concurrency level
    // how many browser should be started simultaneous
    concurrency: Infinity
  })
}
