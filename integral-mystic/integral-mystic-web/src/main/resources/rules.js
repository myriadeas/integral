var mandatoryFields = [ '245', '260' ];

var mandatorySubfields = {
	"040" : [ "a" ],
	"245" : [ "a" ],
	"260" : [ "c" ],
	"300" : [ "a" ],
	"852" : [ "a", "h" ]
}

var linkage = {
	"880" : [ "6" ]
}

function _tagValidator(fields, tag, maxOccur) {
	maxOccur = maxOccur | 0;
	if (tag.length == 0) {
		return {
			valid : true
		};
	} else if (tag.length != 3) {
		return {
			valid : false,
			msg : "Tag length must be 3"
		};
	} else if (marc21[tag] == null) {
		return {
			valid : false,
			msg : "'" + tag + "' is not a valid tag"
		};
	} else {
		var tagCount = countTagOccurences(fields, tag);
		if (!marc21[tag].repeatable && tagCount > maxOccur) {
			return {
				valid : false,
				msg : "'" + tag + "' cannot be repeated"
			};
		} else {
			return {
				valid : true
			};
		}
	}
}

function _indicator1Validator(tag, value) {
	var indi1 = (value !== undefined ? value.replace(/ /g, '#') : value);
	if (value != null && value.length > 1) {
		return {
			valid : false,
			msg : "Indicator 1 length must be 1"
		};
	} else if (marc21[tag] && marc21[tag].ind1 === undefined && indi1 != null) {
		return {
			valid : false,
			msg : "Should not have indicator 1"
		};
	} else if (indi1 && marc21[tag] && marc21[tag].ind1[indi1] == null) {
		return {
			valid : false,
			msg : "'" + indi1 + "' is not a valid indicator 1"
		};
	} else {
		return {
			valid : true
		};
	}
}

function _indicator2Validator(tag, value) {
	var indi2 = (value !== undefined ? value.replace(/ /g, '#') : value);
	if (value != null && value.length > 1) {
		return {
			valid : false,
			msg : "Indicator 2 length must be 1"
		};
	} else if (marc21[tag] && marc21[tag].ind2 == undefined && indi2 != null) {
		return {
			valid : false,
			msg : "Should not have indicator 2"
		};
	} else if (indi2 && marc21[tag] && marc21[tag].ind2[indi2] == null) {
		return {
			valid : false,
			msg : "'" + indi2 + "' is not a valid indicator 2"
		};
	} else {
		return {
			valid : true
		};
	}
}

function _subfieldValidator(tag, subfields) {
	var count = countSubfields(subfields);
	var valid = true;
	var msg = '';
	for ( var subfield in subfields) {
		var existsKey;
		for ( var key in subfields[subfield]) {
			if (existsKey == key){
				break;
			}
			if (marc21[tag] && marc21[tag].subf && marc21[tag].subf[key]) {
				var isRepeated = count[key] > 1;
				if (isRepeated) {
					var isRepeatable = marc21[tag].subf[key].repeatable;
					if (msg.length > 0) {
						msg += '\n';
					}
					msg += "'" + key + "' cannot be repeated";
					if (valid && !isRepeatable) {
						valid = false;
					}
				}
			} else {
				if (msg.length > 0) {
					msg += '\n';
				}
				msg += "'" + key + "' is not a valid subfield";
				if (valid) {
					valid = false;
				}
			}
			existsKey = key;
		}

	}
	return {
		valid : valid,
		msg : msg
	};
}

function _mandatoryFieldValidator() {
	if (mandatoryFields.length > 0) {
		return {
			valid : false,
			msg : "Mandatory tag(s): '" + mandatoryFields + "'"
		};
	} else {
		return {
			valid : true
		};
	}
}

function mergeMessage(src, msg, prefix) {
	var lines = msg.split('\n');
	for ( var index = 0; index < lines.length; index++) {
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

function joinSubfields(subfields) {
	var data = "";
	for ( var index = 0; index < subfields.length; index++) {
		var subfield = subfields[index];
		for ( var code in subfield) {
			data += "|" + code + subfield[code];
		}
	}
	return data;
}

function countTagOccurences(fields, tag) {
	var occurences = 0;
	for ( var i = 0; i < fields.length; i++) {
		var field = fields[i];
		for ( var fieldTag in field) {
			if (fieldTag == tag) {
				occurences++;
			}
		}
	}
	return occurences;
}

function countSubfields(subfields) {
	var count = {};
	if (subfields) {
		for ( var index = 0; index < subfields.length; index++) {
			var subfield = subfields[index];
			for ( var code in subfield) {
				if (count[code]) {
					++count[code];
				} else {
					count[code] = 1;
				}
			}
		}
	}
	return count;
}

function checkMandatoryField(tag) {
	if (mandatoryFields) {
		for ( var i = mandatoryFields.length - 1; i >= 0; i--) {
			if (mandatoryFields[i] === tag) {
				mandatoryFields.splice(i, 1);
			}
		}
	}
}

function checkMandatorySubfield(tag, field, mandatorySubfields) {
	var valid = true;
	var msg = "";
	if (mandatorySubfields) {
		java.lang.System.out.println("'" + tag + "' has "
				+ mandatorySubfields.length + " mandatory subfield(s)");
		var subfields = field.subfields;
		var count = countSubfields(subfields);
		for ( var index = 0; index < mandatorySubfields.length; index++) {
			var code = mandatorySubfields[index];
			if (!count[code] || count[code] === 0) {
				java.lang.System.out.println(". Subfield '" + code
						+ "' is mandatory");
				valid = false;
				if (msg.length > 0) {
					msg += '\n';
				}
				msg += "Subfield '" + code + "' is mandatory"
				
			} else {
				java.lang.System.out.println(". Subfield '" + code
						+ "' occurs " + count[code] + " time(s)");
			}
		}	
	}
	return {
		valid : valid,
		msg : msg
	};
}

function getSubfield(subfields, code){
	for(var subfield in subfields) {
		for (var subfieldCode in subfields[subfield]) {
			if (subfieldCode == code){
				return subfields[subfield][subfieldCode];
			}
		}
	}
}

function verify(json) {
	java.lang.System.out.println(json);

	var record = JSON.parse(json);
	var leader = record.leader;
	var fields = record.fields;

	var typeOfRecord = leader.substring(6, 7);
	var bibliographicLevel = leader.substring(7, 8);
	var valid = true;
	var msg = '';
	var errMsg = '';
	for ( var index = 0; index < fields.length; index++) {
		var field = fields[index];
		for ( var tag in field) {
			var checkTag = false;
			if (linkage[tag]) {
				var subfieldData = getSubfield(field[tag].subfields, linkage[tag]);
				checkTag =  subfieldData.substring(0,3);
				marc21[checkTag].repeatable = marc21[tag].repeatable;
				marc21[tag] = marc21[checkTag];
			}	
			if (marc21[tag]) {
				var varField = field[tag];
				var err = _tagValidator(fields, tag, 1);
				if (!err.valid) {
					msg = mergeMessage(msg, err.msg);
					errMsg = mergeMessage(errMsg, err.msg);
					valid = false;
				}
				err = _indicator1Validator(tag, varField.ind1);
				if (!err.valid) {
					msg = mergeMessage(msg, err.msg, "'" + tag + "'");
					errMsg = mergeMessage(errMsg, err.msg, "'" + tag + "'");
					valid = false;
				}
				err = _indicator2Validator(tag, varField.ind2);
				if (!err.valid) {
					msg = mergeMessage(msg, err.msg, "'" + tag + "'");
					errMsg = mergeMessage(errMsg, err.msg, "'" + tag + "'");
					valid = false;
				}
				err = _subfieldValidator(tag, field[tag].subfields);
				if (!err.valid) {
					msg = mergeMessage(msg, err.msg, "'" + tag + "'");
					errMsg = mergeMessage(errMsg, err.msg, "'" + tag + "'");
					valid = false;
				}
				
				checkMandatoryField(tag);
				if (checkTag) {
					err = checkMandatorySubfield(checkTag, field[tag], mandatorySubfields[checkTag]);
					if (!err.valid) {
						java.lang.System.out.println("err.msg: " + err.msg );
						msg = mergeMessage(msg, err.msg, "'" + tag + "'");
						errMsg = mergeMessage(errMsg, err.msg, "'" + tag + "'");
						valid = false;
					}
				} else {
					err = checkMandatorySubfield(tag, field[tag], mandatorySubfields[tag]);
					if (!err.valid) {
						msg = mergeMessage(msg, err.msg, "'" + tag + "'");
						errMsg = mergeMessage(errMsg, err.msg, "'" + tag + "'");
						valid = false;
					}
				}
			} else {
				java.lang.System.out.println("'" + tag + "': Invalid tag");
				msg = mergeMessage(msg, "'" + tag + "': Invalid tag");
				errMsg = mergeMessage(errMsg, "'" + tag + "': Invalid tag");
				valid = false;
			}

			if (typeof field[tag] === 'string') {
				var data = field[tag];
				java.lang.System.out.println(tag + " " + data);
				msg = mergeMessage(msg, tag + " " + data);
			} else {
				var varField = field[tag];
				java.lang.System.out.println(tag + " " + varField.ind1
						+ varField.ind2 + " "
						+ joinSubfields(varField.subfields));
				msg = mergeMessage(msg, tag + " " + varField.ind1
						+ varField.ind2 + " "
						+ joinSubfields(varField.subfields));
			}
		}
		;
	}

	var err = _mandatoryFieldValidator();
	if (!err.valid) {
		msg = mergeMessage(msg, err.msg);
		errMsg = mergeMessage(errMsg, err.msg);
		valid = false;
	}

	if (!valid) {
		java.lang.System.out.println("errors");
		java.lang.System.out.println("******");
		java.lang.System.out.println(msg);
	}
	return {
		valid : valid,
		msg : msg,
		errMsg : errMsg
	};
}
