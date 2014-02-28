package com.test.fetch.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import org.junit.Test;

import com.iiimms.fetch.impl.BasicFetchContentImpl;

public class FetchContentImplTest_TianyaLunTan {

	@Test
	public void testGetBaName() {
		// 输入帖子链接
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// 建立测试对象，初始化帖子模版参数
		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String baNameQuery = "p.crumbs a[href*=list]";
		System.out.println(testObj.getBaName(baNameQuery));
	}

	@Test
	public void testGetTitle() {

		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String titleQuery = "span.s_title>span";
		System.out.println(testObj.getTitle(titleQuery));
	}

	@Test
	public void testGetPagerUrl() {

		Scanner cin = new Scanner(System.in);
		String strMethod = "getTianyaluntanPagerUrl";
		String strPostLink = cin.next();

		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String pagerQuery = "div.atl-pages";
		for (String strResult : testObj.getPagerUrl(strMethod, pagerQuery)) {
			System.out.println(strResult);
		}
	}

	@Test
	public void testGetContentEachPager() {

		Scanner cin = new Scanner(System.in);
		String strPageUrl = cin.next();

		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		// testObj.setPostLink(strPostLink);
		// testObj.initContentParame(null);

		String postDivQuery = "div#bd";
		String postContentQuery = "div[class^=bbs-content]";
		String postAuthorQuery = "a[uname]";
		String postTimeQuery = "div.atl-info>span:containsOwn(时间)";
		String replyDivQuery = "div[js_username]";
		String replyContentQuery = "div.bbs-content";
		String replyAuthorQuery = "a[uname]";
		String replyTimeQuery = "div.atl-info>span:containsOwn(时间)";
		System.out.println(testObj.getContentEachPager(strPageUrl,
				postDivQuery, postContentQuery, postAuthorQuery, postTimeQuery,
				replyDivQuery, replyContentQuery, replyAuthorQuery,
				replyTimeQuery));
	}

	@Test
	public void testGetAllContent() {
		BasicFetchContentImpl testObj = new BasicFetchContentImpl();

		String postParameXMLFilePath = "./src/contentParameResource/tianyaluntanParame.xml";
		String strPostLink = new Scanner(System.in).next();

		testObj.setPostLink(strPostLink);
		String strResult = testObj.getAllContent(postParameXMLFilePath);

		try {
			File fileResult = new File("./file/content/testTianyaContent.txt");
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
