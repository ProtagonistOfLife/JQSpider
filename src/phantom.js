var page = require('webpage').create(),  
  system = require('system'),  
  t, address;  
//写入文件，用来测试。正式版本可以注释掉用来提高速度。  
var fs = require("fs");  
//读取命令行参数，也就是js文件路径。  
if (system.args.length === 1) {  
  console.log('Usage: loadspeed.js <some URL>');  
//这行代码很重要。凡是结束必须调用。否则phantomjs不会停止  
  phantom.exit();  
}
page.settings.userAgent = 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.104 Safari/537.36';
page.settings.loadImages = false;  //为了提升加载速度，不加载图片  
page.settings.resourceTimeout = 10000;//超过10秒放弃加载  
//此处是用来设置截图的参数。不截图没啥用  

/*block_urls = ['.png','.jpg','.jpeg','gif'];//为了提升速度，屏蔽一些需要时间长的。比如百度广告  
page.onResourceRequested = function(requestData, request){  
    for(url in block_urls) {  
        if(requestData.url.indexOf(block_urls[url]) !== -1) {  
            request.abort();  
            //console.log(requestData.url + " aborted");  
            return;  
        }  
    }              
}*/

t = Date.now();//看看加载需要多久。  
address = system.args[1];  
page.open(address, function(status) {
  if (status !== 'success') {  
    console.log('FAIL to load the address');  
  } else {
    t = Date.now() - t;  

    var websit = address.split('/')[2];
    if(websit == 'www.zhipin.com'){
    	zhipin();
    }else if(websit == 'search.51job.com'){
    	job51();
    }else if(websit == 'www.lagou.com'){
    	lagou();
    }
    console.log('Loading time ' + t + ' msec');  
    setTimeout(function(){ phantom.exit(); }, 18000);  
  }  
  phantom.exit();  
});

function zhipin(){
	var ua = page.evaluate(function () {
		return document.getElementById('main').innerHTML;
	});
	console.log(ua);
}

function job51(){
	var ua = page.evaluate(function () {
		return document.getElementById('resultList').innerHTML;
	});
	console.log(ua);
}

function lagou(){
	var ua = page.evaluate(function () {
		return document.getElementById('s_position_list').innerHTML;
	});
	console.log(ua);
}