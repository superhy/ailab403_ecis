package com.test.grab.thread;

import java.util.Scanner;

import org.junit.Test;

import com.ecis.grab.impl.GrabBaiduTieBaImpl;
import com.ecis.grab.thread.GetBaiduTieBaUrlThread;

public class GrabBaiduTieBaUrlThreadTest_Main {

	public static void main(String[] args) throws Exception {
		GrabBaiduTieBaImpl testObj = new GrabBaiduTieBaImpl();
		
		testObj.getAllPostUrl();
	}

	@Test
	public void testAnayPagination() throws Exception {
		GetBaiduTieBaUrlThread testObj = new GetBaiduTieBaUrlThread(null);
	
		
		String urlTest = new Scanner(System.in).next();
		testObj.anayPagination(urlTest);
	}

	@Test
	public void testGetPostUrl() throws Exception {
		GetBaiduTieBaUrlThread testObj = new GetBaiduTieBaUrlThread(null);
		
		String urlTest = new Scanner(System.in).next();
		testObj.getPostUrl(urlTest);
	}
}
