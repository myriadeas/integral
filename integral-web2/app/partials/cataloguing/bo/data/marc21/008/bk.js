defn_008['bk'] = {
    "0": {
        "start": 0,
        "stop": 5,
        "len": 6,
        "desc": "Date entered on file",
        "type": "auto-date",
        "defaultValue": "121256"
    },
    "6": {
        "start": 6,
        "stop": 6,
        "len": 1,
        "desc": "Type of date/Publication status",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "b",
                "desc": "No dates given; B.C. date involved"
            },
            {
                "code": "c",
                "desc": "Continuing resource currently published"
            },
            {
                "code": "d",
                "desc": "Continuing resource ceased publication"
            },
            {
                "code": "e",
                "desc": "Detailed date"
            },
            {
                "code": "i",
                "desc": "Inclusive dates of collection"
            },
            {
                "code": "k",
                "desc": "Range of years of bulk of collection"
            },
            {
                "code": "m",
                "desc": "Multiple dates"
            },
            {
                "code": "n",
                "desc": "Dates unknown"
            },
            {
                "code": "p",
                "desc": "Date of distribution/release/issue and production/recording session when different"
            },
            {
                "code": "q",
                "desc": "Questionable date"
            },
            {
                "code": "r",
                "desc": "Reprint/reissue date and original date"
            },
            {
                "code": "s",
                "desc": "Single known date/probable date"
            },
            {
                "code": "t",
                "desc": "Publication date and copyright date"
            },
            {
                "code": "u",
                "desc": "Continuing resource status unknown"
            },
            {
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    },
    "7": {
        "start": 7,
        "stop": 10,
        "len": 4,
        "desc": "Date 1",
        "type": "editable-select",
        "defaultValue": "",
        "list": [
            {
                "code": "1",
                "desc": "9 - Date digit"
            },
            {
                "code": "#",
                "desc": "Date element is not applicable"
            },
            {
                "code": "u",
                "desc": "Date element is totally or partially unknown"
            },
            {
                "code": "||||",
                "desc": "No attempt to code"
            }
        ] 
    },
    "11": {
        "start": 11,
        "stop": 14,
        "len": 4,
        "desc": "Date 2",
        "type": "editable-select",
        "defaultValue": "",
        "list": [
            {
                "code": "1",
                "desc": "9 - Date digit"
            },
            {
                "code": "#",
                "desc": "Date element is not applicable"
            },
            {
                "code": "u",
                "desc": "Date element is totally or partially unknown"
            },
            {
                "code": "||||",
                "desc": "No attempt to code"
            }
        ] 
    },
    "15": {
        "start": 15,
        "stop": 17,
        "len": 3,
        "desc": "Place of publication, production, or execution",
        "type": "select",
        "url": "data/countries.xml",
        "parser": countriesParser,
        "defaultValue": " "
    },
    "18": {
        "start": 18,
        "stop": 21,
        "len": 1,
        "desc": "Illustrations(006/01-04)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "No illustrations"
            },
            {
                "code": "a",
                "desc": "Illustrations"
            },
            {
                "code": "b",
                "desc": "Maps"
            },
            {
                "code": "c",
                "desc": "Portraits"
            },
            {
                "code": "d",
                "desc": "Charts"
            },
            {
                "code": "e",
                "desc": "Plans"
            },
            {
                "code": "f",
                "desc": "Plates"
            },
            {
                "code": "g",
                "desc": "Music"
            },
            {
                "code": "h",
                "desc": "Facsimiles"
            },
            {
                "code": "i",
                "desc": "Coats of arms"
            },
            {
                "code": "j",
                "desc": "Genealogical tables"
            },
            {
                "code": "k",
                "desc": "Forms"
            },
            {
                "code": "l",
                "desc": "Samples"
            },
            {
                "code": "m",
                "desc": "Phonodisc, phonowire, etc."
            },
            {
                "code": "o",
                "desc": "Photographs"
            },
            {
                "code": "p",
                "desc": "Illuminations"
            },
            {
                "code": "|",
                "desc": "No attempt tocode"
            }
        ] 
    },
    "22": {
        "start": 22,
        "stop": 22,
        "len": 1,
        "desc": "Target audience(006/05)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "Unknown or not specified"
            },
            {
                "code": "a",
                "desc": "Preschool"
            },
            {
                "code": "b",
                "desc": "Primary"
            },
            {
                "code": "c",
                "desc": "Pre-adolescent"
            },
            {
                "code": "d",
                "desc": "Adolescent"
            },
            {
                "code": "e",
                "desc": "Adult"
            },
            {
                "code": "f",
                "desc": "Specialized"
            },
            {
                "code": "g",
                "desc": "General"
            },
            {
                "code": "j",
                "desc": "Juvenile"
            },
            {
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    },
    "23": {
        "start": 23,
        "stop": 23,
        "len": 1,
        "desc": "Form of item(006/06)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "None of the following"
            },
            {
                "code": "a",
                "desc": "Microfilm"
            },
            {
                "code": "b",
                "desc": "Microfiche"
            },
            {
                "code": "c",
                "desc": "Microopaque"
            },
            {
                "code": "d",
                "desc": "Large print"
            },
            {
                "code": "f",
                "desc": "Braille"
            },
            {
                "code": "o",
                "desc": "Online"
            },
            {
                "code": "q",
                "desc": "Direct electronic"
            },
            {
                "code": "r",
                "desc": "Regular print reproduction"
            },
            {
                "code": "s",
                "desc": "Electronic"
            },
            {
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    },
    "24": {
        "start": 24,
        "stop": 27,
        "len": 1,
        "desc": "Nature of contents(006/07-10)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "No specified nature of contents"
            },
            {
                "code": "a",
                "desc": "Abstracts/summaries"
            },
            {
                "code": "b",
                "desc": "Bibliographies"
            },
            {
                "code": "c",
                "desc": "Catalogs"
            },
            {
                "code": "d",
                "desc": "Dictionaries"
            },
            {
                "code": "e",
                "desc": "Encyclopedias"
            },
            {
                "code": "f",
                "desc": "Handbooks"
            },
            {
                "code": "g",
                "desc": "Legal articles"
            },
            {
                "code": "i",
                "desc": "Indexes"
            },
            {
                "code": "j",
                "desc": "Patent document"
            },
            {
                "code": "k",
                "desc": "Discographies"
            },
            {
                "code": "l",
                "desc": "Legislation"
            },
            {
                "code": "m",
                "desc": "Theses"
            },
            {
                "code": "n",
                "desc": "Surveys of literature in a subject area"
            },
            {
                "code": "o",
                "desc": "Reviews"
            },
            {
                "code": "p",
                "desc": "Programmed texts"
            },
            {
                "code": "q",
                "desc": "Filmographies"
            },
            {
                "code": "r",
                "desc": "Directories"
            },
            {
                "code": "s",
                "desc": "Statistics"
            },
            {
                "code": "t",
                "desc": "Technical reports"
            },
            {
                "code": "u",
                "desc": "Standards/specifications"
            },
            {
                "code": "v",
                "desc": "Legal cases and case notes"
            },
            {
                "code": "w",
                "desc": "Law reports and digests"
            },
            {
                "code": "y",
                "desc": "Yearbooks"
            },
            {
                "code": "z",
                "desc": "Treaties"
            },
            {
                "code": "2",
                "desc": "Offprints"
            },
            {
                "code": "5",
                "desc": "Calendars"
            },
            {
                "code": "6",
                "desc": "Comics/graphic novels"
            },
            {
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    },
    "28": {
        "start": 28,
        "stop": 28,
        "len": 1,
        "desc": "Government publication (006/11)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "Not a government publication"
            },
            {
                "code": "a",
                "desc": "Autonomous orsemi-autonomous component"
            },
            {
                "code": "c",
                "desc": "Multilocal"
            },
            {
                "code": "f",
                "desc": "Federal/national"
            },
            {
                "code": "i",
                "desc": "International intergovernmental"
            },
            {
                "code": "l",
                "desc": "Local"
            },
            {
                "code": "m",
                "desc": "Multistate"
            },
            {
                "code": "o",
                "desc": "Government publication-level undetermined"
            },
            {
                "code": "s",
                "desc": "State, provincial,territorial, dependent, etc."
            },
            {
                "code": "u",
                "desc": "Unknown if item is government publication"
            },
            {
                "code": "z",
                "desc": "Other"
            },
            {
                "code": "|",
                "desc": "No attempt tocode"
            }
        ] 
    },
    "29": {
        "start": 29,
        "stop": 29,
        "len": 1,
        "desc": "Conference publication (006/12)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "0",
                "desc": "Not a conference publication"
            },
            {
                "code": "1",
                "desc": "Conference publication"
            },
            {
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    },
    "30": {
        "start": 30,
        "stop": 30,
        "len": 1,
        "desc": "Festschrift(006/13)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "0",
                "desc": "Not a festschrift"
            },
            {
                "code": "1",
                "desc": "Festschrift"
            },
            {
                "code": "|",
                "desc": "No attempt tocode"
            }
        ] 
    },
    "31": {
        "start": 31,
        "stop": 31,
        "len": 1,
        "desc": "Index(006/14)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "0",
                "desc": "No index"
            },
            {
                "code": "1",
                "desc": "Index present"
            },
            {
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    },
    "32": {
        "start": 32,
        "stop": 32,
        "len": 1,
        "desc": "Undefined(006/15)",
        "type": "auto",
        "defaultValue": ""
    },
    "33": {
        "start": 33,
        "stop": 33,
        "len": 1,
        "desc": "Literary form(006/16)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "0",
                "desc": "Not fiction (not further specified)"
            },
            {
                "code": "1",
                "desc": "Fiction (not further specified)"
            },
            {
                "code": "d",
                "desc": "Dramas"
            },
            {
                "code": "e",
                "desc": "Essays"
            },
            {
                "code": "f",
                "desc": "Novels"
            },
            {
                "code": "h",
                "desc": "Humor, satires,etc."
            },
            {
                "code": "i",
                "desc": "Letters"
            },
            {
                "code": "j",
                "desc": "Short stories"
            },
            {
                "code": "m",
                "desc": "Mixed forms"
            },
            {
                "code": "p",
                "desc": "Poetry"
            },
            {
                "code": "s",
                "desc": "Speeches"
            },
            {
                "code": "u",
                "desc": "Unknown"
            },
            {
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    },
    "34": {
        "start": 34,
        "stop": 34,
        "len": 1,
        "desc": "Biography(006/17)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "No biographical material"
            },
            {
                "code": "a",
                "desc": "Autobiography"
            },
            {
                "code": "b",
                "desc": "Individual biography"
            },
            {
                "code": "c",
                "desc": "Collective biography"
            },
            {
                "code": "d",
                "desc": "Contains biographical information"
            },
            {
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    } ,
    "35": {
        "start": 35,
        "stop": 37,
        "len": 3,
        "desc": "Language",
        "type": "select",
        "url": "data/languages.xml",
        "parser": languagesParser,
        "defaultValue": " "
    },
    "38": {
        "start": 38,
        "stop": 38,
        "len": 1,
        "desc": "Modified record",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "Not modified"
            },
            {
                "code": "d",
                "desc": "Dashed-on information omitted"
            },
            {
                "code": "o",
                "desc": "Completely romanized/printed cards romanized"
            },
            {
                "code": "r",
                "desc": "Completely romanized/printed cards in script"
            },
            {
                "code": "s",
                "desc": "Shortened"
            },
            {
                "code": "x",
                "desc": "Missing characters"
            },
            {
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    },
    "39": {
        "start": 39,
        "stop": 39,
        "len": 1,
        "desc": "Cataloging source",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "National bibliographic agency"
            },
            {
                "code": "c",
                "desc": "Cooperative cataloging program"
            },
            {
                "code": "d",
                "desc": "Other"
            },
            {
                "code": "u",
                "desc": "Unknown"
            },
            {
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    } 
};