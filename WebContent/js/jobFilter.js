var commondata;
function datadeal(data){
	commondata = data;
}

function showdata(){
	console.log(commondata);
}

//智联招聘筛选
function zhilian(urllink,parm){
	$.ajax({
		type:"get",
		url:"http://localhost/JQSpider/getresource",
		data:{
			url:urllink,
			type:"json",
			charset:"utf-8"
		},
		success:function(data){
			console.log(data);
		},
		dataType:"json"
	});
}

//51job筛选
function job51(urllink,parm){
	$.ajax({
		type:"get",
		url:"http://localhost/JQSpider/getresource",
		data:{
			url:urllink,
			type:"html",
			charset:"GBK"
		},
		success:function(data){
			datadeal(data);
			showdata();
		},
		dataType:"html"
	});
}

//boss直聘
function bosszp(urllink,parm){
	$.ajax({
		type:"get",
		url:"http://localhost/JQSpider/getresource",
		data:{
			url:urllink,
			type:"html",
			charset:"UTF-8"
		},
		success:function(data){
			datadeal(data);
			showdata();
		},
		dataType:"html"
	});
}

function lagou(urllink,parm){
	$.ajax({
		type:"post",
		url:urllink,
		data:{
			url:urllink,
			type:"json",
			charset:"UTF-8"
		},
		success:function(data){
			datadeal(data);
			showdata();
		},
		dataType:"json"
	});
}