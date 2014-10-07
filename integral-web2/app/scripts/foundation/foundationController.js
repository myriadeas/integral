
define(['app','lodash','jquery'], function (integralApp, _, $) {
    integralApp.controller('FoundationIndexController', ['$scope', '$state','$rootScope','foundationConfig',  function($scope, $state, $rootScope, foundationConfig){        
        $scope.$state = $state;
        
        $rootScope.$on('m:desktopMode', function(event, option) {
            $scope.isDesktopMode = option;
        });
    }]);
    integralApp.controller('FoundationHomeController', ['$scope','$window','$http', function($scope, $window, $http){
        $scope.print = function(url) {
            var printWindow = $window.open(url, 'printwindow', 'location=no,scrollbars=yes,dependent=yes');
        }
    }]);
	integralApp.controller('NewRegisterController', ['$scope', '$stateParams', '$location', 'Restangular', 'ErrorBinder', 'Tooltips', 'Localization', 
		function($scope, $stateParams, $location, Restangular, ErrorBinder, Tooltips, Localization){
		
        $scope.repository = $scope.entityDomainClass.getRepository();
        $scope[$scope.entityName] = $scope.entityDomainClass.clone();
        $scope.title = Localization.resolve($scope.entityName  + '.create');
		$scope[$scope.entityName].membershipDate = new Date();
	
		$scope.hasFocus  = function(hasFocus, field) {
			Tooltips.show(hasFocus, field, $scope);
		}
		
		$scope.datepickers = {
			membershipDate: false,
			membershipExpiryDate: false
		}
		  
		$scope.open= function($event, selectedDateField){
			$event.stopPropagation();
			$scope.opened = true;
			$scope.datepickers[selectedDateField] = true;
		}
		
		$scope.actions = "createOrSave";
		
		 $scope.$on("event:ModalSelectedItems", function(event, response){
			$scope[$scope.entityName][response.columnName] = response.items[0];
		});
		
		$scope.adminCreate= function(){
			$location.path("/patron/adminCreate");
		}
    }]);
});