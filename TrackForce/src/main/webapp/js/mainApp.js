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

mainApp.controller("batchCtrl", function($scope, $http) {
	
	$scope.getBatches = function($http) {
		// Simple GET request example:
		$http({
		  method: 'GET',
		  url: 'http://localhost:8080/TrackForce/batches'
		}).then(function successCallback(response) {
		    // this callback will be called asynchronously
		    // when the response is available
			$scope.batches = response.data;
		}, function errorCallback(response) {
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
		})
		
	};
	
	$scope.getCountPerBatchType = function($http) {
		// Simple GET request example:
		$http({
		  method: 'GET',
		  url: 'http://localhost:8080/TrackForce/batches/type'
		}).then(function successCallback(response) {
		    // this callback will be called asynchronously
		    // when the response is available
			$scope.amountType = response.data;
		}, function errorCallback(response) {
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
		})
		
	};
	
	$scope.getBatchAssociates = function($http) {
		// Simple GET request example:
		$http({
		  method: 'GET',
		  url: 'http://localhost:8080/TrackForce/batches/' + $scope.batchname + '/associates'
		}).then(function successCallback(response) {
		    // this callback will be called asynchronously
		    // when the response is available
			$scope.associatesBatch = response.data;
		}, function errorCallback(response) {
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
		})
		
	};
	
	$scope.getBatchInfo = function($http) {
		// Simple GET request example:
		$http({
		  method: 'GET',
		  url: 'http://localhost:8080/TrackForce/batches' + $scope.batchname + '/info'
		}).then(function successCallback(response) {
		    // this callback will be called asynchronously
		    // when the response is available
			$scope.batchInfo = response.data;
		}, function errorCallback(response) {
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
		})
		
	};
	
	$scope.getMapStatusBatch = function($http) {
		// Simple GET request example:
		$http({
		  method: 'GET',
		  url: 'http://localhost:8080/TrackForce/batches' + $scope.batchname + '/batchChart'
		}).then(function successCallback(response) {
		    // this callback will be called asynchronously
		    // when the response is available
			$scope.batchMapStatus = response.data;
		}, function errorCallback(response) {
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
		})
		
	};
	
	
	
	$scope.labels = [ '2006', '2007', '2008', '2009', '2010', '2011', '2012' ];
	$scope.series = [ 'Series A', 'Series B' ];

	$scope.data = [ [ 65, 59, 80, 81, 56, 55, 40 ],
			[ 28, 48, 40, 19, 86, 27, 90 ] ];
});
