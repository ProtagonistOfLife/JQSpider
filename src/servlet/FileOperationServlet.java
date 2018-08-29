package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.FileOperation;
import serviceImp.FileOperationImp;

/**
 * Servlet implementation class FileOperation
 */
@WebServlet(urlPatterns="/fileopration",name="init",loadOnStartup=1,description="init")
public class FileOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String rootpath = FileOperationServlet.class.getClassLoader().getResource("../../").getPath();
//	用于缓存信息文件中的数据
	private Map<File, JSONObject> infocache = new HashMap<>();
	private FileOperation fo = new FileOperationImp();
	
	@Override
	public void init() throws ServletException {
		super.init();
		File temp = new File(rootpath);
		rootpath = temp.getParentFile().getParent() + "/resource/JQSpider";
		File file = new File(rootpath + "/info");
		File file2 = new File(rootpath + "/config");
		if(!file.exists()){
			file.mkdirs();
			file2.mkdirs();
		}
	}

//	get请求获取资源文件中的内容
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("context") != null){
			throw new ServletException("get请求不支持保存文件！！");
		}
		String type = request.getParameter("type");
		String filename = request.getParameter("filename");
		PrintWriter writer = response.getWriter();
		File file = new File(rootpath + "/" + type + "/" + filename);
		String strtemp = null;
		
		if(!file.exists()){
			writer.println("false");
			return ;
		}
		if(type.equals("info")){
			flush();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		while((strtemp = br.readLine()) != null){
			writer.println(strtemp);
		}
		if(br != null){
			br.close();
		}
	}

//	存储资源文件
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("context") == null){
			throw new ServletException("post请求仅用于文件保存！！");
		}
		String type = request.getParameter("type");
		String filename = request.getParameter("filename");
		String context = request.getParameter("context");
		File file = new File(rootpath + "/" + type + "/" + filename);
		if(type.equals("config")){
			fo.saveConfig(context, file);
		}else if(type.equals("info")){
			File infofile = fo.getLastFile(rootpath + "/" + type, filename);
			removal(infofile, JSONObject.fromObject(context));
		}else{
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
			PrintWriter pw = new PrintWriter(new FileOutputStream(file));
			pw.println(context);
			pw.close();
		}
	}

	@Override
	public void destroy() {
		try {
			flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.destroy();
	}
	
	public void flush() throws IOException{
		for(Map.Entry<File, JSONObject> entry : infocache.entrySet()){
			fo.saveInfo(entry.getValue(),entry.getKey());
		}
		infocache.clear();
	}
	
	public void removal(File file,JSONObject json){
		JSONObject jsons = null;
		JSONArray array = null;
		
		if((jsons = infocache.get(file)) == null){
			infocache.put(file, json);
			return;
		}
		array = JSONArray.fromObject(jsons);
		array.add(jsons);
		array.add(json);
		infocache.put(file, JSONObject.fromObject(array));
	}
}
