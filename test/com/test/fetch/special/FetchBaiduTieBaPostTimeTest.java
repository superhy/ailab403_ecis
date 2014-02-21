package com.test.fetch.special;

import java.util.Scanner;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.ecis.fetch.special.FetchPostTime_BaiduTieBa;
import com.ecis.util.JsoupDocumentUtil;

public class FetchBaiduTieBaPostTimeTest {

	@Test
	public void testGetBaiduTieBaPostTime() {
		FetchPostTime_BaiduTieBa testObj = new FetchPostTime_BaiduTieBa();

		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		Document docBaiduTieBaPost = JsoupDocumentUtil.getDocument(strPostLink);
		Element eleBaiduTieBaPostDiv = docBaiduTieBaPost.select(
				"div[class*=l_post]").first();
		
		testObj.getBaiduTieBaPostTime(eleBaiduTieBaPostDiv);
	}
}
