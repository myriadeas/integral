define(['app','angular','angular-mocks','angular-bootstrap','i18n/localize', 'ng-grid','angular-ui-router','angular-route', 'angular-flash', 'checklist-model','angular-dialog-service', 'angular-sanitize','restangular','global/global'], function (app, angular, mocks) {
    describe('App module tests', function () {
        var module, rootScope, controller, stateParams, log, location;
        var CheckinService, mockServicesRestangular;
        beforeEach(mocks.module('integral.global'));
        beforeEach(inject(function ($rootScope) {
            rootScope = $rootScope;
        }));
        it("test global", function () {
            expect(rootScope).not.toBeNull();
        });
    });
});