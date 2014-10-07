function FoundationMaintainanceListCtrl($scope) {
	var modules = [ {
		"name" : "gldept",
		"url" : "index.html#/repository/gldepts/search"
	}, {
		"name" : "gldesg",
		"url" : "index.html#/repository/gldesgs/search"
	}, {
		"name" : "glloca",
		"url" : "index.html#/repository/gllocas/search"
	}, {
		"name" : "glcate",
		"url" : "index.html#/repository/glcates/search"
	}, {
		"name" : "glrace",
		"url" : "index.html#/repository/glraces/search"
	}, {
		"name" : "glrelig",
		"url" : "index.html#/repository/glrelig/search"
	}, {
		"name" : "gltitle",
		"url" : "index.html#/repository/gltitles/search"
	}, {
		"name" : "gltown",
		"url" : "index.html#/repository/gltowns/search"
	}];
	$scope.modules = modules;
}