
define(['angular', 'lodash','jquery','./identityAccessModule','./user/user'], 
	function (angular, _, $) {
    var module = angular.module('integral.identityAccess');
    module.controller('IdentityAccessIndexController', ['$scope', '$state', '$rootScope', function($scope, $state, $rootScope){        
        $scope.$state = $state;
        
        $rootScope.$on('m:desktopMode', function(event, option) {
            $scope.isDesktopMode = option;
        });
    }]);
    module.controller('IdentityAccessHomeController', ['$scope', function($scope){
    }]);
});