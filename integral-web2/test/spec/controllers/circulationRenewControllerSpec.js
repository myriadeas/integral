define(['app','angular','angular-mocks', 'angular-ui-router', 'controllers/circulationController', 'circulation/checkin/controller', 'circulation/checkout/controller', 'circulation/renew/controller', 
'angular-bootstrap', 'restangular', 'underscore', 'i18n/localize', 'ng-grid', 'services/services', 'circulation/checkin/service', 'circulation/checkout/service', 'circulation/renew/service',
'angular-flash','angular-route'], function (app) {
	describe('Test RenewCtrl.', function () {
        var module, rootScope, controller, stateParams, log, location;
        beforeEach(angular.mock.module('integralweb'));
        beforeEach(function(){
            console.log("before each");
        });
        beforeEach(inject(function () {
            console.log("beforeEach");
        }));
        
		beforeEach(inject(function ($rootScope, $controller, $stateParams, $location) {
            rootScope = $rootScope;
            controller = $controller;
			stateParams = $stateParams;
			log = console;
			location = $location;
        }));
		
		
		it("should get new transaction id", function () {
			var actualTransactionId;
			var scope= rootScope.$new();
			var DatetimeUtil = {
				now : function() {
					return "20140101";
				}
			};
			var RenewCtrl = controller("RenewCtrl", {
				$scope: scope,
				DatetimeUtil: DatetimeUtil
			});
			actualTransactionId = scope.getNewTransactionId();
			expect(actualTransactionId).not.toBeUndefined();
			expect(actualTransactionId).toBe("_RN20140101");
        });
        
       
        
        it("should be new transaction", function () {
			var actualTransactionId;
			var scope= rootScope.$new();
			var stateParams = {};
			var renewCtrl = controller("RenewCtrl", {
				$scope: scope,
				$stateParams: stateParams
			});
			expect(scope.isNewTransaction()).toBe(true);
			
			stateParams = {transactionId : "_RN20140101"};
			renewCtrl = controller("RenewCtrl", {
				$scope: scope,
				$stateParams: stateParams
			});
			expect(scope.isNewTransaction()).toBe(false);
        });
		
		it("should got to circulation/renew with new transactionId", function () {
			var actualTransactionId;
			var scope= rootScope.$new();
			var stateParams = {};
			var renewCtrl = controller("RenewCtrl", {
				$scope: scope,
				$stateParams: stateParams,
				$location: location
			});
			expect(location.path()).toBe('/circulation/renew');
			expect(location.search().transactionId).not.toBeUndefined();
        });
	});
	
	
});
