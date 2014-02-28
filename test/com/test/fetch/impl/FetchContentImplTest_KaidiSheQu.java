package com.test.fetch.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import org.junit.Test;

import com.iiimms.fetch.impl.BasicFetchContentImpl;
import com.iiimms.fetch.impl.HttpClientFetchContentImpl;

public class FetchContentImplTest_KaidiSheQu {

	/**
	 * 凯迪论坛暂时无法解析
	 */

	@Test
	public void testGetBaName() {
		// 输入帖子链接
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// 建立测试对象，初始化帖子模版参数
		HttpClientFetchContentImpl testObj = new HttpClientFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String baNameQuery = "div.forum";
		System.out.println(testObj.getBaName(baNameQuery));
	}

	@Test
	public void testGetTitle() {

		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		HttpClientFetchContentImpl testObj = new HttpClientFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String titleQuery = "div.posts-title";
		System.out.println(testObj.getTitle(titleQuery));
	}

	@Test
	public void testGetPagerUrl() {

		Scanner cin = new Scanner(System.in);
		String strMethod = "getKaidiSheQuPagerUrl";
		String strPostLink = cin.next();

		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String pagerQuery = "div.pagesmodule";
		for (String strResult : testObj.getPagerUrl(strMethod, pagerQuery)) {
			System.out.println(strResult);
		}
	}

	@Test
	public void testGetContentEachPager() {

		Scanner cin = new Scanner(System.in);
		String strPageUrl = cin.next();

		HttpClientFetchContentImpl testObj = new HttpClientFetchContentImpl();
		// testObj.setPostLink(strPostLink);
		// testObj.initContentParame(null);

		String postDivQuery = "div.posted-box-add,div[class*=reply-box]";
		String postContentQuery = "div.posts-cont,div.replycont-text";
		String postAuthorQuery = "a[href*=user],span[class*=name]>a[href*=user]";
		String postTimeQuery = "div.posts-posted,div[class^=posted-info]:has(span.c-sub)";
		String replyDivQuery = "";
		String replyContentQuery = "";
		String replyAuthorQuery = "";
		String replyTimeQuery = "";
		System.out.println(testObj.getContentEachPager(strPageUrl,
				postDivQuery, postContentQuery, postAuthorQuery, postTimeQuery,
				replyDivQuery, replyContentQuery, replyAuthorQuery,
				replyTimeQuery));
	}

	@Test
	public void testGetAllContent() {
		HttpClientFetchContentImpl testObj = new HttpClientFetchContentImpl();

		String postParameXMLFilePath = "./src/contentParameResource/kaidishequParame.xml";
		String strPostLink = new Scanner(System.in).next();

		testObj.setPostLink(strPostLink);
		String strResult = testObj.getAllContent(postParameXMLFilePath);

		try {
			File fileResult = new File(
					"./file/content/testKaidiContent.txt");
			if (!fileResult.exists()) {
				fileResult.createNewFile();
			}

			FileWriter fw = new FileWriter(fileResult);
			fw.write(strResult);

			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(strResult);
	}
}
