package serviceImp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.junit.Test;

import net.sf.json.JSONObject;
import service.FileOperation;

public class FileOperationImp implements FileOperation {

	@Override
	public void saveConfig(String context, File file) throws IOException {
		if(!file.exists()){
			file.createNewFile();
		}
		PrintWriter writer = new PrintWriter(new FileOutputStream(file,false));
		writer.println(context);
		writer.close();
	}

	@Override
	public void saveInfo(JSONObject context, File file) throws IOException {
		if(context == null){
			return;
		}
		PrintWriter writer = new PrintWriter(new FileOutputStream(file,false));
		writer.println(context);
		writer.close();
		
	}

	@Override
	public String getFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getLastFile(String path,String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	public void newfiletest() throws IOException{
		File position = new File(".");
		String path = position.getCanonicalPath();
		System.out.println(path);
		File file = new File(path + "/config");
		if(!file.exists()){
			System.out.println(file.mkdirs());
		}
		OutputStream os = new FileOutputStream(file.getPath()+"/s.ini",false);
		PrintWriter pw = new PrintWriter(os);
		pw.println("你好！");
		pw.close();
	}
}
