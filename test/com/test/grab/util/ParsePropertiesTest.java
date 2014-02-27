package com.test.grab.util;

import java.util.Scanner;

import org.junit.Test;

import com.iiimms.grab.util.ParseProperties;

public class ParsePropertiesTest {
	
	@Test
	public void testGetSeedUrl() throws Exception {
		ParseProperties testObj = new ParseProperties();
		
		Scanner cin = new Scanner(System.in);
		String testSeedName = cin.next();
		
		String testRes = testObj.getSeedUrl(testSeedName);
		System.out.println(testRes);
	}
}
