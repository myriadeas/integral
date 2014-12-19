'use strict'

define(['app'], function (integralApp) {
	integralApp.factory('ReceiveItemService', function(UserTransactionHistory, ServicesRestangular, Restangular, $log, $q, $timeout) {
		return {
			receive: function(receiveRequest) {
				$log.log("Entering receive service. Parameter passed: ", "receive request: ", receiveRequest);
				var response = "";
				if (receiveRequest.title == "Management") {
					response = ServicesRestangular.one('assetmanagement/receive/receiveResponse.json').get();
				}else{
					var deffered = $q.defer();
					ServicesRestangular.one('assetmanagement/receive/error.json').get().then(function(data){
						deffered.reject(data);
					});
				}
				$log.log("Leaving receive service");
				return response;
			}
		};
	});

});

