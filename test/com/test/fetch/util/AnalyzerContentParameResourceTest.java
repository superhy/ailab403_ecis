package com.test.fetch.util;

import org.junit.Test;

import com.iiimms.fetch.util.AnalyzerContentParameResource;
import com.iiimms.model.ContentParame;

public class AnalyzerContentParameResourceTest {

	@Test
	public void testGetAllContentParameValue_baidutieba() {
		AnalyzerContentParameResource testObj = new AnalyzerContentParameResource();
		testObj.setXmlResourcePath("./src/contentParameResource/baidutiebaParame.xml");

		ContentParame contentParame = testObj.getAllContentParameValue();

		System.out.println(contentParame.getBaNameQuery());
		System.out.println(contentParame.getTitleQuery());
		System.out.println(contentParame.getPagerQuery());
		System.out.println(contentParame.getFetchPagerMethod());
		System.out.println(contentParame.getPostDivQuery());
		System.out.println(contentParame.getPostContentQuery());
		System.out.println(contentParame.getPostAuthorQuery());
		System.out.println(contentParame.getPostTimeQuery());
		System.out.println(contentParame.getReplyDivQuery());
		System.out.println(contentParame.getReplyContentQuery());
		System.out.println(contentParame.getReplyAuthorQuery());
		System.out.println(contentParame.getReplyTimeQuery());
	}
}
