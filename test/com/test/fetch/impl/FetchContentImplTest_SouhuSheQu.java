package com.test.fetch.impl;

import java.util.Scanner;

import org.junit.Test;

import com.iiimms.fetch.impl.BasicFetchContentImpl;

public class FetchContentImplTest_SouhuSheQu {

	@Test
	public void testGetBaName() {
		// ������������
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// �������Զ��󣬳�ʼ������ģ�����
		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String baNameQuery = "div.navigation>a[href$=threads]";
		System.out.println(testObj.getBaName(baNameQuery));
	}

	@Test
	public void testGetTitle() {

		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String titleQuery = "em.title";
		System.out.println(testObj.getTitle(titleQuery));
	}
	
	
}
