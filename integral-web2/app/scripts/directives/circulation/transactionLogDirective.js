
define(['app'], function (integralApp) {
/* Directives */
integralApp.directive('transactionLog', function() {
	return {
		restrict : 'E',
		templateUrl : 'scripts/directives/circulation/transactionLog.html',
		scope : {
			transactions: '=transactions',
      type: '@type'
		},
		controller: function($scope, $log, $attrs, $modal, $timeout, Localization){
        $log.log("Entering transactionLogDirective controller. $scope.transactions=", $scope.transactions);
        $log.log("$scope.type=", $scope.type);
                
        $scope.statistic = function(transactions) {
            var noOfFail = 0;
            var noOfSuccess = 0;
            
            for (var transaction in $scope.transactions) {
                var responseStatus; 
                if ($scope.type=='checkin' || $scope.type=='checkout' || $scope.type=='renew') responseStatus = $scope.transactions[transaction].ok;
                if ($scope.type=='release') responseStatus = $scope.transactions[transaction].successful;
                
                if (responseStatus == false){
                  noOfFail = noOfFail + 1;
                } else if (responseStatus == true){
                  noOfSuccess = noOfSuccess + 1;
                }
            }
            
            $scope.noOfFail = noOfFail;
            $scope.noOfSuccess = noOfSuccess;
        }
                               
        var itemIdentifierCheckoutCellTemplate = "<div class='ngCellText' ng-class='col.colIndex()'><span ng-cell-text><div><modal data='row.entity' template-url='views/circulation/checkout/itemInformations.html' controller='DisplayItemInformationsCtrl' ></modal></div></span></div>";
        var itemIdentifierCheckinCellTemplate = "<div class='ngCellText' ng-class='col.colIndex()'><span ng-cell-text><div><modal data='row.entity' template-url='views/circulation/checkin/itemInformations.html' controller='DisplayItemInformationsCtrl' ></modal></div></span></div>";
        var tooltipCellTemplate = "<div class='ngCellText' ng-class='col.colIndex()' title={{row.getProperty(col.field)}}>{{row.getProperty(col.field)}}<span ng-cell-text>{{col.colIndex}}</span></div>";                                  
                                                             
        var columnsCheckout = [{field: 'itemIdentifier', displayName: 'Item Identifier', cellTemplate: itemIdentifierCheckoutCellTemplate},
                     {field: 'circulationDetail.patron.username', displayName:'Patron Id'},
                     {field: 'circulationDetail.dueDateTime', displayName: 'Due Date', cellFilter: 'date:\'medium\'' }, 
                     {field: 'item.title', displayName:'Title' , cellTemplate: tooltipCellTemplate}];
         
        var columnsCheckin = [{field: 'itemIdentifier', displayName: 'Item Identifier', cellTemplate: itemIdentifierCheckinCellTemplate},
                     {field: 'titleIdentifier', displayName: 'Title', cellTemplate: tooltipCellTemplate}, 
                     {field:'feeAmount', displayName:'Fines', cellFilter: 'currency'}];
        
        var columnsRenew = columnsCheckout;

        var columnsRelease = [{field: 'item.itemIdentifier', displayName: 'Item Identifier'},
                     {field: 'item.title', displayName: 'Title', cellTemplate: tooltipCellTemplate}, 
                     {field:'message', displayName:'Mesage', cellTemplate: tooltipCellTemplate}];
             

        var rowTemplateCheckout = '<div ng-class="{\'cell-error\': !row.getProperty(\'ok\')}">' + 
                     '<div ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell">' +
                     '<div ng-cell></div>' +
                     '</div>' +
                     '</div>';
                   
        var rowTemplateCheckin = '<div ng-class="{\'cell-error\': !row.getProperty(\'ok\')}">' + 
                     '<div ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell">' +
                     '<div ng-cell ></div>' +
                     '</div>' +
                     '</div>';
                     
        var rowTemplateRenew = '<div ng-class="{\'cell-error\': !row.getProperty(\'ok\')}">' + 
                     '<div ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell">' +
                     '<div ng-cell ></div>' +
                     '</div>' +
                     '</div>';
                     
        var rowTemplateRelease = '<div ng-class="{\'cell-error\': !row.getProperty(\'successful\')}">' + 
                     '<div ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell" >' +
                     '<div ng-cell></div>' +
                     '</div>' +
                     '</div>';
                  
        var itemIdentifierCellTemplate;
        var rowTemplateType;
        if ($scope.type == 'checkin'){
            $scope.columnDefsType = columnsCheckin;
            rowTemplateType = rowTemplateCheckin;
            $scope.successCount = Localization.resolve('circulation.checkin.totalSuccess');
            $scope.failCount = Localization.resolve('circulation.checkin.totalFail');    
            
        } else if ($scope.type == 'checkout'){
            $scope.columnDefsType = columnsCheckout;
            rowTemplateType = rowTemplateCheckout; 
            $scope.successCount = Localization.resolve('circulation.checkout.totalSuccess');
            $scope.failCount = Localization.resolve('circulation.checkout.totalFail');
                
        } else if ($scope.type == 'release'){
            $scope.columnDefsType = columnsRelease;
            rowTemplateType = rowTemplateRelease; 
            $scope.successCount = Localization.resolve('cataloguing.release.totalSuccess');
            $scope.failCount = Localization.resolve('cataloguing.release.totalFail');    
            
        } else if ($scope.type == 'renew'){
            $scope.columnDefsType = columnsRenew;
            rowTemplateType = rowTemplateRenew;
            $scope.successCount = Localization.resolve('circulation.renew.totalSuccess');
            $scope.failCount = Localization.resolve('circulation.renew.totalFail');    
            
        } 
       
        $scope.myData = $scope.transactions;         
        $scope.gridOptions = {
            data: 'myData',
            enablePaging: false,
            showFooter: false,
            multiSelect: false, 
            columnDefs: 'columnDefsType',
            rowTemplate: rowTemplateType
                                        
        };
        
        $scope.$watch('transactions', function (newVal, oldVal) {
            $scope.myData = $scope.transactions;
            $scope.statistic($scope.transactions);
            
            $timeout(function () {                    
        		  var grid = $scope.gridOptions.ngGrid;
              //if($scope.transactions.length > 0){
        		    $scope.gridOptions.selectItem($scope.myData.length - 2, false);
        		    $scope.gridOptions.selectItem($scope.myData.length - 1, true);
        		  //}
              grid.$viewport.scrollTop((($scope.myData.length - 1) * grid.config.rowHeight));
        		}, 0);
          
        }, true);
        
        
        $log.log("Leaving transactionLogDirective controller");
      
		}
	}
});

});
