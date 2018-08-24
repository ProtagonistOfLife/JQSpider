package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import service.HtmlData;
import service.JsonData;
import serviceImp.HtmlDataImp;
import serviceImp.JsonDataImp;


@WebServlet("/getresource")
public class DataDealServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> cache = new HashMap<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String url = request.getParameter("url");
		String type = request.getParameter("type");
		String charset = request.getParameter("charset");
		String strtemp = null;

		PrintWriter writer = response.getWriter();
		System.out.println(cache.get(url));
//		用于查看是否存在缓存
		if(cache.get(url) != null){
			System.out.println("执行缓存中的数据");
			writer.println(cache.get(url));
			return;
		}
		if(type.equals("json")){
			JsonData json = new JsonDataImp();
			strtemp = json.getJsonData(url,charset).toString();
			cache.put(url, strtemp);
			writer.println(strtemp);
		}else if(type.equals("html")){
			HtmlData html = new HtmlDataImp();
			strtemp = html.getHtml(url,charset);
			cache.put(url, strtemp);
			writer.println(strtemp);
		}else{
			HtmlData html = new HtmlDataImp();
			try {
				strtemp = html.webKitHtml(url);
				cache.put(url, strtemp);
				writer.println(strtemp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("GBK");
		String url = request.getParameter("url");
		String type = request.getParameter("type");
		String charset = request.getParameter("charset");
		JSONObject parm = JSONObject.fromObject(request.getParameter("parm"));

		PrintWriter writer = response.getWriter();
		System.out.println(url);
		if(type.equals("json")){
			JsonData json = new JsonDataImp();
			writer.println(json.postJsonData(url,parm,charset));
		}else{
			HtmlData html = new HtmlDataImp();
			writer.println(html.postHtml(url,parm,charset));
		}
		if(writer != null){
			writer.flush();
			writer.close();
		}
	}

}
