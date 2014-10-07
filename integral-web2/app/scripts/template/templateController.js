'use strict';

function HeaderCtrl($scope, $state, $rootScope, TOKEN, $dialogs) {
	$scope.template = {
		url : "views/templates/header.html"
	};
    $scope.$state = $state;
	$scope.username = TOKEN.username;
	$scope.isLoggedIn  = false;
	$scope.isLoggedOut  = true;

/*    
	var count = 1;
	$scope.modeType = 'normal';
	$scope.desktopMode = function() {
		count = count + 1;	
		if ( count % 2 == 0) {
			$rootScope.$emit('m:desktopMode', true);
			var modeType = 'desktop';
			
		} else {
			$rootScope.$emit('m:desktopMode', false);
			modeType = 'normal';
		}		
		$scope.modeType = modeType;		
    }
	*/
	if ($scope.username != null) {
		$scope.isLoggedIn  = true;
		$scope.isLoggedOut  = false;
	}
	
}

function BreadcrumbCtrl($rootScope, $scope, $log, $location, Navigation) {
	var breadcrumbs = Navigation.getBreadcrumb($location.path());
	$rootScope.$on('$locationChangeSuccess', function(event, newUrl, oldUrl) {
		$scope.breadcrumbs = Navigation.getBreadcrumb($location.path());
		$rootScope.$broadcast("event:breadcrumbsUpdateSuccess", $scope.breadcrumbs);
		$log.log("broadcast breadcrumbs update success");
	});
	$rootScope.$on('event:breadcrumbsCustom', function(event, breadcrumbs){
		$scope.breadcrumbs = breadcrumbs;
	});
	$scope.breadcrumbs = breadcrumbs;
	$log.log("breadcrumbs=", breadcrumbs);
	$scope.template = {
		url : "templates/breadcrumb.html"
	};
}


function FooterCtrl($scope, $state, $rootScope) {
	$scope.template = {
		url : "views/templates/footer.html"
	};
    
    $rootScope.$on('m:desktopMode', function(event, option) {
        $scope.isDesktopMode = option;
    });
    $scope.$state = $state;
}

function NavigationCtrl($rootScope, $scope, $log, $http) {
	
	var navigationBar = false;
	$scope.navigationBar;
	$http({
		method : 'GET',
		url : 'profile.jsp'
	}).success(function(data, status, headers, config) {
		if (data != null){
			$scope.navigationBar = true;
		}
	}).error(function(data, status, headers, config) {
		// called asynchronously if an error occurs
		// or server returns response with an error status.
	});
	
	$scope.template = {
		url : "templates/navigationBar.html"
	};
}