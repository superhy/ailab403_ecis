package com.ecis.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupDocumentUtil {

	public static Document getDocument(String url) {
		try {
			return Jsoup.connect(url).timeout(600000).ignoreHttpErrors(true).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
