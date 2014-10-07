defn_008['mp'] = {
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
        "desc": "Relief (006/01-04)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "No relief shown"
            },
            {
                "code": "a",
                "desc": "Contours"
            },
            {
                "code": "b",
                "desc": "Shading"
            },
            {
                "code": "c",
                "desc": "Gradient and bathymetric tints"
            },
            {
                "code": "d",
                "desc": "Hachures"
            },
            {
                "code": "e",
                "desc": "Bathymetry/soundings"
            },
            {
                "code": "f",
                "desc": "Form lines"
            },
            {
                "code": "g",
                "desc": "Spot heights"
            },
            {
                "code": "i",
                "desc": "Pictorially"
            },
            {
                "code": "j",
                "desc": "Land forms"
            },
            {
                "code": "k",
                "desc": "Bathymetry/isolines"
            },
            {
                "code": "m",
                "desc": "Rock drawings"
            },
            {
                "code": "z",
                "desc": "Other"
            },
            {
                "code": "||||",
                "desc": "No attempt to code"
            }
        ] 
    },
    "22": {
        "start": 22,
        "stop": 23,
        "len": 2,
        "desc": "Projection (006/05-06)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "##",
                "desc": "Projection not specified"
            },
            {
                "code": "aa",
                "desc": "Aitoff"
            },
            {
                "code": "ab",
                "desc": "Gnomic"
            },
            {
                "code": "ac",
                "desc": "Lambert's azimuthal equal area"
            },
            {
                "code": "ad",
                "desc": "Orthographic"
            },
            {
                "code": "ae",
                "desc": "Azimuthal equidistant"
            },
            {
                "code": "af",
                "desc": "Stereographic"
            },
            {
                "code": "ag",
                "desc": "General vertical near-sided"
            },
            {
                "code": "am",
                "desc": "Modified stereographic for Alaska"
            },
            {
                "code": "an",
                "desc": "Chamberlin trimetric"
            },
            {
                "code": "ap",
                "desc": "Polar stereographic"
            },
            {
                "code": "au",
                "desc": "Azimuthal, specific type unknown"
            },
            {
                "code": "az",
                "desc": "Azimuthal, other"
            },
            {
                "code": "ba",
                "desc": "Gall"
            },
            {
                "code": "bb",
                "desc": "Goode's homolographic"
            },
            {
                "code": "bc",
                "desc": "Lambert's cylindrical equal area"
            },
            {
                "code": "bd",
                "desc": "Mercator"
            },
            {
                "code": "be",
                "desc": "Miller"
            },
            {
                "code": "bf",
                "desc": "Mollweide"
            },
            {
                "code": "bg",
                "desc": "Sinusoidal"
            },
            {
                "code": "bh",
                "desc": "Transverse Mercator"
            },
            {
                "code": "bi",
                "desc": "Gauss-Kruger"
            },
            {
                "code": "bj",
                "desc": "Equirectangular"
            },
            {
                "code": "bk",
                "desc": "Krovak"
            },
            {
                "code": "bl",
                "desc": "Cassini-Soldner"
            },
            {
                "code": "bo",
                "desc": "Oblique Mercator"
            },
            {
                "code": "br",
                "desc": "Robinson"
            },
            {
                "code": "bs",
                "desc": "Space oblique Mercator"
            },
            {
                "code": "bu",
                "desc": "Cylindrical, specific type unknown"
            },
            {
                "code": "bz",
                "desc": "Cylindrical, other"
            },
            {
                "code": "ca",
                "desc": "Albers equal area"
            },
            {
                "code": "cb",
                "desc": "Bonne"
            },
            {
                "code": "cc",
                "desc": "Lambert's conformal conic"
            },
            {
                "code": "ce",
                "desc": "Equidistant conic"
            },
            {
                "code": "cp",
                "desc": "Polyconic"
            },
            {
                "code": "cu",
                "desc": "Conic, specific type unknown"
            },
            {
                "code": "cz",
                "desc": "Conic, other"
            },
            {
                "code": "da",
                "desc": "Armadillo"
            },
            {
                "code": "db",
                "desc": "Butterfly"
            },
            {
                "code": "dc",
                "desc": "Eckert"
            },
            {
                "code": "dd",
                "desc": "Goode's homolosine"
            },
            {
                "code": "de",
                "desc": "Miller's bipolar oblique conformal conic"
            },
            {
                "code": "df",
                "desc": "Van Der Grinten"
            },
            {
                "code": "dg",
                "desc": "Dimaxion"
            },
            {
                "code": "dh",
                "desc": "Cordiform"
            },
            {
                "code": "dl",
                "desc": "Lambert conformal"
            },
            {
                "code": "zz",
                "desc": "Other"
            },
            {
                "code": "||",
                "desc": "No attempt to code"
            }
        ] 
    },
    "24": {
        "start": 24,
        "stop": 24,
        "len": 1,
        "desc": "Undefined (006/07)",
        "type": "auto",
        "defaultValue": ""
    },
    "25": {
        "start": 25,
        "stop": 25,
        "len": 1,
        "desc": "Type of cartographic material (006/08)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "a",
                "desc": "Single map"
            },
            {
                "code": "b",
                "desc": "Map series"
            },
            {
                "code": "c",
                "desc": "Map serial"
            },
            {
                "code": "d",
                "desc": "Globe"
            },
            {
                "code": "e",
                "desc": "Atlas"
            },
            {
                "code": "f",
                "desc": "Separate supplement to another work"
            },
            {
                "code": "g",
                "desc": "Bound as part of another work"
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
    "26": {
        "start": 26,
        "stop": 27,
        "len": 2,
        "desc": "Undefined (006/09-10)",
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
        "stop": 30,
        "len": 1,
        "desc": "Undefined (006/13)",
        "type": "auto",
        "defaultValue": ""
    },
    "31": {
        "start": 31,
        "stop": 31,
        "len": 1,
        "desc": "Index (006/14)",
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
        "desc": "Undefined (006/15)",
        "type": "auto",
        "defaultValue": ""
    },
    "33": {
        "start": 33,
        "stop": 34,
        "len": 1,
        "desc": "Special format characteristics (006/16-17)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "No specified special format characteristics"
            },
            {
                "code": "e",
                "desc": "Manuscript"
            },
            {
                "code": "j",
                "desc": "Picture card, post card"
            },
            {
                "code": "k",
                "desc": "Calendar"
            },
            {
                "code": "l",
                "desc": "Puzzle"
            },
            {
                "code": "n",
                "desc": "Game"
            },
            {
                "code": "o",
                "desc": "Wall map"
            },
            {
                "code": "p",
                "desc": "Playing cards"
            },
            {
                "code": "r",
                "desc": "Loose-leaf"
            },
            {
                "code": "z",
                "desc": "Other"
            },
            {
                "code": "||",
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