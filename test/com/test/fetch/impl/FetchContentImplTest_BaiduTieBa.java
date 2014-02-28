package com.test.fetch.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.iiimms.fetch.impl.BasicFetchContentImpl;
import com.iiimms.model.ContentParame;
import com.iiimms.util.BasicJsoupDocumentUtil;

public class FetchContentImplTest_BaiduTieBa {

	@Test
	public void testGetBaName() {

		// 输入帖子链接
		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		// 建立测试对象，初始化帖子模版参数
		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String baNameQuery = "a#tab_forumname";
		testObj.getBaName(baNameQuery);
	}

	@Test
	public void testGetTitle() {

		Scanner cin = new Scanner(System.in);
		String strPostLink = cin.next();

		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String titleQuery = "h1.core_title_txt";
		testObj.getTitle(titleQuery);
	}

	@Test
	public void testGetPagerUrl() {

		Scanner cin = new Scanner(System.in);
		String strMethod = "getBaidutiebaPagerUrl";
		String strPostLink = cin.next();

		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

		String pagerQuery = "li.l_pager.pager_theme_2";
		for (String strResult : testObj.getPagerUrl(strMethod, pagerQuery)) {
			System.out.println(strResult);
		}
	}

	@Test
	public void testGetReplyEachPost() {

		Scanner cin = new Scanner(System.in);
		String strPageUrl = cin.next();

		BasicFetchContentImpl testObj = new BasicFetchContentImpl();

		Document docTestPage = BasicJsoupDocumentUtil.getDocument(strPageUrl);
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

		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		// testObj.setPostLink(strPostLink);
		// testObj.setDocPostFirstPage();

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

		BasicFetchContentImpl testObj = new BasicFetchContentImpl();
		testObj.setPostLink(strPostLink);
		testObj.setDocPostFirstPage();

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

	@Test
	public void testGetAllContent() {
		BasicFetchContentImpl testObj = new BasicFetchContentImpl();

		String postParameXMLFilePath = "./src/contentParameResource/baidutiebaParame.xml";
		String strPostLink = new Scanner(System.in).next();

		testObj.setPostLink(strPostLink);
		String strResult = testObj.getAllContent(postParameXMLFilePath);

		try {
			File fileResult = new File("./file/content/testBaiduContent.txt");
			if (!fileResult.exists()) {
				fileResult.createNewFile();
			}

			FileWriter fw = new FileWriter(fileResult);
			fw.write(strResult);

			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(strResult);
	}
}
