'use strict'

define(['app'], function (integralApp) {
	integralApp.factory('CreateItemService', function(UserTransactionHistory, ServicesRestangular, Restangular, $log) {
		return {
			create: function(createRequest) {
				$log.log("Entering create service. Parameter passed: ", "create request: ", createRequest);
				var response = ServicesRestangular.all('assetmanagement/create').post(createRequest, {selector:"*,item(itemIdentifier, title)"});
				$log.log("Leaving create service");
				return response;
			},
			updateTransactionLog: function(transaction){
				$log.log("Entering update TransactionLog service. Parameter passed: ", "transaction: ", transaction);
				var transactions = UserTransactionHistory.add(transaction.transactionId, transaction.modifiedCreateResponse);
				$log.log("Leaving updateTransactionLog service");	
				return transactions;
			},
			getResourceDescriptorSolrInformation: function(resourceDescriptorIdentifier){
				$log.log("Entering getItemInformation service. Parameter passed: ", "resource descriptor identifier: ", resourceDescriptorIdentifier);
				$log.log("Leaving getItemInformation service");
				return ServicesRestangular.one('resourceDescriptor/getResourceDescriptorSolrById').get({id : resourceDescriptorIdentifier});
			}
		};
	});

});