package serviceImp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.junit.Test;

import service.FileOperation;

public class FileOperationImp implements FileOperation {

	@Override
	public void saveConfig(String context, String filePath) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveInfo(String context, String filePath) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getConfig(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfo(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExsit(String filePath) {
		File file = new File(filePath);
		return file.exists();
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
