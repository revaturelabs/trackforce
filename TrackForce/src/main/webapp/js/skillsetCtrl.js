/**
 * http://usejsdoc.org/
 */
/**
 * @class mainApp.skillsetCtrl
 * @memberof mainApp
 * @description Controller for skillset.html
 */
angular.module('mainApp').controller("skillsetCtrl", function($scope, $rootScope, $http) {
	$scope.onLoad= function (){
		/**
		 * @member {Integer} statusID
		 * @memberof mainApp.skillsetCtrl
		 * @description This reflects the id used in the database to identify each marketing status.
		 * This number is set using an if-else-if decision structure based on 
		 * the label chosen in the Unmapped chart.
		 */
		$scope.statusID=0;
		if($rootScope.selectedStatus=='Training'){
			$scope.statusID=6;
		} else if($rootScope.selectedStatus=='Open'){
			$scope.statusID=7;
		} else if ($rootScope.selectedStatus=='Selected'){
			$scope.statusID=8;
		} else if ($rootScope.selectedStatus=='Confirmed'){
			$scope.statusID=9;
		}
	$http({ 	
		method : "GET",
		url :"http://localhost:8080/TrackForce/track/skillset/"+ $scope.statusID
	}).then(function(response) {
		/**
		 * @member {String} chartType
		 * @memberof mainApp.skillsetCtrl
		 * @description String used to determine the type of chart displayed. 
		 * The value of this changes based on the button clicked on skillset.html.
		 */
		$scope.chartType='bar';
		/**
		 * @member {Array} skillsets
		 * @memberof mainApp.skillsetCtrl
		 * @description Array used to store all of the skillsets and the total 
		 * number of associates related to it.
		 */
		var skillsets = response.data;
		/**
		 * @member {Array} skillsetLabels
		 * @memberof mainApp.skillsetCtrl
		 * @description Array used to store the labels for the skillset chart.
		 * This array is populated from the name attribute in the skillsets array.
		 */
		$scope.skillsetLabels = [];
		/**
		 * @member {Array} skillsetData
		 * @memberof mainApp.skillsetData
		 * @description Array used to store the data for the skillset chart dataset.
		 * This array is populated from the count attribute in the skillsets array.
		 */
		$scope.skillsetData = [];
		for(let i = 0 ; i < skillsets.length; i++){
			if(skillsets[i].count>0){
				$scope.skillsetLabels.push(skillsets[i].name);
				$scope.skillsetData.push(skillsets[i].count);
			}
		}
		$scope.options = {type: $scope.chartType, xAxes:[{ticks:{autoSkip:false}}]};
		$scope.colors = [ '#e85410', '#59504c', '#2d8799', '#6017a5' ];
	});
	/**
	 * @function changeChartType
	 * @memberof mainApp.skillsetCtrl
	 * @description Handles changing the chart type when the buttons are clicked.
	 * Removes the chart legend for charts that don't utilize it.
	 */
	$scope.changeChartType = (function(selectedType){
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
		var selectedSkill = $scope.skillsetLabels[clickedElementindex];
		window.location.href = "#!/associates/{{selectedSkill}}/{{selectedClient}}/{{selectedStatus}}";
	};
}});