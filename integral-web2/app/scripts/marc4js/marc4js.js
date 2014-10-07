// Array Remove - By John Resig (MIT Licensed)
Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};

//pads left
String.prototype.lpad = function(padString, length) {
    var str = this;
    while (str.length < length)
        str = padString + str;
    return str;
}    
//pads right
String.prototype.rpad = function(padString, length) {
	var str = this;
    while (str.length < length)
        str = str + padString;
    return str;
}
String.prototype.replaceAllHashWithSpace = function() {
    var str = this;
    return str.replace(new RegExp('\#', 'g'), ' ');
}
String.prototype.replaceAllSpaceWithHash = function() {
    var str = this;
    return str.replace(new RegExp('\\s', 'g'), '#');
}
//TODO - should put under utiliy
if (typeof String.prototype.endsWith !== 'function') {
    String.prototype.endsWith = function(suffix) {
        return this.indexOf(suffix, this.length - suffix.length) !== -1;
    };
}

(function (global, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module.
    define(['tv4'], factory);
  } else if (typeof module !== 'undefined' && module.exports){
    // CommonJS. Define export.
    module.exports = factory();
  } else {
    // Browser globals
    global.marc4j = factory();
  }
    }(this, function (tv4) {
    
    
    function FixedLengthSchema(fields) {    
        this.fields = fields;
        this.fieldsMap;
        this.getObjects = function(data) {
            var objects = [];
            $.each(this.fields, function(index, field) {
                objects.push(field.getObject(data));
            });
            return objects
        }
        this.getValue = function(num, value) {
            var fieldsMap = this.fieldsMap;
            if(_.isUndefined(fieldsMap)) {
                fieldsMap = {};
                $.each(this.fields, function(index, field) {
                    fieldsMap[field.num] = field;
                });
                this.fieldsMap = fieldsMap;
            }
            if(_.isUndefined(fieldsMap[num])) {
                throw "Error to get fields map for num= " + num; 
            }
            return fieldsMap[num].getValue(value);
        }
        
        
    }

    function FixedLengthSchemaFactory() {
        this.createSchema = function(jsonSchema) {
            var fields = [];
            $.each(jsonSchema.properties, function(key, property) {
                var field = new FixedLengthField(parseInt(key), property.type, parseInt(property.maxLength), parseInt(property.minLength));
                fields.push(field);
            });
            return new FixedLengthSchema(fields);
        };
    }

    function FixedLengthField(num, type, length, dataLength) {
        this.num = num;
        this.type = type;
        this.length = length;
        this.dataLength = dataLength;
        
        this.getObject = function(data) {
            var stop = this.num + this.length;
            var start = stop - this.dataLength;
            return {
                "key": this.num,
                "value": data.substring(start, stop).replaceAllSpaceWithHash()
            }
        }
        
        this.getValue = function(value) {
            if(this.type == "string") {
                return _.isUndefined(value) ? "".lpad("#", this.length) : value.lpad("#", this.length);
            } else if(this.type == "integer"){
                return _.isUndefined(value)? "".rpad("0", this.length) : value.rpad("0", this.length);
            } else {
                throw this.type + " is not supported in fixed length field schema. Are you sure it is part of marc21 schema?"; 
            }
        }
    }

    function BaseObject() {
    }

    BaseObject.prototype.equals = function(obj) {
        var isEqual = true;
        $.each(this, function(property, value) {
            if(!_.isFunction(obj[property]) && property.indexOf("$") != 0) {
                isEqual &= _.isEqual(obj[property], value);
            }
        });
        return isEqual;
    }

    BaseObject.prototype.addProperty = function(property) {
        if(_.isUndefined(this[property.name])) {
            this[property.name] = new Array();
        }
        this[property.name].push(property);
    }

    BaseObject.prototype.removeProperty = function(property) {
        if(_.isUndefined(property)) {
            return;
        }
        if(!property instanceof BaseObject) {
            throw "This method only applicable for BaseObject type"; 
        }
        var propertyValue = _.reject(this[property.name], function(_property) {
            return _property.equals(property);
        });
        if(_.size(propertyValue) == 0) {
            delete this[property.name];
        } else {
            this[property.name] = propertyValue;
        }
    }

    BaseObject.prototype.getIndex = function(property) {
        var index = -1;
        if(_.isUndefined(property)) {
            return index;
        }
        $.each(this[property.name], function(key, _property) {
            if(_property.equals(property)) {
                index = key;
            }
        });
        return index;
    }

    BaseObject.prototype.isJSONProperty = function(propertyName, propertyValue) {
        return _.isArray(propertyValue);
    }

    BaseObject.prototype.getSortedProperty = function() {
        var obj = new Array();
        var baseObject = this;
        var propertyArray = new Array();
        $.each(this, function(propertyName, propertyValue) {
            if(baseObject.isJSONProperty(propertyName, propertyValue)) {
                $.each(propertyValue, function(key, _propertyValue) {
                    propertyArray.push(_propertyValue);
                });
            }
        });
        var sortedPropertyArray = _.sortBy(propertyArray, function(arrayChild) {
            return arrayChild.weight;
        });
        return sortedPropertyArray;
    }

    BaseObject.prototype.getSortedJSON = function() {
        var obj = new Array();
        /*
            "100" : [{"name" : "100", "data": "test", "weight": 200}],
            "200" : [{"name" : "200", "data": "test2001", "weight": 300},{"name" : "200", "data": "test2002", "weight": 100}]
        */
        /*  
            [{"200": "test2002}, {"100": "test"}, {"200": "test2001"}]
        
        */
        $.each(this.getSortedProperty(), function(key, arrayChild) {
            //Ignore leader - always at first position
            obj.push(arrayChild.getJSON());
            
        });
        
        return obj;
    }

    BaseObject.prototype.moveUp = function(property) {
        var baseObject = this;
        $.each(this.getSortedProperty(), function(key, _property) {
            if(_property.equals(property) && key > 0) {
                var previous = baseObject.getSortedProperty()[key-1];
                previous.weight = previous.weight + 100;
                _property.weight = _property.weight - 100;
            }
        });
        return this;
    }

    BaseObject.prototype.moveDown = function(property) {

        var baseObject = this;
        $.each(this.getSortedProperty(), function(key, _property) {
            if(_property.equals(property) && key < _.size(baseObject.getSortedProperty())) {
                var next = baseObject.getSortedProperty()[key+1];
                next.weight = next.weight - 100;
                _property.weight = _property.weight + 100;
            }
        });
        return this;
    }


    Subfields.prototype = new BaseObject();
    Subfields.prototype.constructor = Subfields;

    function Subfields() {
        var weight = 0;
        var subfields = this;
        this.addSubfield = function(code, data) {
            this.addProperty(new Subfield(code, data, weight+=100));
        }
            
        this.removeSubfield = function(subfield) {
            this.removeProperty(subfield);
        }
        
        this.getJSON = function() {
            return this.getSortedJSON();
        }
            
        this.update = function(szSubfields) {
            var aszSubfield = szSubfields.split("|");
            $.each(aszSubfield, function(key, _szSubfield) {
                if(_.size(_szSubfield) > 0) {
                    var code = _szSubfield.substring(0, 1);
                    var data = _szSubfield.substring(1);
                    subfields.addSubfield(code, data);
                }
            });
        }
        
        this.getData = function() {        
            var szSubfields = "";
            $.each(this.getSortedProperty(), function(key, arrayChild) {
                var subfield = "|" + arrayChild.name + arrayChild.data;
                szSubfields += subfield;
            });
            return szSubfields;
        }
    }

    Subfield.prototype = new BaseObject();
    Subfield.prototype.constructor = Subfield;
    function Subfield(name, data, weight) {
        this.name = name;
        this.data = data;
        this.weight = weight;
        
        this.getJSON = function() {
            var subfield = {};
            subfield[this.name] = this.data;    
            return subfield;
        }
    }

    DataField.prototype = new BaseObject();
    DataField.prototype.constructor = DataField;

    function DataField (name, ind1, ind2, weight, schema) {
        this.name = name;
        this.ind1 = ind1.replaceAllSpaceWithHash();
        this.ind2 = ind2.replaceAllSpaceWithHash();
        this.weight = weight;
        this.subfields = new Subfields();
        this.data;
        
        this.validate = function() {
            if(_.isUndefined(schema)) {
                return;
            }
            this.$errors = tv4.validateMultiple(this.getJSON(), schema, true, true);
        }
        
        this.getJSON = function() {
            var dataField = {};
            dataField[this.name] = {
                "subfields": this.subfields.getJSON(),
                "ind1": this.ind1.replaceAllHashWithSpace(),
                "ind2": this.ind2.replaceAllHashWithSpace()
            };
            return dataField;
        }
        
        this.getData = function() {
            var szData = new String(this.data);
            this.updateSubfields();
            if(!szData.endsWith("|")) {
                this.data = this.subfields.getData();
            }
            return this;
        }
        
        
        this.updateSubfields = function() {
            var szData = new String(this.data);
            if(_.size(this.data) > 0) {
                this.subfields = new Subfields();
                this.subfields.update(this.data);
            }
            return this;
        }
        
    }

    ControlField.prototype = new BaseObject();
    ControlField.prototype.constructor = ControlField;
    ControlField.prototype.addProperty = function(property) {
        this[property.name] = property.value;    
    }

    ControlField.prototype.reset = function() {
        var controlField = this;
        $.each(controlField, function(propertyName, propertyValue) {
            if(controlField.isControlFieldProperty(propertyName, propertyValue)) {
                delete controlField[propertyName];
            }
        });
        this.data = "";
    }
    function ControlField(name, data, weight, schema) {
        this.name = name;
        this.data = data;
        this.weight = weight;
        this.schema = schema;
        this.fixedLengthSchema;
        var controlField = this;
        
        this.clone = function() {
            return new ControlField(this.name, this.data, this.weight, this.schema);
        }
        
        this.getJSON = function() {
            var controlField = {};
            controlField[this.name] = this.getData().data;
            return controlField;
        }
        
        this.getData = function() {
            if(!_.isUndefined(this['ind1'])) {
                delete this['ind1'];
            }
            if(!_.isUndefined(this['ind2'])) {
                delete this['ind2'];
            }
            var viewData = this.getJoinPropertyData().length == 0 ? this.data : this.getJoinPropertyData();
            
            if(this.viewData != viewData) {
                this.viewData = viewData
            }
            this.data = this.viewData.replaceAllHashWithSpace();
            return this;
        }
        
        this.getType = function() {
            if(this.name == "leader") {
                return "leader";
            }
            if(this.name == "007") {
                return this.data.length == 0 ? 'a' : this.data.substring(0,1);
            }
            if(this.name == "006") {
                var maps = {
                    "a": "bk",
                    "c": "mu",
                    "d": "mu",
                    "e": "mp",
                    "f": "mp",
                    "g": "vm",
                    "i": "mu",
                    "j": "mu",
                    "k": "vm",
                    "m": "cf",
                    "o": "vm",
                    "p": "mx",
                    "r": "vm",
                    "s": "cr",
                    "t": "bk"
                }
                return this.data.length == 0 || angular.isUndefined(maps[this.data.substring(0,1)]) ? "bk" : maps[this.data.substring(0,1)];
            }
        }
        
        this.init = function(schema) {
            this.schema = schema;
            if(_.isUndefined(this.data)) {
                return;
            }
            if(_.isUndefined(schema)) {
                return;
            }
            this.fixedLengthSchema = new FixedLengthSchemaFactory().createSchema(schema);
            var Property = function(name, value) {
                this.name = name;
                this.value = value
            };
            var data = this.data;
            $.each(this.fixedLengthSchema.fields, function(index, fixedLengthField) {
                var property = new Property(fixedLengthField.getObject(data).key, fixedLengthField.getObject(data).value);
                controlField.addProperty(property);
            });
        }
        
        this.isControlFieldProperty = function(propertyName, propertyValue) {
            function isInt(value) {
                return (parseInt(value) % 1  === 0);
            }
            return _.size(propertyName) <= 2 && isInt(propertyName);
        }
        
        
        this.getJoinPropertyData = function() {
            var data = "";
            var fixedLengthSchema = this.fixedLengthSchema;
            $.each(this, function(propertyName, propertyValue) {
                if(controlField.isControlFieldProperty(propertyName, propertyValue)) {
                    data += fixedLengthSchema.getValue(propertyName, propertyValue);
                }
            });
            return data;
        }
        
        this.updateSubfields = function() {
        }
        
        this.validate = function() {
        }
        
        this.init(schema);
    }

    Record.prototype = new BaseObject();
    Record.prototype.constructor = Record;
    function Record(data, schema) {
        this.data = data;
        this.schema = schema;
        var record = this;
        var weight = 0;
        var controlField = ["001","003","005","006","007","008"];
        var id;
        var isNew;
        this.isControlField = function (fieldName) {
            return _.contains(controlField, fieldName);
        }
        
        this.addField = function(data) {
            this.addProperty(data);
            this.validate();
            if(this.isErrorAddedField(data)) {
                //this.removeField(data);
            }
        }
        
        this.removeField = function(data) {
            if(data.name == "leader") {
                return;
            }
            this.removeProperty(data);
            this.validate();
        }
        
        this.addMarcField = function(tag, currentTag) {
            var weight = this.getNextWeight(currentTag);
            function isRepeatableControlField(tag) {
                return(tag == "006" || tag == "007");
            }
            if(tag == "leader") {
            } else if(this.isControlField(tag)) {
                this.addControlField(tag, weight);
            } else {
                this.addDataField(tag, weight);
            }
        }
        this.getTag006 = function(weight) {
            return new ControlField('006', '', weight);
        }
        /*
        * refer to http://www.loc.gov/marc/bibliographic/bdleader.html section INPUT CONVENTIONS Dependencies
        */
        this.getTypeOfMaterial = function() {
            var leader06 = this.getMarcJSON().leader.substring(6,7);
            var leader07 = this.getMarcJSON().leader.substring(7,8);
            var typeOfMaterial = "";
            switch (leader06) {
                case 'a':
                    switch(leader07) {
                    case 'a':
                    case 'c':
                    case 'd':
                    case 'm':
                        typeOfMaterial = 'bk';
                        break;
                    case 'b':
                    case 'i':
                    case 's':
                        typeOfMaterial = 'cr';
                        break;
                    }
                    break;
                case 't':
                    typeOfMaterial = 'bk';
                    break;
                case 'c':
                case 'd':
                case 'i':
                case 'j':
                    typeOfMaterial = 'mu';
                    break;
                case 'e':
                case 'f':
                    typeOfMaterial = 'mp';
                    break;
                case 'g':
                case 'k':
                case 'o':
                case 'r':
                    typeOfMaterial = 'vm';
                    break;
                case 'm':
                    typeOfMaterial = 'cf';
                    break;
                case 'p':
                    typeOfMaterial = 'mx';
                    break;
            }
            return typeOfMaterial.length == 0 ? "bk" : typeOfMaterial;
        }
        
        
        this.addControlField = function(tag, weight) {
            var controlField;
            if(tag == "006") {
                controlField = this.getTag006(weight);
            } else if(tag == "007") {
                controlField = new ControlField(tag, "a", weight);
            }else {
                controlField = new ControlField(tag, "", weight);
            }
            this.addField(controlField);
        }
        
        
        this.addDataField = function(tag, weight) {
            if(_.isUndefined(this.schema.properties[tag])) {
                this.addField(new DataField(tag, "", "", weight));
                return;
            }
            var ind1 = this.schema.properties[tag].items.properties.ind1.enum.length > 0 ? this.schema.properties[tag].items.properties.ind1.enum[0] : "";
            var ind2 = this.schema.properties[tag].items.properties.ind2.enum.length > 0 ? this.schema.properties[tag].items.properties.ind2.enum[0] : "";
            var dataField = new DataField(tag, ind1, ind2, weight);
            this.addField(dataField);
        }
        
        this.getNextWeight = function(tag) {
            var nextTag = this.getNextTag(tag);
            function isLastTag() {
                return _.isUndefined(nextTag);
            }
            if(isLastTag()) {
                return weight += 100;
            } else {
                return (nextTag.weight + tag.weight) / 2;
            }
        }
        
        this.getNextTag = function(property) {
            var nextTag;
            var baseObject = this;
            if(property.name == "leader") {
                return this.getSortedProperty()[1];
            }
            
            $.each(this.getSortedProperty(), function(key, _property) {
                if(_property.equals(property) && key > 0) {
                    nextTag = baseObject.getSortedProperty()[key + 1];
                    return;
                }
            });
            return nextTag;
        }
        
        this.getId = function() {
            if(_.isUndefined(this.data.fields[0]["001"])) {
                return "";
            }
            return this.data.fields[0]["001"];
        }
        
        this.init = function() {
            if(_.isUndefined(this.data)) {
                //throw "You must defined json data."; 
                return ;
            }
            var schema = this.schema;
            //this.leader = this.data.leader;
            record.addField(new ControlField("leader", this.data.leader, weight));
            $.each(this.data.fields, function(key, field) {
                $.each(field, function(fieldName, fieldValue) {
                    var data;
                    if(record.isControlField(fieldName)) {
                        data = new ControlField(fieldName, fieldValue, weight+=100);
                    } else {
                        data = new DataField(fieldName, fieldValue.ind1, fieldValue.ind2, weight +=100, schema.properties[fieldName]);
                        $.each(fieldValue.subfields, function(key, subfield) {
                            $.each(subfield, function(subfieldName, subfieldValue) {
                                data.subfields.addSubfield(subfieldName, subfieldValue);
                            });
                        });
                    }
                    record.addField(data);
                });
            });
        }
        
        
        this.getData = function() {
            var tags = [];
            
            $.each(this.getSortedProperty(), function(key, arrayChild) {
                tags.push(arrayChild.getData());
            });
            
            return tags;
        }
        
        this.getJSON = function() {
            this.getData();//make sure binding is working TODO
            var szJsonRecord = JSON.stringify(this);
            var jsonRecord = JSON.parse(szJsonRecord.replaceAllHashWithSpace());
            delete jsonRecord.data;
            delete jsonRecord.schema;
            delete jsonRecord.$errors;
            return jsonRecord;
        }
        
        this.getMarcJSON = function() {
            this.getData();//make sure binding is working TODO
            function removeLeader(jsonFields) {
                jsonFields.remove(0);
                return jsonFields;
            }
            
            var jsonFields = this.getSortedJSON();
            var jsonRecord = {
                "leader" : this.data.leader,
                "fields" : removeLeader(jsonFields)
            };
            
            return jsonRecord;
        }
        this.isEqual = function(oldRecord) {
            return _.isEqual(this.getMarcJSON(), oldRecord);
        }
        
        this.isChanged = function() {
            return !record.isEqual(this.data);
        }
        
        this.validate = function() {
            if(_.isUndefined(this.schema)) {
                return;
            }
            function excludeControlField(json) {
                delete json["006"];
                delete json["007"];
                delete json["008"];
                delete json["leader"];
                return json;
            }
            this.$errors = tv4.validateMultiple(excludeControlField(this.getJSON()), this.schema, true, true);
        }
        
        
        this.getPath = function(property) {
            var path = "/" + property.name + "/" + this.getIndex(property);
            return path;
            
        }
        
        this.isErrorAddedField = function(property) {
            var path = "/" + property.name;
            if(_.isUndefined(this.$errors)) {
                return false;
            }
            var errors = _.filter(this.$errors.errors, function(_error) {
                return (_error.dataPath == path);
            });
            return errors.length > 0;
        }
        
        this.isError = function(property) {
            return (this.getError(property).length > 0);
        }
        
        this.getError = function(property) {
            return this.getErrorByPath(this.getPath(property));
        }
        
        this.getErrorByPath = function(path) {
            if(_.isUndefined(this.$errors)) {
                return {};
            }
            var errors = _.filter(this.$errors.errors, function(_error) {
                return (_error.dataPath.indexOf(path) == 0);
            });
            return errors;
        }
        
        this.version = function() {
            return "1.0";
        }
        
        this.clone = function() {
            var data = this.getMarcJSON();
            data.fields.remove(0);
            data.fields[0]["001"] = "";
            var clone =  new Record(data, this.schema);
            clone.setNew(true);
            return clone;
        }
        
        this.isNew = function() {
            return isNew || _.isUndefined(this.data.fields[0]["001"]);
        }
        
        this.setNew = function(_isNew) {
            isNew = _isNew;
        }
        
        this.reset = function() {
            var baseObject = this;
            $.each(this, function(propertyName, propertyValue) {
                if(baseObject.isJSONProperty(propertyName, propertyValue)) {
                    delete baseObject[propertyName];
                }
            });
            this.init();
        }
        
        this.init();
    }
    
    //legacy property
    var marc4js = {
        newRecord : function(data, schema) {
            return new Record(data, schema);
        }
    };

    return marc4js; // used by _header.js to globalise.

}));