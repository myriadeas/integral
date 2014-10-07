'use strict'

define(['app'], function (integralApp) {
	integralApp.factory('RenewService', function(UserTransactionHistory, ServicesRestangular, $log) {
		return {
			renew: function(renewRequest){
				$log.log("Entering renew service.");
				return ServicesRestangular.all('circulation/renew').post(renewRequest,{selector:"*,circulationDetail(*,patron(username)),item(title)"});
				$log.log("Leaving renew service");
			},
			updateTransactionLog: function(transaction){
				$log.log("Entering update TransactionLog service. Parameter passed: ", "transaction: ", transaction);
				var transactions = UserTransactionHistory.add(transaction.transactionId, transaction.renewResponse);
				$log.log("Leaving updateTransactionLog service");	
				return transactions;
			}
		};
	});
	
});

