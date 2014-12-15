'use strict';

/* App Module */

define(['lodash','jquery','schema-form','bootstrap-decorator','angular-history','utility/inflection','global/global', 'repository/crudRepository', 'repository/crudControllers'], function (_, $) {
    var module = angular.module('integral.assetManager', ['integral.global', 'integral.repository', 
	  'ngGrid',
	  'ui.bootstrap',
      'schemaForm']);
    var assetManagerEntities = ["item"];
    var moduleName = 'assetManager';
    module.config(function($stateProvider) {
        $stateProvider.state('assetManager', {
            abstract: true,
            url : '/assetManager',
            templateUrl : 'views/assetManager/assetManager.html',
            controller : 'AssetManagerIndexController'
        }).state('assetManager.home', {
            url : '/home',
            templateUrl : 'views/assetManager/assetManagerHome.html',
            controller : 'AssetManagerHomeController'
        }).state('assetManager.transfer', {
            url : '/transfer?transactionId',
            templateUrl : 'views/assetManager/item/transfer.html',
            controller : 'TransferController'
        }).state('assetManager.receive', {
            url : '/receive?transactionId',
            templateUrl : 'views/assetManager/item/receive.html',
            controller : 'ReceiveController'
        });
        
        function setupMaintenanceEntitiesState(moduleName, entities) {
            $.each(entities, function(key, entity) {
                var stateBaseName = moduleName + '.' + entity;
                var viewsBaseName = 'views/' + moduleName + '/' + entity; 
                $stateProvider.state(stateBaseName, {
                    abstract: true,
                    url : '/' + entity,
                    templateUrl : 'views/assetManager/repository.html',
                    controller : 'RepositoryEntityCtrl',
                    resolve:{
                        entityDomainClass:  function($injector){
                            return $injector.get(entity.capitalize());
                        }
                    }
                }).state(stateBaseName + '.search', {
                    url : '/search',
                    templateUrl : 'views/assetManager/search.html',
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
                    templateUrl : viewsBaseName + '/create.html',
                    controller : 'CreateController'
                });
            });
        }
        
        setupMaintenanceEntitiesState(moduleName, assetManagerEntities);
    });
    
    
    module.factory('AssetManagerModel', function(AuditableModel, Localization) {
        return angular.extend(this,AuditableModel, {
            getUri: function() {
                return moduleName + '/' + this.name.toLowerCase();
            }
        });
    });
	
	
	module.factory('DeleteItemService', function(ServicesRestangular, Restangular, $log) {
		return {
			delete: function(deleteRequest) {
				$log.log("Entering delete service. Parameter passed: ", "delete request: ", deleteRequest);
				var response = ServicesRestangular.all('assetmanager/delete').post(createRequest, {selector:"*,item(itemIdentifier, title)"});
				$log.log("Leaving delete service");
				return response;
			}
		};
	});
		
/*	module.factory('FindItemsForTransferService', function(ServicesRestangular, Restangular, $log, ItemStatus) {
		return {
			getItemsForTransfer: function() {
				$log.log("Entering get items for transfer service.");
				var itemStatus = ItemStatus.New;
				var items = ServicesRestangular.one('items/search/findByItemStatus').get({search : itemStatus});
				$log.log("Leaving get items for transfer service");
				return items;
			}
		};
	});
    	
*/	
    function setupDefaultEntityAndRepository(entities) {
        $.each(entities, function(key, entity) {
            var entityFactoryName = entity.capitalize();
            module.factory(entityFactoryName, function(AssetManagerModel) {
                this.name = entity;
                return angular.extend(this, AssetManagerModel);
            });     
            module.factory(entityFactoryName + "Repository", function(CrudRepository) {
                return CrudRepository.withConfig(function(Configurer){
                    Configurer.setEntityName(entity);
                });
            });   
        });
    }
    function postSetup() {        
        module.factory('Item', function(AssetManagerModel, ServicesRestangular) {
            this.name = "item";
            var itemModel =  angular.extend(this, AssetManagerModel, {
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
                    ]
                },
            });
            return itemModel;
        });
		
		module.factory('ItemStatus', function(AssetManagerModel, ServicesRestangular) {
            this.name = "itemStatus";
            var itemStatusModel =  angular.extend(this, AssetManagerModel, {
               
            });
            return itemStatusModel;
        });
                
    }
    setupDefaultEntityAndRepository(assetManagerEntities);
    postSetup();
});
