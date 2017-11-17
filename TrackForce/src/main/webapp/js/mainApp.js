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
	
	/**
	 * @function /
	 * @memberof mainApp.config
	 * @description Route for the home page
	 */
	.when("/", {
		templateUrl : "home.html",
		controller : "indexCtrl",
		css: 'style/style.css'
	})
	
	/**
	 * @function /batchListing
	 * @memberof mainApp.config
	 * @description Route for the Batch Listing page
	 */
	.when("/batchListing", {
		templateUrl : "batchListing.html",
		controller : "batchCtrl"
	})

	/**
	 * @function /batchDetails/:batchname
	 * @memberof mainApp.config
	 * @parameter {string} batchname - route parameter to load the specific batches details
	 * @description Route for the Batch Details page 
	 */
	.when("/batchDetails/:batchname", {
		templateUrl : "batchDetails.html",
		controller : "batchDetailsCtrl",
		controllerAs : "batchDetails"
	})
	
	/**
	 * @function /associateListing
	 * @memberof mainApp.config
	 * @description Route for the Associate List page
	 */	
	.when("/associateListing", {
		templateUrl : "associateListing.html",
		controller : "associateCtrl"
	})
	
	/**
	 * @function /associateListing/:skill/:client/:status
	 * @memberof mainApp.config
	 * @param {string} skill - route parameter to send skill for associate filter
	 * @param {string} client - route parameter to send client for associate filter
	 * @param {string} status - route parameter to send status for associate filter
	 * @description Route for the Associate List page
	 */	
	.when("/associateListing/:skill/:client/:status",{
		templateUrl:"associateListing.html",
		controller : "associateCtrl"
	})
	
	/**
	 * @function /associateListing/:skill/:client/:status
	 * @memberof mainApp.config
	 * @param {string} skill - route parameter to send skill for associate filter
	 * @param {string} client - route parameter to send client for associate filter
	 * @param {string} status - route parameter to send status for associate filter
	 * @description Route for the Associate List page
	 */		
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
