(function($) {
	// Request.Mapper
	$.extend(true, window, {
		Request : {
			Mapper : RequestMapper
		}
	});

	function RequestMapper(options) {
		function execute(alias, params, data) {
			
			var url = resolve(options[alias].url, params);
			var type = options[alias].type;
			var contentType = options[alias].contentType;
			if (typeof contentType === 'undefined') {
				contentType = 'application/json; charset=utf-8'
			};
			debug.log('mapper.execute', type, url, contentType);
			
			if (data instanceof FormData) {
				var xhr = new XMLHttpRequest();
				xhr.open(type, url);
				xhr.send(data);
				return xhr;
			}else {
				var promise = $.ajax({
					type : type,
					url : url,
					contentType : contentType,
					data : data,
					dataType : 'json'
				});
	
				return promise;
			}
		}
		
		function resolve(source, params) {
			var dest = source;

			for ( var param in params) {
				var el = "{" + param + "}";
				var value = params[param];
				dest = dest.replace(el, value);
			}

			return dest;
		}

		// ////////////////////////////////////////////////////////////////////////////////////////////
		// Public API

		$.extend(this, {
			'execute' : execute
		});
	}

}(jQuery));