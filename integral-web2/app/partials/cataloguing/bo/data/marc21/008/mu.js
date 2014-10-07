defn_008['mu'] = {
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
        "stop": 19,
        "len": 2,
        "desc": "Form of composition (006/01-02)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "an",
                "desc": "Anthems"
            },
            {
                "code": "bd",
                "desc": "Ballads"
            },
            {
                "code": "bg",
                "desc": "Bluegrass music"
            },
            {
                "code": "bl",
                "desc": "Blues"
            },
            {
                "code": "bt",
                "desc": "Ballets"
            },
            {
                "code": "ca",
                "desc": "Chaconnes"
            },
            {
                "code": "cb",
                "desc": "Chants, Other religions"
            },
            {
                "code": "cc",
                "desc": "Chant, Christian"
            },
            {
                "code": "cg",
                "desc": "Concerti grossi"
            },
            {
                "code": "ch",
                "desc": "Chorales"
            },
            {
                "code": "cl",
                "desc": "Chorale preludes"
            },
            {
                "code": "cn",
                "desc": "Canons and rounds"
            },
            {
                "code": "co",
                "desc": "Concertos"
            },
            {
                "code": "cp",
                "desc": "Chansons, polyphonic"
            },
            {
                "code": "cr",
                "desc": "Carols"
            },
            {
                "code": "cs",
                "desc": "Chance compositions"
            },
            {
                "code": "ct",
                "desc": "Cantatas"
            },
            {
                "code": "cy",
                "desc": "Country music"
            },
            {
                "code": "cz",
                "desc": "Canzonas"
            },
            {
                "code": "df",
                "desc": "Dance forms"
            },
            {
                "code": "dv",
                "desc": "Divertimentos, serenades, cassations, divertissements, and notturni"
            },
            {
                "code": "fg",
                "desc": "Fugues"
            },
            {
                "code": "fl",
                "desc": "Flamenco"
            },
            {
                "code": "fm",
                "desc": "Folk music"
            },
            {
                "code": "ft",
                "desc": "Fantasias"
            },
            {
                "code": "gm",
                "desc": "Gospel music"
            },
            {
                "code": "hy",
                "desc": "Hymns"
            },
            {
                "code": "jz",
                "desc": "Jazz"
            },
            {
                "code": "mc",
                "desc": "Musical revues and comedies"
            },
            {
                "code": "md",
                "desc": "Madrigals"
            },
            {
                "code": "mi",
                "desc": "Minuets"
            },
            {
                "code": "mo",
                "desc": "Motets"
            },
            {
                "code": "mp",
                "desc": "Motion picture music"
            },
            {
                "code": "mr",
                "desc": "Marches"
            },
            {
                "code": "ms",
                "desc": "Masses"
            },
            {
                "code": "mu",
                "desc": "Multiple forms"
            },
            {
                "code": "mz",
                "desc": "Mazurkas"
            },
            {
                "code": "nc",
                "desc": "Nocturnes"
            },
            {
                "code": "nn",
                "desc": "Not applicable"
            },
            {
                "code": "op",
                "desc": "Operas"
            },
            {
                "code": "or",
                "desc": "Oratorios"
            },
            {
                "code": "ov",
                "desc": "Overtures"
            },
            {
                "code": "pg",
                "desc": "Program music"
            },
            {
                "code": "pm",
                "desc": "Passion music"
            },
            {
                "code": "po",
                "desc": "Polonaises"
            },
            {
                "code": "pp",
                "desc": "Popular music"
            },
            {
                "code": "pr",
                "desc": "Preludes"
            },
            {
                "code": "ps",
                "desc": "Passacaglias"
            },
            {
                "code": "pt",
                "desc": "Part-songs"
            },
            {
                "code": "pv",
                "desc": "Pavans"
            },
            {
                "code": "rc",
                "desc": "Rock music"
            },
            {
                "code": "rd",
                "desc": "Rondos"
            },
            {
                "code": "rg",
                "desc": "Ragtime music"
            },
            {
                "code": "ri",
                "desc": "Ricercars"
            },
            {
                "code": "rp",
                "desc": "Rhapsodies"
            },
            {
                "code": "rq",
                "desc": "Requiems"
            },
            {
                "code": "sd",
                "desc": "Square dance music"
            },
            {
                "code": "sg",
                "desc": "Songs"
            },
            {
                "code": "sn",
                "desc": "Sonatas"
            },
            {
                "code": "sp",
                "desc": "Symphonic poems"
            },
            {
                "code": "st",
                "desc": "Studies and exercises"
            },
            {
                "code": "su",
                "desc": "Suites"
            },
            {
                "code": "sy",
                "desc": "Symphonies"
            },
            {
                "code": "tc",
                "desc": "Toccatas"
            },
            {
                "code": "tl",
                "desc": "Teatro lirico"
            },
            {
                "code": "ts",
                "desc": "Trio-sonatas"
            },
            {
                "code": "uu",
                "desc": "Unknown"
            },
            {
                "code": "vi",
                "desc": "Villancicos"
            },
            {
                "code": "vr",
                "desc": "Variations"
            },
            {
                "code": "wz",
                "desc": "Waltzes"
            },
            {
                "code": "za",
                "desc": "Zarzuelas"
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
    "20": {
        "start": 20,
        "stop": 20,
        "len": 1,
        "desc": "Format of music (006/03)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "a",
                "desc": "Full score"
            },
            {
                "code": "b",
                "desc": "Full score, miniature or study size"
            },
            {
                "code": "c",
                "desc": "Accompaniment reduced for keyboard"
            },
            {
                "code": "d",
                "desc": "Vocal score"
            },
            {
                "code": "e",
                "desc": "Condensed score or piano-conductor score"
            },
            {
                "code": "g",
                "desc": "Close score"
            },
            {
                "code": "h",
                "desc": "Chorus score"
            },
            {
                "code": "i",
                "desc": "Condensed score"
            },
            {
                "code": "j",
                "desc": "Performer-conductor part"
            },
            {
                "code": "m",
                "desc": "Multiple score formats"
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
    },
    "21": {
        "start": 21,
        "stop": 21,
        "len": 1,
        "desc": "Music parts (006/04)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "No parts in hand or not specified"
            },
            {
                "code": "d",
                "desc": "Instrumental and vocal parts"
            },
            {
                "code": "e",
                "desc": "Instrumental parts"
            },
            {
                "code": "f",
                "desc": "Vocal parts"
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
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
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
                "desc": "Unknown or unspecified"
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
        "desc": "Form of item (006/06)",
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
        "stop": 29,
        "len": 1,
        "desc": "Accompanying matter (006/07-12)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "No accompanying matter"
            },
            {
                "code": "a",
                "desc": "Discography"
            },
            {
                "code": "b",
                "desc": "Bibliography"
            },
            {
                "code": "c",
                "desc": "Thematic index"
            },
            {
                "code": "d",
                "desc": "Libretto or text"
            },
            {
                "code": "e",
                "desc": "Biography of composer or author"
            },
            {
                "code": "f",
                "desc": "Biography of performer or history of ensemble"
            },
            {
                "code": "g",
                "desc": "Technical and/or historical information on instruments"
            },
            {
                "code": "h",
                "desc": "Technical information on music"
            },
            {
                "code": "i",
                "desc": "Historical information"
            },
            {
                "code": "k",
                "desc": "Ethnological information"
            },
            {
                "code": "r",
                "desc": "Instructional materials"
            },
            {
                "code": "s",
                "desc": "Music"
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
    "30": {
        "start": 30,
        "stop": 31,
        "len": 1,
        "desc": "Literary text for sound recordings (006/13-14)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "Item is a music sound recording"
            },
            {
                "code": "a",
                "desc": "Autobiography"
            },
            {
                "code": "b",
                "desc": "Biography"
            },
            {
                "code": "c",
                "desc": "Conference proceedings"
            },
            {
                "code": "d",
                "desc": "Drama"
            },
            {
                "code": "e",
                "desc": "Essays"
            },
            {
                "code": "f",
                "desc": "Fiction"
            },
            {
                "code": "g",
                "desc": "Reporting"
            },
            {
                "code": "h",
                "desc": "History"
            },
            {
                "code": "i",
                "desc": "Instruction"
            },
            {
                "code": "j",
                "desc": "Language instruction"
            },
            {
                "code": "k",
                "desc": "Comedy"
            },
            {
                "code": "l",
                "desc": "Lectures, speeches"
            },
            {
                "code": "m",
                "desc": "Memoirs"
            },
            {
                "code": "n",
                "desc": "Not applicable"
            },
            {
                "code": "o",
                "desc": "Folktales"
            },
            {
                "code": "p",
                "desc": "Poetry"
            },
            {
                "code": "r",
                "desc": "Rehearsals"
            },
            {
                "code": "s",
                "desc": "Sounds"
            },
            {
                "code": "t",
                "desc": "Interviews"
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
        "stop": 33,
        "len": 1,
        "desc": "Transposition and arrangement (006/16)",
        "type": "select",
        "defaultValue": "",
        "list": [
            {
                "code": "#",
                "desc": "Not arrangement or transposition or not specified"
            },
            {
                "code": "a",
                "desc": "Transposition"
            },
            {
                "code": "b",
                "desc": "Arrangement"
            },
            {
                "code": "c",
                "desc": "Both transposed and arranged"
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
                "code": "|",
                "desc": "No attempt to code"
            }
        ] 
    },
    "34": {
        "start": 34,
        "stop": 34,
        "len": 1,
        "desc": "Undefined (006/17)",
        "type": "auto",
        "defaultValue": ""
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