package com.jglive.www.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jglive.www.vo.SearchOption;

public class Crawling {
	
	public List<Map<String, Object>> getContent_jn(SearchOption searchOption){
		final String URL = "https://search-api.joongna.com/v25/list/category";
		ObjectMapper mapper = new ObjectMapper();
		Document doc;
		
		String jsonString = "{\"startIndex\":"+searchOption.getStartIndex()+",\"searchStartTime\":\"\",\"filter\":{\"maxPrice\":"+searchOption.getMaxPrice()+",\"minPrice\":"+searchOption.getMinPrice()+",\"categoryDepth\":0,\"categorySeq\":0},\"searchQuantity\":"+searchOption.getQuantity()+",\"categoryName1\":\"\",\"categoryName2\":\"\",\"categoryName3\":\"\",\"quantity\":"+searchOption.getQuantity()+",\"firstQuantity\":"+searchOption.getQuantity()+",\"searchWord\":\""+searchOption.getSearchWord()+"\"}";
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36";
		List<Map<String, Object>> items = null;
		
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
			
			Map<String, Object> naverPowerLinkResult = (Map<String, Object>) data.get("naverPowerLinkResult");
			items = (List<Map<String, Object>>) data.get("items");
			
			return items;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
