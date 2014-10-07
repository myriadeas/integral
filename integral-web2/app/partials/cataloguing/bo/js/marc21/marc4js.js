var json2marc = function(marc) {
	if (marc) {
		
		console.log("leader:" + marc.leader);
		this.leader = marc.leader;
		this.variableFields = new VariableFields(marc.fields);
	} else {
		this.leader = '00000nam a2200000   4500';
		this.variableFields = new VariableFields([]);
	}
}

json2marc.prototype.toJson = function() {
	var json = {};

	json['leader'] = this.leader;

	var fields = [];
	var variableFields = this.variableFields;
	for(var index in variableFields) {
		var variableField = variableFields[index];
		if (variableField.tag) {
			var field = {};
			if (variableField.ind1) {
				field[variableField.tag] = {};
				field[variableField.tag]['ind1'] = variableField.ind1;
				field[variableField.tag]['ind2'] = variableField.ind2;
				field[variableField.tag]['subfields'] = splitSubfields(variableField.bib);
			} else {
				field[variableField.tag] = variableField.bib;
			}
			fields.push(field);
		}
	}
	json['fields'] = fields;
	
	return json;
};

var VariableFields = function(fields) {
	var variableFields = [];
	for(var index in fields) {
		var variableField = new VariableField(fields[index]);
		variableFields.push(variableField);
	}
	return variableFields;
};

var VariableField = function(varField) {
	for(var key in varField) {
		var field = varField[key];
		this.tag = key;
		if (field.hasOwnProperty('subfields')) {
			this.ind1 = field.ind1;
			this.ind2 = field.ind2;
			this.bib = joinSubfields(field.subfields);
		} else {
			this.bib = field;
		}
	}
};

function joinSubfields(subfields) {
	var s = '';
	for(var index in subfields) {
		var subfield = subfields[index];
		for(var key in subfield) {
			var value = subfield[key];
			s += '|' + key + value;
		}
	}
	return s;
}

function splitSubfields(bib) {
	var subfields = [];
	var parts = bib.split("|");
	for(var index in parts) {
		var part = parts[index];
		if (part.length > 0) {
			var code = part.substring(0, 1);
			var data = part.substring(1);
			var subfield = {};
			subfield[code] = data;
			subfields.push(subfield);
		}
	}
	return subfields;
}
