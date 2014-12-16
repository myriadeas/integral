'use strict';

/* App Module */
define(['angular','lodash','jquery', 'circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.factory('HoldingService', ['$log', 'ServicesRestangular', function($log, ServicesRestangular) {
        return {
            release: function(request) {
				$log.log("Entering release", request);
				var response = ServicesRestangular.all('circulation2/holding/release').post(request,{selector:"*"});
				$log.log("Leaving release");
				return response;
            }
        };
        
    }]);
});