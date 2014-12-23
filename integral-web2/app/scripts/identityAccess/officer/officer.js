'use strict';

/* App Module */
define(['angular','lodash','jquery', '../identityAccessModule','./officerController'], function (angular, _, $) {
    var module = angular.module('integral.identityAccess');
    module.config(function($stateProvider) {
        $stateProvider.state('identityAccess.officer', {
			url : '/officer',
            templateUrl: 'views/identityAccess/officer/officer.html',
            controller: 'OfficerIndexCtrl'
		}).state('identityAccess.officer.register', {
			url : '/register',
            templateUrl: 'views/identityAccess/officer/register.html',
            controller: 'OfficerRegisterCtrl'
		});
    });
});