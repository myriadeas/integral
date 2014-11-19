
define(['angular', 'lodash','jquery','assetManager/assetManager'], 
	function (angular, _, $) {
    var module = angular.module('integral.assetManager');
    module.controller('AssetManagerIndexController', ['$scope', '$state', '$rootScope', function($scope, $state, $rootScope, foundationConfig){        
        $scope.$state = $state;
        $rootScope.$on('m:desktopMode', function(event, option) {
            $scope.isDesktopMode = option;
        });
    }]);
    module.controller('AssetManagerHomeController', ['$scope', function($scope){
    }]);
	
	    module.controller('ItemCreateCtrl', ['$scope', '$injector', 'flash','Localization','$location', function($scope, $injector, flash, Localization, $location){
        $scope.repository = $scope.entityDomainClass.getRepository();
        $scope[$scope.entityName] = $scope.entityDomainClass.clone();
        $scope.entityTitle = Localization.resolve($scope.entityName  + '.create');
        $scope.actions = "createOrSave";
        $scope.$on('mFormActions:create', function(event, entity) {
            $location.path(entity.getViewLink());
        });	
		
		$scope.open= function($event, selectedDateField){
			$event.stopPropagation();
			$scope.opened = true;
			$scope[$scope.entityName].datepickers[selectedDateField].isOpened = true;
		}			
    }]);
});
