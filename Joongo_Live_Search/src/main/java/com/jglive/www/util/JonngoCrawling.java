package com.jglive.www.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jglive.www.vo.SearchOption;

@Component("joongo")
public class JonngoCrawling {
	
	private SearchOption searchOption;
	
	List<Map<String, Object>> items = null;
	
	public List<Map<String, Object>> getItems(){
		getContent_jn();
		getContent_dg();
		return items;
	}
	
	public List<Map<String, Object>> getNewItem(List<Map<String, Object>> oldItemList){
		
		//새로운 리스트를 가져옴.
		getItems();
		
		//내보낼 데이터
		List<Map<String, Object>> newItemList = new ArrayList<Map<String,Object>>();
		
		for (int i = 0, j = 0; i < 12 ; i++) {
			Map<String,Object> oldItem  = oldItemList.get(j);
			Map<String,Object> newItem  = items.get(i);
			if(i <6) {
				j=i;
			}
			if(!newItem.get("title").equals(oldItem.get("title"))) {
				System.out.println("####################################################################");
				System.out.println(" new"+i+" : "+newItem.get("title") + "\n old"+j+" : "+ oldItem.get("title"));
				newItemList.add(newItem);
			}else {
				//제목값이 같은경우 
				j++;
			}
		}
		return newItemList;
	}
	
	public void setSearchOption(SearchOption searchOption)  {
		this.searchOption = searchOption;
	}

	
	public void getContent_jn() {
		
		final String URL = "https://search-api.joongna.com/v25/list/category";
		Document doc;
		String jsonString = "{\"startIndex\":"+(searchOption.getStartIndex()-1)+",\"searchStartTime\":\"\",\"filter\":{\"maxPrice\":"+searchOption.getMaxPrice()+",\"minPrice\":"+searchOption.getMinPrice()+",\"categoryDepth\":0,\"categorySeq\":0},\"searchQuantity\":"+searchOption.getQuantity()+",\"categoryName1\":\"\",\"categoryName2\":\"\",\"categoryName3\":\"\",\"quantity\":"+searchOption.getQuantity()+",\"firstQuantity\":"+searchOption.getQuantity()+",\"searchWord\":\""+searchOption.getSearchWord()+"\"}";
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36";
		
		try {
			doc = Jsoup.connect(URL)
					.requestBody(jsonString)
					.header("Accept", "application/json, text/plain, */*")
					.header("Accept-Encoding", " gzip, deflate, br")
					.header("Accept-Language", " ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("App-Version", " null")
					.header("Connection", " keep-alive")
					.header("Content-Length", " 281")
					.header("Content-Type", " application/json")
					.header("Host", " search-api.joongna.com")
					.header("Origin", " https://web.joongna.com")
					.header("Os-Type", " 2")
					.header("Sec-Fetch-Dest", " empty")
					.header("Sec-Fetch-Mode", " cors")
					.header("Sec-Fetch-Site", " same-site")
					.header("sec-ch-ua-mobile", " ?0")
					.header("sec-ch-ua-platform", "Windows")
					.ignoreContentType(true)
					.userAgent(userAgent)
					.post();
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> person = objectMapper.readValue(doc.text(), new TypeReference<Map<String, Object>>() {});
			Map<String, Object> data = (Map<String, Object>) person.get("data");
			
			items = (List<Map<String, Object>>) data.get("items");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getContent_dg() {
		
		String searchTxt = "";
		searchTxt = searchOption.getSearchWord();
		Document doc;
		
		try {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
