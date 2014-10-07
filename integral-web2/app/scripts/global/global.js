'use strict';

/* Global Module - Single consisten place to define standard REST and Bootstrap Module module */
define(['lodash','jquery','utility/inflection'], function (_, $) {
    var module = angular.module('integral.global',['ui.bootstrap','localization', 'ngGrid', 'ui.router','ngRoute', 'angular-flash.service', 'angular-flash.flash-alert-directive', 'dialogs', 'ngSanitize', 'restangular']);
});