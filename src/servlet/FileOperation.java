package servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileOperation
 */
@WebServlet(urlPatterns="/fileopration",name="init",loadOnStartup=1,description="init")
public class FileOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String rootpath = FileOperation.class.getClassLoader().getResource("../../").getPath() + "resource";
	
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("init执行了");
		File file = new File(rootpath);
		System.out.println(file.getPath());
		if(!file.exists()){
			file.mkdirs();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("context") != null){
			throw new ServletException("get请求不支持保存文件！！");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("context") == null){
			throw new ServletException("post请求仅用于文件保存！！");
		}
	}
}
