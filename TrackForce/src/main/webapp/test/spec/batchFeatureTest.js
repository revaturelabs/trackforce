'use strict';

// Test Suite for Batch Feature change
describe('Test Batch Feature Suite', function() {

	// Arrange variables for rootScope and controller
	var mockScope = {};
	var controller;
	var backend;
	
	// Before each test it will mock the main module we are using
	beforeEach(angular.mock.module('mainApp'));
	
	// Here we setup the $httpBackend service from angular-mocks that mocks http requests
	// Allows us to not depend on Rest Services to be setup to test http requests
	beforeEach(angular.mock.inject(function($httpBackend) {
		backend = $httpBackend;
		backend.expect("GET", 'http://localhost:8080/TrackForce/track/batches').respond(
				[
					{ "name": "SDET", "startdate": "hello", "enddate": "bye"},
					{ "name": ".NET", "startdate": "hello", "enddate": "bye"},
					{ "name": "PEGA", "startdate": "hello", "enddate": "bye"}
				]);
	}));

	// Inject dependencies needed for controller
	beforeEach(angular.mock.inject(function($rootScope, $controller, $http) {
		mockScope = $rootScope.$new();
		controller = $controller('batchCtrl', {
			$scope: mockScope,
			$http: $http
		});
	}));

	// Test functions that call http methods
	describe('Test functions for $http methods', function() {
		it('Test getBatches()', function() {
			expect(mockScope.getBatches).not.toBeNull();
			console.log('hello there');
			console.log(mockScope.batches);
		});
		
		it('Test getBatches1()', function() {
			mockScope.getBatches();
			
			// This ensures that the http request was sent
			// ***Still trying to check if the data is generated***
			backend.verifyNoOutstandingExpectation();
		});
	});

});
