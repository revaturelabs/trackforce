/**
 * 
 */

angular.module('mainApp').controller("createUserCtrl", function($scope, $http){
	
	$scope.userTitles = [
		{
		  id: 1,
		  title: 'VP'
	    },
		{
	      id : 2,
	      title: 'Manager'
	    	
		},
		{
			id: 3,
			title: 'Administrator'
		}];
	
	$scope.username = "";
	$scope.password = "";
	
	$scope.userPlaceholder = "Enter username...";
	$scope.passPlaceholder="Enter password..."
	
});