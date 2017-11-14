/**
 * http://usejsdoc.org/
 */
angular.module('mainApp').controller("batchDetailsCtrl",function($scope, $http, $routeParams, $location){ 
		$scope.batchname = $routeParams.batchname; 
		$scope.getMapStatusBatch = function() {
		// Simple GET request example:
		$http({
			method : 'GET',
			url : '/TrackForce/track/batches/' 
				+ $scope.batchname + '/batchChart'
		}).then(function(response) {
			// this callback will be called asynchronously
			// when the response is available
			var batchMapStatus = response.data;
			$scope.mapping = ['Mapped', 'Unmapped'];
			$scope.mapStatus = [batchMapStatus.Mapped, batchMapStatus.Unmapped];
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
			console.log('Error in doing http request')
		})
	};
	
	$scope.getBatchAssociates = function() {
		// Simple GET request example:
		$http({
			method : 'GET',
			url : '/TrackForce/track/batches/'
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
	
	$scope.showMapStatusAndAssociates = function(){
		return $scope.batchDetails = true; 
	};
});

