$.rightPad = function(i,l,s) {
	if (i == null) {
		return null;
	}
    var o = i.toString();
    if (!s) { s = '0'; }
    while (o.length < l) {
        o = o + s;
    }
    return o.substring(0, l);
};

$.leftPad = function(i,l,s) {
	if (i == null) {
		return null;
	}
	var o = i.toString();
	if (!s) { s = '0'; }
	while (o.length < l) {
		o = s + o;
	}
	return o.substring(0, l);
};

function countriesParser(element, xml, value) {
	$(xml).find("country").each(function()
	{
		var code = $(this).children("code[status!='obsolete']").text();
		var option = $(document.createElement('option')).val($.rightPad(code, 3, "#")).text(code + "-" + $(this).children("name[authorized='yes']").text());
		element.append(option);
	});
	element.val(value);
}

function languagesParser(element, xml, value) {
	$(xml).find("language").each(function()
	{
		var code = $(this).children("code").text();
		var option = $(document.createElement('option')).val(code).text(code + "-" + $(this).children("name").text());
		element.append(option);
	});
	element.val(value);
}

var cipher;

var integralCataloguingRestBaseUrl = 'http://localhost/integral-mystic/services/cataloguing/';
var uploadFileMysticBaseUrl = 'http://localhost/integral-mystic/services/foundation/integral/';

var urls = {
		'createRecord' : {type: 'POST',   url: integralCataloguingRestBaseUrl + 'marc/{library}/{userid}?cipher={cipher}'},
		'updateRecord' : {type: 'PUT',    url: integralCataloguingRestBaseUrl + 'marc/{library}/{userid}/{marcid}?cipher={cipher}'},
		'deleteRecord' : {type: 'DELETE', url: integralCataloguingRestBaseUrl + 'marc/{library}/{userid}/{marcid}?cipher={cipher}'},
		'findAll'      : {type: 'GET',    url: integralCataloguingRestBaseUrl + 'marc/{library}/{userid}/list?cipher={cipher}'},
		'findOne'      : {type: 'GET',    url: integralCataloguingRestBaseUrl + 'marc/{library}/{userid}/{marcid}?cipher={cipher}'},
		'listTemplate' : {type: 'GET',    url: integralCataloguingRestBaseUrl + 'template/{library}/{userid}/list?cipher={cipher}'},
		'loadTemplate' : {type: 'GET',    url: integralCataloguingRestBaseUrl + 'template/{library}/{userid}/{template}?cipher={cipher}'},
		'uploadFile'   : {type: 'POST',   url: uploadFileMysticBaseUrl + 'marc/{library}/{userid}', contentType: 'multipart/form-data'},
		'verify'   	   : {type: 'POST',   url: integralCataloguingRestBaseUrl + 'marc/verify/{library}/{userid}?cipher={cipher}'},
		'convert'      : {type: 'POST',   url: integralCataloguingRestBaseUrl + 'marc/convert/{library}/{userid}?cipher={cipher}'},
		'duplicateCheck': {type: 'POST',   url: integralCataloguingRestBaseUrl + 'marc/duplicate/{library}/{userid}?cipher={cipher}&tag={tag}'}
	};
var mapper = new Request.Mapper(urls);
