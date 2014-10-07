'use strict';

/* App Module */

define(['lodash','jquery','cataloguing/cataloguing'], function (_, $) {
    var module = angular.module('integral.cataloguing');
    module.config(function($stateProvider) {
        var stateBaseName = 'cataloguing.material';
        var viewsBaseName = 'views/cataloguing/material'; 
        $stateProvider.state(stateBaseName, {
            abstract: true,
            url : '/material',
            templateUrl : 'views/cataloguing/repository.html',
            controller : 'RepositoryEntityCtrl',
            resolve:{
                entityDomainClass:  function($injector){
                    return $injector.get('material'.capitalize());
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
        }).state(stateBaseName + '.create', {
            url : '/create',
            templateUrl : viewsBaseName + '/create.html',
            controller : 'MaterialCreateCtrl',
            resolve: {
                Templates: function(MarcService) {
                    return MarcService.getTemplates();
                },
                Schema: function(MarcService) {
                    return MarcService.getSchema("marc21", "marc21")
                }
            }
        }).state(stateBaseName + '.edit', {
            url : '/{id:[0-9]{1,8}}/edit',
            templateUrl : viewsBaseName + '/edit.html',
            controller : 'MaterialEditCtrl',
            resolve: {
                record: function(MarcService, $stateParams) {
                    var recordId = $stateParams.id;
                    return MarcService.find(recordId);
                },
                Marc21Form: function(MarcService) {
                    return MarcService.getForm('marc21','marc21');
                }
            }
        }); 
    });
});
