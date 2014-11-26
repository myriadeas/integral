
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
});
