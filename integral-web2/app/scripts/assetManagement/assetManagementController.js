
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
	
	module.controller('ItemSearchCtrl', ['$scope', '$stateParams', '$window', 'flash', function($scope, $stateParams, $window, flash){
		$scope.selectedItems = [];
		
		$scope.view = function() {
			$.each($scope.selectedItems, function(index, selection) {
				$window.open("#" + selection.getViewLink() , "_blank");
			});
		}
		$scope.edit = function() {
			$.each($scope.selectedItems, function(index, selection) {
				$window.open("#" + selection.getEditLink() , "_blank");
			});
		}
		$scope.create = function(){
			$window.open("#" + $scope.entityDomainClass.getCreateLink(), "_blank");
		}
    }]);
});
