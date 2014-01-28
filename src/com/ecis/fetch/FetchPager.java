package com.ecis.fetch;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ecis.util.GetJsoupDocument;

public class FetchPager {

	public List<String> getBaidutiebaPagerUrl(String urlPost) {
		// ������ŷ�ҳ����
		List<String> listPagerUrl = new ArrayList<String>();

		Document docPager = GetJsoupDocument.getDocument(urlPost);

		//�����ܵ��������ó�ÿ����ҳ�����ӣ�
		String pagerQuery = "li.l_pager.pager_theme_2";
		Element elePagerDiv = docPager.select(pagerQuery).first();
		String strPagerLast = elePagerDiv.select("a[href]").last().attr("abs:href");
		int numPager =Integer.parseInt(strPagerLast.substring(strPagerLast.length() - 1));
		for (int i = 1; i <= numPager; i++) {
			String strPagerEach = strPagerLast.substring(0, strPagerLast.length() - 1) + Integer.toString(i);
			listPagerUrl.add(strPagerEach);
			
			System.out.println(strPagerEach);
		}

		return listPagerUrl;
	}
}
