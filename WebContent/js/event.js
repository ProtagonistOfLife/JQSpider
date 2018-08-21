$(function(){
	$.ajax({
		type:"get",
		url:"http://localhost/JQSpider",
		data:parm,
		success:function(data){
			/*if($.cookie("id") == null){
				window.location = data;
			}else{
				var root = document.getElementById("bulletarea");
				var child = document.createElement("div");
				child.setAttribute("style","color:"+getrandomcolor());
				child.innerText = text;
				root.appendChild(child);
				init_animated(top_distance);
				/* console.log(data); 
				selflist.push(data);
			}*/
		},
		contentType: "application/x-www-form-urlencoded; charset=gb2312",
		dataType:"text"
	});
});

function searchway(option){
	if(option == 'search'){
		
	}else{
		
	}
}