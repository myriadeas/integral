
define(['angular', 'lodash','jquery','assetManager/assetManager'], 
	function (angular, _, $) {
    var module = angular.module('integral.assetManager');
    module.controller('AssetManagerIndexController', ['$scope', '$state', '$rootScope', 'DeleteItemService', 'TransferItemService', function($scope, $state, $rootScope, foundationConfig, DeleteItemService, TransferItemService){        
        $scope.$state = $state;
        $rootScope.$on('m:desktopMode', function(event, option) {
            $scope.isDesktopMode = option;
        });
		
		// $scope.myData = FindItemsForTransferService.getItemsForTransfer();
		// $scope.gridOptions = { data: 'myData' };

	
    }]);
    module.controller('AssetManagerHomeController', ['$scope', function($scope){
    }]);
});
