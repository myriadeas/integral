'use strict';
define(['app','lodash','jquery'], function (integralApp, _, $) {
    integralApp.controller('RepositoryCtrl', ['$scope', 'RepositoryRestangular', function($scope, RepositoryRestangular){        
    }]);
    
    integralApp.controller('RepositoryEntityCtrl', ['$scope', 'entityDomainClass', function($scope, entityDomainClass){
        $scope.entityDomainClass = entityDomainClass;
        $scope.entityName = $scope.entityDomainClass.name;
        
    }]);
    integralApp.controller('RepositoryEntitySearchCtrl', ['$scope', '$log', 'ResourceDescriptorRepository', 'entityDomainClass', '$stateParams', '$window', 'flash', function($scope, $log, ResourceDescriptorRepository, entityDomainClass, $stateParams, $window, flash){
        $scope.entityName = $scope.entityDomainClass.name;
		$scope.selectedItems = [];
		
		$log.log("entityName=" + $scope.entityName);
	
	/*

      WorkingDayRepository.customGETLIST('search/findByBranchAndWorking', { "branch": $scope.branch.id, "working": false }).then(function(response){
        //$scope.weekends = response;
        //$scope.weekends = [{"dayOfWeek": 1, "working":false}, {"dayOfWeek": 7, "working":false}];
        $log.debug($scope.weekends);
        $scope.refetchEvents($scope.calendar);
      }, function(error) {
          alert(error);
      });
	  //response
	
	*/
	
        $scope.view = function() {
			if ($scope.entityName = 'resourceDescriptor') {
				$.each($scope.selectedItems, function(index, selection) {	
				$log.log("selection.resourceDescriptorId=" + selection.resourceDescriptorId);
					ResourceDescriptorRepository.customGET('search/findByResourceDescriptorId', {"search" : selection.resourceDescriptorId}).then(function(response){
					$log.log("response=" + response);
					$window.open("#" + "/cataloguing2/resourcedescriptor/" + response._embedded.resourceDescriptors[0].id + "/edit" , "_blank");
					
					})
					
				});		
			} else {
				$.each($scope.selectedItems, function(index, selection) {	
					$window.open("#" + selection.getViewLink() , "_blank");
				});	
			}
        }
        $scope.edit = function() {
					if ($scope.entityName = 'resourceDescriptor') {
				$.each($scope.selectedItems, function(index, selection) {	
				$log.log("selection.resourceDescriptorId=" + selection.resourceDescriptorId);
					ResourceDescriptorRepository.customGET('search/findByResourceDescriptorId', {"search" : selection.resourceDescriptorId}).then(function(response){
					$log.log("response=" + response);
					$window.open("#" + "/cataloguing2/resourcedescriptor/" + response._embedded.resourceDescriptors[0].id + "/edit" , "_blank");
					
					})
					
				});		
			} else {
				$.each($scope.selectedItems, function(index, selection) {
					$window.open("#" + selection.getEditLink() , "_blank");
				});
			}
        }
        $scope.create = function(){
            $window.open("#" + $scope.entityDomainClass.getCreateLink(), "_blank");
        }
    }]);
    integralApp.controller('RepositoryEntityViewCtrl', ['$scope', '$stateParams', '$injector', 'flash','Localization','$location', function($scope, $stateParams, $injector, flash, Localization, $location){
        $scope.repository = $scope.entityDomainClass.getRepository();
        $scope.repository.get($stateParams.id).then(function(entity) {
            $scope[$scope.entityName] = entity;
        }, function(error) {
            flash.info = Localization.resolve($scope.entityDomainClass.name + ".notfound", "Not found " + $scope.entityDomainClass.name);
        });
        $scope.entityTitle = Localization.resolve($scope.entityDomainClass.name  + '.view');
        $scope.actions = "edit,remove";
        $scope.$on('mFormActions:delete', function() {
            $location.path($scope[$scope.entityName].getSearchLink());
        });
    }]);
    
    integralApp.controller('RepositoryEntityCreateCtrl', ['$scope','$log', '$injector', 'flash','Localization','$location', function($scope, $log, $injector, flash, Localization, $location){
        $scope.repository = $scope.entityDomainClass.getRepository();
        $scope[$scope.entityName] = $scope.entityDomainClass.clone();
        $scope.entityTitle = Localization.resolve($scope.entityName  + '.create');
        $scope.actions = "createOrSave";
        $scope.$on('mFormActions:create', function(event, entity) {
            $location.path(entity.getViewLink());
			
        });	
		
		$scope.open= function($event, selectedDateField){
			$event.stopPropagation();
			$scope.opened = true;
			$scope[$scope.entityName].datepickers[selectedDateField].isOpened = true;
		}			
    }]);
    integralApp.controller('RepositoryEntityEditCtrl', ['$scope', '$log', '$stateParams', '$injector', 'flash','Localization','$location', function($scope, $log, $stateParams, $injector, flash, Localization, $location){
		$scope.repository = $scope.entityDomainClass.getRepository();

        $scope.repository.get($stateParams.id).then(function(entity) {
            $scope[$scope.entityName] = entity;
        });
		
        $scope.entityTitle = Localization.resolve($scope.entityName  + '.edit');
        /*
		$scope.datepickers = {
			membershipDate: false,
			membershipExpiryDate: false
		}*/
	
		$scope.open= function($event, selectedDateField){
			$event.stopPropagation();
			$scope.opened = true;
			$scope[$scope.entityName].datepickers[selectedDateField].isOpened = true;
		}
        $scope.actions = "createOrSave,remove";
        $scope.$on('mFormActions:delete', function() {
            $location.path($scope[$scope.entityName].getSearchLink());
        });
        $scope.$on('mFormActions:update', function() {
			// $location.path($scope[$scope.entityName].getViewLink());
        });
    }]);
});
