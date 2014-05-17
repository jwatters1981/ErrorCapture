'use strict';

/**
 * CarController
 * @constructor
 */
var CreateApplicationController = function($scope, $http,$routeParams) {
	$scope.fetchBlankApplication = function() {
		
		$http.get('application/newApp').success(function(application){
        	
            $scope.application = application;
        });
    };
    
    $scope.addNewApplication = function() {
        $http.post('application/addApplication', $scope.application).success(function() {
        });
     
    };
   
    $scope.fetchBlankApplication();


};