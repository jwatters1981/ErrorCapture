'use strict';

/**
 * CarController
 * 
 * @constructor
 */
var ApplicationController = function($scope, $http) {
	$scope.fetchApplicationsList = function() {
		$http.get('application/list').success(function(ticketList) {
			$scope.applications = ticketList;
		});
	};

	$scope.fetchApplicationsList();
	// $scope.fetchBlankApplication();

};

var LandingController = function($scope, $http) {
	
	$scope.fetchLandingData = function() {
		$http.get('landing/landingData').success(function(landingDTO) {
			$scope.landingDTO = landingDTO;
		});
	};
	$scope.fetchLandingData();

};


var RolesController = function($scope, $http) {
	$scope.initializeRoles = function() {
		$http.get('authenicateduser/retrieve').success(function(userRole) {
			$scope.permissions = {
				showAdmin : userRole.grantedRoles.indexOf("ADMIN") > -1,
				showUser : userRole.grantedRoles.indexOf("USER") > -1,
				showManager : userRole.grantedRoles.indexOf("MANAGER") > -1

			}
		});
	};

	$scope.initializeRoles();
	// $scope.fetchBlankApplication();

};
