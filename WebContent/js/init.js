var selectway = "search";

$(function(){
	//select触发事件，用于对搜索选择方式的改变
	$("#selectway").on("change",function(){
		if($("option:selected",this).val() == "job"){
			$(".job_span").show();
			$(".common_con").hide();
			$("#search_but").attr("onclick","dosearch('job')");
			selectway = "job";
		}else{
			$(".common_con").show();
			$(".job_span").hide();
			$("#search_but").attr("onclick","dosearch('search')");
			selectway = "search";
		}
	});
	
});