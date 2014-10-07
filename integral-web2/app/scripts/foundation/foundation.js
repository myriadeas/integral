'use strict';

/* App Module */
define(['app','angular', 'lodash','jquery','utility/inflection', 'global/global', 'repository/crudRepository', 'repository/crudControllers'], function (integralApp,angular, _, $) {
    var module = angular.module('integral.foundation',['integral.global']);
    var foundationEntities = [ "sMD", "itemCategory", "condition", "itemStatus", "branch","course", "department", "designation", "location","patronCategory", "patron", "race", "religion", "title", "town", "role", "permission", "state"];
    
    var moduleName = 'foundation';
    module.constant('foundationConfig', {
        entities: foundationEntities
    });

    module.config(function($stateProvider) {
        $stateProvider.state('foundation', {
            abstract: true,
			url : '/foundation',
			templateUrl : 'views/foundation/foundation.html',
            controller : 'FoundationIndexController'
		}).state('foundation.home', {
			url : '/home',
			templateUrl : 'views/foundation/home.html',
            controller : 'FoundationHomeController'
		}).state('foundation.patron.register', {
			url : '/register',
			templateUrl : 'views/foundation/patron/form.html',
            controller : 'NewRegisterController'
		});
        function setupMaintenanceEntitiesState(moduleName, entities) {
            $.each(entities, function(key, entity) {
                var stateBaseName = moduleName + '.' + entity;
                var viewsBaseName = 'views/' + moduleName + '/' + entity; 
                $stateProvider.state(stateBaseName, {
                    abstract: true,
                    url : '/' + entity.toLowerCase(),
                    templateUrl : 'views/foundation/repository.html',
                    controller : 'RepositoryEntityCtrl',
                    resolve:{
                        entityDomainClass:  function($injector){
                            return $injector.get(entity.capitalize());
                        }
                    }
                }).state(stateBaseName + '.search', {
                    url : '/search',
                    templateUrl : 'views/foundation/search.html',
                    controller : 'RepositoryEntitySearchCtrl'
                }).state(stateBaseName + '.view', {
                    url : '/{id:[0-9]{1,8}}',
                    templateUrl : viewsBaseName + '/view.html',
                    controller : 'RepositoryEntityViewCtrl'
                }).state(stateBaseName + '.edit', {
                    url : '/{id:[0-9]{1,8}}/edit',
                    templateUrl : viewsBaseName + '/form.html',
                    controller : 'RepositoryEntityEditCtrl'
                }).state(stateBaseName + '.create', {
                    url : '/create',
                    templateUrl : viewsBaseName + '/form.html',
                    controller : 'RepositoryEntityCreateCtrl'
                });
            });
        }
        setupMaintenanceEntitiesState(moduleName, foundationEntities);
	});
    
    module.factory('FoundationModel', function(AuditableModel, Localization) {
        return angular.extend(this, AuditableModel, {
            getUri: function() {
                return moduleName + '/' + this.name.toLowerCase();
            }
        });
    });
    
    module.factory('UserModel', function(FoundationModel, Localization) {
        return angular.extend(this, FoundationModel, {
            getDescription: function() {
                return this.firstname;
            },
            getSearchColumnDefinition: function(isModal) {
                return [
                    {
                        "field" : "No",
                        "displayName" : "No.",
                        "width" : 100,
                        "pinned" : true,
                        "cellTemplate" : "<div  class='ngCellText'>{{row.rowIndex + 1}}</div>"
                    },
                    {
                        "field" : "username",
                        "displayName" : Localization.resolve(this.name + '.username'),
                        "pinned" : true,
                        "cellTemplate" : this.getViewCellTemplate(isModal)
                    },
                    {
                        "field" : "firstname",
                        "displayName" : Localization.resolve(this.name + '.firstname')
                    }
                ];
            }
        });
    });
    function setupDefaultEntityAndRepository(entities) {
        $.each(entities, function(key, entity) {
            var entityFactoryName = entity.capitalize();
            module.factory(entityFactoryName, function(FoundationModel) {
                this.name = entity;
                return angular.extend(this, FoundationModel);
            });     
            module.factory(entityFactoryName + "Repository", function(CrudRepository) {
                return CrudRepository.withConfig(function(Configurer){
                    Configurer.setEntityName(entity);
                });
            });   
        });
    }
    function postSetup() {
        module.factory('Patron', function(UserModel) {
            this.name = "patron";
            return angular.extend(this, UserModel);
        });
        module.factory('Officer', function(UserModel) {
            this.name = "officer";
            return angular.extend(this, UserModel);
        });
        module.factory('Town', function(FoundationModel) {
            this.name = "town";
            return angular.extend(this, FoundationModel, {
                getDescription: function() {
                    return this.code + '-' + this.description;
                }
            });
        });
    }
    setupDefaultEntityAndRepository(foundationEntities);
    setupDefaultEntityAndRepository(["userGroup","officer"]);
    postSetup();
    
});
