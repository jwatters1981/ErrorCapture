'use strict';

/**
 * CarController
 * @constructor
 */
var CreateTicketController = function($scope, $http, $location) {
  

    $scope.fetchBlankTicket = function() {
        $http.get('tickets/new').success(function(ticket){
            $scope.ticket = ticket;
            $scope.applications = ticket.applications;
        });
    };
   
    $scope.newTicket = function() {
    	 $http.get('tickets/blankTicket').success(function(){
 
    		 $scope.fetchBlankTicket();
         }).error(function(){
        	 alert("error");
         });
    };

    $scope.save = function() {
    	
        $http.post('tickets/addTicket',$scope.ticket).success(function(){
        	
        }).error(function(err){
       	 alert("Error "+err.message);
        });
        $location.path('/tickets/'+$scope.ticket.applicationsHolder.id+'/0/10');
        
    };
    $scope.fetchBlankTicket();

};

var UpdateTicketController = function($scope, $http, $location,$routeParams) {
	  

    $scope.fetchTicket = function() {
        $http.get('tickets/viewTicket/'+$routeParams.ticketId).success(function(ticket){
            $scope.ticket = ticket;
            $scope.applications = ticket.applications;
            angular.forEach($scope.ticket.applications, function(application) {
         	   if(application.id == $scope.ticket.applicationsHolder.id)
         		{
         		   $scope.ticket.applicationsHolder = application;
         		}
            });
        });
    };
   
   

    $scope.update = function() {
    	
        $http.post('tickets/updateTicket',$scope.ticket).success(function(){
        	
        });
        $location.path('/tickets/'+$scope.ticket.applicationsHolder.id+'/0/10');
        
    };
    $scope.fetchTicket();

};

var TabsDemoCtrl = function ($scope) {
	  $scope.tabs = [
	    { title:'Dynamic Title 1', content:'Dynamic content 1' },
	    { title:'Dynamic Title 2', content:'Dynamic content 2', disabled: true }
	  ];

	  $scope.alertMe = function() {
	    setTimeout(function() {
	      alert('You\'ve selected the alert tab!');
	    });
	  };
	};