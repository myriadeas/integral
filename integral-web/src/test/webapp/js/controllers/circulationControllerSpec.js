define(['app','angular','angularMocks', 'angularUiRouter', 'controllers/circulationController', 'angularUiBoostrapTpls', 'restangular', 'underscore', 'localization', 'ngGrid', 'services/services'], function () {
	describe('CheckinCtrl Tests', function () {
        var module, rootScope, controller, stateParams, log, location;
        
        beforeEach(angular.mock.module('integralweb'));
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
			var checkinCtrl = controller("CheckinCtrl", {
				$scope: scope,
				DatetimeUtil: DatetimeUtil
			});
			actualTransactionId = scope.getNewTransactionId();
			expect(actualTransactionId).not.toBeUndefined();
			expect(actualTransactionId).toBe("_CI20140101");
        });
		
		it("should be new transaction", function () {
			var actualTransactionId;
			var scope= rootScope.$new();
			var stateParams = {};
			var checkinCtrl = controller("CheckinCtrl", {
				$scope: scope,
				$stateParams: stateParams
			});
			expect(scope.isNewTransaction()).toBe(true);
			
			stateParams = {transactionId : "_CI20140101"};
			checkinCtrl = controller("CheckinCtrl", {
				$scope: scope,
				$stateParams: stateParams
			});
			expect(scope.isNewTransaction()).toBe(false);
        });
		
		it("should got to circulation/checkin with new transactionId", function () {
			var actualTransactionId;
			var scope= rootScope.$new();
			var stateParams = {};
			var checkinCtrl = controller("CheckinCtrl", {
				$scope: scope,
				$stateParams: stateParams,
				$location: location
			});
			expect(location.path()).toBe('/circulation/checkin');
			expect(location.search().transactionId).not.toBeUndefined();
        });
	});
	
	describe('Test CheckinCtrl for Checkin', function () {
        var module, rootScope, controller, stateParams, log, location, checkinCtrl, scope, mockServicesRestangular;
        beforeEach(angular.mock.module('integralweb', function($provide){
			$provide.value('$log', console);
			mockServicesRestangular = {
					one : function() {
						return this;
					},
					post : function() {
						return this;
					},
					all : function(url) {
						return this;
					}, 
					then : function(){
					}
				};
				$provide.value('ServicesRestangular', mockServicesRestangular);
		}));
        beforeEach(inject(function ($rootScope, $controller, $stateParams, $location) {
            rootScope = $rootScope;
            controller = $controller;
			stateParams = $stateParams;
			location = $location;
			scope= rootScope.$new();
			checkinCtrl = controller("CheckinCtrl", {
				$scope: scope
			});
		}));
			

		
		it("should clear the default message before checkin", function(){
			scope.checkin();
			expect(scope.defaultMessage.length).toBeUndefined();
		});
    });
	
});
