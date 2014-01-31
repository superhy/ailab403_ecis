package com.ecis.fetch.test;

import java.util.Scanner;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.ecis.fetch.impl.FetchContentImpl_BaiduTieBa;
import com.ecis.model.ContentParame;
import com.ecis.util.GetJsoupDocument;

public class FetchBaiduTieBaImplTest {

	@Test
	public void testGetBaName() {

		// 输入帖子链接
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// 建立测试对象，初始化帖子模版参数
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

		String testPagerQuery = "li.l_pager.pager_theme_2";
		for (String strResult : testObj.getPagerUrl(strMethod, testPagerQuery)) {
			System.out.println(strResult);
		}
	}

	@Test
	public void testGetReplyEachPost() {

		Scanner cin = new Scanner(System.in);
		String strPageUrl = cin.next();

		FetchContentImpl_BaiduTieBa testObj = new FetchContentImpl_BaiduTieBa();

		Document docTestPage = GetJsoupDocument.getDocument(strPageUrl);
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
}
