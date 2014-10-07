(function ($) {
	var FIXED_FIELD_008_DIALOG_WIDTH = 800;
	var FIXED_FIELD_008_DIALOG_HEIGHT = 600;

	var FIXED_FIELD_007_DIALOG_WIDTH = 590;
	var FIXED_FIELD_007_DIALOG_HEIGHT = 430;
	
    var _methods = {};
    var tag;
    var fixedFieldData = null;
    var form;

    var methods = {
        init: function (options) {
            debug.log("No init required. This is a utility method");
        },
        data: function () {
        	return fixedFieldData;
        },
        currentCharPosVal: function (defn) {
            var _start = defn.start;
            var _stop = defn.stop + 1;
            var _len = defn.len;
            if (fixedFieldData == null || fixedFieldData.length <= 0 || fixedFieldData.substring(_start, _start + _len).length <= 0) {
                return $.rightPad(defn.defaultValue, _len, '#').replace(/ /g, "#");
            }
            return $.rightPad(fixedFieldData.substring(_start, _stop), _len, "#").replace(/ /g, "#");
        },
        serialize: function () {
            var values = [];
            $.each($(this).serializeArray(), function () {
                values.push($(this).val());
            });
            return values.join("").replace(/#/g, " ");
        },
        '006': function (options) {
        	paintFixedLengthDataElementsDialog(this, options);
        },
        '007': function (options) {
        	paintPhysicalDescriptionDialog(this, options);
        },
        '008': function (options) {
        	paintFixedLengthDataElementsDialog(this, options);
        },
        paint007: function (data) {
            debug.group("construct physical description fields");
            fixedFieldData = data.fixedFieldData;
            console.log(fixedFieldData);
            tag = data.tag;
            console.log(tag);
            
            var defn = data.fixedFieldDefn;
            //console.log(data.fixedFieldDefn);
            form = this;
            var fieldset = $(document.createElement('fieldset')).addClass('leader');
            var dynFieldset = $(document.createElement('fieldset')).addClass('leader');

            var fieldLen = defn.stop - defn.start + 1;
        	var descLabel = $(document.createElement('label')).attr({"for": "fixedField0"}).addClass('desc').html(defn.desc);
            var labelHtml = "[" + $.leftPad(defn.start, 2, '0') + "]";
        	var label = $(document.createElement('label')).attr({"for": "fixedField0"}).addClass('length').html(labelHtml);
        	fieldset.append(label);
        	fieldset.append(descLabel);
        	// var value = $.fixedField('currentCharPosVal', {start: defn.start, stop: defn.start + defn.len, len: defn.len});
        	var value = "";
            if (fixedFieldData.substring(0,1) != ' ') {
            	value = fixedFieldData.substring(0,1);
            }

            debug.group(labelHtml, defn.len, defn.desc, value);
            var select = $(document.createElement('select')).attr({
                "id": "fixedField" + defn.start,
                "name": defn.start,
                "class": "leader"
            });
            select.data("defn", defn);
            select.on('change', function(event) {
            	var defn = $(this).data("defn");
            	var value = $(this).val();
            	var selectedDefn = defn.enum[value].defn;
            	fixedFieldData = $.rightPad(value, getMaxLength(selectedDefn), ' ');
            	paintDynamicFields(dynFieldset, selectedDefn, ['0']);
                form.dialog('option', 'title', tag + ': ' + fixedFieldData.replace(' ', '&nbsp;'));
            });
            $.each(defn.enum, function (selectKey, selectVal) {
                debug.debug(selectKey, selectVal);
                var option = $(document.createElement('option'));
                option.val($.rightPad(selectKey, defn.len, '#'));
                option.html(selectKey + "-" + selectVal.desc);
                select.append(option);
            });
            console.log(value);
            if (value.length > 0){
	            select.val(value);
	        	var selectedDefn = defn.enum[value].defn;
	        	fixedFieldData = $.rightPad(fixedFieldData, getMaxLength(selectedDefn), ' ');
	        	paintDynamicFields(dynFieldset, selectedDefn, ['0']);
            }
            fieldset.append(select);
            debug.groupEnd();

            // paint accordion
            var accordion = $(document.createElement('div'));
            var content = $(document.createElement('div'));
            content.append(fieldset);
            content.append(dynFieldset);
            accordion.append(content);

            form.empty();
            form.append(accordion);
            
            debug.groupEnd();

            return this;
        },
        paint: function (data) {
            debug.group("construct fixed-length data element fields");
            fixedFieldData = data.fixedFieldData;
            tag = data.tag;

            var fixedFieldDefn = data.fixedFieldDefn;
            form = this;
            var fieldset = $(document.createElement('fieldset')).addClass('leader');
            paintDynamicFields(fieldset, fixedFieldDefn);

            // paint accordion
            var accordion = $(document.createElement('div'));
            var content = $(document.createElement('div'));
            content.append(fieldset);
            accordion.append(content);

            form.empty();
            form.append(accordion);
            debug.log("Appended fieldset", fieldset);

            debug.groupEnd();

            return this;
        }
    }

    function paintFixedLengthDataElementsDialog(editor, options) {
    	var leader = options.leader;
		var fixedFieldDefn = options.fixedFieldDefn;
		var fixedFieldLength = options.fixedFieldLength;
    	var editData = options.editData;
    	var grid = options.grid;

		var tag = editData.tag;
		var fixedFieldData = editData.bib;
		
		var leader06 = leader.substring(6,7);
		var leader07 = leader.substring(7,8);
		var materialConfiguration = $.materialConfiguration(leader06, leader07);

		debug.info('show Fixed Length Data Elements dialog', leader06, leader07, materialConfiguration);
		
		if (fixedFieldData.length < fixedFieldLength) {
			fixedFieldData = $.leftPad(fixedFieldData, fixedFieldLength, ' ');
		}
		
		var dialog = $(document.createElement('div')).css({'display': 'none'});
		dialog.fixedField('paint', {fixedFieldDefn: fixedFieldDefn[materialConfiguration], fixedFieldData: fixedFieldData, tag: tag});
		editor.append(dialog);
        dialog.dialog({
            autoOpen: false,
            dialogClass: 'fixedField',
            height: FIXED_FIELD_008_DIALOG_HEIGHT,
            width: FIXED_FIELD_008_DIALOG_WIDTH,
            modal: true,
            title: tag + ': ' + fixedFieldData.replace(' ', '&nbsp;'),
            buttons: {
                'Save': function() {
                	var fixedFieldData = $(this).fixedField('data');
                	editData.bib = fixedFieldData;
                    $(this).dialog('close');
                },
                Cancel: function() {
                    $(this).dialog('close');
                }
            },
            close: function() {
            	debug.debug('activeCell', grid.getActiveCell());
            	var row = grid.getActiveCell().row;
            	grid.invalidateRow(row);
            	grid.render();
                $(this).remove();
            }
        });
        dialog.dialog('open');
	}

    function paintPhysicalDescriptionDialog(editor, options) {
		var fixedFieldDefn = options.fixedFieldDefn;
		var fixedFieldLength = options.fixedFieldLength;
    	var editData = options.editData;
    	console.log ("editData in fixedField: ");
    	console.log(editData);
    	var grid = options.grid;

		debug.info('show physical description dialog');

		var tag = editData.tag;
		var fixedFieldData = editData.bib;

		/*
		if (fixedFieldData.length < fixedFieldLength) {
			fixedFieldData = $.leftPad(fixedFieldData, 2, ' ');
		}*/

		var dialog = $(document.createElement('div')).css({'display': 'none'});
		editor.append(dialog);

		debug.log(fixedFieldDefn);
		console.log ("fixedFieldData in fixedField: ");
		console.log(fixedFieldData);
		dialog.fixedField('paint007', {fixedFieldDefn: fixedFieldDefn, fixedFieldData: fixedFieldData, tag: tag});
        dialog.dialog({
            autoOpen: false,
            dialogClass: 'fixedField',
            height: FIXED_FIELD_007_DIALOG_HEIGHT,
            width: FIXED_FIELD_007_DIALOG_WIDTH,
            modal: true,
            title: tag + ': ' + fixedFieldData.replace(' ', '&nbsp;'),
            buttons: {
                'Save': function() {
                	var fixedFieldData = $(this).fixedField('data');
                	editData.bib = fixedFieldData;
                    $(this).dialog('close');
                },
                Cancel: function() {
                    $(this).dialog('close');
                }
            },
            close: function() {
            	debug.debug('activeCell', grid.getActiveCell());
            	var row = grid.getActiveCell().row;
            	grid.invalidateRow(row);
            	grid.render();
                $(this).remove();
            }
        });
        dialog.dialog('open');
    }

    function getMaxLength(defn) {
    	var maxLength = 1;
    	for(var key in defn) {
    		maxLength = defn[key].stop + 1;
    	}
    	return maxLength;
    }

    function paintDynamicFields(fieldset, fixedFieldDefn, exclusion) {
    	fieldset.empty();
        $.each(fixedFieldDefn, function (key, defn) {
        	if (exclusion == undefined || $.inArray(key, exclusion) == -1) {
	            var fieldLen = defn.stop - defn.start + 1;
	            var count = fieldLen / defn.len;
	            for(var index = 0; index < count; index++) {
	            	var descLabel = $(document.createElement('label')).attr({"for": "fixedField" + key}).addClass('desc').html(defn.desc);
	                var labelHtml;
	                if (defn.len > 1) {
	                    labelHtml = "[" + $.leftPad(defn.start + index, 2, '0') + "-" + $.leftPad(defn.stop + index, 2, '0') + "]";
	                } else {
	                    labelHtml = "[" + $.leftPad(defn.start + index, 2, '0') + "]";
	                }
	            	var label = $(document.createElement('label')).attr({"for": "fixedField" + key}).addClass('length').html(labelHtml);
	            	fieldset.append(label);
	            	fieldset.append(descLabel);
	                var value = $.fixedField('currentCharPosVal', {start: defn.start + index, stop: defn.start + index + defn.len, len: defn.len});
	                debug.group(labelHtml, defn.len, defn.desc, value);
	                switch (defn.type) {
	                case "auto":
	                	if (!value) {
	                		value = defn.defaultValue;
	                	}
	                    fieldset.append($(document.createElement('input')).attr({'readonly':'readonly'}).val(value));
	                    var input = $(document.createElement('input')).attr({
	                        "id": "fixedField" + defn.start,
	                        "name": defn.start,
	                        "class": "leader",
	                        "type": "hidden"
	                    }).val(value);
	                    updateFixedFieldData({'start': defn.start + index, 'len': defn.len}, input.val());
	                    fieldset.append(input);
	                    break;
	                case "select":
	                	if (defn.url) {
	                        var select = $(document.createElement('select')).attr({
	                            "id": "fixedField" + defn.start,
	                            "name": defn.start
	                        });
	                        select.data("defn", defn);
		                    select.data('index', index);
	                        select.on('click change', function(event) {
	                        	var defn = $(this).data("defn");
	                        	updateFixedFieldData({'start': defn.start, 'len': defn.len}, $(this).val());
	                            form.dialog('option', 'title', tag + ': ' + fixedFieldData.replace(' ', '&nbsp;'));
	                        });
	                        select.fillInput('url', {url: defn.url, parser: defn.parser, value: value});
	                        updateFixedFieldData({'start': defn.start + index, 'len': defn.len}, value);
	                        fieldset.append(select);
	                    } else if (defn.list.length == 1) {
	                        defn.defaultValue = defn.list[0].code;
	                        var input = $(document.createElement('input')).attr({
	                            "id": "fixedField" + defn.start,
	                            "name": defn.start,
	                            "class": "leader",
	                            "type": "hidden",
	                        }).val(value);
	                        updateFixedFieldData({'start': defn.start + index, 'len': defn.len}, input.val());
	                        fieldset.append(input);
	                    } else {
	                        var select = $(document.createElement('select')).attr({
	                            "id": "fixedField" + defn.start,
	                            "name": defn.start,
	                            "class": "leader"
	                        });
	                        select.data("defn", defn);
		                    select.data('index', index);
	                        select.on('click change', function(event) {
	                        	var defn = $(this).data("defn");
		                    	var index = $(this).data('index');
	                        	//debug.log('select change', defn, $(this).val());
		                    	updateFixedFieldData({'start': defn.start + index, 'len': defn.len}, $(this).val());
	                            form.dialog('option', 'title', tag + ': ' + fixedFieldData.replace(' ', '&nbsp;'));
	                        });
	                        var notSelected = true;
	                        debug.group("Option for " + labelHtml);
	                        $.each(defn.list, function (selectKey, selectVal) {
	                            debug.debug(selectKey, selectVal);
	                            var option = $(document.createElement('option'));
	                            option.val($.rightPad(selectVal.code, parseInt(defn.len), '#'));
	                            option.html(selectVal.code + "-" + selectVal.desc);
	                            select.append(option);
	                        });
	                        select.val(value);
	                        updateFixedFieldData({'start': defn.start + index, 'len': defn.len}, select.val());
	                        fieldset.append(select);
	                        debug.groupEnd();
	                    }
	                    break;
	                default:
	                	var input = $(document.createElement('input')).attr({
	                        "id": "fixedField" + defn.start,
	                        "name": defn.start,
	                        "class": "leader",
	                        "maxlength": defn.len,
	                        "type": "text"
	                	}).val(value.replace(/#/g, " "));
	                    input.data('defn', defn);
	                    input.data('index', index);
	                	input.on('keyup', function(event) {
	                    	var defn = $(this).data('defn');
	                    	var index = $(this).data('index');
	                		//debug.log('input keyup', defn, $(this).val());
	                    	updateFixedFieldData({'start': defn.start + index, 'len': defn.len}, $(this).val());
	                        form.dialog('option', 'title', tag + ': ' + fixedFieldData.replace(' ', '&nbsp;'));
	                	})
	                    updateFixedFieldData({'start': defn.start + index, 'len': defn.len}, input.val());
	                	fieldset.append(input);
	                }
	                //always create a <br> after each input
	                fieldset.append($(document.createElement('br')));
	                debug.groupEnd();
	            }
            }
        });
    }

    function updateFixedFieldData(defn, value) {
        var _start = defn.start;
        var _len = defn.len;
        fixedFieldData = fixedFieldData.substring(0, _start) + $.rightPad(value, _len, ' ') + fixedFieldData.substring(_start + _len);
        fixedFieldData = fixedFieldData.replace(/#/g, " ");
    	//debug.log(fixedFieldData);
    }
    
    //call as $.fixedField
    $.fixedField = function (method) {
        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method == 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exists in jQuery.FixedField');
        }
    }

    //extend from jquery - called as $('').fixedField('methodname', data).
    $.fn.fixedField = function (method) {
        // Method calling logic
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method == 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exists in jQuery.fixedField');
        }
    }

    $.materialConfiguration = function (leader06, leader07) {
    	var configuration;
    	switch (leader06) {
    	case 'a':
    		switch(leader07) {
    		case 'a':
    		case 'c':
    		case 'd':
    		case 'm':
    			configuration = 'bk';
    			break;
    		case 'b':
    		case 'i':
    		case 's':
    			configuration = 'cr';
    			break;
    		}
    		break;
    	case 't':
    		configuration = 'bk';
    		break;
    	case 'c':
    	case 'd':
    	case 'i':
    	case 'j':
    		configuration = 'mu';
    		break;
    	case 'e':
    	case 'f':
    		configuration = 'mp';
    		break;
    	case 'g':
    	case 'k':
    	case 'o':
    	case 'r':
    		configuration = 'vm';
    		break;
    	case 'm':
    		configuration = 'cf';
    		break;
    	case 'p':
    		configuration = 'mx';
    		break;
    	}
    	return configuration;
    }

})(jQuery);