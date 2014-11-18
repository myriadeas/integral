'use strict';

/* App Module */
define(['angular','lodash','jquery', 'circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.factory('HoldingService', ['$log', 'ServicesRestangular', function($log, ServicesRestangular) {
        return {
            newHolding: function(request) {
				$log.log("Entering newHolding", request);
				var response = ServicesRestangular.all('circulation2/holding/newHolding').post(request,{selector:"*"});
				$log.log("Leaving newHolding");
				return response;
            }
        };
        
    }]);
});