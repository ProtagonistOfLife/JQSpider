package service;

import net.sf.json.JSONObject;

/**
 * html格式数据转发至js
 * @author jxgapengciwen
 *
 */
public interface HtmlData {
//	get请求方式获取HTML
	public String getHtml(String url,String charset);
//	post请求方式获取HTML
	public String postHtml(String url,JSONObject parm,String charset);
}
