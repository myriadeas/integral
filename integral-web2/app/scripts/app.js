'use strict';

/* App Module */


define([ 'angular' ], function(angular) {
	var integralApp = angular.module('integralweb', [ 'ui.bootstrap', 'restangular',
			'localization', 'ngGrid', 'ui.router','ngRoute', 'angular-flash.service', 
            'angular-flash.flash-alert-directive', 
            'dialogs','checklist-model','integral.foundation','integral.circulation', 'integral.cataloguing']);    
    integralApp.config(function (TOKEN, RestangularProvider) {
        var ticket = TOKEN.ticket;
        RestangularProvider.setDefaultRequestParams({ticket: ticket});
    })
	
	integralApp.config(function (flashProvider) {
            // Support bootstrap 3.0 "alert-danger" class with error flash types
            flashProvider.errorClassnames.push('alert-danger');
    });
	
	
	integralApp.config(function(RestangularProvider, TOKEN) {
        RestangularProvider.setBaseUrl(TOKEN.serviceUrl + "/repository");
    });
    
    
    integralApp.factory('RepositoryRestangular', function(Restangular, flash, TOKEN) {
      return Restangular.withConfig(function(RestangularConfigurer) {
        RestangularConfigurer.setBaseUrl(TOKEN.serviceUrl + "/repository");
        RestangularConfigurer.setFullRequestInterceptor(function(element,
				operation, route, url, headers, params) {
            //This is to convert association to uri that Spring Data Rest will convert by itself
            function convertAssociationToUri(element) {
                $.each(element, function(key, property) {
                    if (integralApp.isNotNull(property) && integralApp.isNotNull(property.id) && integralApp.isNotNull(property._links)) {
                        element[key] = property._links.self.href;
                    } else if(angular.isArray(property)){
                        convertAssociationToUri(property);
                    }
                });
            }
            function removeAuditProperty(element) {
                delete element.lastModifiedBy;
                delete element.lastModifiedDate;
                delete element.createdBy;
                delete element.createdDate;
            }
			if (operation == 'put' || operation == 'post' || operation == 'patch') {
                convertAssociationToUri(element);
                removeAuditProperty(element);
			}
			return {
				element : element,
				headers : headers,
				operation : operation,
				route : route,
				url : url,
				params : params
			};
		});
        RestangularConfigurer.addResponseInterceptor(function(data, operation, what, url, response, deferred) {
            var extractedData;
            if (operation === "getList") {
                if(angular.isUndefined(data._embedded)) {
                    extractedData = new Array();
                } else {
                    $.each(data._embedded, function(key, value){
                        extractedData = value;
                    });
                }
            } else {
                extractedData = data;
            }
            return extractedData;
        });
      });
    });
    
    integralApp.factory('ServicesRestangular', function(Restangular, TOKEN) {
      return Restangular.withConfig(function(RestangularConfigurer) {
        RestangularConfigurer.setBaseUrl(TOKEN.serviceUrl + "/services");
      });
    });
    

	// register the interceptor as a service
	integralApp.factory('hateoasOneLevelLinkInterceptor', function($q) {
		return function(promise) {
			return promise.then(function(response) {
				return response;
			}, function(response) {
				return $q.reject(response);
			});
		};
	});
    

	integralApp.config(function($httpProvider) {
		// $httpProvider.responseInterceptors.push('hateoasOneLevelLinkInterceptor');
	});

	integralApp.config(function($httpProvider) {
		// $httpProvider.responseInterceptors.push('hateoasOneLevelLinkInterceptor');
		$httpProvider.interceptors.push('httpRequestInterceptor');
		$httpProvider.responseInterceptors.push('httpInterceptor');
		var spinnerFunction = function(data, headersGetter) {
			$('#ajax-loader').show();
			return data;
		};
		$httpProvider.defaults.transformRequest.push(spinnerFunction);
	});

	integralApp.factory('httpInterceptor', function($q, $window) {
		return function(promise) {
			return promise.then(function(response) {
				$('#ajax-loader').hide();
				return response;
			}, function(response) {
				$('#ajax-loader').hide();
				return $q.reject(response);
			});
		};
	});
	integralApp.factory('httpRequestInterceptor', function ($q, $rootScope) {
		return {
			request: function (config) {
				$rootScope.$emit('$httpRequest');
				return config || $q.when(config);
			}
		};
	});
    
    
    integralApp.config(function(RestangularProvider) {
        RestangularProvider.setErrorInterceptor(
            function(resp) {
                if(resp.status =="404" && resp.data.length == 0) {
                    //Intercept to reload page. will be 404 and data length zero if logout
                    //location.reload();
                    //return false; // stop the promise chain
                    return resp;
                } else {
                    return resp;
                }
          });
    });

	// register global utility method
	integralApp.isNotNull = function(val) {
		return !integralApp.isNull(val)
	}
	integralApp.isNull = function(val) {
		return angular.isUndefined(val) || val === null
	}
	
	integralApp.isEmpty = function(val) {
		return integralApp.isNull(val) || val.length == 0;
	}
    
    integralApp.factory('Config', function(TOKEN) {
        return {
            getSolrUrl: function() {
                return {
                    'search': TOKEN.serviceUrl + '/rest/services/cataloguing/marc/query/solrOri',
                    'searchMaterials': TOKEN.serviceUrl + '/rest/services/cataloguing/marc/query/solrGetMaterials'
                }
            },
            getMysticBaseUrl: function() {
                return TOKEN.serviceUrl;
            }
        }
    });
    
    return integralApp;
});
