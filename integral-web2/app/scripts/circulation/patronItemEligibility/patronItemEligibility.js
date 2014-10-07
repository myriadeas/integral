'use strict';

/* App Module */
define(['angular','lodash','jquery', 'circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.config(function($stateProvider) {
        $stateProvider.state('circulation.patronItemEligibility', {
			url : '/patronitemeligibility',
            templateUrl: 'views/circulation/patronItemEligibility/patronItemEligibility.html',
            controller: 'PatronItemEligibilityIndexCtrl',
            resolve : {
                patronItemEligibilities:  function(PatronItemEligibility){
                    return PatronItemEligibility.getRepository().getList();
                }
            }
		}).state('circulation.patronItemEligibility.create', {
			url : '/create',
            templateUrl: 'views/circulation/patronItemEligibility/patronItemEligibilityForm.html',
            controller: 'PatronItemEligibilityCreateCtrl'
		}).state('circulation.patronItemEligibility.edit', {
			url : '/{id:[0-9]{1,8}}/edit',
            templateUrl: 'views/circulation/patronItemEligibility/patronItemEligibilityForm.html',
            controller: 'PatronItemEligibilityEditCtrl', 
            resolve: {
                patronItemEligibility:  function(PatronItemEligibility, $stateParams){
                    return PatronItemEligibility.getRepository().get($stateParams.id);
                }
            }
		}).state('circulation.patronItemEligibility.view', {
			url : '/{id:[0-9]{1,8}}/view'
		});
    });
    
    module.factory('LoanUnit', function(){
        return [
            "DAILY", "HOURLY"
        ];
    })
    

    module.factory('PatronItemEligibility', function(EligibilityModel, CategorySelectionModelFactory, AspectItemCategoryEligibility, AspectPatronCategoryEligibility) {
        this.name = "patronItemEligibility";
        var Fine = function(start, stop, rate) {
            this.rate = rate;
            this.start = start;
            this.stop = stop;
            this.validate = function() {
                if(this.start > this.stop && this.stop >= 0) {
                    this.stop = this.start;
                }
                if(parseInt(this.rate) < 0) {
                    this.rate = 0.00;
                }
            }
        }
    
        var patronItemEligibilityModel =  angular.extend(this, EligibilityModel, {
            updateCriteriaValue: function() {
                this.criteria = this.itemCategorySelectionModel.getSelectionValue();
                this.criteria += " ";
                this.criteria += this.patronCategorySelectionModel.getSelectionValue();
                return this.criteria;
            },
            getListAllColumnDefinition: function() {
                var columnDefinition = [{field:'allowOverdue', displayName: this.getMessage('allowOverdue')}, 
                {field:'allowReserve', displayName: this.getMessage('allowReserve')}, 
                {field:'includeFines', displayName: this.getMessage('includeFines')}, 
                {field:'maxLoanAllowed', displayName: this.getMessage('maxLoanAllowed')}, 
                {field:'maxFines', displayName: this.getMessage('maxFines')}, 
                {field:'loanPeriod', displayName: this.getMessage('loanPeriod')}, 
                {field:'loanUnit', displayName: this.getMessage('loanUnit')}, 
                {field:'maxRenewAllowed', displayName: this.getMessage('maxRenewAllowed')}];
                return this.getSearchColumnDefinition().concat(columnDefinition);
            },
            addNewFine: function() {
                if(this.fines.length == 0) {
                    this.fines.push(new Fine(0,0, 0.00));
                } else {
                    var fine = this.getLastFine();
                    var start = parseInt(fine.stop) + 1;
                    var stop = -1;
                    if(fine.stop >= 0 ) {
                        this.fines.push(new Fine(start, stop, 0.00));
                    }
                }
            },
            getLastFine: function() {
                return _.last(this.fines);
            },
            removeFine: function() {
                if(this.fines.length > 0) {
                    this.fines.splice(this.fines.length - 1, 1);
                }
            },
            init: function() {
                if(!angular.isUndefined(this.fines)) {
                    var fines = this.fines;
                    $.each(this.fines, function(key, fine) {
                        fines[key] = new Fine(fine.start, fine.stop, fine.rate);
                        fines[key].id = fine.id;
                    });
                }
            }
        }, AspectItemCategoryEligibility, AspectPatronCategoryEligibility);
        return patronItemEligibilityModel;
    });
});