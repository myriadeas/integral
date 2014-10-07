(function ($) {
	var leaderDataSpan = $(document.createElement('span')).css({'font-family':'courier new, courier, monospace'});
    var _methods = {};
    var leaderData = null;
    var methods = {
        init: function (options) {
            debug.log("No init required. This is a utility method");
        },
        data: function () {
        	return leaderData;
        },
        currentCharPosVal: function (defn) {
            var _start = defn.start;
            var _stop = defn.stop + 1;
            var _len = defn.len;
            if (leaderData == null || leaderData.length <= 0 || leaderData.substring(_start, _start + _len).length <= 0) {
                return $.rightPad(defn.defaultValue, _len, '#').replace(/ /g, "#");
            }
            return $.rightPad(leaderData.substring(_start, _stop), _len, "#").replace(/ /g, "#");
        },
        serialize: function () {
            var values = [];
            $.each($(this).serializeArray(), function () {
                values.push($(this).val());
            });
            return values.join("").replace(/#/g, " ");
        },
        paint: function (data) {
            debug.group("construct leader form");
            leaderData = data.leader;

            var leaderDefn = data.leaderDefn;
            var form = this;
            var fieldset = $(document.createElement('fieldset')).addClass('leader');
            $.each(leaderDefn, function (key, defn) {
            	var descLabel = $(document.createElement('label')).attr({"for": "leader" + key}).addClass('desc').html(defn.desc);
                var labelHtml;
                if (defn.len > 1) {
                    labelHtml = "[" + $.leftPad(defn.start,2,'0') + "-" + $.leftPad(defn.stop,2,'0') + "]";
                } else {
                    labelHtml = "[" + $.leftPad(defn.start,2,'0') + "]";
                }
            	var label = $(document.createElement('label')).attr({"for": "leader" + key}).addClass('length').html(labelHtml);
            	fieldset.append(label);
            	fieldset.append(descLabel);
                var value = $.leader('currentCharPosVal', defn);
                debug.group(labelHtml, defn.len, defn.desc, value);
                switch (defn.type) {
                case "auto":
                	if (!value) {
                		value = defn.defaultValue;
                	}
                    fieldset.append($(document.createElement('input')).attr({'readonly':'readonly'}).val(value));
                    var input = $(document.createElement('input')).attr({
                        "id": "leader" + defn.start,
                        "name": defn.start,
                        "class": "leader",
                        "type": "hidden"
                    }).val(value);
                    fieldset.append(input);
                    break;
                case "select":
                    if (defn.enum && defn.enum.length == 1) {
                    	var desc;
                    	for(var key in defn.enum) {
                    		defn.defaultValue = key;
                    		desc = defn.enum[key];
                    	}
                        input.append(defn.defaultValue + "-" + desc);
                        var input = $(document.createElement('input')).attr({
                            "id": "leader" + defn.start,
                            "name": defn.start,
                            "class": "leader",
                            "type": "hidden",
                        }).val(value);
                        fieldset.append(input);
                    } else {
                        var select = $(document.createElement('select')).attr({
                            "id": "leader" + defn.start,
                            "name": defn.start,
                            "class": "leader"
                        });
                        select.data("defn", defn);
                        select.on('click change', function(event) {
                        	var defn = $(this).data("defn");
                        	//debug.log('select change', defn, $(this).val());
                        	updateLeaderData(defn, $(this).val());
                        });
                        var notSelected = true;
                        debug.group("Option for " + labelHtml);
                        select.fillInput('enum', {values: defn.enum, len: defn.len});
                        select.val(value);
                        fieldset.append(select);
                        debug.groupEnd();
                    }
                    break;
                default:
                	var input = $(document.createElement('input')).attr({
                        "id": "leader" + defn.start,
                        "name": defn.start,
                        "class": "leader",
                        "type": "text"
                	}).val(value);
                    input.data('defn', defn);
                	input.on('keyup', function(event) {
                    	var defn = $(this).data('defn');
                		//debug.log('input keyup', defn, $(this).val());
                    	updateLeaderData(defn, $(this).val());
                	})
                	fieldset.append(input);
                }
                //always create a <br> after each input
                fieldset.append($(document.createElement('br')));
                debug.groupEnd();
            });
            
            // paint accordion
            var accordion = $(document.createElement('div'));
            var header = $(document.createElement('h3')).text('LEADER ');
            leaderDataSpan.html(leaderData.replace(/ /g, "&nbsp;"));
            header.append(leaderDataSpan);
            var content = $(document.createElement('div'));
            content.append(fieldset);
            accordion.append(header, content);
            accordion.accordion({
            	collapsible: true,
            	active: false,
            	heightStyle: "content",
            	icons: { "header": "ui-icon-triangle-1-s", "activeHeader": "ui-icon-triangle-1-n" }
            });

            //form.empty();
            form.append(accordion);
            debug.log("Appended fieldset", fieldset);

            debug.groupEnd();

            return this;
        }
    }
    
    function updateLeaderData(defn, value) {
        var _start = defn.start;
        var _stop = defn.stop + 1;
        var _len = defn.len;
        leaderData = leaderData.substring(0, _start) + value + leaderData.substring(_start + _len);
        leaderData = leaderData.replace(/#/g, " ");
        leaderDataSpan.html(leaderData.replace(/ /g, "&nbsp;"));
    	//debug.log(leaderData);
    }
    
    //call as $.leader
    $.leader = function (method) {
        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method == 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exists in jQuery.leader');
        }
    }

    //extend from jquery - called as $('').leader('methodname', data).
    $.fn.leader = function (method) {
        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method == 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exists in jQuery.leader');
        }
    }
})(jQuery);