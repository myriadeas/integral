define(['app','angular','angular-mocks', 'angular-ui-router', 'controllers/circulationController', 'circulation/checkin/controller', 'circulation/checkout/controller', 'circulation/renew/controller', 
'angular-bootstrap', 'restangular', 'underscore', 'i18n/localize', 'ng-grid', 'services/services', 'circulation/checkin/service', 'circulation/checkout/service', 'circulation/renew/service',
'angular-flash','angular-route'], function (app) {
	describe('Test renew services.', function() {
		describe ('Test renew.', function(){
			var module, rootScope, controller, stateParams, log, location;
			var RenewService, mockServicesRestangular;
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
				RenewService = $injector.get('RenewService');
			}));

			it("should be services/circulation/renew for the service url and post has been called", function() {
				spyOn(mockServicesRestangular, 'post').andCallThrough();
				spyOn(mockServicesRestangular, 'all').andCallThrough();
				var renewRequest = {
						"itemIdentifier" : "0000000001"
				}
				RenewService.renew(renewRequest);
				expect(mockServicesRestangular.post).toHaveBeenCalled();
				expect(mockServicesRestangular.all).toHaveBeenCalledWith('services/circulation/renew');
			})
		});
		
		describe ('Test update transaction Log.', function(){
			var module, rootScope, controller, stateParams, log, location;
			var RenewService;
			beforeEach(angular.mock.module('integralweb', function($provide){
				$provide.value('$log', console);
			}));
			
			beforeEach(inject(function($rootScope, $controller, $stateParams,
					$location, $injector, $log) {
				rootScope = $rootScope;
				controller = $controller;
				stateParams = $stateParams;
				log = console;
				location = $location;
				RenewService = $injector.get('RenewService');
			}));

			it("transaction should not be null", function() {
				var renewResponse = {
					"circulationStatus": "04",
					"securityMarker": "01",
					"feeType": "04",
					"titleIdentifier": "Java and Managing Sofware",
					"screenMessage": "Successfully Renew."
				}
				
				var transaction = {transactionId: "_RN0000000002", renewResponse: renewResponse};
				var actualTransaction = RenewService.updateTransactionLog(transaction);
				expect(actualTransaction).not.toBeNull();
			})
		});
	});
	
});
