/*
 * Reference: http://mleibman.github.io/SlickGrid/examples/example3-editing.html
 */
(function ($) {
	var container;

	var grid;
	
	var data = [];
	
	var oriRecord = "";

    var columns = [
                   {id:"sel",   name:"#", field:"num", behavior:"select", cssClass:"cell-selection", width:20, cannotTriggerInsert:true, resizable:false, unselectable:true },
                   {id:"tag",   name:"Tag",                   field:"tag",   width:35, resizable:false, editor: TagCellEditor, validator:tagValidator},
                   {id:"ind1", name:"Indi1",           field:"ind1", width:40, resizable:false, editor: Ind1CellEditor, validator:indicator1Validator},
                   {id:"ind2", name:"Indi2",           field:"ind2", width:40, resizable:false, editor: Ind2CellEditor, validator:indicator2Validator},
                   {id:"bib",   name:"Bibliographic Details", field:"bib",   width:470, minWidth:150, formatter:BibliographicDataFormatter, editor:BibCellEditor, validator:biblioValidator }
                 ];

     var options = {
       editable: true,
       enableAddRow: false,
       enableCellNavigation: true,
       asyncEditorLoading: false
     };

    var methods = {
        init: function (params) {
            debug.log("No init required. This is a utility method");
        },
        paint: function (params) {
        	var mode = params.mode;
        	var marcid = params.marcid;
        	var record = params.record;
        	if (oriRecord === ""){
        		oriRecord = clone(params.record);
        	}
        	
            debug.group("construct marc editor");
            
            /*--
            var marcIdMsg = $(document.createElement('h3')).append("Mode: " + mode + 
            		" Record ID : " + (marcid !== undefined ? marcid : "New"));
            
            this.append(marcIdMsg);*/
            
            var div = $(document.createElement('div')).attr({'id': 'grid'}).css({'height':'446px'});

            var editor = this;

            this.append(div);
            var verify = $(document.createElement('button')).text('Verify').button();
            verify.on('click', function(event) {
            	var json = JSON.stringify(record.toJson());
            	mapper.execute('verify', {
					'library': library, 'userid': userid, 'cipher': cipher
				}, json).done(function(response) {
            		debug.debug(response);
            		alert(response.message);
				});
            });
            this.append(verify);
            
            var convert = $(document.createElement('button')).text('Convert').button();
            convert.on('click', function(event) {
            	var json = JSON.stringify(record.toJson());
            	mapper.execute('convert', {
					'library': library, 'userid': userid, 'cipher': cipher
				}, json).done(function(marc) {
            		debug.debug(marc);
            		var record = new json2marc(marc);
            		switch(mode) {
                	case 'add':
                		container.marcControl('add', {
    						record : record
    					});
                		break;
                	case 'edit':
                		container.marcControl('edit', {
    						marcid : marcid,
    						record : record
    					});
                		break;
                	}
            		alert('Record has been converted');
				});
            });
            this.append(convert);

            var save = $(document.createElement('button')).text('Save').button();
            save.on('click', function(event) {
            	/*
            	var err = recordValidator(data);
            	if (err.valid) {
	            	record.leader = $.leader('data');
	            	alert(JSON.stringify(record.toJson(), undefined, 2));
	            	console.log(record.toJson());
            	} else {
            		alert(err.msg);
            	}
            	*/
            	
            	var json = JSON.stringify(record.toJson());
            	
            	switch(mode) {
            	case 'add':
    				mapper.execute('createRecord', {
    					'library': library, 'userid': userid, 'cipher': cipher
    				}, json).done(function(marc) {
	            		record = new json2marc(marc);
	            		container.marcControl('edit', {
	            			marcid : getTagValue(marc, "001"),
							record : record
						});
	            		alert('New record created');
    				}).fail(function(data) {
    					console.log( data );
    				    alert('New record is either unable to be created or to be indexed due to error : \r\n' + data.statusText + "\r\nPlease try again later.");
    				});
            		break;
            	case 'edit':
            		console.log(json);
    				mapper.execute('updateRecord', {
    					'library': library, 'userid': userid, 'marcid': marcid, 'cipher': cipher
    				}, json).done(function(marc) {
	            		debug.debug(marc);
	            		record = new json2marc(marc);
	            		container.marcControl('edit', {
    						marcid : marcid,
    						record : record
    					});
	            		alert('Modifications saved');
    				}).fail(function(data) {
    					console.log( data );
    				    alert('Modifications are either unable to be saved or to be indexed due to error : \r\n' + data.statusText + "\r\nPlease try again later.");
    				 });
            		break;
            	}
            });
            this.append(save);
            
            var duplicate = $(document.createElement('button')).text('Duplicate').button();
            duplicate.on('click', function(event) {
            	
                container.marcControl('add', {
    				record : record
                });
    					
            	alert('Record has been duplicated');
            });
            
            if (mode === 'edit') this.append(duplicate);
            
            var reset = $(document.createElement('button')).text('Reset').button();
            reset.on('click', function(event) {
            	switch(mode) {
            	case 'add':
            		container.marcControl('add', {
						record : oriRecord
					});
            		break;
            	case 'edit':
            		container.marcControl('edit', {
						marcid : marcid,
						record : oriRecord
					});
            		break;
            	}
            });
            this.append(reset);

            var cancel = $(document.createElement('button')).text('Cancel').button();
            cancel.on('click', function(event) {
            	goBack();
            });
            this.append(cancel);

        	data = record.variableFields;
            var maxrows = (data.length > 40) ? data.length : 40;
            for(var i=0; i < maxrows; i++) {
            	if (!data[i]) {
            		data[i] = {};
            	}
            	data[i].num = i + 1;
            	if (data[i].bib == undefined) {
            		data[i].bib = '';
            	}
            }
            grid = new Slick.Grid("#grid", data, columns, options);
            //grid.setSelectionModel(new Slick.RowSelectionModel());

            var insertMenu = $(document.createElement('li')).attr('data', 'insert').text('Insert Row');
            var deleteMenu = $(document.createElement('li')).attr('data', 'delete').text('Delete Row');
            var contextMenu = $(document.createElement('ul')).attr('id', 'contextMenu').css({'display': 'none', 'position': 'absolute'});
            contextMenu.append(insertMenu, deleteMenu);
            contextMenu.on('click', function(e) {
    			debug.debug("contextMenu.click", $(e.target).attr("data"));
    			if(!$(e.target).is("li"))
    				return;
    			var row = $(this).data("row");
    			var task = $(e.target).attr("data");
    			switch(task) {
    			case 'insert':
    				data.splice(row, 0, {num:"", tag:"", indi1:"", indi2:"", bib:""});
    				data.splice(data.length-1, 1);
            		for (var i = row; i < data.length; i++) {
            			data[i].num = i + 1;
            			grid.updateRow(i);
            		}
    				break;
    			case 'delete':
    	            debug.debug(data.splice(row, 1));
    	            if (data.length < 40) {
    	        		var d = {};
    	        		data.push(d);
    	        		for (var i = row; i < data.length; i++) {
    	        			data[i].num = i + 1;
    	        			grid.updateRow(i);
    	        		}
    	            }
    	            break;
    			}
            });
            this.append(contextMenu);
            
            grid.onDblClick.subscribe(function(e, args){
            	debug.debug("onDblClick", args);
            	var row = args.row;	
    			var editData = data[row];
    			var tag = editData.tag
				
				var json = JSON.stringify(record.toJson());
            	mapper.execute('duplicateCheck', {
					'library': library, 'userid': userid, 'cipher': cipher, 'tag': tag
				}, json).done(function(result) {
            		if (result.exists){
            			alert ("Tag: " + tag + " with term : " + editData.bib + " already exists in the database.");
            		}
				});
			
            });

    		grid.onContextMenu.subscribe(function(e) {
    			e.preventDefault();
    			var cell = grid.getCellFromEvent(e);
    			contextMenu
    				.data("row", cell.row)
    				.css("top", e.pageY)
    				.css("left", e.pageX)
    				.show();
    			$("body").on("click", function() {
    				contextMenu.hide();
    			});
    		});

            grid.onBeforeEditCell.subscribe(function(e, args){
    			debug.debug("onBeforeEditCell", args);
    			var row = args.row;	
    			var editData = data[row];
    			var tag = editData.tag
    			if (args.cell == 4) {
    				switch(tag) {
    				case '006':
    					editor.fixedField('006', {
    						leader: $.leader('data'),
    						fixedFieldDefn: defn_006,
    						fixedFieldLength: 18,
    						grid: grid,
    						editData: editData
    					});
    					return false;
    				case '007':
    					console.log("editData in editor :");
    					console.log(editData);
    					editor.fixedField('007', {
    						fixedFieldDefn: defn_007['0'],
    						fixedFieldLength: 10,
    						grid: grid,
    						editData: editData
    					});
    					return false;
    				case '008':
    					editor.fixedField('008', {
    						leader: $.leader('data'),
    						fixedFieldDefn: defn_008,
    						fixedFieldLength: 40,
    						grid: grid,
    						editData: editData
    					});
    					return false;
    				}
    			}
            });
            
            grid.onActiveCellChanged.subscribe(function(e, args) {
            	var row = args.row;
            	var cell = args.cell;
            	var dataRow = data[row];
            	var tag = dataRow.tag;
            	
            	var panel = $(".options-panel");
            	panel.empty();

            	if (tag == undefined || tag == null || tag.length === 0) {
            		debug.log('tag is undefined');
            	} else if (marc21[tag] == undefined) {
	            	var h3 = $(document.createElement('h3')).text(tag + ' is invalid');
	            	panel.append(h3);
            	} else {
	            	var tagDesc = marc21[tag].desc;
	            	var repeatableTag = marc21[tag].repeatable ? '(R)' : '(NR)';
	            	var url = marc21[tag].referenceUrl;
	            	var h3 = $(document.createElement('h3')).text(tag + ' ' + tagDesc + ' ' + repeatableTag);
	            	
	            	a = document.createElement('a');
	            	a.innerText = tag + ' ' + tagDesc + ' ' + repeatableTag;
	            	a.textContent = tag + ' ' + tagDesc + ' ' + repeatableTag;
	            	a.href = url;
	            	a.style="text-decoration: none; color:black; font-family:Verdana, Arial, Helvetica, sans-serif;";
	            	panel.append(a);
	            	
	            	$('a').click( function() {
	            		window.open(url, '_blank');
	            		return false; 
	            	});

	            	var accordion = $(document.createElement('div'));

	            	var ind1Header = $(document.createElement('h4'))
	            	if (marc21[tag].ind1 == undefined) {
		            	ind1Header.text('No indicator 1');
	            		panel.append(ind1Header);
	            	} else {
		            	ind1Header.text('Indicator 1');
	            		accordion.append(ind1Header);
	            		
	            		var content = $(document.createElement('div')).css({'padding':'0.5em 1em'});
	            		var dl = $(document.createElement('dl')).addClass('dl-horizontal');
	            		var indicator1s = marc21[tag].ind1;
	            		for(var ind1 in indicator1s) {
	            			var dt = $(document.createElement('dt')).text(ind1);
	            			var dd = $(document.createElement('dd')).text(indicator1s[ind1]);
	            			dl.append(dt, dd);
	            		}
	            		content.append(dl);
	            		accordion.append(content);
	            	}
	            	
	            	var ind2Header = $(document.createElement('h4'))
	            	if (marc21[tag].ind2 == undefined) {
		            	ind2Header.text('No indicator 2');
	            		panel.append(ind2Header);
	            	} else {
		            	ind2Header.text('Indicator 2');
	            		accordion.append(ind2Header);
	            		
	            		var content = $(document.createElement('div')).css({'padding':'0.5em 1em'});
	            		var dl = $(document.createElement('dl')).addClass('dl-horizontal');
	            		var indicator2s = marc21[tag].ind2;
	            		for(var ind2 in indicator2s) {
	            			var dt = $(document.createElement('dt')).text(ind2);
	            			var dd = $(document.createElement('dd')).text(indicator2s[ind2]);
	            			dl.append(dt, dd);
	            		}
	            		content.append(dl);
	            		accordion.append(content);
	            	}
	            	
	            	var subfHeader = $(document.createElement('h4'))
	            	if (marc21[tag].subf == undefined) {
		            	subfHeader.text('No subfield');
	            		panel.append(subfHeader);
	            	} else {
		            	subfHeader.text('Subfields');
	            		accordion.append(subfHeader);
	            		
	            		var content = $(document.createElement('div')).css({'padding':'0.5em 1em'});
	            		var dl = $(document.createElement('dl')).addClass('dl-horizontal');
	            		var subfields = marc21[tag].subf;
	            		for(var subfield in subfields) {
	    	            	var repeatableSubfield = subfields[subfield].repeatable ? '(R)' : '(NR)';
	            			var dt = $(document.createElement('dt')).text(subfield);
	            			var dd = $(document.createElement('dd')).text(subfields[subfield].desc + ' ' + repeatableSubfield);
	            			dl.append(dt, dd);
	            		}
	            		content.append(dl);
	            		accordion.append(content);
	            	}

	            	var active = cell - 2;
	            	accordion.accordion({ collapsible: true, heightStyle: "content", active: (active >= 0 ? active : 0) });
	            	
	            	panel.append(accordion);
            	}
            });

            grid.onValidationError.subscribe(function(e, args){
            	debug.debug("onValidationError", args);
                alert(args.validationResults.msg);
            });

            debug.groupEnd();
        	
            return this;
        }
    }

    function paintEditor(record) {
    	
    }
    
    function getTagValue(marc, tag) {
    	var fields = marc.fields;
		for(var index=0; index < fields.length; index++) {
			var field = fields[index];
			for (var key in field) {
				if (key === tag){
					return field[key];
				}
			}
		}
    }

    function tagValidator(value) {
    	debug.debug('tagValidator', grid.getActiveCell(), value);
    	return _tagValidator(value, 0);
    }
    
    function _tagValidator(tag, maxOccur) {
    	maxOccur = maxOccur | 0;
    	if (tag.length == 0) {
        	return {valid:true};
    	} else if (tag.length != 3) {
            return {valid: false, msg: "Tag length must be 3"};
        } else if (marc21[tag] == null) {
        	return {value: false, msg: "'" + tag + "' is not a valid tag"};
        } else {
        	var tagCount = countTagOccurences(tag);
        	if (!marc21[tag].repeatable && tagCount > maxOccur) {
        		return {value: false, msg: "'" + tag + "' cannot be repeated"};
	        } else {
	        	return {valid:true};
	        }
    	}
    }

    function indicator1Validator(value) {
    	debug.debug('indicator1Validator', getActiveData(), value);
    	var tag = getActiveData().tag;
    	return _indicator1Validator(tag, value);
    }
    
    function _indicator1Validator(tag, value) {
    	//if (!_tagValidator(tag).valid) {
    	//	return {valid: false, msg: "Unable to verify indicator 1 for invalid tag '" + tag + "'" };
    	//}
    	var indi1 = (value !== undefined ? value.replace(/ /g, '#') : value);
        if (value != null && value.length > 1) {
            return {valid: false, msg: "Indicator 1 length must be 1"};
        } else if (marc21[tag] && marc21[tag].ind1 === undefined && indi1 != null) {
        	return {valid: false, msg: "Should not have indicator 1"};
        } else if (indi1 && marc21[tag] && marc21[tag].ind1[indi1] == null) {
        	return {valid: false, msg: "'" + indi1 + "' is not a valid indicator 1"};
        } else {
        	return {valid:true};
        }
    }

    function indicator2Validator(value) {
    	debug.debug('indicator2Validator', getActiveData(), value);
    	var tag = getActiveData().tag;
    	return _indicator2Validator(tag, value);
    }

    function _indicator2Validator(tag, value) {
    	//if (!_tagValidator(tag).valid) {
    	//	return {valid: false, msg: "Unable to verify indicator 2 for invalid tag '" + tag + "'" };
    	//}
    	var indi2 = (value !== undefined ? value.replace(/ /g, '#') : value);
        if (value != null && value.length > 1) {
            return {valid: false, msg: "Indicator 2 length must be 1"};
        } else if (marc21[tag] && marc21[tag].ind2 == undefined && indi2 != null) {
        	return {valid: false, msg: "Should not have indicator 2"};
        } else if (indi2 && marc21[tag] && marc21[tag].ind2[indi2] == null) {
        	return {valid: false, msg: "'" + indi2 + "' is not a valid indicator 2"};
        } else {
        	return {valid:true};
        }
    }

    function biblioValidator(value) {
    	var tag = getActiveData().tag;
    	return _biblioValidator(tag, value);
    }

    function _biblioValidator(tag, value) {
    	//if (!_tagValidator(tag).valid) {
    	//	return {valid: false, msg: "Unable to verify subfields for invalid tag '" + tag + "'" };
    	//}
    	var subfields = findSubfields(value);
    	var valid = true;
    	var msg = '';
    	for(var key in subfields) {
    		var subfield = key.substring(1);
    		if (marc21[tag] && marc21[tag].subf && marc21[tag].subf[subfield]) {
    			var isRepeated = subfields[key] > 1;
    			debug.debug(key, subfield, isRepeated);
    			if (isRepeated) {
    				var isRepeatable = marc21[tag].subf[subfield].repeatable;
    				debug.debug(key, subfield, isRepeated, isRepeatable);
    				if (!isRepeatable) {
    					valid = false;
    					if (msg.length > 0) {
            				msg += '\n';
            			}
            			msg += "'" + subfield + "' cannot be repeated";
    				}
    			}
    		} else {
    			if (msg.length > 0) {
    				msg += '\n';
    			}
    			msg += "'" + subfield + "' is not a valid subfield";
    			if (valid) {
    				valid = false;
    			}
    		}
    	}
    	return {valid: valid, msg: msg};
    }
    
    function recordValidator(record) {
    	var valid = true;
    	var msg = '';
    	for(var index=0; index < record.length; index++) {
    		var field = record[index];
    		if (field.tag !== undefined) {
    			var tag = field.tag;
    			var err = _tagValidator(tag, 1);
    			if (!err.valid) {
        			msg = mergeMessage(msg, err.msg);
	    			valid = false;
    			}
    			err = _indicator1Validator(tag, field.ind1);
    			if (!err.valid) {
        			msg = mergeMessage(msg, err.msg, "'" + tag + "'");
	    			valid = false;
    			}
    			err = _indicator2Validator(tag, field.ind2);
    			if (!err.valid) {
        			msg = mergeMessage(msg, err.msg, "'" + tag + "'");
	    			valid = false;
    			}
    			err = _biblioValidator(tag, field.bib);
    			if (!err.valid) {
        			msg = mergeMessage(msg, err.msg, "'" + tag + "'");
	    			valid = false;
    			}
    		}
    	}
    	return {valid: valid, msg: msg};
    }

    function mergeMessage(src, msg, prefix) {
		var lines = msg.split('\n');
		for(var index=0; index < lines.length; index++) {
			if (src.length > 0) {
				src += '\n';
			}
			if (prefix) {
				src += prefix + ': ';
			}
			src += lines[index];
		}
    	return src;
    }

    function BibliographicDataFormatter(row, cell, value, columnDef, dataContext) {
    	//debug.debug(row, cell, value, columnDef, dataContext);
    	if (value != undefined) {
    		return $('<div/>').html(value.replace(/ /g, '&nbsp;')).html();
    	} else {
    		return "";
    	}
    }

    function getActiveData() {
    	var index = grid.getActiveCell().row;
    	return data[index];
    }

    function countTagOccurences(tag, exclusion) {
    	var occurences = 0;
    	for(var index=0; index < data.length; index++) {
    		if (exclusion == undefined || exclusion.row != index) {
	    		if (data[index].tag == tag) {
	    			occurences++;
	    		}
    		}
    	}
    	return occurences;
    }
    
    function findSubfields(bib) {
    	var regex = /\|./g;
    	var re = new RegExp(regex);
    	var match;
    	var subfields = {};
    	while (match = re.exec(bib)) {
    		if (subfields[match[0]]) {
    			subfields[match[0]] += 1;
    		} else {
    			subfields[match[0]] = 1;
    		}
    	}
    	return subfields;
    }
    
    function clone(obj) {
        // Handle the 3 simple types, and null or undefined
        if (null == obj || "object" != typeof obj) return obj;

        // Handle Date
        if (obj instanceof Date) {
            var copy = new Date();
            copy.setTime(obj.getTime());
            return copy;
        }

        // Handle Array
        if (obj instanceof Array) {
            var copy = [];
            for (var i = 0, len = obj.length; i < len; i++) {
                copy[i] = clone(obj[i]);
            }
            return copy;
        }

        // Handle Object
        if (obj instanceof Object) {
            var copy = {};
            for (var attr in obj) {
                if (obj.hasOwnProperty(attr)) copy[attr] = clone(obj[attr]);
            }
            return copy;
        }

        throw new Error("Unable to copy obj! Its type isn't supported.");
    }
    
    function goBack()
    {
    	history.back();
    }
    
    //call as $.marcEditor
    $.marcEditor = function (method) {
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

    //extend from jquery - called as $('').marcEditor('methodname', data).
    $.fn.marcEditor = function (method) {
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