package com.ecis.grab.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ecis.grab.thread.GetBaiduTieBaUrlThread;
import com.ecis.model.Url;
import com.ecis.util.GetJsoupDocument;
import com.ecis.util.ParseProperties;
import com.ecis.util.TransMD5;

public class GrabBaiduTieBaImpl {

	private String seedUrl;

	public String getSeedUrl() {
		return seedUrl;
	}

	public void setSeedUrl(String seedUrl) {
		this.seedUrl = seedUrl;
	}

	// 第一阶段，分析主题树，目标：获取每个主题的总url
	public Set<Url> getThemeUrl() throws Exception {
		setSeedUrl(new ParseProperties().getSeedUrl("baidutieba"));

		Document docSeed = GetJsoupDocument.getDocument(seedUrl);

		// 获得右盒子的元素（详细列表），获取含有每个主题对应链接的元素
		// Element eleRightSec = seedDoc.getElementById("right-sec");
		// Elements elesTheme = eleRightSec.getElementsByTag("li");
		//
		// Set<Url> themesUrlSet = new HashSet<Url>();
		// for (Element eleTheme : elesTheme) {
		// // 获取每个主题的链接，生成对应的MD5标记，组合成实体类存入hashset<>（去重）
		// String themeUrl = eleTheme.select("a[href]").first()
		// .attr("abs:href");
		// String themeUrlMD5 = new TransMD5().getMD5Code(themeUrl);
		// Url urlModel = new Url(themeUrl, themeUrlMD5);
		//
		// themesUrlSet.add(urlModel);
		// }

		// 获取分页url列表，选取前numPageSet_Ba页（按最近流行排序），第一也是种子url本省
		int numPageSet_Ba = 1;

		List<Element> listElePage = docSeed.getElementsByClass("pagination")
				.select("a[href]").subList(0, numPageSet_Ba - 1);

		List<String> listUrlPage = new ArrayList<String>();
		listUrlPage.add(seedUrl);

		for (Element elePage : listElePage) {
			listUrlPage.add(elePage.attr("abs:href"));
		}

		// 所有页面的链接model哈希总表
		Set<Url> urlThemeSet = new HashSet<Url>();

		for (String urlPage : listUrlPage) {
			Document docPage = GetJsoupDocument.getDocument(urlPage);

			// 取出第一页叶子主题（吧）的链接元素
			Elements elesBa_page = docPage.select("a[class*=ba_href]");

			// 第一页的链接model哈希表（去重）
			Set<Url> urlThemeSet_page = new HashSet<Url>();
			for (Element eleBa_page : elesBa_page) {
				// 取出第一页所有叶子主题链接，并生成对应的MD5指纹
				String urlBa_page = eleBa_page.attr("abs:href");
				String urlBa_pageMD5 = new TransMD5().getMD5Code(urlBa_page);

				// 实体化链接model
				Url urlModel_page = new Url(urlBa_page, urlBa_pageMD5);

				// 将第一页的连接诶model加入model哈希表
				urlThemeSet_page.add(urlModel_page);

				// System.out.println(urlBa_page);
			}
			// 将第一页的链接model哈希表整体加入链接model哈希总表中
			urlThemeSet.addAll(urlThemeSet_page);
		}

		// System.out.println(urlThemeSet.size());

		return urlThemeSet;
	}

	// 分析从叶子主题到叶子结点（文章）的各层次，目标：获取叶子结点url（多线程）
	public Set<Url> getAllPostUrl() throws Exception {
		// 获取所有叶子主题链接model的哈希总表
		Set<Url> urlThemeSet = getThemeUrl();

		System.out.println("叶子主题url加载完成...");

		// 创建线程池
		ExecutorService exes = Executors.newCachedThreadPool();
		Set<Future<Set<Url>>> setThread = new HashSet<Future<Set<Url>>>();
		for (Url themeUrl : urlThemeSet) {
			// 创建线程任务
			GetBaiduTieBaUrlThread taskThread = new GetBaiduTieBaUrlThread(
					themeUrl);
			// 提交线程任务
			setThread.add(exes.submit(taskThread));
		}

		// 执行多线程并获取结果

		int numBa = 0;
		for (Future<Set<Url>> future : setThread) {

			Set<Url> listUrlPost_Ba = future.get();

			// 创建文本文档存储每个吧的所有帖子地址
			File filePostUrl_Ba = new File("./file/url/baidutieba/" + (++numBa)
					+ ".txt");
			if (!filePostUrl_Ba.exists()) {
				filePostUrl_Ba.createNewFile();
			}
			FileWriter fw = new FileWriter(filePostUrl_Ba);

			// 防止空吧的情况（该吧被禁）
			if (listUrlPost_Ba == null) {
				fw.write("forbidden");
				fw.close();
				
				continue;
			}

			for (Url urlSinglePost : listUrlPost_Ba) {
				fw.write(urlSinglePost.getUrl() + "\r\n");
			}
			fw.close();
		}

		// 关闭线程
		exes.shutdown();

		return null;
	}
}
