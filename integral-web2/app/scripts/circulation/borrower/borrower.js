'use strict';

/* App Module */
define(['angular','lodash','jquery', 'circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.config(function($stateProvider) {
        $stateProvider.state('circulation.borrower', {
			url : '/borrower',
            templateUrl: 'views/circulation/borrower/borrower.html',
            controller: 'BorrowerIndexCtrl'
		}).state('circulation.borrower.register', {
			url : '/register?username',
            templateUrl: 'views/circulation/borrower/register.html',
            controller: 'BorrowerRegisterCtrl',
            resolve : {
                patronCategories: function(PatronCategory) {
                   //return [{"code" : "Student"},{"code": "Lecturer"}];
                   return PatronCategory.getRepository().getList();
                }
            }
		});
    });
});