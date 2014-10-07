/*
 * Reference: http://mleibman.github.io/SlickGrid/examples/example3-editing.html
 */
(function ($) {
	var container;
    var methods = {
        init: function (params) {
            debug.log("No init required. This is a utility method");
        },
        paint: function (params) {
        	console.log("entered fileUploader.paint")
        	var mode = params.mode;
        	var marcid = params.marcid;
        	
        	var panel = $(".options-panel");
        	var fileUpload = $(".file-upload");
        	
        	fileUpload.empty();
        	
			var browseButton = document.createElement("input");
			browseButton.setAttribute("type", "file");
			browseButton.setAttribute("id", "file");
			browseButton.setAttribute("name", "file");
			browseButton.setAttribute("multiple", "multiple");
			var browseMsg = $(document.createElement('h3')).append(
					'Select a marc file ', browseButton, '');

			fileUpload.append(browseMsg);
			
			$("#file").change(function(e) {
				if (!document.getElementById('uploadFile'))	 {
					var uploadButton = document.createElement("input"); 
					uploadButton.setAttribute("id", "uploadFile"); 
					uploadButton.setAttribute("value", "OK"); 
					uploadButton.setAttribute("alt", "Upload"); 
					uploadButton.setAttribute("type", "button"); 
					var uploadMsg = $(document.createElement('h3')).append(
							'Click ', uploadButton, ' to upload record');
				
					fileUpload.append(uploadMsg);
				}
				
				uploadButton.onclick = function startUpload(e) {
					var fd = new FormData();
					var inputs = $("input[type=file]");
					fd.append('file', inputs.eq(0).prop("files")[0]);
					
					container.empty();
					panel.empty();
					
					switch(mode) {
                	case 'add':
                		mapper.execute('uploadFile', {
							'library' : library,
							'userid' : userid,
							'cipher' : cipher
						}, fd).onreadystatechange = function(orsc) {
					        if (orsc.target.readyState == 4 && orsc.target.status == 200) {
					            var marc = orsc.target.responseText;
					            var record = new json2marc(jQuery.parseJSON( marc ));
								debug.debug('upload marc file', record);
								container.marcControl('add', {
									record : record
								});
					        }
					    };
                		break;
                	case 'edit':
                		if (confirm("Overwrite the current record: " + marcid + "?")) {
						    mapper.execute('uploadFile', {
								'library' : library,
								'userid' : userid,
								'cipher' : cipher
							}, fd).onreadystatechange = function(orsc) {
						        if (orsc.target.readyState == 4 && orsc.target.status == 200) {
						            var marc = orsc.target.responseText;
						            var record = new json2marc(jQuery.parseJSON( marc ));
									debug.debug('upload marc file', record);
									container.marcControl('edit', {
										marcid : marcid,
										record : record
									});
						        }
						    };
						}else{
							mapper.execute('uploadFile', {
								'library' : library,
								'userid' : userid,
								'cipher' : cipher
							}, fd).onreadystatechange = function(orsc) {
						        if (orsc.target.readyState == 4 && orsc.target.status == 200) {
						            var marc = orsc.target.responseText;
						            var record = new json2marc(jQuery.parseJSON( marc ));
									debug.debug('upload marc file', record);
									container.marcControl('add', {
										record : record
									});
						        }
						    };
						}
                		break;
                	}
				};
			});
        }
	};
    //call as $.fileUploader
    $.fileUploader = function (method) {
    	container = this;
        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method == 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exists in jQuery.fileUploader');
        }
    }

    //extend from jquery - called as $('').fileUploader('methodname', data).
    $.fn.fileUploader = function (method) {
    	container = this;
        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method == 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exists in jQuery.fileUploader');
        }
    }

})(jQuery);