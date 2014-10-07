'use strict'
define(['app'], function (integralApp) {
	integralApp.factory('CheckoutService', function($log, ServicesRestangular, Restangular, UserTransactionHistory) {
		return {
			validatePatron: function(patronId) {
				$log.log("Entering validate patron service. Parameter passed: patron id:", patronId);
				var response = ServicesRestangular.one('circulation/checkout/validate/patron').get({patronIdentifier: patronId, selector:"*"});
				$log.log("Leaving validate patron service");
				return response;
			},
			getPatronDetail: function(patronId){
				$log.log("Entering get patron detail service. Parameter passed: patron id: ", patronId);
				var glpatr = ServicesRestangular.one('glpatr/search/findByGl14patrContaining').get({search : patronId});
				$log.log("Leaving get patron detail service");
				return glpatr;
			},
			checkout: function(checkoutRequest){
				$log.log("Entering validate item service. Parameter passed: checkoutRequest: ", checkoutRequest);
				var response = ServicesRestangular.all('circulation/checkout').post(checkoutRequest,{selector:"*,circulationDetail(*,patron(*)),item(title)"});
				$log.log("Leaving validate item service");
				return response;
			},
			updateTransactionLog: function(transaction){
				$log.log("Entering update TransactionLog service. Parameter passed: ", "transaction: ", transaction);
				var transactions = UserTransactionHistory.add(transaction.transactionId, transaction.checkoutResponse);
				$log.log("Leaving updateTransactionLog service");	
				return transactions;
			}
		};
	});

});