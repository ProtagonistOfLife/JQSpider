/*$(function(){
	$.ajax({
		type:"get",
		url:"http://localhost/JQSpider/getresource",
		data:{
			url:"https://fe-api.zhaopin.com/c/i/sou?start=240&pageSize=60&cityId=538&salary=8001,10000&workExperience=-1&education=-1&companyType=-1&employmentType=-1&jobWelfareTag=-1&kw=java&kt=3&lastUrlQuery={'p':5,'jl':'538','sf':'8001','st':'10000','kw':'java','kt':'3'}&=8001",
			type:"json",
			charset:"utf-8"
		},
		success:function(data){
			if($.cookie("id") == null){
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
			}
			console.log(data);
		},
		//contentType: "application/x-www-form-urlencoded; charset=gb2312",
//		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType:"text"
	});
});*/

//按钮点击时间的触发事件
function dosearch(way){
	/*dosave("{context:'你好'}","test","test.tex");
	return;*/
	if(way == "search"){
		//TODO普通搜索筛选
	}else{
		/*var urllink = "https://www.zhipin.com/job_detail/?query=Java&scity=101020100&industry=&position=";
		bosszp(urllink,null);*/
		var condition = {};
		condition.city = "上海";
		condition.keyword = "java";
		
		zhilian(condition, null);
	}
}