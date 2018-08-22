package service;


import net.sf.json.JSONObject;

/**
 * json格式数据转发至js
 * @author jxgapengciwen
 *
 */
public interface JsonData {
//	get请求获取json数据
	public JSONObject getJsonData(String url,String charset);
//	post请求获取json数据
	public JSONObject postJsonData(String url,JSONObject parm,String charset);
}
