function ImageRetrieveCtrl($scope, $stateParams, $rootScope, Notification,
		$http, imageRestApi) {

	$scope.image = {
		path : imageRestApi.get + "/" + $stateParams.id
	};

	$scope.$on('imageUpload', function(arg1, timestamp) {
		$scope.$apply(function($scope) {
			$scope.image.path = $scope.image.path + "?a=" + new Date();
		});
	});
}

function GlpatrSummaryCtrl($scope, $stateParams, $location, Restangular,
		Notification, $rootScope, $http, Localization) {

	$scope.$on('responseCSV', function(arg1, response) {

		var responseCSV = JSON.parse(response);

		$scope.$apply(function($scope) {

			$scope.failedCsvUrl = responseCSV['failedCsvUrl'];
			$scope.startTime = responseCSV['startTime'];
			$scope.totalRecords = responseCSV['totalRecords'];
			$scope.totalFailedRecords = responseCSV['totalFailedRecords'];
			$scope.endTime = responseCSV['endTime'];
			$scope.glpatrs = responseCSV['glpatr'];

			var messages = [];
			var statuses = [];
			var glpatrs = responseCSV['glpatr']
			angular.forEach($scope.glpatrs, function(item, index) {
				var status = 'success';
				if (item.exception) {
					var exception = JSON.parse(item.exception);
					var message = '';
					status='failed';
					angular.forEach(exception.messages, function(value,
							indexMsg) {
						message = message
								+ Localization.resolve("Glpatr."
										+ value.property, value.property) + " "
								+ value.message + ", ";
					});
				}			
				messages[index] = message;
				statuses[index] = status;
			});
			$scope.messages = messages;
			$scope.statuses = statuses;
		});

	});

}

function FileUploadCtrl($scope, $stateParams, $route, $log, $rootScope,
		Notification, $http, imageRestApi, csvRestApi, $location) {
	$scope.setFiles = function(element) {
		$scope.$apply(function($scope) {
			// console.log('files:', element.files);
			// Turn the FileList object into an Array

			$scope.files = []
			for ( var i = 0; i < element.files.length; i++) {
				$scope.files.push(element.files[i])
			}
		});
	};

	$scope.$on('uploadCompleted', function(arg1, files) {
		$scope.$apply(function($scope) {
			$scope.files = files;
		});

	});

	$scope.uploadFile = function() {
		var fd = new FormData()
		for ( var i in $scope.files) {
			fd.append("uploadedFile", $scope.files[i])
		}

		var xhr = new XMLHttpRequest()
		xhr.addEventListener("load", uploadComplete, false)
		xhr.addEventListener("error", uploadFailed, false)
		xhr.open("POST", imageRestApi.upload + "/" + $stateParams.id)
		xhr.send(fd)

	};

	$scope.showLoader = false;
	$scope.showSummary = false;

	$scope.uploadCSVFile = function() {
		$scope.showSummary = false;
		var fd = new FormData()
		for ( var i in $scope.files) {
			fd.append("uploadedFile", $scope.files[i])
		}

		var xhr = new XMLHttpRequest()
		xhr.addEventListener("load", uploadCSVComplete, false)
		xhr.addEventListener("error", uploadFailed, false)
		xhr.open("POST", csvRestApi.upload)
		$scope.showLoader = true;

		xhr.send(fd)

	};

	function uploadCSVComplete(evt) {
		console.log(evt.target.responseText);

		$rootScope.$broadcast('responseCSV', evt.target.responseText);
		// $location.path("/glpatr/adminCreate/summary");
		// var url = "/spring-jpa-data/index.html#/glpatr/adminCreate/summary";
		// window.open(url, "_blank");
		$scope.showSummary = true;
		$scope.showLoader = false;
		$rootScope.$broadcast('uploadCompleted', []);

	}

	function uploadComplete(evt) {
		$rootScope.$broadcast('imageUpload', new Date());
		// clear the file list
		$rootScope.$broadcast('uploadCompleted', []);
		Notification.success(evt.target.responseText);
	}

	function uploadFailed(evt) {

		Notification.error("There was an error attempting to upload the file.");

	}

}