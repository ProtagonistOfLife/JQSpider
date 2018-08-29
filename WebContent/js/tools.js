function dosave(context,type,filename){
	var way = "text";
	if(type in ("config","info")){
		way = "json";
	}
	$.ajax({
		type:"post",
		url:"http://localhost/JQSpider/fileopration",
		data:{
			"context":context,
			"type":type,
			"filename":filename
		},
		
		dataType:way
	});
}

function getinfo(type,filename){
	var way = "text";
	if(type in ("config","info")){
		way = "json";
	}
	$.ajax({
		type:"get",
		url:"http://localhost/JQSpider/fileopration",
		data:{
			"type":type,
			"filename":filename
		},
		
		dataType:way
	});
}

function jsonString(json){
	var strtext = "{";
	for(var item in json){
		strtext += item + ":" + json[item] + ",";
	}
	strtext = strtext.substring(0, strtext.length - 1);
	strtext += "}";
	return strtext;
}

function trim(str){ 
 return str.replace(/(^\s*)|(\s*$)/g, ""); 
}