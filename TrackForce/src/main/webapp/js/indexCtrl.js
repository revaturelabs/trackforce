/**
 * http://usejsdoc.org/
 */
angular.module('mainApp').controller("indexCtrl", function($scope, $http, $rootScope, $window) {
		
	$scope.logout = function() {
		$http({
			method : 'POST',
			url : 'http://localhost:8080/TrackForce/track/user/logout'
		}).then(function(response) {
			$window.location.href = 'login.html';
		})
	};
	
	var currentTime = new Date().getTime();
	var threeMonthsAfter = currentTime + 7889238000;
	var threeMonthsBefore = currentTime - 7889238000;
	
	$scope.onLoad = function(){
		$http({
			method : 'GET',
			url : 'http://localhost:8080/TrackForce/track/info',
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			/**
			* @member {Array} UndeployedLabels
 			* @memberof mainApp.mainCtrl
 			* @description Undeployed chart shows mapped vs. unmapped 
 			* excluding deployed associates. This array is used for the labels of the chart
 			*/			
			$scope.UndeployedLabels = [ "Mapped","Unmapped" ];
			/**
			 * @member {Array} UndeployedData
			 * @memberof mainApp.mainCtrl
			 * @description UndeployedData is an array used to populate the 
			 * dataset of the Undeployed chart. The dataset contains two numbers:
			 * the mapped number is the sum of all mapped associates, the unmapped number
			 * is the sum of all unmapped associates.
			 */
			$scope.UndeployedData = [response.data.trainingMapped
					+ response.data.reservedMapped
					+ response.data.selectedMapped
					+ response.data.confirmedMapped,
					response.data.trainingUnmapped
					+ response.data.openUnmapped
					+ response.data.selectedUnmapped
					+ response.data.confirmedUnmapped ];
			 /**
			  * @member {Array} MappedLabels
			  * @memberof mainApp.mainCtrl
			  * @description Mapped chart shows undeployed mapped associates.
			  * This array is used to set the labels for the chart.
			  */
			$scope.MappedLabels = [ 'Training','Reserved', 'Selected','Confirmed' ];
			/**
			 * @member {Array} MappedData
			 * @memberof mainApp.mainCtrl
			 * @description MappedData is an array that stores the 
			 * data for the dataset of the Mapped chart.
			 * The dataset contains four numbers: training mapped<br>
			 * reserved mapped <br>
			 * selected mapped <br>
			 * confirmed mapped<br>
			 */
			$scope.MappedData = [response.data.trainingMapped,
								response.data.reservedMapped,
								response.data.selectedMapped,
								response.data.confirmedMapped ];
			/**
			  * @member {Array} UnmappedLabels
			  * @memberof mainApp.mainCtrl
			  * @description Unmapped chart shows undeployed unmapped associates.
			  * This array is used to set the labels for the chart.
			  */
			$scope.UnmappedLabels = [ 'Training','Open', 'Selected', 'Confirmed' ];
			/**
			 * @member {Array} UnmappedData
			 * @memberof mainApp.mainCtrl
			 * @description UnmappedData is an array that stores the 
			 * data for the dataset of the Unmapped chart.
			 * The dataset contains four numbers: training unmapped<br>
			 * open unmapped <br>
			 * selected unmapped <br>
			 * confirmed unmapped<br>
			 */
			$scope.UnmappedData = [response.data.trainingUnmapped,
									response.data.openUnmapped,
									response.data.selectedUnmapped,
									response.data.confirmedUnmapped ];
			/**
			* @member {Array} DeployedLabels
 			* @memberof mainApp.mainCtrl
 			* @description Deployed chart shows mapped vs. unmapped 
 			* including only deployed associates. This array is used for the labels of the chart
 			*/	
			$scope.DeployedLabels = [ 'Mapped','Unmapped' ];
			/**
			 * @member {Array} DeployedData
			 * @memberof mainApp.mainCtrl
			 * @description DeployedData is an array used to populate the 
			 * dataset of the Deployed chart. The dataset contains two numbers:
			 * the mapped number is the sum of all mapped associates, the unmapped number
			 * is the sum of all unmapped associates. Both numbers contain only deployed associates.
			 */
			$scope.DeployedData = [response.data.deployedMapped,
								response.data.deployedUnmapped ];
			// Optional styling arrays
			$scope.colors = [ '#e85410', '#59504c','#2d8799', '#6017a5' ];
			$scope.colors2 = [ '#17d339','#59504c', '#2d8799', '#e85410' ];
			$scope.options = {legend : {
							  display : true,
							  position : 'right'}};
			
			//redo $scope.options to include chart titles
			$scope.unmappedOptions = {
				legend : {
							display : true,
							position : 'right'
						 },
				title: {
					display: true,
					text: "Unmapped",
					fontSize: 24,
					fontColor: '#121212'
					
				}
			
			};
			$scope.mappedOptions = {
					legend : {
						      display : true,
						      position : 'right'
							 },
					title: {
						display: true,
						text: 'Mapped',
						fontSize: 24,
						fontColor: '#121212'
			
					}
			};
			$scope.deployedOptions = {
					legend : {
						  		display : true,
						  		position : 'right'
							  },
				    title: {
				    	display: true,
				    	text: 'Mapped vs. Unmapped (Deployed)',
				    	fontSize: 24,
				    	fontColor: '#121212'
				    	
				    }
			};
			$scope.undeployedOptions = {
					legend : {
				  		       display : true,
				  		       position : 'right'
					          },
					title: {
		    	              display: true,
		    	              text: 'Mapped vs. Unmapped (Undeployed)',
		    	              fontSize: 24,
		    	              fontColor: '#121212'
		    	             
		    	          
		                    }
			};
			/**
			 * @function MappedOnClick
			 * @memberof mainApp.mainCtrl
			 * @description When the "Mapped" chart is clicked
			 * the global variable selectedStatus is
			 * set to the label of the slice
			 * clicked. The window then loads the
			 * clientMapped.html partial.
			 */
			$scope.MappedOnClick = function(points,evt) {
				var clickedElementindex = points[0]["_index"];
				$rootScope.selectedStatus = $scope.MappedLabels[clickedElementindex];
				window.location.href = "#!/clientMapped";
			};
				/**
				 * @function UnmappedOnClick
				 * @memberof mainApp.mainCtrl
				 * @description When the "Unmapped" chart is clicked
				 * the global variable selectedStatus is
				 * set to the label of the slice
				 * clicked. The window then loads the
				 * skillset.html partial.
				 */
			$scope.UnmappedOnClick = function(points, evt) {
				var clickedElementindex = points[0]["_index"];
				$rootScope.selectedStatus = $scope.UnmappedLabels[clickedElementindex];
				window.location.href = "#!/skillset";
			};
		});
	};
	
	
	$scope.defaultBatches = function () {
		$http({
			method : 'GET',
			url : 'http://localhost:8080/TrackForce/track/batches/' + threeMonthsBefore + '/' + threeMonthsAfter,
		}).then(function successCallback(response) {
			$rootScope.batches = response.data;
		}, function errorCallback(response) {
			console.log('Error in doing http request')
		});
	}
	
	$scope.getCountPerBatchTypeDefault = function(){
		$http({
			method : 'GET',
			url : 'http://localhost:8080/TrackForce/track/batches/' + threeMonthsBefore + '/' + threeMonthsAfter + '/type'
		}).then(function successCallback(response) {
			// this callback will be called asynchronously
			// when the response is available
			$scope.labels = [];
			$scope.data = []; 
			var amountType = response.data;
			for(var i = 0; i < amountType.length; i++){
				$scope.labels.push(amountType[i].curriculum);
				$scope.data.push(amountType[i].value);
			} 

			$scope.options = {legend : {
				  display : true,
				  position : 'right'}};
		}, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
			$scope.amountType = {
				"JTA_SDET" : "2",
				".NET" : "3"
			}
		})
	};	
});
