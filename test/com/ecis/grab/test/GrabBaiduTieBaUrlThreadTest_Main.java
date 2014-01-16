package com.ecis.grab.test;

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
	
		String urlTest = "http://tieba.baidu.com/f?kw=%B9%AB%CE%F1%D4%B1";
		testObj.anayPagination(urlTest);
	}

	@Test
	public void testGetPostUrl() throws Exception {
		GetBaiduTieBaUrlThread testObj = new GetBaiduTieBaUrlThread(null);
		
		String urlTest = "http://tieba.baidu.com/f?kw=%C9%CF%BA%A3%BE%C5%D4%BA";
		testObj.getPostUrl(urlTest);
	}
}
