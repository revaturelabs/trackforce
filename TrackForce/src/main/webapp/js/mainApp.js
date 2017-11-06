// Configure $routeProvider to create a Single Page Application
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

mainApp.controller("mainCtrl", function ($scope) {
	  $scope.labels = ["Mapped", "Unmapped"];
	  $scope.data = [500, 100];
	  
	  $scope.labels2 = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];

	  $scope.data2 = [65, 59, 80, 81, 56, 55, 40]
	  
	  $scope.labels3 = ["January", "February", "March", "April", "May", "June", "July"];
	  $scope.data3 = [28, 48, 40, 19, 86, 27, 90]
	  $scope.onClick = function (points, evt) {
	    console.log(points, evt);
	  };
	  $scope.datasetOverride = {yAxisID: 'y-axis-1'};
	  $scope.options3 = {
	    scales: {
	      yAxes: [
	        {
	          id: 'y-axis-1',
	          type: 'linear',
	          display: true,
	          position: 'left'
	        }
	      ]
	    }
	  };
});

mainApp.controller("batchCtrl", function($scope, $http) {
	$scope.batches = 'hello';
	$scope.getBatches = function() {
		console.log($scope.fromdate);
		console.log($scope.todate);
		var fromdate = new Date($scope.fromdate);
		var todate = new Date($scope.todate);
		
		// Simple GET request example:
		$http({
			method : 'GET',
			url : 'http://localhost:8080/TrackForce/track/' + 'batches/' + fromdate.getTime() + '/' + todate.getTime(),
		    headers: {'Content-Type': 'application/json' }
		}).then(function(success) {
			$scope.batches = data;
			console.log($scope.batches);
		}, function(error) {
			console.log('Error in doing http request')
		});
	};
	$scope.getCountPerBatchType = function($http) {
		// Simple GET request example:
		$http({
			method : 'GET',
			url : 'http://localhost:8080/TrackForce/track/batches/type'
		}).then(function successCallback(response) {
			// this callback will be called asynchronously
			// when the response is available
			$scope.amountType = response.data;
		}, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
			$scope.amountType = {
				"JTA_SDET" : "2",
				".NET" : "3"
			}
		})
	};
	$scope.getBatchAssociates = function($http) {
		// Simple GET request example:
		$http(
				{
					method : 'GET',
					url : 'http://localhost:8080/TrackForce/track/batches/'
							+ $scope.batchname + '/associates'
				}).then(function successCallback(response) {
			// this callback will be called asynchronously
			// when the response is available
			$scope.associatesBatch = response.data;
		}, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
			$scope.assosicatesBatch = {
				"firstname" : "Raul",
				"lastname" : "Dummy-Data",
				"associateId" : "000"
			};
		})
	};
	$scope.getBatchInfo = function($http) {
		// Simple GET request example:
		$http(
				{
					method : 'GET',
					url : 'http://localhost:8080/TrackForce/track/batches'
							+ $scope.batchname + '/info'
				}).then(function successCallback(response) {
			// this callback will be called asynchronously
			// when the response is available
			$scope.batchInfo = response.data;
		}, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
			$scope.batchInfo = {
				"startdate" : "09/11/2017",
				"enddate" : "11/17/2017"
			};
		})
	};
	$scope.getMapStatusBatch = function() {
		// Simple GET request example:
		$http(
				{
					method : 'GET',
					url : 'http://localhost:8080/TrackForce/track/batches/'
							+ '1707 Jul10 PEGA' + '/batchChart'
				}).then(function successCallback(response) {
			// this callback will be called asynchronously
			// when the response is available
			var batchMapStatus = response.data;
			$scope.mappedOrUnmapped = ['Mapped', 'Unmapped'];
			$scope.data4 = [batchMapStatus.mappedCount, batchMapStatus.unmappedCount];
		}, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
			$scope.batchMapSatus = {
				"Mapped" : "0",
				"Unmapped" : "0"
			}
		})
	};
	$scope.labels = ["Mapped","Unmapped"]
	$scope.data = ["70","61"]; 
	$scope.options = {
		scales : {
			yAxes : [ {
				ticks : {
					beginAtZero : true
				}
			} ]
		}
	};
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
