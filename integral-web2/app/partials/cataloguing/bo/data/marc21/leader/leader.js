var leaderDefn = {
	"0" : {
		"start": 0,
		"stop": 4,
		"len": 5,
		"desc": "Record length",
		"type": "auto",
		"defaultValue": "00000"
	},
	"5" : {
		"start": 5,
		"stop": 5,
		"len": 1,
		"desc": "Record status",
		"type": "select",
		"defaultValue": "n",
		"enum":
		{
			"a": "Increase in encoding level",
			"c": "Correct or revised",
			"d": "Deleted",
			"n": "New",
			"p": "Increase in encoding level from prepublication"
		}
	},
	"6" : {
		"start": 6,
		"stop": 6,
		"len": 1,
		"desc": "Type of record",
		"type": "select",
		"defaultValue": "a",
		"enum":
		{
			"a": "Language material",
			"c": "Notated music",
			"d": "Manuscript notated music",
			"e": "Cartographic material",
			"f": "Manuscript cartographic material",
			"g": "Projected medium",
			"i": "Nonmusical sound recording",
			"j": "Musical sound recording",
			"k": "Two-dimensional nonprojectable graphic",
			"m": "Computer file",
			"o": "Kit",
			"p": "Mixed materials",
			"r": "Three-dimensional artifact or naturally occurring object",
			"t": "Manuscript language material"
		}
	},
	"7" : {
		"start": 7,
		"stop": 7,
		"len": 1,
		"desc": "Bibliographic level",
		"type": "select",
		"defaultValue": "m",
		"enum":
		{
			"a": "Monographic component part",
			"b": "Serial component part",
			"c": "Collection",
			"d": "Subunit",
			"i": "Integrating resource",
			"m": "Monograph/Item",
			"s": "Serial"
		}
	},
	"8" : {
		"start": 8,
		"stop": 8,
		"len": 1,
		"desc": "Type of control",
		"type": "select",
		"defaultValue": " ",
		"enum":
		{
			"#": "No specified type",
			"a": "Archival"
		}
	},
	"9" : {
		"start": 9,
		"stop": 9,
		"len": 1,
		"desc": "Character coding scheme",
		"type": "select",
		"defaultValue": "a",
		"enum":
		{
			"#": "MARC-8",
			"a": "UCS/Unicode"
		}
	},
	"10" : {
		"start": 10,
		"stop": 10,
		"len": 1,
		"desc": "Indicator count",
		"type": "auto",
		"defaultValue": "2"
	},
	"11" : {
		"start": 11,
		"stop": 11,
		"len": 1,
		"desc": "Subfield code count",
		"type": "auto",
		"defaultValue": "2"
	},
	"12" : {
		"start": 12,
		"stop": 16,
		"len": 5,
		"desc": "Base address of data",
		"type": "auto",
		"defaultValue": "00000"
	},
	"17" : {
		"start": 17,
		"stop": 17,
		"len": 1,
		"desc": "Encoding level",
		"type": "select",
		"defaultValue": "#",
		"enum":
		{
			"#": "Full level",
			"1": "Full level, material not examined",
			"2": "Less-than-full level, material not examined",
			"3": "Abbreviated level",
			"4": "Core level",
			"5": "Partial (preliminary) level",
			"7": "Minimal level",
			"8": "Prepublication level",
			"u": "Unknown",
			"z": "Not applicable"
		}
	},
	"18" : {
		"start": 18,
		"stop": 18,
		"len": 1,
		"desc": "Descriptive cataloging form",
		"type": "select",
		"defaultValue": "a",
		"enum":
		{
			"#": "Non-ISBD",
			"a": "AACR 2",
			"c": "ISBD punctuation omitted",
			"i": "ISBD punctuation included",
			"u": "Unknown"
		}
	},
	"19" : {
		"start": 19,
		"stop": 19,
		"len": 1,
		"desc": "Multipart resource record level",
		"type": "select",
		"defaultValue": "#",
		"enum":
		{
			"#": "Not specified or not applicable",
			"a": "Set",
			"b": "Part with independent title",
			"c": "Part with dependent title"
		}
	},
	"20" : {
		"start": 20,
		"stop": 20,
		"len": 1,
		"desc": "Length of the length-of-field-portion",
		"type": "auto",
		"defaultValue": "4"
	},
	"21" : {
		"start": 21,
		"stop": 21,
		"len": 1,
		"desc": "Length of the starting-character-position-portion",
		"type": "auto",
		"defaultValue": "5"
	},
	"22" : {
		"start": 22,
		"stop": 22,
		"len": 1,
		"desc": "Length of the implementation-defined portion",
		"type": "auto",
		"defaultValue": "0"
	},
	"23" : {
		"start": 23,
		"stop": 23,
		"len": 1,
		"desc": "Undefined",
		"type": "auto",
		"defaultValue": "0"
	}
}