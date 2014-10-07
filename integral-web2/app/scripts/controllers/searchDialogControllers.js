'use strict';

function SearchDialogController($scope, $log, dialog, Restangular, Localization){
	
	$scope.search = function(searchDomain) {
		var searchHref = $scope.selectedSearchCriteria.href;
		var searchText = $scope.searchText;		
		$log.log("Search Href: ", searchHref);
		$log.log("Search Text : ", searchText);
		//plularize
		Restangular.oneUrl(searchDomain, searchHref).get({search:searchText}).then(function(response){
			if (angular.isUndefined(response.content)){
				var results = {};
				results[0] = response;
				$scope[searchDomain + 's'] = results;	
				
			} else {
				$scope[searchDomain + 's'] = response.content;
				
			}			
			$scope.showResult = true;
			
		});	
	}
	
	
	$scope.searchCriteria = {};
	
	//"http://localhost:8080/spring-jpa-data/rest/glbrnc/search"
    
    var searchUrl = dialog.options.domain + "s";
    if(!angular.isUndefined(dialog.options.domain)) {
        Restangular.one(searchUrl, 'search').get().then(function(response){
            var links = [];
            $.each(response._links, function(key, link) {
                var rel = key;
                link.rel = Localization.resolve(domainName+ '.' + rel, rel);		
                links.push(link);
            });
            $scope.searchCriteriaItems = links;
            $scope.selectedSearchCriteria = links[1];
              
            // hide second scroll on body
            var body = angular.element(document.getElementsByTagName('body')[0]);
            body.addClass('modal-open');
                
            $scope.select = function(result){
                body.removeClass('modal-open');
                dialog.close(result);
            };
            
            
        });	
    }
	
	
	
}

