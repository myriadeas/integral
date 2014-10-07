define(['angular','lodash','jquery','circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.controller('PatronItemEligibilityIndexCtrl', ['$scope', '$state','PatronItemEligibility','Localization','patronItemEligibilities','EntityObjectUtil','flash','Item','Patron', function($scope, $state, PatronItemEligibility, Localization, patronItemEligibilities, EntityObjectUtil, flash, Item, Patron){
        $scope.selectedItems = [];
        $scope.create = function() {
            $state.go("circulation.patronItemEligibility.create");
        }
        $scope.$on('mFormActions:create', function(event, entity) {
            $state.go("circulation.patronItemEligibility.edit", {id: entity.id}, {reload: true});
        });
        
        $scope.$on('mFormActions:delete', function(event, entity) {
            $state.go("circulation.patronItemEligibility", {}, {reload: true});
        });
        $scope.patronItemEligibilities = patronItemEligibilities;
        $scope.patronItemEligibilitiesGridOptions = PatronItemEligibility.getListAllGridOptions();
        $scope.patronItemEligibilitiesGridOptions.selectedItems = $scope.selectedItems;
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
                    Patron.search("findByUsername", {search: $scope.username}).then(function(patrons){
                        if(patrons.length == 0) {
                            flash.info = Localization.resolve("patron.notfound");
                        } else {
                            $scope.item.getPatronItemEligibility($scope.username).then(function(patronItemEligibility){
                                $scope.matchedEligibility =  EntityObjectUtil.copyObject(patronItemEligibility, $scope.patronItemEligibilities, 'code');
                                $scope.matchedEligibility.isMatched = true;
                            });
                            $scope.patron = patrons[0];
                        }
                    });
                    
                }
            });
        }
        
    }]);
    module.controller('PatronItemEligibilityCreateCtrl', ['$scope', 'PatronItemEligibility','ItemCategoryRepository','PatronCategoryRepository','LoanUnit', function($scope, PatronItemEligibility, ItemCategoryRepository, PatronCategoryRepository, LoanUnit){
        $scope.repository = PatronItemEligibility.getRepository();
        $scope.patronItemEligibility = {};
        angular.copy(PatronItemEligibility, $scope.patronItemEligibility);
        $scope.patronItemEligibility.initItemCategories().then(function(response){
            $scope.itemCategories = response;
        });
        $scope.patronItemEligibility.initPatronCategories().then(function(response){
            $scope.patronCategories = response;
        });            
        $scope.loanUnitEnum = LoanUnit;
        $scope.actions = "createOrSave";
    }]);
    module.controller('PatronItemEligibilityEditCtrl', ['$scope', 'patronItemEligibility', 'LoanUnit','Localization', function($scope, patronItemEligibility, LoanUnit, Localization){
        function isPageReload() {
            return ($scope.selectedItems.length == 0);
        }
        if(isPageReload()) {
            $scope.patronItemEligibility = patronItemEligibility;
            $.each($scope.patronItemEligibilities, function(key, _patronItemEligibility) {
                if(_patronItemEligibility.id == $scope.patronItemEligibility.id) {
                    $scope.selectedItems[0] = $scope.patronItemEligibility;
                    $scope.patronItemEligibilities[key] = $scope.patronItemEligibility;
                }
            });
        } else {
            $scope.patronItemEligibility = $scope.selectedItems[0];
        }
        $scope.patronItemEligibility.init();
        $scope.finesGridOptions = {
            data: 'patronItemEligibility.fines',
            enableRowSelection: false,
            enableSorting: false,
            sortInfo: {fields: ['start'], directions: ['asc']},
            columnDefs: [{field: 'start', displayName: Localization.resolve('fine.start')}, 
                         {field:'stop', displayName: Localization.resolve('fine.stop')}, 
                         {field:'rate', displayName: Localization.resolve('fine.rate'), enableCellEdit: true}]
        };
        $scope.getTableStyle= function() {
           var rowHeight=30;
           var headerHeight=45;
           return {
              height: ($scope.patronItemEligibility.fines.length * rowHeight + headerHeight) + "px"
           };
        };
        $scope.addNewFine = function() {
            $scope.patronItemEligibility.addNewFine();
        }
        $scope.removeFine = function() {
            $scope.patronItemEligibility.removeFine();
        }
        $scope.patronItemEligibility.initItemCategories().then(function(response){
            $scope.itemCategories = response;
        });
        $scope.patronItemEligibility.initPatronCategories().then(function(response){
            $scope.patronCategories = response;
        });
        $scope.loanUnitEnum = LoanUnit;
        $scope.actions = "createOrSave,remove";
    }]);
});