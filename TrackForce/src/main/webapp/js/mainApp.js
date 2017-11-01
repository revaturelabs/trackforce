var mainApp = angular.module("mainApp", [ 'ngRoute','chart.js' ]);

mainApp.config(function($routeProvider) {
    $routeProvider

    .when("/batchDetails", {
        templateUrl : "batchDetails.html",
        controller : "mainCtrl"
    }).otherwise({redirectTo: '/'});
    
});

mainApp.controller("mainCtrl", function mainController($scope) {
	    $scope.labels = ["SDET","Full Stack Java",".NET","Salesforce"];
	    $scope.data = [15, 20, 25, 30];
	    $scope.type = 'polarArea';

	    $scope.toggle = function () {
	      $scope.type = $scope.type === 'polarArea' ?
	        'pie' : 'polarArea';
	    };
});