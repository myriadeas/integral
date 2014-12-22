'use strict';

/* App Module */
define(['angular','lodash','jquery', 'circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.factory('BorrowerService', ['$log', 'ServicesRestangular', function($log, ServicesRestangular) {
        return {
            register: function(request) {
				$log.log("Entering register", request);
				var response = ServicesRestangular.all('circulation2/borrower/register').post(request,{selector:"*"});
				$log.log("Leaving release");
				return response;
            }
        };
        
    }]);
});