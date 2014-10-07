'use strict';

/* App Module */
define(['app','lodash','jquery'], function (integralApp, _, $) {
    integralApp.config(function($stateProvider) {
        $stateProvider.state('eligibility', {
            abstract: true,
			url : '/eligibility',
            templateUrl: 'views/eligibility/index.html'
		}).state('eligibility.itemEligibility', {
			url : '/item',
            templateUrl: 'views/eligibility/item/index.html',
            controller: 'ItemEligibilityIndexCtrl'
		}).state('eligibility.itemEligibility.create', {
			url : '/create',
            templateUrl: 'views/eligibility/item/form.html',
            controller: 'ItemEligibilityCreateCtrl'
		}).state('eligibility.itemEligibility.edit', {
			url : '/{id:[0-9]{1,8}}/edit',
            templateUrl: 'views/eligibility/item/form.html',
            controller: 'ItemEligibilityEditCtrl'
		});
	});
});