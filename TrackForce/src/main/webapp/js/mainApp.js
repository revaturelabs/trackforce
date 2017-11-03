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
	}).when("/clientDetails", {
		templateUrl : "clientDetails.html",
		controller : "clientCtrl"
	})
});
mainApp.controller("batchCtrl", function($scope) {
	$scope.labels = [ '2006', '2007', '2008', '2009', '2010', '2011', '2012' ];
	$scope.series = [ 'Series A', 'Series B' ];
	$scope.data = [ [ 65, 59, 80, 81, 56, 55, 40 ],
			[ 28, 48, 40, 19, 86, 27, 90 ] ];
});
// Controller used for the search bar function
mainApp.controller("clientSearchAndListCtrl", function($scope, $http) {
	/*
	 * This function will return a JavaScript object that contains all of the
	 * client name and their id numbers
	 */
	$scope.getAllClientNames = function() {
		$http({
			method : "GET",
			url : "http://localhost:8080/TrackForce/track/clients"
		}).then(function(response) {
			$scope.clients = response.data;
			console.log(response.data);
		});
	}
});
// This controller is used for generating charts for the client page
mainApp.controller("clientCtrl", function($scope, $http) {
	// This function will create a chart for all of the clients data
	$scope.getAllClients = function() {
		$http({
			method : "GET",
			url : "http://localhost:8080/TrackForce/track/clients/info"
		}).then(
				function(response) {
					// A JavaScript object is created from the client object
					// that is sent from the REST service
					var clients = response.data;
					// This $scope variable binds the data in the client name to
					// the header above the chart on the HTML
					$scope.clientName = clients.name;
					// This will bind an array of strings to the x-axis of the
					// bar chart
					$scope.labels = [ 'Training', 'Reserved - Mapped',
							'Reserved - Unmapped', 'Selected - Mapped',
							'Selected - Unmapped', 'Confirmed - Mapped',
							'Confirmed - Unmapped', 'Deployed - Mapped',
							'Deployed - Unmapped' ];
					// The clients JavaScript object is used for the data it
					// contains which is then bound to the chart
					$scope.data = [ clients.trainingMapped,
							clients.reservedMapped, clients.reservedUnmapped,
							clients.selectedMapped, clients.selectedUnmapped,
							clients.confirmedMapped, clients.confirmedUnmapped,
							clients.deployedMapped, clients.deployedUnmapped ];
				});
	}
	/*
	 * This function will send a search value to the REST service as a path
	 * param in order to find a single client. Once the client object is
	 * received the graph should reflect the changes.
	 */
	$scope.getOneClient = function(searchValue) {
		$http(
				{
					method : "GET",
					url : "http://localhost:8080/TrackForce/track/clients/"
							+ searchValue
				}).then(
				function(response) {
					console.log(response.status);
					console.log(response.data);
					var clients = response.data;
					$scope.clientName = clients.name;
					$scope.labels = [ 'Training', 'Reserved - Mapped',
							'Reserved - Unmapped', 'Selected - Mapped',
							'Selected - Unmapped', 'Confirmed - Mapped',
							'Confirmed - Unmapped', 'Deployed - Mapped',
							'Deployed - Unmapped' ];
					$scope.data = [ clients.trainingMapped,
							clients.reservedMapped, clients.reservedUnmapped,
							clients.selectedMapped, clients.selectedUnmapped,
							clients.confirmedMapped, clients.confirmedUnmapped,
							clients.deployedMapped, clients.deployedUnmapped ];
				});
	}
});
