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
	
	/**
	 * phantomjs的试用
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException{
		String url = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
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
