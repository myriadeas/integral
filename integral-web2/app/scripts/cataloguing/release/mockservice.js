'use strict';

define(['app'], function (ilmuApp) {
	ilmuApp.factory('ReleaseService', function(UserTransactionHistory, $q, ServicesRestangular, $log) {
		return {
			release: function(releaseRequest) {
				$log.log("Entering release service. Parameter passed: ", "release request: ", releaseRequest);
				var response;
				if (releaseRequest.itemIdentifier == "001" || releaseRequest.itemIdentifier == "002"){
					response = ServicesRestangular.one('circulation/release/releaseResponse.json').get();
				}else{
					var deffered = $q.defer();
					ServicesRestangular.one('circulation/checkout/error.json').get().then(function(data){
						deffered.reject(data);
					});
					response = deffered.promise;
				}
				
				$log.log("Leaving release service");
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
				var response;
				if (itemIdentifier == "001"){
					response = ServicesRestangular.one('circulation/checkin/item/information/itemInformation-reservationDetail.json').get({itemIdentifier: itemIdentifier, cicircIdentifier: circulationIdentifier});
				}else{
					response = ServicesRestangular.one('circulation/checkin/item/information/itemInformation.json').get({itemIdentifier: itemIdentifier, cicircIdentifier: circulationIdentifier});
				}
				return response;
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