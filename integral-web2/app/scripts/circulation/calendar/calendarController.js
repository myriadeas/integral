/**
 * calendarDemoApp - 0.1.3
 */
define(['angular','lodash','jquery', 'circulation/circulation', 'circulation/calendar/services'], function (angular, _, $) {
var module = angular.module('integral.circulation');
module.controller('CalendarCtrl', ['$scope', '$modal', '$log', '$window', 'HolidayRepository', 'BranchRepository', 'WorkingDayRepository', 'Localization','calendarService', 'branches', 
function ($scope, $modal, $log, $window, HolidayRepository, BranchRepository, WorkingDayRepository, Localization, calendarService, branches) {
	
    $scope.branches = branches;
    $scope.branch = $scope.branches[1];
    
    calendarService.getHolidays($scope.branch.id).then(function(response) {
      $scope.holidays = response;
      $scope.fetchEvents($scope.calendar, response);
    });
    
  	$scope.selectedIndex = 0;
    $scope.itemClicked = function($index){
    	$scope.selectedIndex = $index;
    };
    
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    
    //$scope.daysOfWeek = {1: "Sunday", 2: "Monday", 3: "Tuesday", 4: "Wednesday", 5: "Thursday", 6: "Friday", 7: "Saturday"};
    //$scope.weekdays = $scope.daysOfWeek;
    //$scope.definedWeekends = {};
    $scope.dayOfWeekSelections = {};
    
    $scope.changeTo = 'Bahasa Malaysia';
    
    /* event source that pulls from google.com */
    $scope.eventSource = {
            url: "https://www.google.com/calendar/feeds/en.malaysia%23holiday%40group.v.calendar.google.com/public/full",
            className: 'gcal-event',           // an option!
            currentTimezone: 'America/Chicago' // an option!
    };
    /* event source that pulls from json source */
    
    
    $scope.eventDatabase = function (start, end, callback) {
        var events = [];
        var holidays = $scope.holidays;
    		if (holidays) {
    			angular.forEach(holidays, function(holiday, index) {
    			  var event = calendarService.holidayToEvent(holiday);
    				events.push(event);
    			});
    		}
        callback(events);	
    };
    
    $scope.eventsWeekend = function (start, end, callback) {
      var events = [];
      var s = new Date(start);
      var e = new Date(end);
      for(var n=s; n<e; n.setDate(n.getDate() + 1)) {
        angular.forEach($scope.weekends, function(weekend, index) {
          if ((n.getDay() + 1) === weekend.dayOfWeek) {
            events.push({title: Localization.resolve('circulation.calendar.weekend.title', "Weekend"), start: n.toString(), allDay: true, className: ['customFeed']});
          }
        })
      }
      callback(events);
    };
    
    $scope.addWeekend = function() {
      var workingDay = {};
      workingDay.dayOfWeek = 2;
      workingDay.working = false; 
      workingDay.branch = $scope.branch;
      workingDay.code = "1_" + $scope.branch.code;
      
      $log.debug ("WorkingDay", workingDay);
      WorkingDayRepository.doPOST(workingDay).then(function(response){
          $scope.alertMessage = Localization.resolve('circulation.calendar.weekend.addAlertMessage', "Weekend was added for") + ' ' + $scope.branch.description;
          $scope.failMessage = "";
          $scope.refetchEvents($scope.calendar);
      }, function(error) {
          $scope.failMessage = Localization.resolve('circulation.calendar.weekend.addFailMessage', "Weekend cannot be added due to") + ' ' + error.data.message;
          $scope.alertMessage = "";
      });
    };
    
    $scope.addWeekendBySelection= function() {
        var modalInstance = $modal.open({
            templateUrl: 'views/circulation/calendar/weekendModal.html',
            controller: WeekendModalInstanceCtrl,
            size: 'lg',
            resolve: {
              dayOfWeekSelections: function () {
                $scope.dayOfWeekSelections = calendarService.getWeekendSelections($scope.weekends);
                return $scope.dayOfWeekSelections;
              },
              weekendMessage: function () {
                //return "Please select a day to set as weekend";             
                return Localization.resolve('circulation.calendar.weekend.selectMessage', "Please select a day to set as weekend");
              }
            }
        });

        modalInstance.result.then(function (selectedWeekend) {
            var workingDay = {};
            workingDay.branch = $scope.branch;
            workingDay.dayOfWeek = parseInt(selectedWeekend);
            calendarService.setWeekend(workingDay).then(function(response) {
                console.log("response", response);
                workingDay = response;
                $log.debug("workingDay", workingDay);
                WorkingDayRepository.doPOST(workingDay).then(function(response){
                    $scope.alertMessage = Localization.resolve('circulation.calendar.weekend.addAlertMessage', "Weekend was added for") + ' ' + $scope.branch.description;
                    $scope.failMessage = "";
                    $scope.fetchEvents($scope.calendar, $scope.holidays)
                }, function(error) {
                    $scope.failMessage = Localization.resolve('circulation.calendar.weekend.addFailMessage', "Weekend cannot be added due to") + ' ' + error.data.message;                    
                    $scope.alertMessage = "";
                });
            }, function(error) {
                $scope.failMessage = error.data.message;
                $scope.alertMessage = "";
            });
        });
    };
    
    $scope.deleteWeekendBySelection= function() {
        var modalInstance = $modal.open({
            templateUrl: 'views/circulation/calendar/weekendModal.html',
            controller: WeekendModalInstanceCtrl,
            size: 'lg',
            resolve: {
              dayOfWeekSelections: function () {
                $scope.dayOfWeekSelections = calendarService.getDefinedWeekends($scope.weekends);
                return $scope.dayOfWeekSelections;
              },                                                                                 
              weekendMessage: function () {
                return Localization.resolve('circulation.calendar.weekend.deleteMessage', "Please select a weekend to be deleted");
              }
            }
        });

        modalInstance.result.then(function (selectedWeekend) {
            var workingDay = {};
            workingDay.branch = $scope.branch;
            workingDay.dayOfWeek = parseInt(selectedWeekend);
            calendarService.setWeekend(workingDay).then(function(response) {
                console.log("response", response);
                workingDay = response;
                $log.debug("workingDay", workingDay);
                workingDay.working = true;
                WorkingDayRepository.doPOST(workingDay).then(function(response){
                    $scope.alertMessage = Localization.resolve('circulation.calendar.weekend.removeAlertMessage', "Weekend was removed for") + ' ' + workingDay.branch.description;
                    $scope.failMessage = "";
                    $scope.fetchEvents($scope.calendar, $scope.holidays)
                }, function(error) {
                    $scope.failMessage = Localization.resolve('circulation.calendar.weekend.removeFailMessage',"Weekend cannot be removed due to") + ' ' + error.data.message;
                    $scope.alertMessage = "";
                });
            }, function(error) {
                $scope.failMessage = error.data.message;
                $scope.alertMessage = "";
            });
      });
    };
    
    var WeekendModalInstanceCtrl = function ($scope, $modalInstance, $log, dayOfWeekSelections, weekendMessage) {
        $scope.dayOfWeekSelections = dayOfWeekSelections;
        $scope.weekendMessage = weekendMessage;
        $scope.selected = {
          weekday: $scope.dayOfWeekSelections[0]
        };
        $scope.ok = function () {
            $log.debug('return', $scope.selected.weekday);
            $modalInstance.close($scope.selected.weekday);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    };
    
    $scope.displayWeekend = function() {
      WorkingDayRepository.customGETLIST('search/findByBranchAndWorking', { "branch": $scope.branch.id, "working": false }).then(function(response){
        //$scope.weekends = response;
        //$scope.weekends = [{"dayOfWeek": 1, "working":false}, {"dayOfWeek": 7, "working":false}];
        $log.debug($scope.weekends);
        $scope.refetchEvents($scope.calendar);
      }, function(error) {
          alert(error);
      });
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
            templateUrl: 'views/circulation/calendar/myModalContent.html',
            controller: ModalInstanceCtrl,
            size: 'lg',
            resolve: {
                range: function() {
                    return {startDate: startDate, endDate: endDate, description: ''}
                
                }
            }
        });

        
        modalInstance.result.then(function (newHoliday) {
            var holiday = calendarService.newHolidayToHoliday(newHoliday);
          	holiday.branch = $scope.branch;
          	holiday.code = holiday.code + $scope.branch.code;
          	HolidayRepository.doPOST(holiday).then(function(response){
                $scope.alertMessage = (newHoliday.description + ' ' + Localization.resolve('circulation.calendar.holiday.addAlertMessage',"was added as a library holiday for") + ' ' + $scope.branch.description);
                $scope.failMessage = "";
                $scope.refetchEvents($scope.calendar);
            }, function(error) {
                $scope.failMessage = (newHoliday.description + ' ' + Localization.resolve('circulation.calendar.holiday.addFailMessage',"cannot be added as a library holiday due to") + ' ' + error.data.message);
                $scope.alertMessage = "";
            });
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };

    var ModalInstanceCtrl = function ($scope, $modalInstance, $log, range) {
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
        if (event.title != "Weekend"){
          var deleteHoliday = $window.confirm(Localization.resolve('circulation.calendar.holiday.removeConfirmMessage',"Are you sure you want to delete the holiday?"));   
  
          if (deleteHoliday) {
              HolidayRepository.doDELETE(event.id).then(function(response){
                  $scope.alertMessage = (event.title + ' ' + Localization.resolve('circulation.calendar.holiday.removeAlertMessage', "was dismissed from library holidays for") + ' ' + $scope.branch.description);
                  $scope.failMessage = "";
                  $scope.refetchEvents($scope.calendar);
              }, function(error) {
                  $scope.failMessage = (event.title + ' ' + Localization.resolve('circulation.calendar.holiday.removeFailMessage', "cannot be deleted due to") + ' ' + error.data.message);
                  $scope.alertMessage = "";
              });
          	  
          }
        }
    };   
  
    /* alert on Drop */
     $scope.alertOnDrop = function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view){
       $scope.alertMessage = ('Event Dropped to make dayDelta ' + dayDelta);
    };
    /* alert on Resize */
    $scope.alertOnResize = function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view ){
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
    /* add custom event*/
    $scope.addEvent = function() {
      $scope.events.push({
        title: 'Open Sesame',
        start: new Date(y, m, 28),
        end: new Date(y, m, 29),
        className: ['openSesame']
      });
    };
    /* remove event */
    $scope.remove = function(index) {
      $scope.events.splice(index,1);
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
    
    $scope.refetchEvents = function(calendar) {
      $log.debug ("Enter $scope.refetchEvents = function(calendar)");
      if(calendar){
         calendarService.getHolidays($scope.branch.id)
          .then(function(response) {
                console.log($scope.response);
                $scope.holidays = response;
                $log.debug("$scope.holidays", $scope.holidays);
                $scope.fetchEvents(calendar, $scope.holidays)
            }, function(error) {
                $scope.failMessage = error.data.message;
                $scope.alertMessage = "";
            });
      }
    };
    
    $scope.fetchEvents = function(calendar, holidays) {
      $log.debug ("Enter $scope.fetchEvents = function(calendar, holidays)");
      if(calendar){
          calendarService.getWeekends($scope.branch.id)
            .then(function(response) {
                $scope.weekends = response;
                $log.debug("$scope.weekends", $scope.weekends);
                calendar.fullCalendar('refetchEvents');
                calendar.fullCalendar('render');
          }, function(error) {
                $scope.failMessage = error.data.message;
                $scope.alertMessage = "";
          });   
      }
    };
    
    /* config object */
    $scope.uiConfig = {
      calendar:{
        editable: true,
        selectable: true,
        header:{
        	left: '',
    			center: 'title',
    			right: 'prev,next today'
        },
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

    $scope.changeLang = function() {
      if($scope.changeTo === 'Bahasa Malaysia'){
        $scope.uiConfig.calendar.dayNames = ["Ahad", "Isnin", "Selasa", "Rabu", "Khamis", "Jumaat", "Sabtu"];
        $scope.uiConfig.calendar.dayNamesShort = ["Aha", "Isn", "Sel", "Rab", "Kha", "Jum", "Sab"];
        $scope.changeTo= 'English';
      } else {
        $scope.uiConfig.calendar.dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        $scope.uiConfig.calendar.dayNamesShort = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
        $scope.changeTo = 'Bahasa Malaysia';
      }
    };
    /* event sources array*/
    $scope.eventSources = [$scope.eventDatabase, $scope.eventsWeekend];
}]);
});
/* EOF */
