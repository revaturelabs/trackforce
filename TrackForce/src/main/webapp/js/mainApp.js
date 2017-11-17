/**
 * http://usejsdoc.org/
 */


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
 * @param {service} routeProvider
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
	 * @parameter {string} batchname - route parameter to send batchname to batch details page
	 * @description Route for the Batch Details page that takes in route parameters to present a specify batch's detail
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
	 * @description Route for the Associate List page that takes in route parameters to filter the associate list
	 */	
	.when("/associateListing/:skill/:client/:status",{
		templateUrl:"associateListing.html",
		controller : "associateCtrl"
	})
	
	/**
	 * @function /form/:associateId
	 * @memberof mainApp.config
	 * @param {string} associateId - route parameter to send associateId to associate info page
	 * @description Route for the Associate Info page that takes in route parameters to show a specific associates information
	 */		
	.when("/form/:associateId", {
		templateUrl : "form.html",
		controller : "associateCtrl"
	})
	
	/**
	 * @function /clientDetails
	 * @memberof mainApp.config
	 * @description Route for the Client Details Page
	 */
	.when("/clientDetails", {
		templateUrl : "clientDetails.html",
		controller : "clientCtrl"
	})
	
	/**
	 * @function /clientMapped
	 * @memberof mainApp.config
	 * @description Route for the Client Mapped Page
	 */
	.when("/clientMapped", {
		templateUrl : "clientMapped.html",
		controller : "clientMappedCtrl"
	})
	
	/**
	 * @function /skillset
	 * @memberof mainApp.config
	 * @description Route for the Skillset Page
	 */
	.when("/skillset", {
		templateUrl : "skillset.html",
		controller : "skillsetCtrl"
	})
	
	/**
	 * @function /createUser
	 * @memberof mainApp.config
	 * @description Route for the Create User Page
	 */
	.when("/createUser",{
		templateUrl: "createNewUser.html",
		controller: "createUserCtrl"
	})
});
