
define(['angular', 'lodash','jquery','cataloguing/cataloguing'], 
	function (angular, _, $) {
    var module = angular.module('integral.cataloguing');
    module.controller('CataloguingIndexController', ['$scope', '$state', '$rootScope', function($scope, $state, $rootScope, foundationConfig){        
        $scope.$state = $state;
        $rootScope.$on('m:desktopMode', function(event, option) {
            $scope.isDesktopMode = option;
        });
    }]);
    module.controller('CataloguingHomeController', ['$scope', function($scope){
    }]);
});
