'use strict';
define(['angular', 'lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
	module.controller('HoldingIndexCtrl', ['$scope', function($scope){ 
        
    }]); 
    
    module.controller('HoldingReleaseCtrl', ['$scope','HoldingService','$log','itemCategories','flash','Localization', function($scope, HoldingService, $log, itemCategories, flash, Localization){ 	
        $log.log("Entering HoldingReleaseCtrl");
        $scope.releaseRequest = {};
        $scope.itemCategories = itemCategories;
        $scope.newHolding = function() { 
            HoldingService.release($scope.releaseRequest).then(function(response) {
                flash.success = Localization.resolve("circulation.holding.release.success", "Successfully release for circulation.", response);
            }, function(error){
                flash.error = Localization.resolve("circulation.holding.release.fail", "Successfully release for circulation.");
            });
        };
    }]); 
});