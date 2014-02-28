package com.iiimms.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BasicJsoupDocumentUtil {

	public static Document getDocument(String url) {
		try {

			// �������ӳ�ʱ�Ͷ�����ʱ
			// ���ú��Թ���ҳ��
			return Jsoup.connect(url).timeout(600000).ignoreHttpErrors(true)
					.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
