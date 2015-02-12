'use strict';

/* App Module */

define(['lodash','jquery','schema-form','bootstrap-decorator','angular-history','utility/inflection','global/global', 'repository/crudRepository', 'repository/crudControllers'], function (_, $) {
    var module = angular.module('integral.cataloguing2', ['integral.global', 'integral.repository',
	  'ngGrid',
	  'ui.bootstrap',
      'schemaForm']);
    var moduleName = 'cataloguing2';
    var cataloguing2Entities = ["item"];
    module.config(function($stateProvider) {
        $stateProvider.state('cataloguing2', {
            abstract: true,
            url : '/cataloguing2',
            templateUrl : 'views/cataloguing2/cataloguing.html',
            controller : 'Cataloguing2IndexController'
        }).state('cataloguing2.home', {
            url : '/home',
            templateUrl : 'views/cataloguing2/cataloguingHome.html',
            controller : 'Cataloguing2HomeController'
        });
        
        function setupMaintenanceEntitiesState(moduleName, entities) {
            $.each(entities, function(key, entity) {
                var stateBaseName = moduleName + '.' + entity;
                var viewsBaseName = 'views/' + moduleName + '/' + entity; 
                $stateProvider.state(stateBaseName, {
                    abstract: true,
                    url : '/' + entity,
                    templateUrl : 'views/cataloguing2/repository.html',
                    controller : 'RepositoryEntityCtrl',
                    resolve:{
                        entityDomainClass:  function($injector){
                            return $injector.get(entity.capitalize());
                        }
                    }
                }).state(stateBaseName + '.search', {
                    url : '/search',
                    templateUrl : 'views/cataloguing2/search.html',
                    controller : 'RepositoryEntitySearchCtrl'
                }).state(stateBaseName + '.view', {
                    url : '/{id:[0-9]{1,8}}',
                    templateUrl : viewsBaseName + '/view.html',
                    controller : 'RepositoryEntityViewCtrl'
                }).state(stateBaseName + '.edit', {
                    url : '/{id:[0-9]{1,8}}/edit',
                    templateUrl : viewsBaseName + '/form.html',
                    controller : 'RepositoryEntityEditCtrl'
                }).state(stateBaseName + '.finalize', {
                    url : '/{id:[0-9]{1,8}}/edit',
                    templateUrl : viewsBaseName + '/form.html',
                    controller : 'RepositoryEntityEditCtrl'
                }).state(stateBaseName + '.create', {
                    url : '/create',
                    templateUrl : viewsBaseName + '/form.html',
                    controller : 'RepositoryEntityCreateCtrl'
				}).state(stateBaseName + '.new', {
                    url : '/new',
                    templateUrl : viewsBaseName + '/create.html',
                    controller : 'CreateItemController'
                });
            });
        }
		
        
        setupMaintenanceEntitiesState(moduleName, cataloguing2Entities);
	
    });
    
    
    module.factory('Cataloguing2Model', function(AuditableModel, Localization) {
        return angular.extend(this,AuditableModel, {
            getUri: function() {
                return moduleName + '/' + this.name.toLowerCase();
            }
        });
    });
    
    function setupDefaultEntityAndRepository(entities) {
        $.each(entities, function(key, entity) {
            var entityFactoryName = entity.capitalize();
            module.factory(entityFactoryName, function(Cataloguing2Model) {
                this.name = entity;
                return angular.extend(this, Cataloguing2Model);
            });     
            module.factory(entityFactoryName + "Repository", function(CrudRepository) {
                return CrudRepository.withConfig(function(Configurer){
                    Configurer.setEntityName(entity);
                });
            });   
        });
    }
    function postSetup() {        
        
        module.factory('ResourceDescriptor', function(Cataloguing2Model, ServicesRestangular) {
            this.name = "resourceDescriptor";
            var resourceDescriptorModel =  angular.extend(this, Cataloguing2Model, {
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
                            "field" : "resourceDescriptorId",
                            "displayName" : this.getMessage('resourceDescriptorId'),
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
            return resourceDescriptorModel;
        });
        
    }
    setupDefaultEntityAndRepository(cataloguing2Entities);
    setupDefaultEntityAndRepository(['resourceDescriptor']);
    postSetup();
});
