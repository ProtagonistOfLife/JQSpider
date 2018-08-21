package serviceImp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import net.sf.json.JSONObject;
import service.DataResource;

public class ZhiLian implements DataResource {

	@Override
	public JSONObject getJsonDate(JSONObject parm) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Test
	public void test(){
		URL url = null;
		InputStream is = null;
		BufferedReader br = null;
		String str = null;
		try {
			url = new URL("https://fe-api.zhaopin.com/c/i/sou?start=240&pageSize=60&cityId=538&salary=8001,10000&workExperience=-1&education=-1&companyType=-1&employmentType=-1&jobWelfareTag=-1&kw=java&kt=3&lastUrlQuery={'p':5,'jl':'538','sf':'8001','st':'10000','kw':'java','kt':'3'}&=8001");
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			while((str = br.readLine()) != null){
				System.out.println(str);
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
	}

	@Test
	public void getHtml() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		WebClient webclient = new WebClient(BrowserVersion.CHROME);
		webclient.getOptions().setCssEnabled(false);
		webclient.getOptions().setJavaScriptEnabled(true);
		webclient.setAjaxController(new NicelyResynchronizingAjaxController());  
		webclient.getOptions().setThrowExceptionOnScriptError(false);
		webclient.getOptions().setTimeout(10000);
		HtmlPage  page = (HtmlPage) webclient.getCurrentWindow().getEnclosedPage();
		page = webclient.getPage("https://www.lagou.com/jobs/list_java?px=default&gx=%E5%85%A8%E8%81%8C&gj=&isSchoolJob=1&city=%E4%B8%8A%E6%B5%B7#filterBox");
//		HtmlPage  page = webclient.getPage("http://www.baidu.com");
		webclient.waitForBackgroundJavaScript(10000);
		System.out.println(page.asXml());
		System.out.println("--------------<-xml--text->-----------------");
		System.out.println(page.asText());
		webclient.closeAllWindows();
	}
	
	@Test
	public void phantomJS(){
		System.out.println(System.getProperty("user.dir"));
		
	}
}
