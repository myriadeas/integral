'use strict';

/* App Module */
define(['angular','lodash','jquery','foundation/foundation'], function (angular, _, $) {
    var module = angular.module('integral.foundation');
    module.config(function($stateProvider) {
        var stateBaseName = 'foundation.userGroup';
        var viewsBaseName = 'views/foundation/userGroup'; 
        $stateProvider.state(stateBaseName, {
            abstract: true,
            url : '/usergroup',
            templateUrl : 'views/foundation/repository.html',
            controller : 'RepositoryEntityCtrl',
            resolve:{
                entityDomainClass:  function($injector){
                    return $injector.get("UserGroup");
                }
            }
        }).state(stateBaseName + '.search', {
            url : '/search',
            templateUrl : 'views/foundation/search.html',
            controller : 'RepositoryEntitySearchCtrl'
        }).state(stateBaseName + '.view', {
            url : '/{id:[0-9]{1,8}}',
            templateUrl : viewsBaseName + '/view.html',
            controller : 'UserGroupViewCtrl',
            resolve: {
                userGroup: function(UserGroupRepository, $stateParams) {
                    return UserGroupRepository.get($stateParams.id);
                }
            }
        }).state(stateBaseName + '.edit', {
            url : '/{id:[0-9]{1,8}}/edit',
            templateUrl : viewsBaseName + '/form.html',
            controller : 'UserGroupEditCtrl',
            resolve: {
                userGroup: function(UserGroupRepository, $stateParams) {
                    return UserGroupRepository.get($stateParams.id);
                }
            }
        }).state(stateBaseName + '.create', {
            url : '/create',
            templateUrl : viewsBaseName + '/form.html',
            controller : 'UserGroupCreateCtrl'
        });
    });
    
    module.factory('UserGroup', function(RoleRepository,FoundationModel, EntityObjectUtil, $q) {
        var model =  {
            name: 'userGroup',
            roles: [],
            initRoles: function() {
                var deferred = $q.defer();
                var Model = this;
                this.getRoles().then(function(roles){
                    Model.roles = roles;
                    RoleRepository.getList().then(function(response){
                        Model.rolesSelections = response;
                        EntityObjectUtil.copyArray(Model.roles, Model.rolesSelections);
                        deferred.resolve(Model.rolesSelections);
                    });
                });
                return deferred.promise;
            }
        };
        return angular.extend(this, FoundationModel, model);
    });
    
    module.factory("UserGroupRepository", function(CrudRepository) {
        return CrudRepository.withConfig(function(Configurer){
            Configurer.setEntityName('userGroup');
            Configurer.setLazyLoading(false);
        });
    });   
});