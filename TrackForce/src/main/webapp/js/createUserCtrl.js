/**
 * http://usejsdoc.org/
 */

/**
 * @class mainApp.createUserCtrl
 * @memberof mainApp
 * @description controller for the create user page.
 */

angular.module('mainApp').controller("createUserCtrl", function($scope, $http){
	
	/**
	 * @member {Array} userTitles
	 * @memberof mainApp.createUserCtrl
	 * @description This creates an array of possible user titles and associated ids.
	 * A user title is selected in the select element on the createNewUser.html page.
	 * In future sprints, an http post request should be sent containing the associated id,
	 * along with the username and password.
	 */
	
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
	
	/**
	 * @member {String} userName
	 * @memberof mainApp.createUserCtrl
	 * @description A string used to contain the username. 
	 */
	$scope.username = "";
	/**
	 * @member {String} password
	 * @memberof mainApp.createUserCtrl
	 * @description A string used to contain the password. 
	 */
	$scope.password = "";
	
	/**
	 * @member {String} userPlaceholder
	 * @memberof mainApp.createUserCtrl
	 * @description A string used to contain a placeholder in the username input. 
	 */
	$scope.userPlaceholder = "Enter username...";
	
	/**
	 * @member {String} passPlaceholder
	 * @memberof mainApp.createUserCtrl
	 * @description A string used to contain a placeholder in the password input. 
	 */
	$scope.passPlaceholder="Enter password..."
	
});