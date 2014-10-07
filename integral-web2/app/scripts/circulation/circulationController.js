
define(['angular', 'lodash','jquery','circulation/circulation', 'circulation/checkout/checkoutController', 'circulation/checkout/checkoutService', 
	'circulation/checkin/checkinController', 'circulation/checkin/checkinService', 'circulation/renew/renewController', 'circulation/renew/renewService'
  ], 
	function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.controller('CirculationIndexController', ['$scope', '$state', '$rootScope', function($scope, $state, $rootScope){        
        $scope.$state = $state;
        
        $rootScope.$on('m:desktopMode', function(event, option) {
            $scope.isDesktopMode = option;
        });
    }]);
    module.controller('CirculationHomeController', ['$scope', function($scope){
    }]);
});