'use strict';

/* App Module */
define([ 'app' ], function(integralApp) {
	integralApp.config(function($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise('/html/error/404');
	});
});