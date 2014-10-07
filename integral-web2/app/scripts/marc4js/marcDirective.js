define(['app', 'lodash','tv4'], function (integralApp, _, tv4) {

    integralApp.directive('sfMarcInput', ['$parse', function($parse) {
        return function(scope, element, attr) {        
            function convertSpaceToHash(str) {
                if(!angular.isUndefined(str)) {
                    return str.split(' ').join('#');
                } else {
                    return str;
                }
            }
            element.on('blur', function(event) {
                element.val(convertSpaceToHash(element.val()));
            });
        };
    }]);


    integralApp.directive('mMarcSchemaError', function() {
        return {
            templateUrl : 'scripts/marc4js/marcSchemaError.html',
            restrict: 'E',
            priority: 0,
            scope: {
                $errors: '=ngModel',
            },
            link : function(scope, element, attrs) {
                function ValidationError(code, dataPath, message) {
                    this.code = code;
                    this.dataPath = dataPath;
                    this.message = message;
                }                        
                ValidationError.prototype.constructor = ValidationError;
                ValidationError.prototype.name = 'ValidationError';
                ValidationError.prototype.getDataPathArray = function() {
                        return this.dataPath.split("/");
                }
                ValidationError.prototype.getTagName = function() {
                    if(this.dataPath == "" && this.code == OBJECT_REQUIRED) {
                        return this.message.substring(this.message.length - 3);
                    }
                    return this.getDataPathArray()[1];
                }
                ValidationError.prototype.getSubfieldName = function() {
                    if(this.isSubfieldError()) {
                        if(this.code == tv4.errorCodes.OBJECT_REQUIRED) {
                            return this.message.substring(this.message.length-1);
                        } else {
                            return this.getDataPathArray()[4];
                        }
                    } else {
                        return "";
                    }
                }
                ValidationError.prototype.getPosition = function() {
                    return this.getDataPathArray().length > 2 ? this.getDataPathArray()[1] : "";
                }
                ValidationError.prototype.isSubfieldError = function() {
                    return this.getDataPathArray().length > 3 && this.getDataPathArray()[3] == "subfields";
                }

                ValidationError.prototype.isIndicator1Error = function() {
                    return this.getDataPathArray().length > 3 && this.getDataPathArray()[3] == "ind1";
                }

                ValidationError.prototype.isIndicator2Error = function() {
                    return this.getDataPathArray().length > 3 && this.getDataPathArray()[3] == "ind2";
                }

                ValidationError.prototype.getMessage = function() {
                    var messageTemplate = "Tag {{tagName}}";
                    var object = {
                        tagName: this.getTagName(),
                        subfieldName: this.getSubfieldName()
                    };
                    if(this.isSubfieldError()) {
                            messageTemplate += ": Subfield ({{subfieldName}})";
                    } 
                    if(this.code == tv4.errorCodes.UNKNOWN_PROPERTY) {
                        messageTemplate += " is not allowed.";
                    }
                    if(this.isIndicator1Error()) {
                        messageTemplate += ": Indicator 1 is not valid.";
                    }
                    if(this.isIndicator2Error()) {
                        messageTemplate += ": Indicator 2 is not valid.";
                    }                
                    if(this.code == tv4.errorCodes.ARRAY_LENGTH_LONG) {
                        messageTemplate += " is non repeatable.";
                    }
                    if(this.code == tv4.errorCodes.OBJECT_REQUIRED) {
                        messageTemplate += " is mandatory.";
                    }
                    if(this.code == tv4.errorCodes.STRING_PATTERN) {
                        messageTemplate +=" data format is not valid.";
                        
                    }
                    return _.template(messageTemplate, object);
                }
                scope.$watch('$errors.errors', function($errors) {
                    if(angular.isUndefined($errors)) {
                        return;
                    }
                    var errors = []
                    $.each($errors, function(index, error) {
                        errors.push(new ValidationError(error.code, error.dataPath, error.message));
                    });
                    scope.errors = errors;
                });
            }
        }
    });
});
