/**
 * calendarDemoApp - 0.1.3
 */
define(['angular','lodash','jquery', 'circulation/circulation', 'circulation/calendar/services'], function (angular, _, $) {
var module = angular.module('integral.circulation');
module.controller('TimeClassCtrl', ['$scope', '$modal', '$log', '$window', 'BranchRepository', 'WorkingDayRepository', 'Localization', 'branches', 'calendarService',
function ($scope, $modal, $log, $window, BranchRepository, WorkingDayRepository, Localization, branches, calendarService) {
	
    $scope.branches = branches;
    $scope.branch = $scope.branches[1];
    
    calendarService.getWeekends($scope.branch.id).then(function(response) {
        $scope.fetchEvents($scope.calendar, response);
    })
    
  	$scope.selectedIndex = 0;
    $scope.itemClicked = function($index){
    	$scope.selectedIndex = $index;
    };
    
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    
    //$scope.weekends = [{"dayOfWeek": 1, "working":false}, {"dayOfWeek": 7, "working":false}];
		//$scope.workingDays = [{"dayOfWeek": 2, "working":true, "workingHours": "08:30-12:30&14:00-18:00"}, {"dayOfWeek": 3, "working":true, "workingHours": "08:30-18:00"}];
		
    $scope.changeTo = 'Bahasa Malaysia';
    
    $scope.eventsWeekend = function (start, end, callback) {
      var events = [];
      var s = new Date(start);
      var e = new Date(end);
      for(var n=s; n<e; n.setDate(n.getDate() + 1)) {
        angular.forEach($scope.weekends, function(weekend, index) {
          if ((n.getDay() + 1) === weekend.dayOfWeek) {
            events.push({title: Localization.resolve('circulation.calendar.weekend.title', "Weekend"), start: n.toString(), allDay: true, className: ['customFeed'], color: 'rgb(70, 130, 180)'});
          }
        })
      }
      callback(events);
    };
    
    $scope.eventsOpeningHours = function (start, end, callback) {
      var events = [];
      var s = new Date(start);
      var e = new Date(end);
      for(var n=s; n<e; n.setDate(n.getDate() + 1)) {
          angular.forEach($scope.workingDays, function(workingDay, index) {
            if ((n.getDay() + 1) === workingDay.dayOfWeek) {
              events = calendarService.workingDayToEvents(events, workingDay, n);
            }
          })
      }
      $log.debug("events", events);
      callback(events);
    };

    /* dayRender callback */
    $scope.dayRender = function( date, cell ){
        angular.forEach($scope.weekends, function(weekend, index) {
          if ((date.getDay() + 1) === weekend.dayOfWeek) {
            cell.addClass('weekend');
          }
        })
    };
    
    /* eventRender callback */
    $scope.eventRender = function( event, element, view ){
        if (event.repeated) {
            element.addClass("event-repeated");
            $log.debug("repeated", element);
        }
        var dataDate = $.fullCalendar.formatDate(event.start, 'yyyy-MM-dd');
        
        if (event.title == 'Weekend') {
            return false;
        }else{
            $(".fc-day[data-date="+dataDate+"]").addClass('holiday');
        }
    };
    
    /* eventDataTransform callback */
    $scope.eventDataTransform = function( eventData ) {
    	$log.debug("eventDataTransform");
        $log.debug(eventData);
    };

    /* select callback */
    $scope.select = function(startDate, endDate, allDay, jsEvent, view ) {
        var modalInstance = $modal.open({
            templateUrl: 'views/circulation/calendar/timeClassModal.html',
            controller: TimeClassModalInstanceCtrl,
            size: 'lg',
            resolve: {
                range: function() {
                    return {startDate: startDate, endDate: endDate, description: Localization.resolve('circulation.timeClass.openingHours.description', "Library Opening Hours")}
                }
            }
        });
        modalInstance.result.then(function (openingHoursRange) {
            var workingHours = calendarService.selectedRangeToWorkingHours(openingHoursRange);
            
            var workingDay = {};
            workingDay.branch = $scope.branch;
            workingDay.dayOfWeek = parseInt(openingHoursRange.startDate.getDay()) + 1;
            calendarService.addWorkingHours(workingDay, workingHours).then(function(response) {
                console.log("response", response);
                workingDay = response;
                $log.debug("workingDay", workingDay);
                WorkingDayRepository.doPOST(workingDay).then(function(response){
                    $scope.alertMessage = Localization.resolve('circulation.timeClass.workingHours.addAlertMessage', "Working hours was added for") + ' ' + $scope.branch.description;
                    $scope.failMessage = "";
                    $scope.refetchEvents($scope.calendar);
                }, function(error) {
                    $scope.failMessage = Localization.resolve('circulation.timeClass.workingHours.addFailMessage', "Working hours cannot be added due to") + ' ' + error.data.message;
                    $scope.alertMessage = "";
                });
            }, function(error) {
                $scope.failMessage = error.data.message;
                $scope.alertMessage = "";
            });
            
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };

    var TimeClassModalInstanceCtrl = function ($scope, $modalInstance, $log, range) {
        $log.debug('ModalInstanceCtrl', range);

        $scope.range = range;

        $scope.ok = function () {
            $log.debug('return', $scope.range);
            $modalInstance.close($scope.range);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    };

    /* alert on dayClick */
    $scope.alertOnDayClick = function( date, allDay, jsEvent, view ){
        if (allDay) {
            $log.info('Clicked on the entire day: ' + date);
        }else{
            $log.info('Clicked on the slot: ' + date);
        }
    };
    /* alert on eventClick */
    $scope.alertOnEventClick = function( event, allDay, jsEvent, view ){
        var deleteOpeningHours = $window.confirm(Localization.resolve('circulation.timeClass.openingHours.removeConfirmMessage',"Are you sure you want to delete the library opening hours?"));   
        if (deleteOpeningHours) {
            var range = {};
            range.startDate = event.start;
            range.endDate = event.end;
            var workingHours = calendarService.selectedRangeToWorkingHours(range);
            $log.debug ("workingHours", workingHours);
            var workingDay = {};
            workingDay.branch = $scope.branch;
            workingDay.dayOfWeek = parseInt(range.startDate.getDay()) + 1;
            calendarService.removeWorkingHours(workingDay, workingHours).then(function(response) {
                console.log("response", response);
                workingDay = response;
                $log.debug("workingDay", workingDay);
                WorkingDayRepository.doPOST(workingDay).then(function(response){
                    $scope.alertMessage = Localization.resolve('circulation.timeClass.workingHours.removeAlertMessage', "Working hours was removed for") + ' ' + $scope.branch.description;
                    $scope.failMessage = "";                                                                                                                  
                    $scope.refetchEvents($scope.calendar);
                }, function(error) {
                    $scope.failMessage = Localization.resolve('circulation.timeClass.workingHours.removeFailMessage', "Working hours cannot be removed due to") + ' ' + error.data.message;
                    $scope.alertMessage = "";
                });
            }, function(error) {
                $scope.failMessage = error.data.message;
                $scope.alertMessage = "";
            });
            
        }
    };   
  
    /* alert on Drop */
     $scope.alertOnDrop = function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view){
       $scope.alertMessage = ('Event Dropped to make dayDelta ' + dayDelta);
    };
    /* alert on Resize */
    $scope.alertOnResize = function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view ){
       $log.debug(event);
       $scope.alertMessage = ('Event Resized to make dayDelta ' + minuteDelta);
    };
    /* add and removes an event source of choice */
    $scope.addRemoveEventSource = function(sources,source) {
      var canAdd = 0;
      angular.forEach(sources,function(value, key){
        if(sources[key] === source){
          sources.splice(key,1);
          canAdd = 1;
        }
      });
      if(canAdd === 0){
        sources.push(source);
      }
    };
    
    /* Change View */
    $scope.changeView = function(view,calendar) {
      calendar.fullCalendar('changeView',view);
    };
    /* Change View */
    $scope.renderCalender = function(calendar) {
      if(calendar){
        calendar.fullCalendar('render');
      }
    };
    
    $scope.fetchEvents = function(calendar, weekends) {
      if(calendar){
          $scope.weekends = weekends;
          $log.debug("$scope.weekends", $scope.weekends);
          calendarService.getWorkingDaysByBranch($scope.branch.id)
            .then(function(response) {
                $scope.workingDays = response;
                calendar.fullCalendar('refetchEvents');
                calendar.fullCalendar('render');
          }, function(error) {
                $scope.failMessage = error.data.message;
                $scope.alertMessage = "";
          });   
      }
    };
    
    $scope.refetchEvents = function(calendar) {
      if(calendar){
        calendarService.getWeekends($scope.branch.id).then(function(response) {
            $scope.fetchEvents($scope.calendar, response);
        })
      }
    }
    
    /* config object */
    $scope.uiConfig = {
      calendar:{
        editable: true,
        eventDurationEditable: false,
        selectable: true,
        firstHour: 6,
        header:{
        	left: '',
    			center: '',
    			right: ''
        },
        columnFormat: {
            month: 'dddd',    // Mon
            week: 'dddd', // Mon 9/7
            day: 'dddd'      // Monday
        },
        defaultView: 'agendaWeek',
        dayRender: $scope.dayRender,
        dayClick: $scope.alertOnDayClick,
        eventClick: $scope.alertOnEventClick,
        eventDrop: $scope.alertOnDrop,
        eventResize: $scope.alertOnResize,
        eventRender: $scope.eventRender,
        //eventDataTransform: $scope.eventDataTransform,
        select: $scope.select
      }
    };

    /* event sources array*/
    $scope.eventSources = [$scope.eventsWeekend, $scope.eventsOpeningHours];
}]);
});
/* EOF */
