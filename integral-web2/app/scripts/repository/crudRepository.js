'use strict'

define(['app','lodash', 'angular','i18n/localize'], function (integralApp, _, angular) {
    var module = angular.module('integral.repository', ['localization', 'restangular']);
    module.factory('BaseModel', function(Localization, $injector, $q) {
        //we don't use object prototype because http://blog.shinetech.com/2014/02/04/rich-object-models-and-angular-js/
        return {
            getViewTemplate: function() {
                return "views" + this.getUri() + "/view.html";
            },
            getEditTemplate: function() {
                return "views" + this.getUri() + "/form.html";
            },
            getCreateTemplate: function() {
                return this.getEditTemplate();
            },
            getViewLink: function() {
                return this.getUri() + '/' + this.id;
            },
            getEditLink: function() {
                return this.getUri() + '/' +  this.id + "/edit";
            },
            getSearchLink: function() {
                return this.getUri() + "/search";
            },
            getCreateLink: function() {
                return this.getUri() + "/create";
            },
            getDescription: function() {
                return !angular.isUndefined(this.description) ? this.description : this.code;
            },
            getLocalizedName: function() {
                return Localization.resolve(this.name);
            },
            getNotFoundMessage: function() {
                var notFoundMessageTemplate = Localization.resolve('repository.domain.notfound');
                return _.template(notFoundMessageTemplate, {"name": this.getLocalizedName()});
            },
            getRepository: function() {
                return $injector.get(this.getRepositoryName());
            },
            getRepositoryName: function() {
                return this.name.capitalize() + "Repository";
            }, 
            getUri: function() {
                return '/' + this.name.toLowerCase();
            },
            getListSearches: function() {
                return this.getRepository().customGET("search");
            },
            search: function(queryLink, query) {
                return this.getRepository().customGETLIST("search/" + queryLink, query);
            },
            getQueryLinks: function() {
                var model = this;
                var QueryLink = function (entityName, link) {
                    this.link = link;
                    this.name = Localization.resolve(entityName + '.' + link, link);
                }
                var deferred = $q.defer();
                this.getListSearches().then(function(response){
                    var queryLinks = new Array();
                    $.each(response._links, function(_queryKey, _queryLink) {
                        var queryLink = new QueryLink(model.name, _queryKey);
                        queryLinks.push(queryLink);
                    });
                    deferred.resolve(queryLinks);               
                });
                return deferred.promise;
            },
            clone: function() {
                return angular.copy(this);
            },
            getAssociationCellTemplate: function(isModal) {
                var associationCellTemplate = "<div class='ngCellText'><a ng-href='#{{row.entity.getViewLink()}}'>{{row.entity[col.field].getDescription()}}</a></div>";
                if(isModal) {
                    associationCellTemplate = "<div class='ngCellText'><a ng-href='#{{row.entity.getViewLink()}}' target='_blank'>{{row.entity[col.field].getDescription()}}</a></div>";
                }
                return associationCellTemplate;
            },
            getViewCellTemplate: function(isModal) {
                var viewCellTemplate = "<div class='ngCellText'><a ng-href='#{{row.entity.getViewLink()}}'>{{row.entity[col.field]}}</a></div>";
                if(isModal) {
                    viewCellTemplate = "<div class='ngCellText'><a ng-href='#{{row.entity.getViewLink()}}' target='_blank'>{{row.entity[col.field]}}</a></div>";
                }
                return viewCellTemplate;
            },
            getSearchColumnDefinition: function(isModal) {
                var associationCellTemplate = this.getAssociationCellTemplate(isModal);
                return [
                    {
                        "field" : "No",
                        "displayName" : "No.",
                        "width" : 100,
                        "pinned" : true,
                        "cellTemplate" : "<div  class='ngCellText'>{{row.rowIndex + 1}}</div>"
                    },
                    {
                        "field" : "code",
                        "displayName" : this.getMessage('code'),
                        "pinned" : true
                    },
                    {
                        "field" : "description",
                        "displayName" : this.getMessage('description'),
                        "cellTemplate" : this.getViewCellTemplate(isModal)
                    } 
                ];
            },
            getSearchColumnDefinitionForModal: function() {
                return this.getSearchColumnDefinition(true);
            },
            getMessage: function(property) {
                return Localization.resolve(this.name + "." + property);
            },
            datepickers: {
                
            },
            //initialize method
            init: function() {
            
            },
            isLazyLoading: function(property) {
                return (property != "createdBy");
            }
        }
    });
    
    module.factory('AuditableModel', function(BaseModel) {
        return angular.extend(BaseModel, {
        });
    });
    module.factory('UriToEntityConverter', function($injector, TOKEN) {
        return {
            convert: function(uri) {
                var prefix = TOKEN.serviceUrl + "/repository/";
                var parts = uri.substring(prefix.length).split("/");
                if(parts.length < 2) {
                    throw "Cannot resolve URI " + uri + ". Is it local or remote? Only local URIs are resolvable.";
                }
                var entityStringType = parts[0].singularize();
                var entity = $injector.get(entityStringType.capitalize());
                return entity;
            }
        }
    });
    
    module.factory('EntityObjectUtil', function() {
        return {
            object: function(fromArray) {
                return {
                    fromArray: fromArray, 
                    getObjectById: function(id) {
                        return _.find(fromArray, function (fromObject) {
                            return id == fromObject.id;
                        });
                    }
                }
            },
            copyObject: function(sourceObject, fromArray, matchedKey) {                
                var matchedObject = _.find(fromArray, function (fromObject) {
                    if(angular.isUndefined(matchedKey)) {
                        return sourceObject.id == fromObject.id;
                    } else {
                        return sourceObject[matchedKey] == fromObject[matchedKey];
                    }
                });
                sourceObject = matchedObject;
                return sourceObject;
            },
            copyArray: function(sourceArray, fromArray) {
                var EntityObjectUtil = this;
                $.each(sourceArray, function(key, fromObject) {
                    sourceArray[key] = EntityObjectUtil.copyObject(sourceArray[key], fromArray);
                });
            }
        }
    });
    
    module.provider('CrudRepository', function() { 
        this.setupDefaultEntityAndRepository = function (entities, model) {
                $.each(entities, function(key, entity) {
                    var entityFactoryName = entity.capitalize();
                    module.factory(entityFactoryName, function(model) {
                        this.name = entity;
                        return angular.extend(this, model);
                    });     
                    module.factory(entityFactoryName + "Repository", function(CrudRepository) {
                        return CrudRepository.withConfig(function(Configurer){
                            Configurer.setEntityName(entity);
                        });
                    });   
                });
        }
        
        this.$get = function(RepositoryRestangular, UriToEntityConverter) {
            var service = {};
            var Config = function() {             
                var _isLazyLoading = true;
                var _entityName;
                this.setLazyLoading = function(lazyLoading) {
                    _isLazyLoading = lazyLoading;
                }
                this.isLazyLoading = function() {
                    return _isLazyLoading;
                }
                this.setEntityName = function(entityName) {
                    _entityName = entityName;
                }
                this.getEntityUri = function() {
                    return _entityName.pluralize();
                }
            }
			
			
            //TODO - This is something that is amazing. You can pass in a function and give the object to that function to setup
            function withConfigurationFunction(configurer) {
                var newConfig = new Config();
                configurer(newConfig);
                return createRepository(newConfig);
            }
            
            function createRepository(config) {
                var base = RepositoryRestangular.all(config.getEntityUri());
                RepositoryRestangular.extendModel(config.getEntityUri(), function(model) {
                    if(angular.isUndefined(model._links) || angular.isUndefined(model._links.self)) {
                        return model;
                    }
                    $.each(model._links, function(propertyName, rel) {
                        if(propertyName != "self" && propertyName != "createdBy") {
                            model['get' + propertyName.capitalize()] = function() {
                                return model.one(propertyName).get().then(function(response) {
                                    if(!angular.isUndefined(response._links)) {
                                        var entity = UriToEntityConverter.convert(response._links.self.href);
                                        model[propertyName] = angular.extend(response, entity);
                                        return model[propertyName];
                                    } else if(!angular.isUndefined(response._embedded)){
                                        model[propertyName] = response._embedded[propertyName];
                                        return model[propertyName];
                                    }
                                });
                            }
                            //lazy-loading
                            if(config.isLazyLoading()) {
                                model['get' + propertyName.capitalize()]();
                            }
                        }
                    });
                    var entity = UriToEntityConverter.convert(model._links.self.href);
                    return angular.extend(model, entity);
                });
                return base;
            }
            service.withConfig = _.bind(withConfigurationFunction, service);
            return service;
        }
    });
});
