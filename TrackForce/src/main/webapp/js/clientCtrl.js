/**
 * http://usejsdoc.org/
 */


/**
 * @class mainApp.clientCtrl
 * @memberof mainApp
 * @description This controller is used for generating charts for the client
 *              page
 */
angular.module('mainApp').controller("clientCtrl", function($scope, $http) {
	/**
	 * @function getAllClientNames
	 * @memberof mainApp.clientCtrl
	 * @description This function will return a JavaScript object that contains
	 *              all of the client names and their id numbers
	 */
	$scope.getAllClientNames = function() {
		$http({
			method : 'GET',
			url : '/TrackForce/track/clients'
		}).then(function(response) {
			$scope.clients = response.data;
		});
	}
	/**
	 * @function getAllClients
	 * @memberof mainApp.clientCtrl
	 * @description This function will create a chart for all of the clients
	 *              data <br>
	 *              Local Variables: <br>
	 *              clients <br>
	 *              clientName <br>
	 *              clientLabels <br>
	 *              clientSeries <br>
	 *              clientData <br>
	 *              clientColors <br>
	 *              clientOptions
	 */
	$scope.getAllClients = function() {
		$http({
			method : 'GET',
			url : '/TrackForce/track/clients/info'
		}).then(function(response) {
			/**
			 * @member {Client} clients
			 * @description Local variable of getAllClients. A
			 *              JavaScript object is created from the client
			 *              object that is sent from the REST service.
			 *              This client object contains data from all
			 *              clients
			 */
			var clients = response.data;
			/**
			 * @member {String} clientName
			 * @description Local variable of getAllClients. This $scope
			 *              variable binds the data in the client name
			 *              to the header above the chart on the HTML
			 */
			$scope.clientName = clients.name;
			/**
			 * @member {Array} clientLabels
			 * @description Local variable of getAllClients. This will
			 *              bind an array of strings to the x-axis of
			 *              the bar chart
			 */
			$scope.clientLabels = [ 'Training', 'Reserved/Open',
					'Selected', 'Confirmed', 'Deployed' ];
			/**
			 * @member {Array} clientSeries
			 * @description Local variable of getAllClients. This array
			 *              describes the different bars you want to
			 *              display.
			 */
			$scope.clientSeries = [ 'Mapped', 'Unmapped' ];
			/**
			 * @member {Array} clientData
			 * @description Local variable of getAllClients. The clients
			 *              JavaScript object is used for the data it
			 *              contains which is then bound to the chart
			 *              dataset using the $scope service.
			 */
			$scope.clientData = [
					[ clients.trainingMapped, clients.reservedMapped,
							clients.selectedMapped,
							clients.confirmedMapped,
							clients.deployedMapped ],
					[ clients.trainingUnmapped, clients.openUnmapped,
							clients.selectedUnmapped,
							clients.confirmedUnmapped,
							clients.deployedUnmapped ] ];
			/**
			 * @property {Array} clientColors
			 * @description Local variable of getAllClients. This array
			 *              sets the color scheme for the chart.
			 */
			$scope.clientColors = [ {
				backgroundColor : '#e85410'
			}, {
				backgroundColor : '#59504c'
			}, '#e85410', '#e85410' ];
			/**
			 * @property {Array} clientOptions
			 * @description Local variable of getAllClients. This array
			 *              modifies the options of the chart.
			 */
			$scope.clientOptions = {
				legend : {
					display : true,
					position : 'right'
				},
			};
		});
	}
	/**
	 * @function getOneClient
	 * @memberof mainApp.clientCtrl
	 * @param {Integer}
	 *            searchValue The ID of the client you are searching for.
	 * @description This function will send a search value to the REST service
	 *              as a path parameter in order to find a single client. Once
	 *              the client object is received the graph should reflect the
	 *              changes. <br>
	 *              Local Variables: <br>
	 *              clients <br>
	 *              clientName <br>
	 *              clientLabels <br>
	 *              clientSeries <br>
	 *              clientData <br>
	 *              clientColors <br>
	 *              clientOptions
	 */
	$scope.getOneClient = function(searchValue) {
		$http({
			method : 'GET',
			url : '/TrackForce/track/clients/'
					+ searchValue
		}).then(
		function(response) {
			var clients = response.data;
			$scope.clientName = clients.name;
			$scope.clientLabels = [ 'Training', 'Reserved/Open',
					'Selected', 'Confirmed', 'Deployed' ];
			$scope.clientSeries = [ 'Mapped', 'Unmapped' ];
			$scope.clientData = [
					[ clients.trainingMapped, clients.reservedMapped,
							clients.selectedMapped,
							clients.confirmedMapped,
							clients.deployedMapped ],
					[ clients.trainingUnmapped, clients.openUnmapped,
							clients.selectedUnmapped,
							clients.confirmedUnmapped,
							clients.deployedUnmapped ] ];
			$scope.clientOptions = {
				legend : {
					display : true,
					position : 'right'
				}
			};
		});
	}
});
