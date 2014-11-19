'use strict'

define(['app'], function (ilmuApp) {
	ilmuApp.factory('CreateItemService', function(UserTransactionHistory, ServicesRestangular, Restangular, $log) {
		return {
			create: function(createRequest) {
				$log.log("Entering create service. Parameter passed: ", "create request: ", createRequest);
        var response = ServicesRestangular.all('assetmanager/create').post(createRequest, {selector:"*,item(itemIdentifier, title)"});
				$log.log("Leaving create service");
				return response;
			},
			updateTransactionLog: function(transaction){
				$log.log("Entering update TransactionLog service. Parameter passed: ", "transaction: ", transaction);
				var transactions = UserTransactionHistory.add(transaction.transactionId, transaction.releaseResponse);
				$log.log("Leaving updateTransactionLog service");	
				return transactions;
			},
			getItemInformation: function(itemIdentifier, circulationIdentifier){
				$log.log("Entering getItemInformation service. Parameter passed: ", "item identifier: ", itemIdentifier, "circulation identifier: ", circulationIdentifier);
				$log.log("Leaving getItemInformation service");
				return ServicesRestangular.one('assetmanager/item/information').get({itemIdentifier: itemIdentifier, cicircIdentifier: circulationIdentifier});
			}
		};
	});

});