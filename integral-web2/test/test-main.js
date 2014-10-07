var tests = [];
for (var file in window.__karma__.files) {
    if (/Spec\.js$/.test(file)) {
        tests.push(file);
    }
}

require.config({
    baseUrl:'/base/app/scripts',
    shim: {
		angular: {
			exports: 'angular'
		},
		'angular-ui-router': {
			exports: 'angular-ui-router',
			deps: [
				'angular'
			]
		},
		'ng-grid': {
			deps: [
				'jquery'
			],
			exports: 'ng-grid'
		},
		'angular-intro': {
			deps: [
				'jquery',
				'introJs'
			],
			exports: 'angular-intro'
		},
		'angular-sanitize': {
			deps: [
				'jquery',
				'angular'
			],
			exports: 'angular-sanitize'
		},
		'angular-deferred-bootstrap': {
			deps: [
				'jquery',
				'angular'
			],
			exports: 'angular-deferred-bootstrap'
		},
		'angular-mocks': {
			deps:['angular'],
			'exports':'angular.mock'
		}
	},
	paths: {
		json3: '../bower_components/json3/lib/json3.min',
		'es5-shim': '../bower_components/es5-shim/es5-shim',
		'angular-scenario': '../bower_components/angular-scenario/angular-scenario',
		'angular-sanitize': '../bower_components/angular-sanitize/angular-sanitize',
		'angular-route': '../bower_components/angular-route/angular-route',
		'angular-resource': '../bower_components/angular-resource/angular-resource',
		'angular-mocks': '../bower_components/angular-mocks/angular-mocks',
		'angular-cookies': '../bower_components/angular-cookies/angular-cookies',
		angular: '../bower_components/angular/angular',
		jquery: '../bower_components/jquery/jquery',
		restangular: '../bower_components/restangular/dist/restangular',
		'ng-grid': '../bower_components/ng-grid/build/ng-grid',
		'angular-ui-utils': '../bower_components/angular-ui-utils/ui-utils',
		'angular-ui-router': '../bower_components/angular-ui-router/release/angular-ui-router',
		'angular-bootstrap': '../bower_components/angular-bootstrap/ui-bootstrap-tpls',
		toastr: '../bower_components/toastr/toastr',
		requirejs: '../bower_components/requirejs/require',
		'angular-loader': '../bower_components/angular-loader/angular-loader',
		'angular-flash': '../bower_components/angular-flash/dist/angular-flash',
		'angular-promise-tracker': '../bower_components/angular-promise-tracker/promise-tracker',
		'angular-intro': '../bower_components/angular-intro.js/src/angular-intro',
		introJs: '../bower_components/intro.js/intro',
		'angular-dialog-service': '../bower_components/angular-dialog-service/dialogs',
		lodash: '../bower_components/lodash/dist/lodash.compat',
		affix: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/affix',
		alert: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/alert',
		button: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/button',
		carousel: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/carousel',
		collapse: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/collapse',
		dropdown: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/dropdown',
		tab: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/tab',
		transition: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/transition',
		scrollspy: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/scrollspy',
		modal: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/modal',
		tooltip: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/tooltip',
		popover: '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap/popover',
		'angular-deferred-bootstrap': '../bower_components/angular-deferred-bootstrap/angular-deferred-bootstrap',
		'checklist-model': '../bower_components/checklist-model/checklist-model',
		'bootstrap-sass-official': '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap'
	},
    // ask Require.js to load these files (all our tests)
    deps: tests,

    // start test run, once Require.js is done
    callback: window.__karma__.start
});
