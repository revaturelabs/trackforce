'use strict';

//Test Suite for Click Graph Feature
describe("skillsetCtrl Test Suite", function(){
	
	var mockscope = {};
	var controller;
	var backend;

	//Before each test it will mock the main module we are using
	beforeEach(angular.mock.module('mainApp'));
	
	beforeEach(angular.mock.inject(function($rootScope, $httpBackend) {
		backend = $httpBackend;
		mockscope = $rootScope.$new();
		
		backend.expect("GET", 'http://localhost:8080/TrackForce/track/unmapped/'+ mockScope.statusID).respond(
				[
					{ "name": "SDET", "count": "45"},
					{ "name": ".NET", "count": "17"},
					{ "name": "Full Stack Java", "count": "27"}
				]);
	}));
	
	// Inject dependencies needed for controller
	beforeEach(angular.mock.inject(function($rootScope, $controller, $http) {
		mockScope = $rootScope.$new();
		controller = $controller('skillsetCtrl', {
			$scope: mockScope,
			$http: $http
		});
	}));
	
	
	describe('Test functions for $http methods', function() {
		it('Test getSkillsets()', function() {
			
			var response = [
				{ "name": "SDET", "count": "45"},
				{ "name": ".NET", "count": "17"},
				{ "name": "Full Stack Java", "count": "27"}
			];
			mockscope.getSkillsets();
			expect(mockScope.skillsets).toEqual(response);
			console.log(mockScope.skillsets);
		});

	});
	
	
	
});
