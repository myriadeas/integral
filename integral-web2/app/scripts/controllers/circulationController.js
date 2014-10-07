'use strict';

function CirculationIndexCtrl($scope, $log, $location) {
	$scope.checkin = function() {
		$location.path("circulation/checkin");
	};

	$scope.checkout = function() {
		$location.path("circulation/checkout");
	};

	$scope.renew = function() {
		$location.path("circulation/renew");
	};
}

function DisplayItemInformationsCtrl($scope, $log, $modalInstance, data) {
	$log.log("Entering displayItemInformationsCtrl.", data);
	$scope.itemInformation = data;
	$scope.ok = function() {
		$modalInstance.close();
	};
	$log.log("Leaving displayItemInformationsCtrl.");
};





function ReserveCtrl($scope, $log, $http, $stateParams, $location, Reserve,
		Utility) {
	$log.log("stateParams", $stateParams);
	var patronId = $stateParams.patronId;
	var id = $stateParams.id;
	$scope.patronId = patronId;
	$scope.id = id;

	$scope.reserved = function() {
		var patronId = $scope.patronId;
		$location.path("circulation/reserve/" + id)
				.search("patronId", patronId);
	};

	$scope.validateReserveItem = function() {
		var itemIdentifier = "0000000013";
		if ($scope.id == "1" || $scope.id == "2" || $scope.id == "3") {
			itemIdentifier = "0000000C16";
		}
		var reserveRequest = {
			"patronIdentifier" : $scope.patronId,
			"itemIdentifier" : itemIdentifier
		};
		Reserve.reserveItem(reserveRequest);
	};

	if (Utility.isNotNull($scope.patronId)) {
		$scope.validateReserveItem();
	}
	;

	$scope.$on("reserve-success", function(event, reserveResponse) {
		$scope.reserve = reserveResponse;
	});

	$scope.$on("reserve-fail", function(event, error) {
		$scope.error = error;
	});
}

function RecallCtrl($scope, $log, $http, $stateParams, $location, Recall,
		Utility) {
	$log.log("stateParams", $stateParams);
	var patronId = $stateParams.patronId;
	var itemId = $stateParams.itemId;
	var id = $stateParams.id;
	$scope.patronId = patronId;
	$scope.itemId = itemId;
	$scope.id = id;
	$scope.ctdocm = {
		ct03matno : {
			id : id
		}
	};

	Recall.getAccession(id);
	$scope.$on("accession-success", function(event, items) {
		$scope.items = items
	});

	$scope.selectedItem = function(itemId) {
		$location.path("circulation/recall/" + id).search("itemId", itemId);
	};

	if (Utility.isNotNull($scope.itemId)) {
		$scope.showInputPatron = $scope.itemId;
	}
	;

	$scope.submit = function() {
		var patronId = $scope.patronId;
		$location.path("circulation/recall/" + id).search("patronId", patronId);
	};

	$scope.validateRecallItem = function() {
		var recallRequest = {
			"patronIdentifier" : $scope.patronId,
			"itemIdentifier" : $scope.itemId
		};
		Recall.recallItem(recallRequest);
	};

	if (Utility.isNotNull($scope.patronId)) {
		$scope.validateRecallItem();
	}
	;

	$scope.$on("recall-success", function(event, recallResponse) {
		$scope.recall = recallResponse;
	});

	$scope.$on("recall-fail", function(event, error) {
		$scope.error = error;
	});
}

function HistoryCtrl($scope, $log, $stateParams, History) {
	$log.log("stateParams", $stateParams);
	var id = $stateParams.id;
	History.getHistory('limsyenie');
	$scope.$on("circulation-history-loaded-success", function(event,
			circulationHistories) {
		$scope.circulationHistories = circulationHistories;
	});

	$scope.$on("circulation-history-loaded-failed", function(event, error) {
		$scope.error = error;
	});
}

function CirculationMaintainanceListCtrl($scope) {
	var modules = [ {
		"name" : "glforx",
		"url" : "index.html#/repository/glforxes/search"
	}, {
		"name" : "glicat",
		"url" : "index.html#/repository/glicats/search"
	}, {
		"name" : "glloca",
		"url" : "index.html#/repository/gllocas/search"
	}, {
		"name" : "glsmd",
		"url" : "index.html#/repository/glsmds/search"
	}, {
		"name" : "glholiday",
		"url" : "index.html#/repository/glholidays/search"
	} ];
	$scope.modules = modules;
}
