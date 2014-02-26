package com.iiimms.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ParseProperties {

	static String propertiesPath = "/src/seedList.properties";

	public String getSeedUrl(String seedName) throws Exception {
		// ����properties��������д���ļ�����
		File pf = new File(System.getProperty("user.dir") + propertiesPath);
		FileInputStream ins = new FileInputStream(pf);

		// ����properties���󣬲������ļ���
		Properties properties = new Properties();
		properties.load(ins);

		// ���properties�ļ�������
		String seedUrl = properties.getProperty(seedName);

		return seedUrl;
	}
}
