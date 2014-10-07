'use strict';

define(['app','toastr'], function (integralApp, toastr) {
/* Services */
integralApp.factory('Notification', function () {
    return {
        success: function (message) {
            toastr.success(message);
        },
        error: function (message) {
            toastr.error(message);
        }
    };
});

/*
integralApp.factory('CheckoutService', function($log, $q, ServicesRestangular, Restangular, UserTransactionHistory) {
	return {
		validatePatron: function(patronId) {
			$log.log("Entering validate patron service. Parameter passed: patron id:", patronId);
			var response;
			if (patronId == "chiaweiwei"){
				response = ServicesRestangular.one('circulation/checkout/validate/patron/validatePatronResponse.json').get();
			}else{
				var deffered = $q.defer();
				ServicesRestangular.one('circulation/checkout/validate/patron/error.json').get().then(function(data){
					deffered.reject(data);
				});
				response = deffered.promise;
			}
			$log.log("Leaving validate patron service");
			return response;
		},
		getPatronDetail: function(patronId){
			$log.log("Entering get patron detail service. Parameter passed: patron id: ", patronId);
			var glpatr = ServicesRestangular.one('circulation/checkout/glpatr/search/findByGl14patrContaining/glpatr.json').get();
			$log.log("Leaving get patron detail service");
			return glpatr;
		},
		checkout: function(checkoutRequest){
			$log.log("Entering validate item service. Parameter passed: checkoutRequest: ", checkoutRequest);
			var response;
			if (checkoutRequest.itemIdentifier == "001"){
				response = ServicesRestangular.one('circulation/checkout/checkoutResponse.json').get();
			}else{
				var deffered = $q.defer();
				ServicesRestangular.one('circulation/checkout/error.json').get().then(function(data){
					deffered.reject(data);
				});
				response = deffered.promise;
			}
			$log.log("Leaving validate item service");
			return response;
		},
		updateTransactionLog: function(transaction){
			$log.log("Entering update TransactionLog service. Parameter passed: ", "transaction: ", transaction);
			var transactions = UserTransactionHistory.add(transaction.transactionId, transaction.checkoutResponse);
			$log.log("Leaving updateTransactionLog service");	
            return transactions;
		}
	};
});

integralApp.factory('CheckinService', function(UserTransactionHistory, $q, ServicesRestangular, $log) {
	return {
		checkin: function(checkinRequest) {
			$log.log("Entering checkin service. Parameter passed: ", "checkin request: ", checkinRequest);
			var response;
			if (checkinRequest.itemIdentifier == "001" || checkinRequest.itemIdentifier == "002"){
				response = ServicesRestangular.one('circulation/checkin/checkinResponse.json').get();
			}else{
				var deffered = $q.defer();
				ServicesRestangular.one('circulation/checkout/error.json').get().then(function(data){
					deffered.reject(data);
				});
				response = deffered.promise;
			}
			
			$log.log("Leaving checkin service");
            return response;
		},
		updateTransactionLog: function(transaction){
			$log.log("Entering update TransactionLog service. Parameter passed: ", "transaction: ", transaction);
			var transactions = UserTransactionHistory.add(transaction.transactionId, transaction.checkinResponse);
			$log.log("Leaving updateTransactionLog service");	
            return transactions;
		},
		getItemInformation: function(itemIdentifier, circulationIdentifier){
			$log.log("Entering getItemInformation service. Parameter passed: ", "item identifier: ", itemIdentifier, "circulation identifier: ", circulationIdentifier);
			$log.log("Leaving getItemInformation service");
			var response;
			if (itemIdentifier == "001"){
				response = ServicesRestangular.one('circulation/checkin/item/information/itemInformation-reservationDetail.json').get({itemIdentifier: itemIdentifier, cicircIdentifier: circulationIdentifier});
			}else{
				response = ServicesRestangular.one('circulation/checkin/item/information/itemInformation.json').get({itemIdentifier: itemIdentifier, cicircIdentifier: circulationIdentifier});
			}
            return response;
		}
	};
});
*/
integralApp.factory('ItemInformationService', function($log, Utility) {
	return {
		getReservationDetail: function(itemInformation) {
			$log.log("Entering getReservationDetail service. Parameter passed: ", "item information: ", itemInformation);
			var reservationDetail = null;
			$log.log("Curculation Status= ", itemInformation.circulationStatus);
			if (itemInformation.circulationStatus == "ON_HOLD_SHELF"){
				reservationDetail = itemInformation.reservationDetail;
	        }
			$log.log("Leaving getReservationDetail service. Reservation detail: ", reservationDetail);
            return reservationDetail;
		}
	};
});

integralApp.factory('Renew', function($http, $rootScope, integralMysticBaseUrl) {
	return {
		validatePatron: function(patronId) {
			$http({
				method : "GET",
				url : integralMysticBaseUrl + "/repository/glpatr/search/findByGl14patr?search=" + patronId
			}).success(function(data, status, headers, config) {
				$rootScope.$broadcast("renew-patron-validation-success", data.content[0]);//TODO - it is a list
			}).error(function(data, status, headers, config) {
				$rootScope.$broadcast("renew-patron-validation-failed", data);
			});	
		},
		getItemInformations: function(patronId){
			$http({
				method : "GET",
				url : integralMysticBaseUrl + "/services/circulation/" + patronId + "/onLoanItems"
			}).success(function(data, status, headers, config) {
				$rootScope.$broadcast("get-item-informations-success", data);
			}).error(function(data, status, headers, config) {
			});
		},
		renewItem: function(renewRequest){
			$http({
				method : "POST",
				url : integralMysticBaseUrl + "/services/circulation/renew",
				data: renewRequest
			}).success(function(data, status, headers, config) {
				$rootScope.$broadcast("renew-success", data);
			}).error(function(data, status, headers, config) {
				$rootScope.$broadcast("renew-fail", data);
			});
		}
	};
});

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
            if(errorResponse.status == 400) {
                this.constraints(form, errorResponse.data.messages);
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
        	var formInputName = 'input' + entity.substring(entity.lastIndexOf('.') + 1, entity.length) + property.substring(0,1).toUpperCase() + property.substring(1);
            return formInputName;
        },
		getFormInputName2: function(entity, property) {
        	var formInputName = 'input_my.com.myriadeas.dao.domain.' + entity + '.' + property;
            return formInputName;
        }
    };
});

integralApp.factory('Lookup', function ($rootScope, Restangular) {
    return {    	
		loadPropertyDomain: function (lookups, lookupId) {
            var rest = lookups.rest;
            var $scope = lookups.$scope;
            var domain = $scope[lookups.name];
            $.each(lookups.properties, function(key, property){
            	var propertyName = property.name;
            	 if (!jQuery.isEmptyObject(property)) {       		
            		rest.one(propertyName).get().then(function(response){
            			   delete response.parentResource; //TODO - this prevent from update - if remove, it is break restangular?
            			   console.log("property reponse=", response);
		        		   domain[propertyName] = response;
		                   $rootScope.$broadcast(propertyName + '-loaded', response);
		                }, function(errorResponse) {
		             	 
		            });
            	 }
            });
        },
    
    	loadDuplicateProperty: function (selectedFields, lookups) {
    	    var Lookup = this;
    	    if (!jQuery.isEmptyObject(selectedFields)) {
    	    	$.each(lookups.properties, function(key, property){
    	        	var propertyName = property.name;
    	        	var isRemoveProperty= true;
    	    		$.each(selectedFields, function(index, selectedProperty){	
		        	    if (selectedProperty == propertyName) {		
		        	    	isRemoveProperty = false;
		        	    }
	    	        });
    	    		
    	    		if(isRemoveProperty) {
    	    			delete property.name;
    	    			delete property.domain;
    	    		}     
    	    	});
	    		Lookup.loadPropertyDomain(lookups);
    	    }
    	}
    };
});

integralApp.factory('SelectionLookup', function ($rootScope, $log, Restangular) {
    return {
        registerPropertyListener: function(lookups) {
    		var $scope = lookups.$scope;
            var domain = $scope[lookups.name];
            var SelectionLookup = this;
            $.each(lookups.properties, function(key, property){
        	    var propertyName = property.name;
        	    var propertyDomain = property.domain;
                $scope.$on(propertyName + '-loaded', function() {
                    SelectionLookup.loadPropertyValue(lookups, property);
                });
            });
        },
        loadPropertyList: function(lookups){
    		var $scope = lookups.$scope;
            $.each(lookups.properties, function(key, property){
            	var propertyDomain = property.domain;
                var propertyName = property.name;
            	if(angular.isUndefined($scope[propertyDomain + 's'])) {
            		//pluralize the domain to retrieve
            		Restangular.all(propertyDomain).getList().then(function(response){
            			$.each(response.content, function(index, object){
                			delete object.links;
            			});
            			$scope[propertyDomain + 's'] = response.content;
                        $scope[propertyName + 'Selections'] = response.content;
                        $rootScope.$broadcast(propertyName + '-loaded');
                    }, function(errorResponse){
                    });
                } else {
                    $scope[propertyName + 'Selections'] = $scope[propertyDomain + 's'];
                    $rootScope.$broadcast(propertyName + '-loaded');
                }
                
            });
    	}, 
        loadPropertyValue: function(lookups, property) {
            var $scope = lookups.$scope;
            var associationDomains = $scope[property.name + 'Selections'];
            var domain = $scope[lookups.name];
            var propertyDomain = $scope[lookups.name][property.name];            
            if(angular.isUndefined(associationDomains) || angular.isUndefined(propertyDomain)) {
                return;
            }
            $.each(associationDomains, function(key, associationDomain){
                if(associationDomain.id == propertyDomain.id) {
                    domain[property.name] = associationDomain;
                }
	        });
        }
    };
});

integralApp.factory('ConfirmDeleteDialog', function($dialog) {
	return {
		prompt: function() {
			var title = 'Confirm delete';
			var msg = 'Are you sure you want to delete this record?';
			var btns = [{result:false, label: 'Cancel'}, {result:true, label: 'OK', cssClass: 'btn-primary'}];
		    return $modal.messageBox(title, msg, btns);
		}
	};
});

integralApp.factory('Localization', function (localize, $log) {
    return {
        resolve: function (originalString, defaultString) {
        	var newString;
        	try{	
        		newString = localize.getLocalizedString(originalString);  
        		$log.log("new message=", newString);
        	} catch (error){
        		$log.log("fail to resolve", error);
        	}
        	if (newString == ""){
        		newString = defaultString;
        	}
        	return newString;
        }
    };
});


integralApp.factory('DomainUtils', function ($log) {
    return {
        getGeneralInfo: function (domain) {
        	var generalInfo = {
        			id : domain.id,
        			createdDate : domain.createdDate,
        			createdBy : domain.createdBy,
        			lastModifiedDate : domain.lastModifiedDate,
        			lastModifiedBy : domain.lastModifiedBy
        	};
        	return generalInfo;
        },
        updateModel: function($scope, response) {
        	$log.log("update model, response=", response);
        	//items is a list. always get the first one. Why? Don't know. Based on the existing code only. TODO
        	$scope[response.parentDomainName][response.columnName] = response.items[0];
        	//again remove links manually;
        	
        }
    };
});

integralApp.factory('BeanValidation', function ($http, $log, integralMysticBaseUrl) {
    return {
    	getConstraintsData: function(domain) {
			var url = integralMysticBaseUrl + '/services/beanvalidation/constrainedProperties/' + domain;
			// url = 'data/validation/' + domain;
    		return $http({method: 'GET', url: url, cache: true});
    	},
    	getValidatorType: function(validatorClass) {
    		var validatorType;
    		switch(validatorClass) {
    			case 'org.hibernate.validator.internal.constraintvalidators.LengthValidator' : 
    				validatorType = 'Length';
    				break;
    			case 'org.hibernate.validator.internal.constraintvalidators.NotBlankValidator' : 
    				validatorType = 'NotBlank';
    				break;
    			case 'org.hibernate.validator.internal.constraintvalidators.MinValidatorForNumber' : 
    				validatorType = 'Min';
    				break;
    			case 'org.hibernate.validator.internal.constraintvalidators.MaxValidatorForNumber' : 
    				validatorType = 'Max';
    				break;
    			case 'org.hibernate.validator.internal.constraintvalidators.NotNullValidator' :
    				validatorType = 'NotNull';
    				break;
    			case 'org.hibernate.validator.internal.constraintvalidators.EmailValidator' :
    				validatorType = 'Email';
    				break;
    			case 'org.hibernate.validator.internal.constraintvalidators.URLValidator' :
    				validatorType = 'URL';
    				break;
    			case 'org.hibernate.validator.internal.constraintvalidators.PatternValidator' :
    				validatorType = 'Pattern';
    				break;
    			default:
    				validatorType = 'Unknown';
    				break;
    		}
    		return validatorType;
    	},
        getConstraints: function (data) {
        	$log.log("bean validation constraints", data);
        	var constraints = [];
        	var constraintDescriptors = data.constraintDescriptors;
        	var BeanValidation = this;
        	$.each(constraintDescriptors, function(key, constraintDescriptor){
        		$log.log("constraintDescriptor", constraintDescriptor);	
        		$.each(constraintDescriptor.constraintValidatorClasses, function(key, validatorClass){
        			$log.log('validatorClass', validatorClass);
        			var validatorType = BeanValidation.getValidatorType(validatorClass);
        			var constraint = {};
        			switch(validatorType) {
        				case 'Length':
        					var maxConstraint = {};
        					maxConstraint.type = 'maxlength';
        					maxConstraint.error = 'maxlength';
        					maxConstraint.value = constraintDescriptor.attributes.max;
        					maxConstraint.message = 'default.invalid.max.length.message';
        					constraints.push(maxConstraint);
        					var minConstraint = {};
        					minConstraint.type = 'ng-minlength';
        					minConstraint.error = 'minlength';
        					minConstraint.value = constraintDescriptor.attributes.min;
        					minConstraint.message = 'default.invalid.min.length.message';
        					constraint = minConstraint;
        					break;
        				case 'NotNull':
            				constraint.type = 'ng-required';
            				constraint.error = 'required';
            				constraint.value = 'true';
        					constraint.message = 'default.not.null.message';
        					break;
        				case 'NotBlank':
            				constraint.type = 'ng-required';
            				constraint.error = 'required';
            				constraint.value = 'true';
        					constraint.message = 'default.not.blank.message';
        					break;
        				case 'Min':
            				constraint.type = 'min';
            				constraint.error = 'min';
            				constraint.value = constraintDescriptor.attributes.value;
        					constraint.message = 'default.invalid.min.message';
        					break;
        				case 'Max':
            				constraint.type = 'max';
            				constraint.error = 'max';
            				constraint.value = constraintDescriptor.attributes.value;
        					constraint.message = 'default.invalid.max.message';
        					break;
        				case 'Email':
            				constraint.type = 'type';
            				constraint.value = 'email';
            				constraint.error = 'email';
        					constraint.message = 'default.invalid.email.message';
        					break;
        				case 'URL':
            				constraint.type = 'type';
            				constraint.value = 'url';
            				constraint.error = 'url';
        					constraint.message = 'default.invalid.url.message';
        					break;
        				case 'Pattern1':
            				constraint.type = 'ng-pattern';
            				constraint.error = 'pattern';
            				constraint.value = BeanValidation.convertJavaRegexpToJSRegexp(constraintDescriptor.attributes.regexp);
            				constraint.message = 'default.invalid.pattern.message';
        					break;
        				default:
        					return;
        			}
        			constraints.push(constraint);
        		});
        	});
        	$log.log("constraints", constraints);
        	return constraints;
        },
        convertJavaRegexpToJSRegexp : function (javaRegexp) {
        	var jsRegexp = javaRegexp;
        	return jsRegexp;
        }
    };
});

integralApp.factory('UniquenessChecker', function($http, $log, $q) {
    return {
        apiPath:'http://localhost:7788/services/rest/get/isUnique/tablecolumns',
        check: function(jsonStr) {
            var deferred = $q.defer();
            var unique = true;
            $http({ 
                method: 'GET', 
                params: jsonStr,
                url: this.apiPath
            }).
            success(function (data, status, headers, config) {
              if (data > 0){
                  unique = false;           
              }
              deferred.resolve(unique);
            }).
            error(function (data, status, headers, config) {
                // deferred.reject("An error occured while checking
				// uniqueness");
            })
            return deferred.promise;
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
			$log.log(data, htmlPath, controller);
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

