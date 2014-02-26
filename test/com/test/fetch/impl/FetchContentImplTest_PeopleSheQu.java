package com.test.fetch.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import org.junit.Test;

import com.iiimms.fetch.impl.FetchContentImplBasic;

public class FetchContentImplTest_PeopleSheQu {

	@Test
	public void testGetBaName() {
		// 输入帖子链接
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// 建立测试对象，初始化帖子模版参数
		FetchContentImplBasic testObj = new FetchContentImplBasic();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String baNameQuery = "div.wrapNavBar.clearfix>a[href*=board]";
		System.out.println(testObj.getBaName(baNameQuery));
	}

	@Test
	public void testGetTitle() {

		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		FetchContentImplBasic testObj = new FetchContentImplBasic();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String titleQuery = "div.navBar>h2";
		System.out.println(testObj.getTitle(titleQuery));
	}

	@Test
	public void testGetPagerUrl() {

		Scanner cin = new Scanner(System.in);
		String strMethod = "getPeopleshequPagerUrl";
		String strPostLink = cin.next();

		FetchContentImplBasic testObj = new FetchContentImplBasic();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String pagerQuery = "div.pageBar";
		for (String strResult : testObj.getPagerUrl(strMethod, pagerQuery)) {
			System.out.println(strResult);
		}
	}

	@Test
	public void testGetContentEachPager() {

		Scanner cin = new Scanner(System.in);
		String strPageUrl = cin.next();

		FetchContentImplBasic testObj = new FetchContentImplBasic();
		// testObj.setPostLink(strPostLink);
		// testObj.initContentParame(null);

		String postDivQuery = "p.treeTitle,ul.subUL>li";
		String postContentQuery = "a.treeReply";
		String postAuthorQuery = "a[class=userNick]";
		String postTimeQuery = "span:not(.showSubreply)";
		String replyDivQuery = "ul.subUL>li";
		String replyContentQuery = "a.treeReply";
		String replyAuthorQuery = "a[class=userNick]";
		String replyTimeQuery = "span";
		System.out.println(testObj.getContentEachPager(strPageUrl,
				postDivQuery, postContentQuery, postAuthorQuery, postTimeQuery,
				replyDivQuery, replyContentQuery, replyAuthorQuery,
				replyTimeQuery));
	}

	@Test
	public void testGetAllContent() {
		FetchContentImplBasic testObj = new FetchContentImplBasic();

		String postParameXMLFilePath = "./src/contentParameResource/peopleshequParame.xml";
		String strPostLink = new Scanner(System.in).next();

		testObj.setPostLink(strPostLink);
		String strResult = testObj.getAllContent(postParameXMLFilePath);

		try {
			File fileResult = new File("./file/testResult.txt");
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
