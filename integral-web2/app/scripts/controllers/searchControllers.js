
var SearchCtrl = function ($scope, $modal, $attrs, $route, $stateParams, $http, $location, $log, $rootScope, RepositoryRestangular, Localization, SelectionLookup, Lookup, $injector) {
	$log.log('entering SearchCtrl');
	
	$scope.$watch('inputItem', function(newValue) {
		try {
			$scope.$emit('manualInputItem', newValue);    	
		} catch (exception){
		}
    }, true);

	var domainName;
	var columnName;
	if ($attrs.isModal == 'true'){
		domainName = $scope.$eval($attrs.domainName);
		columnName = $scope.$eval($attrs.columnName);				
	} else {
		domainName = $scope.domainName;
		columnName = $scope.columnName;
	}
	
	if (!angular.isUndefined(domainName)){
		var tempDomainName = domainName;
		$scope.tempDomainName = $scope.domainName;
	}
	$scope.domainNameLocaled = Localization.resolve($scope.tempDomainName, $scope.tempDomainName);
	$log.log('$scope.domainNameLocaled: ', $scope.domainNameLocaled);
	//$scope.domainName = domainName;
	//$scope.columnName = columnName;
	$log.log('domainName: ', domainName, ' columnName: ', columnName);
	
	
	
	
	$scope.loadSearchCriteria = function(){	
		// load search criterias
		// "http://localhost:8080/spring-jpa-data/rest/glpatr/search"		
	    //var searchCriteriaUrl = "http://192.168.10.245:8080/spring-jpa-data/rest/glpatr/search";	   
		var searchUrl = domainName;
        if(!angular.isUndefined(domainName)){
            RepositoryRestangular.one(searchUrl.pluralize(), 'search').get().then(function(response){
                var links = [];
                function Link(domainName, key, link) {
                    this.link = link;
                    this.key = key;
                    this.domainName = domainName;
                    this.init = function() {
                        this.rel = Localization.resolve(domainName + '.' + this.key, this.key);
                        this.href = link.href.substring(0, link.href.indexOf(this.key)) + this.key;
                    }
                    this.init();
                }
                $.each(response._links, function(key, link) {
                    links.push(new Link(domainName, key, link));
                });
                $scope.searchCriteriaItems = links;
                $scope.selectedSearchCriteria = links[1];
                $log.log('$scope.selectedSearchCriteria ', $scope.selectedSearchCriteria);
                            
                // set the search text and criteria back to the UI
                if ($stateParams.searchHref != ''){
                    for (var link in response.links){
                        if (link.href == $stateParams.searchHref){
                            $scope.selectedSearchCriteria = link;
                            break;
                        }	    		
                    }
                }	    	   
                $scope.searchText = $stateParams.searchText;
            }, function (response) {
                console.log("Error", response);
            });
        }
	    
	}
	
	var loadPropertyDomain = function (record) {
		
			$scope[domainName] = record;
			var domainRest = Restangular.one(domainName, record.id);
			
			var idLookups = {
					"rest" : domainRest,
					"name" : domainName,
					"$scope" : $scope,
					"properties" : [
							{ 
								"name" : "gl14grid",
								"domain" : "glgrma"
							},
							{
								"name" : "gl14brnc",
								"domain" : "glbrnc"
							}
							]
				};
			
			
			
			
			var links = record.links;
			
            SelectionLookup.registerPropertyListener(idLookups);
	        Lookup.loadPropertyDomain(idLookups);

	};
	
	
	$scope.restSearch = function(searchText, searchHref, currentPage){	
		console.log("restSearch");
	    // perform search
		RepositoryRestangular.oneUrl(domainName, searchHref).getList('',{search:searchText, page:currentPage}).then(function(response){
            $scope[columnName] = new Array();
            $.each(response, function(index, entity){
                $scope[columnName].push(angular.extend(entity, $injector.get(_(domainName).capitalize())));
            });
            
            /*
			$.each($scope[columnName], function(i,record){
				loadPropertyDomain(record);
			});*/
			$scope.showResult = true;	  
            console.log("results", $scope[columnName]);
				
		}, function (response) {
	        console.log("Error", response);
	    });
	}
    
	$scope.search = function(searchDomain, recordPerPage) {
		console.log("search");
		$scope.showGrid = true;
    	$scope[columnName] = {};
    	$scope['totalServerItems_' + columnName] = 0;
    					
		$scope.currentPage = 0;    		
		//$scope.searchText = $stateParams.searchText;
		$scope.searchHref = $scope.selectedSearchCriteria.href;
		$scope.restSearch($scope.searchText, $scope.searchHref, $scope.currentPage);    	
		$log.log("searchText: ", $scope.searchText, " searchHref: ", $scope.searchHref, " currentPage: ", $scope.currentPage);
		
	}
	
	
	//set up ng-grid
    $scope['totalServerItems_' + columnName] = 0;    
    $scope['mySelections_' + columnName] = [];     
    $scope['currentColumnDefs_' + domainName] = columnDefs[domainName];
  
    
    var gridOptionsList = {
	        data: columnName,
	        showColumnMenu: true,
	        showSelectionCheckbox: true,
	        //checkboxCellTemplate: "<div><input class='ngSelectionCheckbox' type='checkbox' ng-checked='row.selected' ng-click='check(row)'/></div>",
	        selectedItems: $scope['mySelections_' + columnName],
	        enablePaging: false,
	        showFooter: true,
	        //headerRowHeight: 100,//500,
	        totalServerItems:'totalServerItems_' + columnName,
	        pagingOptions: $scope.pagingOptions,
	        // filterOptions: $scope.filterOptions,
	        multiSelect: $scope.enableMultiSelect,
	        enableColumnResize: true,
	        enableColumnReordering: true,
	        enablePinning: false,
	        columnDefs: 'currentColumnDefs_' + domainName,
	        virtualizationThreshold: 5
    };
    
    var gridOptionsModal = {
	        data: columnName,
	        showColumnMenu: true,
	        showSelectionCheckbox: false,
	        //checkboxCellTemplate: "<div class='ngSelectionCell ng-scope'><input class='ngSelectionCheckbox' type='checkbox' ng-checked='row.selected' ng-click='check(row)'/></div></div>",
	        selectedItems: $scope['mySelections_' + columnName],
	        enablePaging: false,
	        showFooter: true,
	        //headerRowHeight: 100,//500,
	        totalServerItems:'totalServerItems_' + columnName,
	        pagingOptions: $scope.pagingOptions,
	        // filterOptions: $scope.filterOptions,
	        multiSelect: false,
	        enableColumnResize: true,
	        enableColumnReordering: true,
	        enablePinning: false,
	        columnDefs: 'currentColumnDefs_' + domainName,        
	        virtualizationThreshold: 5
    };
    
    
    var gridOptions;
    if ($attrs.isModal=="true"){
    	gridOptions = gridOptionsModal;
    } else {
    	gridOptions = gridOptionsList;
    }
    
        
    $scope.openHref = function(id){
    	var url = '#/repository/' + domainName.pluralize() + '/' + id;
    	window.open(url, "_blank");
    }
    
    //load search criteria
	$scope.loadSearchCriteria();
	$scope.showGrid = false;
	$log.log('SearchCtrl_$scope', $scope);
    
	$scope.gridOptionsArray = {};
	$scope.gridOptionsArray[columnName] = getGridOptions(gridOptions);
	$scope.gridOptions = $scope.gridOptionsArray[columnName];
	
	
	$scope.$on('SelectedItems', function() {
		$scope.$emit('ModalSelectedItems', $scope.gridOptionsArray[columnName].selectedItems);
	});
    
    $scope.open = function (domainName, columnName) {
        
    	var data = {"domainName": domainName, "columnName": columnName};
        console.log(data);
		var modalInstance = $modal.open({
	            templateUrl: "partials/searchModal.html",                    
                controller: SearchModalInstanceCtrl,
	            resolve: {
					items: function () {
					  return data;
					}
				 }
	    });
	
		modalInstance.result.then(function (modalSelectedItem) {
			$scope.modalSelectedItem = modalSelectedItem;
	    	$scope.$emit('modalSelectedItem', $scope.modalSelectedItem);   
		}, function () {
			//$log.info('Modal dismissed at: ' + new Date());
		});
    	
		/*var d = $modal.dialog({
            dialogFade: true,
            dialogOpenClass: 'modal-open',
            domainName: domainName,
            columnName: columnName
        });
				
		var modalInstance = d.open('partials/searchModal.html', 'ModalInstanceCtrl').then(function (modalSelectedItem) {
	    	$scope.modalSelectedItem = modalSelectedItem;
	    	$scope.$emit('modalSelectedItem', $scope.modalSelectedItem);    
	    }, function () {
	    	//$log.info('Modal dismissed at: ' + new Date());
	    });	       */
	}
	
	
	$scope.$watch('mySelections_' + columnName, function(newValue) {
        
		try {
			$scope.$emit('ListSelectedItems', $scope['mySelections_' + columnName]);    	
		} catch (exception){
			//$log.log(exception);
		}
    }, true);
	
    $scope.$on('ngGridEventScroll', function(){		
		$scope.currentPage = $scope.currentPage + 1; 
		RepositoryRestangular.oneUrl(domainName, $scope.searchHref).get({search:$scope.searchText, page:$scope.currentPage}).then(function(response){
			var results = {};
			if (angular.isUndefined(response.content)){					
				results[0] = response;			
			} else {				
				results = response.content;	
			}	
						
			$.each(results, function(i,item){
	            $scope[columnName].push(item);
	            loadPropertyDomain(item);
	        }); 
		}, function (response) {
	        console.log("Error", response);
	    });		
    });
			
	console.log('Leaving SearchCtrl');
	
	
	
};


function getGridOptions(gridOptions){
	return gridOptions;
}

/*
var ModalInstanceCtrl = function ($scope, $log, dialog, $rootScope, items) {
	$log.log('ModalInstanceCtrl dialog.options', dialog.options);
	
	$scope.domainName = dialog.options.domainName;
	$scope.columnName = dialog.options.columnName;
	
	$scope.$on('ModalSelectedItems', function(event, ModalSelectedItems) {
		var response = {"items" : ModalSelectedItems, domainName: $scope.domainName, columnName: $scope.columnName};
		$rootScope.$broadcast("event:ModalSelectedItems", response);
		$log.log("broadcast event:ModalSelectedItems")
		$log.log('ModalSelectedItems', ModalSelectedItems);		
		dialog.close(ModalSelectedItems);
	});
		
	$scope.cancel = function () {
		dialog.close('cancel');
	};
	
};*/

var SearchModalInstanceCtrl = function ($scope, $log, $modalInstance, $rootScope, items) {
	$log.log('Entering Modal Instance Ctrl. Parameter passed: ', items);
	
	$scope.domainName = items.domainName;
	$scope.columnName = items.columnName;
	
	$scope.$on('ModalSelectedItems', function(event, ModalSelectedItems) {
		var response = {"items" : ModalSelectedItems, domainName: $scope.domainName, columnName: $scope.columnName};
		$rootScope.$broadcast("event:ModalSelectedItems", response);
		$log.log("broadcast event:ModalSelectedItems")
		$log.log('ModalSelectedItems', ModalSelectedItems);		
		$modalInstance.close(ModalSelectedItems);
	});
		
	$scope.cancel = function () {
		$modalInstance.close('cancel');
	};
	
};