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


public class DangCrawling {
	
	public List<Map<String, Object>> getContent_dg(SearchOption searchOption){
		
		List<Map<String, Object>> items = new ArrayList<>();
		
		try {
			
			//검색어
			String searchTxt = "";
			
			//검색 단어
			searchTxt = searchOption.getSearchWord();
			
			  //검색결과 최초 6건
			  Document doc = Jsoup.connect("https://www.daangn.com/search/"+searchTxt).get();
			  Elements els = doc.getElementsByClass("flea-market-article flat-card");
			  
			  for(int i=0; i<els.size(); i++) {
				  
				  //map Object 생성
				  Map<String, Object> temp = new HashMap<String, Object>();
				  
				  temp.put("title", els.get(i).getElementsByAttributeValue("class","article-title").text());
				  temp.put("region", els.get(i).getElementsByAttributeValue("class", "article-region-name").text());
				  temp.put("price", els.get(i).getElementsByAttributeValue("class", "article-price").text());
				  temp.put("urlLink", "https://www.daangn.com" + els.get(i).getElementsByAttributeValue("class", "flea-market-article-link").attr("href"));
				  
				  //map object list에 넣어주기
				  items.add(temp);
			  }
			  
			  //더보기 시 2번 째 페이지 결과
			  Document doc2 = Jsoup.connect("https://www.daangn.com/search/"+searchTxt+"/more/flea_market?page=2").get();
			  Elements els2 = doc2.getElementsByClass("flea-market-article flat-card");
			  
			  for(int i=0; i<els2.size(); i++) {
				  
				  //map Object 생성
				  Map<String, Object> temp = new HashMap<String, Object>();
				  
				  temp.put("title", els2.get(i).getElementsByAttributeValue("class","article-title").text());
				  temp.put("region", els2.get(i).getElementsByAttributeValue("class", "article-region-name").text());
				  temp.put("price", els2.get(i).getElementsByAttributeValue("class", "article-price").text());
				  temp.put("urlLink", "https://www.daangn.com" + els2.get(i).getElementsByAttributeValue("class", "flea-market-article-link").attr("href"));
				  
				  //map object list에 넣어주기
				  items.add(temp);
			  }
			  
			  //더보기 시 3번 째 페이지 결과(총 게시글 데이터 10개만 가져오기)
			  Document doc3 = Jsoup.connect("https://www.daangn.com/search/"+searchTxt+"/more/flea_market?page=3").get();
			  Elements els3 = doc3.getElementsByClass("flea-market-article flat-card");
			  
			  for(int i=0; i<els3.size()-10; i++) {
				  
				  //map Object 생성
				  Map<String, Object> temp = new HashMap<String, Object>();
				  
				  temp.put("title", els3.get(i).getElementsByAttributeValue("class","article-title").text());
				  temp.put("region", els3.get(i).getElementsByAttributeValue("class", "article-region-name").text());
				  temp.put("price", els3.get(i).getElementsByAttributeValue("class", "article-price").text());
				  temp.put("urlLink", "https://www.daangn.com" + els2.get(i).getElementsByAttributeValue("class", "flea-market-article-link").attr("href"));
				  
				  //map object list에 넣어주기
				  items.add(temp);
			  }
			  
			  System.out.println(items);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return items;
	}

}
