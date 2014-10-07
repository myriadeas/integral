var marc21 = {
		"001" : {
		    "desc" : "Control Number",
		    "repeatable" : false
		  },
		  "003" : {
		    "desc" : "Control Number Identifier",
		    "repeatable" : false
		  },
		  "005" : {
		    "desc" : "Date and Time of Latest Transaction",
		    "repeatable" : false
		  },
		  "006" : {
		    "desc" : "Fixed-Length Data Elements-Additional Material Characteristics",
		    "repeatable" : true
		  },
		  "007" : {
		    "desc" : "Physical Description Fixed Field-General Information",
		    "repeatable" : true
		  },
		  "008" : {
		    "desc" : "Fixed-Length Data Elements-General Information",
		    "repeatable" : false
		  },
		  "010" : {
		    "desc" : "Library of Congress Control Number",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "LC control number",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "NUCMC control number",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Canceled/invalid LC control number",
		        "repeatable" : true
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "013" : {
		    "desc" : "Patent Control Information",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Number",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Country",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Type of number",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Status",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Party to document",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "015" : {
		    "desc" : "National Bibliography Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "National bibliography number",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Canceled/invalid national bibliography number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "016" : {
		    "desc" : "National Bibliographic Agency Control Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Library and Archives Canada",
		      "7" : "Source specified in subfield $2 Used when the source of the control number is indicated by a code in subfield $2. Codes from: MARC Code List for Organizations."
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Record control number",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Canceled/invalid control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "017" : {
		    "desc" : "Copyright or Legal Deposit Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Copyright or legal deposit number",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Copyright or legal deposit number",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Assigning agency",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Display text",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Canceled/invalid copyright or legal deposit number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "018" : {
		    "desc" : "Copyright Article-Fee Code",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Copyright article-fee code",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "020" : {
		    "desc" : "International Standard Book Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Terms of availability",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Canceled/invalid ISBN",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "022" : {
		    "desc" : "International Standard Serial Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No level specified",
		      "0" : "Continuing resource of international interest",
		      "1" : "Continuing resource not of international interest"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "l" : {
		        "desc" : "ISSN-L",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Canceled ISSN-L",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Incorrect ISSN",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Canceled ISSN",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "024" : {
		    "desc" : "Other Standard Identifier",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "International Standard Recording Code",
		      "1" : "Universal Product Code",
		      "2" : "International Standard Music Number",
		      "3" : "International Article Number",
		      "4" : "Serial Item and Contribution Identifier",
		      "7" : "Source specified in subfield $2",
		      "8" : "Unspecified type of standard number or code"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "0" : "No difference",
		      "1" : "Difference"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Standard number or code",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Terms of availability",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Additional codes following the standard number or code",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Canceled/invalid standard number or code",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of number or code",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "025" : {
		    "desc" : "Overseas Acquisition Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Overseas acquisition number",
		        "repeatable" : true
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "026" : {
		    "desc" : "Fingerprint Identifier",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "First and second groups of characters",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Third and fourth groups of characters",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Date",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Number of volume or part",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Unparsed fingerprint",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "027" : {
		    "desc" : "Standard Technical Report Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Standard technical report number",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Canceled/invalid number",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "028" : {
		    "desc" : "Publisher Number",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Issue number Number used to identify the issue designation, or serial identification, assigned by a publisher to a specific sound recording, side of a sound recording, or performance on a sound recording or to a group of sound recordings issued as a set.",
		      "1" : "Matrix number Master from which the specific recording was pressed.",
		      "2" : "Plate number Assigned by a publisher to a specific music publication.",
		      "3" : "Other music number",
		      "4" : "Videorecording number",
		      "5" : "Other publisher number"
		    },
		    "ind2" : {
		      "0" : "No note, no added entry",
		      "1" : "Note, added entry",
		      "2" : "Note, no added entry",
		      "3" : "No note, added entry"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Publisher number",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "q" : {
		        "desc" : "Qualifying information",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "030" : {
		    "desc" : "CODEN Designation",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "CODEN",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Canceled/invalid CODEN",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "031" : {
		    "desc" : "Musical Incipits Information",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Number of work",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Number of movement",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Number of excerpt",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Caption or heading",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Role",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Clef",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Voice/instrument",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Key signature",
		        "repeatable" : false
		      },
		      "o" : {
		        "desc" : "Time signature",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Musical notation",
		        "repeatable" : false
		      },
		      "q" : {
		        "desc" : "General note",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Key or mode",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Coded validity note",
		        "repeatable" : true
		      },
		      "t" : {
		        "desc" : "Text incipit",
		        "repeatable" : true
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Link text",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Public note",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "System code",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "032" : {
		    "desc" : "Postal Registration Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Postal registration number",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Source agency assigning number",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "033" : {
		    "desc" : "Date/Time and Place of an Event",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No date information",
		      "0" : "Single date",
		      "1" : "Multiple single dates",
		      "2" : "Range of dates"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "0" : "Capture Pertains to the recording of sound, the filming of visual images, the making or producing of an item, or other form of creation of an item.",
		      "1" : "Broadcast Pertains to the broadcasting (i.e., transmission) or re-broadcasting of sound or visual images.",
		      "2" : "Finding Pertains to the finding of a naturally occurring object."
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Formatted date/time",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Geographic classification area code",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Geographic classification subarea code",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Place of event",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of term",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "034" : {
		    "desc" : "Coded Cartographic Mathematical Data",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Scale indeterminable/No scale recorded Used when no representative fraction is given in field 255.",
		      "1" : "Single scale",
		      "3" : "Range of scales"
		    },
		    "ind2" : {
		      "#" : "Not applicable",
		      "0" : "Outer ring",
		      "1" : "Exclusion ring"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Category of scale",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Constant ratio linear horizontal scale",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Constant ratio linear vertical scale",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Coordinates - westernmost longitude",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Coordinates - easternmost longitude",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Coordinates - northernmost latitude",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Coordinates - southernmost latitude",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Angular scale",
		        "repeatable" : true
		      },
		      "j" : {
		        "desc" : "Declination - northern limit",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Declination - southern limit",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Right ascension - eastern limit",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Right ascension - western limit",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Equinox",
		        "repeatable" : false
		      },
		      "r" : {
		        "desc" : "Distance from earth",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "G-ring latitude",
		        "repeatable" : true
		      },
		      "t" : {
		        "desc" : "G-ring longitude",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "Beginning date",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "Ending date",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Name of extraterrestrial body",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "035" : {
		    "desc" : "System Control Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "System control number",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Canceled/invalid control number",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "036" : {
		    "desc" : "Original Study Number for Computer Data Files",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Original study number",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Source agency assigning number",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "037" : {
		    "desc" : "Source of Acquisition",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Stock number",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Source of stock number/acquisition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Terms of availability",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Form of issue",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Additional format characteristics",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "038" : {
		    "desc" : "Record Content Licensor",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Record content licensor",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "040" : {
		    "desc" : "Cataloging Source",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Original cataloging agency",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Language of cataloging",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Transcribing agency",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Modifying agency",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Description conventions",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "041" : {
		    "desc" : "Language Code",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Item not a translation/does not include a translation",
		      "1" : "Item is or includes a translation"
		    },
		    "ind2" : {
		      "#" : "MARC language code",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Language code of text/sound track or separate title",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Language code of summary or abstract",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Language code of sung or spoken text",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Language code of librettos",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Language code of table of contents",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Language code of accompanying material other than librettos",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Language code of original",
		        "repeatable" : true
		      },
		      "j" : {
		        "desc" : "Language code of subtitles or captions",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Language code of intermediate translations",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Language code of original accompanying materials other than librettos",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Language code of original libretto",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of code",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "042" : {
		    "desc" : "Authentication Code",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Authentication code",
		        "repeatable" : true
		      }
		    }
		  },
		  "043" : {
		    "desc" : "Geographic Area Code",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Geographic area code",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Local GAC code",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "ISO code",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of local code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "044" : {
		    "desc" : "Country of Publishing/Producing Entity Code",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "MARC country code",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Local subentity code",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "ISO country code",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of local subentity code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "045" : {
		    "desc" : "Time Period of Content",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Subfield $b or $c not present",
		      "0" : "Single date/time",
		      "1" : "Multiple single dates/times Multiple $b and/or $c subfields are present, each containing a date/time.",
		      "2" : "Range of dates/times Two $b and/or $c subfields are present and contain a range of dates/times."
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Time period code",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Formatted 9999 B.C. through C.E. time period",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Formatted pre-9999 B.C. time period",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "046" : {
		    "desc" : "Special Coded Dates",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Type of date code",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Date 1, B.C. date",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Date 1, C.E. date",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date 2, B.C. date",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Date 2, C.E. date",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Date resource modified",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Beginning or single date created",
		        "repeatable" : false
		      },
		      "l" : {
		        "desc" : "Ending date created",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Beginning of date valid",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "End of date valid",
		        "repeatable" : false
		      },
		      "o" : {
		        "desc" : "Single or starting date for aggregated content",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Ending date for aggregated content",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Source of date",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "047" : {
		    "desc" : "Form of Musical Composition Code",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "MARC musical composition code",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Form of musical composition code",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of code",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "048" : {
		    "desc" : "Number of Musical Instruments or Voices Code",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "MARC code",
		      "7" : "Source specified in subfield ‡2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Performer or ensemble",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Soloist",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of code",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "050" : {
		    "desc" : "Library of Congress Call Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided Used for all call numbers assigned by agencies other than the Library of Congress.",
		      "0" : "Item is in LC Other agencies should use this value when transcribing from LC cataloging copy on which the call number is neither enclosed within brackets nor preceded by a Maltese cross.",
		      "1" : "Item is not in LC Used by other agencies when transcribing from LC copy on which the call number appears in brackets or is preceded by a Maltese cross. Brackets that customarily surround call numbers for items not in LC are not carried in the MARC record; they may be generated for display."
		    },
		    "ind2" : {
		      "0" : "Assigned by LC Used when an institution is transcribing from LC cataloging copy.",
		      "4" : "Assigned by agency other than LC"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Classification number",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Item number",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "051" : {
		    "desc" : "Library of Congress Copy, Issue, Offprint Statement",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Classification number",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Item number",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Copy information",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "052" : {
		    "desc" : "Geographic Classification",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Library of Congress Classification",
		      "1" : "U.S. Dept. of Defense Classification",
		      "7" : "Source specified in subfield $2"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Geographic classification area code",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Geographic classification subarea code",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Populated place name",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Code source",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "055" : {
		    "desc" : "Classification Numbers Assigned in Canada",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Information not provided Used in any record input by an institution other than LAC.",
		      "0" : "Work held by LAC",
		      "1" : "Work not held by LAC"
		    },
		    "ind2" : {
		      "0" : "LC-based call number assigned by LAC",
		      "1" : "Complete LC class number assigned by LAC",
		      "2" : "Incomplete LC class number assigned by LAC",
		      "3" : "LC-based call number assigned by the contributing library",
		      "4" : "Complete LC class number assigned by the contributing library",
		      "5" : "Incomplete LC class number assigned by the contributing library",
		      "6" : "Other call number assigned by LAC",
		      "7" : "Other class number assigned by LAC",
		      "8" : "Other call number assigned by the contributing library",
		      "9" : "Other class number assigned by the contributing library"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Classification number",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Item number",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Source of call/class number",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "060" : {
		    "desc" : "National Library of Medicine Call Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided Used for call numbers assigned by an organization other than NLM.",
		      "0" : "Item is in NLM",
		      "1" : "Item is not in NLM"
		    },
		    "ind2" : {
		      "0" : "Assigned by NLM",
		      "4" : "Assigned by agency other than NLM"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Classification number",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Item number",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "061" : {
		    "desc" : "National Library of Medicine Copy Statement",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Classification number",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Item number",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Copy information",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "066" : {
		    "desc" : "Character Sets Present",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Primary G0 character set",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Primary G1 character set",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Alternate G0 or G1 character set",
		        "repeatable" : true
		      }
		    }
		  },
		  "070" : {
		    "desc" : "National Agricultural Library Call Number",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Item is in NAL",
		      "1" : "Item is not in NAL"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Classification number",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Item number",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "071" : {
		    "desc" : "National Agricultural Library Copy Statement",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Classification number",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Item number",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Copy information",
		        "repeatable" : true
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "072" : {
		    "desc" : "Subject Category Code",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "0" : "NAL subject category code list",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Subject category code",
		        "repeatable" : false
		      },
		      "x" : {
		        "desc" : "Subject category code subdivision",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "074" : {
		    "desc" : "GPO Item Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "GPO item number",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Canceled/invalid GPO item number",
		        "repeatable" : true
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "080" : {
		    "desc" : "Universal Decimal Classification Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Full",
		      "1" : "Abridged"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Universal Decimal Classification number",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Item number",
		        "repeatable" : false
		      },
		      "x" : {
		        "desc" : "Common auxiliary subdivision",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Edition identifier",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "082" : {
		    "desc" : "Dewey Decimal Classification Number",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Full edition",
		      "1" : "Abridged edition",
		      "7" : "Other edition specified in subfield $2"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "0" : "Assigned by LC May be used by organizations transcribing from LC copy.",
		      "4" : "Assigned by agency other than LC"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Classification number",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Item number",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Standard or optional designation",
		        "repeatable" : false
		      },
		      "q" : {
		        "desc" : "Assigning agency",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Edition number",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "083" : {
		    "desc" : "Additional Dewey Decimal Classification Number",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Full edition",
		      "1" : "Abridged edition",
		      "7" : "Other edition specified in subfield $2"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Classification number",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Classification number--Ending number of span",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Standard or optional designation",
		        "repeatable" : false
		      },
		      "q" : {
		        "desc" : "Assigning agency",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "Table sequence number for internal subarrangement or add table",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Table identification",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Edition number",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "084" : {
		    "desc" : "Other Classification Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Classification number",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Item number",
		        "repeatable" : false
		      },
		      "q" : {
		        "desc" : "Assigning agency",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Number source",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "085" : {
		    "desc" : "Synthesized Classification Number Components",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Number where instructions are found-single number or beginning number of span",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Base number",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Classification number-ending number of span",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Facet designator",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Root number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Digits added from classification number in schedule or external table",
		        "repeatable" : true
		      },
		      "t" : {
		        "desc" : "Digits added from internal subarrangement or add table",
		        "repeatable" : true
		      },
		      "u" : {
		        "desc" : "Number being analyzed",
		        "repeatable" : true
		      },
		      "v" : {
		        "desc" : "Number in internal subarrangement or add table where instructions are found",
		        "repeatable" : true
		      },
		      "w" : {
		        "desc" : "Table identification-Internal subarrangement or add table",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Table sequence number for internal subarrangement or add table",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Table identification",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "086" : {
		    "desc" : "Government Document Classification Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Source specified in subfield $2 Classification number other than the U.S. or Canadian scheme.",
		      "0" : "Superintendent of Documents Classification System Assigned by the U.S Government Printing Office. Supt. of Docs. no.: may be generated for display.",
		      "1" : "Government of Canada Publications: Outline of Classification"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Classification number",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Canceled/invalid classification number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Number source",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "088" : {
		    "desc" : "Report Number",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Report number",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Canceled/invalid report number",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "100" : {
		    "desc" : "Main Entry-Personal Name",
		    "repeatable" : false,
		    "ind1" : {
		      "0" : "Forename Forename or a name consisting of words, initials, letters, etc., that are formatted in direct order.",
		      "1" : "Surname Single or multiple surname formatted in inverted order or a single name without forenames that is known to be a surname.",
		      "3" : "Family name Name represents a family, clan, dynasty, house, or other such group and may be formatted in direct or inverted order."
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Personal name",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Numeration",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Titles and words associated with a name",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Dates associated with a name",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Attribution qualifier",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "q" : {
		        "desc" : "Fuller form of name",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "110" : {
		    "desc" : "Main Entry-Corporate Name",
		    "repeatable" : false,
		    "ind1" : {
		      "0" : "Inverted name Corporate name begins with a personal name in inverted order.",
		      "1" : "Jurisdiction name Name of a jurisdiction that is also an ecclesiastical entity or is a jurisdiction name under which a corporate name or a title of a work is entered.",
		      "2" : "Name in direct order"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Corporate name or jurisdiction name as entry element",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Subordinate unit",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Location of meeting",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of meeting or treaty signing",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Number of part/section/meeting",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "111" : {
		    "desc" : "Main Entry-Meeting Name",
		    "repeatable" : false,
		    "ind1" : {
		      "0" : "Inverted name Meeting name begins with a personal name in inverted order.",
		      "1" : "Jurisdiction name Jurisdiction name under which a meeting name is entered.",
		      "2" : "Name in direct order"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Meeting name or jurisdiction name as entry element",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Location of meeting",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of meeting",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Subordinate unit",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Number of part/section/meeting",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "q" : {
		        "desc" : "Name of meeting following jurisdiction name entry element",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "130" : {
		    "desc" : "Main Entry-Uniform Title",
		    "repeatable" : false,
		    "ind1" : {
		      "0-9" : "Number of nonfiling characters"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of treaty signing",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "210" : {
		    "desc" : "Abbreviated Title",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "No added entry",
		      "1" : "Added entry"
		    },
		    "ind2" : {
		      "#" : "Abbreviated key title",
		      "0" : "Other abbreviated title"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Abbreviated title",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "222" : {
		    "desc" : "Key Title",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "0" : "No nonfiling characters",
		      "1-9" : "Number of nonfiling characters"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Key title",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "240" : {
		    "desc" : "Uniform Title",
		    "repeatable" : false,
		    "ind1" : {
		      "0" : "Not printed or displayed",
		      "1" : "Printed or displayed"
		    },
		    "ind2" : {
		      "0-9" : "Number of nonfiling characters"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of treaty signing",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "242" : {
		    "desc" : "Translation of Title by Cataloging Agency",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "No added entry",
		      "1" : "Added entry"
		    },
		    "ind2" : {
		      "0" : "No nonfiling characters",
		      "1-9" : "Number of nonfiling characters"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Remainder of title",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Statement of responsibility, etc.",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Language code of translated title",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "243" : {
		    "desc" : "Collective Uniform Title",
		    "repeatable" : false,
		    "ind1" : {
		      "0" : "Not printed or displayed",
		      "1" : "Printed or displayed"
		    },
		    "ind2" : {
		      "0-9" : "Number of nonfiling characters"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of treaty signing",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "245" : {
		    "desc" : "Title Statement",
		    "repeatable" : false,
		    "ind1" : {
		      "0" : "No added entry",
		      "1" : "Added entry"
		    },
		    "ind2" : {
		      "0" : "No nonfiling characters",
		      "1-9" : "Number of nonfiling characters"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Remainder of title",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Statement of responsibility, etc.",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Inclusive dates",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Bulk dates",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Form",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "246" : {
		    "desc" : "Varying Form of Title",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Note, no added entry",
		      "1" : "Note, added entry",
		      "2" : "No note, no added entry",
		      "3" : "No note, added entry"
		    },
		    "ind2" : {
		      "#" : "No type specified",
		      "0" : "Portion of title",
		      "1" : "Parallel title",
		      "2" : "Distinctive title",
		      "3" : "Other title",
		      "4" : "Cover title",
		      "5" : "Added title page title",
		      "6" : "Caption title",
		      "7" : "Running title",
		      "8" : "Spine title"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Title proper/short title",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Remainder of title",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Date or sequential designation",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Display text",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "247" : {
		    "desc" : "Former Title",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "No added entry",
		      "1" : "Added entry"
		    },
		    "ind2" : {
		      "0" : "Display note",
		      "1" : "Do not display note"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Remainder of title",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Date or sequential designation",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "250" : {
		    "desc" : "Edition Statement",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Edition statement",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Remainder of edition statement",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "254" : {
		    "desc" : "Musical Presentation Statement",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Musical presentation statement",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "255" : {
		    "desc" : "Cartographic Mathematical Data",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Statement of scale",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Statement of projection",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Statement of coordinates",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Statement of zone",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Statement of equinox",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Outer G-ring coordinate pairs",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Exclusion G-ring coordinate pairs",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "256" : {
		    "desc" : "Computer File Characteristics",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Computer file characteristics",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "257" : {
		    "desc" : "Country of Producing Entity",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Country of producing entity",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "258" : {
		    "desc" : "Philatelic Issue Data",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Issuing jurisdiction",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Denomination",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "260" : {
		    "desc" : "Publication, Distribution, etc.",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Not applicable/No information provided/Earliest available publisher",
		      "2" : "Intervening publisher",
		      "3" : "Current/latest publisher"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Place of publication, distribution, etc.",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Name of publisher, distributor, etc.",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Date of publication, distribution, etc.",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Place of manufacture",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Manufacturer",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Date of manufacture",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "263" : {
		    "desc" : "Projected Publication Date",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Projected publication date",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "264" : {
		    "desc" : "Production, Publication, Distribution, Manufacture, and Copyright Notice",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Not applicable/No information provided/Earliest",
		      "2" : "Intervening",
		      "3" : "Current/latest"
		    },
		    "ind2" : {
		      "0" : "Production",
		      "1" : "Publication",
		      "2" : "Distribution",
		      "3" : "Manufacture",
		      "4" : "Copyright notice date"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Place of production, publication, distribution, manufacture",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Name of producer, publisher, distributor, manufacturer",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Date of production, publication, distribution, manufacture, or copyright notice",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "270" : {
		    "desc" : "Address",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No level specified",
		      "1" : "Primary",
		      "2" : "Secondary"
		    },
		    "ind2" : {
		      "#" : "No type specified",
		      "0" : "Mailing",
		      "7" : "Type specified in subfield $i"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Address",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "City",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "State or province",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Country",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Postal code",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Terms preceding attention name",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Attention name",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Attention position",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Type of address",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Specialized telephone number",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Telephone number",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Fax number",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Electronic mail address",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "TDD or TTY number",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Contact person",
		        "repeatable" : true
		      },
		      "q" : {
		        "desc" : "Title of contact person",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Hours",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Public note",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "300" : {
		    "desc" : "Physical Description",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Extent",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Other physical details",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Dimensions",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Accompanying material",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Type of unit",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Size of unit",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "306" : {
		    "desc" : "Playing Time",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Playing time",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "307" : {
		    "desc" : "Hours, Etc.",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Hours",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Hours",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Additional information",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "310" : {
		    "desc" : "Current Publication Frequency",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Current publication frequency",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Date of current publication frequency",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "321" : {
		    "desc" : "Former Publication Frequency",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Former publication frequency",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Dates of former publication frequency",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "336" : {
		    "desc" : "Content Type",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Content type term",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Content type code",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "337" : {
		    "desc" : "Media Type",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Media type term",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Media type code",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "338" : {
		    "desc" : "Carrier Type",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Carrier type term",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Carrier type code",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "340" : {
		    "desc" : "Physical Medium",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Material base and configuration",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Dimensions",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Materials applied to surface",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Information recording technique",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Support",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Production rate/ratio",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Location within medium",
		        "repeatable" : true
		      },
		      "i" : {
		        "desc" : "Technical specifications of medium",
		        "repeatable" : true
		      },
		      "j" : {
		        "desc" : "Generation",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Layout",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Book format",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Font size",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Polarity",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "342" : {
		    "desc" : "Geospatial Reference Data",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Horizontal coordinate system",
		      "1" : "Vertical coordinate system"
		    },
		    "ind2" : {
		      "0" : "Geographic",
		      "1" : "Map projection",
		      "2" : "Grid coordinate system",
		      "3" : "Local planar",
		      "4" : "Local",
		      "5" : "Geodetic model",
		      "6" : "Altitude",
		      "7" : "Method specified in $2",
		      "8" : "Depth"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Name",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Coordinate units or distance units",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Latitude resolution",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Longitude resolution",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Standard parallel or oblique line latitude",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Oblique line longitude",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Longitude of central meridian or projection center",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Latitude of projection center or projection origin",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "False easting",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "False northing",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Scale factor",
		        "repeatable" : false
		      },
		      "l" : {
		        "desc" : "Height of perspective point above surface",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Azimuthal angle",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Azimuth measure point longitude or straight vertical longitude from pole",
		        "repeatable" : false
		      },
		      "o" : {
		        "desc" : "Landsat number and path number",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Zone identifier",
		        "repeatable" : false
		      },
		      "q" : {
		        "desc" : "Ellipsoid name",
		        "repeatable" : false
		      },
		      "r" : {
		        "desc" : "Semi-major axis",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Denominator of flattening ratio",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Vertical resolution",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Vertical encoding method",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Local planar, local, or other projection or grid description",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Local planar or local georeference information",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Reference method used",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "343" : {
		    "desc" : "Planar Coordinate Data",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Planar coordinate encoding method",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Planar distance units",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Abscissa resolution",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Ordinate resolution",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Distance resolution",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Bearing resolution",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Bearing units",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Bearing reference direction",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Bearing reference meridian",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "344" : {
		    "desc" : "Sound Characteristics",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Type of recording",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Recording medium",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Playing speed",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Groove characteristic",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Track configuration",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Tape configuration",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Configuration of playback channels",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Special playback characteristics",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "345" : {
		    "desc" : "Projection Characteristics of Moving Image",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Presentation format",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Projection speed",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "346" : {
		    "desc" : "Video Characteristics",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Video format",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Broadcast standard",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "347" : {
		    "desc" : "Digital File Characteristics",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "File type",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Encoding format",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "File size",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Resolution",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Regional encoding",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Transmission speed",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "351" : {
		    "desc" : "Organization and Arrangement of Materials",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Organization",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Arrangement",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Hierarchical level",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "352" : {
		    "desc" : "Digital Graphic Representation",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Direct reference method",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Object type",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Object count",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Row count",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Column count",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Vertical count",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "VPF topology level",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Indirect reference description",
		        "repeatable" : false
		      },
		      "q" : {
		        "desc" : "Format of the digital image",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "355" : {
		    "desc" : "Security Classification Control",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Document",
		      "1" : "Title",
		      "2" : "Abstract",
		      "3" : "Contents note",
		      "4" : "Author",
		      "5" : "Record",
		      "8" : "None of the above"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Security classification",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Handling instructions",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "External dissemination information",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Downgrading or declassification event",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Classification system",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Country of origin code",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Downgrading date",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Declassification date",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Authorization",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "357" : {
		    "desc" : "Originator Dissemination Control",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Originator control term",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Originating agency",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Authorized recipients of material",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Other restrictions",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "362" : {
		    "desc" : "Dates of Publication and/or Sequential Designation",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Formatted style",
		      "1" : "Unformatted note"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Dates of publication and/or sequential designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "Source of information",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "363" : {
		    "desc" : "Normalized Date and Sequential Designation",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Starting information",
		      "1" : "Ending information"
		    },
		    "ind2" : {
		      "#" : "Not specified",
		      "0" : "Closed The sequence of the publication has terminated and is no longer being issued.",
		      "1" : "Open The sequence of the publication continues to be issued."
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "First level of enumeration",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Second level of enumeration",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Third level of enumeration",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Fourth level of enumeration",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Fifth level of enumeration",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Sixth level of enumeration",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Alternative numbering scheme, first level of enumeration",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Alternative numbering scheme, second level of enumeration",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "First level of chronology",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Second level of chronology",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Third level of chronology",
		        "repeatable" : false
		      },
		      "l" : {
		        "desc" : "Fourth level of chronology",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Alternative numbering scheme, chronology",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "First level textual designation",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "First level of chronology, issuance",
		        "repeatable" : false
		      },
		      "x" : {
		        "desc" : "Nonpublic note",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Public note",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : false
		      }
		    }
		  },
		  "365" : {
		    "desc" : "Trade Price",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Price type code",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Price amount",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Currency code",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Unit of pricing",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Price note",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Price effective from",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Price effective until",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Tax rate 1",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Tax rate 2",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "ISO country code",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "MARC country code",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Identification of pricing entity",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Source of price type code",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "366" : {
		    "desc" : "Trade Availability Information",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Publishers' compressed title identification",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Detailed date of publication",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Availability status code",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Expected next availability date",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Note",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Publisher's discount category",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Date made out of print",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "ISO country code",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "MARC country code",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Identification of agency",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Source of availability status code",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "377" : {
		    "desc" : "Associated Language",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "MARC language code Code from: MARC Code List for Languages.",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Language code",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language term",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "380" : {
		    "desc" : "Form of Work",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Form of work",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of term",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "381" : {
		    "desc" : "Other Distinguishing Characteristics of Work or Expression",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Other distinguishing characteristic",
		        "repeatable" : true
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "v" : {
		        "desc" : "Source of information",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of term",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "382" : {
		    "desc" : "Medium of Performance",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Medium of performance",
		      "1" : "Partial medium of performance"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "0" : "Not intended for access",
		      "1" : "Intended for access"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Medium of performance",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Soloist",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Doubling instrument",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of performers of the same medium",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Alternative medium of performance",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Total number of performers",
		        "repeatable" : true
		      },
		      "v" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of term",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "383" : {
		    "desc" : "Numeric Designation of Musical Work",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Serial number",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Opus number",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Thematic index number",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Thematic index code",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Publisher associated with opus number",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "384" : {
		    "desc" : "Key",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Relationship to original unknown",
		      "0" : "Original key",
		      "1" : "Transposed key"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Key",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "385" : {
		    "desc" : "Audience Characteristics",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Audience term",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Audience code",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Demographic group term",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Demographic group code",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "386" : {
		    "desc" : "Creator/Contributor Characteristics",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Creator/contributor term",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Creator/contributor code",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Demographic group term",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Demographic group code",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "440" : {
		    "desc" : "Series Statement/Added Entry-Title",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "0" : "No nonfiling characters",
		      "1-9" : "Number of nonfiling characters"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "v" : {
		        "desc" : "Volume/sequential designation",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Bibliographic record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "490" : {
		    "desc" : "Series Statement",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Series not traced",
		      "1" : "Series traced"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Series statement",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Library of Congress call number",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Volume/sequential designation",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "500" : {
		    "desc" : "General Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "General note",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "501" : {
		    "desc" : "With Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "With note",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "502" : {
		    "desc" : "Dissertation Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Dissertation note",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Degree type",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Name of granting institution",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Year degree granted",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Dissertation identifier",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "504" : {
		    "desc" : "Bibliography, Etc. Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Bibliography, etc. note",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Number of references",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "505" : {
		    "desc" : "Formatted Contents Note",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Contents",
		      "1" : "Incomplete contents",
		      "2" : "Partial contents",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Basic",
		      "0" : "Enhanced"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Formatted contents note",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Statement of responsibility",
		        "repeatable" : true
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : true
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "506" : {
		    "desc" : "Restrictions on Access Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "No restrictions",
		      "1" : "Restrictions apply"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Terms governing access",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Jurisdiction",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Physical access provisions",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Authorized users",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Authorization",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Standardized terminology for access restriction",
		        "repeatable" : true
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "507" : {
		    "desc" : "Scale Note for Graphic Material",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Representative fraction of scale note",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Remainder of scale note",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "508" : {
		    "desc" : "Creation/Production Credits Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Creation/production credits note",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "510" : {
		    "desc" : "Citation/References Note",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Coverage unknown",
		      "1" : "Coverage complete",
		      "2" : "Coverage is selective",
		      "3" : "Location in source not given",
		      "4" : "Location in source given"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Name of source",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Coverage of source",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Location within source",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "511" : {
		    "desc" : "Participant or Performer Note",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "No display constant generated",
		      "1" : "Cast"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Participant or performer note",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "513" : {
		    "desc" : "Type of Report and Period Covered Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Type of report",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Period covered",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "514" : {
		    "desc" : "Data Quality Note",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Attribute accuracy report",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Attribute accuracy value",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Attribute accuracy explanation",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Logical consistency report",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Completeness report",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Horizontal position accuracy report",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Horizontal position accuracy value",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Horizontal position accuracy explanation",
		        "repeatable" : true
		      },
		      "i" : {
		        "desc" : "Vertical positional accuracy report",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Vertical positional accuracy value",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Vertical positional accuracy explanation",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Cloud cover",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Display note",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "515" : {
		    "desc" : "Numbering Peculiarities Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Numbering peculiarities note",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "516" : {
		    "desc" : "Type of Computer File or Data Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Type of file",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Type of computer file or data note",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "518" : {
		    "desc" : "Date/Time and Place of an Event Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Date/time and place of an event note",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of event",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other event information",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Place of event",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of term",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "520" : {
		    "desc" : "Summary, Etc.",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Summary",
		      "0" : "Subject",
		      "1" : "Review",
		      "2" : "Scope and content",
		      "3" : "Abstract",
		      "4" : "Content advice",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Summary, etc.",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Expansion of summary note",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Assigning source",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "521" : {
		    "desc" : "Target Audience Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Audience",
		      "0" : "Reading grade level",
		      "1" : "Interest age level",
		      "2" : "Interest grade level",
		      "3" : "Special audience characteristics",
		      "4" : "Motivation/interest level",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Target audience note",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Source",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "522" : {
		    "desc" : "Geographic Coverage Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Geographic coverage",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Geographic coverage note",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "524" : {
		    "desc" : "Preferred Citation of Described Materials Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Cite as",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Preferred citation of described materials note",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Source of schema used",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "525" : {
		    "desc" : "Supplement Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Supplement note",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "526" : {
		    "desc" : "Study Program Information Note",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Reading program",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Program name",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Interest level",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Reading level",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Title point value",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Display text",
		        "repeatable" : false
		      },
		      "x" : {
		        "desc" : "Nonpublic note",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Public note",
		        "repeatable" : true
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "530" : {
		    "desc" : "Additional Physical Form Available Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Additional physical form available note",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Availability source",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Availability conditions",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Order number",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "533" : {
		    "desc" : "Reproduction Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Type of reproduction",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Place of reproduction",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Agency responsible for reproduction",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Date of reproduction",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Physical description of reproduction",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Series statement of reproduction",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Dates and/or sequential designation of issues reproduced",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Note about reproduction",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Fixed-length data elements of reproduction",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "534" : {
		    "desc" : "Original Version Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry of original",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition statement of original",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Publication, distribution, etc. of original",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Physical description, etc. of original",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Series statement of original",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Key title of original",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Location of original",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Material specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note about original",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other resource identifier",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Introductory phrase",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title statement of original",
		        "repeatable" : false
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "535" : {
		    "desc" : "Location of Originals/Duplicates Note",
		    "repeatable" : true,
		    "ind1" : {
		      "1" : "Holder of originals",
		      "2" : "Holder of duplicates"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Custodian",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Postal address",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Country",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Telecommunications address",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Repository location code",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "536" : {
		    "desc" : "Funding Information Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Text of note",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Contract number",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Grant number",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Undifferentiated number",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Program element number",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Project number",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Task number",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Work unit number",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "538" : {
		    "desc" : "System Details Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "System details note",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Display text",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "540" : {
		    "desc" : "Terms Governing Use and Reproduction Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Terms governing use and reproduction",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Jurisdiction",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Authorization",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Authorized users",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "541" : {
		    "desc" : "Immediate Source of Acquisition Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Private",
		      "1" : "Not private"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Source of acquisition",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Address",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Method of acquisition",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of acquisition",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Accession number",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Owner",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Purchase price",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Extent",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Type of unit",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "542" : {
		    "desc" : "Information Relating to Copyright Status",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Private",
		      "1" : "Not private"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Personal creator",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Personal creator death date",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Corporate creator",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Copyright holder",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Copyright holder contact information",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Copyright statement",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Copyright date",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Copyright renewal date",
		        "repeatable" : true
		      },
		      "i" : {
		        "desc" : "Publication date",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Creation date",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Publisher",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Copyright status",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Publication status",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Research date",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Country of publication or creation",
		        "repeatable" : true
		      },
		      "q" : {
		        "desc" : "Supplying agency",
		        "repeatable" : false
		      },
		      "r" : {
		        "desc" : "Jurisdiction of copyright assessment",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Source of information",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "544" : {
		    "desc" : "Location of Other Archival Materials Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Associated materials",
		      "1" : "Related materials"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Custodian",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Address",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Country",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Title",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Provenance",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "545" : {
		    "desc" : "Biographical or Historical Data",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Biographical sketch",
		      "1" : "Administrative history"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Biographical or historical data",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Expansion",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "546" : {
		    "desc" : "Language Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Language note",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Information code or alphabet",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "547" : {
		    "desc" : "Former Title Complexity Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Former title complexity note",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "550" : {
		    "desc" : "Issuing Body Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Issuing body note",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "552" : {
		    "desc" : "Entity and Attribute Information Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Entity type label",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Entity type definition and source",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Attribute label",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Attribute definition and source",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Enumerated domain value",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Enumerated domain value definition and source",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Range domain minimum and maximum",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Codeset name and source",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Unrepresentable domain",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Attribute units of measurement and resolution",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Beginning and ending date of attribute values",
		        "repeatable" : false
		      },
		      "l" : {
		        "desc" : "Attribute value accuracy",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Attribute value accuracy explanation",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Attribute measurement frequency",
		        "repeatable" : false
		      },
		      "o" : {
		        "desc" : "Entity and attribute overview",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Entity and attribute detail citation",
		        "repeatable" : true
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Display note",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "555" : {
		    "desc" : "Cumulative Index/Finding Aids Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Indexes Used to generate the display constant Indexes:.",
		      "0" : "Finding aids Used to generate the display constant Finding aids:.",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Cumulative index/finding aids note",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Availability source",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Degree of control",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Bibliographic reference",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "556" : {
		    "desc" : "Information About Documentation Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Documentation",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Information about documentation note",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "561" : {
		    "desc" : "Ownership and Custodial History",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Private",
		      "1" : "Not private"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "History",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "562" : {
		    "desc" : "Copy and Version Identification Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Identifying markings",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Copy identification",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Version identification",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Presentation format",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Number of copies",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "563" : {
		    "desc" : "Binding Information",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Binding note",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "565" : {
		    "desc" : "Case File Characteristics Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "File size",
		      "0" : "Case file characteristics",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Number of cases/variables",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Name of variable",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Unit of analysis",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Universe of data",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Filing scheme or code",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "567" : {
		    "desc" : "Methodology Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Methodology",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Methodology note",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "580" : {
		    "desc" : "Linking Entry Complexity Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Linking entry complexity note",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "581" : {
		    "desc" : "Publications About Described Materials Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Publications",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Publications about described materials note",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "583" : {
		    "desc" : "Action Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Private",
		      "1" : "Not private"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Action",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Action identification",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Time/date of action",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Action interval",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Contingency for action",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Authorization",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Jurisdiction",
		        "repeatable" : true
		      },
		      "i" : {
		        "desc" : "Method of action",
		        "repeatable" : true
		      },
		      "j" : {
		        "desc" : "Site of action",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Action agent",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Status",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Extent",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Type of unit",
		        "repeatable" : true
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "Nonpublic note",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Public note",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "584" : {
		    "desc" : "Accumulation and Frequency of Use Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Accumulation",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Frequency of use",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "585" : {
		    "desc" : "Exhibitions Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Exhibitions note",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "586" : {
		    "desc" : "Awards Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Awards",
		      "8" : "No display constant generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Awards note",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "588" : {
		    "desc" : "Source of Description Note",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Source of description note",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "600" : {
		    "desc" : "Subject Added Entry-Personal Name",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Forename",
		      "1" : "Surname",
		      "3" : "Family name"
		    },
		    "ind2" : {
		      "0" : "Library of Congress Subject Headings",
		      "1" : "LC subject headings for children's literature",
		      "2" : "Medical Subject Headings",
		      "3" : "National Agricultural Library subject authority file",
		      "4" : "Source not specified",
		      "5" : "Canadian Subject Headings",
		      "6" : "Répertoire de vedettes-matière",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Personal name",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Numeration",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Titles and other words associated with a name",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Dates associated with a name",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Attribution qualifier",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "q" : {
		        "desc" : "Fuller form of name",
		        "repeatable" : false
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Form subdivision",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "General subdivision",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Chronological subdivision",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Geographic subdivision",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of heading or term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "610" : {
		    "desc" : "Subject Added Entry-Corporate Name",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Inverted name",
		      "1" : "Jurisdiction name",
		      "2" : "Name in direct order"
		    },
		    "ind2" : {
		      "0" : "Library of Congress Subject Headings",
		      "1" : "LC subject headings for children's literature",
		      "2" : "Medical Subject Headings",
		      "3" : "National Agricultural Library subject authority file",
		      "4" : "Source not specified",
		      "5" : "Canadian Subject Headings",
		      "6" : "Répertoire de vedettes-matière",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Corporate name or jurisdiction name as entry element",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Subordinate unit",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Location of meeting",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of meeting or treaty signing",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section/meeting",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Form subdivision",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "General subdivision",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Chronological subdivision",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Geographic subdivision",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of heading or term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "611" : {
		    "desc" : "Subject Added Entry-Meeting Name",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Inverted name",
		      "1" : "Jurisdiction name",
		      "2" : "Name in direct order"
		    },
		    "ind2" : {
		      "0" : "Library of Congress Subject Headings",
		      "1" : "LC subject headings for children's literature",
		      "2" : "Medical Subject Headings",
		      "3" : "National Agricultural Library subject authority file",
		      "4" : "Source not specified",
		      "5" : "Canadian Subject Headings",
		      "6" : "Répertoire de vedettes-matière",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Meeting name or jurisdiction name as entry element",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Location of meeting",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of meeting",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Subordinate unit",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Number of part/section/meeting",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "q" : {
		        "desc" : "Name of meeting following jurisdiction name entry element",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Form subdivision",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "General subdivision",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Chronological subdivision",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Geographic subdivision",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of heading or term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "630" : {
		    "desc" : "Subject Added Entry-Uniform Title",
		    "repeatable" : true,
		    "ind1" : {
		      "0-9" : "Number of nonfiling characters"
		    },
		    "ind2" : {
		      "0" : "Library of Congress Subject Headings",
		      "1" : "LC subject headings for children's literature",
		      "2" : "Medical Subject Headings",
		      "3" : "National Agricultural Library subject authority file",
		      "4" : "Source not specified",
		      "5" : "Canadian Subject Headings",
		      "6" : "Répertoire de vedettes-matière",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of treaty signing",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Form subdivision",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "General subdivision",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Chronological subdivision",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Geographic subdivision",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of heading or term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "648" : {
		    "desc" : "Subject Added Entry-Chronological Term",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Date or time period covered or depicted",
		      "1" : "Date or time period of creation or origin"
		    },
		    "ind2" : {
		      "0" : "Library of Congress Subject Headings",
		      "1" : "LC subject headings for children's literature",
		      "2" : "Medical Subject Headings",
		      "3" : "National Agricultural Library subject authority file",
		      "4" : "Source not specified",
		      "5" : "Canadian Subject Headings",
		      "6" : "Répertoire de vedettes-matière",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Chronological term",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Form subdivision",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "General subdivision",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Chronological subdivision",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Geographic subdivision",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of heading or term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "650" : {
		    "desc" : "Subject Added Entry-Topical Term",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "No level specified",
		      "1" : "Primary",
		      "2" : "Secondary"
		    },
		    "ind2" : {
		      "0" : "Library of Congress Subject Headings",
		      "1" : "LC subject headings for children's literature",
		      "2" : "Medical Subject Headings",
		      "3" : "National Agricultural Library subject authority file",
		      "4" : "Source not specified",
		      "5" : "Canadian Subject Headings",
		      "6" : "Répertoire de vedettes-matière",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Topical term or geographic name entry element",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Topical term following geographic name entry element",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Location of event",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Active dates",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "v" : {
		        "desc" : "Form subdivision",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "General subdivision",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Chronological subdivision",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Geographic subdivision",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of heading or term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "651" : {
		    "desc" : "Subject Added Entry-Geographic Name",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "0" : "Library of Congress Subject Headings",
		      "1" : "LC subject headings for children's literature",
		      "2" : "Medical Subject Headings",
		      "3" : "National Agricultural Library subject authority file",
		      "4" : "Source not specified",
		      "5" : "Canadian Subject Headings",
		      "6" : "Répertoire de vedettes-matière",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Geographic name",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "v" : {
		        "desc" : "Form subdivision",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "General subdivision",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Chronological subdivision",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Geographic subdivision",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of heading or term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "653" : {
		    "desc" : "Index Term-Uncontrolled",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "No level specified",
		      "1" : "Primary",
		      "2" : "Secondary"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "0" : "Topical term",
		      "1" : "Personal name",
		      "2" : "Corporate name",
		      "3" : "Meeting name",
		      "4" : "Chronological term",
		      "5" : "Geographic name",
		      "6" : "Genre/form term"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Uncontrolled term",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "654" : {
		    "desc" : "Subject Added Entry-Faceted Topical Terms",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "No level specified",
		      "1" : "Primary",
		      "2" : "Secondary"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Focus term",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Non-focus term",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Facet/hierarchy designation",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "v" : {
		        "desc" : "Form subdivision",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Chronological subdivision",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Geographic subdivision",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of heading or term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "655" : {
		    "desc" : "Index Term-Genre/Form",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Basic",
		      "0" : "Faceted"
		    },
		    "ind2" : {
		      "0" : "Library of Congress Subject Headings",
		      "1" : "LC subject headings for children's literature",
		      "2" : "Medical Subject Headings",
		      "3" : "National Agricultural Library subject authority file",
		      "4" : "Source not specified",
		      "5" : "Canadian Subject Headings",
		      "6" : "Répertoire de vedettes-matière",
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Genre/form data or focus term",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Non-focus term",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Facet/hierarchy designation",
		        "repeatable" : true
		      },
		      "v" : {
		        "desc" : "Form subdivision",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "General subdivision",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Chronological subdivision",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Geographic subdivision",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "656" : {
		    "desc" : "Index Term-Occupation",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Occupation",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Form",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Form subdivision",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "General subdivision",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Chronological subdivision",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Geographic subdivision",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "657" : {
		    "desc" : "Index Term-Function",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "7" : "Source specified in subfield $2"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Function",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Form subdivision",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "General subdivision",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Chronological subdivision",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Geographic subdivision",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "658" : {
		    "desc" : "Index Term-Curriculum Objective",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main curriculum objective",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Subordinate curriculum objective",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Curriculum code",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Correlation factor",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Source of term or code",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "662" : {
		    "desc" : "Subject Added Entry-Hierarchical Place Name",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Country or larger entity",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "First-order political jurisdiction",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Intermediate political jurisdiction",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "City",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "City subsection",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Other nonjurisdictional geographic region and feature",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Extraterrestrial area",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of heading or term",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "700" : {
		    "desc" : "Added Entry-Personal Name",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Forename",
		      "1" : "Surname",
		      "3" : "Family name"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "2" : "Analytical entry"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Personal name",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Numeration",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Titles and other words associated with a name",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Dates associated with a name",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "j" : {
		        "desc" : "Attribution qualifier",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "q" : {
		        "desc" : "Fuller form of name",
		        "repeatable" : false
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "710" : {
		    "desc" : "Added Entry-Corporate Name",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Inverted name",
		      "1" : "Jurisdiction name",
		      "2" : "Name in direct order"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "2" : "Analytical entry"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Corporate name or jurisdiction name as entry element",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Subordinate unit",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Location of meeting",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of meeting or treaty signing",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section/meeting",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "711" : {
		    "desc" : "Added Entry-Meeting Name",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Inverted name",
		      "1" : "Jurisdiction name",
		      "2" : "Name in direct order"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "2" : "Analytical entry"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Meeting name or jurisdiction name as entry element",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Location of meeting",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of meeting",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Subordinate unit",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "j" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Number of part/section/meeting",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "q" : {
		        "desc" : "Name of meeting following jurisdiction name entry element",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "720" : {
		    "desc" : "Added Entry-Uncontrolled Name",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Not specified",
		      "1" : "Personal",
		      "2" : "Other"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Name",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "730" : {
		    "desc" : "Added Entry-Uniform Title",
		    "repeatable" : true,
		    "ind1" : {
		      "0-9" : "Number of nonfiling characters"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "2" : "Analytical entry"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of treaty signing",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "740" : {
		    "desc" : "Added Entry-Uncontrolled Related/Analytical Title",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "No nonfiling characters",
		      "1-9" : "Number of nonfiling characters"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "2" : "Analytical entry"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Uncontrolled related/analytical title",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "751" : {
		    "desc" : "Added Entry-Geographic Name",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Geographic name",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of heading or term",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "752" : {
		    "desc" : "Added Entry-Hierarchical Place Name",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Country or larger entity",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "First-order political jurisdiction",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Intermediate political jurisdiction",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "City",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "City subsection",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Other nonjurisdictional geographic region and feature",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Extraterrestrial area",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of heading or term",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "753" : {
		    "desc" : "System Details Access to Computer Files",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Make and model of machine",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Programming language",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Operating system",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "754" : {
		    "desc" : "Added Entry-Taxonomic Identification",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Taxonomic name",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Taxonomic category",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Common or alternative name",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "Non-public note",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Public note",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of taxonomic identification",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "760" : {
		    "desc" : "Main Series Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Main series",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relationship code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "762" : {
		    "desc" : "Subseries Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Has subseries",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relationship code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "765" : {
		    "desc" : "Original Language Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Translation of",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relationship code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "767" : {
		    "desc" : "Translation Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Translated as",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relationship code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "770" : {
		    "desc" : "Supplement/Special Issue Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Has supplement",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relationshop code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "772" : {
		    "desc" : "Supplement Parent Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Supplement to",
		      "0" : "Parent",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relationship code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "773" : {
		    "desc" : "Host Item Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "In",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Abbreviated title",
		        "repeatable" : false
		      },
		      "q" : {
		        "desc" : "Enumeration and first page",
		        "repeatable" : false
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relationship code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "774" : {
		    "desc" : "Constituent Unit Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Constituent unit",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relationship code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "775" : {
		    "desc" : "Other Edition Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Other edition available",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Language code",
		        "repeatable" : false
		      },
		      "f" : {
		        "desc" : "Country code",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relationshop code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "776" : {
		    "desc" : "Additional Physical Form Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Available in another form",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relationshop code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "777" : {
		    "desc" : "Issued With Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Issued with",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relationshop code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "780" : {
		    "desc" : "Preceding Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "0" : "Continues",
		      "1" : "Continues in part",
		      "2" : "Supersedes",
		      "3" : "Supersedes in part",
		      "4" : "Formed by the union of ... and ...",
		      "5" : "Absorbed",
		      "6" : "Absorbed in part",
		      "7" : "Separated from"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relationshop code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "785" : {
		    "desc" : "Succeeding Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "0" : "Continued by",
		      "1" : "Continued in part by",
		      "2" : "Superseded by",
		      "3" : "Superseded in part by",
		      "4" : "Absorbed by",
		      "5" : "Absorbed in part by",
		      "6" : "Split into ... and ...",
		      "7" : "Merged with ... to form ...",
		      "8" : "Changed back to"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relationshop code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "786" : {
		    "desc" : "Data Source Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Data source",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "j" : {
		        "desc" : "Period of content",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Abbreviated title",
		        "repeatable" : false
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Source Contribution",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relationshop code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "787" : {
		    "desc" : "Other Relationship Entry",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Display note",
		      "1" : "Do not display note Textual note is contained in field 580 (Linking Entry Complexity Note)."
		    },
		    "ind2" : {
		      "#" : "Related item",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Main entry heading",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Edition",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Qualifying information",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Place, publisher, and date of publication",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Related parts",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Physical description",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Relationship information",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Series data for related item",
		        "repeatable" : true
		      },
		      "m" : {
		        "desc" : "Material-specific details",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Note",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Other item identifier",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Report number",
		        "repeatable" : true
		      },
		      "s" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Standard Technical Report Number",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "y" : {
		        "desc" : "CODEN designation",
		        "repeatable" : false
		      },
		      "z" : {
		        "desc" : "International Standard Book Number",
		        "repeatable" : true
		      },
		      "4" : {
		        "desc" : "Relationshop code",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "7" : {
		        "desc" : "Control subfield",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "800" : {
		    "desc" : "Series Added Entry-Personal Name",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Forename",
		      "1" : "Surname",
		      "3" : "Family name"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Personal name",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Numeration",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Titles and other words associated with a name",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Dates associated with a name",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Attribution qualifier",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "q" : {
		        "desc" : "Fuller form of name",
		        "repeatable" : false
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Volume/sequential designation",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Bibliographic record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "810" : {
		    "desc" : "Series Added Entry-Corporate Name",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Inverted name",
		      "1" : "Jurisdiction name",
		      "2" : "Name in direct order"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Corporate name or jurisdiction name as entry element",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Subordinate unit",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Location of meeting",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of meeting or treaty signing",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section/meeting",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Volume/sequential designation",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Bibliographic record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "811" : {
		    "desc" : "Series Added Entry-Meeting Name",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Inverted name",
		      "1" : "Jurisdiction name",
		      "2" : "Name in direct order"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Meeting name or jurisdiction name as entry element",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Location of meeting",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of meeting",
		        "repeatable" : false
		      },
		      "e" : {
		        "desc" : "Subordinate unit",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "j" : {
		        "desc" : "Relator term",
		        "repeatable" : true
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "n" : {
		        "desc" : "Number of part/section/meeting",
		        "repeatable" : true
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "q" : {
		        "desc" : "Name of meeting following jurisdiction name entry element",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Affiliation",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Volume/sequential designation",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Bibliographic record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "4" : {
		        "desc" : "Relator code",
		        "repeatable" : true
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "830" : {
		    "desc" : "Series Added Entry-Uniform Title",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "0" : "No nonfiling characters",
		      "1-9" : "Number of nonfiling characters"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Uniform title",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Date of treaty signing",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Date of a work",
		        "repeatable" : false
		      },
		      "g" : {
		        "desc" : "Miscellaneous information",
		        "repeatable" : false
		      },
		      "h" : {
		        "desc" : "Medium",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Form subheading",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Language of a work",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Medium of performance for music",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Number of part/section of a work",
		        "repeatable" : true
		      },
		      "o" : {
		        "desc" : "Arranged statement for music",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Name of part/section of a work",
		        "repeatable" : true
		      },
		      "r" : {
		        "desc" : "Key for music",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Version",
		        "repeatable" : false
		      },
		      "t" : {
		        "desc" : "Title of a work",
		        "repeatable" : false
		      },
		      "v" : {
		        "desc" : "Volume/sequential designation",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Bibliographic record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "International Standard Serial Number",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "Authority record control number",
		        "repeatable" : true
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "5" : {
		        "desc" : "Institution to which field applies",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "841" : {
			"desc" : "Holdings Coded Data Values", 
			"repeatable" : false
		  },
		  "842" : {
		  	"desc" : "Textual Physical Form Designator", 
		  	"repeatable" : false
		  },
		  "843" : {
		  	"desc" : "Reproduction Note", 
		  	"repeatable" : true
		  },
		  "844" : {
		  	"desc" : "Name of Unit", 
		  	"repeatable" : false
		  },
		  "845" : {
		  	"desc" : "Terms Governing Use and Reproduction", 
		  	"repeatable" : true
		  },
		  "850" : {
		    "desc" : "Holding Institution",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Holding institution",
		        "repeatable" : true
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "852" : {
		    "desc" : "Location",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Library of Congress classification",
		      "1" : "Dewey Decimal classification",
		      "2" : "National Library of Medicine classification",
		      "3" : "Superintendent of Documents classification",
		      "4" : "Shelving control number",
		      "5" : "Title",
		      "6" : "Shelved separately",
		      "7" : "Source specified in subfield $2",
		      "8" : "Other scheme"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "0" : "Not enumeration",
		      "1" : "Primary enumeration",
		      "2" : "Alternative enumeration"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Location",
		        "repeatable" : false
		      },
		      "b" : {
		        "desc" : "Sublocation or collection",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Shelving location",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Former shelving location",
		        "repeatable" : true
		      },
		      "e" : {
		        "desc" : "Address",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Coded location qualifier",
		        "repeatable" : true
		      },
		      "g" : {
		        "desc" : "Non-coded location qualifier",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Classification part",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Item part",
		        "repeatable" : true
		      },
		      "j" : {
		        "desc" : "Shelving control number",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Call number prefix",
		        "repeatable" : true
		      },
		      "l" : {
		        "desc" : "Shelving form of title",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Call number suffix",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Country code",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Piece designation",
		        "repeatable" : false
		      },
		      "q" : {
		        "desc" : "Piece physical condition",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "Copyright article-fee code",
		        "repeatable" : true
		      },
		      "t" : {
		        "desc" : "Copy number",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "Nonpublic note",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Public note",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Source of classification or shelving scheme",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Sequence number",
		        "repeatable" : false
		      }
		    }
		  },
		  "853" : {
		  	"desc" : "Captions and Pattern - Basic Bibliographic Unit", 
		  	"repeatable" : true
		  },
		  "854" : {
		  	"desc" : "Captions and Pattern - Supplementary Material", 
		  	"repeatable" : true
		  },
		  "855" : {
		  	"desc" : "Captions and Pattern - Indexes", 
		  	"repeatable" : true
		  },
		  "856" : {
		    "desc" : "Electronic Location and Access",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided",
		      "0" : "Email",
		      "1" : "FTP",
		      "2" : "Remote login (Telnet)",
		      "3" : "Dial-up",
		      "4" : "HTTP",
		      "7" : "Method specified in subfield $2"
		    },
		    "ind2" : {
		      "#" : "No information provided",
		      "0" : "Resource",
		      "1" : "Version of resource",
		      "2" : "Related resource",
		      "8" : "No display constant generated"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Host name",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Access number",
		        "repeatable" : true
		      },
		      "c" : {
		        "desc" : "Compression information",
		        "repeatable" : true
		      },
		      "d" : {
		        "desc" : "Path",
		        "repeatable" : true
		      },
		      "f" : {
		        "desc" : "Electronic name",
		        "repeatable" : true
		      },
		      "h" : {
		        "desc" : "Processor of request",
		        "repeatable" : false
		      },
		      "i" : {
		        "desc" : "Instruction",
		        "repeatable" : true
		      },
		      "j" : {
		        "desc" : "Bits per second",
		        "repeatable" : false
		      },
		      "k" : {
		        "desc" : "Password",
		        "repeatable" : false
		      },
		      "l" : {
		        "desc" : "Logon",
		        "repeatable" : false
		      },
		      "m" : {
		        "desc" : "Contact for access assistance",
		        "repeatable" : true
		      },
		      "n" : {
		        "desc" : "Name of location of host",
		        "repeatable" : false
		      },
		      "o" : {
		        "desc" : "Operating system",
		        "repeatable" : false
		      },
		      "p" : {
		        "desc" : "Port",
		        "repeatable" : false
		      },
		      "q" : {
		        "desc" : "Electronic format type",
		        "repeatable" : false
		      },
		      "r" : {
		        "desc" : "Settings",
		        "repeatable" : false
		      },
		      "s" : {
		        "desc" : "File size",
		        "repeatable" : true
		      },
		      "t" : {
		        "desc" : "Terminal emulation",
		        "repeatable" : true
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : true
		      },
		      "v" : {
		        "desc" : "Hours access method available",
		        "repeatable" : true
		      },
		      "w" : {
		        "desc" : "Record control number",
		        "repeatable" : true
		      },
		      "x" : {
		        "desc" : "Nonpublic note",
		        "repeatable" : true
		      },
		      "y" : {
		        "desc" : "Link text",
		        "repeatable" : true
		      },
		      "z" : {
		        "desc" : "Public note",
		        "repeatable" : true
		      },
		      "2" : {
		        "desc" : "Access method",
		        "repeatable" : false
		      },
		      "3" : {
		        "desc" : "Materials specified",
		        "repeatable" : false
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "880" : {
		    "desc" : "Alternate Graphic Representation",
		    "repeatable" : true,
		    "ind1" : {
		    },
		    "ind2" : {
		    },
		    "subf" : {
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      }
		    }
		  },
		  "863" : {
		  	"desc" : "Enumeration and Chronology - Basic Bibliographic Unit", 
		  	"repeatable" : true
		  },
		  "864" : {
		  	"desc" : "Enumeration and Chronology - Supplementary Material", 
		  	"repeatable" : true
		  },
		  "865" : {
		  	"desc" : "Enumeration and Chronology - Indexes", 
		  	"repeatable" : true
		  },
		  "866" : {
		  	"desc" : "Textual Holdings - Basic Bibliographic Unit", 
		  	"repeatable" : true
		  },
		  "867" : {
		  	"desc" : "Textual Holdings - Supplementary Material", 
		  	"repeatable" : true
		  },
		  "868" : {
		  	"desc" : "Textual Holdings - Indexes", 
		  	"repeatable" : true
		  },
		  "876" : {
		  	"desc" : "Item Information - Basic Bibliographic Unit", 
		  	"repeatable" : true
		  },
		  "877" : {
		  	"desc" : "Item Information - Supplementary Material", 
		  	"repeatable" : true
		  },
		  "878" : {
		  	"desc" : "Item Information - Indexes", 
		  	"repeatable" : true
		  },
		  "882" : {
		    "desc" : "Replacement Record Information",
		    "repeatable" : false,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Replacement title",
		        "repeatable" : true
		      },
		      "i" : {
		        "desc" : "Explanatory text",
		        "repeatable" : true
		      },
		      "w" : {
		        "desc" : "Replacement bibliographic record control number",
		        "repeatable" : true
		      },
		      "6" : {
		        "desc" : "Linkage",
		        "repeatable" : false
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "883" : {
		    "desc" : "Machine-generated Metadata Provenance",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "No information provided/not applicable",
		      "0" : "Fully machine-generated",
		      "1" : "Partially machine-generated"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Generation process",
		        "repeatable" : false
		      },
		      "c" : {
		        "desc" : "Confidence value",
		        "repeatable" : false
		      },
		      "d" : {
		        "desc" : "Generation date",
		        "repeatable" : false
		      },
		      "q" : {
		        "desc" : "Generation agency",
		        "repeatable" : false
		      },
		      "x" : {
		        "desc" : "Validity end date",
		        "repeatable" : false
		      },
		      "u" : {
		        "desc" : "Uniform Resource Identifier",
		        "repeatable" : false
		      },
		      "w" : {
		        "desc" : "Bibliographic record control number",
		        "repeatable" : true
		      },
		      "0" : {
		        "desc" : "Authority record control number or standard number",
		        "repeatable" : true
		      },
		      "8" : {
		        "desc" : "Field link and sequence number",
		        "repeatable" : true
		      }
		    }
		  },
		  "886" : {
		    "desc" : "Foreign MARC Information Field",
		    "repeatable" : true,
		    "ind1" : {
		      "0" : "Leader",
		      "1" : "Variable control fields (002-009)",
		      "2" : "Variable data fields (010-999)"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "- Foreign MARC subfield",
		        "repeatable" : true
		      },
		      "b" : {
		        "desc" : "Content of the foreign MARC field",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Source of data",
		        "repeatable" : false
		      },
		      "0" : {
		        "desc" : "- Foreign MARC subfield",
		        "repeatable" : true
		      }
		    }
		  },
		  "887" : {
		    "desc" : "Non-MARC Information Field",
		    "repeatable" : true,
		    "ind1" : {
		      "#" : "Undefined"
		    },
		    "ind2" : {
		      "#" : "Undefined"
		    },
		    "subf" : {
		      "a" : {
		        "desc" : "Content of non-MARC field",
		        "repeatable" : false
		      },
		      "2" : {
		        "desc" : "Source of data",
		        "repeatable" : false
		      }
		    }
		  }
}