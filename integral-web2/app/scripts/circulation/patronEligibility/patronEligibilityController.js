define(['angular','lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.controller('PatronEligibilityIndexCtrl', ['$scope', '$state','PatronEligibility','patronEligibilities','Localization','EntityObjectUtil', 'Patron','flash', function($scope, $state, PatronEligibility, patronEligibilities, Localization, EntityObjectUtil, Patron, flash){
        $scope.selectedItems = [];
        $scope.create = function() {
            $state.go("circulation.patronEligibility.create");
        }
        $scope.$on('mFormActions:create', function(event, entity) {
            $state.go("circulation.patronEligibility.edit", {id: entity.id}, {reload: true});
        });
        
        $scope.$on('mFormActions:delete', function(event, entity) {
            $state.go("circulation.patronEligibility", {}, {reload: true});
        });
        $scope.patronEligibilities = patronEligibilities;
        $scope.patronEligibilitiesGridOptions = PatronEligibility.getListAllGridOptions();
        $scope.patronEligibilitiesGridOptions.selectedItems = $scope.selectedItems;
        $scope.matchedEligibility = {};
        $scope.matchEligibility = function() {
            function clearPrevious(){
                $scope.matchedEligibility.isMatched = false;
            }
            clearPrevious();
            Patron.search("findByUsername", {search: $scope.username}).then(function(patrons){
                if(patrons.length == 0) {
                    flash.info = Localization.resolve("patron.notfound");
                } else {
                    $scope.patron = patrons[0];
                    $scope.matchedEligibility =  EntityObjectUtil.copyObject(patrons[0].patronEligibility, $scope.patronEligibilities);
                    $scope.matchedEligibility.isMatched = true;
                }
            });
        }
    }]);
    module.controller('PatronEligibilityCreateCtrl', ['$scope', 'PatronEligibility','PatronCategoryRepository', function($scope, PatronEligibility, PatronCategoryRepository){
        $scope.repository = PatronEligibility.getRepository();
        $scope.patronEligibility = {};
        angular.copy(PatronEligibility, $scope.patronEligibility);
        $scope.patronEligibility.initPatronCategories().then(function(response){
            $scope.patronCategories = response;
        });            
        $scope.actions = "createOrSave";
    }]);
    module.controller('PatronEligibilityEditCtrl', ['$scope', '$stateParams','PatronCategoryRepository','patronEligibility', function($scope, $stateParams, PatronCategoryRepository,patronEligibility){  
         function isPageReload() {
            return ($scope.selectedItems.length == 0);
        }
        if(isPageReload()) {
            $scope.patronEligibility = patronEligibility;
            $.each($scope.patronEligibilities, function(key, _patronEligibility) {
                if(_patronEligibility.id == $scope.patronEligibility.id) {
                    $scope.selectedItems[0] = $scope.patronEligibility;
                    $scope.patronEligibilities[key] = $scope.patronEligibility;
                }
            });
        } else {
            $scope.patronEligibility = $scope.selectedItems[0];
        }
        $scope.patronEligibility.initPatronCategories().then(function(response){
            $scope.patronCategories = response;
        });            
        $scope.actions = "createOrSave,remove";
    }]);
});