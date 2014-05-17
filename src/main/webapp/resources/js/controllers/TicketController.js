'use strict';
var AlertDemoCtrl = function($scope, $http,$routeParams) {
	
		  $scope.alerts = [
		    { type: 'danger', msg: 'Oh snap! Change a few things up and try submitting again.' },
		    { type: 'success', msg: 'Well done! You successfully read this important alert message.' }
		  ];

		  $scope.addAlert = function() {
		    $scope.alerts.push({msg: 'Another alert!'});
		  };

		  $scope.closeAlert = function(index) {
		    $scope.alerts.splice(index, 1);
		  };

		
}

var DatepickerDemoCtrl = function ($scope) {
	  $scope.today = function() {
	    $scope.dt = new Date();
	  };
	  $scope.today();

	  $scope.clear = function () {
	    $scope.dt = null;
	  };

	  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };

	  $scope.toggleMin = function() {
	    $scope.minDate = $scope.minDate ? null : new Date();
	  };
	  $scope.toggleMin();

	  $scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	  };

	  $scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	  };

	  $scope.initDate = new Date('2016-15-20');
	  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	  $scope.format = $scope.formats[0];
	};

/**
 * TicketController
 * @constructor
 */
var TicketController = function($scope, $http,$routeParams) {
    $scope.fetchTicketsList = function() {
        $http.get('tickets/ticketlist.json/'+$routeParams.applicationId+'/'+$routeParams.start+'/'+$routeParams.end).success(function(ticketList){
            $scope.tickets = ticketList;
        });
    };

  
    $scope.fetchBlankTicket = function() {
        $http.get('tickets/new').success(function(ticket){
            $scope.ticket = ticket;
        });
    };

    $scope.addNewTicket = function(newTicket) {
        $http.post('tickets/addTicket/' + newTicket).success(function() {
            $scope.fetchTicketsList();
        });
        $scope.carName = '';
    };
    
    $scope.newTicket = function() {
    	 $http.get('tickets/blankTicket').success(function(){
    		
    		 $scope.fetchBlankTicket();
         });
    };

    $scope.removeTicket = function(ticket) {
        $http.delete('tickets/removeTicket/' + ticket).success(function() {
            $scope.fetchTicketsList();
        });
    };

    $scope.removeAllTickets = function() {
        $http.delete('tickets/removeAllTickets').success(function() {
            $scope.fetchTicketsList();
        });

    };

    $scope.fetchTicketsList();
    $scope.fetchBlankTicket();

};