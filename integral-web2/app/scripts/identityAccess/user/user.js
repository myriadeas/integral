'use strict';

/* App Module */
define(['angular','lodash','jquery', '../identityAccessModule','./userController'], function (angular, _, $) {
    var module = angular.module('integral.identityAccess');
    module.config(function($stateProvider) {
        $stateProvider.state('identityAccess.user', {
			url : '/user',
            templateUrl: 'views/identityAccess/user/user.html',
            controller: 'UserIndexCtrl'
		}).state('identityAccess.user.register', {
			url : '/register',
            templateUrl: 'views/identityAccess/user/register.html',
            controller: 'UserRegisterCtrl'
		});
    });
});