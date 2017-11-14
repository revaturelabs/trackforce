/**
 * http://usejsdoc.org/
 */
/**
 * @class mainApp.databaseCtrl
 * @memberof mainApp
 * @description controller for database population and deletion.
 */
angular
		.module('mainApp')
		.controller(
				'databaseCtrl',
				function($http, $scope) {
					/**
					 * @function populateDB
					 * @memberof mainApp.databaseCtrl
					 * @description Populates the database with information from
					 *              data script
					 */
					$scope.populateDB = function() {
						$http(
								{
									method : "GET",
									url : "http://localhost:8080/TrackForce/track/database/populateDB"
								}).then(function(response) {
							$scope.dbMessage = response.data;
							$scope.myStatus = response.status;

						}).then(function() {
							window.location.reload();

						}).then(function() {
							console.log($scope.myStatus);
							console.log($scope.dbMessage);
						})
					}
					/**
					 * @function deleteDB
					 * @memberof mainApp.databaseCtrl
					 * @description Truncates all the tables in the database
					 */
					$scope.deleteDB = function() {
						$http(
								{
									method : "GET",
									url : "http://localhost:8080/TrackForce/track/database/deleteFromDB"
								}).then(function(response) {

							$scope.myStatus = response.status;
							$scope.dbMessage = response.data;

						}).then(function() {
							window.location.reload();
						}).then(function() {
							console.log($scope.myStatus);
							console.log($scope.dbMessage);
						})
					}
					/**
					 * @function populateDBSF
					 * @memberof mainApp.databaseCtrl
					 * @description SF Populates the database with information
					 *              from data script
					 */
					$scope.populateDBSF = function() {
						$http(
								{
									method : "GET",
									url : "http://localhost:8080/TrackForce/track/database/populateDBSF"
								}).then(function(response) {
							$scope.myStatus = response.status;
							$scope.dbMessage = response.data;

						}).then(function() {
							window.location.reload();

						}).then(function() {
							console.log($scope.myStatus);
							console.log($scope.dbMessage);
						})
					}
					$scope.InitForce = function() {
						$http({
							method : "PUT",
							url : "http://localhost:8080/TrackForce/track/init"
						})
					}

				});
