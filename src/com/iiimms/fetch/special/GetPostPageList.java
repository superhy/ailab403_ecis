package com.iiimms.fetch.special;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.iiimms.util.JsoupDocumentUtil;

public class GetPostPageList {

	public List<String> getBaidutiebaPagerUrl(String urlPost, String pagerQuery) {
		// 容器存放分页链接
		List<String> listPagerUrl = new ArrayList<String>();

		Document docPager = JsoupDocumentUtil.getDocument(urlPost);

		// 根据总的链接数得出每个分页的链接，第一页就是当前页
		listPagerUrl.add(urlPost);
		Element elePagerDiv = docPager.select(pagerQuery).first();

		// 假如只有一页，没有分页信息的情况
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

	public List<String> getFenghuangluntanPagerUrl(String urlPost,
			String pagerQuery) {

		List<String> listPagerUrl = new ArrayList<String>();

		Document docPager = JsoupDocumentUtil.getDocument(urlPost);

		// 根据总的链接数得出每个分页的链接，第一页就是当前页
		listPagerUrl.add(urlPost);

		// 假如只有一页，没有分页信息的情况
		if (docPager.select(pagerQuery).size() == 0) {

			return listPagerUrl;
		}
		Element elePagerDiv = docPager.select(pagerQuery).first();
		String strLastPage = elePagerDiv.select("a[href*=viewthread]").last()
				.attr("abs:href");

		// System.out.println(strLastPage);

		String strPageModel = strLastPage.substring(0,
				strLastPage.lastIndexOf("=") + 1);
		int numPages = Integer.valueOf(strLastPage.substring(
				strLastPage.lastIndexOf("=") + 1, strLastPage.length()));

		for (int i = 2; i <= numPages; i++) {
			listPagerUrl.add(strPageModel + i);
		}

		return listPagerUrl;
	}

	public List<String> getKaidiSheQuPagerUrl(String urlPost, String pagerQuery) {

		return null;
	}

	public List<String> getMaoputietiePagerUrl(String urlPost, String pagerQuery) {

		List<String> listPagerUrl = new ArrayList<String>();

		Document docPager = JsoupDocumentUtil.getDocument(urlPost);

		// 根据总的链接数得出每个分页的链接，第一页就是当前页
		listPagerUrl.add(urlPost);

		// 假如只有一页，没有分页信息的情况
		if (docPager.select(pagerQuery).size() == 0) {

			return listPagerUrl;
		}

		Element elePagerDiv = docPager.select(pagerQuery).first();
		while (elePagerDiv.select("a.end").size() != 0) {
			String strNextPage = elePagerDiv.select("a.end").first()
					.attr("abs:href");

			listPagerUrl.add(strNextPage);

			docPager = JsoupDocumentUtil.getDocument(strNextPage);
			elePagerDiv = docPager.select(pagerQuery).first();
		}

		return listPagerUrl;
	}

	public List<String> getPeopleshequPagerUrl(String urlPost, String pagerQuery) {

		List<String> listPagerUrl = new ArrayList<String>();

		Document docPager = JsoupDocumentUtil.getDocument(urlPost);

		// 根据总的链接数得出每个分页的链接，第一页就是当前页
		listPagerUrl.add(urlPost);

		// 假如只有一页，没有分页信息的情况
		if (docPager.select(pagerQuery).size() == 0) {

			return listPagerUrl;
		}

		String strPageModel = urlPost.substring(0, urlPost.indexOf("_") + 1);

		Element elePagerDiv = docPager.select(pagerQuery).first();
		String strPagerJavaScript = elePagerDiv.select("script[type^=text]")
				.first().toString();
		int numPages = Integer
				.parseInt(""
						+ strPagerJavaScript.charAt(strPagerJavaScript
								.indexOf(',') + 1));

		for (int i = 2; i <= numPages; i++) {
			listPagerUrl.add(strPageModel + i);
		}

		return listPagerUrl;
	}

	public List<String> getSinaluntanPagerUrl(String urlPost, String pagerQuery) {

		List<String> listPagerUrl = new ArrayList<String>();

		Document docPager = JsoupDocumentUtil.getDocument(urlPost);

		// 根据总的链接数得出每个分页的链接，第一页就是当前页
		listPagerUrl.add(urlPost);

		// 假如只有一页，没有分页信息的情况
		if (docPager.select(pagerQuery).size() == 0) {

			return listPagerUrl;
		}

		Element elePagerDiv = docPager.select(pagerQuery).first();
		String strLastPage = elePagerDiv.select("a:not(.next)").last()
				.attr("abs:href");

		String strPageModel = strLastPage.substring(0,
				strLastPage.lastIndexOf("=") + 1);
		int numPages = Integer.valueOf(strLastPage.substring(
				strLastPage.lastIndexOf("=") + 1, strLastPage.length()));

		for (int i = 2; i <= numPages; i++) {
			listPagerUrl.add(strPageModel + i);
		}

		return listPagerUrl;
	}

	public List<String> getSouhushequPagerUrl(String urlPost, String pagerQuery) {

		return null;
	}

	public List<String> getTianyaluntanPagerUrl(String urlPost,
			String pagerQuery) {

		return null;
	}

	public List<String> getWy163luntanPagerUrl(String urlPost, String pagerQuery) {

		return null;
	}

}
