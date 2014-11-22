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
			resourceDescriptorIdentifier: undefined,
			foreignCost : undefined,
			localCost : undefined
		}
		$scope.createRequest = createRequest;       

		$scope.create = function() {
			$log.log("Entering create ctrl");
				var createPromise = CreateItemService.create($scope.createRequest);
				createPromise.then(function(createResponse) {
					$log.log("Successfully create. Create response=", createResponse);            
		   
					flash.success = createResponse.message;           
					$scope.successCreate(createResponse);
				   
					$scope.createRequest={
						resourceDescriptorIdentifier: "",
						foreignCost: "",
						localCost: ""							
					};
				}, function(error) {
					$log.log("Failed to create. Error=", error);
					$scope.isDisallowedToPerformRelease = false;
					flash.error = error.data.message;
					var item = {"resourceDescriptorIdentifier":$scope.createRequest.resourceDescriptorIdentifier,
								"foreignCost":$scope.createRequest.foreignCost,
								"localCost":$scope.createRequest.localCost};  
					var createResponse = {
					  "item":item,
					  "successful": false,
					  "message": error.data.message
					};
					$log.log("Failure create. Create response=", createResponse);
			
					$scope.createRequest.resourceDescriptorIdentifier = "";
				});
			};

			$scope.successCreate = function(createResponse) {
				$log.log("Entering successCreate ctrl. Parameter passed: ", "create response: ", createResponse);
				$scope.createResponse = createResponse;
				//flash.success = createResponse.screenMessage;      
				$scope.getItemInformation($scope.createRequest, createResponse);            
			$log.log("Leaving successCreate ctrl");
		}  
		$log.log("Leaving CreateCtrl");
				
	}]);
    
});