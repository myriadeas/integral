defn_008['vm'] = {
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
        "stop": 20,
        "len": 3,
        "desc": "Running time for motion pictures and videorecordings (006/01-03)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "0",
                "desc": "Running time exceeds three characters"
            },
            {
                "code": "1",
                "desc": "999 - Running time"
            },
            {
                "code": "nnn",
                "desc": "Not applicable"
            },
            {
                "code": "",
                "desc": "-- - Unknown"
            },
            {
                "code": "|||",
                "desc": "No attempt to code"
            }
        ] 
    },
    "21": {
        "start": 21,
        "stop": 21,
        "len": 1,
        "desc": "Undefined (006/04)",
        "type": "auto",
        "defaultValue": ""
    },
    "22": {
        "start": 22,
        "stop": 22,
        "len": 1,
        "desc": "Target audience (006/05)",
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
        "stop": 27,
        "len": 5,
        "desc": "Undefined (006/06-10)",
        "type": "auto",
        "defaultValue": ""
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
                "desc": "State, provincial, territorial, dependent, etc."
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
                "desc": "No attempt to code"
            }
        ] 
    },
    "29": {
        "start": 29,
        "stop": 29,
        "len": 1,
        "desc": "Form of item (006/12)",
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
    "30": {
        "start": 30,
        "stop": 32,
        "len": 3,
        "desc": "Undefined (006/13-15)",
        "type": "auto",
        "defaultValue": ""
    },
    "33": {
        "start": 33,
        "stop": 33,
        "len": 1,
        "desc": "Type of visual material (006/16)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "a",
                "desc": "Art original"
            },
            {
                "code": "b",
                "desc": "Kit"
            },
            {
                "code": "c",
                "desc": "Art reproduction"
            },
            {
                "code": "d",
                "desc": "Diorama"
            },
            {
                "code": "f",
                "desc": "Filmstrip"
            },
            {
                "code": "g",
                "desc": "Game"
            },
            {
                "code": "i",
                "desc": "Picture"
            },
            {
                "code": "k",
                "desc": "Graphic"
            },
            {
                "code": "l",
                "desc": "Technical drawing"
            },
            {
                "code": "m",
                "desc": "Motion picture"
            },
            {
                "code": "n",
                "desc": "Chart"
            },
            {
                "code": "o",
                "desc": "Flash card"
            },
            {
                "code": "p",
                "desc": "Microscope slide"
            },
            {
                "code": "q",
                "desc": "Model"
            },
            {
                "code": "r",
                "desc": "Realia"
            },
            {
                "code": "s",
                "desc": "Slide"
            },
            {
                "code": "t",
                "desc": "Transparency"
            },
            {
                "code": "v",
                "desc": "Videorecording"
            },
            {
                "code": "w",
                "desc": "Toy"
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
        "desc": "Technique (006/17)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "a",
                "desc": "Animation"
            },
            {
                "code": "c",
                "desc": "Animation and live action"
            },
            {
                "code": "l",
                "desc": "Live action"
            },
            {
                "code": "n",
                "desc": "Not applicable"
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