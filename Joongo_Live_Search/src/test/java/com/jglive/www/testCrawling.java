package com.jglive.www;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class testCrawling {
	public static void main(String[] args) {
			List<Map<String, Object>> items = null;
			final String URL = "https://search-api.joongna.com/v25/list/category";
			Document doc;
			String jsonString = "{\"startIndex\": 0,\"searchStartTime\":\"\",\"filter\":{\"maxPrice\":2000000000,\"minPrice\":0,\"categoryDepth\":0,\"categorySeq\":0},\"searchQuantity\":6,\"categoryName1\":\"\",\"categoryName2\":\"\",\"categoryName3\":\"\",\"quantity\":6,\"firstQuantity\":6,\"searchWord\":\"중고\"}";
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
				for(Map<String, Object> temp : items) {
					System.out.println(temp.get("title"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
