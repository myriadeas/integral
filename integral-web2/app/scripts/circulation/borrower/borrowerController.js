'use strict';
define(['angular', 'lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
	module.controller('BorrowerIndexCtrl', ['$scope', function($scope){ 
        
    }]); 
    
    module.controller('BorrowerRegisterCtrl', ['$scope','BorrowerService','$log','patronCategories','flash','Localization', function($scope, BorrowerService, $log, patronCategories, flash, Localization){ 	
        $log.log("Entering BorrowerRegisterCtrl");
        $scope.releaseRequest = {};
        $scope.patronCategories = patronCategories;
        $scope.register = function() { 
            BorrowerService.register($scope.releaseRequest).then(function(response) {
                flash.success = Localization.resolve("circulation.borrower.register.success", "Successfully register borrower.", response);
            }, function(error){
                flash.error = Localization.resolve("circulation.borrower.register.fail", "Fail to register boorwer.");
            });
        };
    }]); 
});