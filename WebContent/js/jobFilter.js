var commondata;
function datadeal(data){
	commondata = data;
}

function showdata(){
	console.log(commondata);
}

//智联招聘筛选
function zhilian(condition,parm){
//	var cities = {"上海":538};
	var cities = {538:"上海"};
	var parm = {
			citycode:538,
			workExperience:-1,
			education:-1,
			companyType:-1,
			employmentType:-1,
			jobWelfareTag:-1,
			kw:"java",
			kt:3
	}
	
	var lasturlquery = {
			//p:2,
			jl:538,
			kw:"java",
			kt:3
	}
	var citycode = 538;
	var urllink = "https://fe-api.zhaopin.com/c/i/sou?pageSize=60&cityId="
		+ parm.citycode + "&workExperience="
		+ parm.workExperience + "&education="
		+ parm.education + "&companyType="
		+ parm.companyType + "&employmentType="
		+ parm.employmentType + "&jobWelfareTag="
		+ parm.jobWelfareTag + "&kw="
		+ parm.kw + "&kt="
		+ parm.kt + "&lastUrlQuery=" 
		+ jsonString(lasturlquery);
	$.ajax({
		type:"get",
		url:"http://localhost/JQSpider/getresource",
		data:{
			url:urllink,
			type:"json",
			charset:"utf-8"
		},
		success:function(data){
			var jobinfo = {};
			jobinfo[results[ind].company.name] = [];
			var results = data.data.results;
			console.log(results);
			for(var ind in results){
				if(jobinfo[results[ind].company.name] != null)
					console.log(results[ind].company.name);
				var json = {"url":results[ind].positionURL,
							"companyurl":results[ind].company.url,
							"size":results[ind].company.size.name,
							"type":results[ind].company.type.name,
							"display":results[ind].jobType.display,
							"workingExp":results[ind].workingExp.name,
							"eduLevel":results[ind].eduLevel.name,
							"salary":results[ind].salary,
							"emplType":results[ind].emplType,
							"jobName":results[ind].jobName,
							"city":results[ind].city.display,
							"updateDate":results[ind].updateDate,
							"createDate":results[ind].createDate,
							"endDate":results[ind].endDate,
							"timeState":results[ind].timeState};
				jobinfo[results[ind].company.name][jobinfo[results[ind].company.name].length] = json;
			}
			jobfirstregular(jobinfo);
			console.log(jobinfo);
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
			var jobinfo = {};
			var divs = $('#resultList div.el',$(data));
			
			for(var ord = 1; ord < divs.length;ord++){
				var companyname = divs[ord].childNodes[3].childNodes[0].innerText;
				var companyinfo = {};
				companyinfo.url = divs[ord].childNodes[3].childNodes[0].href;
				companyinfo.city = divs[ord].childNodes[5].innerText;
				companyinfo.salary = divs[ord].childNodes[7].innerText;
				companyinfo.updateDate = "2018-" + divs[ord].childNodes[9].innerText + " 00:00:00";
				companyinfo.display = trim(divs[ord].childNodes[1].innerText);
				jobinfo[companyname] = companyinfo;
//				console.log(divs[ord]);
			}
			console.log(jobinfo);
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
			var jobinfo = {};
			var divs = $('#main div.job-list ul li a',$(data));

			console.log(divs);
			console.log(divs[1]+divs[2]);
			for(var ord in divs){
//				var companyname = trim(divs[ord].childNodes[3].childNodes[3].innerText);
				console.log(divs[ord].children[0]);
				var companyinfo = {};
//				console.log(companyname);
				companyinfo.url = divs[ord].href;
				console.log(companyinfo);
//				jobinfo[companyname] = companyinfo;
			}
//			console.log(jobinfo);
		},
		dataType:"html"
	});
}

function lagou(urllink,parm){
	$.ajax({
		type:"get",
		url:"http://localhost/JQSpider/getresource",
		data:{
			url:urllink,
			type:"webkit",
			charset:"UTF-8"
		},
		success:function(data){
			datadeal(data);
			showdata();
		},
		dataType:"text"
	});
}