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
			var amountType = response.data;
			console.log(response.data); 
			$scope.labels = ["Java", "SEED", "JTA",".NET", "PEGA", "DynamicCRM", "Salesforce","Microservices","Oracle Fusion"]
			$scope.data = [amountType.Java, amountType.SEED, amountType.JTA, amountType[".Net"], amountType.PEGA, amountType.DynamicCRM, amountType.Salesforce, amountType.Microservices, amountType["Oracle Fusion"]];
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