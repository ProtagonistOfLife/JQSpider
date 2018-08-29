package service;

import java.io.File;
import java.io.IOException;

import net.sf.json.JSONObject;

public interface FileOperation {
	public void saveConfig(String context,File file) throws IOException;
	public void saveInfo(JSONObject context,File file) throws IOException;
	public String getFile(File file);
	public File getLastFile(String path,String filename);
}
