define(['angular','lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.controller('RoleListCtrl', ['$scope','roles','permissions', function($scope, roles, permissions){
        $scope.roles = roles;
        $scope.permissions = permissions
    }]);
});