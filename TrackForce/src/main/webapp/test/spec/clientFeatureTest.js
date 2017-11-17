'use strict';

// Test Suite for Batch Feature change
describe('Test Batch Feature Suite', function() {

	// Arrange variables for rootScope and controller
	
	// Before each test it will mock the main module we are using
	beforeEach(angular.mock.module('mainApp'));
	
	// Here we setup the $httpBackend service from angular-mocks that mocks http requests
	// Allows us to not depend on Rest Services to be setup to test http requests
	beforeEach(angular.mock.inject(function($httpBackend, $rootScope) {
		var backend = $httpBackend;
		backend.expect("GET", 'http://localhost:8080/TrackForce/track/clients/info').respond(
				[
					{"name":"All Clients","trainingMapped":100,"trainingUnmapped":95,"reservedMapped":75,"reservedUnmapped":107,"selectedMapped":145,"selectedUnmapped":23,"confirmedMapped":65,"confirmedUnmapped":72,"deployedMapped":15,"deployedUnmapped":34}
				]);
	}));

	// Inject dependencies needed for controller
	beforeEach(angular.mock.inject(function($rootScope, $controller, $http) {
		var mockScope = $rootScope.$new();
		var controller = $controller('clientCtrl', {
			$scope: mockScope,
			$http: $http
		});
	}));

	// Test functions that call http methods
	describe('Test functions for $http methods', function() {
		it('Test getAllClients()', function() {
			mockScope.getAllClients();
			console.log(mockScope.clients);
			expect(mockScope.getAllClients).not.toBeNull();
			console.log('hello there');
			
		});
		
		it('Test getAllClients1()', function() {
			mockScope.getAllClients();
			// This ensures that the http request was sent
			// ***Still trying to check if the data is generated***
			backend.verifyNoOutstandingExpectation();
		});
	});

});
