
define(['app','lodash'], function (integralApp, _) {
//TODO should move to underscore plugins
/* Directives */
integralApp.directive('errSrc', function() {
	return {
		link : function(scope, element, attrs) {
			element.bind('error', function() {
				element.attr('src', attrs.errSrc);
			});
		}
	}
});

var INTEGER_REGEXP = /^\-?\d*$/;
integralApp.directive('integer', function() {
  return {
    require: 'ngModel',
    link: function(scope, elm, attrs, ctrl) {
      ctrl.$parsers.unshift(function(viewValue) {
        if (INTEGER_REGEXP.test(viewValue)) {
          // it is valid
          ctrl.$setValidity('integer', true);
          return viewValue;
        } else {
          // it is invalid, return undefined (no model update)
          ctrl.$setValidity('integer', false);
          return undefined;
        }
      });
    }
  };
});


var SHORT_REGEXP = /^\-?\d*$/;
integralApp.directive('short', function() {
	  return {
	    require: 'ngModel',
	    link: function(scope, elm, attrs, ctrl) {
	      ctrl.$parsers.unshift(function(viewValue) {
	        if (SHORT_REGEXP.test(viewValue)) {
	          // it is valid
	          ctrl.$setValidity('short', true);
	          return viewValue;
	        } else {
	          // it is invalid, return undefined (no model update)
	          ctrl.$setValidity('short', false);
	          return undefined;
	        }
	      });
	    }
	  };
	});
 
var FLOAT_REGEXP = /^\-?\d+((\.|\,)\d+)?$/;
integralApp.directive('float', function() {
  return {
    require: 'ngModel',
    link: function(scope, elm, attrs, ctrl) {
      ctrl.$parsers.unshift(function(viewValue) {
        if (FLOAT_REGEXP.test(viewValue)) {
          ctrl.$setValidity('float', true);
          return parseFloat(viewValue.replace(',', '.'));
        } else {
          ctrl.$setValidity('float', false);
          return undefined;
        }
      });
    }
  };
});


integralApp.directive('pwCheck', [function () {
	return {
		require: 'ngModel',
		link: function (scope, elem, attrs, ctrl) {
			var firstPassword = '#' + attrs.pwCheck;
			elem.add(firstPassword).on('keyup', function () {
				scope.$apply(function () {
					var v = elem.val()===$(firstPassword).val();
					ctrl.$setValidity('pwmatch', v);
				});
			});
		}
	}
}]);

integralApp.directive('checkStrength', function () {
	return {
        replace: false,
        restrict: 'EACM',
        link: function (scope, iElement, iAttrs) {
            var strength = {
                colors: ['#F00', '#F90', '#FF0', '#9F0', '#0F0'],
                mesureStrength: function (p) {

                    var _force = 0;                    
                    var _regex = /[$-/:-?{-~!"^_`\[\]]/g;
                                          
                    var _lowerLetters = /[a-z]+/.test(p);                    
                    var _upperLetters = /[A-Z]+/.test(p);
                    var _numbers = /[0-9]+/.test(p);
                    var _symbols = _regex.test(p);
                                          
                    var _flags = [_lowerLetters, _upperLetters, _numbers, _symbols];                    
                    var _passedMatches = $.grep(_flags, function (el) { return el === true; }).length;                                          
                    
                    _force += 2 * p.length + ((p.length >= 10) ? 1 : 0);
                    _force += _passedMatches * 10;
                        
                    // penality (short password)
                    _force = (p.length <= 6) ? Math.min(_force, 10) : _force;                                      
                    
                    // penality (poor variety of characters)
                    _force = (_passedMatches == 1) ? Math.min(_force, 10) : _force;
                    _force = (_passedMatches == 2) ? Math.min(_force, 20) : _force;
                    _force = (_passedMatches == 3) ? Math.min(_force, 40) : _force;
                    
                    return _force;

                },
                getColor: function (s) {

                    var idx = 0;
                    if (s <= 10) { idx = 0; }
                    else if (s <= 20) { idx = 1; }
                    else if (s <= 30) { idx = 2; }
                    else if (s <= 40) { idx = 3; }
                    else { idx = 4; }

                    return { idx: idx + 1, col: this.colors[idx] };

                }
            };

            scope.$watch(iAttrs.checkStrength, function (password) {
                if (password === '' || angular.isUndefined(password)) {
                    iElement.css({ "display": "none"  });
                } else {
                    var c = strength.getColor(strength.mesureStrength(password));
                    iElement.css({ "display": "inline" });
                    iElement.children('li')
                        .css({ "background": "#DDD" })
                        .slice(0, c.idx)
                        .css({ "background": c.col });
                }
            });

        },
        template: '<li class="point"></li><li class="point"></li><li class="point"></li><li class="point"></li><li class="point"></li>'
    };
});

integralApp.factory('beanUniquenessValidator', function(ServicesRestangular) {
    return {
        validate : function(table, columns) {
            return ServicesRestangular.oneUrl('beanvalidation').post('uniqueness',{table:table, columns:columns});
        }
    }
});

integralApp.directive('ngUnique', ['beanUniquenessValidator', function (beanUniquenessValidator) {
    return {
        require : 'ngModel',
        scope : {
            ngModel : "=ngModel"
        },
        link : function (scope, elem, attrs, ctrl) {
            
            function isFirstTimeLoaded(newValue, oldValue) {
                return (angular.isUndefined(oldValue) && !angular.isUndefined(newValue));
            }
            
            function isSameAsOriginal() {
                return ctrl.$pristine || (scope.originalNgModel === scope.ngModel);
            }
            
            scope.$watch('ngModel', function(newValue, oldValue) {
                if(isFirstTimeLoaded(newValue, oldValue)) {
                    scope.originalNgModel = newValue;
                }
            });
            
            elem.on('blur', function (evt) {
                if (isSameAsOriginal()) {
                    return;
                }
                ctrl.$setValidity('unique', true);
                scope.$apply(function () {
                    var columns = {};
                    columns[attrs.ngUnique] = scope.ngModel;
                    var table = attrs.tableName;
                    beanUniquenessValidator.validate(table, columns).then(function (response) {
                        ctrl.$setValidity('unique', response.valid);
                    });
                });
            });
        }
    }
}]);
/*-deprecated - remove in future
integralApp.directive('getTitle', ['solrUrl', function (solrUrl) {
    return {
        scope: {
            query: '@',
            results: '&'
        },
        restrict: 'E',
        controller: function ($scope, $http) {
            $scope.$watch('query', function () {
                $http({
                    method: 'JSONP',
                    url: solrUrl.search,
                    params: {
                        'json.wrf': 'JSON_CALLBACK',
                         'q': $scope.query
                    }
                })
                    .success(function (data) {
                    var docs = data.response.docs;
                    $scope.results.docs = docs;

                }).error(function () {});
            });
        },
        templateUrl: 'partials/cataloguing/title.html'
    };
}]);

integralApp.directive('getDetails', ['solrUrl', function (solrUrl) {
    return {
        scope: {
            query: '@',
            results: '&'
        },
        restrict: 'E',
        controller: function ($scope, $http) {
            $scope.$watch('query', function () {
                $http({
                    method: 'JSONP',
                    url: solrUrl.search,
                    params: {
                        'json.wrf': 'JSON_CALLBACK',
                         'q': $scope.query
                    }
                })
                    .success(function (data) {
                    var docs = data.response.docs;
                    $scope.results.docs = docs;

                }).error(function () {});
            });
        },
        templateUrl: 'partials/cataloguing/details.html'
    };
}]);
*/
});
