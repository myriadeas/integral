'use strict';

/* App Module */
define(['angular','lodash','jquery', 'circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.config(function($stateProvider) {
        $stateProvider.state('circulation.holding', {
			url : '/holding',
            templateUrl: 'views/circulation/holding/holding.html',
            controller: 'HoldingIndexCtrl'
		}).state('circulation.holding.newHolding', {
			url : '/newholding',
            templateUrl: 'views/circulation/holding/newHolding.html',
            controller: 'HoldingNewHoldingCtrl',
            resolve : {
                itemCategories: function(ItemCategory) {
                    return ItemCategory.getRepository().getList();
                }
            }
		});
    });
});