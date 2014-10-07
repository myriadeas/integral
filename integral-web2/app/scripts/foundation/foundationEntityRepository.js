'use strict'

define(['app','lodash','jquery', 'utility/utility'], function (integralApp, _, $) {
    /**
    * Why we setup manually repository? Because we still want user to override if there don't want existing factory
    * implementation
    */    
    //TODO - DRY - refactor a factory
    function setupDefaultEntityAndRepository(entities) {
        $.each(entities, function(key, entity) {
            var entityFactoryName = _.capitalize(entity);
            integralApp.factory(entityFactoryName, function(AuditableModel) {
                this.name = entity;
                return angular.extend(this, AuditableModel);
            });     
            integralApp.factory(entityFactoryName + "Repository", function(CrudRepository) {
                return CrudRepository.withConfig(function(Configurer){
                    Configurer.setEntityName(entity);
                });
            });   
        });
    }
    function postSetup() {
        integralApp.factory('Patron', function(AuditableModel) {
            this.name = "patron";
            return angular.extend(this, AuditableModel, {
                getDescription: function() {
                    return this.firstname;
                }
            });
        });
        integralApp.factory('Town', function(AuditableModel) {
            this.name = "town";
            return angular.extend(this, AuditableModel, {
                getDescription: function() {
                    return this.code + '-' + this.description;
                }
            });
        });
    }
    var defaultEntities = ["patron","department", "designation", "location", "patronCategory", "race", "religion", "title", "town","branch", "patron", "officer", "patronCategory"];
    setupDefaultEntityAndRepository(defaultEntities);
    postSetup();    
});