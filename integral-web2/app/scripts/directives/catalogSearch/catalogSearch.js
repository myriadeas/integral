define(['app', 'lodash'], function (integralApp, _) {
    
    integralApp.directive('catalogSearch', ['Config', function (Config) {
	    var solrUrl = config.getSolrUrl();
        return {
	        scope: {
	            query: '=',
	            results: '&',
	            ngModel : '='
	        },
	        restrict: 'E',
	        controller: function ($scope, $http, $location, $log, $stateParams) {
	        	$scope.query = $stateParams.query;
	        	console.log($stateParams.query);
	        	$scope.search = function(){
	        		$location.search({"query": $scope.query});
	            }
	        	console.log("solrUrl", solrUrl.search);
	            if(!angular.isUndefined($scope.query)) {
	            	$http({
	                    //method: 'JSONP',
	                    url: solrUrl.search,
	                    params: {
	                        'json.wrf': 'JSON_CALLBACK',
	                         'q': $scope.query
	                    }
	                }).success(function (data) {
	                    $scope.$broadcast('event:solr-search-return', data);
	                }).error(function () {});
	            }
	            
	            $scope.$on('event:solr-search-return', function(event, data){
	                $log.log("on ", event);
	            	var docs = data.response.docs;
	                $scope.results.docs = docs;	
	            });
	        },
	        templateUrl : 'scripts/directives/catalogSearch/result.html'
	    };
	}]);
    
});
