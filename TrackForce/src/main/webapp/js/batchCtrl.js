/**
 * http://usejsdoc.org/
 */
/**
 * @class mainApp.batchCtrl
 * @memberof mainApp
 * @description controller for the batch page
 */
angular.module('mainApp').controller("batchCtrl", function($scope, $http) {	

	$scope.getBatches = function() {
		var fromdate = new Date($scope.fromdate);
		var todate = new Date($scope.todate);
		// Simple GET request example:
		$http({
			method : 'GET',
			url : 'http://localhost:8080/TrackForce/track/batches/' + fromdate.getTime() + '/' + todate.getTime(),
			headers : {'Content-Type' : 'application/json'}
		}).then(function successCallback(response) {
			$scope.batches = response.data;
			console.log($scope.batches);
		}, function errorCallback(response) {
			console.log('Error in doing http request')
		});
	};

	$scope.getCountPerBatchType = function() {
		var fromdate = new Date($scope.fromdate);
		var todate = new Date($scope.todate);
		$http({
			method : 'GET',
			url : 'http://localhost:8080/TrackForce/track/batches/' + fromdate.getTime() + '/' + todate.getTime() + '/type'
		}).then(function successCallback(response) {
			// this callback will be called asynchronously
			// when the response is available
			$scope.labels = []; 
			$scope.data = []; 
			var amountType = response.data;
			console.log(response.data); 
			for(var i = 0; i < amountType.length; i++){
				$scope.labels.push(amountType[i].curriculum);
				$scope.data.push(amountType[i].value); 
			} 
		}, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
			$scope.amountType = {
				"JTA_SDET" : "2",
				".NET" : "3"
			}
		})
	};
}); 