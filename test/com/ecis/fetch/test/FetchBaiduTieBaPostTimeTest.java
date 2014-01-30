package com.ecis.fetch.test;

import java.util.Scanner;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.ecis.fetch.special.FetchBaiduTieBaPostTime;
import com.ecis.util.GetJsoupDocument;

public class FetchBaiduTieBaPostTimeTest {

	@Test
	public void testGetBaiduTieBaPostTime() {
		FetchBaiduTieBaPostTime testObj = new FetchBaiduTieBaPostTime();

		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		Document docBaiduTieBaPost = GetJsoupDocument.getDocument(strPostLink);
		Element eleBaiduTieBaPostDiv = docBaiduTieBaPost.select(
				"div[class*=l_post]").first();
		
		testObj.getBaiduTieBaPostTime(eleBaiduTieBaPostDiv);
	}
}
