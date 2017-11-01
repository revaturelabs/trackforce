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


mainApp.controller("clientDetailsCtrl", function($scope, $http){
$scope.getAllClients=function(){
	$http(method: "GET", 
		url:"http://localhost:8080/TrackForce/track/clients")
			.then(function(response){
		$scope.clients=response.data;
		$scope.labels=['Training', 'Reserved', 'Selected', 'Confirmed', 'Deployed'];
		$scope.series=['Mapped','Unmapped'];
		$scope.data=[$scope.clients.training,$scope.clients.reserved,$scope.clients.selected, 
			$scope.clients.confirmed, $scope.clients.deployed];
		});
	}

$scope.getOneClient=function(){
	$http(method: "GET", 
		url:"http://localhost:8080/TrackForce/track/clients/"+$scope.searchValue)
			.then(function(response){
		$scope.client=response.data;
		$scope.labels=['Training', 'Reserved', 'Selected', 'Confirmed', 'Deployed'];
		$scope.series=['Mapped','Unmapped'];
		$scope.data=[$scope.client.training,$scope.client.reserved,$scope.client.selected, 
			$scope.client.confirmed, $scope.client.deployed];
		});
	}
});

