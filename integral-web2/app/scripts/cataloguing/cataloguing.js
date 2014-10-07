'use strict';

/* App Module */

define(['lodash','jquery','schema-form','bootstrap-decorator','angular-history','utility/inflection','global/global', 'repository/crudRepository', 'repository/crudControllers'], function (_, $) {
    var module = angular.module('integral.cataloguing', ['integral.global', 'integral.repository', 
	  'ngGrid',
	  'ui.bootstrap',
      'schemaForm']);
    var cataloguingEntities = ["item"];
    var moduleName = 'cataloguing';
    module.config(function($stateProvider) {
        $stateProvider.state('cataloguing', {
            abstract: true,
            url : '/cataloguing',
            templateUrl : 'views/cataloguing/cataloguing.html',
            controller : 'CataloguingIndexController'
        }).state('cataloguing.home', {
            url : '/home',
            templateUrl : 'views/cataloguing/cataloguingHome.html',
            controller : 'CataloguingHomeController'
        }).state('cataloguing.release', {
            url : '/release?transactionId',
            templateUrl : 'views/cataloguing/release/release.html',
            controller : 'ReleaseController'
        });
        
        function setupMaintenanceEntitiesState(moduleName, entities) {
            $.each(entities, function(key, entity) {
                var stateBaseName = moduleName + '.' + entity;
                var viewsBaseName = 'views/' + moduleName + '/' + entity; 
                $stateProvider.state(stateBaseName, {
                    abstract: true,
                    url : '/' + entity,
                    templateUrl : 'views/cataloguing/repository.html',
                    controller : 'RepositoryEntityCtrl',
                    resolve:{
                        entityDomainClass:  function($injector){
                            return $injector.get(entity.capitalize());
                        }
                    }
                }).state(stateBaseName + '.search', {
                    url : '/search',
                    templateUrl : 'views/cataloguing/search.html',
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
        
        setupMaintenanceEntitiesState(moduleName, cataloguingEntities);
    });
    
    
    module.factory('CataloguingModel', function(AuditableModel, Localization) {
        return angular.extend(this,AuditableModel, {
            getUri: function() {
                return moduleName + '/' + this.name.toLowerCase();
            }
        });
    });
    
    function setupDefaultEntityAndRepository(entities) {
        $.each(entities, function(key, entity) {
            var entityFactoryName = entity.capitalize();
            module.factory(entityFactoryName, function(CataloguingModel) {
                this.name = entity;
                return angular.extend(this, CataloguingModel);
            });     
            module.factory(entityFactoryName + "Repository", function(CrudRepository) {
                return CrudRepository.withConfig(function(Configurer){
                    Configurer.setEntityName(entity);
                });
            });   
        });
    }
    function postSetup() {        
        module.factory('Item', function(CataloguingModel, ServicesRestangular) {
            this.name = "item";
            var itemModel =  angular.extend(this, CataloguingModel, {
                getDescription: function() {
                    console.log("postSetup", this);
                    return this.itemIdentifier;
                },
                getSearchColumnDefinition: function() {                
                    return [
                        {
                            "field" : "No",
                            "displayName" : "No.",
                            "cellTemplate" : "<div  class='ngCellText'>{{row.rowIndex + 1}}</div>",
                            "width" : 100
                        },
                        {field:'itemIdentifier', displayName: this.getMessage('itemIdentifier')},
                        {field:'material.title', displayName: this.getMessage('title')},
                        {field:'itemCategory.description', displayName: this.getMessage('itemCategory')},
                        {field:'smd.description', displayName: this.getMessage('smd')},
                        {field:'location.description', displayName: this.getMessage('location')}
                    ]
                },
            });
            return itemModel;
        });
        
        module.factory('Material', function(CataloguingModel, ServicesRestangular) {
            this.name = "material";
            var materialModel =  angular.extend(this, CataloguingModel, {
                getDescription: function() {
                    console.log("postSetup", this);
                    return this.title;
                },
                getSearchColumnDefinition: function() {                
                    return [
                        {
                            "field" : "No",
                            "displayName" : "No.",
                            "cellTemplate" : "<div  class='ngCellText'>{{row.rowIndex + 1}}</div>",
                            "width" : 100
                        },
                        {
                            "field" : "materialNo",
                            "displayName" : this.getMessage('materialNo'),
                            "width" : 130
                        },
                        {
                            "field" : "isbn",
                            "displayName" : this.getMessage('isbn')
                        },
                        {
                            "field" : "title",
                            "displayName" : this.getMessage('title'),
                            "cellTemplate" : "<div class='ngCellText'><a ng-href='#{{row.entity.getViewLink()}}' target='_blank'>{{row.entity[col.field]}}</a></div>"
                        },
                        {
                            "field" : "author",
                            "displayName" : this.getMessage('author')
                        },
                        {
                            "field" : "edition",
                            "displayName" : this.getMessage('edition')
                        }
                        ]
                }
            });
            return materialModel;
        });
        
    }
    setupDefaultEntityAndRepository(cataloguingEntities);
    setupDefaultEntityAndRepository(['material'])
    postSetup();
});
