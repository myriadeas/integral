'use strict'

define(['app'], function (integralApp) {
	integralApp.factory('ReceiveItemService', function(UserTransactionHistory, ServicesRestangular, Restangular, $log) {
		return {
			receive: function(receiveRequest) {
				$log.log("Entering receive service. Parameter passed: ", "receive request: ", receiveRequest);
				var response = ServicesRestangular.all('assetmanager/receive').post(receiveRequest, {selector:"*,item(itemIdentifier, title)"});
				$log.log("Leaving receive service");
				return response;
			}
		};
	});

});