package com.test.fetch.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import com.iiimms.fetch.impl.FetchContentImplBasic;

public class FetchContentImplTest_FenghuangLunTan {

	@Test
	public void testGetBaName() {
		// 输入帖子链接
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// 建立测试对象，初始化帖子模版参数
		FetchContentImplBasic testObj = new FetchContentImplBasic();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String baNameQuery = "a[href*=forumdisplay]";
		System.out.println(testObj.getBaName(baNameQuery));
	}

	@Test
	public void testGetTitle() {

		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		FetchContentImplBasic testObj = new FetchContentImplBasic();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String titleQuery = "a[class=ltx2]";
		System.out.println(testObj.getTitle(titleQuery));
	}

	@Test
	public void testGetPagerUrl() {

		Scanner cin = new Scanner(System.in);
		String strMethod = "getFenghuangluntanPagerUrl";
		String strPostLink = cin.next();

		FetchContentImplBasic testObj = new FetchContentImplBasic();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String pagerQuery = "div.numb_post2";
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

		String postDivQuery = "div[class^=left]";
		String postContentQuery = "div[id^=postmessage]";
		String postAuthorQuery = "a[target=_blank]";
		String postTimeQuery = "span.cRed,li.cGray";
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
	public void testGetContentAllPager() {

		Scanner cin = new Scanner(System.in);
		String strMethod = "getFenghuangluntanPagerUrl";
		String strPostLink = cin.next();

		FetchContentImplBasic testObj = new FetchContentImplBasic();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String pagerQuery = "div.numb_post2";
		String postDivQuery = "div[class^=left]";
		String postContentQuery = "div[id^=postmessage]";
		String postAuthorQuery = "a[target=_blank]";
		String postTimeQuery = "span.cRedORli.cGray";
		String replyDivQuery = "";
		String replyContentQuery = "";
		String replyAuthorQuery = "";
		String replyTimeQuery = "";
		System.out.println(testObj.getContentAllPager(strMethod, pagerQuery,
				postDivQuery, postContentQuery, postAuthorQuery, postTimeQuery,
				replyDivQuery, replyContentQuery, replyAuthorQuery,
				replyTimeQuery));
	}

	@Test
	public void testGetAllContent() {
		FetchContentImplBasic testObj = new FetchContentImplBasic();

		String postParameXMLFilePath = "./src/contentParameResource/fenghuangluntanParame.xml";
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
