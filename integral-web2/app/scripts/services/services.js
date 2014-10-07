'use strict';

define(['app'], function (integralApp) {
integralApp.factory('Reserve', function($http, $rootScope, integralMysticBaseUrl) {
	return {
		reserveItem: function(reserveRequest) {
			$http({
				method : "POST",
				url : integralMysticBaseUrl + "/services/circulation/reserve",
				data: reserveRequest
			}).success(function(data, status, headers, config) {
				$rootScope.$broadcast("reserve-success", data);
			}).error(function(data, status, headers, config) {
				$rootScope.$broadcast("reserve-fail", data);
			});
		}
	};
});

integralApp.factory('Recall', function($http, $rootScope, integralMysticBaseUrl) {
	return {
		getAccession: function(id){
			$http({
				method : "GET",
				url : integralMysticBaseUrl + "/repository/ctdocm/search/findByCt03matno?ctmatm=" + id
			}).success(function(data, status, headers, config) {
				$rootScope.$broadcast("accession-success", data.content);
			}).error(function(data, status, headers, config) {
			});	
		},
		recallItem: function(recallRequest) {
			$http({
				method : "POST",
				url : integralMysticBaseUrl + "/services/circulation/recall",
				data: recallRequest
			}).success(function(data, status, headers, config) {
				$rootScope.$broadcast("recall-success", data);
			}).error(function(data, status, headers, config) {
				$rootScope.$broadcast("recall-fail", data);
			});			
		}
	};
});

integralApp.factory('ReleaseCirculation', function($http, $rootScope, Restangular, integralMysticBaseUrl) {
	return {
		getObjectCtdocm: function(id){
			var ctdocmRest = Restangular.one('ctdocm', id);
			ctdocmRest.get().then(function(data){
				$rootScope.$broadcast("objectCtdocm-success", data);
			}, function(errorResponse) {
				$rootScope.$broadcast("objectCtdocm-fail", data);
			});
		},
		release: function(releaseCirculationRequest) {
			$http({
				method : "POST",
				url : integralMysticBaseUrl + "/services/circulation/release",
				data : releaseCirculationRequest
			}).success(function(data, status, headers, config) {
				$rootScope.$broadcast("releaseCirculation-success", data);
			}).error(function(data, status, headers, config) {
				$rootScope.$broadcast("releaseCirculation-fail", data);
			});	
		}
	}
});

integralApp.factory('ErrorBinder', function ($log) {
    return {
        bind: function (form, errorResponse) {
            console.log(errorResponse);
            if(errorResponse.status == 400) {
                this.constraints(form, errorResponse.data.errors);
            }
            if(errorResponse.status == 409) {
                this.conflict(form, errorResponse);
            }
        },
        constraints: function(form, messages) {
        	var ErrorBinder = this;
            $.each(messages, function(key, message) {
                var formInputName = ErrorBinder.getFormInputNameFromMessage(message);
                $log.log('formInputName=' + formInputName);
                $log.log('form=', form);
                form[formInputName].$setValidity('constraint', false);
                
                form[formInputName].$error['constraintObject'] = message;
                
            });
        },
        conflict: function(form, errorResponse) {
            form.$setValidity('uniqueConstraint', false);
        },
        reset: function(form) {
            form.$setValidity('uniqueConstraint', true);
            if(!angular.isUndefined(form.$error.constraint)) {
                $.each(form.$error.constraint, function(key, error){
                    form.$error.constraint[0].$setValidity('constraint', true);
                });
            }
            
        }, 
        getFormInputNameFromMessage: function(message) {
           return this.getFormInputName(message.entity, message.property);
        }, 
        getFormInputName: function(entity, property) {
        	//var formInputName = 'input' + entity.substring(entity.lastIndexOf('.') + 1, entity.length) + property.substring(0,1).toUpperCase() + property.substring(1);
            var formInputName = 'input' + property.substring(0,1).toUpperCase() + property.substring(1);
            return formInputName;
        },
		getFormInputName2: function(entity, property) {
        	var formInputName = 'input_my.com.myriadeas.dao.domain.' + entity + '.' + property;
            return formInputName;
        }
    };
});

integralApp.factory('Tooltips', function($log) {
	return{
		show: function(hasFocus, field, $scope){
			var tooltip = {};
			tooltip.patron;
			tooltip.password;
			tooltip.emailAdd;
			tooltip.newIc;
			tooltip.oldIc;
			$scope.showTooltip = tooltip;
	        $scope.showTooltip[field] = hasFocus;
		}
	};
});

integralApp.factory('UserTransactionHistory', function($log) {
	return {
		add: function(id, data) {
			$log.log("Entering add (", "id=", id, ",data=", data, ")");
			if (integralApp.isEmpty(sessionStorage.getItem(id))){
				$log.log("Session Storage doesn't contain transaction with id", id, ".Inserting a new array");
				var stringArray = '[]';
				sessionStorage.setItem(id, stringArray);
			}
      var transactions = JSON.parse(sessionStorage.getItem(id));
			
      $log.log("Data= ", data);
			if(!angular.isUndefined(data)) {
				$log.log("Data is defined. Inserting into transaction.");
				transactions.push(data);
				sessionStorage.setItem(id, JSON.stringify(transactions));
			}
			$log.log("Leaving add()", transactions);
			return transactions;
		},
		retrieve: function(id){
			return JSON.parse(sessionStorage.getItem(id));
		},
		clear: function(){
			return sessionStorage.clear();
		}
	}
});

integralApp.factory("ModalPopup", function($log, $modal){
	return{
		openDialog: function(data, htmlPath, controller){
			console.log(data, htmlPath, controller);
			var modalInstance = $modal.open({
	            templateUrl: htmlPath,
				controller: controller,
	            resolve: {
					items: function () {
					  return data;
					}
				}
	        });
		}
	}
});


integralApp.factory('Utility', function () {
    return {
        isNotNull: function(val) {
        	return !this.isNull(val)
        },
        isNull: function(val) { 
        	return angular.isUndefined(val) || val == null
        }
    };
});

integralApp.factory('History', function($http, $rootScope, integralMysticBaseUrl) {
	return {
		getHistory: function(id) {
			$http({
				method : "GET",
				url : integralMysticBaseUrl + "/services/circulation/"+ id + "/circulationTransactionHistory"
			}).success(function(data, status, headers, config) {
				$rootScope.$broadcast("circulation-history-loaded-success", data.content);
			}).error(function(data, status, headers, config) {
				$rootScope.$broadcast("circulation-history-loaded-failed", data);
			});
		}
	}
});

integralApp.factory('CurrentDateTime', function($log) {
	return {
		getCurrentDateTime: function() {
			var currentTime = new Date();
			return currentTime.getTime();
		}
	};
});

integralApp.factory('DatetimeUtil', function() {
	return {
		now: function() {
			var now = new Date();
			return now.getTime();
		}
	};
});


integralApp.factory('CirculationUtil', function($log, $CurrentDateTime) {
	return {
		getTransactionId: function(prefix) {
			return prefix + $CurrentDateTime.getCurrentDateTime();
		},
		getCheckinTransactionId: function() {
			var prefix = "_CI";
			return getTransactionId(prefix);
		}
	};
});




});

