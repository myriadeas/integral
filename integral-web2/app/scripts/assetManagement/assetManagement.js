'use strict';

/* App Module */

define(['lodash','jquery','schema-form','bootstrap-decorator','angular-history','utility/inflection','global/global', 'repository/crudRepository', 'repository/crudControllers'], function (_, $) {
    var module = angular.module('integral.assetManagement', ['integral.global', 'integral.repository', 
	  'ngGrid',
	  'ui.bootstrap',
      'schemaForm']);
    var assetManagementEntities = ["item"];
    var moduleName = 'assetManagement';
    module.config(function($stateProvider) {
        $stateProvider.state('assetManagement', {
            abstract: true,
            url : '/assetManagement',
            templateUrl : 'views/assetManagement/assetManagement.html',
            controller : 'AssetManagementIndexController'
        }).state('assetManagement.home', {
            url : '/home',
            templateUrl : 'views/assetManagement/assetManagementHome.html',
            controller : 'AssetManagementHomeController'
        }).state('assetManagement.transfer', {
            url : '/transfer?transactionId',
            templateUrl : 'views/assetManagement/item/transfer.html',
            controller : 'TransferController'
        }).state('assetManagement.receive', {
            url : '/receive?transactionId',
            templateUrl : 'views/assetManagement/item/receive.html',
            controller : 'ReceiveController'
        });
        
        function setupMaintenanceEntitiesState(moduleName, entities) {
            $.each(entities, function(key, entity) {
                var stateBaseName = moduleName + '.' + entity;
                var viewsBaseName = 'views/' + moduleName + '/' + entity; 
                $stateProvider.state(stateBaseName, {
                    abstract: true,
                    url : '/' + entity,
                    templateUrl : 'views/assetManagement/repository.html',
                    controller : 'RepositoryEntityCtrl',
                    resolve:{
                        entityDomainClass:  function($injector){
                            return $injector.get(entity.capitalize());
                        }
                    }
                }).state(stateBaseName + '.search', {
                    url : '/search',
                    templateUrl : 'views/assetManagement/search.html',
                    controller : 'ItemSearchCtrl'
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
        
        setupMaintenanceEntitiesState(moduleName, assetManagementEntities);
    });
    
    
    module.factory('AssetManagementModel', function(AuditableModel, Localization) {
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
				var response = ServicesRestangular.all('assetmanagement/delete').post(createRequest, {selector:"*,item(itemIdentifier, title)"});
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
            module.factory(entityFactoryName, function(AssetManagementModel) {
                this.name = entity;
                return angular.extend(this, AssetManagementModel);
            });     
            module.factory(entityFactoryName + "Repository", function(CrudRepository) {
                return CrudRepository.withConfig(function(Configurer){
                    Configurer.setEntityName(entity);
                });
            });   
        });
    }
    function postSetup() {        
        module.factory('Item', function(AssetManagementModel, ServicesRestangular) {
            this.name = "item";
            var itemModel =  angular.extend(this, AssetManagementModel, {
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
		
		module.factory('ItemStatus', function(AssetManagementModel, ServicesRestangular) {
            this.name = "itemStatus";
            var itemStatusModel =  angular.extend(this, AssetManagementModel, {
               
            });
            return itemStatusModel;
        });
		
		module.factory('ItemDisplay', function(AssetManagementModel, ServicesRestangular) {
            this.name = "itemDisplay";
            var itemDisplayModel =  angular.extend(this, AssetManagementModel, {
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
                            "field" : "itemIdentifier",
                            "displayName" : this.getMessage('itemIdentifier'),
                            "width" : 130
                        },
                        {
                            "field" : "resourceDescriptorIdentifier",
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
                        }
                        ]
                }
            });
            return itemDisplayModel;
        });
        
    }
                
    
    setupDefaultEntityAndRepository(assetManagementEntities);
    setupDefaultEntityAndRepository(['itemDisplay']);
    postSetup();
});
