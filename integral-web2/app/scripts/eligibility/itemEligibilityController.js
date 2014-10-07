define(['app','lodash','jquery'], function (integralApp, _, $) {

    integralApp.controller('ItemEligibilityIndexCtrl', ['$scope', '$state','ItemEligibility','$q', function($scope, $state, ItemEligibility, $q){
        $scope.selectedItems = [];
        $scope.create = function() {
            $state.go("eligibility.itemEligibility.create");
        }
        
        $scope.$watch('selectedItems', function(selectedItems) {
            if(_.size(selectedItems) > 0) {
                $state.go("eligibility.itemEligibility.edit", {id: selectedItems[0].id});
                $scope.itemEligibility = selectedItems[0];
            }
        }, true);
        
        ItemEligibility.getRepository().getList().then(function(response){
            $scope.itemEligibilities = response;
        });
        $scope.itemEligibilitiesGridOptions = {
            data: 'itemEligibilities',
            enablePinning: true,
            multiSelect: false,
            selectedItems: $scope['selectedItems'],
            columnDefs: [
                {field:'code', displayName:'Code'}, 
                {field:'description', displayName:'Description'}, 
                {field:'criteria', displayName:'Criteria'}, 
                {field:'maxLoanAllowed', displayName:'Maximum Loan Allowed'}
            ]
        };
        
        $scope.select = function(id) {
            var deferred = $q.defer();
            ItemEligibility.getRepository().getList().then(function(response){
                $scope.itemEligibilities = response;
                $scope.selectedItems.push(_.find($scope.itemEligibilities, function (_itemEligibility) {
                    return _itemEligibility.id == id;
                }));
                $scope.itemEligibility = $scope.selectedItems[0];
                deferred.resolve($scope.selectedItems[0]);
            });
            return deferred.promise;
        }
    }]);
    integralApp.controller('ItemEligibilityCreateCtrl', ['$scope', 'ItemEligibility','ItemCategoryRepository', function($scope, ItemEligibility, ItemCategoryRepository){
        $scope.repository = ItemEligibility.getRepository();
        ItemCategoryRepository.getList().then(function(response){
            $scope.itemCategories = response;
        });
        $scope.itemEligibility = {};
        angular.copy(ItemEligibility, $scope.itemEligibility);
        $scope.$watch('itemEligibility.itemCategories', function(newValue) {
            if(!angular.isUndefined(newValue)) {
                $scope.itemEligibility.updateCriteriaValue($scope.itemCategories);
            }
        }, true);
        $scope.actions = "createOrSave";
    }]);
    integralApp.controller('ItemEligibilityEditCtrl', ['$scope', '$stateParams','ItemCategoryRepository', function($scope, $stateParams, ItemCategoryRepository){
        ItemCategoryRepository.getList().then(function(response){
            $scope.itemCategories = response;
            
            function isInheritFromParent() {
                return !angular.isUndefined($scope.itemEligibility);
            }
            
            if(isInheritFromParent()) {
                $scope.itemEligibility.clearNullItemCategories();
                $scope.itemEligibility.initItemCategories($scope.itemCategories);
            } else {
                $scope.select($stateParams.id).then(function(itemEligibility){ 
                    $scope.itemEligibility = itemEligibility;
                    $scope.itemEligibility.clearNullItemCategories();
                    $scope.itemEligibility.initItemCategories($scope.itemCategories);
                });            
            }
        });
        $scope.$watch('itemEligibility.itemCategories', function(newValue) {
            if(!angular.isUndefined(newValue)) {
                $scope.itemEligibility.updateCriteriaValue($scope.itemCategories);
            }
        }, true);
        $scope.actions = "createOrSave,remove";
    }]);
});