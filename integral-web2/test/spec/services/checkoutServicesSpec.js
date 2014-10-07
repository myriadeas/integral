define(['app','angular','angular-mocks', 'angular-ui-router', 'controllers/circulationController', 'circulation/checkin/controller', 'circulation/checkout/controller', 'circulation/renew/controller', 
'angular-bootstrap', 'restangular', 'underscore', 'i18n/localize', 'ng-grid', 'services/services', 'circulation/checkin/service', 'circulation/checkout/service', 'circulation/renew/service',
'angular-flash','angular-route'], function (app) {
	describe('Test checkout services.', function() {
		describe ('Test validate Patron.', function(){
			var module, rootScope, controller, stateParams, log, location;
			var CheckoutService, mockServicesRestangular;
			beforeEach(angular.mock.module('integralweb', function($provide){
				mockServicesRestangular = {
					one : function() {
						return this;
					},
					get : function() {
						return this;
					},
					all : function(url) {
						return this;
					}
				};
				$provide.value('ServicesRestangular', mockServicesRestangular);
				$provide.value('$log', console);
			}));
			
			beforeEach(inject(function($rootScope, $controller, $stateParams,
					$location, $injector, $log) {
				rootScope = $rootScope;
				controller = $controller;
				stateParams = $stateParams;
				log = console;
				location = $location;
				CheckoutService = $injector.get('CheckoutService');
			}));

			it("should be circulation/checkout/validate/patron for the service url and get has been called", function() {
				spyOn(mockServicesRestangular, 'get').andCallThrough();
				spyOn(mockServicesRestangular, 'one').andCallThrough();
				var patronId = "SYSADMIN";
				CheckoutService.validatePatron(patronId);
				expect(mockServicesRestangular.get).toHaveBeenCalled();
				expect(mockServicesRestangular.one).toHaveBeenCalledWith('circulation/checkout/validate/patron');
			})
		});
		
		describe ('Test get patron detail.', function(){
			var module, rootScope, controller, stateParams, log, location;
			var CheckoutService, mockServicesRestangular;
			beforeEach(angular.mock.module('integralweb', function($provide){
				mockServicesRestangular = {
					one : function() {
						return this;
					},
					get : function() {
						return this;
					},
					all : function(url) {
						return this;
					}
				};
				$provide.value('ServicesRestangular', mockServicesRestangular);
				$provide.value('$log', console);
			}));
			
			beforeEach(inject(function($rootScope, $controller, $stateParams,
					$location, $injector, $log) {
				rootScope = $rootScope;
				controller = $controller;
				stateParams = $stateParams;
				log = console;
				location = $location;
				CheckoutService = $injector.get('CheckoutService');
			}));

			it("should be circulation/checkout for the service url and post has been called", function() {
				spyOn(mockServicesRestangular, 'get').andCallThrough();
				spyOn(mockServicesRestangular, 'one').andCallThrough();
				var patronId = "SYSADMIN";
				CheckoutService.getPatronDetail(patronId);
				expect(mockServicesRestangular.get).toHaveBeenCalled();
				expect(mockServicesRestangular.one).toHaveBeenCalledWith('glpatr/search/findByGl14patrContaining');
			})
		});
		
		describe ('Test checkout.', function(){
			var module, rootScope, controller, stateParams, log, location;
			var CheckoutService, mockServicesRestangular;
			beforeEach(angular.mock.module('integralweb', function($provide){
				mockServicesRestangular = {
					one : function() {
						return this;
					},
					post : function() {
						return this;
					},
					all : function(url) {
						return this;
					}
				};
				$provide.value('ServicesRestangular', mockServicesRestangular);
				$provide.value('$log', console);
			}));
			
			beforeEach(inject(function($rootScope, $controller, $stateParams,
					$location, $injector, $log) {
				rootScope = $rootScope;
				controller = $controller;
				stateParams = $stateParams;
				log = console;
				location = $location;
				CheckoutService = $injector.get('CheckoutService');
			}));

			it("should be circulation/checkout for the service url and post has been called", function() {
				spyOn(mockServicesRestangular, 'post').andCallThrough();
				spyOn(mockServicesRestangular, 'all').andCallThrough();
				var checkoutRequest = {
						"itemIdentifier" : "0000000001"
				}
				CheckoutService.checkout(checkoutRequest);
				expect(mockServicesRestangular.post).toHaveBeenCalled();
				expect(mockServicesRestangular.all).toHaveBeenCalledWith('circulation/checkout');
			})
		});
	});
	
});
