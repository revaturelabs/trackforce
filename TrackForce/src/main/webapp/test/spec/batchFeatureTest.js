'use strict';

// Test Suite for Batch Feature change
describe('Test Batch Feature Suite', function() {

	// Before each test it will mock the main module we are using
	beforeEach(angular.mock.module('mainApp'));
	
	// Here we setup the $httpBackend service from angular-mocks that mocks http requests
	// Allows us to not depend on Rest Services to be setup to test http requests
	beforeEach(angular.mock.inject(function($httpBackend, $rootScope) {
		var backend = $httpBackend;
		var fromDate = new Date(123456789);
		var toDate = new Date(123456789);
		
		$rootScope.backend.expect("GET", 'http://localhost:8080/TrackForce/track/batches/' + fromDate.getTime() + '/' + toDate.getTime()).respond(
				[
					{ "name": "SDET", "startdate": "hello", "enddate": "bye"},
					{ "name": ".NET", "startdate": "hello", "enddate": "bye"},
					{ "name": "PEGA", "startdate": "hello", "enddate": "bye"}
				]);
	}));

	// Inject dependencies needed for controller
	beforeEach(angular.mock.inject(function($rootScope, $controller, $http) {
		var mockScope = $rootScope.$new();
		var controller = $controller('batchCtrl', {
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

	});

});
