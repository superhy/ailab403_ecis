package com.ecis.fetch.special;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ecis.model.ContentParame;

public class AnalyzerContentParameResource {

	static SAXReader xmlReader = new SAXReader();

	private Document docXML;
	private Element rootElement;
	private String xmlResourcePath;

	public String getXmlResourcePath() {
		return xmlResourcePath;
	}

	public void setXmlResourcePath(String xmlResourcePath) {
		this.xmlResourcePath = xmlResourcePath;
	}

	public Document getDocXML() {
		return docXML;
	}

	public void setDocXML(Document docXML) {
		this.docXML = docXML;
	}

	public Element getRootElement() {
		return rootElement;
	}

	public void setRootElement(Element rootElement) {
		this.rootElement = rootElement;
	}

	public void initXMLInfo() {
		try {
			setDocXML(xmlReader.read(new File(getXmlResourcePath())));
			setRootElement(docXML.getRootElement());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ContentParame getAllContentParameValue() {

		initXMLInfo();

		String eleBaNameQueryXpath = "//element[@id='baNameQuery']";
		String eleTitleQueryXpath = "//element[@id='titleQuery']";
		String elePagerQueryXpath = "//element[@id='pagerQuery']";
		String eleFetchPagerMethodXpath = "//element[@id='fetchPagerMethod']";
		String elePostDivQueryXpath = "//element[@id='postDivQuery']";
		String elePostContentQueryXpath = "//element[@id='postContentQuery']";
		String elePostAuthorQueryXpath = "//element[@id='postAuthorQuery']";
		String elePostTimeQueryXpath = "//element[@id='postTimeQuery']";
		String eleReplyDivQueryXpath = "//element[@id='replyDivQuery']";
		String eleReplyContentQueryXpath = "//element[@id='replyContentQuery']";
		String eleReplyAuthorQueryXpath = "//element[@id='replyAuthorQuery']";
		String eleReplyTimeQueryXpath = "//element[@id='replyTimeQuery']";

		Element eleBaNameQuery = (Element) docXML.selectNodes(
				eleBaNameQueryXpath).get(0);
		Element eleTitleQuery = (Element) docXML
				.selectNodes(eleTitleQueryXpath).get(0);
		Element elePagerQuery = (Element) docXML
				.selectNodes(elePagerQueryXpath).get(0);
		Element eleFetchPagerMethod = (Element) docXML.selectNodes(
				eleFetchPagerMethodXpath).get(0);
		Element elePostDivQuery = (Element) docXML.selectNodes(
				elePostDivQueryXpath).get(0);
		Element elePostContentQuery = (Element) docXML.selectNodes(
				elePostContentQueryXpath).get(0);
		Element elePostAuthorQuery = (Element) docXML.selectNodes(
				elePostAuthorQueryXpath).get(0);
		Element elePostTimeQuery = (Element) docXML.selectNodes(
				elePostTimeQueryXpath).get(0);
		Element eleReplyDivQuery = (Element) docXML.selectNodes(
				eleReplyDivQueryXpath).get(0);
		Element eleReplyContentQuery = (Element) docXML.selectNodes(
				eleReplyContentQueryXpath).get(0);
		Element eleReplyAuthorQuery = (Element) docXML.selectNodes(
				eleReplyAuthorQueryXpath).get(0);
		Element eleReplyTimeQuery = (Element) docXML.selectNodes(
				eleReplyTimeQueryXpath).get(0);

		String strBaNameQuery = eleBaNameQuery.elementText("value");
		String strTitleQuery = eleTitleQuery.elementText("value");
		String strPagerQuery = elePagerQuery.elementText("value");
		String strFetchPagerMethod = eleFetchPagerMethod.elementText("value");
		String strPostDivQuery = elePostDivQuery.elementText("value");
		String strPostContentQuery = elePostContentQuery.elementText("value");
		String strPostAuthorQuery = elePostAuthorQuery.elementText("value");
		String strPostTimeQuery = elePostTimeQuery.elementText("value");
		String strReplyDivQuery = eleReplyDivQuery.elementText("value");
		String strReplyContentQuery = eleReplyContentQuery.elementText("value");
		String strReplyAuthorQuery = eleReplyAuthorQuery.elementText("value");
		String strReplyTimeQuery = eleReplyTimeQuery.elementText("value");

		ContentParame contentParame = new ContentParame(strBaNameQuery,
				strTitleQuery, strPagerQuery, strFetchPagerMethod,
				strPostDivQuery, strPostContentQuery, strPostAuthorQuery,
				strPostTimeQuery, strReplyDivQuery, strReplyContentQuery,
				strReplyAuthorQuery, strReplyTimeQuery);

		return contentParame;
	}
}
