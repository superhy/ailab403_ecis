package com.ecis.fetch.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ecis.model.ContentParame;
import com.ecis.util.GetJsoupDocument;

public class FetchBaiduTieBaImpl {

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
		try {
			FetchBaiduTieBaImpl.docPostFirstPage = GetJsoupDocument
					.getDocument(postLink);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���Ի���������������Ϣ����������ݶ�Ϊ�����ĵ�
	public void initContentParame(String postParameXMLFilePath) {

		setDocPostFirstPage();
	}

	// ��ȡ��������������
	public String getBaName(String baNameQuery) {
		Document docBaName = getDocPostFirstPage();
		Element eleBaName = docBaName.select(baNameQuery).first();
		String strBaName = eleBaName.text();

		System.out.println(strBaName);

		return strBaName;
	}

	// ��ȡ���ӱ���
	public String getTitle(String titleQuery) {
		Document docTitle = getDocPostFirstPage();
		Element eleTitle = docTitle.select(titleQuery).first();
		String strTitle = eleTitle.text();

		System.out.println(strTitle);

		return strTitle;
	}

	
	public List<String> getPagerUrl(String fetchPagerMethod) {
		List<String> listPagerUrl = new ArrayList<String>();

		return null;
	}
}
