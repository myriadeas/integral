'use strict';

define([ 'app'], function(integralApp) {
	/* Services */
	integralApp.factory('Navigation', function($log, $location, Localization) {
		return {
			isActiveUrl : function(href) {
				return $location.path() == href;
			},
			getBreadcrumb : function(path) {
				var pathArray = path.split('/');
				var breadcrumb = new Array();
				var currentPath = "/";
				var localizationKey = "";
				function isInt(n) {
					   return n % 1 == 0;
				}
				angular.forEach(pathArray, function(value, key) {
					if (value.length > 0) {
						currentPath = currentPath + value + "/";
                        if(localizationKey.length > 0 ) {
                            localizationKey += ".";
                        }
                        localizationKey += value;
						this.push({
							"path" : currentPath,
							"value" : value
						});
					}
				}, breadcrumb);
				return breadcrumb;
			}
		};
	});
});
