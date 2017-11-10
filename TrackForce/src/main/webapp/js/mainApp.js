/**                                                     
 *	  *   )                    )  (                          
 *	` )  /( (       )       ( /(  )\ )       (           (   
 *	 ( )(_)))(   ( /(   (   )\())(()/(   (   )(    (    ))\  
 *	(_(_())(()\  )(_))  )\ ((_)\  /(_))  )\ (()\   )\  /((_) 
 *	|_   _| ((_)((_)_  ((_)| |(_)(_) _| ((_) ((_) ((_)(_))   
 *	  | |  | '_|/ _` |/ _| | / /  |  _|/ _ \| '_|/ _| / -_)  
 *	  |_|  |_|  \__,_|\__| |_\_\  |_|  \___/|_|  \__| \___| 
 * @namespace mainApp
 * @description Start the main module to be used for angular app
 */
angular.module('mainApp', [ 'ngRoute', 'chart.js' ]);
/**
 * @function config
 * @memberof mainApp
 * @param {service} routeprovider
 * @description Configure $routeProvider to create a Single Page Application
 */
angular.module('mainApp').config(function($routeProvider) {
	$routeProvider
	
	// Home Page 
	.when("/", {
		templateUrl : "home.html",
		controller : "indexCtrl"
	})
	
	// Batch Listing Page
	.when("/batchListing", {
		templateUrl : "batchListing.html",
		controller : "batchCtrl"
	})

	// Unmapped View Page route
	.when("/batchDetails/:batchname", {
		templateUrl : "batchDetails.html",
		controller : "batchDetailsCtrl",
		controllerAs : "batchDetails"
	})
	
	// Associate List Page
	.when("/associateListing", {
		templateUrl : "associateListing.html",
		controller : "associateCtrl"
	})
	
	// Form Page
	.when("/form/:associateId", {
		templateUrl : "form.html",
		controller : "associateCtrl"
	})
	
	// Client Details Page
	.when("/clientDetails", {
		templateUrl : "clientDetails.html",
		controller : "clientCtrl"
	})
	
	// Client Mapped Page
	.when("/clientMapped", {
		templateUrl : "clientMapped.html",
		controller : "clientMappedCtrl"
	})
	
	// Skillset Page
	.when("/skillset", {
		templateUrl : "skillset.html",
		controller : "skillsetCtrl"
	})
	
	//Create user page
	.when("/createUser",{
		templateUrl: "createNewUser.html",
		controller: "createUserCtrl"
	})
});