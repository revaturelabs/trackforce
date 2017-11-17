/**
 * http://usejsdoc.org/
 */


/**
 * @class mainApp.batchCtrl
 * @memberof mainApp
 * @description This controller is used in the batch list page
 * 				to get all batches and show meaningful information
 */
angular.module('mainApp').controller("batchCtrl", function($scope, $http) {	

	/**
	 * @function getBatches
	 * @memberof mainApp.batchCtrl
	 * @description This function will return a JavaScript object that contains
	 *              all of the batches within fromdate and todate
	 */
	$scope.getBatches = function() {
		var fromdate = new Date($scope.fromdate);
		var todate = new Date($scope.todate);
		// Simple GET request example:
		$http({
			method : 'GET',
			url : '/TrackForce/track/batches/' + fromdate.getTime() + '/' + todate.getTime(),
		}).then(function successCallback(response) {
			$scope.batches = response.data;
		}, function errorCallback(response) {
			console.log('Error in doing http request')
		});
	};
	
	/**
	 * @function getBatches
	 * @memberof mainApp.batchCtrl
	 * @description This function will return a JavaScript object that contains
	 *              all of the batches within fromdate and todate
	 */
	$scope.getCountPerBatchType = function() {
		var fromdate = new Date($scope.fromdate);
		var todate = new Date($scope.todate);
		$http({
			method : 'GET',
			url : '/TrackForce/track/batches/' + fromdate.getTime() + '/' + todate.getTime() + '/type'
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