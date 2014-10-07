(function ($) {
	var container;

    var methods = {
    	add: function(params) {
    		var record = params.record;
    		
    		this.empty();
    		
    		var marcIdMsg = $(document.createElement('h3')).append("Mode: Add, Record ID : New");
    		container.append(marcIdMsg);
    		
    		console.log("container");
    		console.log(container);
    		debug.debug(record);
    		if (record == undefined) {
    			record = new json2marc();
    		}
    		
            container.leader('paint', {
	            leaderDefn: leaderDefn,
	            leader: record.leader
            })
            container.marcEditor('paint', {mode: 'add', record: record})
            // container.fileUploader('paint', {mode: 'add'});
    	},
        edit: function (params) {
        	var marcid = params.marcid;
        	var record = params.record;
        	
        	this.empty();
        	
        	var marcIdMsg = $(document.createElement('h3')).append("Mode: Edit, Record ID : " + marcid);
        	container.append(marcIdMsg);
    		
            container.leader('paint', {
	            leaderDefn: leaderDefn,
	            leader: record.leader
            })
            container.marcEditor('paint', {mode: 'edit', marcid: marcid, record: record})
            container.fileUploader('paint', {mode: 'edit', marcid: marcid});
        }
    }

    // call as $.marcControl
    $.marcControl = function (method) {
    	container = this;
        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method == 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exists in jQuery.marcEditor');
        }
    }

    // extend from jquery - called as $('').marcControl('methodname', data).
    $.fn.marcControl = function (method) {
    	container = this;
        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method == 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exists in jQuery.marcEditor');
        }
    }

})(jQuery);