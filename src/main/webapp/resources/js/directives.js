'use strict';

/* Directives */

var AppDirectives = angular.module('AngularSpringApp.directives', []);

AppDirectives.directive('appVersion', ['version', function (version) {
    return function (scope, elm, attrs) {
        elm.text(version);
    };
}]);


AppDirectives.directive('chart', function() {
    return {
        restrict: 'E',
        link: function(scope, elem, attrs) {
            var data = scope[attrs.ngModel];
            var options = scope[attrs.ngOptions];
            $.plot(elem, data, options);
           
        }
    };
});
