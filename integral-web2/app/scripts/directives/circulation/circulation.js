
define(['app', 'lodash'], function (integralApp, _) {
    integralApp.directive('mCircReloadPolicy', function(CirculationService, flash, Localization) {
        return {
            restrict: 'E',
            template: '<a id="btnReload" class="btn btn-primary" ng-click="reloadPolicy()">{{\'circulation.reloadPolicy\' | i18n}}</a>',
            link: function(scope) {
                scope.reloadPolicy = function() {
                    scope.$emit('mCircReloadPolicy:reload');
                    CirculationService.reloadPolicy().then(function(response) {
                        scope.$emit('mCircReloadPolicy:reload:success');
                        flash.info = Localization.resolve("circulation.reloadPolicy.success");
                    }, function(response) {
                        scope.$emit('mCircReloadPolicy:reload:failed');
                        flash.info = Localization.resolve("circulation.reloadPolicy.failed");
                    });
                }
            }
        }
    });
});