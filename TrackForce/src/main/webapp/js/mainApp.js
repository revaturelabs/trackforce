/**
 * @namespace mainApp
 * @description Start the main module to be used for angular app
 */
var mainApp = angular.module('mainApp', [ 'ngRoute', 'chart.js' ]);
/**
 * @function config
 * @memberof mainApp
 * @param {service}
 *            routeprovider
 * @description Configure $routeProvider to create a Single Page Application
 */
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
	}).when("/clientMapped", {
		templateUrl : "clientMapped.html",
		controller : "clientMappedCtrl"
	}).when("/skillset", {
		templateUrl : "skillset.html",
		controller : "clientCtrl"
	})
});
/**
 * @class mainApp.mainCtrl
 * @memberof mainApp
 * @description controller for the home page
 */
mainApp
		.controller(
				"mainCtrl",
				function($scope, $http, $rootScope) {
					$http({
						method : 'GET',
						url : 'http://localhost:8080/TrackForce/track/info',
						headers : {
							'Content-Type' : 'application/json'
						}
					})
							.then(
									function(response) {
										// Undeployed table shows mapped vs.
										// unmapped excluding deployed
										// associates
										$scope.UndeployedLabels = [ "Mapped",
												"Unmapped" ];
										$scope.UndeployedData = [
												response.data.trainingMapped
														+ response.data.reservedMapped
														+ response.data.selectedMapped
														+ response.data.confirmedMapped,
												response.data.trainingUnmapped
														+ response.data.openUnmapped
														+ response.data.selectedUnmapped
														+ response.data.confirmedUnmapped ];
										// Mapped table shows undeployed mapped
										// associates
										$scope.MappedLabels = [ 'Training',
												'Reserved', 'Selected',
												'Confirmed' ];
										$scope.MappedData = [
												response.data.trainingMapped,
												response.data.reservedMapped,
												response.data.selectedMapped,
												response.data.confirmedMapped ];
										// Unmapped table shows undeployed
										// unmapped associates
										$scope.UnmappedLabels = [ 'Training',
												'Open', 'Selected', 'Confirmed' ];
										$scope.UnmappedData = [
												response.data.trainingUnmapped,
												response.data.openUnmapped,
												response.data.selectedUnmapped,
												response.data.confirmedUnmapped ];
										// Deployed table shows mapped vs.
										// unmapped deployed associates
										$scope.DeployedLabels = [ 'Mapped',
												'Unmapped' ];
										$scope.DeployedData = [
												response.data.deployedMapped,
												response.data.deployedUnmapped ];
										// Optional styling arrays
										$scope.colors = [ '#e85410', '#59504c',
												'#2d8799', '#6017a5' ];
										$scope.colors2 = [ '#17d339',
												'#59504c', '#2d8799', '#e85410' ];
										$scope.options = {
											legend : {
												display : true,
												position : 'right'
											}
										};
										/*
										 * When the "Mapped" chart is clicked
										 * the global variable selectedStatus is
										 * set to the label of the slice
										 * clicked. The window then loads the
										 * clientMapped.html partial.
										 */
										$scope.MappedOnClick = function(points,
												evt) {
											console.log(points, evt);
											var clickedElementindex = points[0]["_index"];
											console
													.log($scope.MappedLabels[clickedElementindex]);
											$rootScope.selectedStatus = $scope.MappedLabels[clickedElementindex];
											window.location.href = "#!/clientMapped";
										};
										$scope.UnmappedOnClick = function(
												points, evt) {
											console.log(points, evt);
											var clickedElementindex = points[0]["_index"];
											console
													.log($scope.UnmappedLabels[clickedElementindex]);
											$rootScope.selectedStatus = $scope.UnmappedLabels[clickedElementindex];
											window.location.href = "#!/skillset";
										};
									});
				});
// clientMapped Ctrl
mainApp.controller("clientMappedCtrl", function($scope, $http, $rootScope) {
	$http(
			{
				method : 'GET',
				/*
				 * This URL will pull varying data from the REST service based
				 * on the selectedStatus
				 */
				// TODO: update this URL with the REST service for pulling all
				// associates
				url : 'http://localhost:8080/TrackForce/track/mapped/'
						+ $rootScope.selectedStatus
			}).then(function(response) {
		// clients is a JSON array of clients mapped with their respective
		// numbers
		var clients = response.data;
		$scope.clientMappedLabels = [];
		$scope.clientMappedData = [];
		for (let i = 0; i < clients.length; i++) {
			/*
			 * These variable names may need to be changed according to the JSON
			 * (clients[].name and clients[].count)
			 */
			clientMappedLabels.push(clients[i].name);
			clientMappedData.push(clients[i].count);
		}
		$scope.options = {
			legend : {
				display : true,
				position : 'right'
			}
		}
		$scope.colors = [ '#e85410', '#59504c', '#2d8799', '#6017a5' ];
	});
});

//Controller for skillset.html
mainApp.controller("skillsetCtrl", function($scope, $rootScope, $http) {
	$http(
			{
				method : "GET",
				url : "http://localhost:8080/TrackForce/track/unmapped/"
						+ $rootScope.selectedStatus
			}).then(function(response) {

	});
});
/**
 * @class mainApp.batchCtrl
 * @memberof mainApp
 * @description controller for the batch page
 */
mainApp.controller("batchCtrl", function($scope, $http, baseURL) {
	$scope.batches = 'hello';
	$scope.getBatches = function() {
		console.log($scope.fromdate);
		console.log($scope.todate);
		var fromdate = new Date($scope.fromdate);
		var todate = new Date($scope.todate);

		// Simple GET request example:
		$http(
				{
					method : 'GET',
					url : baseURL + 'batches/' + fromdate.getTime() + '/'
							+ todate.getTime(),
					headers : {
						'Content-Type' : 'application/json'
					}
				}).then(function(success) {
			$scope.batches = data;
			console.log($scope.batches);
			console.log(baseURL);
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
			/*
			 * this callback will be called asynchronously when the response is
			 * available
			 */
			$scope.amountType = response.data;
		}, function errorCallback(response) {
			/*
			 * called asynchronously if an error occurs or server returns
			 * response with an error status.
			 */
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
			/*
			 * this callback will be called asynchronously when the response is
			 * available
			 */
			$scope.associatesBatch = response.data;
		}, function errorCallback(response) {
			/*
			 * called asynchronously if an error occurs or server returns
			 * response with an error status.
			 */
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
	$scope.getMapStatusBatch = function($http) {
		// Simple GET request example:
		$http(
				{
					method : 'GET',
					url : 'http://localhost:8080/TrackForce/track/batches'
							+ $scope.batchname + '/batchChart'
				}).then(function successCallback(response) {
			// this callback will be called asynchronously
			// when the response is available
			$scope.batchMapStatus = response.data;
		}, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
			$scope.batchMapSatus = {
				"Mapped" : "0",
				"Unmapped" : "0"
			}
		})
	};
	$scope.labels = [ 'Mapped', 'Unmapped' ];
	$scope.series = [ 'Series A' ];
	$scope.data = [ 70, 61 ];
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
/**
 * @class mainApp.clientCtrl
 * @memberof mainApp
 * @description This controller is used for generating charts for the client
 *              page
 */
mainApp.controller("clientCtrl", function($scope, $http) {
	/**
	 * @function getAllClientNames
	 * @memberof mainApp.clientCtrl
	 * @description This function will return a JavaScript object that contains
	 *              all of the client names and their id numbers
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
	/**
	 * @function getAllClients
	 * @memberof mainApp.clientCtrl
	 * @description This function will create a chart for all of the clients
	 *              data <br>
	 *              Local Variables: <br>
	 *              clients <br>
	 *              clientName <br>
	 *              clientLabels <br>
	 *              clientSeries <br>
	 *              clientData <br>
	 *              clientColors <br>
	 *              clientOptions
	 */
	$scope.getAllClients = function() {
		$http({
			method : "GET",
			url : "http://localhost:8080/TrackForce/track/clients/info"
		}).then(
				function(response) {
					/**
					 * @member {Client} clients
					 * @description Local variable of getAllClients. A
					 *              JavaScript object is created from the client
					 *              object that is sent from the REST service.
					 *              This client object contains data from all
					 *              clients
					 */
					var clients = response.data;
					/**
					 * @member {String} clientName
					 * @description Local variable of getAllClients. This $scope
					 *              variable binds the data in the client name
					 *              to the header above the chart on the HTML
					 */
					$scope.clientName = clients.name;
					/**
					 * @member {Array} clientLabels
					 * @description Local variable of getAllClients. This will
					 *              bind an array of strings to the x-axis of
					 *              the bar chart
					 */
					$scope.clientLabels = [ 'Training', 'Reserved/Open',
							'Selected', 'Confirmed', 'Deployed' ];
					/**
					 * @member {Array} clientSeries
					 * @description Local variable of getAllClients. This array
					 *              describes the different bars you want to
					 *              display.
					 */
					$scope.clientSeries = [ 'Mapped', 'Unmapped' ];
					/**
					 * @member {Array} clientData
					 * @description Local variable of getAllClients. The clients
					 *              JavaScript object is used for the data it
					 *              contains which is then bound to the chart
					 *              dataset using the $scope service.
					 */
					$scope.clientData = [
							[ clients.trainingMapped, clients.reservedMapped,
									clients.selectedMapped,
									clients.confirmedMapped,
									clients.deployedMapped ],
							[ clients.trainingUnmapped, clients.openUnmapped,
									clients.selectedUnmapped,
									clients.confirmedUnmapped,
									clients.deployedUnmapped ] ];
					/**
					 * @property {Array} clientColors
					 * @description Local variable of getAllClients. This array
					 *              sets the color scheme for the chart.
					 */
					$scope.clientColors = [ {
						backgroundColor : '#e85410'
					}, {
						backgroundColor : '#59504c'
					}, '#e85410', '#e85410' ];
					/**
					 * @property {Array} clientOptions
					 * @description Local variable of getAllClients. This array
					 *              modifies the options of the chart.
					 */
					$scope.clientOptions = {
						legend : {
							display : true,
							position : 'right'
						},
					};
				});
	}
	/**
	 * @function getOneClient
	 * @memberof mainApp.clientCtrl
	 * @param {Integer}
	 *            searchValue The ID of the client you are searching for.
	 * @description This function will send a search value to the REST service
	 *              as a path parameter in order to find a single client. Once
	 *              the client object is received the graph should reflect the
	 *              changes. <br>
	 *              Local Variables: <br>
	 *              clients <br>
	 *              clientName <br>
	 *              clientLabels <br>
	 *              clientSeries <br>
	 *              clientData <br>
	 *              clientColors <br>
	 *              clientOptions
	 */
	$scope.getOneClient = function(searchValue) {
		$http(
				{
					method : "GET",
					url : "http://localhost:8080/TrackForce/track/clients/"
							+ searchValue
				}).then(
				function(response) {
					var clients = response.data;
					$scope.clientName = clients.name;
					$scope.clientLabels = [ 'Training', 'Reserved/Open',
							'Selected', 'Confirmed', 'Deployed' ];
					$scope.clientSeries = [ 'Mapped', 'Unmapped' ];
					$scope.clientData = [
							[ clients.trainingMapped, clients.reservedMapped,
									clients.selectedMapped,
									clients.confirmedMapped,
									clients.deployedMapped ],
							[ clients.trainingUnmapped, clients.openUnmapped,
									clients.selectedUnmapped,
									clients.confirmedUnmapped,
									clients.deployedUnmapped ] ];
					$scope.clientOptions = {
						legend : {
							display : true,
							position : 'right'
						}
					};
				});
	}
});
/**
 * @class mainApp.databaseCtrl
 * @memberof mainApp
 * @description controller for database population and deletion.
 */
mainApp
		.controller(
				'databaseCtrl',
				function($http, $scope) {
					/**
					 * @function populateDB
					 * @memberof mainApp.databaseCtrl
					 * @description Populates the database with information from
					 *              data script
					 */
					$scope.populateDB = function() {
						$http(
								{
									method : "GET",
									url : "http://localhost:8080/TrackForce/track/database/populateDB"
								}).then(function(response) {
							$scope.dbMessage = response.data;
						})
					}
					/**
					 * @function deleteDB
					 * @memberof mainApp.databaseCtrl
					 * @description Truncates all the tables in the database
					 */
					$scope.deleteDB = function() {
						$http(
								{
									method : "GET",
									url : "http://localhost:8080/TrackForce/track/database/deleteFromDB"
								}).then(function(response) {
							$scope.dbMessage = response.data;
						})
					}
					$scope.refresh = function() {
						window.location.reload();
					}
				});
