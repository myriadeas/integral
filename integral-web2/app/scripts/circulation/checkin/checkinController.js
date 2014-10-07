'use strict';
define(['angular', 'lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
	 module.controller('CheckinController', ['$scope', '$log', '$stateParams', '$location', 'Restangular', 'CheckinService', 'Utility', 'DatetimeUtil', 'ItemInformationService', 'UserTransactionHistory', 'ModalPopup', 'flash',
	function($scope, $log, $stateParams, $location, Restangular, CheckinService, Utility, DatetimeUtil, ItemInformationService, UserTransactionHistory, ModalPopup, flash, $modal){ 
		$log.log("Entering CheckinCtrl", $stateParams);
		// Initialize Code 
		
		var defaultMessage = {
			error : "",
			checkinResponse : "",
			itemReserveMessage : "",
			fineMessage : ""
		};
		var checkinRequest = {
			itemIdentifier : undefined
		};

		$scope.checkinRequest = checkinRequest;
		$scope.template = {
			url : "views/circulation/checkin/transactionLog.html"
		};
		// End of Intialize Code 

		$scope.isNewTransaction = function() {
			return $stateParams.transactionId == undefined;
		}

		$scope.getNewTransactionId = function() {
			var prefix = "_CI";
			return transactionId = prefix + DatetimeUtil.now();
		}

		if ($scope.isNewTransaction()) {
			$log.log("New Checkin Transaction. Creating transaction id.");
			var transactionId = $scope.getNewTransactionId();
			$log.log("Redirect to checkin with transactionId=", transactionId);
			$location.path("circulation/checkin").search("transactionId",
					transactionId);
		} else {
			$log.log("Existing Transaction with transactionId=", $stateParams.transactionId);
			$scope.transactionId = $stateParams.transactionId;
		}

		$scope.disableCheckin = function() {
			$scope.isDisallowedToPerformCheckin = true;
		}

		$scope.enableCheckin = function() {
			$scope.isDisallowedToPerformCheckin = false;
		}

		$scope.checkin = function() {
			$scope.disableCheckin();
			var checkinPromise = CheckinService.checkin($scope.checkinRequest);
			checkinPromise.then(function(checkinResponse) {
				$log.log("Successfully checkin. Checkin response=", checkinResponse);
				$scope.successCheckin(checkinResponse);
        $scope.checkinRequest.itemIdentifier = "";
			}, function(error) {
				$log.log("Failed to checkin. Error=", error);
				$scope.isDisallowedToPerformCheckin = false;
				flash.error = error.data.message;
				var transaction = {
					transactionId : $scope.transactionId,
					checkinResponse : undefined
				};
				$scope.updateTransactionLog(transaction);
        $scope.checkinRequest.itemIdentifier = "";
			});
      
		};

		$scope.successCheckin = function(checkinResponse) {
			$log.log("Entering successCheckin ctrl. Parameter passed: ", "checkin response: ", checkinResponse);
			$scope.checkinResponse = checkinResponse;
			flash.success = checkinResponse.screenMessage;  
			$scope.getItemInformation($scope.checkinRequest, checkinResponse);
			$log.log("Leaving successCheckin ctrl");
		}

		$scope.getItemInformation = function(checkinRequest, checkinResponse) {
			$log.log("Entering getItemInformation ctrl. Parameter passed: ", "checkin request: ", checkinRequest, " checkin response: ", checkinResponse);
			var itemId = checkinRequest.itemIdentifier;
			var circulationId = checkinResponse.circulationDetail.PK;
			CheckinService.getItemInformation(itemId, circulationId).then(function(itemInformationResponse) {
      
        var modifiedCheckinResponse = checkinResponse;
        modifiedCheckinResponse['feeAmount'] = itemInformationResponse.feeAmount;
        modifiedCheckinResponse['feeType'] = itemInformationResponse.feeType;
        
				var transaction = {
					transactionId : $scope.transactionId,
					modifiedCheckinResponse : modifiedCheckinResponse,
          itemInformationResponse : itemInformationResponse
				};
        $log.log("transaction", transaction);
        $scope.reservationDetail = CheckinService.getReservationDetail(itemInformationResponse);
				$scope.updateTransactionLog(transaction, $scope.reservationDetail);
				if ($scope.reservationDetail){
					var data = {"reservationDetail": $scope.reservationDetail};
					var htmlPath = "views/circulation/checkin/reservationMessage.html";
					var controller = DisplayReservationMessageCtrl;
          ModalPopup.openDialog(data, htmlPath, controller);
          
				}
				$scope.enableCheckin();
			});
			$log.log("Leaving getItemInformation ctrl");
		}

		$scope.updateTransactionLog = function(transaction, reservationDetail) {
			$log.log("Entering updateTransactionLog ctrl. Parameter passed: ", "transaction:", transaction, " reservation detail: ", reservationDetail);
			if (reservationDetail != null) {
				transaction.modifiedCheckinResponse.pickupBranch = reservationDetail.branch.description;
			}
      
			$scope.transactions = CheckinService.updateTransactionLog(transaction);
			$log.log("Leaving updateTransactionLog ctrl", $scope.transactions);
		}

		$scope.updateTransactionLog({transactionId : $scope.transactionId, modifiedCheckinResponse : undefined});
		
		$scope.newPatron = function(){
			$log.log("Entering newPatron");
			//$location.url($location.path());
			UserTransactionHistory.clear();
			$scope.updateTransactionLog({transactionId : $scope.transactionId});
			$scope.checkinRequest.itemIdentifier = "";
			flash.info = 'Previous record has been clear.';
			$log.log("Leaving newPatron");
		}
		$log.log("Leaving CheckinCtrl");
	

	  var DisplayReservationMessageCtrl = function ($scope, $log, $modalInstance, items){
    	$log.log("Entering DisplayReservationMessageCtrl. Parameter passed: ", items);
      
      $scope.reservationDetail = items.reservationDetail;
    	console.log($scope.reservationDetail);
    	$scope.ok = function() {
    		$modalInstance.close();
    	};
      
      $log.log("Leaving DisplayReservationMessageCtrl.");
    };

		
		
		
	}]);
  
  
    
});