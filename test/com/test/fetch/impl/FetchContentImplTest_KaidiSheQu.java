package com.test.fetch.impl;

import java.util.Scanner;

import org.junit.Test;

import com.iiimms.fetch.impl.FetchContentImplBasic;

public class FetchContentImplTest_KaidiSheQu {

	/**
	 * ������̳��ʱ�޷�����
	 */

	@Test
	public void testGetBaName() {
		// ������������
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// �������Զ��󣬳�ʼ������ģ�����
		FetchContentImplBasic testObj = new FetchContentImplBasic();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String baNameQuery = "div.forum";
		System.out.println(testObj.getBaName(baNameQuery));
	}
}
