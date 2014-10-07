define(['app', 'lodash'], function (integralApp, _) {
    integralApp.factory('SearchGridOptions', function(){
        return  {
            data: 'query.searchResult',
            showColumnMenu: true,
            showSelectionCheckbox: true,
            enablePaging: false,
            showFooter: true,
            totalServerItems: 'query.searchResult.length',
            multiSelect: true,
            enableColumnResize: true,
            enableColumnReordering: true,
            enablePinning: false,
            columnDefs: 'query.entity.getSearchColumnDefinition()',
            virtualizationThreshold: 5
        }
    });
    integralApp.factory('ModalSearchGridOptions', function(SearchGridOptions){
        return  angular.extend(this, SearchGridOptions, {
            showSelectionCheckbox: false,
            multiSelect: false,
            enablePinning: false,
            columnDefs: 'query.entity.getSearchColumnDefinitionForModal()'
        });
    });
    integralApp.factory('SearchQuery', function($q, $http, Config) {
        var solrUrl = Config.getSolrUrl();
        var searchQuery = {
            defaultPageSize: 10,
            query: "%",
            page: 0,
            searchResult: new Array(),
            setEntity: function(entity) {
                this.entity = entity;
                return this;
            },
            setLink: function(link) {
                this.link = link;
                return this;
            },
            setQuery: function(query) {
                this.query = query;
                return this;
            },
            nextPage: function() {
                var searchQuery = this;
                var deferred = $q.defer();
                if(this.isNextPage()) {
                    this.page++;
                    this.entity.search(this.link, {search: this.query, page: this.page}).then(function(searchResult){
                        if(searchResult.length > 0) {
                            searchQuery.searchResult = searchQuery.searchResult.concat(searchResult);
                        }
                        deferred.resolve(searchQuery);
                    });
                }
                return deferred.promise;
            },
            search: function() {      
                var searchQuery = this;
                var deferred = $q.defer();
                this.page = 0;
                this.searchResult = [];
                this.entity.search(this.link, {search: this.query}).then(function(searchResult){
                    searchQuery.searchResult = searchResult;
                    deferred.resolve(searchQuery);       
                });
                return deferred.promise;
            },
            solrSearch: function() {    
                var searchQuery = this;
                var deferred = $q.defer();
                this.page = 0;
                this.searchResult = [];
                $http({
                    url: solrUrl.search,
                    params: {
                        'json.wrf': 'JSON_CALLBACK',
                         'q': this.query
                    }
                }).success(function (data) {
                	var docs = data.response.docs;
	                for (var i in docs) {
	                	material = {};
	 	                material.id = parseInt(docs[i].id);
	 	                material.materialNo = docs[i].id;
	 	                if (docs[i].isbn){
	   	 	                var isbn = docs[i].isbn;
	   	 	                material.isbn = isbn.join(', ');
   	 	            	}
	 	                material.title = docs[i].title_full;
	 	                material.author = docs[i].author;
	 	                material.edition = docs[i].edition;
	 	                searchQuery.searchResult.push(material);
	                }
                	deferred.resolve(searchQuery);  
                });
                return deferred.promise;
          
            },
            isNextPage: function() {
                return ((this.searchResult.length >= this.page + 1) * this.defaultPageSize);
            }
            
        };
        return searchQuery;
    });
    
    integralApp.directive('mSearchForm', function() {
        return {
            restrict : 'E',
            priority : 0,
            templateUrl : 'scripts/directives/search/search.html',
            scope : {
                entity : '=',
                selectedItems : '='
            },
            controller : function($scope, SearchQuery, SearchGridOptions, flash, Localization) {
				var solrLink = 'querySolr';
                $scope.query = {};
                angular.copy(SearchQuery, $scope.query);
                $scope.query.setEntity($scope.entity);
                $scope.entity.getQueryLinks().then(function(queryLinks) {
                    $scope.queryLinks = queryLinks;
					if ($scope.entity.name === 'material'){
						var QueryLink = function (entityName, link) {
	                        this.link = link;
	                        this.name = Localization.resolve(entityName + '.' + link, link);
	                    }
	                    var queryLink = new QueryLink("material", solrLink);
	                    queryLinks.push(queryLink);
					}	
                    $scope.query.setLink(queryLinks[0].link).setQuery("%");
                    $scope.search();
                });
                $scope.search = function() {
                	console.log ($scope.query.link, solrLink,$scope.query.link === solrLink);
					if ($scope.query.link === solrLink){
                    	$scope.query.solrSearch().then(function(searchQuery){
        	                $scope.searchResult = searchQuery.searchResult;//HACK - update binding
        	                if($scope.searchResult.length == 0) {
        	                    $scope.message = Localization.resolve('search.noresultfound');
        	                }
        	            });
                    } else {
	                    $scope.query.search().then(function(searchQuery){
	                        $scope.searchResult = searchQuery.searchResult;//HACK - update binding
	                        if($scope.searchResult.length == 0) {
	                            flash.info = Localization.resolve('search.noresultfound');
	                        }
	                    });
					}
                }
                $scope.nextPage = function() {
					if ($scope.query.link === solrLink){
	                    $scope.query.nextPage().then(function(searchQuery){
	                        $scope.searchResult = searchQuery.searchResult;//HACK - update binding
	                    });
					}
                }
                $scope.searchGridOptions = {};
                angular.copy(SearchGridOptions, $scope.searchGridOptions);
                $scope.searchGridOptions.selectedItems = $scope.selectedItems;
                $scope.$on('ngGridEventScroll', function(){
                    $scope.nextPage();
                });
            }
        }
    });
    
    integralApp.controller('SearchInputModalController', ['$scope', 'entity', 'SearchQuery','ModalSearchGridOptions', '$modalInstance','flash','Localization', function($scope, entity, SearchQuery, ModalSearchGridOptions, $modalInstance, flash, Localization){
		var solrLink = 'querySolr';
        $scope.query = {};
        angular.copy(SearchQuery, $scope.query);
        $scope.entity = entity;
        $scope.selectedItems = [];
        $scope.query.setEntity($scope.entity);
        $scope.entity.getQueryLinks().then(function(queryLinks) {
            $scope.queryLinks = queryLinks;
			if ($scope.entity.name === 'material'){
				var QueryLink = function (entityName, link) {
	                this.link = link;
	                this.name = Localization.resolve(entityName + '.' + link, link);
	            }
	            var queryLink = new QueryLink("material", solrLink);
	            queryLinks.push(queryLink);
			}
            $scope.query.setLink(queryLinks[0].link);
        });
        $scope.search = function(SearchQuery) {
            $scope.message = "";
            console.log ("$scope.query.link === solrLink", $scope.query.link === solrLink);
			if ($scope.query.link === solrLink){
            	$scope.query.solrSearch().then(function(searchQuery){
	                $scope.searchResult = searchQuery.searchResult;//HACK - update binding
	                if($scope.searchResult.length == 0) {
	                    $scope.message = Localization.resolve('search.noresultfound');
	                }
	            });
            } else {
	            $scope.query.search().then(function(searchQuery){
	                $scope.searchResult = searchQuery.searchResult;//HACK - update binding
	
	                if($scope.searchResult.length == 0) {
	                    $scope.message = Localization.resolve('search.noresultfound');
	                }
	            });
			}
        }
        $scope.nextPage = function() {
			if ($scope.query.link !== solrLink){
	            $scope.query.nextPage().then(function(searchQuery){
	                $scope.searchResult = searchQuery.searchResult;//HACK - update binding
	            });
			}
        }
        $scope.searchGridOptions = {};
        angular.copy(ModalSearchGridOptions, $scope.searchGridOptions);
        $scope.searchGridOptions.selectedItems = $scope.selectedItems;
        $scope.$on('ngGridEventScroll', function(){
            $scope.nextPage();
        });
        $scope.select = function() {
        	if ($scope.query.link === solrLink){
        		$scope.query.entity.search("findById", {search: $scope.selectedItems[0].id}).then(function(result){
        			$modalInstance.close(result[0]);
        		});
        	}else{
        		$modalInstance.close($scope.selectedItems[0]);
        	}
        }
        
        $scope.cancel = function() {
            $modalInstance.close('cancel');
        }
    }]);
    
    integralApp.directive('mSearchInput', function() {
        return {
            restrict : 'E',
            priority : 0,
            templateUrl : 'scripts/directives/search/searchInput.html',
            transclude: true,
            scope : {
                entityName : '@',
                ngModel : '='
            },
            controller : function($scope, $injector, SearchQuery, ModalSearchGridOptions, $modal) {
                $scope.entity = $injector.get($scope.entityName.capitalize());
                $scope.openSearchModal = function() {        
                    var modalInstance = $modal.open({
                            templateUrl: "scripts/directives/search/searchModal.html",                    
                            controller: 'SearchInputModalController',
                            size: 'lg',
                            resolve: {
                                entity: function() {
                                    return $scope.entity;
                                }
                            }
                    });                    
                    modalInstance.result.then(function (modalSelectedItem) {
						if (modalSelectedItem === 'cancel'){
							$scope.$emit("mSearchInput:cancel", $scope.ngModel);
						}else {
	                        $scope.ngModel = modalSelectedItem;
							$scope.$emit("mSearchInput:selectItem", modalSelectedItem);
						}
                    }, function () {
                    });
                }
                
            }
        }
    });
    
});
