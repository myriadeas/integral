'use strict';

/* App Module */
define(['angular','lodash','jquery', 'circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.config(function($stateProvider) {
        $stateProvider.state('circulation.itemEligibility', {
			url : '/itemeligibility',
            templateUrl: 'views/circulation/itemEligibility/itemEligibility.html',
            controller: 'ItemEligibilityIndexCtrl',
            resolve : {
                itemEligibilities:  function(ItemEligibility){
                    return ItemEligibility.getRepository().getList();
                }
            }
		}).state('circulation.itemEligibility.create', {
			url : '/create',
            templateUrl: 'views/circulation/itemEligibility/itemEligibilityForm.html',
            controller: 'ItemEligibilityCreateCtrl'
		}).state('circulation.itemEligibility.edit', {
			url : '/{id:[0-9]{1,8}}/edit',
            templateUrl: 'views/circulation/itemEligibility/itemEligibilityForm.html',
            controller: 'ItemEligibilityEditCtrl', 
            resolve: {
                itemEligibility:  function(ItemEligibility, $stateParams){
                    return ItemEligibility.getRepository().get($stateParams.id);
                }
            }
		});
    });
    
    module.factory('AspectItemCategoryEligibility', function(ItemCategoryRepository, EntityObjectUtil, CategorySelectionModelFactory, $q) {
        var aspect =  {
            itemCategorySelections: new Array(),
            initItemCategories: function() {
                var deferred = $q.defer();
                var Aspect = this;
                if(angular.isUndefined(this.itemCategories)) {
                    this.itemCategories = new Array();
                }
                ItemCategoryRepository.getList().then(function(response){
                    Aspect.itemCategorySelections = response;
                    EntityObjectUtil.copyArray(Aspect.itemCategories, Aspect.itemCategorySelections);
                    Aspect.itemCategorySelectionModel = CategorySelectionModelFactory.newCategorySelectionModel(Aspect.itemCategories, Aspect.itemCategorySelections);
                    deferred.resolve(Aspect.itemCategorySelections);
                });
                return deferred.promise;
            },
            itemCategorySelectionModel: CategorySelectionModelFactory.newCategorySelectionModel()
        };
        return aspect;
    });
    module.factory('ItemEligibility', function(EligibilityModel, CategorySelectionModelFactory, AspectItemCategoryEligibility) {
        this.name = "itemEligibility";
        var itemEligibilityModel =  angular.extend(this, EligibilityModel, {
            updateCriteriaValue: function() {
                this.criteria = this.itemCategorySelectionModel.getSelectionValue();
                return this.criteria;
            },
            getListAllColumnDefinition: function() {
                var columnDefinition = {field:'maxLoanAllowed', displayName: this.getMessage('maxLoanAllowed')};
                return this.getSearchColumnDefinition().concat(columnDefinition);
            }
        }, AspectItemCategoryEligibility);
        return itemEligibilityModel;
    });

});