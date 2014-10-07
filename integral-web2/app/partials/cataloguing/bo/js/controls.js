/*
 * Reference: http://www.switchonthecode.com/tutorials/xml-parsing-with-jquery
 * Reference: http://api.jquery.com/category/selectors/
 * Reference: http://docs.jquery.com/Plugins/Authoring#Plugin_Methods
 */

// make sure required JavaScript modules are loaded
if (typeof jQuery === "undefined") {
	throw new Error("fillInput requires jquery module to be loaded");
}

(function($) {

	var methods = {
		url: function(options) {
			var self = $(this);
			$.ajax({
				type: "GET",
				url: options.url,
				dataType: "xml"
			}).done(function(xml) {
				options.parser(self, xml, options.value);
			});
			return self;
		},
		enum: function(options) {
			var self = $(this);
            $.each(options.values, function (selectKey, selectVal) {
                debug.debug(selectKey, selectVal);
                var option = $(document.createElement('option'));
                option.val($.rightPad(selectKey, options.len, '#'));
                option.html(selectKey + "-" + selectVal);
                if (selectKey == '#') {
                	self.prepend(option);
                } else {
                	self.append(option);
                }
            });
            return self;
		},
		options: function(values) {
			var self = $(this);
			for(var index=0; index < values.length; index++) {
                var option = $(document.createElement('option'));
                option.val(values[index]);
                option.html(values[index]);
                self.append(option);
			}
			return self;
		}
	}

	$.fn.fillInput = function(method) {
		element = $(this);
		// Method calling logic
		if ( methods[method] ) {
			return methods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
		} else if ( typeof method == 'object' || !method ) {
			return methods.init.apply( this, arguments );
		} else {
			$.error( 'Method ' + method + ' does not exists on jQuery.fillInput' );
		}
	}

})(jQuery);