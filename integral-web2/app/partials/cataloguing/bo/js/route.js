if ("onhashchange" in window) { // event supported?
	window.onhashchange = function() {
		console.log(window.location.hash.split('#')[1]);
		router.applyRoute(window.location.hash.split('#')[1]);
	}
} else {
	// Support starts at IE8, however be very carefull to have a correct
	// DOCTYPE,
	// because any IE going in Quircksmode will report having the event, but
	// never fires it.
	console.log("hashchanging not supported!");
}

(function() {
	var Route = function() {
		this.route = arguments[0].route;
		this.fn = arguments[0].fn;
		this.scope = arguments[0].scope ? arguments[0].scope : null;
		this.rules = arguments[0].rules ? arguments[0].rules : {};

		this.routeArguments = Array();
		this.optionalRouteArguments = Array();

		// Create the route arguments if they exist
		this.routeParts = this.route.split("/");
		for ( var i = 0, j = this.routeParts.length; i < j; i++) {
			var rPart = this.routeParts[i]

			// See if there are pseudo macro's in the route

			// will fetch all {id} parts of the route. So the manditory parts
			if (rPart.substr(0, 1) == "{"
					&& rPart.substr(rPart.length - 1, 1) == "}") {
				var rKey = rPart.substr(1, rPart.length - 2);
				this.routeArguments.push(rKey);
			}
			// will fetch all :id: parts of the route. So the optional parts
			if (rPart.substr(0, 1) == ":"
					&& rPart.substr(rPart.length - 1, 1) == ":") {
				var rKey = rPart.substr(1, rPart.length - 2);
				this.optionalRouteArguments.push(rKey);
			}
		}
	}

	Route.prototype.matches = function(route) {
		// We'd like to examen every individual part of the incoming route
		var incomingRouteParts = route.split("/");
		// This might seem strange, but assuming the route is correct
		// makes the logic easier, than assuming it is wrong.
		var routeMatches = true;
		// if the route is shorter than the route we want to check it against we
		// can immidiatly stop.
		if (incomingRouteParts.length < this.routeParts.length
				- this.optionalRouteArguments.length) {
			routeMatches = false;

		} else {
			for ( var i = 0, j = incomingRouteParts.length; i < j
					&& routeMatches; i++) {
				// Lets cache the variables, to prevent variable lookups by the
				// javascript engine
				var iRp = incomingRouteParts[i];// current incoming Route Part
				var rP = this.routeParts[i];// current routePart
				if (typeof rP == 'undefined') {
					// The route almost certainly doesn't match it's longer than
					// the route to check against
					routeMatches = false;
				} else {
					var cP0 = rP.substr(0, 1); // char at postion 0
					var cPe = rP.substr(rP.length - 1, 1);// char at last
					// postion
					if ((cP0 != "{" && cP0 != ":")
							&& (cPe != "}" && cPe != ":")) {
						// This part of the route to check against is not a
						// pseudo macro, so it has to match exactly
						if (iRp != rP) {
							routeMatches = false;
						}
					} else {
						// Since this is a pseudo macro and there was a value at
						// this place. The route is correct.
						// But a rule might change that
						if (this.rules != null) {
							var rKey = rP.substr(1, rP.length - 2);
							// RegExp will return as object. One more test
							// required
							if (this.rules[rKey] instanceof RegExp) {
								routeMatches = this.rules[rKey].test(iRp);
							}
							// Array will return as object
							if (this.rules[rKey] instanceof Array) {
								if (this.rules[rKey].indexOf(iRp) < 0) {
									routeMatches = false;
								}
							}
							if (this.rules[rKey] instanceof Function) {
								// getArgumentsObject see example package
								routeMatches = this.rules[rKey](iRp, this
										.getArgumentsObject(route), this.scope);
							}
						}
					}
				}
			}
		}

		return routeMatches;
	}

	Route.prototype.getArgumentsValues = function(route) {
		// Split the incoming route
		var rRouteParts = route.split("/");
		// Create an array for the values
		var rArray = new Array();
		for ( var i = 0, j = this.routeParts.length; i < j; i++) {
			var rP = this.routeParts[i];// current routePart
			var cP0 = rP.substr(0, 1); // char at postion 0
			var cPe = rP.substr(rP.length - 1, 1);// char at last postion
			if ((cP0 == "{" || cP0 == ":") && (cPe == "}" || cPe == ":")) {
				// if this part of the route was a pseudo macro,
				// either manditory or optional add this to the array
				rArray[rArray.length] = rRouteParts[i];
			}
		}
		return rArray;
	}

	// We'll create the alias for route in the window object
	window["Route"] = Route;

})();

(function() {
	var Router = function() {
		this.routes = new Array();
	}

	Router.prototype = {
		// Here we use a somewhat different style of create the prototype
		// than for the Route prototype. Both ways are valid.
		// I'm using them mixed here, but It's probably wise not to do that.
		// And stick to a single pattern. Here I'm doing it to show both
		// possibilities
		registerRoute : function(route, fn, paramObject) {
			// We'll have route and function as named parameters
			// and all the future optional parameters in a single object.
			// Right now we just have scope as a optional parameters
			var scope = paramObject ? paramObject.scope ? paramObject.scope
					: {} : {};
			var rules = paramObject ? paramObject.rules ? paramObject.rules
					: null : null;
			return this.routes[this.routes.length] = new Route({
				route : route,
				fn : fn,
				scope : scope,
				rules : rules
			});
		},
		applyRoute : function(route) {
			// iterate all routes
			for ( var i = 0, j = this.routes.length; i < j; i++) {
				var sRoute = this.routes[i];
				// match route
				if (sRoute.matches(route)) {
					// if true call callback function with the proper scope and
					// send in the variables
					sRoute.fn.apply(sRoute.scope, sRoute
							.getArgumentsValues(route));
				}
			}

		}

	}

	// We'll create an alias for router in the window object
	window["Router"] = Router;

	// We'll create an instance of router in the window object
	window["router"] = new Router();
})();

router.registerRoute("{library}/{userid}/ctmatm/{marcid}",
		function(library, userid, marcid) {
			// console.log("ctmatm/{marcid} route true, marcid=" + marcid);
			mapper.execute('findOne', {
				'library' : library,
				'userid' : userid,
				'marcid' : marcid,
				'cipher' : cipher
			}).done(function(marc) {
				var record = new json2marc(marc);
				var container = $('#container');
				container.empty();
				var panel = $(".options-panel");
				panel.empty();
				container.marcControl('edit', {
					marcid : marcid,
					record : record
				});
			});
		}, {
			rules : {
				marcid : new RegExp('^[0-9]+$')
			}
		});

router.registerRoute("{library}/{userid}/ctmatm/new",
		function(library, userid) {
			var container = $('#container');
			container.empty();
        	container.marcList('paint');
        	$(document.getElementById("list")).remove();
		});

router.registerRoute("{library}/{userid}/ctmatm/all",
		function(library, userid) {
			var container = $('#container');
			container.empty();
        	container.marcList('paint');
		});

router.registerRoute("{library}/{userid}/ctmatm/delete/{marcid}",
		function(library, userid, marcid) {
			// console.log("ctmatm/{marcid} route true, marcid=" + marcid);
			if (confirm("Confirm delete record " + marcid + "?")) {
				mapper.execute('deleteRecord', {
					'library' : library,
					'userid' : userid,
					'marcid' : marcid,
					'cipher' : cipher
				}).done(function(json) {
					$(".options-panel").remove();
					$(".file-upload").empty();
					var deletedMsg = $(document.createElement('h1')).append(
							'Record ' + marcid + ' is deleted successfully');

					$(".file-upload").append(deletedMsg);
				}).fail(function(data) {
					$(".options-panel").remove();
					$(".file-upload").empty();
					var deletedMsg = $(document.createElement('h1')).append(
							'Record ' + marcid + ' is unable to be deleted due to error : \r\n' + data.statusText + "\r\nPlease try again later.");
					$(".file-upload").append(deletedMsg);
				});
			}
		}, {
			rules : {
				marcid : new RegExp('^[0-9]+$')
			}
		});

