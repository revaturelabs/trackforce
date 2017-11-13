/**
 * http://usejsdoc.org/
 */
angular.module('mainApp').controller('associateCtrl', function($http, $scope, $routeParams, $rootScope) {
	$http({
		method : "GET",
		url : "http://localhost:8080/TrackForce/track/associates/all"
	}).then(function(response) {
		$scope.associateInfo = response.data;
	});
	
	$http({
  		method : "GET",
 		url : "http://localhost:8080/TrackForce/track/associates/" + $routeParams.associateId
  	}).then(function(response) {
  		$scope.associate = response.data;
  	})
	
	$scope.updateAssociate = function() {
		$http({
			method : "GET",
			url : "http://localhost:8080/TrackForce/track/associates/" + $routeParams.associateId + "/update/" + $scope.associateInfo.marketingStatus + "/" + $scope.associateInfo.client
		}).then(function(response) {
			$scope.message = response.data;
			if(response.statusCode == 404) {
				$scope.message = "Resource does not exist";
			}
		})
	}
	
	$scope.onLoad = function (){
		if($routeParams.client=='default'||$routeParams.client==undefined){
			$scope.searchByClient='';
		} else {
			$scope.searchByClient=$routeParams.client;
		}
		if($routeParams.skill=='default'||$routeParams.skill==undefined){
			$scope.searchBySkill='';
		} else {
			$scope.searchBySkill=$routeParams.skill;
		}
		if($routeParams.status=='default'||$routeParams.status==undefined){
			$scope.searchByStatus='';
		} else {
			$scope.searchByStatus=$routeParams.status;
			if($scope.searchByStatus=='Training' && ($routeParams.skill=='default'||$routeParams.skill==undefined)){
				$scope.searchByStatus='MAPPED: TRAINING';
			} 
			else if($scope.searchByStatus=='Training' && ($routeParams.client=='default'||$routeParams.client==undefined)){
				$scope.searchByStatus='UNMAPPED: TRAINING';
			}
			else if($scope.searchByStatus=='Reserved'){
				$scope.searchByStatus='MAPPED: RESERVED';
			}
			else if($scope.searchByStatus=='Open'){
				$scope.searchByStatus='UNMAPPED: OPEN';
			}
			else if($scope.searchByStatus=='Selected' &&($routeParams.skill=='default'||$routeParams.skill==undefined)){
				$scope.searchByStatus='MAPPED: SELECTED';
			} 
			else if($scope.searchByStatus=='Selected' && ($routeParams.client=='default'||$routeParams.client==undefined)){
				$scope.searchByStatus='UNMAPPED: SELECTED';
			}
			else if($scope.searchByStatus=='Confirmed'&& ($routeParams.skill=='default'||$routeParams.skill==undefined)){
				$scope.searchByStatus='MAPPED: CONFIRMED';
			} else if($scope.searchByStatus=='Confirmed' && ($routeParams.client=='default'||$routeParams.client==undefined)){
				$scope.searchByStatus='UNMAPPED: CONFIRMED';
			}
			else if($scope.searchByStatus=='Deployed'&&($routeParams.skill=='default'||$routeParams.skill==undefined)){
				$scope.searchByStatus='MAPPED: DEPLOYED';
			} else{$scope.searchByStatus='UNMAPPED: DEPLOYED';}
		}
	}
	
	$scope.getAllClientNames = function() {
		$http({
			method : "GET",
			url : "http://localhost:8080/TrackForce/track/clients"
		}).then(function(response) {
			$scope.clients = response.data;
		});
	}
});
