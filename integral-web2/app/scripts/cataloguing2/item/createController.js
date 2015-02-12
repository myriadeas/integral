'use strict';
define(['angular', 'lodash','jquery','cataloguing2/cataloguing2'], function (angular, _, $) {
    var module = angular.module('integral.cataloguing2');
	 module.controller('CreateItemController', ['$scope', '$log', '$stateParams', '$location', 'Restangular', 'CreateItemService', 'Utility', 'DatetimeUtil', 'ItemInformationService', 'UserTransactionHistory', 'ModalPopup', 'flash','Localization',
	function($scope, $log, $stateParams, $location, Restangular, CreateItemService, Utility, DatetimeUtil, ItemInformationService, UserTransactionHistory, ModalPopup, flash, Localization){ 
		$log.log("Entering CreateItemCtrl", $stateParams);
		// Initialize Code 
	
		var defaultMessage = {
			error : "",
			createResponse : ""
		};
		
		var createRequest = {
			resourceDescriptorIdentifier: undefined,
			foreignCost : undefined,
			localCost :  undefined	
		};
		$scope.createRequest = createRequest;  
		
		$scope.template = {
			url : "views/cataloguing2/item/transactionLog.html"
		};
		
		$scope.isNewTransaction = function() {
			return $stateParams.transactionId == undefined;
		}

		$scope.getNewTransactionId = function() {
			var prefix = "_R";
			return transactionId = prefix + DatetimeUtil.now();
		}
		
		if ($scope.isNewTransaction()) {
			$log.log("New Create Transaction. Creating transaction id.");
			var transactionId = $scope.getNewTransactionId();
			$log.log("Redirect to create with transactionId=", transactionId);
			$location.path("cataloguing2/item").search("transactionId",
					transactionId);
		} else {
			$log.log("Existing Transaction with transactionId=", $stateParams.transactionId);
			$scope.transactionId = $stateParams.transactionId;
		}
		
		$scope.create = function() {
			$log.log("Entering create item ctrl");	
				var createPromise = CreateItemService.create($scope.createRequest);
				createPromise.then(function(createResponse) {
					$log.log("Successfully create. Create response=", createResponse);            
		   
					$scope.showTransactionLog = createResponse;
					flash.success = Localization.resolve("accessionMaintenance.item.create.success", "Successfully create accession.", createResponse);
					$scope.getItemInformation($scope.createRequest, createResponse);  
					//$scope.successCreate(createResponse);				   			
					$scope.createRequest={
						resourceDescriptorIdentifier: "",
						foreignCost: "",
						localCost: ""							
					};			
					
					//$location.path('cataloguing2/item/' + parseInt(createResponse.id) + '/edit');
					
				}, function(error) {
					$log.log("Failed to create. Error=", error);
					
					flash.error = Localization.resolve("accessionMaintenance.item.create.fail", "Unsuccessfully create item.");
					
					var createResponse = {
					  "id":null,
					  "isSuccessful": false,
					  "message": error.data.message
					};
					$log.log("Failure create. Create response=", createResponse);
					var transaction = {
						transactionId : $scope.transactionId,
						createResponse : createResponse
					};
					$scope.updateTransactionLog(transaction);
					$scope.createRequest.resourceDescriptorIdentifier = "";
				});
			};
			
			
			/**********************************************************************/
			
			
		$scope.getItemInformation = function(createRequest, createResponse) {
			$log.log("Entering getItemInformation ctrl. Parameter passed: ", "create request: ", createRequest, " create response: ", createResponse);
			var id = createResponse.id;
			var itemRest = Restangular.one('items', createResponse.id);
			//var resourceDescriptorSolrRest = Restangular.one('item', itemId);
			$log.log("itemRest=" + itemRest);
			var modifiedCreateResponse = createResponse;
		//CreateItemService.getItemInformation(itemId).then(function(itemInformationResponse) {
		itemRest.get().then(function(itemInformationResponse){
			
			modifiedCreateResponse['itemIdentifier'] = itemInformationResponse.itemIdentifier;
			modifiedCreateResponse['resourceDescriptorIdentifier'] = itemInformationResponse.resourceDescriptorIdentifier;
		//	modifiedCreateResponse['title'] = itemInformationResponse.title;
     		var transaction = {
				transactionId : $scope.transactionId,
				modifiedCreateResponse : modifiedCreateResponse,
				itemInformationResponse : itemInformationResponse
		
			};	
		    CreateItemService.getResourceDescriptorSolrInformation(itemInformationResponse.resourceDescriptorIdentifier).then(function(resourceDescriptorSolrInformationResponse) {
			 transaction.modifiedCreateResponse.title = resourceDescriptorSolrInformationResponse.title;
			 $scope.updateTransactionLog(transaction);
			});

			
			
			$log.log("transaction", transaction);
	
			});

			$log.log("Leaving getItemInformation ctrl");
		}

		$scope.updateTransactionLog = function(transaction) {
			$log.log("Entering updateTransactionLog ctrl. Parameter passed: ", "transaction:", transaction);

			$scope.transactions = CreateItemService.updateTransactionLog(transaction);       
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

		
		$scope.updateTransactionLog({transactionId : $scope.transactionId, modifiedCreateResponse : undefined});
		
		$scope.clear = function(){			
			//$location.url($location.path());
			UserTransactionHistory.clear();
			$scope.updateTransactionLog({transactionId : $scope.transactionId});
					
			flash.info = Localization.resolve("accessionMaintenance.item.transaction.clear", "Previous record has been clear.");
			$log.log("Leaving newPatron");
		}

		$log.log("Leaving Create Item Ctrl");
				
	}]);

    
});