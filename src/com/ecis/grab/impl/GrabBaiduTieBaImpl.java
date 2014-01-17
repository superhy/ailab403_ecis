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

	// ��һ�׶Σ�������������Ŀ�꣺��ȡÿ���������url
	public Set<Url> getThemeUrl() throws Exception {
		setSeedUrl(new ParseProperties().getSeedUrl("baidutieba"));

		Document docSeed = GetJsoupDocument.getDocument(seedUrl);

		// ����Һ��ӵ�Ԫ�أ���ϸ�б�����ȡ����ÿ�������Ӧ���ӵ�Ԫ��
		// Element eleRightSec = seedDoc.getElementById("right-sec");
		// Elements elesTheme = eleRightSec.getElementsByTag("li");
		//
		// Set<Url> themesUrlSet = new HashSet<Url>();
		// for (Element eleTheme : elesTheme) {
		// // ��ȡÿ����������ӣ����ɶ�Ӧ��MD5��ǣ���ϳ�ʵ�������hashset<>��ȥ�أ�
		// String themeUrl = eleTheme.select("a[href]").first()
		// .attr("abs:href");
		// String themeUrlMD5 = new TransMD5().getMD5Code(themeUrl);
		// Url urlModel = new Url(themeUrl, themeUrlMD5);
		//
		// themesUrlSet.add(urlModel);
		// }

		// ��ȡ��ҳurl�б�ѡȡǰnumPageSet_Baҳ��������������򣩣���һҲ������url��ʡ
		int numPageSet_Ba = 1;

		List<Element> listElePage = docSeed.getElementsByClass("pagination")
				.select("a[href]").subList(0, numPageSet_Ba - 1);

		List<String> listUrlPage = new ArrayList<String>();
		listUrlPage.add(seedUrl);

		for (Element elePage : listElePage) {
			listUrlPage.add(elePage.attr("abs:href"));
		}

		// ����ҳ�������model��ϣ�ܱ�
		Set<Url> urlThemeSet = new HashSet<Url>();

		for (String urlPage : listUrlPage) {
			Document docPage = GetJsoupDocument.getDocument(urlPage);

			// ȡ����һҳҶ�����⣨�ɣ�������Ԫ��
			Elements elesBa_page = docPage.select("a[class*=ba_href]");

			// ��һҳ������model��ϣ��ȥ�أ�
			Set<Url> urlThemeSet_page = new HashSet<Url>();
			for (Element eleBa_page : elesBa_page) {
				// ȡ����һҳ����Ҷ���������ӣ������ɶ�Ӧ��MD5ָ��
				String urlBa_page = eleBa_page.attr("abs:href");
				String urlBa_pageMD5 = new TransMD5().getMD5Code(urlBa_page);

				// ʵ�廯����model
				Url urlModel_page = new Url(urlBa_page, urlBa_pageMD5);

				// ����һҳ��������model����model��ϣ��
				urlThemeSet_page.add(urlModel_page);

				// System.out.println(urlBa_page);
			}
			// ����һҳ������model��ϣ�������������model��ϣ�ܱ���
			urlThemeSet.addAll(urlThemeSet_page);
		}

		// System.out.println(urlThemeSet.size());

		return urlThemeSet;
	}

	// ������Ҷ�����⵽Ҷ�ӽ�㣨���£��ĸ���Σ�Ŀ�꣺��ȡҶ�ӽ��url�����̣߳�
	public Set<Url> getAllPostUrl() throws Exception {
		// ��ȡ����Ҷ����������model�Ĺ�ϣ�ܱ�
		Set<Url> urlThemeSet = getThemeUrl();

		System.out.println("Ҷ������url�������...");

		// �����̳߳�
		ExecutorService exes = Executors.newCachedThreadPool();
		Set<Future<Set<Url>>> setThread = new HashSet<Future<Set<Url>>>();
		for (Url themeUrl : urlThemeSet) {
			// �����߳�����
			GetBaiduTieBaUrlThread taskThread = new GetBaiduTieBaUrlThread(
					themeUrl);
			// �ύ�߳�����
			setThread.add(exes.submit(taskThread));
		}

		// ִ�ж��̲߳���ȡ���

		int numBa = 0;
		for (Future<Set<Url>> future : setThread) {

			Set<Url> listUrlPost_Ba = future.get();

			// �����ı��ĵ��洢ÿ���ɵ��������ӵ�ַ
			File filePostUrl_Ba = new File("./file/url/baidutieba/" + (++numBa)
					+ ".txt");
			if (!filePostUrl_Ba.exists()) {
				filePostUrl_Ba.createNewFile();
			}
			FileWriter fw = new FileWriter(filePostUrl_Ba);

			// ��ֹ�հɵ�������ðɱ�����
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

		// �ر��߳�
		exes.shutdown();

		return null;
	}
}
