package com.test.fetch.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import org.junit.Test;

import com.iiimms.fetch.impl.FetchContentImplBasic;

public class FetchContentImplTest_SinaLunTan {

	@Test
	public void testGetBaName() {
		// ������������
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// �������Զ��󣬳�ʼ������ģ�����
		FetchContentImplBasic testObj = new FetchContentImplBasic();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String baNameQuery = "div#nav>a[href^=forumdisplay]";
		System.out.println(testObj.getBaName(baNameQuery));
	}

	@Test
	public void testGetTitle() {

		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		FetchContentImplBasic testObj = new FetchContentImplBasic();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String titleQuery = "span[id^=posttitle]";
		System.out.println(testObj.getTitle(titleQuery));
	}

	@Test
	public void testGetPagerUrl() {

		Scanner cin = new Scanner(System.in);
		String strMethod = "getSinaluntanPagerUrl";
		String strPostLink = cin.next();

		FetchContentImplBasic testObj = new FetchContentImplBasic();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String pagerQuery = "div.pages";
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

		String postDivQuery = "div[id^=floor]";
		String postContentQuery = "div[id^=postmessage]";
		String postAuthorQuery = "div.myInfo_up>a.f14";
		String postTimeQuery = "div.myInfo_up>font";
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
		FetchContentImplBasic testObj = new FetchContentImplBasic();

		String postParameXMLFilePath = "./src/contentParameResource/sinaluntanParame.xml";
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
