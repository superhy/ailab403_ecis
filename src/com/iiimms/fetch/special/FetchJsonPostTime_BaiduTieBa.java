package com.iiimms.fetch.special;

import net.sf.json.JSONObject;

import org.jsoup.nodes.Element;

public class FetchJsonPostTime_BaiduTieBa {

	public String getBaiduTieBaPostTime(Element eleBaiduTieBaPostDiv) {
		String strDataField = eleBaiduTieBaPostDiv
				.select("div[data-field*=date]").first().attr("data-field");

		// ʹ��java��json�Ľ�������������dateԪ��
		JSONObject jsonObject = JSONObject.fromObject(strDataField)
				.getJSONObject("content");
		String strDate = jsonObject.getString("date");

		return strDate;
	}
}
