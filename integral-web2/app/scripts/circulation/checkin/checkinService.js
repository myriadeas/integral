'use strict'

define(['app'], function (integralApp) {
	integralApp.factory('CheckinService', function(UserTransactionHistory, ServicesRestangular, $log) {
		return {
			checkin: function(checkinRequest) {
				$log.log("Entering checkin service. Parameter passed: ", "checkin request: ", checkinRequest);
        var response = ServicesRestangular.all('circulation/checkin').post(checkinRequest,{selector:"*,circulationDetail(*,patron(username)),item(title)"});
				$log.log("Leaving checkin service");
				return response;
			},
			updateTransactionLog: function(transaction){
				$log.log("Entering update TransactionLog service. Parameter passed: ", "transaction: ", transaction);
				var transactions = UserTransactionHistory.add(transaction.transactionId, transaction.modifiedCheckinResponse);
				$log.log("Leaving updateTransactionLog service");	
				return transactions;
			},
			getItemInformation: function(itemIdentifier, circulationIdentifier){
				$log.log("Entering getItemInformation service. Parameter passed: ", "item identifier: ", itemIdentifier, "circulation identifier: ", circulationIdentifier);
				$log.log("Leaving getItemInformation service");
				return ServicesRestangular.one('circulation/checkin/item/information').get({itemIdentifier: itemIdentifier, circulationTransactionId: circulationIdentifier, selector:"*,item(PK,itemIdentifier),awaitingCollectionReservationTransaction(patron(PK,username,firstname,lastname),material(PK,getTitle),branch(PK,code,description))"});
			},
      getReservationDetail: function(itemInformation) {
				$log.log("Entering getReservationDetail service. Parameter passed: ", "item information: ", itemInformation);
				var reservationDetail = null;
				$log.log("Circulation Status= ", itemInformation.circulationStatus);
				if (itemInformation.circulationStatus == "ON_HOLD_SHELF"){
					reservationDetail = itemInformation.awaitingCollectionReservationTransaction;
				}
				$log.log("Leaving getReservationDetail service. Reservation detail: ", reservationDetail);
				return reservationDetail;
			}
		};
	});

	
});