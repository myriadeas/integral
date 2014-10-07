function findFirstField(record, tag) {
	var found = undefined;
	for(var index in record.fields) {
		var field = record.fields[index];
		if (tag in field) {
			found = index;
			break;
		}
	}
	return found;
}

function findFirstSubfield(field, subfieldCode) {
	var found = undefined;
	var subfields = field.subfields;
	for(var index in subfields) {
		var subfield = subfields[index];
		if (subfieldCode in subfield) {
			found = index;
			break;
		}
	}
	return found;
}

function removeFirstSubfield(record, tag, subfieldCode) {
	var index = findFirstField(record, tag);
	if (index && index >= 0) {
		var field = record.fields[index][tag];
		var subfieldIndex = findFirstSubfield(field, subfieldCode);
		if (subfieldIndex && subfieldIndex >= 0) {
			field.subfields.splice(subfieldIndex, 1);
		}
	}
}

function removeFirstField(record, tag) {
	var index = findFirstField(record, tag);
	if (index && index >= 0) {
		record.fields.splice(index, 1);
	}
}

function removeAllFields(record, tag) {
	do {
		var index = findFirstField(record, tag);
		if (index && index >= 0) {
			record.fields.splice(index, 1);
		}
	} while(index);
}

function convert(json) {
	
	var record=JSON.parse(json);
	var leader = record.leader;
	var fields = record.fields;

	removeFirstField(record, '005');
	removeAllFields(record, '880');
	removeFirstSubfield(record, '020', 'c');
	removeAllFields(record, '906');
	removeAllFields(record, '925');
	removeAllFields(record, '955');

	return JSON.stringify(record);
}
