define(['angular','lodash','jquery', 'circulation/circulation'], function (angular, _, $) {
    var module = angular.module('integral.circulation');
    module.config(function($stateProvider) {
      $stateProvider.state('circulation.calendar', {
			   url : '/calendar',
			   templateUrl : 'views/circulation/calendar/calendar.html',
          controller : 'CalendarCtrl',
          resolve : {
              branches:  function(Branch){
                  return Branch.getRepository().getList();
              }
          }
		  }).state('circulation.timeClass', {
          url : '/timeclass',
          templateUrl : 'views/circulation/calendar/timeClass.html',
          controller : 'TimeClassCtrl',
          resolve : {
              branches:  function(Branch){
                  return Branch.getRepository().getList();
              }
          }
      });
	  })
});
