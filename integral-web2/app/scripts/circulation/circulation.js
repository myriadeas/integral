'use strict';

/* App Module */

define(['lodash','jquery','utility/inflection','global/global', 'repository/crudRepository', 'repository/crudControllers', 'angular-ui-calendar'], function (_, $) {
    var module = angular.module('integral.circulation', ['integral.global', 'integral.repository', 'ui.calendar']);
    //var circulationEntities = ["SMD", "itemCategory", "item", "condition", "material", "itemStatus", "holiday", "branch", "workingDay"];
    var circulationEntities = ["itemStatus", "holiday", "branch", "workingDay"];
    var moduleName = 'circulation';
    module.config(function($stateProvider) {
        $stateProvider.state('circulation', {
            abstract: true,
            url : '/circulation',
            templateUrl : 'views/circulation/circulation.html',
            controller : 'CirculationIndexController'
        }).state('circulation.home', {
            url : '/home',
            templateUrl : 'views/circulation/circulationHome.html',
            controller : 'CirculationHomeController'
        }).state('circulation.checkout', {
            url : '/checkout?transactionId&patronId',
            templateUrl : 'views/circulation/checkout/checkout.html',
            controller : 'CheckoutController'
        }).state('circulation.checkin', {
            url : '/checkin?transactionId',
            templateUrl : 'views/circulation/checkin/checkin.html',
            controller : 'CheckinController'
        }).state('circulation.renew', {
            url : '/renew?transactionId',
            templateUrl : 'views/circulation/renew/renew.html',
            controller : 'RenewController'
        });
        
        
        function setupMaintenanceEntitiesState(moduleName, entities) {
            $.each(entities, function(key, entity) {
                var stateBaseName = moduleName + '.' + entity;
                var viewsBaseName = 'views/' + moduleName + '/' + entity; 
                $stateProvider.state(stateBaseName, {
                    abstract: true,
                    url : '/' + entity,
                    templateUrl : 'views/circulation/repository.html',
                    controller : 'RepositoryEntityCtrl',
                    resolve:{
                        entityDomainClass:  function($injector){
                            return $injector.get(entity.capitalize());
                        }
                    }
                }).state(stateBaseName + '.search', {
                    url : '/search',
                    templateUrl : 'views/circulation/search.html',
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
        
        setupMaintenanceEntitiesState(moduleName, circulationEntities);
    });
    
    
    module.factory('CirculationModel', function(AuditableModel, Localization) {
        return angular.extend(this,AuditableModel, {
            getUri: function() {
                return moduleName + '/' + this.name.toLowerCase();
            }
        });
    });
    
    
    module.factory('EligibilityModel', function(CirculationModel, Localization, $state) {
        return angular.extend(this, CirculationModel, {
            getViewLink: function() {
                return this.getUri() + '/' + this.id + '/edit';
            }, getSearchLink: function() {
                return this.getUri();
            },
            getSearchColumnDefinition: function() {                
                return [
                    {field:'weight', displayName: this.getMessage('weight')},
                    //{field:'code', displayName: this.getMessage('code')}, 
                    //{field:'description', displayName: this.getMessage('description')}, 
                    //{field:'criteria', displayName: this.getMessage('criteria')}
                ]
            },
            getListAllGridOptions: function() {
                var EligibilityModel = this;
                return {
                    data: EligibilityModel.name.pluralize(),
                    enablePinning: true,
                    multiSelect: false,
                    showColumnMenu: true,
                    enableColumnResize: true,
                    afterSelectionChange: function(rowItem, event) {
                        $state.go("circulation." + EligibilityModel.name + ".edit", {id: rowItem.entity.id})
                    },
                    columnDefs: this.getListAllColumnDefinition(),
                    sortInfo: {fields:['weight'], directions:['desc']},
                    rowTemplate:'<div style="height: 100%" ng-class="{\'bg-primary\': row.getProperty(\'isMatched\')}"><div ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell ">' +
                                   '<div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }"> </div>' +
                                   '<div ng-cell></div>' +
                             '</div></div>'
                };
            }
        });
    });

    
    function setupDefaultEntityAndRepository(entities) {
        $.each(entities, function(key, entity) {
            var entityFactoryName = entity.capitalize();
            module.factory(entityFactoryName, function(CirculationModel) {
                this.name = entity;
                return angular.extend(this, CirculationModel);
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
                getPatronItemEligibility: function(username) {
                    return ServicesRestangular.all('circulation/item/' + this.itemIdentifier + '/patronitemeligibility?username=' + username + '&selector=*').customGET();
                },
            });
            return itemModel;
        });
        
    }
    setupDefaultEntityAndRepository(circulationEntities);
    setupDefaultEntityAndRepository(['itemEligibility', 'patronEligibility', 'patronItemEligibility']);
    postSetup();
    
    module.factory('CategorySelectionModelFactory', function() {
        var CategorySelectionModel = function(categories, selections) {
            this.categories = categories;
            this.selections = selections;
            this.getSelectionValue = function() {
                if(angular.isUndefined(categories) ) {
                    return "";
                }
                var selectionValue = "";
                if(_.size(selections) == _.size(this.categories) && _.size(this.selections) > 0) {
                    selectionValue = "-" + this.selections[0].criteriaCode + " ANY";
                } else {
                    $.each(this.categories, function(key, category) {
                        if(selectionValue.length > 0) {
                            selectionValue += " ";
                        }                       
                        selectionValue += category.criteriaValue;
                    });
                }
                return selectionValue;
            }
        }
        var categorySelectionModelFactory = {
            newCategorySelectionModel: function(categories, selections) {
                return new CategorySelectionModel(categories, selections);
            }
        }
        return categorySelectionModelFactory;
    });
    
    module.factory('CirculationService', function(ServicesRestangular){
        return {
            reloadPolicy: function() {
                return ServicesRestangular.all('circulation/policy/reload').post();
            }
        }
    });    
});
