var mainApp = angular.module("mainApp", [ 'ngRoute', 'chart.js' ]);
mainApp.config(function($routeProvider) {
	$routeProvider.when("/batchListing", {
		templateUrl : "batchListing.html",
		controller : "batchListingCtrl"
	}).when("/batchDetails", {
		templateUrl : "batchDetails.html",
		controller : "batchDetailsCtrl"
	}).otherwise({
		redirectTo : '/'
	});

});
mainApp.controller("batchListingCtrl", function mainController($scope) {
	$scope.labels = [ "SDET", "Full Stack Java", ".NET", "Salesforce" ];
	$scope.data = [ 15, 20, 25, 30 ];
	$scope.type = 'polarArea';
	$scope.toggle = function() {
		$scope.type = $scope.type === 'polarArea' ? 'pie' : 'polarArea';
	};
});

mainApp.controller("batchDetailsCtrl", function($scope) {
	$scope.labels = [ 'Mapped', 'Unmapped' ];
	//$scope.series = ['Mapped', 'Unmapped'];
	$scope.data = [ [ 25, 35 ] ];
});