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
		.when("/clientDetails", {
		templateUrl : "clientDetails.html",
		controller : "clientCtrl"
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

mainApp.controller("clientSearchAndListCtrl", function($scope, $http){
    $scope.getAllClientNames = function(){
    	$http({
    		method : "GET",
    		url : "http://localhost:8080/TrackForce/track/clients"
    	}).then( function(response){
    		$scope.clients = response.data;
    		console.log(response.data);
    	});
    }
	
});

mainApp.controller("clientCtrl", function($scope, $http) {
	$scope.getAllClients = function() {
		console.log('before http');
		$http({
			method : "GET",
			url : "http://localhost:8080/TrackForce/track/clients/info"
				
		})
				.then(
						function(response) {
							var clients = response.data;
							$scope.clientName=clients.name;
							$scope.labels = [ 'Training', 'Reserved - Mapped',
								'Reserved - Unmapped', 'Selected - Mapped',
								'Selected - Unmapped', 'Confirmed - Mapped',
								'Confirmed - Unmapped', 'Deployed - Mapped',
								'Deployed - Unmapped' ];
							$scope.data = [ clients.trainingMapped,
									clients.reservedMapped,clients.reservedUnmapped,
									clients.selectedMapped,clients.selectedUnmapped,
									clients.confirmedMapped,clients.confirmedUnmapped,
									clients.deployedMapped,clients.deployedUnmapped  ];
						});
	}

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
					var clients = $scope.clients;
					$scope.clientName=clients.name;
					$scope.labels = [ 'Training', 'Reserved - Mapped',
							'Reserved - Unmapped', 'Selected - Mapped',
							'Selected - Unmapped', 'Confirmed - Mapped',
							'Confirmed - Unmapped', 'Deployed - Mapped',
							'Deployed - Unmapped' ];
					$scope.data = [ clients.trainingMapped,
						clients.reservedMapped,clients.reservedUnmapped,
						clients.selectedMapped,clients.selectedUnmapped,
						clients.confirmedMapped,clients.confirmedUnmapped,
						clients.deployedMapped,clients.deployedUnmapped  ];
				});
	}

});
