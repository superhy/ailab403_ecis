package com.iiimms.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BasicJsoupDocumentUtil {

	public static Document getDocument(String url) {
		try {

			// 设置连接超时和读数超时
			// 设置忽略过期页面
			return Jsoup.connect(url).timeout(600000).ignoreHttpErrors(true)
					.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
