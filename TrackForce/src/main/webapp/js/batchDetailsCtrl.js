/**
 * http://usejsdoc.org/
 */
/**
 * @class mainApp.batchCtrl
 * @memberof mainApp
 * @description controller for the batch details page
 */
angular.module('mainApp').controller("batchDetailsCtrl",function($scope, $http, $routeParams, $location){ 
		
		/**
		 * @member {String} batchname
		 * @memberof mainApp.batchDetailsCtrl
		 * @description This reflects the name of the batch in the database to pass into the url. 
		 */
		$scope.batchname = $routeParams.batchname; 
		
		/**
		 * @function getMapStatusBatch
		 * @memberof mainApp.batchDetailsCtrl
		 * @description This function will return a JavaScript object that contains
		 *              the number of mapped and unmapped associates in a specific batch
		 */
		$scope.getMapStatusBatch = function() {
		$http({
			method : 'GET',
			url : '/TrackForce/track/batches/' 
				+ $scope.batchname + '/batchChart'
		}).then(function(response) {
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
					},
					title: {
						display: true,
						text: $scope.batchname,
						fontSize: 24,
						fontColor: '#121212'
					}
				};
		}, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
			console.log('Error in doing http request')
		})
	};
	
	/**
	 * @function getBatchAssociates
	 * @memberof mainApp.batchDetailsCtrl
	 * @description This function will return a JavaScript object that contains
	 *              the all of the associates in a batch
	 */
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

