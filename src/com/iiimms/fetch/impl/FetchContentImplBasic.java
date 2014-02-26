package com.iiimms.fetch.impl;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.iiimms.fetch.special.FetchJsonPostTime_BaiduTieBa;
import com.iiimms.fetch.util.AnalyzerContentParameResource;
import com.iiimms.fetch.util.GetPostPageList;
import com.iiimms.model.ContentParame;
import com.iiimms.util.JsoupDocumentUtil;

public class FetchContentImplBasic {

	// 页面链接
	private static String postLink;

	// 页面元素信息
	private static ContentParame cntParame;

	// 页面文档加载项
	private static Document docPostFirstPage;

	public String getPostLink() {
		return postLink;
	}

	public void setPostLink(String postLink) {
		this.postLink = postLink;
	}

	public ContentParame getCntParame() {
		return cntParame;
	}

	public void setCntParame(ContentParame cntParame) {
		this.cntParame = cntParame;
	}

	public static Document getDocPostFirstPage() {
		return docPostFirstPage;
	}

	// 初始化帖子页面文档变量
	public static void setDocPostFirstPage() {
		FetchContentImplBasic.docPostFirstPage = JsoupDocumentUtil
				.getDocument(postLink);
	}

	/**
	 * 初试话解析参数配置信息，参入参数暂定为配置文档路径
	 * 
	 * @param postParameXMLFilePath
	 */
	public void initContentParame(String postParameXMLFilePath) {
		setDocPostFirstPage();

		AnalyzerContentParameResource analyzerObj = new AnalyzerContentParameResource();
		analyzerObj.setXmlResourcePath(postParameXMLFilePath);

		setCntParame(analyzerObj.getAllContentParameValue());
	}

	/*
	 * 以下方法注释，（*）标志表示直接获取内容，无此标志表示此方法是作为其他方法的准备
	 */

	/**
	 * 获取帖子所属贴吧（*）
	 * 
	 * @param baNameQuery
	 * @return
	 */
	public String getBaName(String baNameQuery) {
		String strBaName = "";

		// 应对主题名缺失的情况
		if (baNameQuery == null || baNameQuery.equals("")) {
			strBaName = "\r\n";

			return strBaName;
		}

		Document docBaName = getDocPostFirstPage();
		Element eleBaName = docBaName.select(baNameQuery).first();
		strBaName = eleBaName != null ? eleBaName.text() : "" + "\r\n";

		// System.out.println(strBaName);

		return strBaName;
	}

	/**
	 * 获取帖子标题（*）
	 * 
	 * @param titleQuery
	 * @return
	 */
	public String getTitle(String titleQuery) {
		String strTitle = "";

		// 应对贴子标题缺失的情况
		if (titleQuery == null || titleQuery.equals("")) {
			strTitle = "\r\n";

			return strTitle;
		}

		Document docTitle = getDocPostFirstPage();
		Element eleTitle = docTitle.select(titleQuery).first();
		strTitle = eleTitle != null ? eleTitle.text() : "" + "\r\n";

		// System.out.println(strTitle);

		return strTitle;
	}

	/**
	 * 获取帖子各分页链接（集合），反射机制
	 * 
	 * @param fetchPagerMethod
	 * @param pagerQuery
	 * @return
	 */
	public List<String> getPagerUrl(String fetchPagerMethod, String pagerQuery) {
		List<String> listPagerUrl = new ArrayList<String>();
		Class<?> classFetchPager = null;

		// 反射机制获取类名
		try {
			classFetchPager = Class.forName(new GetPostPageList().getClass()
					.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 反射机制调用制定名称的方法（传入参数并获取返回值）
		try {
			Method methodGetBaidutiebaPagerUrl = classFetchPager.getMethod(
					fetchPagerMethod, String.class, String.class);

			listPagerUrl = (List<String>) methodGetBaidutiebaPagerUrl.invoke(
					classFetchPager.newInstance(), getPostLink(), pagerQuery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listPagerUrl;
	}

	/**
	 * 获取每个跟帖的回复信息
	 * 
	 * @param elePostDiv
	 * @param replyDivQuery
	 * @param replyContentQuery
	 * @param replyAuthorQuery
	 * @param replyTimeQuery
	 * @return
	 */

	public String getReplyEachPost(Element elePostDiv, String replyDivQuery,
			String replyContentQuery, String replyAuthorQuery,
			String replyTimeQuery) {

		String strReplyContentEachPost = "";

		// 应对平行回复的情况（无叶子回复）
		if ((replyDivQuery == null || replyDivQuery.equals(""))
				&& (replyContentQuery == null || replyContentQuery.equals(""))
				&& (replyAuthorQuery == null || replyAuthorQuery.equals(""))
				&& (replyTimeQuery == null || replyTimeQuery.equals(""))) {

			strReplyContentEachPost = "\r\n";

			return strReplyContentEachPost;
		}

		// 获取单个回复元素
		Elements elesReplyDiv = elePostDiv.select(replyDivQuery);

		for (Element eleReplyDiv : elesReplyDiv) {

			// 获取回复作者信息
			Element eleReplyAuthorDiv = eleReplyDiv.select(replyAuthorQuery)
					.first();
			String strReplyAuthor = eleReplyAuthorDiv != null ? eleReplyAuthorDiv
					.text() : "";

			// 获取回复内容信息
			Element eleReplyContentDiv = eleReplyDiv.select(replyContentQuery)
					.first();
			String strReplyContent = eleReplyContentDiv != null ? eleReplyContentDiv
					.text() : "";

			// 获取回复时间信息
			Element eleReplyTimeDiv = eleReplyDiv.select(replyTimeQuery)
					.first();
			String strReplyTime = eleReplyTimeDiv != null ? eleReplyTimeDiv
					.text() : "";

			// 将分析出来的各部分内容组合
			strReplyContentEachPost += ("replyAuthor: " + strReplyAuthor
					+ "\r\nreplyContent:" + strReplyContent + "\r\nreplyTime: "
					+ strReplyTime + "\r\n");
		}

		// System.out.println(strReplyContentEachPost);

		return strReplyContentEachPost;
	}

	/**
	 * 获取每个分页的帖子内容
	 * 
	 * @param pageUrl
	 * @param postDivQuery
	 * @param postContentQuery
	 * @param postAuthorQuery
	 * @param postTimeQuery
	 * @param replyDivQuery
	 * @param replyContentQuery
	 * @param replyAuthorQuery
	 * @param replyTimeQuery
	 * @return
	 */

	public String getContentEachPager(String pageUrl, String postDivQuery,
			String postContentQuery, String postAuthorQuery,
			String postTimeQuery, String replyDivQuery,
			String replyContentQuery, String replyAuthorQuery,
			String replyTimeQuery) {

		String strContentEachPage = "";

		if ((postDivQuery == null || postDivQuery.equals(""))
				&& (postContentQuery == null || postContentQuery.equals(""))
				&& (postAuthorQuery == null || postAuthorQuery.equals(""))
				&& (postTimeQuery == null || postTimeQuery.equals(""))) {

			strContentEachPage = "\r\n";

			return strContentEachPage;
		}

		// 获取单独每页的文档信息
		Document docEachPage = JsoupDocumentUtil.getDocument(pageUrl);
		// 获取每个帖子div的元素集合
		Elements elesPostDiv = docEachPage.select(postDivQuery);

		for (Element elePostDiv : elesPostDiv) {

			// 获取跟帖作者信息
			Element elePostAuthorDiv = elePostDiv.select(postAuthorQuery)
					.first();
			String strPostAuthor = elePostAuthorDiv != null ? elePostAuthorDiv
					.text() : "";

			// 获取帖子内容信息
			Element elePostContentDiv = elePostDiv.select(postContentQuery)
					.first();
			String strPostContent = elePostContentDiv != null ? elePostContentDiv
					.text() : "";

			// 获取发帖时间及楼层信息
			Element elePostTimeDiv = elePostDiv.select(postTimeQuery).first();
			String strPostTime = elePostTimeDiv != null ? elePostTimeDiv.text()
					: "";

			// 百度的发帖时间在javascript中显示，需要单独编写解析json的方法
			if (pageUrl.indexOf("baidu") != -1) {
				strPostTime += (new FetchJsonPostTime_BaiduTieBa()
						.getBaiduTieBaPostTime(elePostDiv));
			}

			// 获取回复模块（通过调用单独的方法实现）
			String strReplyContentEachPost = getReplyEachPost(elePostDiv,
					replyDivQuery, replyContentQuery, replyAuthorQuery,
					replyTimeQuery);

			// 将分析出来的各部分内容组合
			strContentEachPage += ("author: " + strPostAuthor + "\r\ncontent:"
					+ strPostContent + "\r\ntime: " + strPostTime + "\r\n");
			strContentEachPage += ("------\r\nreply:\r\n"
					+ strReplyContentEachPost + "\r\n");
		}

		// System.out.println(strContentEachPage);

		return strContentEachPage;
	}

	/**
	 * 获取所有分页的帖子内容（*）
	 * 
	 * @param fetchPagerMethod
	 * @param pagerQuery
	 * @param postDivQuery
	 * @param postContentQuery
	 * @param postAuthorQuery
	 * @param postTimeQuery
	 * @param replyDivQuery
	 * @param replyContentQuery
	 * @param replyAuthorQuery
	 * @param replyTimeQuery
	 * @return
	 */
	public String getContentAllPager(String fetchPagerMethod,
			String pagerQuery, String postDivQuery, String postContentQuery,
			String postAuthorQuery, String postTimeQuery, String replyDivQuery,
			String replyContentQuery, String replyAuthorQuery,
			String replyTimeQuery) {

		// 获取某一帖子所有页面的链接列表
		List<String> listPostPage = getPagerUrl(fetchPagerMethod, pagerQuery);
		String strContentAllPost = "";

		// 按照链接列表逐一分析页面
		for (String strEachPostPage : listPostPage) {
			String strContentEachPage = getContentEachPager(strEachPostPage,
					postDivQuery, postContentQuery, postAuthorQuery,
					postTimeQuery, replyDivQuery, replyContentQuery,
					replyAuthorQuery, replyTimeQuery);

			strContentAllPost += strContentEachPage;
		}

		// System.out.println(strContentAllPost);

		return strContentAllPost;
	}

	// 获取话题全部内容
	public String getAllContent(String postParameXMLFilePath) {
		initContentParame(postParameXMLFilePath);

		String strAllContent = "";

		strAllContent += (getBaName(cntParame.getBaNameQuery()) + "\r\n");
		strAllContent += (getTitle(cntParame.getTitleQuery()) + "\r\n");
		strAllContent += (getContentAllPager(cntParame.getFetchPagerMethod(),
				cntParame.getPagerQuery(), cntParame.getPostDivQuery(),
				cntParame.getPostContentQuery(),
				cntParame.getPostAuthorQuery(), cntParame.getPostTimeQuery(),
				cntParame.getReplyDivQuery(), cntParame.getReplyContentQuery(),
				cntParame.getReplyAuthorQuery(), cntParame.getReplyTimeQuery()));

		return strAllContent;
	}
}
