(function($) {
	var container;
	var grid;
	var data = [];
	var columns = [ {
		id : "edit",
		name : "",
		field : "edit",
		width : 20,
		unselectable : true,
		resizable : false,
		cssClass : "cell-edit"
	}, {
		id : "delete",
		name : "",
		field : "delete",
		width : 20,
		unselectable : true,
		resizable : false,
		cssClass : "cell-delete"
	}, {
		id : "id",
		name : "Id",
		field : "id",
		width : 60
	}, {
		id : "title",
		name : "Title",
		field : "title",
		width : 500
	} ];
	var options = {
		enableAddRow : false,
		enableCellNavigation : true,
		enableColumnReorder : false,
		rowHeight : 30,
		enableWrap : true
	};

	var methods = {
		init : function(params) {
			debug.log("No init required. This is a utility method");
		},
		paint : function() {
			var div = $(document.createElement('div')).attr({
				'id' : 'list'
			}).css({
				'height' : '526px'
			});
			container.append(div);

			var panel = $(".options-panel");
			
			container.fileUploader('paint', {mode: 'add'});

			var editImg = $(document.createElement('img')).attr({
				'src' : 'bo/images/edit.gif'
			});
			var editMsg = $(document.createElement('h3')).append('Click on ',
					editImg, ' to edit record');

			var deleteImg = $(document.createElement('img')).attr({
				'src' : 'bo/images/delete.gif'
			});
			var deleteMsg = $(document.createElement('h3')).append('Click on ',
					deleteImg, ' to delete record');

			var templateSelect = $(document.createElement('select'));
			mapper.execute('listTemplate', {
				'library' : library,
				'userid' : userid,
				'cipher' : cipher
			}).done(function(json) {
				templateSelect.fillInput('options', json.templates);
			});
			var templateMsg = $(document.createElement('h3')).append('Use ',
					templateSelect, ' template when creating new record');

			var addButton = $(document.createElement('button')).text('Add')
					.button();
			addButton.on('click', function(e) {
				container.empty();
				panel.empty();
				var template = templateSelect.val();
				mapper.execute('loadTemplate', {
					'library' : library,
					'userid' : userid,
					'template' : template,
					'cipher' : cipher
				}).done(function(marc) {
					var record = new json2marc(marc);
					debug.debug('get template', record);
					container.marcControl('add', {
						record : record
					});
				})
			});
			var addMsg = $(document.createElement('h3')).append('Click ',
					addButton, ' to create a new record');

			/* --
			var verifyButton = $(document.createElement('button')).text(
					'Verify').button();
			verifyButton.on('click', function(e) {
				alert('verify clicked');
			});
			var verifyMsg = $(document.createElement('h3')).append('Click ',
					verifyButton, ' to verify this batch of record(s)');
			var submitButton = $(document.createElement('button')).text(
					'Submit').button();
			submitButton.on('click', function(e) {
				alert('submit clicked');
			});
			var submitMsg = $(document.createElement('h3')).append('Click ',
					submitButton, ' to submit this batch of record(s)');
			*/

			panel.empty();
			panel.append(templateMsg);
			panel.append(addMsg);
			/*
			panel.append(verifyMsg);
			panel.append(submitMsg);
			*/
			panel.append(editMsg);
			panel.append(deleteMsg);

			grid = new Slick.Grid("#list", data, columns, options);

			grid.onClick.subscribe(function(e) {
				var cell = grid.getCellFromEvent(e);
				var gridData = grid.getData();
				var gridColumns = grid.getColumns();
				switch (gridColumns[cell.cell].id) {
				case 'delete':
					var marcid = gridData[cell.row].id;
					if (confirm("Confirm delete record " + marcid + "?")) {
						mapper.execute('deleteRecord', {
							'library' : library,
							'userid' : userid,
							'marcid' : marcid,
							'cipher' : cipher
						}).done(function(json) {
							debug.debug('deleted', json);
							gridData.splice(cell.row, 1)
							grid.invalidate();
						});
					}
					break;
				case 'edit':
					container.empty();
					panel.empty();
					var marcid = gridData[cell.row].id;
					mapper.execute('findOne', {
						'library' : library,
						'userid' : userid,
						'marcid' : marcid,
						'cipher' : cipher
					}).done(function(marc) {
						var record = new json2marc(marc);
						container.marcControl('edit', {
							marcid : marcid,
							record : record
						});
					});
					break;
				}
			});
			
			mapper.execute('findAll', {
				'library' : library,
				'userid' : userid,
				'cipher' : cipher
			}).done(function(records) {
				var i = 0; 
				for (var key in records) {
				   var obj = records[key];
				   for (var prop in obj) {
				      if(obj.hasOwnProperty(prop)){
				    	  data[i] = {
							'id' : prop,
							'title' : obj[prop],
							'edit' : '',
							'delete' : ''
						};
				        
				      }
				      i++;
				   }
				}
				
				grid.invalidate();
			});
		}
	};

	// call as $.marcList
	$.marcList = function(method) {
		// Method calling logic
		container = this;
		if (methods[method]) {
			return methods[method].apply(this, Array.prototype.slice.call(
					arguments, 1));
		} else if (typeof method == 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method ' + method + ' does not exists in jQuery.marcList');
		}
	}

	// extend from jquery - called as $('').marcList('methodname', data).
	$.fn.marcList = function(method) {
		// Method calling logic
		container = this;
		if (methods[method]) {
			return methods[method].apply(this, Array.prototype.slice.call(
					arguments, 1));
		} else if (typeof method == 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method ' + method + ' does not exists in jQuery.marcList');
		}
	}
})(jQuery);