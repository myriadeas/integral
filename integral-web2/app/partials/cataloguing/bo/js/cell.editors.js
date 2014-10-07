/*
 * Reference: http://stackoverflow.com/questions/11941311/how-to-implement-jquery-autocomplete-in-slickgrid-cell-editor
 * Reference: http://stackoverflow.com/questions/597769/how-do-i-create-an-abstract-base-class-in-javascript
 * Reference: http://stackoverflow.com/questions/3601932/how-do-i-call-an-inherited-javascript-constructor-with-parameters
 * Reference: http://www.lateralcode.com/javascript-inerhitance/
 */

function TagCellEditor(args) {
	AbstractCellEditor.apply(this, arguments);
}
TagCellEditor.prototype = new AbstractCellEditor(); // make TagCellEditor inherit from AbstractCellEditor object
TagCellEditor.prototype.constructor = TagCellEditor; // fix constructor property
TagCellEditor.prototype.sourceProvider = tagSourceProvider;
TagCellEditor.prototype.autocompleteHandler = function(input, source) {
	input.attr('maxlength', '3');
	input.autocomplete({
		source: source
	}).on("autocompleteselect", function(event, ui) {
		$(event.target).val(ui.item.value.substring(0,3));
		return false;
	});
};

function Ind1CellEditor(args)  {
	AbstractCellEditor.apply(this, arguments);
}
Ind1CellEditor.prototype = new AbstractCellEditor();
Ind1CellEditor.prototype.constructor = Ind1CellEditor;
Ind1CellEditor.prototype.sourceProvider = ind1SourceProvider;
Ind1CellEditor.prototype.autocompleteHandler = function(input, source) {
	input.attr('maxlength', '1');
	input.autocomplete({
		source: source,
		minLength: 0,
		delay: 0
	}).on("autocompleteselect", function(event, ui) {
		$(event.target).val(ui.item.value.substring(0,1).replace(/#/g, ' '));
		return false;
	});
};

function Ind2CellEditor(args) {
	Ind1CellEditor.apply(this, arguments);
}
Ind2CellEditor.prototype = new Ind1CellEditor();
Ind2CellEditor.prototype.constructor = Ind2CellEditor;
Ind2CellEditor.prototype.sourceProvider = ind2SourceProvider;

function BibCellEditor(args) {
	AbstractCellEditor.apply(this, arguments);
};
BibCellEditor.prototype = new AbstractCellEditor();
BibCellEditor.prototype.constructor = BibCellEditor;
BibCellEditor.prototype.sourceProvider = subfieldSourceProvider;
BibCellEditor.prototype.autocompleteHandler = function(input, source) {
	input.autocomplete({
		delay: 100,
		source: function(request, response) {
			debug.log('request', request, 'response', response);
			var term = request.term;
			if (term.charAt(term.length - 1) == '|') {
				response(source);
			}
		},
		search: function() {
			debug.log('search', this.value);
			var term = this.value;
			if (term.charAt(term.length - 1) == '|') {
				return true;
			} else {
				$(this).autocomplete('close');
				return false;
			}
		},
		focus: function() {
			return false;
		},
		select: function(event, ui) {
			var subfield = ui.item.value.substring(0,1);
			var value = $(event.target).val();
			$(event.target).val(value + subfield);
			return false;
		}
	});
};

function AbstractCellEditor(args) {
	if (arguments.length == 0) {
		return;
	}
	if (this instanceof TagCellEditor) {
		debug.log('TagCellEditor', args);
	} else if (this instanceof Ind1CellEditor) {
		debug.log('Ind1CellEditor', args);
	} else if (this instanceof Ind2CellEditor) {
		debug.log('Ind2CellEditor', args);
	} else if (this instanceof BibCellEditor) {
		debug.log('BibCellEditor', args);
	}
	var $input;
	var defaultValue;
	var scope = this;

	this.init = function() {
		var grid = args.grid;
		var row = grid.getActiveCell().row;
		var editRow = grid.getData()[row];
		var tag = editRow.tag;
		debug.log('AbstractCellEditor.init()', tag, scope.sourceProvider, scope.autocompleteHandler);

		$input = $("<INPUT type=text class='editor-text' />")
			.appendTo(args.container)
			.bind("keydown.nav", function(e) {
				if (e.keyCode === $.ui.keyCode.LEFT || e.keyCode === $.ui.keyCode.RIGHT) {
					e.stopImmediatePropagation();
				}
				if ((e.keyCode == $.ui.keyCode.DOWN || e.keyCode == $.ui.keyCode.UP ||
						e.keyCode == $.ui.keyCode.ENTER)
						&& $('ul.ui-autocomplete').is(':visible')) {
					e.stopPropagation();
				}
			})
			.focus()
			.select();
		
		var source = scope.sourceProvider(tag);
		
		if (source != undefined) {
			scope.autocompleteHandler($input, source);
		}
	}

	this.destroy = function() {
		$input.remove();
	}

	this.focus = function() {
		$input.focus();
	}

	this.loadValue = function(item) {
		defaultValue = item[args.column.field] || "";
		$input.val(defaultValue);
		$input[0].defaultValue = defaultValue;
		$input.select();
	}

	this.serializeValue = function() {
		return $input.val();
	}

	this.applyValue = function(item,state) {
		item[args.column.field] = state;
	}

	this.isValueChanged = function() {
		return (!($input.val() == "" && defaultValue == null)) && ($input.val() != defaultValue);
	}

	this.validate = function() {
		if (args.column.validator) {
			var validationResults = args.column.validator($input.val());
			if (!validationResults.valid)
				return validationResults;
		}
		
		return {
			valid: true,
			msg: null
		};
	}

	this.init();
};

function tagSourceProvider() {
	var source = [];
	for(var tag in marc21) {
		source.push(tag + " " + marc21[tag].desc);
	}
	return source;
}

function ind1SourceProvider(tag) {
	if (marc21[tag] != undefined && marc21[tag].ind1 != undefined) {
		var source = [];
		var validInd1 = marc21[tag].ind1;
		for(var ind1 in validInd1) {
			source.push(ind1 + " " + validInd1[ind1]);
		}
		return source;
	} else {
		return undefined;
	}
}

function ind2SourceProvider(tag) {
	if (marc21[tag] != undefined && marc21[tag].ind2 != undefined) {
		var source = [];
		var validInd2 = marc21[tag].ind2;
		for(var ind2 in validInd2) {
			source.push(ind2 + " " + validInd2[ind2]);
		}
		return source;
	} else {
		return undefined;
	}
}

function subfieldSourceProvider(tag) {
	if (marc21[tag] != undefined && marc21[tag].subf != undefined) {
		var source = [];
		var subfields = marc21[tag].subf;
		for(var subfield in subfields) {
			source.push(subfield + " " + subfields[subfield].desc);
		}
		return source;
	} else {
		return undefined;
	}
}

