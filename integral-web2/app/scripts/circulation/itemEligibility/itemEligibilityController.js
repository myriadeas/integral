define(['angular','lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.controller('ItemEligibilityIndexCtrl', ['$scope', '$state','Localization','itemEligibilities', 'ItemEligibility','Item','EntityObjectUtil','Localization','flash','CirculationService', 
    function($scope, $state, Localization, itemEligibilities, ItemEligibility, Item, EntityObjectUtil, Localization, flash, CirculationService){
        $scope.selectedItems = [];
        $scope.itemEligibilities = itemEligibilities;
        $scope.$state = $state;
        $scope.create = function() {
            $state.go("circulation.itemEligibility.create");
        }
        
        $scope.$on('mFormActions:create', function(event, entity) {
            $state.go("circulation.itemEligibility.edit", {id: entity.id}, {reload: true});
        });
        
        $scope.$on('mFormActions:delete', function(event, entity) {
            $state.go("circulation.itemEligibility", {}, {reload: true});
        });
        
        $scope.$on('mFormActions:update', function(event, entity) {
            //$state.go("circulation.itemEligibility.edit", {id: entity.id}, {reload: true});
        });
        

        $scope.itemEligibilitiesGridOptions = ItemEligibility.getListAllGridOptions();
        $scope.itemEligibilitiesGridOptions.selectedItems = $scope.selectedItems;
        $scope.itemIdentifier = "";
        
        $scope.matchedEligibility = {};
        $scope.matchEligibility = function() {
            function clearPrevious(){
                $scope.matchedEligibility.isMatched = false;
            }
            clearPrevious();
            Item.search("findByItemIdentifier", {search: $scope.itemIdentifier}).then(function(items){
                if(items.length == 0) {
                    flash.info = Localization.resolve("item.notfound");
                } else {
                    $scope.item = items[0];
                    $scope.matchedEligibility =  EntityObjectUtil.copyObject(items[0].itemEligibility, $scope.itemEligibilities);
                    $scope.matchedEligibility.isMatched = true;
                }
            });
        }
    }]);
    module.controller('ItemEligibilityCreateCtrl', ['$scope', 'ItemEligibility','ItemCategoryRepository','$state', function($scope, ItemEligibility, ItemCategoryRepository, $state){
        $scope.repository = ItemEligibility.getRepository();
        $scope.itemEligibility = {};
        angular.copy(ItemEligibility, $scope.itemEligibility);
        $scope.itemEligibility.initItemCategories().then(function(response){
            $scope.itemCategories = response;
        });
        $scope.actions = "createOrSave";
    }]);
    module.controller('ItemEligibilityEditCtrl', ['$scope', 'itemEligibility','$state','$q','$http', function($scope, itemEligibility, $state, $q, $http){  
        function isPageReload() {
            return ($scope.selectedItems.length == 0);
        }
        if(isPageReload()) {
            $scope.itemEligibility = itemEligibility;
            $.each($scope.itemEligibilities, function(key, _itemEligibility) {
                if(_itemEligibility.id == $scope.itemEligibility.id) {
                    $scope.selectedItems[0] = $scope.itemEligibility;
                    $scope.itemEligibilities[key] = $scope.itemEligibility;
                }
            });
        } else {
            $scope.itemEligibility = $scope.selectedItems[0];
        }
        $scope.itemEligibility.initItemCategories().then(function(response){
            $scope.itemCategories = response;
        });
        
        $scope.actions = "createOrSave,remove";
    }]);
});
