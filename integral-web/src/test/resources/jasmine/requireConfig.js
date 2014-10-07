baseUrl: '/src/main/webapp/js',
paths : {
    angular : '../lib/angular/angular',
    angularUiRouter : '../lib/angular/angular-ui-router',
    jquery : '../lib/jquery/jquery',
    toastr : '../lib/toastr/toastr',
    angularResource : '../lib/angular/angular-resource',
    angularUiBoostrapTpls : '../lib/angular/ui-bootstrap-tpls-0.5.0',
    underscore : '../lib/restangular/underscore-min',
    restangular : '../lib/restangular/restangular',
    ngGrid : '../lib/ng-grid/js/ng-grid.debug',
    ngTable : '../lib/ng-table/js/ng-table',
    localization : '../lib/i18n/localize',
    angularMocks: '../lib/angular/angular-mocks',
},
shim : {
    angular : {
        exports : 'angular'
    },
    angularUiRouter : {
        exports : 'angularUiRouter',
        deps: ['angular']
    },
    ngGrid : {
        deps : [ 'jquery' ],
        exports : 'ngGrid'
    }
}