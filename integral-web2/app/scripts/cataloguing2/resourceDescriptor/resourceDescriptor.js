'use strict';

/* App Module */

define(['lodash','jquery','cataloguing2/cataloguing2'], function (_, $) {
    var module = angular.module('integral.cataloguing2');
    module.config(function($stateProvider) {
        var stateBaseName = 'cataloguing2.resourceDescriptor';
        var viewsBaseName = 'views/cataloguing2/resourceDescriptor'; 
        $stateProvider.state(stateBaseName, {
            abstract: true,
            url : '/resourcedescriptor',
            templateUrl : 'views/cataloguing2/repository.html',
            controller : 'RepositoryEntityCtrl',
            resolve:{
                entityDomainClass:  function($injector){
                    return $injector.get('resourceDescriptor'.capitalize());
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
        }).state(stateBaseName + '.create', {
            url : '/create',
            templateUrl : viewsBaseName + '/create.html',
            controller : 'ResourceDescriptorCreateCtrl',
            resolve: {
                Templates: function(MarcService2) {
                    return MarcService2.getTemplates();
                },
                Schema: function(MarcService2) {
                    return MarcService2.getSchema("marc21", "marc21")
                }
            }
        }).state(stateBaseName + '.edit', {
            url : '/{id:[0-9]{1,8}}/edit',
            templateUrl : viewsBaseName + '/edit.html',
            controller : 'ResourceDescriptorEditCtrl',
            resolve: {
                record: function(MarcService2, $stateParams) {
                    var recordId = $stateParams.id;
                    return MarcService2.find(recordId);
                },
                Marc21Form: function(MarcService2) {
                    return MarcService2.getForm('marc21','marc21');
                }
            }
        }).state(stateBaseName + '.finalize', {
            url : '/{id:[0-9]{1,8}}/edit',
            templateUrl : viewsBaseName + '/edit.html',
            controller : 'ResourceDescriptorEditCtrl',
            resolve: {
                record: function(MarcService2, $stateParams) {
                    var recordId = $stateParams.id;
                    return MarcService2.find(recordId);
                },
                Marc21Form: function(MarcService2) {
                    return MarcService2.getForm('marc21','marc21');
                }
            }
        }); 
    });
});
