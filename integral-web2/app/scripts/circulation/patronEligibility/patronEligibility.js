'use strict';

/* App Module */
define(['angular','lodash','jquery', 'circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.config(function($stateProvider) {
        $stateProvider.state('circulation.patronEligibility', {
			url : '/patroneligibility',
            templateUrl: 'views/circulation/patronEligibility/patronEligibility.html',
            controller: 'PatronEligibilityIndexCtrl',
            resolve : {
                patronEligibilities:  function(PatronEligibility){
                    return PatronEligibility.getRepository().getList();
                }
            }
		}).state('circulation.patronEligibility.create', {
			url : '/create',
            templateUrl: 'views/circulation/patronEligibility/patronEligibilityForm.html',
            controller: 'PatronEligibilityCreateCtrl'
		}).state('circulation.patronEligibility.edit', {
			url : '/{id:[0-9]{1,8}}/edit',
            templateUrl: 'views/circulation/patronEligibility/patronEligibilityForm.html',
            controller: 'PatronEligibilityEditCtrl', 
            resolve: {
                patronEligibility:  function(PatronEligibility, $stateParams){
                    return PatronEligibility.getRepository().get($stateParams.id);
                }
            }
		});
    });
        
    module.factory('AspectPatronCategoryEligibility', function(PatronCategoryRepository, EntityObjectUtil, CategorySelectionModelFactory, $q) {
        var aspect =  {
            patronCategorySelections: new Array(),
            initPatronCategories: function() {
                var deferred = $q.defer();
                var Aspect = this;
                if(angular.isUndefined(this.patronCategories)) {
                    this.patronCategories = new Array();
                }
                PatronCategoryRepository.getList().then(function(response){
                    Aspect.patronCategorySelections = response;
                    EntityObjectUtil.copyArray(Aspect.patronCategories, Aspect.patronCategorySelections);
                    Aspect.patronCategorySelectionModel = CategorySelectionModelFactory.newCategorySelectionModel(Aspect.patronCategories, Aspect.patronCategorySelections);
                    deferred.resolve(Aspect.patronCategorySelections);
                });
                return deferred.promise;
            },
            patronCategorySelectionModel: CategorySelectionModelFactory.newCategorySelectionModel()
        };
        return aspect;
    });
    
    module.factory('PatronEligibility', function(EligibilityModel, CategorySelectionModelFactory, AspectPatronCategoryEligibility) {
        this.name = "patronEligibility";
        var patronEligibilityModel =  angular.extend(this, EligibilityModel, {
            updateCriteriaValue: function() {
                this.criteria = this.patronCategorySelectionModel.getSelectionValue();
                return this.criteria;
            },
            getListAllColumnDefinition: function() {
                var columnDefinition = [{field:'maxLoanAllowed', displayName: this.getMessage('maxLoanAllowed')},
                {field:'allowOverdue', displayName: this.getMessage('allowOverdue')},
                {field:'allowReserve', displayName: this.getMessage('allowReserve')},
                {field:'finesLimit', displayName: this.getMessage('finesLimit')},
                {field:'maxFines', displayName: this.getMessage('maxFines')},
                {field:'maxReservationAllowed', displayName: this.getMessage('maxReservationAllowed')}];
                return this.getSearchColumnDefinition().concat(columnDefinition);
            }
        }, AspectPatronCategoryEligibility);
        return patronEligibilityModel;
    });

});