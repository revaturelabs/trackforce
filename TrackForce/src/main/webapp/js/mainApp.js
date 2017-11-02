var mainApp = angular.module('mainApp', [ 'ngRoute', 'chart.js' ]);

mainApp.config(function($routeProvider) {
	$routeProvider

	// Home Page route
	.when("/", {
		templateUrl : "home.html",
		controller : "mainCtrl"
	})

	// Mapped View Page route
	.when("/batchListing", {
		templateUrl : "batchListing.html",
		controller : "batchCtrl"
	})

	// Unmapped View Page route
	.when("/batchDetails", {
		templateUrl : "batchDetails.html",
		controller : "batchCtrl"
	})

});

mainApp.controller("mainCtrl", function mainCtrl($scope, $http) {

})

mainApp.controller("batchCtrl", function($scope) {
	$scope.labels = [ '2006', '2007', '2008', '2009', '2010', '2011', '2012' ];
	$scope.series = [ 'Series A', 'Series B' ];

	$scope.data = [ [ 65, 59, 80, 81, 56, 55, 40 ],
			[ 28, 48, 40, 19, 86, 27, 90 ] ];
});
