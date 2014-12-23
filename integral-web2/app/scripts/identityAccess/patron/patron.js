'use strict';

/* App Module */
define(['angular','lodash','jquery', '../identityAccessModule','./patronController'], function (angular, _, $) {
    var module = angular.module('integral.identityAccess');
    module.config(function($stateProvider) {
        $stateProvider.state('identityAccess.patron', {
			url : '/patron',
            templateUrl: 'views/identityAccess/patron/patron.html',
            controller: 'PatronIndexCtrl'
		}).state('identityAccess.patron.register', {
			url : '/register',
            templateUrl: 'views/identityAccess/patron/register.html',
            controller: 'PatronRegisterCtrl'
		});
    });
});