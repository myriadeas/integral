
define(['angular', 'lodash','jquery','cataloguing/cataloguing', 'marc4js'], 
	function (angular, _, $, cataloguing, marc4js) {
    var module = angular.module('integral.cataloguing');
    
    module
      .controller('Tag007FormCtrl', function ($scope, $http, Schema, Form, Tag, MarcService, Marc21TagForm, $modalInstance) {
        $scope.Marc21TagForm = Marc21TagForm;
        $scope.changeCategoryOfMaterial = function() {
            var type = $scope.tag[0];
            MarcService.getSchemaAndForm($scope.tag.name, type).then(function(response) {
                $scope.schema = response.schema.data;
                $scope.form = response.form.data;
                $scope.addSchemaCategoryOfMaterial();
                $scope.addFormCategoryOfMaterial();
                $scope.tag.reset();
                $scope.tag.init(response.schema.data);
                $scope.tag[0] = type;
            });
        }
        $scope.$watch('tag.0', function(newCategoryOfMaterial, oldCategoryOfMaterial) {
            if(!angular.isUndefined(newCategoryOfMaterial) && !angular.isUndefined(oldCategoryOfMaterial) && newCategoryOfMaterial != oldCategoryOfMaterial) {
                $scope.changeCategoryOfMaterial();
            }
        });
        $scope.addSchemaCategoryOfMaterial = function() {
            $scope.schema.properties[0].enum = [
            'a','c','d','f','g','h','k','m','o','q','r','s','t','v','z'
            ];
        };
        $scope.addFormCategoryOfMaterial = function(){
            var categoryOfMaterial = _.find($scope.form, function (formField) { return formField.key == 0 });
            categoryOfMaterial.titleMap = {
                  "a": "Map",
                  "c": "Electronic Resource",
                  "d": "Globe",
                  "f": "Tactile Material",
                  "g": "Projected Graphic",
                  "h": "Microform",
                  "k": "Nonprojected Graphic",
                  "m": "Motion Picture",
                  "o": "Kit",
                  "q": "Notated Music",
                  "r": "Remote-sensing Image",
                  "s": "Sound Recording",
                  "t": "Text",
                  "v": "Video Recording",
                  "z": "Unspecified"
            };
        }
        Tag.init(Schema.data);
        $scope.tag = Tag;
        $scope.schema = Schema.data;
        $scope.form = Form.data;
        $scope.addSchemaCategoryOfMaterial();
        $scope.addFormCategoryOfMaterial();
        $scope.save = function () {
            $modalInstance.close($scope.tag);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    });

    module
      .controller('Tag006FormCtrl', function ($scope, $http, Schema, Form, Tag, MarcService, Marc21TagForm, $modalInstance) {
        $scope.Marc21TagForm = Marc21TagForm;
        $scope.changeTypeOfMaterial = function() {
            var type = $scope.tag.typeOfMaterial;        
            MarcService.getSchemaAndForm($scope.tag.name, type).then(function(response) {
                $scope.schema = response.schema.data;
                $scope.addSchemaTypeOfMaterial();
                $scope.form = response.form.data;
                $scope.addFormTypeOfMaterial();
                $scope.tag.reset();
                $scope.tag.init(response.schema.data);
            });
        }
        $scope.$watch('tag.typeOfMaterial', function(newTypeOfMaterial, oldTypeOfMaterial) {
            if(!angular.isUndefined(newTypeOfMaterial) && !angular.isUndefined(oldTypeOfMaterial) && newTypeOfMaterial != oldTypeOfMaterial) {
                $scope.changeTypeOfMaterial();
            }
        });
        $scope.addSchemaTypeOfMaterial = function() {
            $scope.schema.properties.typeOfMaterial = {
                "title": "Type of Material",
                "type": "string",
                "minLength": 0,
                "maxLength": 2,
                enum: [
                    'bk','cf','cr','mp','mu','mx','vm'
                ],
                "default": "bk"
            };
        };
        $scope.addFormTypeOfMaterial = function(){
            $scope.form.splice(0,0, {
                key: "typeOfMaterial",
                titleMap: {
                  "bk": "Books",
                  "cf": "Computer Files",
                  "cr": "Continuing Resources",
                  "mp": "Maps",
                  "mu": "Music",
                  "mx": "Mixed Materials",
                  "vm": "Visual Materials"
                },
                type: "select"
            });
        }
        Tag.init(Schema.data);
        $scope.tag = Tag;
        $scope.schema = Schema.data;
        $scope.form = Form.data;
        $scope.addSchemaTypeOfMaterial();
        $scope.addFormTypeOfMaterial();
        $scope.save = function () {
            $modalInstance.close($scope.tag);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    });
    module
      .controller('LeaderFormCtrl', function ($scope, $http, Schema, Form, Tag, Marc21TagForm, $modalInstance) {
        Tag.init(Schema.data);
        $scope.tag = Tag;
        $scope.schema = Schema.data;
        $scope.form = Form.data;
        $scope.Marc21TagForm = Marc21TagForm;
        
        $scope.save = function () {
            $modalInstance.close($scope.tag);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    });

    module
      .controller('Tag008FormCtrl', function ($scope, $http, Schema, Form, $q, Tag, MarcService, Marc21TagForm, $modalInstance) {
        $scope.Marc21TagForm = Marc21TagForm;
        $scope.populateLanguageAndCountry = function(form) {
            var deferred = $q.defer();
            MarcService.getCounteriesAndLanguages().then(function(response){
                var countries = _.filter(form, function(field) {
                    return field.parser == "country";
                });
                $.each(countries, function(index, country) {
                    country.titleMap = response.countries;
                });
                var languages = _.filter(form, function(field) {
                    return field.parser == "language";
                });
                $.each(languages, function(index, language) {
                    language.titleMap = response.languages;
                });
                deferred.resolve(form);
            });
            return deferred.promise;
        }
        
        $scope.populateLanguageAndCountry(Form.data).then(function(response) {
            Tag.init(Schema.data);
            $scope.tag = Tag;
            $scope.schema = Schema.data;
            $scope.form = Form.data;
        });
        
        $scope.save = function () {
            $modalInstance.close($scope.tag);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    });

    module.controller('MaterialCreateCtrl', function ($scope, Templates, MarcService, $location, Schema) {
        $scope.template = Templates[0];
        $scope.templates = Templates;
        $scope.select = function (template) {
            MarcService.getTemplate(template).then(function(response) {
                var newRecord = marc4js.newRecord(response.data, Schema.data);
                MarcService.save(newRecord).then(function(response) {
                    $location.path('cataloguing/material/' + parseInt(response.getId()) + '/edit');
                    
                });
            });
        };
    });
    
    module.controller('MaterialEditCtrl', function ($scope, $http, $modal, MarcService, record, Marc21Form, flash, Localization, $location) {
        $scope.record = record;
        renderGrid();
        var rowTemplate = '<div ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-click="displayHelp(row.entity, col)" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell ng-class="{\'cell-error\': record.isError(row.entity) || record.isErrorAddedField(row.entity)}"></div></div>';
        
        var tagCellTemplate = '<div class="ngCellText" ng-class="col.colIndex()"><span ng-cell-text>{{COL_FIELD}}</span></div>';
        var indicator1CellTemplate = '<div class="ngCellText" ng-class="col.colIndex()"><span ng-cell-text >{{COL_FIELD}}</span></div>';
        var indicator2CellTemplate = indicator1CellTemplate;
        var dataCellTemplate = '<div class="ngCellText" ng-class="col.colIndex()" ng-dblclick="openTagEditor(row.entity)"><span ng-cell-text >{{COL_FIELD}}</span></div>';
        
        $scope.$on('ngGridEventEndCellEdit', function() {
            $scope.record.validate();
            $scope.$apply();
        });
        $scope.selectedItems = [];
        $scope.gridOptions = {
            data: 'gridData',
            multiSelect: false,
            enableCellEdit: true,
            enableCellSelection: true,
            rowTemplate: rowTemplate,
            columnDefs: [{ field: "name", displayName:Localization.resolve("cataloguing.material.marcedit.tag", "Tag"), width: 80, sortable: false, enableCellEdit: false, cellTemplate: tagCellTemplate},
                        { field: "ind1", displayName:Localization.resolve("cataloguing.material.marcedit.indi1", "Indi1"), width: 60, sortable: false, cellTemplate: indicator1CellTemplate},
                        { field: "ind2", displayName:Localization.resolve("cataloguing.material.marcedit.indi2", "Indi2"), width: 60, sortable: false, cellTemplate: indicator2CellTemplate },
                        { field: "data", displayName:Localization.resolve("cataloguing.material.marcedit.bo", "Bibliographic Details"), width: 500, sortable: false, cellTemplate: dataCellTemplate},
                        { field: "weight", displayName:Localization.resolve("cataloguing.material.marcedit.sequence", "Sequence"), sortable: true, visible: false}],
            selectedItems: $scope['selectedItems'],
            sortInfo: { fields: ['weight'], directions: ['asc']}
        };
        $scope.selectedItems[0] = $scope.gridData[0];
        
        $scope.$watch('selectedItems[0]', function(tag) {
            //Validate when user finish edit one field - just like lost focus
            if(!angular.isUndefined(tag) && tag.data.endsWith("|")) {
                tag.updateSubfields();
                $scope.record.validate();
            }
            
        },true);
        
        $scope.displayHelp = function(tag, col) {
            var form = Marc21Form.data;
            if(angular.isUndefined(form[tag.name]) ) {
                return;
            }
            $scope.property = form[tag.name];
            $scope.help = {
                isOpenInd1 : col.field == "ind1" && $scope.property.ind1 != undefined,
                isOpenInd2 : col.field == "ind2" && $scope.property.ind2 != undefined,
                isOpenSubfield : col.field == "data" && $scope.property.subf != undefined
            }
        }
        function renderGrid() {
            $scope.gridData = $scope.record.getData();
            $scope.DataField = function(ind1, ind2) {
                return new DataField(ind1, ind2);
            }
            $scope.jsonRecord = $scope.record.getMarcJSON();
            
            $scope.$watch('record', function(newRecord, oldRecord){
                if(!angular.isUndefined(newRecord) && !newRecord.equals(oldRecord)) {
                    $scope.jsonRecord = newRecord.getMarcJSON();
                    $scope.gridData = $scope.record.getData();
                    newRecord.validate();   
                }
            }, false);
            $scope.refresh = function() {
                var data = $scope.record.getData();
                $scope.gridData = data;
            }
        }
        var openTagEditor = function(tag){
            if(!angular.isUndefined(tag)){
                if(tag.name == "leader" || tag.name == "006" || tag.name == "007" || tag.name == "008") {
                    var controller = 'Tag' + tag.name + 'FormCtrl';
                    if(tag.name == "leader") {
                        controller = 'LeaderFormCtrl';
                    }
                    var tagName = tag.name;
                    var tagType = tag.name == "008" ? $scope.record.getTypeOfMaterial() : tag.getType();
                    var modalInstance = $modal.open({
                      templateUrl: 'views/cataloguing/material/tagForm.html',
                      controller: controller,
                      size: 'lg',
                      resolve: {
                            Schema: function(MarcService) {
                                return  MarcService.getSchema(tagName, tagType);
                            },
                            Form: function(MarcService) {
                                return  MarcService.getForm(tagName, tagType);
                            },
                            Tag: function() {
                                return tag.clone();
                            },
                            Marc21TagForm: function(MarcService, $q) {
                                var deferred = $q.defer();
                                MarcService.getMarc21Form().then(function(response) {
                                    var form = response.data;
                                    deferred.resolve(form[tag.name]);
                                });
                                return deferred.promise;
                            }
                       }
                    });
                    modalInstance.result.then(function (updatedTag) {
                        angular.copy(updatedTag, tag);
                    });
                } else {
                    console.log("This is not popup modal tag");
                }
            } 
        }
        $scope.openTagEditor = openTagEditor;

        $scope.save = function() {
            MarcService.save($scope.record).then(function(record){
                flash.info = Localization.resolve("material.editBiblio.success", "Successfully update bibliography", {id: $scope.record.getId()});
                if($scope.record.isNew()) {
                    $location.path('cataloguing/material/' + parseInt(record.getId()) + '/edit');
                }
                $scope.record = record;
            });
        }
        
        $scope.remove = function() {
        }
        
        $scope.verify = function() {
            MarcService.verify($scope.record).then(function(response) {
                if(response.errMsg != "") {
                    flash.error = response.errMsg;
                } else {
                    flash.info = Localization.resolve("material.verify.success", "Record is valid");
                }
            });
        }
        
        $scope.convert = function() {
            MarcService.convert($scope.record).then(function(convertedRecord) {
                $scope.record = convertedRecord;
            });
        }
        
        $scope.duplicate = function() {
            $scope.record = $scope.record.clone();
            renderGrid();
        }
        
        $scope.reset = function() {
            $scope.record.reset();
            renderGrid();
        }
        
        $scope.create = function() {
            $location.path('cataloguing/material/create');
        }
    });
});
