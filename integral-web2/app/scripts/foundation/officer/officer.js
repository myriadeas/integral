'use strict';

/* App Module */
define(['angular','lodash','jquery','foundation/foundation','foundation/officer/officerController'], function (angular, _, $) {
    var module = angular.module('integral.foundation');
    module.config(function($stateProvider) {
        var stateBaseName = 'foundation.officer';
        var viewsBaseName = 'views/foundation/officer'; 
        $stateProvider.state(stateBaseName, {
            abstract: true,
            url : '/officer',
            templateUrl : 'views/foundation/repository.html',
            controller : 'RepositoryEntityCtrl',
            resolve:{
                entityDomainClass:  function($injector){
                    return $injector.get("Officer");
                }
            }
        }).state(stateBaseName + '.search', {
            url : '/search',
            templateUrl : 'views/foundation/search.html',
            controller : 'RepositoryEntitySearchCtrl'
        }).state(stateBaseName + '.view', {
            url : '/{id:[0-9]{1,8}}',
            templateUrl : viewsBaseName + '/view.html',
            controller : 'OfficerViewCtrl',
            resolve: {
                officer: function(OfficerRepository, $stateParams) {
                    return OfficerRepository.get($stateParams.id);
                }
            }
        }).state(stateBaseName + '.edit', {
            url : '/{id:[0-9]{1,8}}/edit',
            templateUrl : viewsBaseName + '/form.html',
            controller : 'OfficerEditCtrl',
            resolve: {
                officer: function(OfficerRepository, $stateParams) {
                    return OfficerRepository.get($stateParams.id);
                }
            }
        }).state(stateBaseName + '.create', {
            url : '/create',
            templateUrl : viewsBaseName + '/form.html',
            controller : 'OfficerCreateCtrl'
        }).state('foundation.officer.register', {
			url : '/register',
			templateUrl : 'views/foundation/officer/simpleForm.html',
            controller : 'OfficerCreateCtrl'
		});
    });
    
    module.factory('Officer', function(UserGroupRepository,UserModel, EntityObjectUtil, $q) {
        this.name = 'officer';
        var model =  {
            userGroups: [],
            initUserGroups: function() {
                var deferred = $q.defer();
                var Model = this;
                this.getUserGroups().then(function(userGroups){
                    Model.userGroups = userGroups;
                    UserGroupRepository.getList().then(function(response){
                        Model.userGroupsSelections = response;
                        if(!angular.isUndefined(Model.userGroups)) {
                            EntityObjectUtil.copyArray(Model.userGroups, Model.userGroupsSelections);
                        }
                        deferred.resolve(Model.userGroupsSelections);
                    });
                });
                return deferred.promise;
            },
            datepickers: {
                dateOfBirth: {
                    isOpened: false
                }
            },
            lazyLoading: function() {
                this.getLastModifiedBy();
                this.getBranch();
                this.getRace();
                this.getReligion();
                this.getDesignation();
                this.getDepartment();
                this.getTitle();
            }
        };
        return angular.extend(this, UserModel, model);
    });
    
    module.factory("OfficerRepository", function(CrudRepository) {
        return CrudRepository.withConfig(function(Configurer){
            Configurer.setEntityName('officer');
            Configurer.setLazyLoading(false);
        });
    });   
});