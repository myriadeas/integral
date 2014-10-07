'use strict'

define(['app'], function (integralApp) {
	integralApp.factory('CheckoutService', function($log, $q, ServicesRestangular, Restangular, UserTransactionHistory) {
		return {
			validatePatron: function(patronId) {
				$log.log("Entering validate patron service. Parameter passed: patron id:", patronId);
				var response;
				if (patronId == "chiaweiwei"){
					response = ServicesRestangular.one('circulation/checkout/validate/patron/validatePatronResponse.json').get();
				}else{
					var deffered = $q.defer();
					ServicesRestangular.one('circulation/checkout/validate/patron/error.json').get().then(function(data){
						deffered.reject(data);
					});
					response = deffered.promise;
				}
				$log.log("Leaving validate patron service");
				return response;
			},
			getPatronDetail: function(patronId){
				$log.log("Entering get patron detail service. Parameter passed: patron id: ", patronId);
				var glpatr = ServicesRestangular.one('circulation/checkout/glpatr/search/findByGl14patrContaining/glpatr.json').get();
				$log.log("Leaving get patron detail service");
				return glpatr;
			},
			checkout: function(checkoutRequest){
				$log.log("Entering validate item service. Parameter passed: checkoutRequest: ", checkoutRequest);
				var response;
				if (checkoutRequest.itemIdentifier == "001"){
					response = ServicesRestangular.one('circulation/checkout/checkoutResponse.json').get();
				}else{
					var deffered = $q.defer();
					ServicesRestangular.one('circulation/checkout/error.json').get().then(function(data){
						deffered.reject(data);
					});
					response = deffered.promise;
				}
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