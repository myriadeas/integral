'use strict';
define(['angular', 'lodash','jquery','../identityAccessModule','./officerService'], function (angular, _, $) {
    var module = angular.module('integral.identityAccess');
	module.controller('OfficerIndexCtrl', ['$scope', function($scope){ 
        
    }]); 
    
    module.controller('OfficerRegisterCtrl', ['$scope','OfficerService','$log','flash','Localization', function($scope, OfficerService, $log, flash, Localization){ 	
        $log.log("Entering OfficerRegisterCtrl");
        $scope.registerOfficerRequest = {};
        $scope.register = function() { 
            OfficerService.register($scope.registerOfficerRequest).then(function(response) {
                flash.success = Localization.resolve("identityAccess.officer.register.success", "Successfully register officer", response);
                $scope.nextAction=true;
            }, function(errors){
                flash.error = errors.getHtmlErrorMessages();
                //flash.success = Localization.resolve("identityAccess.officer.register.success", "Successfully register officer", errors);
            });
        };
    }]); 
});
