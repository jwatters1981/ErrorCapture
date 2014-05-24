'use strict';



var App = angular.module('AngularSpringApp', [ 'ngRoute',
		'AngularSpringApp.filters', 'AngularSpringApp.services',
		'AngularSpringApp.directives', 'ui.bootstrap' ]).provider({
		    
		    $exceptionHandler: function(){
		        var handler = function(exception, cause) {
		            alert("Problem with the request"+exception);
		           
		        };

		        this.$get = function() {
		            return handler;
		        };
		    }
		});;

// Declare app level module which depends on filters, and services
App.config([ '$routeProvider', function($routeProvider) {

	
	
	$routeProvider.when('/tickets', {
		templateUrl : 'tickets/layout',
		controller : TicketController
	});

	$routeProvider.when('/tickets/:applicationId/:start/:end', {
		templateUrl : 'tickets/layout',
		controller : TicketController
	});

	$routeProvider.when('/application', {
		templateUrl : 'application/layout',
		controller : ApplicationController
	});
	
	$routeProvider.when('/landing', {
		templateUrl : 'landing/viewLanding',
		controller : LandingController
	});

	$routeProvider.when('/dashboard', {
		templateUrl : 'dashboard/viewDashboard',
		controller : DashboardController
	});

	$routeProvider.when('/tickets/newTicket', {

		templateUrl : 'tickets/newBlankTicket',
		controller : CreateTicketController
	});
	
	$routeProvider.when('/tickets/viewTicket/:ticketId', {

		templateUrl : 'tickets/updateTicket',
		controller : UpdateTicketController
	});

	$routeProvider.when('/application/new', {

		templateUrl : 'application/newBlankApplication',
		controller : CreateApplicationController
	});

	$routeProvider.otherwise({
		redirectTo : '/landing'
	});
} ]);
