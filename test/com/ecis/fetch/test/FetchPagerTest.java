package com.ecis.fetch.test;

import java.util.Scanner;

import org.junit.Test;

import com.ecis.fetch.special.FetchPager;

public class FetchPagerTest {

	@Test
	public void testGetBaidutiebaPagerUrl() {
		FetchPager testObj = new FetchPager();

		Scanner cin = new Scanner(System.in);
		String testUrlPost = cin.next();
		String testPagerQuery = "li.l_pager.pager_theme_2";

		testObj.getBaidutiebaPagerUrl(testUrlPost, testPagerQuery);
	}
}
