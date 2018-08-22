package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String url = request.getParameter("url");
		String type = request.getParameter("type");
		String charset = request.getParameter("charset");

		PrintWriter writer = response.getWriter();
		System.out.println(url);
		if(type.equals("json")){
			JsonData json = new JsonDataImp();
			writer.println(json.getJsonData(url,charset));
		}else{
			HtmlData html = new HtmlDataImp();
			writer.println(html.getHtml(url,charset));
		}
		if(writer != null){
			writer.flush();
			writer.close();
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
