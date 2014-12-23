'use strict';
define(['angular', 'lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
	module.controller('BorrowerIndexCtrl', ['$scope', function($scope){ 
        
    }]); 
    
    module.controller('BorrowerRegisterCtrl', ['$scope','BorrowerService','$log','patronCategories','flash','Localization', '$stateParams', function($scope, BorrowerService, $log, patronCategories, flash, Localization, $stateParams){ 	
        $log.log("Entering BorrowerRegisterCtrl");
<<<<<<< HEAD
        $scope.registerRequest = {};
=======
        $scope.registerRequest = {username: $stateParams.username};
>>>>>>> 184cf21d62bc4fa747be3dfd6d7f56d9caed5f85
        $scope.patronCategories = patronCategories;
        $scope.register = function() { 
            BorrowerService.register($scope.registerRequest).then(function(response) {
                flash.success = Localization.resolve("circulation.borrower.register.success", "Successfully register borrower.", response);
            }, function(error){
                flash.error = Localization.resolve("circulation.borrower.register.fail", "Fail to register boorwer.");
            });
        };
    }]); 
});