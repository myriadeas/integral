
define(['angular', 'lodash','jquery','cataloguing2/cataloguing2'], 
	function (angular, _, $) {
    var module = angular.module('integral.cataloguing2');
    module.controller('Cataloguing2IndexController', ['$scope', '$state', '$rootScope', function($scope, $state, $rootScope, foundationConfig){        
        $scope.$state = $state;
        $rootScope.$on('m:desktopMode', function(event, option) {
            $scope.isDesktopMode = option;
        });
    }]);
    module.controller('Cataloguing2HomeController', ['$scope', function($scope){
    }]);
});
