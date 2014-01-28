package com.ecis.fetch.test;

import java.util.Scanner;

import org.junit.Test;

import com.ecis.fetch.impl.FetchBaiduTieBaImpl;

public class FetchBaiduTieBaImplTest {

	@Test
	public void testGetBaName() {

		// ������������
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// �������Զ��󣬳�ʼ������ģ�����
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
}
