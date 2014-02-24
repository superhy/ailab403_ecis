package com.test.fetch.special;

import java.util.Scanner;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.ecis.fetch.special.SelectFinalQuery;
import com.ecis.util.JsoupDocumentUtil;

public class SelectFinalQueryTest {

	@Test
	public void testGetFinalQueryByDocument() {
		SelectFinalQuery testObj = new SelectFinalQuery();

		Scanner cin = new Scanner(System.in);
		String testPostLink = cin.next();
		String testInitQuery = cin.next();
		Document testDocument = JsoupDocumentUtil.getDocument(testPostLink);

		String resultQuery = testObj.getFinalQuery(testDocument, testInitQuery);
		System.out.println(resultQuery);
	}
	
	@Test
	public void testGetFinalQueryByElement() {
		SelectFinalQuery testObj = new SelectFinalQuery();

		Scanner cin = new Scanner(System.in);
		String testPostLink = cin.next();
		String testDivQuery = cin.next();
		String testInitQuery = cin.next();
		Document testDocument = JsoupDocumentUtil.getDocument(testPostLink);
		Element testElement = testDocument.select(testDivQuery).get(2);

		String resultQuery = testObj.getFinalQuery(testElement, testInitQuery);
		System.out.println(resultQuery);
	}
}
