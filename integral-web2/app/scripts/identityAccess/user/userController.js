'use strict';
define(['angular', 'lodash','jquery','../identityAccessModule','./userService'], function (angular, _, $) {
    var module = angular.module('integral.identityAccess');
	module.controller('UserIndexCtrl', ['$scope', function($scope){ 
        
    }]); 
    
    module.controller('UserRegisterCtrl', ['$scope','UserService','$log','flash','Localization', function($scope, UserService, $log, flash, Localization){ 	
        $log.log("Entering UserRegisterCtrl");
        $scope.registerUserRequest = {};
        $scope.register = function() { 
            UserService.register($scope.registerUserRequest).then(function(response) {
                flash.success = Localization.resolve("identityAccess.user.register.success", "Successfully register user", response);
            }, function(errors){
                flash.error = errors.getHtmlErrorMessages();
            });
        };
    }]); 
});