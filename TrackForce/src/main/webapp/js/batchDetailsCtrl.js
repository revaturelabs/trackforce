/**
 * http://usejsdoc.org/
 */
angular.module('mainApp').controller("batchDetailsCtrl",function($scope, $http, $routeParams, $location){ 
		$scope.batchname = $routeParams.batchname; 
		$scope.getMapStatusBatch = function() {
		// Simple GET request example:
		$http({
			method : 'GET',
			url : 'http://localhost:8080/TrackForce/track/batches/' 
				+ $scope.batchname + '/batchChart'
		}).then(function(response) {
			// this callback will be called asynchronously
			// when the response is available
			var batchMapStatus = response.data;
			console.log(response.data);
			$scope.labels = ['Mapped', 'Unmapped'];
			console.log($scope.labels);
			$scope.data = [batchMapStatus.Mapped, batchMapStatus.Unmapped];
			console.log($scope.data); 
			$scope.options = {
					scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : true
							}
						} ]
					}
				};
		}, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		})
	};
	$scope.getBatchAssociates = function() {
		// Simple GET request example:
		$http({
			method : 'GET',
			url : 'http://localhost:8080/TrackForce/track/batches/'
					+ $scope.batchname + '/associates'
		}).then(function successCallback(response) {
			// this callback will be called asynchronously
			// when the response is available
			$scope.associatesBatch = response.data;
			console.log(response.data); 
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
	
	$scope.showMapStatusAndAssociates = function(){
		return $scope.batchDetails = true; 
	};
});

