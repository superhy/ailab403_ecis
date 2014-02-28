package com.iiimms.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpClientJsoupDocumentUtil {

	public static Document getDocument(String url) {
		try {

			HttpClient httpClient = new HttpClient();

			GetMethod getMethod = new GetMethod(url);

			// �������ӳ�ʱ�Ͷ�����ʱ
			httpClient.getHttpConnectionManager().getParams()
					.setConnectionTimeout(600000);
			httpClient.getHttpConnectionManager().getParams()
					.setSoTimeout(600000);

			// ���ú��Թ���ҳ��
			httpClient.getHttpConnectionManager().getParams()
					.setStaleCheckingEnabled(false);

			httpClient.executeMethod(getMethod);

			String strResponseHtml = getMethod.getResponseBodyAsString();
			getMethod.releaseConnection();

			return Jsoup.parse(strResponseHtml);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
