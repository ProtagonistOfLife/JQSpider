function dosave(context,isconfig,filename){
	$.ajax({
		type:"post",
		url:"http://localhost/JQSpider/dosave",
		data:{
			"context":context,
			"isconfig":isconfig,
			"filename":filename
		},
		
		dataType:"text"
	});
}

function trim(str){ 
 return str.replace(/(^\s*)|(\s*$)/g, ""); 
}