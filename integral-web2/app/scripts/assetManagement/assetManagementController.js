
define(['angular', 'lodash','jquery','assetManagement/assetManagement'], 
	function (angular, _, $) {
    var module = angular.module('integral.assetManagement');
    module.controller('AssetManagementIndexController', ['$scope', '$state', '$rootScope', 'DeleteItemService', 'TransferItemService', function($scope, $state, $rootScope, foundationConfig, DeleteItemService, TransferItemService){        
        $scope.$state = $state;
        $rootScope.$on('m:desktopMode', function(event, option) {
            $scope.isDesktopMode = option;
        });
		
		// $scope.myData = FindItemsForTransferService.getItemsForTransfer();
		// $scope.gridOptions = { data: 'myData' };

	
    }]);
    module.controller('AssetManagementHomeController', ['$scope', function($scope){
    }]);
});
