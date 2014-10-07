'use strict'

define(['app'], function (ilmuApp) {
	ilmuApp.factory('ReleaseService', function(UserTransactionHistory, ServicesRestangular, Restangular, $log) {
		return {
			release: function(releaseRequest) {
				$log.log("Entering release service. Parameter passed: ", "release request: ", releaseRequest);
        var response = ServicesRestangular.all('circulation/release').post(releaseRequest, {selector:"*,item(itemIdentifier,title)"});
				$log.log("Leaving release service");
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
				return ServicesRestangular.one('circulation/item/information').get({itemIdentifier: itemIdentifier, cicircIdentifier: circulationIdentifier});
			}
		};
	});

	ilmuApp.factory('ItemInformationService', function($log, Utility) {
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