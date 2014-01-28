package com.ecis.fetch.test;

import java.util.Scanner;

import org.junit.Test;

import com.ecis.fetch.FetchPager;

public class FetchPagerTest {
	
	@Test
	public void testGetBaidutiebaPagerUrl() {
		FetchPager testObj = new FetchPager();
		
		Scanner cin = new Scanner(System.in);
		String testUrlPost = cin.next();
		
		testObj.getBaidutiebaPagerUrl(testUrlPost);
	}
}
