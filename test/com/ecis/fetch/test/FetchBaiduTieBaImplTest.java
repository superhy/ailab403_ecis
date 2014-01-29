package com.ecis.fetch.test;

import java.util.Scanner;

import org.junit.Test;

import com.ecis.fetch.impl.FetchBaiduTieBaImpl;

public class FetchBaiduTieBaImplTest {

	@Test
	public void testGetBaName() {

		// 输入帖子链接
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// 建立测试对象，初始化帖子模版参数
		FetchBaiduTieBaImpl testObj = new FetchBaiduTieBaImpl();
		testObj.setPostLink(strPostLink);
		testObj.initContentParame(null);

		String baNameQuery = "a#tab_forumname";
		testObj.getBaName(baNameQuery);
	}

	@Test
	public void testGetTitle() {

		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		FetchBaiduTieBaImpl testObj = new FetchBaiduTieBaImpl();
		testObj.setPostLink(strPostLink);
		testObj.initContentParame(null);

		String titleQuery = "h1.core_title_txt";
		testObj.getTitle(titleQuery);
	}

	@Test
	public void testGetPagerUrl() {

		Scanner cin = new Scanner(System.in);
		String strMethod = "getBaidutiebaPagerUrl";
		String strPostLink = cin.next();

		FetchBaiduTieBaImpl testObj = new FetchBaiduTieBaImpl();
		testObj.setPostLink(strPostLink);
		testObj.initContentParame(null);

		String testPagerQuery = "li.l_pager.pager_theme_2";
		for (String strResult : testObj.getPagerUrl(strMethod, testPagerQuery)) {
			System.out.println(strResult);
		}
	}
}
