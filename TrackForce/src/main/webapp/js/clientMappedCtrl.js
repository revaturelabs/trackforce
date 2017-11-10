/**
 * http://usejsdoc.org/
 */
/**
 * @class mainApp.clientMappedCtrl
 * @memberof mainApp
 * @description controller for the Client Mapped page.
 */
angular.module('mainApp').controller("clientMappedCtrl", function($scope, $http, $rootScope) {
	$scope.onLoad = function(){
		/**
		 * @member {Integer} statusID
		 * @memberof mainApp.clientMappedCtrl
		 * @description This reflects the id used in the database to identify each marketing status.
		 * This number is set using an if-else-if decision structure based on 
		 * the label chosen in the Mapped chart.
		 */
		$scope.statusID=0;
		if($rootScope.selectedStatus=='Training'){
			$scope.statusID=1;
		} else if($rootScope.selectedStatus=='Reserved'){
			$scope.statusID=2;
		} else if ($rootScope.selectedStatus=='Selected'){
			$scope.statusID=3;
		} else if ($rootScope.selectedStatus=='Confirmed'){
			$scope.statusID=4;
		}
	$http({
		method : 'GET',
		/*
		 * This URL will pull varying data from the REST service based
		 * on the statusID
		 */
		url :'http://localhost:8080/TrackForce/track/client/'+ $scope.statusID
	}).then(function(response) {
		$scope.chartType='bar';
		/**
		 *  @member {Array} clients
		 *  @memberof mainApp.clientMappedCtrl
		 *  @description clients is a JSON array of clients mapped with their respective
		 *  numbers for the corresponding status. 
		 *  (Example: [{'name':'Revature', 'count':'100'},{'name':'Another','count':'100'}])
		 */
		var clients = response.data;
		/**
		 * @member {Array} clientMappedLabels
		 * @memberof mainApp.clientMappedCtrl
		 * @description clientMappedLabels is initialized as empty and then populated
		 * in a for loop that will add on each client name to the array. This array is then used
		 * to create the labels for the chart on the clientMapped.html page.
		 */
		$scope.clientMappedLabels = [];
		/**
		 * @member {Array} clientMappedData
		 * @memberof mainApp.clientMappedCtrl
		 * @description clientMappedData is initialized as empty and then populated 
		 * in a for loop that will add on each set of data corresponding to the label in the 
		 * clientMappedLabel array. This array is used to populate the dataset of the chart
		 * on the clientMapped.html page.
		 */
		$scope.clientMappedData = [];
		for (let i = 0; i < clients.length; i++) {
			if(clients[i].count>0){
				$scope.clientMappedLabels.push(clients[i].name);
				$scope.clientMappedData.push(clients[i].count);
			}
		}
		$scope.options = {
			type : $scope.chartType, xAxes:[{ticks:{autoSkip:false}}]
		}
		$scope.colors = [ '#e85410', '#59504c', '#2d8799', '#6017a5' ];
	});
	/**
	 * @function changeChartType
	 * @memberof mainApp.clientMappedCtrl
	 * @description Handles changing the chart type when the buttons are clicked.
	 * Removes the chart legend for charts that don't utilize it.
	 */
	$scope.changeChartType = (function(selectedType){
		//This adds on a legend if pie or polarArea are selected
		if(selectedType=='pie'||selectedType=='polarArea'){
			$scope.chartType=selectedType;
			$scope.options={type:selectedType, legend:{display:true, position: 'right'}, xAxes:[{ticks:{autoSkip:false}}]};
		} else{
			$scope.chartType=selectedType;
			$scope.options={type:selectedType, legend:{display:false}, xAxes:[{ticks:{autoSkip:false}}]};
		}
	});
	//TODO: URL will need to be changed
	$scope.skillsetClick = function(points, evt){
		var clickedElementindex = points[0]["_index"];
		var selectedClient = $scope.clientMappedLabels[clickedElementindex];
		window.location.href = "#!/associates/{{selectedSkill}}/{{selectedClient}}/{{selectedStatus}}";
	};
}});