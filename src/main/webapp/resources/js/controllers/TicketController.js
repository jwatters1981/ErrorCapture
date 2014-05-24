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
var ErrorController = function($scope, $exceptionHandler) {
    console.log($exceptionHandler);
    //throw "Fatal error";
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
        $http.post('tickets/addTicket/' + newTicket).then(onSuccess, onError);

        function onSuccess(data) {
        	  $scope.fetchTicketsList();
        }
        
        function onError(data) {
        	alert("error");
        }
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