var dev = false;
function CataloguingIndexCtrl($scope, integralCataloguingBoBaseUrl) {
	
	$scope.createNew = function() {
		$scope.url = "#/cataloguing/create";
	}
	
	$scope.edit = function(id) {
		var url = integralCataloguingBoBaseUrl + "#data/library/ctmatm/" + id ; 
		window.open(url, "_blank");
	}
	
	$scope.delete = function(id) {
		var url = integralCataloguingBoBaseUrl + "#data/library/ctmatm/delete/" + id ; 
		window.open(url, "_blank");
	}
	
}


function ExpandCollapseCtrl($scope, $log) {
    
    $scope.expand = function(item){
        if ($scope.isExpanded(item)){
            $scope.expanded = undefined;
        } else {
            $scope.expanded = item;
        }        
    };
    
    $scope.isExpanded = function(item){
        return $scope.expanded === item;
    };
    
    $scope.anyItemExpanded = function() {
        return $scope.expanded !== undefined;
    };
    
    $scope.collapse = function() {
        $scope.expanded = undefined;
    };
}

function CtdocmListCtrl($dialog, $scope, $route, $stateParams, $location, $log, Restangular, Notification, ConfirmDeleteDialog) {
    $scope.$on('ListSelectedItems', function(event, selectedItems){	
		$scope.mySelections = selectedItems;
		
		if (selectedItems.length > 0){
    		$scope.isRecordSelected = true;
    	} else {
    		$scope.isRecordSelected = false;
    	}
    });
	
	
	$scope.view = function() {
    	if ($scope.mySelections.length > 0){
    		var itemSelected = [];
    		angular.forEach($scope.mySelections, function(item){
    			itemSelected.push(item.id); 
    		});
    		
			$.each(itemSelected, function(index, value) {
	    		var url = "#/ctdocm/" + value; 
	    		window.open(url, "_blank");
			});
			
		}else{
			alert('No record selected!');
		}
    }
	
	$scope.edit = function() {
    	if ($scope.mySelections.length > 0){
    		var itemSelected = [];
    		angular.forEach($scope.mySelections, function(item){
    			itemSelected.push(item.id); 
    		});
    		
			$.each(itemSelected, function(index, value) {
	    		var url = "#/ctdocm/edit/" + value; 
	    		window.open(url, "_blank");
			});
			
		}else{
			alert('No record selected!');
		}
    }
	
	$scope.releaseCirculation = function() {
    	if ($scope.mySelections.length > 0){
    		var itemSelected = [];
    		angular.forEach($scope.mySelections, function(item){
    			itemSelected.push(item.id); 
    		});
    		
			$.each(itemSelected, function(index, value) {
	    		var url = "#/ctdocm/releaseCirculation/" + value;
	    		window.open(url, "_blank");
			});
			
		}else{
			alert('No record selected!');
		}
    }
	
	
    $scope.delete = function(id) {
    	ConfirmDeleteDialog.prompt($dialog).open()
    	.then(function(result){
    		if (result) {
    			Restangular.one('ctdocm', id).remove().then(function(response){
    				Notification.success("Successfully deleted Ctdocm.");
    				$route.reload();
    			}, function(errorResponse){
    				Notification.error("Failed to delete Ctdocm.");
    			});
	         }
    	});
    }
	
}

function CtdocmEditCtrl($scope, $modal, $log, $stateParams, $location, Restangular, Notification, ErrorBinder, SelectionLookup, Lookup, ConfirmDeleteDialog) {
	var ctdocmRest = Restangular.one('ctdocm', $stateParams.id);
	var selectionLookups = {
		"rest" : ctdocmRest,
		"name" : "ctdocm",
		"$scope" : $scope,
		"properties" : [
						{ 
			"name" : "ct03order",
			"domain" : "acordd"
		},
						{ 
			"name" : "ct03cond",
			"domain" : "glcond"
		},
																												{ 
			"name" : "ct03icat",
			"domain" : "glicat"
		},
																{ 
			"name" : "ct03smd",
			"domain" : "glsmd"
		},
																				{ 
			"name" : "ct03forex",
			"domain" : "glforx"
		},
								{ 
			"name" : "ct03vend",
			"domain" : "glvend"
		},
						{ 
			"name" : "ct03loca",
			"domain" : "glloca"
		},
																										{ 
			"name" : "ct03stat",
			"domain" : "gldocs"
		},
			]
	};
	var idLookups = {
			"rest" : ctdocmRest,
			"name" : "ctdocm",
			"$scope" : $scope,
			"properties" : [
																												{ 
			
				"name" : "ct03matno",
				"domain" : "ctmatm"
			
			},
																							{ 
			
				"name" : "ct03patr",
				"domain" : "glpatr"
			
			},
																																																																																																																																	]
		};
	ctdocmRest.get().then(function(response){
	   var ctdocm = response;
	   $scope.ctdocm = ctdocm;
	   SelectionLookup.registerPropertyListener(selectionLookups);
       SelectionLookup.loadPropertyList(selectionLookups);
       Lookup.loadPropertyDomain(selectionLookups);
       Lookup.loadPropertyDomain(idLookups);
	}, function(errorResponse) {
        Notification.success("Failed to load Ctdocm.");
	});
	
	$scope.save = function() {
		ErrorBinder.reset($scope.formCtdocm);
        $scope.ctdocm.put().then(function(response){
            Notification.success("Successfully updated Ctdocm.");
        }, function(errorResponse){
			Notification.error("Failed to update Ctdocm.");
			ErrorBinder.bind($scope.formCtdocm, errorResponse);
        });
	}
	
	$scope.delete = function(ctdocm) {
		var id = ctdocm.id;
		ConfirmDeleteDialog.prompt($dialog).open()
    	.then(function(result){
    		if (result) {
    			Restangular.one('ctdocm', id).remove().then(function(response){
    				Notification.success("Successfully deleted Ctdocm.");
    				var path = '/ctdocm/';
    				$location.path(path);
    			}, function(errorResponse){
    				Notification.error("Failed to delete Ctdocm.");
    			});
	         }
    	});
    }
	
}

function CtdocmViewCtrl($dialog, $scope, $stateParams, $location, Restangular, Notification, ErrorBinder, SelectionLookup, Lookup, ConfirmDeleteDialog, DomainUtils) {
	var ctdocmRest = Restangular.one('ctdocm', $stateParams.id);
	var selectionLookups = {
		"rest" : ctdocmRest,
		"name" : "ctdocm",
		"$scope" : $scope,
		"properties" : [
						{ 
			"name" : "ct03order",
			"domain" : "acordd"
		},
						{ 
			"name" : "ct03cond",
			"domain" : "glcond"
		},
																												{ 
			"name" : "ct03icat",
			"domain" : "glicat"
		},
																{ 
			"name" : "ct03smd",
			"domain" : "glsmd"
		},
																				{ 
			"name" : "ct03forex",
			"domain" : "glforx"
		},
								{ 
			"name" : "ct03vend",
			"domain" : "glvend"
		},
						{ 
			"name" : "ct03loca",
			"domain" : "glloca"
		},
																										{ 
			"name" : "ct03stat",
			"domain" : "gldocs"
		},
			]
	};
	var idLookups = {
			"rest" : ctdocmRest,
			"name" : "ctdocm",
			"$scope" : $scope,
			"properties" : [
																												{ 
			
				"name" : "ct03matno",
				"domain" : "ctmatm"
			
			},
																							{ 
			
				"name" : "ct03patr",
				"domain" : "glpatr"
			
			},
																																																																																																																																	]
		};
	
	ctdocmRest.get().then(function(response){
	   var ctdocm = response;
	   $scope.ctdocm = ctdocm;
	   SelectionLookup.registerPropertyListener(selectionLookups);
       SelectionLookup.loadPropertyList(selectionLookups);
       Lookup.loadPropertyDomain(selectionLookups);
       Lookup.loadPropertyDomain(idLookups);
       $scope.generalInfo = DomainUtils.getGeneralInfo(ctdocm);
	}, function(errorResponse) {
        Notification.success("Failed to load Ctdocm.");
	});
	
	$scope.delete = function(ctdocm) {
		var id = ctdocm.id;
		ConfirmDeleteDialog.prompt($dialog).open()
    	.then(function(result){
    		if (result) {
    			Restangular.one('ctdocm', id).remove().then(function(response){
    				Notification.success("Successfully deleted Ctdocm.");
    				var path = '/ctdocm/';
    				$location.path(path);
    			}, function(errorResponse){
    				Notification.error("Failed to delete Ctdocm.");
    			});
	         }
    	});
    }
}

function CtdocmCreateCtrl($scope, $stateParams, $location, Restangular, Notification, ErrorBinder, SelectionLookup) {
	var ctdocm = {};
	$scope.ctdocm = ctdocm;
	var selectionLookups = {
		"name" : "ctdocm",
		"$scope" : $scope,
		"properties" : [
		{
			"name" : "ct03matno",
			"domain" : "ctmatm"
		},
						{
			"name" : "ct03order",
			"domain" : "acordd"
		},
						{
			"name" : "ct03cond",
			"domain" : "glcond"
		},
																												{
			"name" : "ct03icat",
			"domain" : "glicat"
		},
																{
			"name" : "ct03smd",
			"domain" : "glsmd"
		},
																				{
			"name" : "ct03forex",
			"domain" : "glforx"
		},
								{
			"name" : "ct03vend",
			"domain" : "glvend"
		},
						{
			"name" : "ct03loca",
			"domain" : "glloca"
		},
																										{
			"name" : "ct03stat",
			"domain" : "gldocs"
		},
			]
	};
	SelectionLookup.loadPropertyList(selectionLookups);
	
	$scope.save = function() {
        ErrorBinder.reset($scope.formCtdocm);
		Restangular.all('ctdocm').post($scope.ctdocm).then(function(response){
			var ctdocm = response;
						$location.path("/ctdocm/" + ctdocm.id);
			Notification.success("Successfully created Ctdocm.");
		}, function(errorResponse){
			Notification.error("Failed to create Ctdocm.");
			ErrorBinder.bind($scope.formCtdocm, errorResponse);
		});
	}
}

function CtdocmCreateWithMatnoCtrl($scope, $stateParams, $location, Restangular, Notification, ErrorBinder, SelectionLookup, $log) {
	var ctdocm = {};
	if ($stateParams.id > 0){
		var ctmatmRest = Restangular.one('ctmatm', $stateParams.id);
		ctmatmRest.get().then(function(response){
		   var ctmatm = response;
		   $log.info("ctmatm", ctmatm);
		   delete ctmatm.route;
		   delete ctmatm.parentResource;
		   delete ctmatm.restangularCollection;
		   ctdocm.ct03matno = ctmatm;
		}, function(errorResponse) {
	        // Notification.fail(errorResponse);
		});
	}
	
	var gldocsRest = Restangular.one('gldocs/search/findByGl36stat');
	gldocsRest.get({gl36stat: 'F'}).then(function(response){
	   var gldocs = response;
	   delete gldocs.route;
	   delete gldocs.parentResource;
	   delete gldocs.restangularCollection;
	   ctdocm.ct03stat = gldocs;
	}, function(errorResponse) {
        // Notification.fail(errorResponse);
	});
	
	$scope.ctdocm = ctdocm;
	var selectionLookups = {
		"name" : "ctdocm",
		"$scope" : $scope,
		"properties" : [
						{
			"name" : "ct03order",
			"domain" : "acordd"
		},
						{
			"name" : "ct03cond",
			"domain" : "glcond"
		},
																												{
			"name" : "ct03icat",
			"domain" : "glicat"
		},
																{
			"name" : "ct03smd",
			"domain" : "glsmd"
		},
																				{
			"name" : "ct03forex",
			"domain" : "glforx"
		},
								{
			"name" : "ct03vend",
			"domain" : "glvend"
		},
						{
			"name" : "ct03loca",
			"domain" : "glloca"
		},
																										{
			"name" : "ct03stat",
			"domain" : "gldocs"
		},
			]
	};
	SelectionLookup.loadPropertyList(selectionLookups);
	
	$scope.save = function() {
        ErrorBinder.reset($scope.formCtdocm);
		Restangular.all('ctdocm').post($scope.ctdocm).then(function(response){
			var ctdocm = response;
						$location.path("/ctdocm/" + ctdocm.id);
			Notification.success("Successfully created Ctdocm.");
		}, function(errorResponse){
			Notification.error("Failed to create Ctdocm.");
			ErrorBinder.bind($scope.formCtdocm, errorResponse);
		});
	}
}

function ReleaseCirculationCtrl($scope, $log, $stateParams, ReleaseCirculation, Restangular){
	$scope.releaseCirculation = function() {
		var releaseCirculationRequest = {
					itemIdentifier: $scope.item.ct03docno
		};
		ReleaseCirculation.release(releaseCirculationRequest);

	}
	var ctdocmRest = Restangular.one('ctdocm', $stateParams.id);
	ctdocmRest.get().then(function(data){
		$scope.item = data;
	}, function(errorResponse) {
	});
	
	$scope.$on("releaseCirculation-success", function(event, data){
		$scope.releaseSuccess= true;
		$log.log("release circulation success.");
	});
	
	$scope.$on("releaseCirculation-fail", function(event, error){
		$scope.releaseError= true;
		$log.log("release circulation fail.");
	});
}

/*
function CatalogSearchListCtrl($scope, integralCataloguingBoBaseUrl) {
	
	$scope.createNew = function() {
		var url = integralCataloguingBoBaseUrl + "#data/library/ctmatm/new" ; 
		window.open(url, "_blank");
	}
	
	$scope.edit = function(id) {
		var url = integralCataloguingBoBaseUrl + "#data/library/ctmatm/" + id ; 
		window.open(url, "_blank");
	}
	
	$scope.delete = function(id) {
		var url = integralCataloguingBoBaseUrl + "#data/library/ctmatm/delete/" + id ; 
		window.open(url, "_blank");
	}
	
}*/

if(dev){
	ReleaseCirculationCtrl.$inject = ['$scope', '$log', '$stateParams', 'ReleaseCirculationMock', 'Restangular'];
}else{
	ReleaseCirculationCtrl.$inject = ['$scope', '$log', '$stateParams', 'ReleaseCirculation', 'Restangular'];
}
