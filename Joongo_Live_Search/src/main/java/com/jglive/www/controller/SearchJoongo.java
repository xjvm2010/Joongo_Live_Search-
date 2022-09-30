package com.jglive.www.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jglive.www.util.Crawling;
import com.jglive.www.util.DangCrawling;
import com.jglive.www.vo.SearchOption;

@Controller
@RequestMapping("/search")
public class SearchJoongo {
	
	@RequestMapping("/getContent")
	@ResponseBody
	public List<Map<String, Object>> getContent(SearchOption searchOption){
		List<Map<String, Object>> result;
		Crawling cra = new Crawling();
		result = cra.getContent_jn(searchOption);
		
		return result;
	}
	
	@RequestMapping("/getContent2")
	@ResponseBody
	public List<Map<String, Object>> getContent2(SearchOption searchOption){
		List<Map<String, Object>> result;
		DangCrawling cra = new DangCrawling();
		result = cra.getContent_dg(searchOption);
		
		return result;
	}
}
