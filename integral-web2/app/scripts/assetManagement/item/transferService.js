'use strict'

define(['app'], function (integralApp) {
	integralApp.factory('TransferItemService', function(UserTransactionHistory, ServicesRestangular, Restangular, $log) {
		return {
			transfer: function(releaseRequest) {
				$log.log("Entering transfer service. Parameter passed: ", "release request: ", releaseRequest);
        var response = ServicesRestangular.all('assetmanagement/release').post(releaseRequest, {selector:"*,item(itemIdentifier, resourceDescriptorIdentifier)"});
				$log.log("Leaving transfer service");
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
				return ServicesRestangular.one('assetmanagement/item/information').get({itemIdentifier: itemIdentifier, cicircIdentifier: circulationIdentifier});
			}
		};
	});

	integralApp.factory('ItemInformationService', function($log, Utility) {
		return {
			getReservationDetail: function(itemInformation) {
				$log.log("Entering getReservationDetail service. Parameter passed: ", "item information: ", itemInformation);
				var reservationDetail = null;
				$log.log("Curculation Status= ", itemInformation.circulationStatus);
				if (itemInformation.circulationStatus == "ON_HOLD_SHELF"){
					reservationDetail = itemInformation.reservationDetail;
				}
				$log.log("Leaving getReservationDetail service. Reservation detail: ", reservationDetail);
				return reservationDetail;
			}
		};
	});
});