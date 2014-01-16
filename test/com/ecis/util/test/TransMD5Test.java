package com.ecis.util.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import com.ecis.util.TransMD5;

public class TransMD5Test {

	@Test
	public void testGetMD5Code() {
		Scanner cin = new Scanner(System.in);
		String testUrl = cin.next();
		
		System.out.println(new TransMD5().getMD5Code(testUrl));
	}

}
