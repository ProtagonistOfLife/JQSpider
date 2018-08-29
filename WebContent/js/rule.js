//职业开始筛选的规则
function jobfirstregular(jsons){
	for(var item in jsons){
		var json = jsons[item];
		var minexp = json.workingExp.substring(0,1);
//		经验年限筛选
		if(parseInt(minexp) > 1){
			delete jsons[item]
		}
		var minsalary = parseFloat(json.salary);
//		薪资筛选
		if(minsalary < 7){
			delete jsons[item];
		}
		if(json.eduLevel == "硕士"){
			console.log(json);
			delete jsons[item];
		}
	}
}