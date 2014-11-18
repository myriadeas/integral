'use strict';
define(['angular', 'lodash','jquery','assetManager/assetManager'], function (angular, _, $) {
    var module = angular.module('integral.assetManager');
	 module.controller('CreateController', ['$scope', '$log', '$stateParams', '$location', 'Restangular', 'CreateItemService', 'Utility', 'DatetimeUtil', 'ItemInformationService', 'UserTransactionHistory', 'ModalPopup', 'flash',
	function($scope, $log, $stateParams, $location, Restangular, CreateItemService, Utility, DatetimeUtil, ItemInformationService, UserTransactionHistory, ModalPopup, flash){ 
		$log.log("Entering CreateCtrl", $stateParams);
		// Initialize Code 
		
		var defaultMessage = {
			error : "",
			createResponse : ""
		};
		var createRequest = {
			itemIdentifier : undefined,
			resourceDescriptorIdentifier: undefined
		};

		$scope.createRequest = createRequest;       
    
		$scope.disableCreate = function() {
			$scope.isDisallowedToPerformCreate = true;
		}

		$scope.enableCreate = function() {
			$scope.isDisallowedToPerformCreate = false;
		}

		$scope.create = function() {
      $log.log("Entering create ctrl");
			$scope.disableCreate();
			var createPromise = CreateItemService.create($scope.createRequest);
			createPromise.then(function(createResponse) {
				$log.log("Successfully create. Create response=", createResponse);            
       
        flash.success = createResponse.message;           
				$scope.successCreate(createResponse);
        $scope.createRequest.itemIdentifier = "";
			}, function(error) {
				$log.log("Failed to create. Error=", error);
				$scope.isDisallowedToPerformRelease = false;
				flash.error = error.data.message;
        var item = {"itemIdentifier":$scope.createRequest.itemIdentifier};  
        var createResponse = {
          "item":item,
          "successful": false,
          "message": error.data.message
        };
        $log.log("Failure create. Create response=", createResponse);
				var transaction = {
					transactionId : $scope.transactionId,
					createResponse : createResponse
				};
				
        $scope.createRequest.itemIdentifier = "";
			});
		};

		$scope.successCreate = function(createResponse) {
			$log.log("Entering successCreate ctrl. Parameter passed: ", "create response: ", createResponse);
			$scope.createResponse = createResponse;
			//flash.success = createResponse.screenMessage;      
			$scope.getItemInformation($scope.createRequest, createResponse);            
      $log.log("Leaving successCreate ctrl");
		}

		$scope.getItemInformation = function(createRequest, createResponse) {
			$log.log("Entering getItemInformation ctrl. Parameter passed: ", "create request: ", createRequest, " create response: ", createResponse);
			var itemId = createRequest.itemIdentifier;
			
		}
    
		$log.log("Leaving CreateCtrl");
				
	}]);
    
});