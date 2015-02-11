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

    integralApp.factory('ResourceDescriptorSearchQuery', function($q, $http, Config) {
        var solrUrl = Config.getSolrUrl();
		var integralMysticBaseUrl = Config.getMysticBaseUrl();
        var resourceDescriptorSearchQuery = {
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
                var resourceDescriptorSearchQuery = this;
                var deferred = $q.defer();
                if(this.isNextPage()) {
                    this.page++;
                    this.entity.search(this.link, {search: this.query, page: this.page}).then(function(searchResult){
                        if(searchResult.length > 0) {
                            resourceDescriptorSearchQuery.searchResult = resourceDescriptorSearchQuery.searchResult.concat(searchResult);
                        }
                        deferred.resolve(resourceDescriptorSearchQuery);
                    });
                }
                return deferred.promise;
            },
            search: function() {      
                var resourceDescriptorSearchQuery = this;
                var deferred = $q.defer();
                this.page = 0;
                this.searchResult = [];
				console.log("Testing!!");
                $http({
                    url: integralMysticBaseUrl + "/services/resourceDescriptor/" + this.link + "/" + this.query,

                    params: {
                       // 'json.wrf': 'JSON_CALLBACK'
                    }
                }).success(function (data) {
	                for (var i in data) {
	                	var resourceDescriptorSolr = {};
	 	                resourceDescriptorSolr.resourceDescriptorId = data[i].id;
	 	                if (data[i].isbn){
	   	 	                var isbn = data[i].isbn;
	   	 	                resourceDescriptorSolr.isbn = isbn.join(', ');
   	 	            	}
	 	                resourceDescriptorSolr.title = data[i].title;
	 	                resourceDescriptorSolr.author = data[i].author;
						if ( data[i].id > 0) {
	 	                resourceDescriptorSearchQuery.searchResult.push(resourceDescriptorSolr);
						}
	                }
                	deferred.resolve(resourceDescriptorSearchQuery);  
                });
                return deferred.promise;    
            },
            isNextPage: function() {
                return ((this.searchResult.length >= this.page + 1) * this.defaultPageSize);
            }
            
        };
        return resourceDescriptorSearchQuery;
    });
 
  
/*######################################################################################################################*/


    integralApp.controller('ResourceDescriptorSearchInputModalController', ['$scope', 'entity', 'ResourceDescriptorSearchQuery','ModalSearchGridOptions', '$modalInstance','flash','Localization', function($scope, entity, ResourceDescriptorSearchQuery, ModalSearchGridOptions, $modalInstance, flash, Localization){
        $scope.query = {};
        angular.copy(ResourceDescriptorSearchQuery, $scope.query);
        $scope.entity = entity;
        $scope.selectedItems = [];
        $scope.query.setEntity($scope.entity);
			          
		var queryLinks = [{ link : 'getResourceDescriptorSolrListByTitle', name : 'Find Material By Title' }, 
						  { link : 'getResourceDescriptorSolrListByAuthor', name : 'Find Material By Author' },
						  { link : 'getResourceDescriptorSolrListByIsbn', name : 'Find Material By ISBN' }
						  ];
		$scope.queryLinks = queryLinks;
		
		$scope.query.setLink(queryLinks[0].link).setQuery("%");
        $scope.search = function(ResourceDescriptorSearchQuery) {
            $scope.message = "";

	            $scope.query.search().then(function(resourceDescriptorSearchQuery){
	                $scope.searchResult = resourceDescriptorSearchQuery.searchResult;//HACK - update binding
	
	                if($scope.searchResult.length == 0) {
	                    $scope.message = Localization.resolve('search.noresultfound');
	                }
	            });
			
        }
        $scope.nextPage = function() {}
        $scope.searchGridOptions = {};
        angular.copy(ModalSearchGridOptions, $scope.searchGridOptions);
        $scope.searchGridOptions.selectedItems = $scope.selectedItems;
        $scope.$on('ngGridEventScroll', function(){
            $scope.nextPage();
        });
        $scope.select = function() {

        	$modalInstance.close($scope.selectedItems[0]);
        }
        
        $scope.cancel = function() {
            $modalInstance.close('cancel');
        }
    }]);
    
    integralApp.directive('mResourceDescriptorSearchInput', function() {
        return {
            restrict : 'E',
            priority : 0,
            templateUrl : 'scripts/directives/search/searchInput.html',
            transclude: true,
            scope : {
                entityName : '@',
                ngModel : '='
            },
            controller : function($scope, $log, $injector, ResourceDescriptorSearchQuery, ModalSearchGridOptions, $modal) {
                $scope.entity = $injector.get($scope.entityName.capitalize());
                $scope.openSearchModal = function() {        
                    var modalInstance = $modal.open({
                            templateUrl: "scripts/directives/search/searchModal.html",                    
                            controller: 'ResourceDescriptorSearchInputModalController',
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
	                        $scope.ngModel = modalSelectedItem.resourceDescriptorId;
							$log.log("modalSelectedItem=" + modalSelectedItem.resourceDescriptorId);
							$scope.$emit("mSearchInput:selectItem", modalSelectedItem);
						}
                    }, function () {
                    });
                }
                
            }
        }
    });
	
/*#########################################################################################################################*/
});
