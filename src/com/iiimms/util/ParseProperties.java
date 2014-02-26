package com.iiimms.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ParseProperties {

	static String propertiesPath = "/src/seedList.properties";

	public String getSeedUrl(String seedName) throws Exception {
		// 生成properties输入流，写入文件对象
		File pf = new File(System.getProperty("user.dir") + propertiesPath);
		FileInputStream ins = new FileInputStream(pf);

		// 生成properties对象，并读入文件流
		Properties properties = new Properties();
		properties.load(ins);

		// 输出properties文件的内容
		String seedUrl = properties.getProperty(seedName);

		return seedUrl;
	}
}
