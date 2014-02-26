package com.test.fetch.special;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.iiimms.fetch.special.GetPostPageList;

public class GetPostPageListTest {

	@Test
	public void testGetBaidutiebaPagerUrl() {
		GetPostPageList testObj = new GetPostPageList();

		Scanner cin = new Scanner(System.in);
		String testUrlPost = cin.next();
		String testPagerQuery = "li[class*=l_pager]";

		testObj.getBaidutiebaPagerUrl(testUrlPost, testPagerQuery);
	}

	@Test
	public void testGetFenghuangluntanPagerUrl() {
		GetPostPageList testObj = new GetPostPageList();

		Scanner cin = new Scanner(System.in);
		String testUrlPost = cin.next();
		String testPagerQuery = "div.numb_post2";

		List<String> testList = testObj.getFenghuangluntanPagerUrl(testUrlPost,
				testPagerQuery);

		for (String string : testList) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testGetKaidiSheQuPagerUrl() {
		
	}

	@Test
	public void testGetMaopuTieTiePagerUrl() {
		GetPostPageList testObj = new GetPostPageList();

		Scanner cin = new Scanner(System.in);
		String testUrlPost = cin.next();
		String testPagerQuery = "div.page";

		List<String> testList = testObj.getMaoputietiePagerUrl(testUrlPost,
				testPagerQuery);

		for (String string : testList) {
			System.out.println(string);
		}
	}
}
