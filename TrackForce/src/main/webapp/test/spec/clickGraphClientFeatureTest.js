'use strict';

//Test Suite for Click Graph Feature
describe("clientMappedCtrl Test Suite", function(){

	var mockScope={};
	var controller;
	//Before each test it will mock the main module we are using
	beforeEach(angular.mock.module('mainApp'));
	
	beforeEach(angular.mock.inject(function($rootScope, $httpBackend) {
		mockscope = $rootScope.$new();
		//TODO: change url to test with
		$httpBackend.expect("GET", 'http://localhost:8080/TrackForce/track/client/' +mockScope.statusID).respond(
				[
					{ "name": "Infosys", "count": "45"},
					{ "name": "Popeyes", "count": "17"},
					{ "name": "Federal Reserve", "count": "27"}
				]);
	}));
	
	// Inject dependencies needed for controller
	beforeEach(angular.mock.inject(function($rootScope, $controller, $http) {
		mockScope = $rootScope.$new();
		controller = $controller('clientMappedCtrl', {
			$scope: mockScope,
			$http: $http
		});
	}));
	
	
	describe('Test functions for $http methods', function() {
		it('Test clientMappedCtrl', function() {
			
			var names = [
				"Infosys", 
			    "Popeyes", 
				"Federal Reserve"
			];
			
            var counts = ["45","17","27"];
            mockscope.onLoad();
			expect(mockScope.clientMappedLabels).toEqual(names);
			expect(mockScope.clientMappedData).toEqual(counts);
		});

	});
	
	
	
});
