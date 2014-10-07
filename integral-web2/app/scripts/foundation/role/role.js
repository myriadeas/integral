'use strict';

/* App Module */
define(['angular','lodash','jquery','foundation/foundation'], function (angular, _, $) {
    var module = angular.module('integral.foundation');
    module.config(function($stateProvider) {
        var stateBaseName = 'foundation.role';
        var viewsBaseName = 'views/foundation/role'; 
        $stateProvider.state(stateBaseName + '.list', {
            url : '/list',
            templateUrl : viewsBaseName + '/list.html',
            controller : 'RoleListCtrl',
            resolve: {
                roles: function(RoleRepository) {
                    return RoleRepository.getList();
                },
                permissions: function(PermissionRepository) {
                    return PermissionRepository.getList();
                }
            }
        });
    });
    
    
    module.factory('Role', function(FoundationModel) {
        return angular.extend(this, FoundationModel, {
            isContainPermission: function(permission) {
                if(angular.isUndefined(this.securityPermissions)) {
                    return false;
                }
                var matched = false;
                $.each(this.securityPermissions, function(key, securityPermission) {
                    if(securityPermission.id == permission.id) {
                        matched = true;
                    }
                });
                return matched;
            }
        });
    });
    
    
});