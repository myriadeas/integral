
define(['app'], function (integralApp) {
/* Directives */
integralApp.directive('modal', function() {
	return {
		restrict : 'E',
		template : '<a href="" ng-click="open()" tooltip-html-unsafe="Details view">{{data.itemIdentifier}}</a>',
		scope : {
			data: '=',
			templateUrl : '=',
			controller: '=',
		},
		controller: function($scope, $log, $attrs, $modal){
      $log.log("$scope=", $scope.data);
			$scope.open = function () {
				$log.log("data=", $scope.data);
				var modalInstance = $modal.open({
					templateUrl: $attrs.templateUrl,
					controller: $attrs.controller,
					resolve: {
						data: function() {
							return $scope.data;
						}
					}
				});
			}
		},
		link : function(scope, elem, attrs) {	
      console.log("scope=", scope);
		}
	}
});

});
