'use strict';

/* App Module */

define(['lodash','jquery','utility/inflection','global/global', 'repository/crudRepository', 'repository/crudControllers', 
    'angular-ui-calendar','./identityAccessModule','./identityAccessController'], function (_, $) {
    var module = angular.module('integral.identityAccess');
    module.config(function($stateProvider) {
        $stateProvider.state('identityAccess', {
            abstract: true,
            url : '/identityaccess',
            templateUrl : 'views/identityAccess/identityAccess.html',
            controller : 'IdentityAccessIndexController'
        }).state('identityAccess.home', {
            url : '/home',
            templateUrl : 'views/identityAccess/identityAccessHome.html',
            controller : 'IdentityAccessHomeController'
        })
   });
});
