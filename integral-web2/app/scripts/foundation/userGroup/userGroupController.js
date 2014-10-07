define(['angular','lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.controller('UserGroupCreateCtrl', ['$scope','$controller','UserGroup','UserGroupRepository','Localization','$location','RoleRepository', function($scope, $controller, UserGroup, UserGroupRepository, Localization, $location, RoleRepository){        
        $scope.repository = UserGroupRepository;
        $scope.userGroup = UserGroup.clone();
        $scope.title = Localization.resolve('userGroup.create');
        $scope.actions = "createOrSave";
        $scope.$on('mFormActions:create', function(event, entity) {
            $location.path(entity.getViewLink());
        });
        RoleRepository.getList().then(function(roles){
            $scope.roles = roles;
        });
        
        
    }]);
    module.controller('UserGroupViewCtrl', ['$scope','userGroup','UserGroupRepository','$location','Localization', function($scope, userGroup, UserGroupRepository, $location, Localization){
        $scope.repository = UserGroupRepository;
        $scope.userGroup = userGroup;
        $scope.title = Localization.resolve('userGroup.view');
        $scope.actions = "edit,remove";
        $scope.$on('mFormActions:delete', function() {
            $location.path($scope.userGroup.getSearchLink());
        });
        $scope.userGroup.getLastModifiedBy();
        $scope.userGroup.getRoles();
    }]);
    module.controller('UserGroupEditCtrl', ['$scope','$controller','userGroup','UserGroupRepository','Localization','$location', function($scope, $controller, userGroup, UserGroupRepository, Localization, $location){
        $scope.userGroup = userGroup;
        $scope.userGroup.initRoles().then(function(roles){
            $scope.roles = roles;
        });
        $scope.userGroup.getLastModifiedBy();
        $scope.actions = "createOrSave,remove";
        $scope.$on('mFormActions:delete', function() {
            $location.path($scope.userGroup.getSearchLink());
        });
        $scope.$on('mFormActions:update', function() {
            $location.path($scope.userGroup.getViewLink());
        });
        
		$scope.repository = UserGroupRepository;
        $scope.title = Localization.resolve('userGroup.edit');
    }]);
});