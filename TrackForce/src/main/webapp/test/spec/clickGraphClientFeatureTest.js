'use strict';

//Test Suite for Click Graph Feature
describe("clientMappedCtrl Test Suite", function(){

	//Before each test it will mock the main module we are using
	beforeEach(angular.mock.module('mainApp'));
	
	beforeEach(angular.mock.inject(function($rootScope, $httpBackend) {
		var backend = $httpBackend;
		let mockscope = $rootScope.$new();
		//TODO: change url to test with
		backend.expect("GET", 'http://localhost:8080/TrackForce/track/client/' +mockScope.statusID).respond(
				[
					{ "name": "Infossy", "count": "45"},
					{ "name": "Popeyes", "count": "17"},
					{ "name": "Federal Reserve", "count": "27"}
				]);
	}));
	
	// Inject dependencies needed for controller
	beforeEach(angular.mock.inject(function($rootScope, $controller, $http) {
		var mockScope = $rootScope.$new();
		var controller = $controller('clientMappedCtrl', {
			$scope: mockScope,
			$http: $http
		});
	}));
	
	
	describe('Test functions for $http methods', function() {
		it('Test clientMappedCtrl', function() {
			
			var names = [
				"Infossy", 
			    "Popeyes", 
				"Federal Reserve"
			];
			
            var counts = ["45","17","27"];
            mockscope.onLoad();
			expect($rootScope.mockScope.clientMappedLabels).toEqual(names);
			expect($rootScope.mockScope.clientMappedData).toEqual(counts);
		});

	});
	
	
	
});
