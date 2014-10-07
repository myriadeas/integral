'use strict';

function CreateCtmatmCtrl($scope, $log, $stateParams, $location, $modal) {
	$log.log("Entering CreateCtmatmCtrl");
	$scope.isDisallowedToPerformAction = true;
	$scope.template = "Choose template";
	$scope.templates = {
		"books": "books",
		"computerFiles": "computer file",
		"maps": "maps"
		}
	$scope.chooseTemplate = function(val) {
		$scope.isDisallowedToPerformAction = false;
		$scope.template = val;
	};
	$scope.add = function(){
		$log.log("Entering add mar record");
		
		var data = createBigData();
		var $container = $("#dataTable");
		$container.handsontable({
			readOnly: false,
			data: data,
			colHeaders: ["#", "Tag", "Ind1", "Ind2", "Bibliographic Details"],
			columns: [
				{readOnly: true},{},{},{},{}
			],
			colWidths: [50, 50, 50, 50, 400],
			afterSelection: function(r, p, r2, p2){
				console.log(r, p, r2, p2);
				$scope.colors = [
				  {name:'b- No date given; B.C. date involved'},
				  {name:'c-Continuing resource currently published'},
				  {name:'d-Continuing resource ceased publication'},
				  {name:'e-Detailed date'},
				  {name:'i-Inclusive dates of collection'}
				];
				
				if (r==0 && p==4){
					var modalInstance = $modal.open({
					  templateUrl: 'views/cataloguing/bo/modalPopup.html',
					  controller: ModalInstanceCtrl,
					  resolve: {
						colors: function () {
						  return $scope.colors;
						}
					  }
					});
					
					modalInstance.result.then(function (selectedColor) {
					  data[r][p] = selectedColor;
						$container.handsontable('render');
					}, function () {
					  $log.info('Modal dismissed at: ' + new Date());
					});
					
				}
				
			}
		});
	}
	
	$scope.edit = function(){
		$log.log("Entering edit mar record");
	}
	
	$scope.trash = function(){
		$log.log("Entering trash mar record");
	}
	
	
	
	$(document).ready(function() {
		
	});
	
	
	
	$log.log("Leaving CreateCtmatmCtrl");
}

function createBigData() {
    var rows = []
      for (var j=0; j < 100; j++) {
		rows.push([j, "00"+j, "", "", ""])
      }
  
    return rows;
  }