'use strict';
define(['angular', 'lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.controller('CheckoutController', ['$scope', '$log', '$stateParams', '$location', 'CheckoutService', 'Utility', 'DatetimeUtil', 'ModalPopup', 'UserTransactionHistory', 'flash', 'Patron',
		function($scope, $log, $stateParams, $location, CheckoutService, Utility, DatetimeUtil, ModalPopup, UserTransactionHistory, flash, patron){        
			$log.log("stateParams", $stateParams);
			// Initialize Code
			$scope.inputItem = {"username": ""};
			$scope.inputItem.username = $stateParams.patronId;
			$scope.template = {
					url : "views/circulation/checkout/transactionLog.html"
				};
			// End of Initialize Code 
			
			
			$scope.isNewTransaction = function() {
				return $stateParams.transactionId == undefined;
			}

			$scope.getNewTransactionId = function() {
				var prefix = "_CO";
				return transactionId = prefix + DatetimeUtil.now();
			}
		 
			if ($scope.isNewTransaction()) {
				$log.log("New Checkout Transaction. Creating transaction id.");
				var transactionId = $scope.getNewTransactionId();
				$log.log("Redirect to checkout with transactionId=", transactionId);
				$location.path("circulation/checkout").search("transactionId", transactionId);
			} else {
				$log.log("Existing Transaction with transactionId=", transactionId);
				$scope.transactionId = $stateParams.transactionId;
			}
			
			
			
			$scope.$on('mSearchInput:selectItem', function(event, selectedItem) {
				$scope.inputItem.username = selectedItem.username;
				if ($scope.inputItem.username != null) {
					$scope.patronSearch();
				}
			});
			
			
			$scope.disallowChangePatron = function() {
				$scope.isDisallowedToChangePatron = true;
			}
			
			
			$scope.patronSearch = function() {
				$log.log("Entering patron search", $scope.inputItem.username);
				if ($scope.inputItem.username != null) {
					$location.path("circulation/checkout").search("patronId", $scope.inputItem.username);
				}
			}
			
			$scope.validatePatron = function() { 
				var patronId = $scope.inputItem.username;
				CheckoutService.validatePatron(patronId).then(function(responseValidatePatron){
					$log.log("Successfully validate patron. validate patron response=", responseValidatePatron);
					$scope.showItemIdentifier = true;
					$scope.populatePatronDetails();
				}, function(error){
					$log.log("Invalid patron. Error=", error);
					//flash("error", error.data.message);
					flash.error = error.data.message;
				});
			};
			
			$scope.populatePatronDetails = function() { 
				patron.search("findByUsername", {search: $scope.inputItem.username}).then(function(response){
					$scope.patron = response[0];
				});
			};
			
			if (Utility.isNotNull($scope.inputItem.username)) {
				$scope.validatePatron();
			};
			
			$scope.checkout = function() {
				var checkoutRequest = {
					patronIdentifier : $scope.inputItem.username,
					itemIdentifier : $scope.itemIdentifier
				};
				CheckoutService.checkout(checkoutRequest).then(function(checkoutResponse){
					$log.log("Successfully validate item. checkout response=", checkoutResponse);
					$scope.disallowChangePatron(); 
					$scope.showTransactionLog = checkoutResponse;
					flash.success = checkoutResponse.screenMessage;
					var transaction = {"transactionId": $scope.transactionId, "checkoutResponse": checkoutResponse};
					$scope.updateTransactionLog(transaction);
          $scope.itemIdentifier = "";
				}, function(error){
					$log.log("Invalid Item Identifier. Error=", error);
					$scope.showTransactionLog = error;
					$scope.error = error;
					flash.error = error.data.message;
          
					var transaction = {
						transactionId : $scope.transactionId,
						checkoutResponse : undefined
					};
          $log.log("transaction=", transaction);
					$scope.updateTransactionLog(transaction);
          $scope.itemIdentifier = "";
				});
			};
			
			$scope.updateTransactionLog = function(transaction) { 
				$scope.transactions = CheckoutService.updateTransactionLog(transaction);
			}
			
			$scope.updateTransactionLog({transactionId : $scope.transactionId, checkinResponse : undefined});
			
			$scope.newPatron = function(){
				$log.log("Entering newPatron");
				delete $location.$$search.patronId;
				$location.$$compose();
				UserTransactionHistory.clear();
				$scope.updateTransactionLog({transactionId : $scope.transactionId});
				flash.info = 'Previous record has been clear.';
				$log.log("Leaving newPatron ");
			}
    }]);
    
});
