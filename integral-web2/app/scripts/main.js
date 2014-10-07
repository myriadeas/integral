require.config({
	baseUrl: 'scripts',
	shim: {
		angular: {
			exports: 'angular'
		},
		'jquery-ui': {
			exports: 'jquery-ui',
			deps: [
				'jquery'
			]
		},
		fullcalendar: {
			exports: 'fullcalendar',
			deps: [
				'jquery'
			]
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
		'angular-ui-calendar': {
			deps: [
				'fullcalendar',
				'jquery-ui',
				'angular'
			],
			exports: 'angular-ui-calendar'
		},
		gcal: {
			deps: [
				'fullcalendar'
			],
			exports: 'gcal'
		},
        marc4js: {
            deps: [
                'tv4'
            ],
            exports: 'marc4j'
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
		jquery: '../bower_components/jquery/dist/jquery',
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
		'bootstrap-sass-official': '../bower_components/bootstrap-sass-official/vendor/assets/javascripts/bootstrap',
		'sass-bootstrap': '../bower_components/sass-bootstrap/dist/js/bootstrap',
		'angular-ui-calendar': '../bower_components/angular-ui-calendar/src/calendar',
		'jquery-ui': '../bower_components/jquery-ui/ui/jquery-ui',
		'bootstrap-css': '../bower_components/bootstrap-css/js/bootstrap',
		fullcalendar: '../bower_components/fullcalendar/fullcalendar',
		gcal: 'circulation/calendar/gcal',
		tv4: 'marc4js/tv4',
		pickadate: '../bower_components/pickadate/lib/picker',
		'schema-form': '../bower_components/angular-schema-form/dist/schema-form.min',
		'bootstrap-decorator': 'directives/bootstrap-decorator',
		'angular-history': '../bower_components/angular-history/history',
        'marc4js': 'marc4js/marc4js',
        'marcDirective':'marc4js/marcDirective'
	}
});

var dependencies = [ 'angular', 'angular-deferred-bootstrap','jquery', 'angular-ui-router','angular-route','angular-sanitize','lodash',
		'angular-bootstrap', 'ng-grid', 'toastr', 'angular-flash', 'angular-promise-tracker', 'angular-intro','angular-dialog-service',
        'i18n/localize','checklist-model','restangular', 'fullcalendar', 'angular-ui-calendar', 'jquery-ui','schema-form','angular-history',
        'marc4js','tv4','marcDirective',
        'foundation/foundation', 'foundation/foundationController',
        'app', 'states', 'services/navigationServices', 'services/services',
		'directives/directives', 'directives/modalDirective', 'directives/circulation/transactionLogDirective',
		'template/templateController',
		'controllers/searchControllers', 'controllers/circulationController',
		'controllers/fileUploadControllers',
        'global/global',
        'cataloguing/cataloguing','cataloguing/cataloguingController','cataloguing/material/material','cataloguing/material/materialController',
        'cataloguing/material/materialService', 'cataloguing/release/releaseController','cataloguing/release/releaseService',
        'circulation/circulation','circulation/circulationController',
        'circulation/itemEligibility/itemEligibilityController','circulation/itemEligibility/itemEligibility',
        'circulation/patronEligibility/patronEligibilityController','circulation/patronEligibility/patronEligibility',
        'circulation/patronItemEligibility/patronItemEligibility', 'circulation/patronItemEligibility/patronItemEligibilityController',
        'circulation/calendar/calendarController', 'circulation/calendar/calendar', 'gcal', 'circulation/calendar/timeClassController',
        'foundation/userGroup/userGroup','foundation/userGroup/userGroupController','foundation/role/role','foundation/role/roleController',
        'foundation/officer/officer','foundation/officer/officerController',
        'repository/crudRepository', 'repository/crudControllers', 
        'directives/form/form','directives/search/search','directives/circulation/circulation',
        'directives/catalogSearch/catalogSearch',
        'utility/inflection'];
		
var dev = false;
var envPrefix = '';
if(dev) {
	envPrefix = 'mock';
}


require(dependencies, function(angular) {
	//angular.bootstrap(document, [ 'integralweb' ]);
    //https://github.com/philippd/angular-deferred-bootstrap
    deferredBootstrapper.bootstrap({
        element: document.body, 
        module: 'integralweb', 
        resolve: {
            TOKEN: function ($http) {
                return $http.get('token/token.jsp');
            },
            LOCALIZATION_DATA: function($http, $q) {
                var deferred = $q.defer();
                $http.get('token/token.jsp').then(function(response){
                    var url = response.data.serviceUrl + '/services/i18n/resources-locale_en-Us';
                    $http.get(url).then(function(response) {
                        deferred.resolve(response);
                    });
                },function(response) {
					location.reload();
				});
                return deferred.promise;
            }
        }
    });
});
