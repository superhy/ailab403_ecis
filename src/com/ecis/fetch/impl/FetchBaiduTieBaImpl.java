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
		try {
			FetchBaiduTieBaImpl.docPostFirstPage = GetJsoupDocument
					.getDocument(postLink);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 初试话解析参数配置信息，参入参数暂定为配置文档
	public void initContentParame(String postParameXMLFilePath) {

		setDocPostFirstPage();
	}

	// 获取帖子所属贴吧名
	public String getBaName(String baNameQuery) {
		Document docBaName = getDocPostFirstPage();
		Element eleBaName = docBaName.select(baNameQuery).first();
		String strBaName = eleBaName.text();

		System.out.println(strBaName);

		return strBaName;
	}

	// 获取帖子标题
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
