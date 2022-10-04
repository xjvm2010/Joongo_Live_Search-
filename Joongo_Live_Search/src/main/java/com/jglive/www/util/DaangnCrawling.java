package com.jglive.www.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.jglive.www.vo.SearchOption;

public class DaangnCrawling {

	public List<Map<String, Object>> getContent_dg(SearchOption searchOption) {

		List<Map<String, Object>> items = new ArrayList<>();

		try {
			String searchTxt = "";
			searchTxt = searchOption.getSearchWord();
			Document doc;
			if(searchOption.getStartIndex() == 1 ) {
				doc = Jsoup.connect("https://www.daangn.com/search/" + searchTxt).get();
			}else {
				doc = Jsoup.connect("https://www.daangn.com/search/"+searchTxt+"/more/flea_market?page="+searchOption.getStartIndex()).get();
			}
			Elements els = doc.getElementsByClass("flea-market-article flat-card");

			for (int i = 0; i < els.size(); i++) {

				// map Object 생성
				Map<String, Object> temp = new HashMap<String, Object>();
				
				temp.put("title", els.get(i).getElementsByAttributeValue("class", "article-title").text());
				String str = els.get(i).getElementsByAttributeValue("class", "article-region-name").text();
				String[] array = str.split(" ");
				temp.put("mainLocationName", array[2]);
				temp.put("region", els.get(i).getElementsByAttributeValue("class", "article-region-name").text());
				
				temp.put("price", els.get(i).getElementsByAttributeValue("class", "article-price").text());
				temp.put("urlLink", "https://www.daangn.com"
						+ els.get(i).getElementsByAttributeValue("class", "flea-market-article-link").attr("href"));
				items.add(temp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return items;
	}

}
