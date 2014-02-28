package com.iiimms.fetch;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.iiimms.model.ContentParame;

public interface FetchContent {

	// ҳ������
	String postLink = null;

	// ҳ��Ԫ����Ϣ
	static ContentParame cntParame = null;

	// ҳ���ĵ�������
	static Document docPostFirstPage = null;

	public String getPostLink();

	public void setPostLink(String postLink);

	public ContentParame getCntParame();

	public void setCntParame(ContentParame cntParame);

	public Document getDocPostFirstPage();

	// ��ʼ������ҳ���ĵ�����
	public void setDocPostFirstPage();

	/**
	 * ���Ի���������������Ϣ����������ݶ�Ϊ�����ĵ�·��
	 * 
	 * @param postParameXMLFilePath
	 */
	public void initContentParame(String postParameXMLFilePath);

	/*
	 * ���·���ע�ͣ���*����־��ʾֱ�ӻ�ȡ���ݣ��޴˱�־��ʾ�˷�������Ϊ����������׼��
	 */

	/**
	 * ��ȡ�����������ɣ�*��
	 * 
	 * @param baNameQuery
	 * @return
	 */
	public String getBaName(String baNameQuery);

	/**
	 * ��ȡ���ӱ��⣨*��
	 * 
	 * @param titleQuery
	 * @return
	 */
	public String getTitle(String titleQuery);

	/**
	 * ��ȡ���Ӹ���ҳ���ӣ����ϣ����������
	 * 
	 * @param fetchPagerMethod
	 * @param pagerQuery
	 * @return
	 */
	public List<String> getPagerUrl(String fetchPagerMethod, String pagerQuery);

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
			String replyTimeQuery);

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
			String replyTimeQuery);

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
			String replyTimeQuery);

	// ��ȡ����ȫ������
	public String getAllContent(String postParameXMLFilePath);
}
