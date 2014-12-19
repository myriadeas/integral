'use strict';
define(['angular', 'lodash','jquery','assetManagement/assetManagement'], function (angular, _, $) {
    var module = angular.module('integral.assetManagement');
	 module.controller('ReceiveController', ['$scope', '$log', '$stateParams', '$location', 'Restangular', 'ReceiveItemService', 'Utility', 'DatetimeUtil', 'ItemInformationService', 'UserTransactionHistory', 'ModalPopup', 'flash', 'Localization',
	function($scope, $log, $stateParams, $location, Restangular, ReceiveItemService, Utility, DatetimeUtil, ItemInformationService, UserTransactionHistory, ModalPopup, flash, Localization){ 
		$log.log("Entering ReceiveCtrl", $stateParams);
		// Initialize Code 
		
		var defaultMessage = {
			error : "",
			receiveRequest : ""
		};
		
		var receiveRequest = {
			title: undefined,
			author: undefined,
			isbn: undefined,
			numberOfCopy: undefined,
			foreignCost : undefined,
			localCost : undefined
		}
		$scope.receiveRequest = receiveRequest;       

		$scope.receive = function() {
			$log.log("Entering receive ctrl");
				ReceiveItemService.receive($scope.receiveRequest).then(function(receiveResponse) {
					$log.log("Successfully receive. receive response=", receiveResponse);            
		   					     
					flash.success = Localization.resolve("assetManagement.item.receive.success", "Successfully receive item.", receiveResponse);
				   
					$scope.receiveRequest={
						title: "",
						author: "",
						isbn: "",
						numberOfCopy: "",
						foreignCost: "",
						localCost: ""							
					};
				}, function(error) {
					$log.log("Failed to receive. Error=", error);
					$scope.isDisallowedToPerformReceive = false;
					flash.error = error.data.message;
					
					var receiveResponse = {
					  "title":null,
					  "isSuccessful": false,
					  "message": error.data.message
					};
					$log.log("Failure receive. Receive response=", receiveResponse);
					
					flash.error = Localization.resolve("assetManagement.item.receive.fail", "Unsuccessfully receive item.");
					$scope.receiveRequest.title = "";
				});
			};
		$log.log("Leaving ReceiveCtrl");
				
	}]);
    
});