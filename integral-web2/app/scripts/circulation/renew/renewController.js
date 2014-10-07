'use strict';
define(['angular', 'lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
	 module.controller('RenewController', ['$scope', '$log', '$stateParams', '$location', 'RenewService', 'Utility', 'DatetimeUtil', 'flash', 'UserTransactionHistory', '$timeout',
	function($scope, $log, $stateParams, $location, RenewService, Utility, DatetimeUtil, flash, UserTransactionHistory, $timeout){ 
		$log.log("Entering RenewCtrl", $stateParams);
		// Initialize Code 
		var renewRequest = {
			itemIdentifier : undefined
		};
		$scope.renewRequest = renewRequest;
		$scope.template = {
			url : "views/circulation/renew/transactionLog.html"
		};
		// End Initialize Code 
		
		
		$scope.isNewTransaction = function() {
			return $stateParams.transactionId == undefined;
		}

		$scope.getNewTransactionId = function() {
			var prefix = "_RN";
			return transactionId = prefix + DatetimeUtil.now();
		}

		if ($scope.isNewTransaction()) {
			$log.log("New Renew Transaction. Creating transaction id.");
			var transactionId = $scope.getNewTransactionId();
			$log.log("Redirect to checkin with transactionId=", transactionId);
			$location.path("circulation/renew").search("transactionId", transactionId);
		} else {
			$log.log("Existing Transaction with transactionId=", $stateParams.transactionId);
			$scope.transactionId = $stateParams.transactionId;
		}
		
		$scope.disableRenew = function() {
			$scope.isDisallowedToPerformRenew = true;
		}
		
		$scope.enableRenew = function() {
			$scope.isDisallowedToPerformRenew = false;
		}

		$scope.renew = function(){
			$log.log("Entering Submit renew.", $scope.renewRequest);
			$scope.disableRenew();
			var renewPromise = RenewService.renew($scope.renewRequest);
			renewPromise.then(function(renewResponse) {
				$log.log("Successfully renew. Renew response=", renewResponse);
				$scope.successRenew(renewResponse);
        $scope.renewRequest.itemIdentifier = "";
			}, function(error) {
				$log.log("Failed to renew. Error=", error);
				$scope.isDisallowedToPerformRenew = false;
				flash.error = error.data.message;
				
				var transaction = {
					transactionId : $scope.transactionId,
					renewResponse : undefined
				};
				$scope.updateTransactionLog(transaction);
        $scope.renewRequest.itemIdentifier = "";         
			});
			$log.log("Leaving Submit renew.");
		}
		
		$scope.successRenew = function(renewResponse) {
			$log.log("Entering successRenew ctrl. Parameter passed: ", "renew response: ", renewResponse);
			$scope.renewResponse = renewResponse;
			flash.success = renewResponse.screenMessage;
			$scope.enableRenew();
			var transaction = {
					transactionId : $scope.transactionId,
					renewResponse : renewResponse
				};
			$scope.updateTransactionLog(transaction);
			$log.log("Leaving successRenew ctrl");
		}
		
		$scope.updateTransactionLog = function(transaction) {
			$log.log("Entering updateTransactionLog ctrl. Parameter passed: transaction: ", transaction);
			$scope.transactions = RenewService.updateTransactionLog(transaction);
			$log.log("Leaving updateTransactionLog ctrl", $scope.transactions);
		}
		
		$scope.updateTransactionLog({transactionId : $scope.transactionId});

		$scope.newPatron = function(){
			$log.log("Entering newPatron");
			UserTransactionHistory.clear();
			$scope.updateTransactionLog({transactionId : $scope.transactionId});
			$scope.renewRequest.itemIdentifier = "";
			flash.info = "Previous record has been clear";
			$log.log("Leaving newPatron");
		}
		
		
		
		
	}]);
    
});