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

	// ҳ������
	private static String postLink;

	// ҳ��Ԫ����Ϣ
	private static ContentParame cntParame;

	// ҳ���ĵ�������
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

	// ��ʼ������ҳ���ĵ�����
	public static void setDocPostFirstPage() {
		FetchContentImplBasic.docPostFirstPage = JsoupDocumentUtil
				.getDocument(postLink);
	}

	/**
	 * ���Ի���������������Ϣ����������ݶ�Ϊ�����ĵ�·��
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
	 * ���·���ע�ͣ���*����־��ʾֱ�ӻ�ȡ���ݣ��޴˱�־��ʾ�˷�������Ϊ����������׼��
	 */

	/**
	 * ��ȡ�����������ɣ�*��
	 * 
	 * @param baNameQuery
	 * @return
	 */
	public String getBaName(String baNameQuery) {
		String strBaName = "";

		// Ӧ��������ȱʧ�����
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
	 * ��ȡ���ӱ��⣨*��
	 * 
	 * @param titleQuery
	 * @return
	 */
	public String getTitle(String titleQuery) {
		String strTitle = "";

		// Ӧ�����ӱ���ȱʧ�����
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
	 * ��ȡ���Ӹ���ҳ���ӣ����ϣ����������
	 * 
	 * @param fetchPagerMethod
	 * @param pagerQuery
	 * @return
	 */
	public List<String> getPagerUrl(String fetchPagerMethod, String pagerQuery) {
		List<String> listPagerUrl = new ArrayList<String>();
		Class<?> classFetchPager = null;

		// ������ƻ�ȡ����
		try {
			classFetchPager = Class.forName(new GetPostPageList().getClass()
					.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ������Ƶ����ƶ����Ƶķ����������������ȡ����ֵ��
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
	 * ��ȡÿ�������Ļظ���Ϣ
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

		// Ӧ��ƽ�лظ����������Ҷ�ӻظ���
		if ((replyDivQuery == null || replyDivQuery.equals(""))
				&& (replyContentQuery == null || replyContentQuery.equals(""))
				&& (replyAuthorQuery == null || replyAuthorQuery.equals(""))
				&& (replyTimeQuery == null || replyTimeQuery.equals(""))) {

			strReplyContentEachPost = "\r\n";

			return strReplyContentEachPost;
		}

		// ��ȡ�����ظ�Ԫ��
		Elements elesReplyDiv = elePostDiv.select(replyDivQuery);

		for (Element eleReplyDiv : elesReplyDiv) {

			// ��ȡ�ظ�������Ϣ
			Element eleReplyAuthorDiv = eleReplyDiv.select(replyAuthorQuery)
					.first();
			String strReplyAuthor = eleReplyAuthorDiv != null ? eleReplyAuthorDiv
					.text() : "";

			// ��ȡ�ظ�������Ϣ
			Element eleReplyContentDiv = eleReplyDiv.select(replyContentQuery)
					.first();
			String strReplyContent = eleReplyContentDiv != null ? eleReplyContentDiv
					.text() : "";

			// ��ȡ�ظ�ʱ����Ϣ
			Element eleReplyTimeDiv = eleReplyDiv.select(replyTimeQuery)
					.first();
			String strReplyTime = eleReplyTimeDiv != null ? eleReplyTimeDiv
					.text() : "";

			// �����������ĸ������������
			strReplyContentEachPost += ("replyAuthor: " + strReplyAuthor
					+ "\r\nreplyContent:" + strReplyContent + "\r\nreplyTime: "
					+ strReplyTime + "\r\n");
		}

		// System.out.println(strReplyContentEachPost);

		return strReplyContentEachPost;
	}

	/**
	 * ��ȡÿ����ҳ����������
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

		// ��ȡ����ÿҳ���ĵ���Ϣ
		Document docEachPage = JsoupDocumentUtil.getDocument(pageUrl);
		// ��ȡÿ������div��Ԫ�ؼ���
		Elements elesPostDiv = docEachPage.select(postDivQuery);

		for (Element elePostDiv : elesPostDiv) {

			// ��ȡ����������Ϣ
			Element elePostAuthorDiv = elePostDiv.select(postAuthorQuery)
					.first();
			String strPostAuthor = elePostAuthorDiv != null ? elePostAuthorDiv
					.text() : "";

			// ��ȡ����������Ϣ
			Element elePostContentDiv = elePostDiv.select(postContentQuery)
					.first();
			String strPostContent = elePostContentDiv != null ? elePostContentDiv
					.text() : "";

			// ��ȡ����ʱ�估¥����Ϣ
			Element elePostTimeDiv = elePostDiv.select(postTimeQuery).first();
			String strPostTime = elePostTimeDiv != null ? elePostTimeDiv.text()
					: "";

			// �ٶȵķ���ʱ����javascript����ʾ����Ҫ������д����json�ķ���
			if (pageUrl.indexOf("baidu") != -1) {
				strPostTime += (new FetchJsonPostTime_BaiduTieBa()
						.getBaiduTieBaPostTime(elePostDiv));
			}

			// ��ȡ�ظ�ģ�飨ͨ�����õ����ķ���ʵ�֣�
			String strReplyContentEachPost = getReplyEachPost(elePostDiv,
					replyDivQuery, replyContentQuery, replyAuthorQuery,
					replyTimeQuery);

			// �����������ĸ������������
			strContentEachPage += ("author: " + strPostAuthor + "\r\ncontent:"
					+ strPostContent + "\r\ntime: " + strPostTime + "\r\n");
			strContentEachPage += ("------\r\nreply:\r\n"
					+ strReplyContentEachPost + "\r\n");
		}

		// System.out.println(strContentEachPage);

		return strContentEachPage;
	}

	/**
	 * ��ȡ���з�ҳ���������ݣ�*��
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

		// ��ȡĳһ��������ҳ��������б�
		List<String> listPostPage = getPagerUrl(fetchPagerMethod, pagerQuery);
		String strContentAllPost = "";

		// ���������б���һ����ҳ��
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

	// ��ȡ����ȫ������
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
