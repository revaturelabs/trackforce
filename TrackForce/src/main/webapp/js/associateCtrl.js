/**
 * http://usejsdoc.org/
 */
angular.module('mainApp').controller('associateCtrl', function($http, $scope, $routeParams, $rootScope) {
	$scope.limit = 10;
	$scope.start = -671;
	
	$scope.terminated = "TERMINATED";
	
	$scope.beginning = function() {
		$scope.start = -671;
	}
	
	$scope.decrease = function() {
		$scope.start -= $scope.limit;
		console.log($scope.start);
	}	
	
	$scope.increase = function() {
		$scope.start += $scope.limit;
		console.log($scope.start);
	}
	
	$scope.end = function() {
		$scope.start -= 0;
	}
	
	
	$scope.getAssociate = function() {
		
		$http({
			method : 'GET',
			url : '/TrackForce/track/associates/' + $routeParams.associateId
	    }).then(function(response) {
	        $scope.associate = response.data;
	    });
	}
	
	$scope.getAllAssociates = function() {
	
		$http({
			method : 'GET',
			url : '/TrackForce/track/associates/all'
		}).then(function(response) {
			$scope.curriculum = $rootScope.curriculum; 
			$rootScope.associateInfo = response.data;
		});
	}
	
	$scope.updateAssociate = function() {
		$http({
			method : 'GET',
			url : '/TrackForce/track/associates/' + $routeParams.associateId + '/update/' + $scope.associate.marketingStatus + '/' + $scope.associate.client
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
			method : 'GET',
			url : '/TrackForce/track/clients/all'
		}).then(function(response) {
			$scope.clients = response.data;
		});
	}
	
	$scope.getAvailableClients = function() {
		$http({
			method : 'GET',
			url : '/TrackForce/track/clients'
		}).then(function(response) {
			$scope.availableClients = response.data;
		});
	}
});
