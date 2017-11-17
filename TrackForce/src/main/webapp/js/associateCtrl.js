/**
 * http://usejsdoc.org/
 */


/**
 * @class mainApp.associateCtrl
 * @memberof mainApp
 * @description controller for the associate listing page
 */
angular.module('mainApp').controller('associateCtrl', function($http, $scope, $routeParams, $rootScope) {
	
	/**
	 * @member {String} limit
	 * @memberof mainApp.associateCtrl
	 * @description This represents the initial number of associate displayed in the table. 
	 */
	$scope.limit = 10;
	
	/**
	 * @member {String} start
	 * @memberof mainApp.associateCtrl
	 * @description This represents where the associates list starts from in the json array 
	 */
	$scope.start = -671;
	
	/**
	 * @function beginning
	 * @memberof mainApp.associateCtrl
	 * @description This function sets where the table begins for the associate listing page
	 */
	$scope.beginning = function() {
		$scope.start = -671;
	}
	
	/**
	 * @function decrease
	 * @memberof mainApp.associateCtrl
	 * @description This function helps navigate through the associates list. 
	 */
	$scope.decrease = function() {
		$scope.start -= $scope.limit;
		console.log($scope.start);
	}	
	
	/**
	 * @function increase
	 * @memberof mainApp.associateCtrl
	 * @description This function helps navigate through the associates list. 
	 */
	$scope.increase = function() {
		$scope.start += $scope.limit;
		console.log($scope.start);
	}
	
	/**
	 * @function end
	 * @memberof mainApp.associateCtrl
	 * @description This function will return a JavaScript object that contains
	 *              the number of mapped and unmapped associates in a specific batch
	 */
	$scope.end = function() {
		$scope.start -= 0;
	}
	
	
	/**
	 * @function getAvailableClients
	 * @memberof mainApp.associateCtrl
	 * @description This function will return a JavaScript object that contains
	 *              information for one associate. 
	 */
	$scope.getAssociate = function() {
		
		$http({
			method : 'GET',
			url : '/TrackForce/track/associates/' + $routeParams.associateId
	    }).then(function(response) {
	        $scope.associate = response.data;
	    });
	}
	
	/**
	 * @function getAllAssociates
	 * @memberof mainApp.associateCtrl
	 * @description This function will return a JavaScript object that contains
	 *              all of the associates. 
	 */
	$scope.getAllAssociates = function() {
	
		$http({
			method : 'GET',
			url : '/TrackForce/track/associates/all'
		}).then(function(response) {
			$scope.curriculum = $rootScope.curriculum; 
			$rootScope.associateInfo = response.data;
		});
	}
	
	/**
	 * @function updateAssociates
	 * @memberof mainApp.associateCtrl
	 * @description This function i sused to update an associates mapped or unmapped status and 
	 * 				client. 
	 */
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
	
	/**
	 * @function onload
	 * @memberof mainApp.associateCtrl
	 * @description This function will apply filters to the associates table the page has been 
	 * 				loaded by someone has clicked on one of the graphs (mapped or unmapped, or clients graphs)
	 */
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
	
	/**
	 * @function getAllClientsNames
	 * @memberof mainApp.associateCtrl
	 * @description This function will return a JavaScript object that contains
	 *              the names of al the clients
	 */
	$scope.getAllClientNames = function() {
		$http({
			method : 'GET',
			url : '/TrackForce/track/clients/all'
		}).then(function(response) {
			$scope.clients = response.data;
		});
	}
	
	/**
	 * @function getAvailableClients
	 * @memberof mainApp.associateCtrl
	 * @description This function will return a JavaScript object that contains
	 *              the available clients. 
	 */
	$scope.getAvailableClients = function() {
		$http({
			method : 'GET',
			url : '/TrackForce/track/clients'
		}).then(function(response) {
			$scope.availableClients = response.data;
		});
	}
});
