/**
 * http://usejsdoc.org/
 */
angular.module('mainApp').controller('associateCtrl', function($http, $scope, $routeParams) {
	$http({
		method : "GET",
		url : "http://localhost:8080/TrackForce/track/associates/all"
	}).then(function(response) {
		$scope.associateInfo = response.data;
	})
	
//	$http({
//		method : "GET",
//		url : "http://localhost:8080/TrackForce/track/" + $routeParam.statusId
//	}).then(function(response) {
//		$scope.associates = response.data;
//	})
});
