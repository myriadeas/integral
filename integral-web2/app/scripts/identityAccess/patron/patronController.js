'use strict';
define(['angular', 'lodash','jquery','../identityAccessModule','./patronService'], function (angular, _, $) {
    var module = angular.module('integral.identityAccess');
	module.controller('PatronIndexCtrl', ['$scope', function($scope){ 
        
    }]); 
    
    module.controller('PatronRegisterCtrl', ['$scope','PatronService','$log','flash','Localization', function($scope, PatronService, $log, flash, Localization){ 	
        $log.log("Entering PatronRegisterCtrl");
        $scope.registerPatronRequest = {};
        $scope.register = function() { 
            PatronService.register($scope.registerPatronRequest).then(function(response) {
                flash.success = Localization.resolve("identityAccess.patron.register.success", "Successfully register patron", response);
                $scope.nextAction=true;
            }, function(errors){
                flash.error = errors.getHtmlErrorMessages();
                //flash.success = Localization.resolve("identityAccess.patron.register.success", "Successfully register officer", errors);
            });
        };
    }]); 
});
