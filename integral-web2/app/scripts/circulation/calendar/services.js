'use strict'

define(['app'], function (integralApp) {
integralApp.factory('calendarService',
				function($http, $q, $log, HolidayRepository, WorkingDayRepository) {
					var calendar = {};
					var y = (new Date()).getFullYear();
					
					calendar.eventToHoliday = function (event){
						var holiday = {};
						holiday.description = event.title;
						var initial = event.title.replace(/[^A-Z]/g, '');
						holiday = constructIntegralDate(holiday, event.start, event.end);
						var postfix = ($.inArray('customFeed', event.className)!==-1 ? "@weekend" : "@google");
						holiday.code =  initial + getDateString(holiday) + postfix;
					
						return holiday;
					}
					
					calendar.holidayToEvent = function (holiday){
						var event = {};
						var start = new Date(y, holiday.startMonth - 1, holiday.startDayOfMonth);
    				var end = new Date(y, holiday.endMonth - 1, holiday.endDayOfMonth);
    				var allDay = true;
    				
  					event.id= holiday.id,
  					event.title= holiday.description,
  					event.start= start,
  					event.end= end,
  					event.allDay= allDay,
  					event.title= holiday.description
						return event;
					}
					
					calendar.newHolidayToHoliday = function (newHoliday){
						var holiday = {};
          	holiday.description = newHoliday.description;
            holiday = constructIntegralDate(holiday, newHoliday.startDate, newHoliday.endDate);
            var initial = holiday.description.replace(/[^A-Z]/g, '');
						holiday.code =  initial + getDateString(holiday);
						
						return holiday;
					}
					
					calendar.workingDayToEvents = function (events, workingDay, n){
					    if (workingDay.workingHours != null && workingDay.workingHours.length> 0){
  						  var day = n.getDate();
                var month = n.getMonth();
                var year = n.getFullYear();
                var timeArray = workingDay.workingHours.split('&');
                for(var i = 0; i < timeArray.length; i++) {
                   var startEnd = timeArray[i].split('-');
                   var start = startEnd[0];
                   var end = startEnd[1];
                   var startTimeMinute = start.split(':');
                   var startTime = parseInt(startTimeMinute[0]);
                   var startMinute = parseInt(startTimeMinute[1]);
                   var endTimeMinute = end.split(':');
                   var endTime = parseInt(endTimeMinute[0]);
                   var endMinute = parseInt(endTimeMinute[1]);
                   events.push({title: 'Library Opening Hours',start: new Date(year, month, day, startTime, startMinute),end: new Date(year, month, day, endTime, endMinute),allDay: false, color: 'rgb(70, 130, 180)'});
                }
              }
              return events;
					}
					
					calendar.selectedRangeToWorkingHours = function (range){
						  var startHour = range.startDate.getHours();
						  startHour = ("0" + startHour).slice(-2);
						  var startMinute = range.startDate.getMinutes();
						  startMinute = ("0" + startMinute).slice(-2);
						  var endHour = range.endDate.getHours();
						  endHour = ("0" + endHour).slice(-2);
						  var endMinute = range.endDate.getMinutes();
						  endMinute = ("0" + endMinute).slice(-2);
              
              return startHour + ":" + startMinute + "-" + endHour + ":" + endMinute;
					}
					
					calendar.getHolidays = function (branch){
	            return HolidayRepository.customGETLIST('search/findByBranch', { "branch": branch})
	                .then(function(response) {
	                    if (typeof response === 'object') {
	                        return response;
	                    } else {
	                        // invalid response
	                        return $q.reject(response);
	                    }
	                }, function(response) {
	                    // something went wrong
	                    return $q.reject(response);
	            	});
					}
					
					calendar.getWeekends = function (branch){
	            return WorkingDayRepository.customGETLIST('search/findByBranchAndWorking', { "branch": branch, "working": false })
	                .then(function(response) {
	                    if (typeof response === 'object') {
	                        return response;
	                    } else {
	                        // invalid response
	                        return $q.reject(response);
	                    }
	                }, function(response) {
	                    // something went wrong
	                    return $q.reject(response);
	            	});
					}
					
					calendar.setWeekend = function (workingDay){
	            return WorkingDayRepository.customGETLIST('search/findByBranchAndDayOfWeek', 
              { "branch":workingDay.branch.id, "dayOfWeek": workingDay.dayOfWeek })
	                .then(function(response) {
	                    if (typeof response === 'object') {
	                        var workingDays = response;
	                        var branch = workingDay.branch;
                          $log.debug ("workingDays", workingDays);
                          if (workingDays.length > 0){
                              $log.debug ("working day exists");
                              workingDay = workingDays[0];
                              workingDay.branch = branch;
                              $log.debug ("workingDay", workingDay);
                              $log.debug ("branch", workingDay.branch);
                          }else{
                              $log.debug ("working day doesn't exists");
                              workingDay.code = workingDay.dayOfWeek + "_" + workingDay.branch.code;
                          }
                          workingDay.branch = branch;
                          workingDay.working = false; 
                          return workingDay;
	                    } else {
	                        // invalid response
	                        return $q.reject(response);
	                    }
	                }, function(response) {
	                    // something went wrong
	                    return $q.reject(response);
	            	});
					}
					
					calendar.addWorkingHours = function (workingDay, workingHours){
	            return WorkingDayRepository.customGETLIST('search/findByBranchAndDayOfWeek', 
              { "branch":workingDay.branch.id, "dayOfWeek": workingDay.dayOfWeek })
	                .then(function(response) {
	                    if (typeof response === 'object') {
	                        var workingDays = response;
	                        var branch = workingDay.branch;
                          $log.debug ("workingDays", workingDays);
                          if (workingDays.length > 0){
                              $log.debug ("working day exists");
                              workingDay = workingDays[0];
                              workingDay.branch = branch;
                              if (workingDay.workingHours != null && workingDay.workingHours.length > 0){
                                workingDay.workingHours = workingDay.workingHours + "&";
                              }else{
                                workingDay.workingHours = "";
                              }
                              workingDay.workingHours = workingDay.workingHours + workingHours;
                          }else{
                              $log.debug ("working day doesn't exists");
                              workingDay.code = workingDay.dayOfWeek + "_" + workingDay.branch.code;
                              workingDay.workingHours = workingHours;
                          }
                          workingDay.branch = branch;
                          workingDay.working = true; 
                          return workingDay;
	                    } else {
	                        // invalid response
	                        return $q.reject(response);
	                    }
	                }, function(response) {
	                    // something went wrong
	                    return $q.reject(response);
	            	});
					}
					
					calendar.removeWorkingHours = function (workingDay, workingHours){
	            return WorkingDayRepository.customGETLIST('search/findByBranchAndDayOfWeek', 
              { "branch":workingDay.branch.id, "dayOfWeek": workingDay.dayOfWeek })
	                .then(function(response) {
	                    if (typeof response === 'object') {
	                        var workingDays = response;
	                        var branch = workingDay.branch;
	                        var newWorkingHours;
                          $log.debug ("workingDays", workingDays);
                          if (workingDays.length > 0){
                              $log.debug ("working day exists");
                              workingDay = workingDays[0];
                              workingDay.branch = branch;
                              if (workingDay.workingHours != null && workingDay.workingHours.length > 0){
                                newWorkingHours = workingDay.workingHours.replace('&' + workingHours,'');
                                newWorkingHours = newWorkingHours.replace(workingHours + '&' ,'');
                                newWorkingHours = newWorkingHours.replace(workingHours ,'');
                              }else{
                                return $q.reject("Selected opening hours doesn't exist");
                              }
                          }else{
                              return $q.reject("Working day doesn't exist");
                          }
                          workingDay.branch = branch;
                          workingDay.workingHours = newWorkingHours;
                          return workingDay;
	                    } else {
	                        // invalid response
	                        return $q.reject(response);
	                    }
	                }, function(response) {
	                    // something went wrong
	                    return $q.reject(response);
	            	});
					}
          
          calendar.getWeekendSelections = function (weekends){
              var daysOfWeek = {1: "Sunday", 2: "Monday", 3: "Tuesday", 4: "Wednesday", 5: "Thursday", 6: "Friday", 7: "Saturday"};
              var weekdays = daysOfWeek;
  	          angular.forEach(weekends, function(weekend, index) {
          		  $.each(daysOfWeek, function(k, v) {
          		    if (parseInt(k) === parseInt(weekend.dayOfWeek)){
          		        delete weekdays[k];
                  }
                });
          		});
          		return weekdays;
					}			
					
					calendar.getDefinedWeekends = function (weekends){
					    var daysOfWeek = {1: "Sunday", 2: "Monday", 3: "Tuesday", 4: "Wednesday", 5: "Thursday", 6: "Friday", 7: "Saturday"};
					    var definedWeekends = {};
					    $log.debug("weekends", weekends);
					    $log.debug("daysOfWeek", daysOfWeek);
	            angular.forEach(weekends, function(weekend, index) {
          		  $.each(daysOfWeek, function(k, v) {
          		    if (parseInt(k) === parseInt(weekend.dayOfWeek)){
          		        definedWeekends[k] = daysOfWeek[k];
            		      definedWeekends[k] = v;
                  }
                });
          		});
          		$log.debug("definedWeekends", definedWeekends);
          		return definedWeekends;
					}			
					
					calendar.getWorkingDaysByBranch = function (branch){
	            return WorkingDayRepository.customGETLIST('search/findByBranch', { "branch": branch })
	                .then(function(response) {
	                    if (typeof response === 'object') {
	                        return response;
	                    } else {
	                        // invalid response
	                        return $q.reject(response);
	                    }
	                }, function(response) {
	                    // something went wrong
	                    return $q.reject(response);
	            	});
					}
          		
					function constructIntegralDate (holiday, startDate, endDate){
					  if (endDate == null){
							endDate = startDate;
						}
					   
					  holiday.startMonth = startDate.getMonth() + 1;
						holiday.startDayOfMonth = startDate.getDate();
						holiday.endMonth = endDate.getMonth() + 1;
						holiday.endDayOfMonth = endDate.getDate();
						
						return holiday;
          }
          
          function getDateString (holiday){
					  return holiday.startDayOfMonth + "/" + (holiday.startMonth + 1) + "/" + y;
          }
          
          
					 return calendar;
				});
				
});
