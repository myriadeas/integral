define(['angular','lodash','jquery','foundation/foundation'], function (angular, _, $) {
    var module = angular.module('integral.foundation');
    module.controller('OfficerCreateCtrl', ['$scope','$controller','Officer','OfficerRepository','Localization','$location','UserGroupRepository', function($scope, $controller, Officer, OfficerRepository, Localization, $location, UserGroupRepository){        
        $scope.repository = OfficerRepository;
        $scope.officer = Officer.clone();
        $scope.title = Localization.resolve('officer.create');
        $scope.actions = "createOrSave";
        $scope.$on('mFormActions:create', function(event, entity) {
            $location.path(entity.getViewLink());
        });
        UserGroupRepository.getList().then(function(userGroups){
            $scope.userGroups = userGroups;
        });
        $scope.open = function($event, selectedDateField){
			$event.stopPropagation();
			$scope.officer.datepickers[selectedDateField].isOpened = true;
        }
        
    }]);
    module.controller('OfficerViewCtrl', ['$scope','officer','OfficerRepository','$location','Localization', function($scope, officer, OfficerRepository, $location, Localization){
        $scope.repository = OfficerRepository;
        $scope.officer = officer;
        $scope.title = Localization.resolve('officer.view');
        $scope.actions = "edit,remove";
        $scope.$on('mFormActions:delete', function() {
            $location.path($scope.officer.getSearchLink());
        });
        $scope.officer.initUserGroups().then(function(userGroups){
            $scope.userGroups = userGroups;
        });
        $scope.officer.lazyLoading();
    }]);
    module.controller('OfficerEditCtrl', ['$scope','$controller','officer','OfficerRepository','Localization','$location', function($scope, $controller, officer, OfficerRepository, Localization, $location){
        $scope.officer = officer;
        $scope.officer.initUserGroups().then(function(userGroups){
            $scope.userGroups = userGroups;
        });
        $scope.officer.getLastModifiedBy();
        $scope.actions = "createOrSave,remove";
        $scope.$on('mFormActions:delete', function() {
            $location.path($scope.officer.getSearchLink());
        });
        $scope.$on('mFormActions:update', function() {
            $location.path($scope.officer.getViewLink());
        });
        $scope.open = function($event, selectedDateField){
			$event.stopPropagation();
			$scope.officer.datepickers[selectedDateField].isOpened = true;
        }
		$scope.repository = OfficerRepository;
        $scope.title = Localization.resolve('officer.edit');
        $scope.officer.lazyLoading();
    }]);
});