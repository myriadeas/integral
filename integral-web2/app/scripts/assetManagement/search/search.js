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

    integralApp.factory('ItemSearchQuery', function($q, $http, Config) {
        var solrUrl = Config.getSolrUrl();
        var itemSearchQuery = {
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
                var itemSearchQuery = this;
                var deferred = $q.defer();
                if(this.isNextPage()) {
                    this.page++;
                    this.entity.search(this.link, {search: this.query, page: this.page}).then(function(searchResult){
                        if(searchResult.length > 0) {
                            itemSearchQuery.searchResult = itemSearchQuery.searchResult.concat(searchResult);
                        }
                        deferred.resolve(itemSearchQuery);
                    });
                }
                return deferred.promise;
            },
            search: function() {      
                var itemSearchQuery = this;
                var deferred = $q.defer();
                this.page = 0;
                this.searchResult = [];
                $http({
                    url: integralMysticBaseUrl + "/services/item/" + this.link + "/" + this.query,
                    params: {
                       // 'json.wrf': 'JSON_CALLBACK'
                    }
                }).success(function (data) {
	                for (var i in data) {
	                	var itemDisplay = {};
	 	                itemDisplay.itemIdentifier = data[i].itemIdentifier
	 	                itemDisplay.resourceDescriptorIdentifier = data[i].resourceDescriptorIdentifier;
	 	                if (data[i].isbn){
	   	 	                var isbn = data[i].isbn;
	   	 	                itemDisplay.isbn = isbn.join(', ');
   	 	            	}
	 	                itemDisplay.title = data[i].title;
	 	                itemDisplay.author = data[i].author;
						if ( data[i].itemIdentifier > 0) {
	 	                itemSearchQuery.searchResult.push(itemDisplay);
						}
	                }
                	deferred.resolve(itemSearchQuery);  
                });
                return deferred.promise;    
            },
            isNextPage: function() {
                return ((this.searchResult.length >= this.page + 1) * this.defaultPageSize);
            }
            
        };
        return itemSearchQuery;
    });
    
    integralApp.directive('mCatalogSearchForm', function() {
        return {
            restrict : 'E',
            priority : 0,
            templateUrl : 'scripts/assetManagement/search/search.html',
            scope : {
                entity : '=',
                selectedItems : '='
            },
            controller : function($scope, ItemSearchQuery, SearchGridOptions, flash, Localization) {
				var solrLink = 'querySolr';
                $scope.query = {};
                angular.copy(ItemSearchQuery, $scope.query);
                $scope.query.setEntity($scope.entity);
			          
				var queryLinks = [{ link : 'getItemListByTitle', name : 'Find Items By Title' }, 
								  { link : 'getItemListByAuthor', name : 'Find Items By Author' },
								  { link : 'getItemListByIsbn', name : 'Find Items By ISBN' }
								  ];
				 $scope.queryLinks = queryLinks;
				
				$scope.query.setLink(queryLinks[0].link).setQuery("%");
				
                $scope.search = function() {
					$scope.query.search().then(function(itemSearchQuery){
						$scope.searchResult = itemSearchQuery.searchResult;//HACK - update binding
						if($scope.searchResult.length == 0) {
							flash.info = Localization.resolve('search.noresultfound');
						}
					});
					
                }
                $scope.nextPage = function() {
					if ($scope.query.link === solrLink){
	                    $scope.query.nextPage().then(function(itemSearchQuery){
	                        $scope.searchResult = itemSearchQuery.searchResult;//HACK - update binding
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
    
    
});
