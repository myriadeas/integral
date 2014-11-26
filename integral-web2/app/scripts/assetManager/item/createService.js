'use strict'

define(['app'], function (ilmuApp) {
	ilmuApp.factory('CreateItemService', function(UserTransactionHistory, ServicesRestangular, Restangular, $log) {
		return {
			create: function(createRequest) {
				$log.log("Entering create service. Parameter passed: ", "create request: ", createRequest);
				var response = ServicesRestangular.all('assetmanager/create').post(createRequest, {selector:"*,item(itemIdentifier, title)"});
				$log.log("Leaving create service");
				return response;
			}
		};
	});

});