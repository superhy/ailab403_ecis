package com.ecis.fetch.special;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ecis.util.JsoupDocumentUtil;

public class FetchPager {

	public List<String> getBaidutiebaPagerUrl(String urlPost, String pagerQuery) {
		// ������ŷ�ҳ����
		List<String> listPagerUrl = new ArrayList<String>();

		Document docPager = JsoupDocumentUtil.getDocument(urlPost);

		// �����ܵ��������ó�ÿ����ҳ�����ӣ���һҳ���ǵ�ǰҳ
		listPagerUrl.add(urlPost);
		Element elePagerDiv = docPager.select(pagerQuery).first();
		
		// ����ֻ��һҳ��û�з�ҳ��Ϣ�����
		if (elePagerDiv.select("a[href]").size() == 0) {

			return listPagerUrl;
		}
		String strPagerLast = elePagerDiv.select("a[href]").last()
				.attr("abs:href");
		int numPager = Integer.parseInt(strPagerLast.substring(strPagerLast
				.indexOf('=') + 1));
		for (int i = 2; i <= numPager; i++) {
			String strPagerEach = strPagerLast.substring(0,
					strPagerLast.indexOf('=') + 1)
					+ Integer.toString(i);
			listPagerUrl.add(strPagerEach);

			/* System.out.println(strPagerEach); */
		}

		return listPagerUrl;
	}
}
