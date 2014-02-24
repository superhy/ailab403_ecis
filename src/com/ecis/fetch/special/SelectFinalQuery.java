package com.ecis.fetch.special;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SelectFinalQuery {

	public String getFinalQuery(Document document, String initQuery) {

		String[] listQuery = initQuery.split("OR");

		String finalQuery = null;
		for (String strQuery : listQuery) {
			if (document.select(strQuery).size() > 0) {
				finalQuery = strQuery;
				break;
			}
		}

		return finalQuery;
	}

	public String getFinalQuery(Element element, String initQuery) {

		String[] listQuery = initQuery.split("OR");

		String finalQuery = null;
		for (String strQuery : listQuery) {
			if (element.select(strQuery).size() > 0) {
				finalQuery = strQuery;
				break;
			}
		}

		return finalQuery;
	}
}
