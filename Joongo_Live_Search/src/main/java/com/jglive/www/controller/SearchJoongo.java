package com.jglive.www.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jglive.www.util.Crawling;
import com.jglive.www.vo.SearchOption;

@Controller
@RequestMapping("/search")
public class SearchJoongo {
	
	@RequestMapping("/getContent")
	@ResponseBody
	public List<Map<String, Object>> getContent(SearchOption searchOption){
		List<Map<String, Object>> result = new ArrayList();
		Crawling cra = new Crawling();
		result = cra.getContent_jn(searchOption);
		
		return result;
	}
}
