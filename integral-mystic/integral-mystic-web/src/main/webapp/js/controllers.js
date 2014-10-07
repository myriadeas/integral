function FetchCtrl($scope, $http, $templateCache) {
	var modules = [ {
		"name" : "circulation",
		"services" : [ {
			"name" : "checkin",
			"description" : "Check in process",
			"url" : "rest/services/circulation/checkin",
			"method" : "POST",
			"body" : {
				"itemIdentifier" : "0000000C14"
			}
		} ]
	}, {
		"name" : "acquisition",
		"services" : [ {
			"name" : "add request",
			"description" : "Request submission",
			"url" : "services/acquisition/request/add",
			"method" : "POST",
			"body" : {
				"ac01request" : "REQUEST001",
				"ac01requestor" : {
					"id" : 1,
					"version" : 0,
					"gl14rmvd": false
				},
				"ac01title" : "Time Management",
				"ac01copies" : 1
			}
		}, {
			"name" : "edit request",
			"description" : "Request editing",
			"url" : "services/acquisition/request/edit",
			"method" : "PUT",
			"body" : {
				"id" : 1,
				"version" : 0,
				"ac01title" : "Time Management: How Important It Is",
				"ac01price" : 10,
				"ac01request" : "REQ0000001",
				"ac01requestor" : {
					"id" : 1,
					"version" : 0
				},
				"ac01copies" : 1
			}
		}, {
			"name" : "delete request",
			"description" : "Request deletion",
			"url" : "services/acquisition/request/delete",
			"method" : "DELETE",
			"body" : {
				"id" : 11,
				"version" : 0,
				"ac01title" : "Time Management: How Important It Is",
				"ac01price" : 10,
				"ac01request" : "REQ0000011",
				"ac01requestor" : {
					"id" : 1,
					"version" : 0,
					"gl14rmvd": false
				},
				"ac01copies" : 1
			}
		}, {
			"name" : "send request for review",
			"description" : "Send request for management to review",
			"url" : "services/acquisition/request/submitForReview",
			"method" : "PUT",
			"body" : {
				"id" : 2,
				"version" : 0,
				"ac01title" : "Time Management: How Important It Is",
				"ac01price" : 10,
				"ac01request" : "REQ0000002",
				"ac01requestor" : {
					"id" : 1,
					"version" : 0
				},
				"ac01status" : {
					"id" : 3,
					"version" : 0,
					"gl43acqstat" : "01"
				},
				"ac01copies" : 1,
				"ac01datereq" : 1394084159993
			}
		}, {
			"name" : "approve request for order",
			"description" : "Approve the request to proceed to ordering",
			"url" : "services/acquisition/request/authorizePurchase/APPROVE",
			"method" : "PUT",
			"body" : {
				"id" : 7,
				"version" : 0,
				"ac01title" : "Time Management: How Important It Is",
				"ac01price" : 10,
				"ac01request" : "REQ0000007",
				"ac01requestor" : {
					"id" : 1,
					"version" : 0
				},
				"ac01status" : {
					"id" : 4,
					"version" : 0,
					"gl43acqstat" : "02"
				},
				"ac01copies" : 1,
				"ac01datereq" : 1394084159993
			}
		}, {
			"name" : "reject request",
			"description" : "Reject the request",
			"url" : "services/acquisition/request/authorizePurchase/REJECT",
			"method" : "PUT",
			"body" : {
				"id" : 8,
				"version" : 0,
				"ac01title" : "Time Management: How Important It Is",
				"ac01price" : 10,
				"ac01request" : "REQ0000008",
				"ac01requestor" : {
					"id" : 1,
					"version" : 0
				},
				"ac01status" : {
					"id" : 4,
					"version" : 0,
					"gl43acqstat" : "02"
				},
				"ac01copies" : 1,
				"ac01datereq" : 1394084159993
			}
		}, {
			"name" : "merge approval",
			"description" : "Approve the request to proceed to ordering in batch",
			"url" : "services/acquisition/request/batchAuthorizePurchase/APPROVE",
			"method" : "PUT",
			"body" : [{
				"id" : 9,
				"version" : 0,
				"ac01title" : "Time Management: How Important It Is",
				"ac01price" : 10,
				"ac01request" : "REQ0000009",
				"ac01requestor" : {
					"id" : 1,
					"version" : 0
				},
				"ac01status" : {
					"id" : 4,
					"version" : 0,
					"gl43acqstat" : "02"
				},
				"ac01copies" : 1,
				"ac01datereq" : 1394084159993
			},{
				"id" : 10,
				"version" : 0,
				"ac01title" : "Time Management: How Important It Is",
				"ac01price" : 10,
				"ac01request" : "REQ0000010",
				"ac01requestor" : {
					"id" : 1,
					"version" : 0
				},
				"ac01status" : {
					"id" : 4,
					"version" : 0,
					"gl43acqstat" : "02"
				},
				"ac01copies" : 1,
				"ac01datereq" : 1394084159993
			}]
		},{
			"name" : "add order",
			"description" : "Create order",
			"url" : "services/acquisition/order/add",
			"method" : "POST",
			"body" :{
				"ac03order": "ORDER1",
			    "ac03copies": 1,
			    "ac03status": {
			        "id": 12,
			        "version": 0,
			        "gl43acqstat": "10"
			    },
			    "ac03vend": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    }
			}
		},{
			"name" : "edit order",
			"description" : "Edit order",
			"url" : "services/acquisition/order/edit",
			"method" : "PUT",
			"body" :{
				"id": 1,
				"version": 0,
				"ac03order": "ORDER00001",
			    "ac03copies": 1,
			    "ac03status": {
			        "id": 12,
			        "version": 0,
			        "gl43acqstat": "10"
			    },
			    "ac03vend": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    },
			    "ac03acqmode": {
			        "id": 1,
			        "version": 0
			    }
			}
		},{
			"name" : "delete order",
			"description" : "Delete order",
			"url" : "services/acquisition/order/delete",
			"method" : "DELETE",
			"body" :{
				"id": 2,
				"version": 0,
				"ac03order": "ORDER00002",
			    "ac03copies": 1,
			    "ac03status": {
			        "id": 12,
			        "version": 0,
			        "gl43acqstat": "10"
			    },
			    "ac03vend": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    },
			    "ac03acqmode": {
			        "id": 1,
			        "version": 0
			    }
			}
		},{
			"name" : "purchase order",
			"description" : "Send Local Purchase Order",
			"url" : "services/acquisition/order/submit",
			"method" : "PUT",
			"body" :{
				"id": 3,
				"version": 0,
				"ac03order": "ORDER00003",
			    "ac03copies": 1,
			    "ac03status": {
			        "id": 12,
			        "version": 0,
			        "gl43acqstat": "10"
			    },
			    "ac03vend": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    },
			    "ac03acqmode": {
			        "id": 1,
			        "version": 0,
			        "gl42acqmode": "0"
			    }
			}
		},{
			"name" : "proforma invoice",
			"description" : "Request for proforma invoice",
			"url" : "services/acquisition/order/submit",
			"method" : "PUT",
			"body" :{
				"id": 4,
				"version": 0,
				"ac03order": "ORDER00004",
			    "ac03copies": 1,
			    "ac03status": {
			        "id": 12,
			        "version": 0,
			        "gl43acqstat": "10"
			    },
			    "ac03vend": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    },
			    "ac03acqmode": {
			        "id": 2,
			        "version": 0,
			        "gl42acqmode": "1"
			    }
			}
		},{
			"name" : "request gift",
			"description" : "Request for gift",
			"url" : "services/acquisition/order/submit",
			"method" : "PUT",
			"body" :{
				"id": 5,
				"version": 0,
				"ac03order": "ORDER00005",
			    "ac03copies": 1,
			    "ac03status": {
			        "id": 12,
			        "version": 0,
			        "gl43acqstat": "10"
			    },
			    "ac03vend": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    },
			    "ac03acqmode": {
			        "id": 3,
			        "version": 0,
			        "gl42acqmode": "2"
			    }
			}
		},{
			"name" : "blanket order",
			"description" : "Send blanket order",
			"url" : "services/acquisition/order/submit",
			"method" : "PUT",
			"body" :{
				"id": 6,
				"version": 0,
				"ac03order": "ORDER00006",
			    "ac03copies": 1,
			    "ac03status": {
			        "id": 12,
			        "version": 0,
			        "gl43acqstat": "10"
			    },
			    "ac03vend": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    },
			    "ac03acqmode": {
			        "id": 4,
			        "version": 0,
			        "gl42acqmode": "3"
			    }
			}
		},{
			"name" : "invoice entry",
			"description" : "Enter Invoice",
			"url" : "services/acquisition/order/invoice",
			"method" : "PUT",
			"body" :[{
				"ac05invno": "INVNO00001",
			    "ac05amt": 10,
			    "ac05stat": {
			    	"id": 40,
			        "version": 0,
			        "gl43acqstat": "N"
			    },
			    "ac05supplier": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    },
			    "ac05order": {
					"id": 7,
					"version": 0,
					"ac03order": "ORDER00007",
				    "ac03copies": 1,
				    "ac03status": {
				    	"id": 16,
				        "version": 0,
				        "gl43acqstat": "20"
				    },
				    "ac03invstat": {
				    	"id": 16,
				        "version": 0,
				        "gl43acqstat": "20"
				    },
				    "ac03vend": {
				        "id": 2,
				        "version": 0,
				        "gl39code": "STT"
				    },
				    "ac03acqmode": {
				        "id": 1,
				        "version": 0,
				        "gl42acqmode": "0"
				    }
				}
			},{
				"ac05invno": "INVNO00001",
			    "ac05amt": -1,
			    "ac05stat": {
			    	"id": 40,
			        "version": 0,
			        "gl43acqstat": "N"
			    },
			    "ac05discount": 0.1,
			    "ac05supplier": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    }
			},{
				"ac05invno": "INVNO00001",
			    "ac05amt": 2,
			    "ac05stat": {
			    	"id": 40,
			        "version": 0,
			        "gl43acqstat": "N"
			    },
			    "ac05hchrg": true,
			    "ac05supplier": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    }
			}]
		},{
			"name" : "request for payment",
			"description" : "Request for payment of invoice",
			"url" : "services/acquisition/order/requestForPayment",
			"method" : "PUT",
			"body" :[{
				"ac05invno": "REQPAY0001",
			    "ac05amt": 10,
			    "ac05stat": {
			    	"id": 40,
			        "version": 0,
			        "gl43acqstat": "N"
			    },
			    "ac05supplier": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    },
			    "ac05order": {
					"id": 10,
					"version": 0,
					"ac03order": "ORDER00010",
				    "ac03copies": 1,
				    "ac03status": {
				    	"id": 16,
				        "version": 0,
				        "gl43acqstat": "20"
				    },
				    "ac03invstat": {
				    	"id": 17,
				        "version": 0,
				        "gl43acqstat": "21"
				    },
				    "ac03vend": {
				        "id": 2,
				        "version": 0,
				        "gl39code": "STT"
				    },
				    "ac03acqmode": {
				        "id": 1,
				        "version": 0,
				        "gl42acqmode": "0"
				    }
				}
			},{
				"ac05invno": "REQPAY0001",
			    "ac05amt": -1,
			    "ac05stat": {
			    	"id": 40,
			        "version": 0,
			        "gl43acqstat": "N"
			    },
			    "ac05discount": 0.1,
			    "ac05supplier": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    }
			},{
				"ac05invno": "REQPAY0001",
			    "ac05amt": 2,
			    "ac05stat": {
			    	"id": 40,
			        "version": 0,
			        "gl43acqstat": "N"
			    },
			    "ac05hchrg": true,
			    "ac05supplier": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    }
			}]
		},{
			"name" : "record payment",
			"description" : "Record payment for invoice",
			"url" : "services/acquisition/order/recordPayment",
			"method" : "PUT",
			"body" :[{
				"ac05invno": "RECPAY0001",
			    "ac05amt": 10,
			    "ac05stat": {
			    	"id": 41,
			        "version": 0,
			        "gl43acqstat": "R"
			    },
			    "ac05supplier": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    },
			    "ac05order": {
					"id": 11,
					"version": 0,
					"ac03order": "ORDER00011",
				    "ac03copies": 1,
				    "ac03status": {
				    	"id": 16,
				        "version": 0,
				        "gl43acqstat": "20"
				    },
				    "ac03invstat": {
				    	"id": 19,
				        "version": 0,
				        "gl43acqstat": "22"
				    },
				    "ac03vend": {
				        "id": 2,
				        "version": 0,
				        "gl39code": "STT"
				    },
				    "ac03acqmode": {
				        "id": 1,
				        "version": 0,
				        "gl42acqmode": "0"
				    }
				}
			},{
				"ac05invno": "RECPAY0001",
			    "ac05amt": -1,
			    "ac05stat": {
			    	"id": 41,
			        "version": 0,
			        "gl43acqstat": "R"
			    },
			    "ac05discount": 0.1,
			    "ac05supplier": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    }
			},{
				"ac05invno": "RECPAY0001",
			    "ac05amt": 2,
			    "ac05stat": {
			    	"id": 41,
			        "version": 0,
			        "gl43acqstat": "R"
			    },
			    "ac05hchrg": true,
			    "ac05supplier": {
			        "id": 2,
			        "version": 0,
			        "gl39code": "STT"
			    }
			}]
		}]
	} ]
	$scope.hello = "Hello world";
	$scope.modules = modules;
	$scope.module = {};
	$scope.$watch('module', function() {
		$scope.services = $scope.module.services;
	})
	$scope.services = $scope.module.services;
	$scope.service = {};
	$scope.$watch('service', function() {
		$scope.jsonBody = angular.toJson($scope.service.body);
	})
	$scope.submit = function() {
		console.log($scope.jsonBody);
		$http({
			method : $scope.service.method,
			url : $scope.service.url,
			data : $scope.jsonBody,
			headers: {
		        "Content-Type": "application/json"
		    }
		}).success(function(data, status) {
			$scope.status = status;
			$scope.data = data;
		}).error(function(data, status) {
			$scope.data = data || "Request failed";
			$scope.status = status;
		});
	};
}