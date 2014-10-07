
define(['app', 'lodash'], function (integralApp, _) {
    integralApp.factory('Gender', function(Localization) {
        var Gender = function(code) {
            this.code = code;
            this.getDescription = function() {
                return Localization.resolve('gender.' + code);
            }
        }
        
        var genders = [
            new Gender(1), new Gender(2)
        ];
        return {
            getList: function() {
                return genders;
            },
            convertToGender: function(value) {
                if(value == "MALE") {
                    return genders[0];
                } else if(value == "FEMALE") {
                    return genders[1];
                }
            }
        }
    });
    
    integralApp.factory('ItemStatus', function(Localization) {
        var ItemStatus = function(code) {
            this.code = code;
            this.getDescription = function() {
                return Localization.resolve('item.itemStatus.' + code);
            }
        }
        
        var itemStatuses = [
            new ItemStatus("AVAILABLE"), new ItemStatus("CIRCULATED"), new ItemStatus("FINAL_PROCESSING")
        ];
        
               
        return {
            getList: function() {
                return itemStatuses;
            },
            convertToItemStatus: function(value) {
                var x;
                for (x in itemStatuses){
                    if (itemStatuses[x].code == value){
                        return itemStatuses[x];
                    }
                }                
                return selectedItemStatus;
                /*    
                if(value == "AVAILABLE") {                  
                    return itemStatuses["AVAILABLE"];
                } else if(value == "CIRCULATED") {
                    return itemStatuses["CIRCULATED"];
                } else if(value == "FINAL_PROCESSING") {
                    
                    console.log("TTTTTTTTTT", itemStatuses["FINAL_PROCESSING"]);
                    console.log("UUUUUUUUUUUU", itemStatuses[2]);
                    return itemStatuses["FINAL_PROCESSING"];
                }
                */
            }
        }
    });
    
    
    integralApp.factory('InputModel', function() {
        var InputModel = function() {
            this.showLabel = true;
            this.setName = function(name) {
                this.name = name;
                return this;
            }
            this.setProperty =  function(property) {
                this.property = property;
                return this;
            }
            this.setSize = function(size) {
                this.size = size;
                return this;
            }
            this.setShowLabel = function(showLabel) {
                this.showLabel = showLabel;
            }
            this.isShowLabel = function() {
                return this.showLabel;
            }
            this.getFormName = function() {
                return "form";
            }
            this.getInputName =  function() {
                return "input" + this.property.capitalize();
            }
            this.getInputClass = function() {
                var inputClasses = {
                    "xxs":"col-md-1",
                    "xs": "col-md-2",
                    "s": "col-md-3",
                    "m": "col-md-4",
                    "l": "col-md-5",
                    "xl": "col-md-6",
                    "xxl": "col-md-8"
                };
                return inputClasses[this.size];
            }
            this.getLabelKey = function() {
                return this.name + '.' + this.property;
            }
            
        }
        return InputModel;
    });
    integralApp.directive('mFormInput', function(InputModel) {
        return {
            templateUrl : 'scripts/directives/form/formInput.html',
            restrict: 'E',
            transclude: true,
            scope: {
                form: '=form'
            },
            link : function(scope, element, attrs) {
                var inputModel = new InputModel();
                inputModel.setName(attrs.name).setProperty(attrs.property).setSize(attrs.size);
                if(attrs.noLabel) {
                    inputModel.setShowLabel(false);
                }
                scope.inputModel = inputModel;
                scope.isInvalid = function(){
                    if(angular.isUndefined(scope.form[inputModel.getInputName()])) {
                        return false;
                    }
                    return scope.form[inputModel.getInputName()].$invalid || scope.isConstraintError();
                };
                scope.isConstraintError = function() {
                    return (!angular.isUndefined(scope.form[inputModel.getInputName()]) 
                        && !angular.isUndefined(scope.form[inputModel.getInputName()].$error)
                        && !angular.isUndefined(scope.form[inputModel.getInputName()].$error.constraintObject));
                }
                
                scope.getConstraintError = function() {
                    if(scope.isConstraintError()) {
                        return scope.form[inputModel.getInputName()].$error.constraintObject.message;
                    } else {
                        return "";
                    }
                }
                
                scope.isError = function(error) {
                    return !angular.isUndefined(scope.form[inputModel.getInputName()]) && 
                    !angular.isUndefined(scope.form[inputModel.getInputName()].$error) && 
                    !angular.isUndefined(scope.form[inputModel.getInputName()].$error[error]) &&
                    scope.form[inputModel.getInputName()].$error[error];
                }
                
            }
        }
    });
    
    integralApp.directive('mFormAddress', function() {
        return {
            templateUrl : 'scripts/directives/form/formAddress.html',
            restrict: 'E',
            transclude: true,
            priority: 0,
            scope: {
                address: '=ngModel',
                form: '=form'
            },
            link : function(scope, element, attrs) {
                if(scope.address == null) {
                    scope.address = {};
                }
            }
        }
    });
    
    integralApp.directive('mFormViewAddress', function() {
        return {
            templateUrl : 'scripts/directives/form/formViewAddress.html',
            restrict: 'E',
            transclude: true,
            priority: 0,
            scope: {
                address: '=ngModel',
                form: '=form'
            },
            link : function(scope, element, attrs) {
            }
        }
    });
    
    
    
    integralApp.directive('mFormGender', function(Gender) {
        return {
            templateUrl : 'scripts/directives/form/formGender.html',
            restrict: 'E',
            transclude: true,
            priority: 0,
            scope: {
                gender: '=ngModel',
                form: '=form'
            },
            link : function(scope, element, attrs) {
                scope.genders = Gender.getList();
                scope.selectedGender = Gender.convertToGender(scope.gender);
                scope.$watch('selectedGender', function(selectedGender) {
                    if(!angular.isUndefined(selectedGender)) {
                        scope.gender = selectedGender.code;
                    }
                });
            }
        }
    });
    
    integralApp.directive('mFormItemStatus', function(ItemStatus) {
        return {
            templateUrl : 'scripts/directives/form/formItemStatus.html',
            restrict: 'E',
            transclude: true,
            priority: 0,
            scope: {
                itemStatus: '=ngModel',
                form: '=form'
            },
            link : function(scope, element, attrs) {
                scope.itemStatuses = ItemStatus.getList();
                //scope.selectedItemStatus = ItemStatus.convertToItemStatus(scope.itemStatus);
                scope.$watch("itemStatus",function(newValue,OldValue,scope){
                     if (newValue){    
                        console.log('scope.itemStatus', scope.itemStatus);
                        scope.selectedItemStatus = ItemStatus.convertToItemStatus(scope.itemStatus);
                        console.log('scope.selectedItemStatus', scope.selectedItemStatus);
                     }
                 });


                
                scope.$watch('selectedItemStatus', function(selectedItemStatus) {
                    if(!angular.isUndefined(selectedItemStatus)) {
                        scope.itemStatus = selectedItemStatus.code;
                    }
                });
            }
        }
    });
    
    
    
    integralApp.directive('mFormAuditableInfo', function() {
        return {
            require: 'ngModel',
            templateUrl : 'scripts/directives/form/formAuditableInfo.html',
            restrict: 'E',
            scope: {
                auditableModel: '=ngModel'
            }
        }
    });
    
    integralApp.directive('mFormSelect', function($injector, $compile, EntityObjectUtil) {
        return {
            require: 'ngModel',
            restrict: 'E', // only activate on element attribute,
            scope: {
                model: '=ngModel'
            },
            link: function(scope, element, attrs) {
                var repository = $injector.get(attrs.entityName.capitalize() + 'Repository');
                repository.getList().then(function(selections) {
                    scope.selections = selections;
                    element.children().attr("ng-options", "selection.getDescription() for selection in selections");
                    element.children().attr("ng-model", "model");
                    $compile(element.children())(scope);   
                    scope.$broadcast('mSelect:event:modelLoaded');
                });
                function isFirstTimeLoad(newVal, oldVal) {
                    return (!angular.isUndefined(newVal) && angular.isUndefined(oldVal));
                }
                scope.$watch('model', function(newVal, oldVal) {
                    if(isFirstTimeLoad(newVal, oldVal)) {
                        scope.$broadcast('mSelect:event:modelLoaded');
                    }
                });
                
                function isBothModelAndSelectionsLoaded() {
                    return (!angular.isUndefined(scope.model) && !angular.isUndefined(scope.selections));
                }
                
                scope.$on('mSelect:event:modelLoaded', function() {
                    if(isBothModelAndSelectionsLoaded()) {
                        scope.model = _.find(scope.selections, function (selection) {
                            return scope.model != null && selection.id === scope.model.id;
                        });
                    }
                });
            }
        }
    });
    
    integralApp.directive('mFormA', function() {
        return {
            require: 'ngModel',
            template : '<a ng-href="#{{entity.getViewLink()}}">{{entity.getDescription()}}</a>',
            restrict: 'E',
            scope: {
                entity: '=ngModel'
            }
        }
    });
    
    
    integralApp.directive('mGridAutoHeight', function() {
        return {
            priority: 2000,
            restrict: 'A',
            scope: {
                mGridAutoHeight: '=mGridAutoHeight'
            },
            link: function(scope, element, attrs) {                
                var rowHeight = 30;
                var headerHeight = 45;
                scope.$watch('mGridAutoHeight', function(){
                    var height = (scope.mGridAutoHeight.length * rowHeight + headerHeight) + "px";
                    element.children().attr('style', 'height:' + height);
                }, true);
            }
        }
    });
    
    
    integralApp.directive('mPopoverI18n', function(Localization, $compile) {
        return {
            priority: 2000,
            restrict: 'A',
            link: function ( scope, element, attrs ) {
                attrs.$set('popover', Localization.resolve(attrs['mPopoverI18n']));
                attrs.$addClass('glyphicon glyphicon-info-sign input-group-addon');
            }
        }
    });
    
    
    integralApp.directive('mFormActions', function($dialogs, ErrorBinder, $location, flash, Localization, $state) {
        return {
            templateUrl : 'scripts/directives/form/formActions.html',
            restrict: 'E',
            scope: {
                entity: '=entity',
                form: '=form',
                repo: '=repo'
            },
            link : function(scope, element, attrs) {
                scope.isShowActionButton = function(actionType) {
                    return !angular.isUndefined(attrs.actions) && attrs.actions.indexOf(actionType) >= 0;
                }
                
                scope.remove = function() {
                    var dlg = $dialogs.confirm('Please Confirm','Are you sure you want to delete this record(' + scope.entity.getDescription()  + ')?');
                    dlg.result.then(function(btn){
                        scope.entity.remove().then(function(response){
                            scope.$emit("mFormActions:delete", response);
                            flash.info = Localization.resolve(scope.entity.name + ".deleted", "Successfully deleted " + scope.entity.name) 
                                                + " ( " + scope.entity.getDescription() + " )";
                        }, function(error){
                            
                            flash.info = Localization.resolve(scope.entity.name + ".deleted.failed", "Failed to delete " + scope.entity.name)
                                                + " ( " + scope.entity.getDescription() + " )";
                        });
                    },function(btn){
                    });
                }
                
                scope.createOrSave = function() {
                    ErrorBinder.reset(scope.form);
                    if(scope.form.$invalid) {
                        $dialogs.error('Form Error', 'There are errors in the form. Please rectify again');
                        return;
                    } else {
                        function isNew() {
                            return angular.isUndefined(scope.entity.$fromServer);
                        }
                        var genericSuccessfulMessage = "";
                        var genericErrorMessage = "";
                        var action;
                        var event;
                        if(isNew()) {
                            genericSuccessfulMessage = Localization.resolve(scope.entity.name + ".created", "Successfully created " + scope.entity.name);
                            genericErrorMessage = Localization.resolve(scope.entity.name + ".created.failed", "Failed to create " + scope.entity.name);
                            action = scope.create();
                            event = "mFormActions:create";
                        } else {
                            genericSuccessfulMessage = Localization.resolve(scope.entity.name + ".updated", "Successfully updated " + scope.entity.name);
                            genericErrorMessage = Localization.resolve(scope.entity.name + ".updated.failed", "Failed to update " + scope.entity.name);
                            action = scope.update();
                            event = "mFormActions:update";
                        }
                        action.then(function(response) {
                            scope.$emit(event, response);
                            flash.info = genericSuccessfulMessage + " ( " + response.getDescription() + " )";
                        }, function(errorResponse){
                            ErrorBinder.bind(scope.form, errorResponse);
                            flash.error = genericErrorMessage;
                        });
                    }
                
                }
                scope.create = function() {
                    return scope.repo.post(scope.entity.clone());
                }
                
                scope.update = function() {
                    console.log(scope.entity.homeAddress);
                    return scope.entity.patch();
                }
            }
        }
    });

});