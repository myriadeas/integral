'use strict'

define(['app'], function (ilmuApp) {
	ilmuApp.factory('CheckinService', function(UserTransactionHistory, ServicesRestangular, $log) {
		return {
			checkin: function(checkinRequest) {
				$log.log("Entering checkin service. Parameter passed: ", "checkin request: ", checkinRequest);
				var response = ServicesRestangular.all('circulation/checkin').post(checkinRequest);
				$log.log("Leaving checkin service");
				return response;
			},
			updateTransactionLog: function(transaction){
				$log.log("Entering update TransactionLog service. Parameter passed: ", "transaction: ", transaction);
				var transactions = UserTransactionHistory.add(transaction.transactionId, transaction.checkinResponse);
				$log.log("Leaving updateTransactionLog service");	
				return transactions;
			},
			getItemInformation: function(itemIdentifier, circulationIdentifier){
				$log.log("Entering getItemInformation service. Parameter passed: ", "item identifier: ", itemIdentifier, "circulation identifier: ", circulationIdentifier);
				$log.log("Leaving getItemInformation service");
				return ServicesRestangular.one('circulation/checkin/item/information').get({itemIdentifier: itemIdentifier, cicircIdentifier: circulationIdentifier});
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