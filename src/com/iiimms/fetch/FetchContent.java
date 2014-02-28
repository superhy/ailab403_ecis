package com.iiimms.fetch;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.iiimms.model.ContentParame;

public interface FetchContent {

	// 页面链接
	String postLink = null;

	// 页面元素信息
	static ContentParame cntParame = null;

	// 页面文档加载项
	static Document docPostFirstPage = null;

	public String getPostLink();

	public void setPostLink(String postLink);

	public ContentParame getCntParame();

	public void setCntParame(ContentParame cntParame);

	public Document getDocPostFirstPage();

	// 初始化帖子页面文档变量
	public void setDocPostFirstPage();

	/**
	 * 初试话解析参数配置信息，参入参数暂定为配置文档路径
	 * 
	 * @param postParameXMLFilePath
	 */
	public void initContentParame(String postParameXMLFilePath);

	/*
	 * 以下方法注释，（*）标志表示直接获取内容，无此标志表示此方法是作为其他方法的准备
	 */

	/**
	 * 获取帖子所属贴吧（*）
	 * 
	 * @param baNameQuery
	 * @return
	 */
	public String getBaName(String baNameQuery);

	/**
	 * 获取帖子标题（*）
	 * 
	 * @param titleQuery
	 * @return
	 */
	public String getTitle(String titleQuery);

	/**
	 * 获取帖子各分页链接（集合），反射机制
	 * 
	 * @param fetchPagerMethod
	 * @param pagerQuery
	 * @return
	 */
	public List<String> getPagerUrl(String fetchPagerMethod, String pagerQuery);

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
			String replyTimeQuery);

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
			String replyTimeQuery);

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
			String replyTimeQuery);

	// 获取话题全部内容
	public String getAllContent(String postParameXMLFilePath);
}
