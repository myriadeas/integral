'use strict';
define(['angular', 'lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.cataloguing');
	 module.controller('ReleaseController', ['$scope', '$log', '$stateParams', '$location', 'Restangular', 'ReleaseService', 'Utility', 'DatetimeUtil', 'ItemInformationService', 'UserTransactionHistory', 'ModalPopup', 'flash',
	function($scope, $log, $stateParams, $location, Restangular, ReleaseService, Utility, DatetimeUtil, ItemInformationService, UserTransactionHistory, ModalPopup, flash){ 
		$log.log("Entering ReleaseCtrl", $stateParams);
		// Initialize Code 
		
		var defaultMessage = {
			error : "",
			releaseResponse : "",
			itemReserveMessage : ""
		};
		var releaseRequest = {
			itemIdentifier : undefined
		};

		$scope.releaseRequest = releaseRequest;
		$scope.template = {
			url : "views/cataloguing/release/transactionLog.html"
		};
       
    
		// End of Intialize Code 

		$scope.isNewTransaction = function() {
			return $stateParams.transactionId == undefined;
		}

		$scope.getNewTransactionId = function() {
			var prefix = "_R";
			return transactionId = prefix + DatetimeUtil.now();
		}

		if ($scope.isNewTransaction()) {
			$log.log("New Release Transaction. Creating transaction id.");
			var transactionId = $scope.getNewTransactionId();
			$log.log("Redirect to release with transactionId=", transactionId);
			$location.path("cataloguing/release").search("transactionId",
					transactionId);
		} else {
			$log.log("Existing Transaction with transactionId=", $stateParams.transactionId);
			$scope.transactionId = $stateParams.transactionId;
		}

		$scope.disableRelease = function() {
			$scope.isDisallowedToPerformRelease = true;
		}

		$scope.enableRelease = function() {
			$scope.isDisallowedToPerformRelease = false;
		}

		$scope.release = function() {
      $log.log("Entering release ctrl");
			$scope.disableRelease();
			var releasePromise = ReleaseService.release($scope.releaseRequest);
			releasePromise.then(function(releaseResponse) {
				$log.log("Successfully released. Release response=", releaseResponse);            
        $scope.showTransactionLog = releaseResponse; 
        flash.success = releaseResponse.message;           
				$scope.successRelease(releaseResponse);
        $scope.releaseRequest.itemIdentifier = "";
			}, function(error) {
				$log.log("Failed to release. Error=", error);
				$scope.isDisallowedToPerformRelease = false;
				flash.error = error.data.message;
        var item = {"itemIdentifier":$scope.releaseRequest.itemIdentifier};  
        var releaseResponse = {
          "item":item,
          "successful": false,
          "message": error.data.message
        };
        $log.log("Failure release. Release response=", releaseResponse);
				var transaction = {
					transactionId : $scope.transactionId,
					releaseResponse : releaseResponse
				};
				$scope.updateTransactionLog(transaction);
        $scope.releaseRequest.itemIdentifier = "";
			});
		};

		$scope.successRelease = function(releaseResponse) {
			$log.log("Entering successRelease ctrl. Parameter passed: ", "release response: ", releaseResponse);
			$scope.releaseResponse = releaseResponse;
			//flash.success = releaseResponse.screenMessage;      
			$scope.getItemInformation($scope.releaseRequest, releaseResponse);            
      $log.log("Leaving successRelease ctrl");
		}

		$scope.getItemInformation = function(releaseRequest, releaseResponse) {
			$log.log("Entering getItemInformation ctrl. Parameter passed: ", "release request: ", releaseRequest, " release response: ", releaseResponse);
			var itemId = releaseRequest.itemIdentifier;
			
      var transaction = {
					transactionId : $scope.transactionId,
					releaseResponse : releaseResponse
				};			
			$scope.updateTransactionLog(transaction);  			
			$scope.enableRelease();
      
			$log.log("Leaving getItemInformation ctrl");
		}

		$scope.updateTransactionLog = function(transaction, reservationDetail) {
			$log.log("Entering updateTransactionLog ctrl. Parameter passed: ", "transaction:", transaction, " reservation detail: ", reservationDetail);
			if (reservationDetail != null) {
				transaction.releaseResponse.pickupBranch = reservationDetail.ci03brnc.description;
			}
			$scope.transactions = ReleaseService.updateTransactionLog(transaction);       
      $scope.statistic($scope.transactions);                                              
			$log.log("Leaving updateTransactionLog ctrl", $scope.transactions);
		}
    
    $scope.statistic = function(transactions) {
      var noOfFail = 0;
      var noOfSuccess = 0;
      for (var transaction in $scope.transactions) {
        if ($scope.transactions[transaction].successful == false){
          noOfFail = noOfFail + 1;
        } else {
          noOfSuccess = noOfSuccess + 1;
        }
      }
      
      $scope.noOfFail = noOfFail;
      $scope.noOfSuccess = noOfSuccess;
    }

		$scope.updateTransactionLog({transactionId : $scope.transactionId, releaseResponse : undefined});
		
		$scope.newPatron = function(){
			$log.log("Entering newPatron");
			//$location.url($location.path());
			UserTransactionHistory.clear();
			$scope.updateTransactionLog({transactionId : $scope.transactionId});
			$scope.releaseRequest.itemIdentifier = "";
			flash.info = 'Previous record has been clear.';
			$log.log("Leaving newPatron");
		}
    
		$log.log("Leaving ReleaseCtrl");
	

	function DisplayReservationMessageCtrl($scope, $log, $modalInstance, items){
		$log.log("Entering DisplayReservationMessageCtrl. Parameter passed: ", items);
		$scope.reservationDetail = items.reservationDetail;
		console.log($scope.reservationDetail);
		$scope.ok = function() {
			$modalInstance.close();
		};
	}
		
		
		
	}]);
    
});