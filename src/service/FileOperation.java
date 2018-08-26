package service;

public interface FileOperation {
	public void saveConfig(String context,String filePath);
	public void saveInfo(String context,String filePath);
	public String getConfig(String filePath);
	public String getInfo(String filePath);
	public boolean isExsit(String filePath);
}
