package com.test.fetch.impl;

import java.util.Scanner;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.ecis.fetch.impl.FetchContentImpl_BaiduTieBa;
import com.ecis.model.ContentParame;
import com.ecis.util.JsoupDocumentUtil;

public class FetchContentImpl_BaiduTieBaTest {

	@Test
	public void testGetBaName() {

		// ������������
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// �������Զ��󣬳�ʼ������ģ�����
		FetchContentImpl_BaiduTieBa testObj = new FetchContentImpl_BaiduTieBa();
		testObj.setPostLink(strPostLink);
		testObj.initContentParame(null);

		String baNameQuery = "a#tab_forumname";
		testObj.getBaName(baNameQuery);
	}

	@Test
	public void testGetTitle() {

		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		FetchContentImpl_BaiduTieBa testObj = new FetchContentImpl_BaiduTieBa();
		testObj.setPostLink(strPostLink);
		testObj.initContentParame(null);

		String titleQuery = "h1.core_title_txt";
		testObj.getTitle(titleQuery);
	}

	@Test
	public void testGetPagerUrl() {

		Scanner cin = new Scanner(System.in);
		String strMethod = "getBaidutiebaPagerUrl";
		String strPostLink = cin.next();

		FetchContentImpl_BaiduTieBa testObj = new FetchContentImpl_BaiduTieBa();
		testObj.setPostLink(strPostLink);
		testObj.initContentParame(null);

		String pagerQuery = "li.l_pager.pager_theme_2";
		for (String strResult : testObj.getPagerUrl(strMethod, pagerQuery)) {
			System.out.println(strResult);
		}
	}

	@Test
	public void testGetReplyEachPost() {

		Scanner cin = new Scanner(System.in);
		String strPageUrl = cin.next();

		FetchContentImpl_BaiduTieBa testObj = new FetchContentImpl_BaiduTieBa();

		Document docTestPage = JsoupDocumentUtil.getDocument(strPageUrl);
		Element eleTestPost = docTestPage.select("div[class*=l_post]").get(1);

		String replyDivQuery = "li[class*=lzl_single_post]";
		String replyContentQuery = "span.lzl_content_main";
		String replyAuthorQuery = "a.at.j_user_card";
		String replyTimeQuery = "span.lzl_time";
		testObj.getReplyEachPost(eleTestPost, replyDivQuery, replyContentQuery,
				replyAuthorQuery, replyTimeQuery);
	}

	@Test
	public void testGetContentEachPager() {

		Scanner cin = new Scanner(System.in);
		String strPageUrl = cin.next();

		FetchContentImpl_BaiduTieBa testObj = new FetchContentImpl_BaiduTieBa();
		// testObj.setPostLink(strPostLink);
		// testObj.initContentParame(null);

		String postDivQuery = "div[class*=l_post]";
		String postContentQuery = "div.p_content";
		String postAuthorQuery = "a[class*=p_author_name]";
		String postTimeQuery = "ul.p_tail";
		String replyDivQuery = "li[class*=lzl_single_post]";
		String replyContentQuery = "span.lzl_content_main";
		String replyAuthorQuery = "a.at.j_user_card";
		String replyTimeQuery = "span.lzl_time";
		testObj.getContentEachPager(strPageUrl, postDivQuery, postContentQuery,
				postAuthorQuery, postTimeQuery, replyDivQuery,
				replyContentQuery, replyAuthorQuery, replyTimeQuery);
	}

	@Test
	public void testGetContentAllPager() {

		Scanner cin = new Scanner(System.in);
		String strMethod = "getBaidutiebaPagerUrl";
		String strPostLink = cin.next();

		FetchContentImpl_BaiduTieBa testObj = new FetchContentImpl_BaiduTieBa();
		testObj.setPostLink(strPostLink);
		testObj.initContentParame(null);

		String pagerQuery = "li[class*=l_pager]";
		String postDivQuery = "div[class*=l_post]";
		String postContentQuery = "div.p_content";
		String postAuthorQuery = "a[class*=p_author_name]";
		String postTimeQuery = "ul.p_tail";
		String replyDivQuery = "li[class*=lzl_single_post]";
		String replyContentQuery = "span.lzl_content_main";
		String replyAuthorQuery = "a.at.j_user_card";
		String replyTimeQuery = "span.lzl_time";
		testObj.getContentAllPager(strMethod, pagerQuery, postDivQuery,
				postContentQuery, postAuthorQuery, postTimeQuery,
				replyDivQuery, replyContentQuery, replyAuthorQuery,
				replyTimeQuery);
	}
	
	
}
