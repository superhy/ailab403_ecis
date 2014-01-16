package com.ecis.grab.thread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ecis.model.Url;
import com.ecis.util.GetJsoupDocument;
import com.ecis.util.TransMD5;

public class GetBaiduTieBaUrlThread implements Callable<Set<Url>> {
	private Url url;

	public GetBaiduTieBaUrlThread(Url url) {
		this.url = url;
	}

	// 通过最后一页获得总页数，拼接出所有全部页面url
	public Set<String> anayPagination(String urlBa) throws Exception {
		// int subPonit = urlLastPage.lastIndexOf("=");
		//
		// String pageMainPart_Level = urlLastPage.substring(0, subPonit + 1);
		// int pageNum_Level = Integer.parseInt(urlLastPage
		// .substring(subPonit + 1));
		//
		// List<String> urlPageList_Level = new ArrayList<String>();
		// for (int i = 1; i <= pageNum_Level; i++) {
		// String urlEachPage = pageMainPart_Level + i;
		//
		// urlPageList_Level.add(urlEachPage);
		// }

		Document docRoot = GetJsoupDocument.getDocument(urlBa);

		// 获取分页区域信息
		Element elePager = docRoot.getElementById("frs_list_pager");
		// 判断此吧被封（找不到任何分页任何）的情况
		if (elePager == null) {
			return null;
		}

		// 用于存储该吧所有帖子链接的哈希表
		Set<String> listUrlPage = new HashSet<String>();
		// 第1页为该吧首页本身
		listUrlPage.add(urlBa);

		// 设置默认选取前（传参）页的帖子，百度是20
		final int numPageGrab = 20;
		// 初始化当前已加载页面的页数，一开始为1
		int numPageNow = 1;

		while (numPageNow < numPageGrab) {
			// 检查如果没有下一页这一标识，直接跳出
			Elements elesNext = elePager.select("a.next");
			if (elesNext == null) {
				break;
			}

			// 获取下一页url，加入帖子链接哈希表
			String urlNext = elesNext.first().attr("abs:href");
			listUrlPage.add(urlNext);
			numPageNow++;

			// 将分页区域元素更新为下一页所指向页面的分页区域元素
			elePager = GetJsoupDocument.getDocument(urlNext).getElementById(
					"frs_list_pager");
		}
		
		for (String string : listUrlPage) {
			System.out.println(string);
		}

		return listUrlPage;
	}

	// 根据每一页的分页url获取该页所有帖子url
	public Set<Url> getPostUrl(String urlSinglePage) throws Exception {
		Document docSinglePage = GetJsoupDocument.getDocument(urlSinglePage);

		Set<Url> listUrlPost = new HashSet<Url>();
		Elements elesPost = docSinglePage
				.select("div.threadlist_text.threadlist_title.j_th_tit*");

		// System.out.println(elesPost.size());

		for (Element elePost : elesPost) {
			String urlPost = elePost.select("a[href]").attr("abs:href");
			String urlPostMD5 = new TransMD5().getMD5Code(urlPost);

			Url urlModelPost = new Url(urlPost, urlPostMD5);

			listUrlPost.add(urlModelPost);

			System.out.println(urlPost);
		}

		return listUrlPost;
	}

	@Override
	public Set<Url> call() throws Exception {
		// 创建连接，获取下一层的全部元素
		// Document docLevel_01 = Jsoup.connect(url.getUrl()).timeout(600000)
		// .ignoreHttpErrors(true).get();
		// String urlLastPage_Level_01 = docLevel_01
		// .getElementsByClass("pagination").select("a[href]").last().attr("abs:href");
		//
		// List<String> urlPageList_Level_01 =
		// anayPagination(urlLastPage_Level_01);
		//
		// for (String urlEachPage_Level_01 : urlPageList_Level_01) {
		// Document docEachPage_Level_01 = Jsoup.connect(urlEachPage_Level_01)
		// .timeout(600000).ignoreHttpErrors(true).get();
		//
		// Elements elesLevel_01 = docEachPage_Level_01
		// .select("[class*=ba_href]");
		//
		// FileWriter fw = new FileWriter(new File(
		// "./file/baidutieba_ba_urlList.txt"));
		// for (Element eleLevel_01 : elesLevel_01) {
		// String urlLevel_02 = eleLevel_01.attr("abs:href");
		//
		// fw.write(urlLevel_02 + "\r\n");
		// System.out.println(urlLevel_02);
		// }
		//
		// fw.close();
		// }

		String urlBa = url.getUrl();

		// 创建帖子url库文件
		Document docTitle_Ba = GetJsoupDocument.getDocument(urlBa);

		// 获取贴吧计划抓取的所有分页url
		Set<String> listUrlPage = anayPagination(urlBa);

		// 预防贴吧被封的情况
		if (listUrlPage == null) {
			return null;
		}

		Set<Url> listUrlPost_Ba = new HashSet<Url>();

		for (String urlSinglePage : listUrlPage) {
			listUrlPost_Ba.addAll(getPostUrl(urlSinglePage));
		}

		return listUrlPost_Ba;
	}
}
