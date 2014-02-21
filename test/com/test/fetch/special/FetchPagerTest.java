package com.test.fetch.special;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.ecis.fetch.special.FetchPager;

public class FetchPagerTest {

	@Test
	public void testGetBaidutiebaPagerUrl() {
		FetchPager testObj = new FetchPager();

		Scanner cin = new Scanner(System.in);
		String testUrlPost = cin.next();
		String testPagerQuery = "li[class*=l_pager]";

		testObj.getBaidutiebaPagerUrl(testUrlPost, testPagerQuery);
	}

	@Test
	public void testGetFenghuangluntanPagerUrl() {
		FetchPager testObj = new FetchPager();

		Scanner cin = new Scanner(System.in);
		String testUrlPost = cin.next();
		String testPagerQuery = "div.numb_post2";

		List<String> testList = testObj.getFenghuangluntanPagerUrl(testUrlPost,
				testPagerQuery);
		
		for (String string : testList) {
			System.out.println(string);
		}
	}
}
