'use strict';

/* App Module */
define(['angular','lodash','jquery', '../identityAccessModule'], function (angular, _, $) {
    var module = angular.module('integral.identityAccess');
    module.factory('UserService', ['$log', 'ServicesRestangular', function($log, ServicesRestangular) {
        return {
            register: function(request) {
				$log.log("Entering register", request);
				var response = ServicesRestangular.all('identityaccess/identity/user/register').post(request,{selector:"*(*(*))"});
				$log.log("Leaving register");
				return response;
            }
        };
        
    }]);
});