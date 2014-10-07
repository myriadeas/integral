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

function createField(record, tag, ind1, ind2, subfields) {
	var field = {};
	field[tag] = {};
	field[tag].ind1 = ind1;
	field[tag].ind2 = ind2;
	field[tag].subfields = subfields;
	record.fields.push(field);
}

function convert(json) {
	//java.lang.System.out.println(json);
	//java.lang.System.out.println("converting for " + libraryId);
	
	var record=JSON.parse(json);
	var leader = record.leader;
	var fields = record.fields;

	removeFirstField(record, '005');
	removeAllFields(record, '880');
	removeFirstSubfield(record, '020', 'c');
	removeAllFields(record, '906');
	removeAllFields(record, '925');
	removeAllFields(record, '955');
	
	createField(record, '040', '1', '2', [ {'a': 'PNQ'} ] );

	return JSON.stringify(record);
}
