'use strict'

define(['app'], function (integralApp) {
	integralApp.factory('RenewService', function(UserTransactionHistory, ServicesRestangular, $log, $q, $timeout, promiseTracker) {
		return {
			renew: function(renewRequest){
				$log.log("Entering renew service.");
				var response = "";
				if (renewRequest.itemIdentifier == "001"){
					response = ServicesRestangular.one('circulation/renew/renewResponse.json').get();
				}else{
					var deffered = $q.defer();
					ServicesRestangular.one('circulation/renew/error.json').get().then(function(data){
						deffered.reject(data);
					});
					response = deffered.promise;
				}
				$log.log("Leaving renew service");
				return response;
			},
			updateTransactionLog: function(transaction){
				$log.log("Entering update TransactionLog service. Parameter passed: ", "transaction: ", transaction);
				var transactions = UserTransactionHistory.add(transaction.transactionId, transaction.renewResponse);
				$log.log("Leaving updateTransactionLog service");	
				return transactions;
			},
		};
	});
	
});

