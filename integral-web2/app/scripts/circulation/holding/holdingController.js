'use strict';
define(['angular', 'lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
	module.controller('HoldingIndexCtrl', ['$scope', function($scope){ 
        
    }]); 
    
    module.controller('HoldingNewHoldingCtrl', ['$scope','HoldingService','$log','itemCategories','flash','Localization', function($scope, HoldingService, $log, itemCategories, flash, Localization){ 	
        $log.log("Entering HoldingNewHoldingCtrl");
        $scope.newHoldingRequest = {};
        $scope.itemCategories = itemCategories;
        $scope.newHolding = function() { 
            HoldingService.newHolding($scope.newHoldingRequest).then(function(response) {
                flash.success = Localization.resolve("circulation.holding.newHolding.success", "Successfully release for circulation.", response);
            }, function(error){
                flash.error = Localization.resolve("circulation.holding.newHolding.fail", "Successfully release for circulation.");
            });
        };
    }]); 
});