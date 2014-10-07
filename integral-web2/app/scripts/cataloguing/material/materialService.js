'use strict';
define(['angular', 'lodash','jquery','cataloguing/cataloguing','tv4', 'marc4js'], function (angular, _, $, cataloguing, tv4, marc4js) {    
    angular.module('integral.cataloguing').factory('MarcService', function($http, $q, MaterialRepository, ServicesRestangular) {    
        var baseUrl = 'scripts/marc4js/schema/';
        return {
            find: function(id) {
                var deferred = $q.defer();
                var materialPromise = MaterialRepository.get(id);
                $q.all({data: materialPromise, schema: this.getSchema("marc21", "marc21")}).then(function(response) {
                    var record = marc4js.newRecord(JSON.parse(response.data.jsonString), response.schema.data);
                    deferred.resolve(record);
                });
                return deferred.promise;
            },
            save: function(record) {
                var deferred = $q.defer();
                var url = "cataloguing/marc/1/1/" + record.getId();
                
                function handleSaveResponse(marcJson) {
                    var newRecord = marc4js.newRecord(marcJson, record.schema);
                    deferred.resolve(newRecord);
                }
                if(record.isNew()) {
                    ServicesRestangular.all(url).customPOST(record.getMarcJSON()).then(function(marcJson) {
                        handleSaveResponse(marcJson);
                    });
                } else {
                    ServicesRestangular.all(url).customPUT(record.getMarcJSON()).then(function(marcJson) {
                        handleSaveResponse(marcJson);
                    });                    
                }
                return deferred.promise;
            },
            verify: function(record) {
                var url = "cataloguing/marc/verify/1/1/";
                return ServicesRestangular.all(url).customPOST(record.getMarcJSON());
            },
            convert: function(record) {
                
                var deferred = $q.defer();
                var url = "cataloguing/marc/convert/1/1/";
                ServicesRestangular.all(url).customPOST(record.getMarcJSON()).then(function(marcJson) {
                    var convertedRecord = marc4js.newRecord(marcJson, record.schema);
                    deferred.resolve(convertedRecord);
                });
                
                return deferred.promise;
            },
            getTemplates: function() {
                return [
                    {
                        "title": "Sound Recording",
                        "url": "scripts/marc4js/templates/soundrecording.json",
                    },
                    {
                        "title": "Books",
                        "url": "scripts/marc4js/templates/book.json",
                    }
                ]
            },
            getTemplate: function(template) {
                return $http.get(template.url);
            },
            getMarc21Form: function() {
                return this.getForm('marc21', 'marc21');
            },
            getSchemaAndForm: function(tagName, tagType) {
                return $q.all({ schema: this.getSchema(tagName, tagType), form: this.getForm(tagName, tagType)});
            },
            getSchema: function(tagName, tagType) {
                var schemaUrl = baseUrl + tagName + '/' + tagType + '_schema.json';
                var schemaPromise = $http.get(schemaUrl);
                return schemaPromise;
            },
            getForm: function(tagName, tagType) {
                var formUrl = baseUrl + tagName + '/' + tagType + '_form.json';
                var formPromise = $http.get(formUrl);
                return formPromise;
            },
            getCounteriesAndLanguages: function() {
                return $q.all({ countries: this.getCountries(), languages: this.getLanguages()});
            },
            getCountries: function() {
                var element = {};
                element["|||"] = "|||-No attempt to code";
                var deferred = $q.defer();
                $http.get(baseUrl + "countries.xml").then(function(response) {
                    var xml = response.data;
                    $(xml).find("country").each(function(){
                        var code = $(this).children("code[status!='obsolete']").text();
                        var name = code + "-" + $(this).children("name[authorized='yes']").text();
                        element[code.rpad("#", 3)] = name;
                    });
                    deferred.resolve(element);
                });
                return deferred.promise;
            },
            getLanguages: function(element, xml) {
                var element = {};
                element["|||"] = "|||-No attempt to code";
                var deferred = $q.defer();
                $http.get(baseUrl + "languages.xml").then(function(response) {
                    var xml = response.data;
                    $(xml).find("language").each(function(){
                        var code = $(this).children("code").text();
                        var name = code + "-" + $(this).children("name").text();
                        element[code] = name;
                    });
                    deferred.resolve(element);
                });
                return deferred.promise;
            }

        };
    });
});

