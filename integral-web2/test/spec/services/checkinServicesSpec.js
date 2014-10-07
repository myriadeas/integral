define(['app','angular','angular-mocks', 'angular-ui-router', 'controllers/circulationController', 'circulation/checkin/controller', 'circulation/checkout/controller', 'circulation/renew/controller', 
'angular-bootstrap', 'restangular', 'underscore', 'i18n/localize', 'ng-grid', 'services/services', 'circulation/checkin/service', 'circulation/checkout/service', 'circulation/renew/service',
'angular-flash','angular-route'], function (app) {
	describe('Test checkin services.', function() {
		describe ('Test checkin.', function(){
			var module, rootScope, controller, stateParams, log, location;
			var CheckinService, mockServicesRestangular;
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
				CheckinService = $injector.get('CheckinService');
			}));

			it("should be circulation/checkin for the service url and post has been called", function() {
				spyOn(mockServicesRestangular, 'post').andCallThrough();
				spyOn(mockServicesRestangular, 'all').andCallThrough();
				var checkinRequest = {
						"itemIdentifier" : "0000000001"
				}
				CheckinService.checkin(checkinRequest);
				expect(mockServicesRestangular.post).toHaveBeenCalled();
				expect(mockServicesRestangular.all).toHaveBeenCalledWith('circulation/checkin');
			})
		});
		
		describe ('Test update transaction Log.', function(){
			var module, rootScope, controller, stateParams, log, location;
			var CheckinService;
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
				CheckinService = $injector.get('CheckinService');
			}));

			it("transaction should not be null", function() {
				var checkinResponse = {
						"ok":true,
						"resensitize":false,
						"magneticMedia":false,
						"alert":false,
						"transactionDate":1391662967946,
						"institutionId":"Petaling Jaya Library"
				}
				
				var transaction = {transactionId: "_CI0000000002", checkinResponse: checkinResponse};
				var actualTransaction = CheckinService.updateTransactionLog(transaction);
				expect(actualTransaction).not.toBeNull();
			})
		});
		
		describe ('Test get item information.', function(){
			var module, rootScope, controller, stateParams, log, location;
			var CheckinService, mockServicesRestangular;
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
				CheckinService = $injector.get('CheckinService');
			}));

			it("should be circulation/checkin/item/information for the service url and get has been called", function() {
				spyOn(mockServicesRestangular, 'get').andCallThrough();
				spyOn(mockServicesRestangular, 'one').andCallThrough();
				var checkinRequest = {
						"itemIdentifier" : "0000000001"
				}
				var ctdocm = {
						"ok":true,
						"resensitize":false,
						"magneticMedia":false,
						"alert":false,
						"transactionDate":1391662967946,
						"institutionId":"Petaling Jaya Library",
						"circulationDetail": {"id": "11"}
				}
				CheckinService.getItemInformation(checkinRequest, ctdocm);
				expect(mockServicesRestangular.get).toHaveBeenCalled();
				expect(mockServicesRestangular.one).toHaveBeenCalledWith('circulation/checkin/item/information');
			})
		});
	});
	
	describe('Test session Storage.', function() {
		var module, rootScope, controller, stateParams, log, location;
		var UserTransactionHistory;
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
			UserTransactionHistory = $injector.get('UserTransactionHistory');
			sessionStorage.clear();
		}));

		it("Should be create a new transaction id.", function() {
			function getData() {
				return  {
					mockTest: "testId01_1"
				};
			}
			function addIntoUserTransactionHistory(id, data, expectedTransaction) {
				var actual = UserTransactionHistory.add(id, data);
				expect(actual).toEqual(expectedTransaction);
			}
			
			function createExpectedTransaction(size){
				var data = getData();
				var expectedTransaction = new Array();
				for(var i = 0; i < size; i++) {
					expectedTransaction.push(data);
				}
				return expectedTransaction;
			}
			
			//Transactions not exists
			var id = "firstId";
			var data = getData();
			var expectedTransaction = createExpectedTransaction(1);
			addIntoUserTransactionHistory(id, data, expectedTransaction);

			//Transactions exists
			data = getData();
			expectedTransaction = createExpectedTransaction(2);
			addIntoUserTransactionHistory(id, data, expectedTransaction);
			
			//Inserting undefined data
			data = undefined
			expectedTransaction = createExpectedTransaction(2);
			addIntoUserTransactionHistory(id, data, expectedTransaction);
			
			//Transactions not exists
			id = "newid";
			data = getData();
			expectedTransaction = createExpectedTransaction(1);
			addIntoUserTransactionHistory(id, data, expectedTransaction);
			
			//Transactions not exists, data undefined
			id = "idnotexist";
			data = undefined;
			expectedTransaction = createExpectedTransaction(0);
			addIntoUserTransactionHistory(id, data, expectedTransaction);
		})
	});
	
	describe('Test item Information services.', function() {
		var module, rootScope, controller, stateParams, log, location;
		var ItemInformation;
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
			ItemInformation = $injector.get('ItemInformationService');
		}));
		
		describe('get patron reserved', function() {
			function getData(status){
				return {"circulationStatus":status, "reservationDetail": {"ci03patr": {"gl14patr": "ChiaKaiYong"}}};
			}
			
			it("Should get reservation detail", function() {
				var itemInformation = getData("ON_HOLD_SHELF");
				var actual = ItemInformation.getReservationDetail(itemInformation);
				var expectedResult = {"ci03patr": {"gl14patr": "ChiaKaiYong"}};
				expect(actual).toEqual(expectedResult);
			});
			
			it("Should not get reservation detail", function() {
				var itemInformation = getData("otherstatus");
				var actual = ItemInformation.getReservationDetail(itemInformation);
				expect(actual).toBeNull;
			});
		});
	});
	
});
