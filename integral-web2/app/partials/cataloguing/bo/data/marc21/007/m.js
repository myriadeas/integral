var _007m = {
    "0":{"start":0, "stop":0, "len":1, "desc":"Category of material", "type":"select", "defaultValue":"",
        "list":[ {"code":"m", "desc":"Motion picture"}]
    },
    "1":{"start":1, "stop":1, "len":1, "desc":"Specific material designation", "type":"select", "defaultValue":"",
        "list":[ {"code":"c", "desc":"Film cartridge"},
                {"code":"f", "desc":"Film cassette"},
                {"code":"o", "desc":"Film roll"},
                {"code":"r", "desc":"Film reel"},
                {"code":"u", "desc":"Unspecified"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "2":{"start":2, "stop":2, "len":1, "desc":"Undefined", "type":"auto", "defaultValue":" "
    },
    "3":{"start":3, "stop":3, "len":1, "desc":"Color", "type":"select", "defaultValue":"",
        "list":[ {"code":"b", "desc":"Black-and-white"},
                {"code":"c", "desc":"Multicolored"},
                {"code":"h", "desc":"Hand colored"},
                {"code":"m", "desc":"Mixed"},
                {"code":"n", "desc":"Not applicable"},
                {"code":"u", "desc":"Unknown"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "4":{"start":4, "stop":4, "len":1, "desc":"Motion picture presentation format", "type":"select", "defaultValue":"",
        "list":[ {"code":"a", "desc":"Standard sound aperture (reduced frame)"},
                {"code":"b", "desc":"Nonanamorphic (wide-screen)"},
                {"code":"c", "desc":"3D"},
                {"code":"d", "desc":"Anamorphic (wide-screen)"},
                {"code":"e", "desc":"Other wide-screen format"},
                {"code":"f", "desc":"Standard silent aperture (full frame)"},
                {"code":"u", "desc":"Unknown"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "5":{"start":5, "stop":5, "len":1, "desc":"Sound on medium or separate", "type":"select", "defaultValue":"",
        "list":[ {"code":"#", "desc":"No sound (silent)"},
                {"code":"a", "desc":"Sound on medium"},
                {"code":"b", "desc":"Sound separate from medium"},
                {"code":"u", "desc":"Unknown"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "6":{"start":6, "stop":6, "len":1, "desc":"Medium for sound", "type":"select", "defaultValue":"",
        "list":[ {"code":"#", "desc":"No sound (silent)"},
                {"code":"a", "desc":"Optical sound track on motion picture film"},
                {"code":"b", "desc":"Magnetic sound track on motion picture film"},
                {"code":"c", "desc":"Magnetic audio tape in cartridge"},
                {"code":"d", "desc":"Sound disc"},
                {"code":"e", "desc":"Magnetic audio tape on reel"},
                {"code":"f", "desc":"Magnetic audio tape in cassette"},
                {"code":"g", "desc":"Optical and magnetic sound track on motion picture film"},
                {"code":"h", "desc":"Videotape"},
                {"code":"i", "desc":"Videodisc"},
                {"code":"u", "desc":"Unknown"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "7":{"start":7, "stop":7, "len":1, "desc":"Dimensions", "type":"select", "defaultValue":"",
        "list":[ {"code":"a", "desc":"Standard 8 mm."},
                {"code":"b", "desc":"Super 8 mm./single 8 mm."},
                {"code":"c", "desc":"9.5 mm."},
                {"code":"d", "desc":"16 mm."},
                {"code":"e", "desc":"28 mm."},
                {"code":"f", "desc":"35 mm."},
                {"code":"g", "desc":"70 mm."},
                {"code":"u", "desc":"Unknown"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "8":{"start":8, "stop":8, "len":1, "desc":"Configuration of playback channels", "type":"select", "defaultValue":"",
        "list":[ {"code":"k", "desc":"Mixed"},
                {"code":"m", "desc":"Monaural"},
                {"code":"n", "desc":"Not applicable"},
                {"code":"q", "desc":"Quadraphonic, multichannel, or surround"},
                {"code":"s", "desc":"Stereophonic"},
                {"code":"u", "desc":"Unknown"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "9":{"start":9, "stop":9, "len":1, "desc":"Production elements", "type":"select", "defaultValue":"",
        "list":[ {"code":"a", "desc":"Workprint"},
                {"code":"b", "desc":"Trims"},
                {"code":"c", "desc":"Outtakes"},
                {"code":"d", "desc":"Rushes"},
                {"code":"e", "desc":"Mixing tracks"},
                {"code":"f", "desc":"Title bands/inter-title rolls"},
                {"code":"g", "desc":"Production rolls"},
                {"code":"n", "desc":"Not applicable"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "10":{"start":10, "stop":10, "len":1, "desc":"Positive/negative aspect", "type":"select", "defaultValue":"",
        "list":[ {"code":"a", "desc":"Positive"},
                {"code":"b", "desc":"Negative"},
                {"code":"n", "desc":"Not applicable"},
                {"code":"u", "desc":"Unknown"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "11":{"start":11, "stop":11, "len":1, "desc":"Generation", "type":"select", "defaultValue":"",
        "list":[ {"code":"d", "desc":"Duplicate"},
                {"code":"e", "desc":"Master"},
                {"code":"o", "desc":"Original"},
                {"code":"r", "desc":"Reference print/viewing copy"},
                {"code":"u", "desc":"Unknown"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "12":{"start":12, "stop":12, "len":1, "desc":"Base of film", "type":"select", "defaultValue":"",
        "list":[ {"code":"a", "desc":"Safety base, undetermined"},
                {"code":"c", "desc":"Safety base, acetate undetermined"},
                {"code":"d", "desc":"Safety base, diacetate"},
                {"code":"i", "desc":"Nitrate base"},
                {"code":"m", "desc":"Mixed base (nitrate and safety)"},
                {"code":"n", "desc":"Not applicable"},
                {"code":"p", "desc":"Safety base, polyester"},
                {"code":"r", "desc":"Safety base, mixed"},
                {"code":"t", "desc":"Safety base, triacetate"},
                {"code":"u", "desc":"Unknown"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "13":{"start":13, "stop":13, "len":1, "desc":"Refined categories of color", "type":"select", "defaultValue":"",
        "list":[ {"code":"a", "desc":"3 layer color"},
                {"code":"b", "desc":"2 color, single strip"},
                {"code":"c", "desc":"Undetermined 2 color"},
                {"code":"d", "desc":"Undetermined 3 color"},
                {"code":"e", "desc":"3 strip color"},
                {"code":"f", "desc":"2 strip color"},
                {"code":"g", "desc":"Red strip"},
                {"code":"h", "desc":"Blue or green strip"},
                {"code":"i", "desc":"Cyan strip"},
                {"code":"j", "desc":"Magenta strip"},
                {"code":"k", "desc":"Yellow strip"},
                {"code":"l", "desc":"S E N 2"},
                {"code":"m", "desc":"S E N 3"},
                {"code":"n", "desc":"Not applicable"},
                {"code":"p", "desc":"Sepia tone"},
                {"code":"q", "desc":"Other tone"},
                {"code":"r", "desc":"Tint"},
                {"code":"s", "desc":"Tinted and toned"},
                {"code":"t", "desc":"Stencil color"},
                {"code":"u", "desc":"Unknown"},
                {"code":"v", "desc":"Hand colored"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "14":{"start":14, "stop":14, "len":1, "desc":"Kind of color stock or print", "type":"select", "defaultValue":"",
        "list":[ {"code":"a", "desc":"Imbibition dye transfer prints"},
                {"code":"b", "desc":"Three-layer stock"},
                {"code":"c", "desc":"Three layer stock, low fade"},
                {"code":"d", "desc":"Duplitized stock"},
                {"code":"n", "desc":"Not applicable"},
                {"code":"u", "desc":"Unknown"},
                {"code":"z", "desc":"Other"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "15":{"start":15, "stop":15, "len":1, "desc":"Deterioration stage", "type":"select", "defaultValue":"",
        "list":[ {"code":"a", "desc":"None apparent"},
                {"code":"b", "desc":"Nitrate: suspicious odor"},
                {"code":"c", "desc":"Nitrate: pungent odor"},
                {"code":"d", "desc":"Nitrate: brownish, discoloration, fading, dusty"},
                {"code":"e", "desc":"Nitrate: sticky"},
                {"code":"f", "desc":"Nitrate: frothy, bubbles, blisters"},
                {"code":"g", "desc":"Nitrate: congealed"},
                {"code":"h", "desc":"Nitrate: powder"},
                {"code":"k", "desc":"Non-nitrate: detectable deterioration"},
                {"code":"l", "desc":"Non-nitrate: advanced deterioration"},
                {"code":"m", "desc":"Non-nitrate: disaster"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "16":{"start":16, "stop":16, "len":1, "desc":"Completeness", "type":"select", "defaultValue":"",
        "list":[ {"code":"c", "desc":"Complete"},
                {"code":"i", "desc":"Incomplete"},
                {"code":"n", "desc":"Not applicable"},
                {"code":"u", "desc":"Unknown"},
                {"code":"|", "desc":"No attempt to code"}]
    },
    "17":{"start":17, "stop":22, "len":6, "desc":"Film inspection date", "type":"auto", "defaultValue":""}
}