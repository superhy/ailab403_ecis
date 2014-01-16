package com.ecis.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetJsoupDocument {

	public static Document getDocument(String url) throws Exception {
		return Jsoup.connect(url).timeout(600000).ignoreHttpErrors(true).get();
	}
}
