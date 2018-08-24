package serviceImp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import net.sf.json.JSONObject;
import service.HtmlData;

public class HtmlDataImp implements HtmlData {
	private static String projectPath = System.getProperty("user.dir");
	private static String jsPath = projectPath + File.separator + "src" + File.separator + "phantom.js";
	private static String exePath = "D:\\software\\phantomjs-2.5.0-beta2-windows\\bin\\phantomjs.exe";
	
	@Override
	public String getHtml(String url,String charset) {
		URL link = null;
		InputStream is = null;
		BufferedReader br = null;
		String str = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			link = new URL(url);
			is = link.openStream();
			br = new BufferedReader(new InputStreamReader(is, charset));
			while((str = br.readLine()) != null){
				if(str.equals(""))
					continue;
				System.out.println(str);
				sb.append(str).append("\r\n");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(is != null){
				is.close();
				}
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}

	@Override
	public String postHtml(String url,JSONObject parm,String charset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String webKitHtml(String url) throws IOException {
		Runtime rt = Runtime.getRuntime();  
		Process p = rt.exec(exePath + " " + jsPath + " " + url);  
		InputStream is = p.getInputStream();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));  
		String tmp = null;
		while ((tmp = br.readLine()) != null){
			if(tmp != ""){
				System.out.println(tmp); 
				sb.append(tmp).append("\r\n");
			}
		}
		if(is != null)
			is.close();
		if(br != null)
			br.close();
		return sb.toString();
	}
	
	@Test
	public void use() throws IOException{
		String url = "https://www.lagou.com/jobs/list_Java?city=%E4%B8%8A%E6%B5%B7&cl=false&fromSearch=true&labelWords=&suginput=";
		webKitHtml(url);
	}
	
	/**
	 * phantomjs的试用
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException{
		String url = "https://www.lagou.com/jobs/list_Java?city=%E4%B8%8A%E6%B5%B7&cl=false&fromSearch=true&labelWords=&suginput=";
		Runtime rt = Runtime.getRuntime();  
		Process p = rt.exec(exePath + " " + jsPath + " " + url);  
		InputStream is = p.getInputStream();  
		BufferedReader br = new BufferedReader(new InputStreamReader(is));  
		String tmp = null;  
		while ((tmp = br.readLine()) != null)  
		{  if(tmp != "")
			System.out.println(tmp); 
		}
		if(is != null)
			is.close();
		if(br != null)
			br.close();
	}
}
