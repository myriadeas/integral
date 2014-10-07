defn_008['cr'] = {
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
        "stop": 18,
        "len": 1,
        "desc": "Frequency(006/01)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "No determinable frequency"
            },
            {
                "code": "a",
                "desc": "Annual"
            },
            {
                "code": "b",
                "desc": "Bimonthly"
            },
            {
                "code": "c",
                "desc": "Semiweekly"
            },
            {
                "code": "d",
                "desc": "Daily"
            },
            {
                "code": "e",
                "desc": "Biweekly"
            },
            {
                "code": "f",
                "desc": "Semiannual"
            },
            {
                "code": "g",
                "desc": "Biennial"
            },
            {
                "code": "h",
                "desc": "Triennial"
            },
            {
                "code": "i",
                "desc": "Three times a week"
            },
            {
                "code": "j",
                "desc": "Three times a month"
            },
            {
                "code": "k",
                "desc": "Continuously updated"
            },
            {
                "code": "m",
                "desc": "Monthly"
            },
            {
                "code": "q",
                "desc": "Quarterly"
            },
            {
                "code": "s",
                "desc": "Semimonthly"
            },
            {
                "code": "t",
                "desc": "Three times ayear"
            },
            {
                "code": "u",
                "desc": "Unknown"
            },
            {
                "code": "w",
                "desc": "Weekly"
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
    "19": {
        "start": 19,
        "stop": 19,
        "len": 1,
        "desc": "Regularity(006/02)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "n",
                "desc": "Normalizedirregular"
            },
            {
                "code": "r",
                "desc": "Regular"
            },
            {
                "code": "u",
                "desc": "Unknown"
            },
            {
                "code": "x",
                "desc": "Completelyirregular"
            },
            {
                "code": "|",
                "desc": "No attempt tocode"
            }
        ] 
    },
    "20": {
        "start": 20,
        "stop": 20,
        "len": 1,
        "desc": "Undefined(006/03)",
        "type": "auto",
        "defaultValue": ""
    },
    "21": {
        "start": 21,
        "stop": 21,
        "len": 1,
        "desc": "Type of continuing resource (006/04)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "None of the following"
            },
            {
                "code": "d",
                "desc": "Updating database"
            },
            {
                "code": "l",
                "desc": "Updating loose-leaf"
            },
            {
                "code": "m",
                "desc": "Monographic series"
            },
            {
                "code": "n",
                "desc": "Newspaper"
            },
            {
                "code": "p",
                "desc": "Periodical"
            },
            {
                "code": "w",
                "desc": "Updating Website"
            },
            {
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    },
    "22": {
        "start": 22,
        "stop": 22,
        "len": 1,
        "desc": "Form of original item(006/05)",
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
                "desc": "Largeprint"
            },
            {
                "code": "e",
                "desc": "Newspaper format"
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
                "code": "s",
                "desc": "Electronic"
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
                "desc": "Largeprint"
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
                "desc": "No attempt tocode"
            }
        ] 
    },
    "24": {
        "start": 24,
        "stop": 24,
        "len": 1,
        "desc": "Nature of entire work(006/07)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "Not specified"
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
                "desc": "Legalarticles"
            },
            {
                "code": "h",
                "desc": "Biography"
            },
            {
                "code": "i",
                "desc": "Indexes"
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
                "code": "5",
                "desc": "Calendars"
            },
            {
                "code": "6",
                "desc": "Comics/graphic novels"
            },
            {
                "code": "|",
                "desc": "No attempt tocode"
            }
        ] 
    },
    "25": {
        "start": 25,
        "stop": 27,
        "len": 1,
        "desc": "Nature of contents(006/08-10)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "Not specified"
            },
            {
                "code": "a",
                "desc": "Abstracts/summaries other"
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
                "code": "h",
                "desc": "Biography"
            },
            {
                "code": "i",
                "desc": "Indexes"
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
                "code": "5",
                "desc": "Calendars"
            },
            {
                "code": "6",
                "desc": "Comics/graphic novels"
            },
            {
                "code": "|||",
                "desc": "No attempt to code"
            }
        ] 
    },
    "28": {
        "start": 28,
        "stop": 28,
        "len": 1,
        "desc": "Governmentpublication (006/11)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "Not a government publication"
            },
            {
                "code": "a",
                "desc": "Autonomous or semi-autonomous component"
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
        "stop": 32,
        "len": 3,
        "desc": "Undefined(006/13-15)",
        "type": "auto",
        "defaultValue": ""
    },
    "33": {
        "start": 33,
        "stop": 33,
        "len": 1,
        "desc": "Original alphabet or script of title (006/16)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "No alphabet or script given/No key title"
            },
            {
                "code": "a",
                "desc": "Basic Roman"
            },
            {
                "code": "b",
                "desc": "Extended Roman"
            },
            {
                "code": "c",
                "desc": "Cyrillic"
            },
            {
                "code": "d",
                "desc": "Japanese"
            },
            {
                "code": "e",
                "desc": "Chinese"
            },
            {
                "code": "f",
                "desc": "Arabic"
            },
            {
                "code": "g",
                "desc": "Greek"
            },
            {
                "code": "h",
                "desc": "Hebrew"
            },
            {
                "code": "i",
                "desc": "Thai"
            },
            {
                "code": "j",
                "desc": "Devanagari"
            },
            {
                "code": "k",
                "desc": "Korean"
            },
            {
                "code": "l",
                "desc": "Tamil"
            },
            {
                "code": "u",
                "desc": "Unknown"
            },
            {
                "code": "z",
                "desc": "Other"
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
        "desc": "Entry convention(006/17)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "0",
                "desc": "Successive entry"
            },
            {
                "code": "1",
                "desc": "Latest entry"
            },
            {
                "code": "2",
                "desc": "Integrated entry"
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
} 