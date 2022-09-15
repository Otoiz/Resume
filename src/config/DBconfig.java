package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class DBconfig {
	
	//DBconfig 読込み
	public String[] getDBinfo(String file_path) throws FileNotFoundException{
	
		Properties info = new Properties();
		FileInputStream file_stream = null;

		try {
			file_stream = new FileInputStream(file_path);
			info.load(file_stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//DBconfig.propertiesのキーから値を取得
		String url = info.getProperty("url");
		String user = info.getProperty("user");
		String password = info.getProperty("password");

		//DBconfig.propertiesの値を配列に格納
		String[] db_info = {url,user,password};
		
		return db_info;
	}
}
